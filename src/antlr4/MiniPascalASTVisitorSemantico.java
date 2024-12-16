package antlr4;

import org.antlr.v4.runtime.tree.ParseTree;

import javax.crypto.spec.OAEPParameterSpec;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class MiniPascalASTVisitorSemantico extends MiniPascalBaseVisitor<Void> {
    private final TablaSimbolos tablaSimbolos;
    private String currentScope = "global";
    private Simbolo simboloIzquierdo = null;
    private Boolean opAritmetica = false;
    private String opAditivo = "";

    public MiniPascalASTVisitorSemantico(TablaSimbolos tablaSimbolos) {
        this.tablaSimbolos = tablaSimbolos;
    }

    String salida = "";
    public String generarSalida(ParseTree tree) {
        visit(tree);
        if (salida.isEmpty()) {
            salida += "No se encontraron errores.\n¡Compilación exitosa!";
        }
        return salida;
    }

    @Override
    public Void visitAssignmentStatement(MiniPascalParser.AssignmentStatementContext ctx) {
        String variable = ctx.variable().getText();
        // Extraer solo el nombre de la variable array
        if (variable.contains("[")) {
            int indiceLlave = variable.indexOf('[', 0);
            if(indiceLlave > 0) {
                variable = variable.substring(0, indiceLlave);
            }
        }
        String scope = currentScope;
        if (!tablaSimbolos.findSimbolo(variable)) {
            // Si es una funcion, cambiar el scope al scope de la funcion
            scope = variable;
        }
        Simbolo sim = tablaSimbolos.getSimbolo(variable, scope);
        opAritmetica = false;
        if (sim != null) {
            simboloIzquierdo = sim;
        }
        visit(ctx.expression());
        return null;
    }

//    // Operaciones entre tipos compatibles
    @Override
    public Void visitExpression(MiniPascalParser.ExpressionContext ctx) {
        opAritmetica = false;
        visit(ctx.simpleExpression());
        return null;
    }

    @Override
    public Void visitSimpleExpression(MiniPascalParser.SimpleExpressionContext ctx) {
        visit(ctx.term());
        if (ctx.additiveoperator() != null) {
            opAritmetica = ctx.additiveoperator().getText().equals("+");
            opAditivo = ctx.additiveoperator().getText();
            visit(ctx.simpleExpression());
        }
        return null;
    }

    @Override
    public Void visitTerm(MiniPascalParser.TermContext ctx) {
        visit(ctx.signedFactor());
        if (ctx.multiplicativeoperator() != null) {
            opAritmetica = false;
            visit(ctx.term());
        }
        return null;
    }

    public void validacionTipos(String tipoAsignado, int linea, int columna) {
        if (opAritmetica) {
            salida += "\n\nError en la linea " + linea + ", columna " + columna + ": Los operandos de expresiones aritméticas deben ser de tipo Integer.";
        }
        else {
            salida += (simboloIzquierdo instanceof SimboloFuncion)
                    ? ("\n\nError en la linea " + linea + ", columna " + columna + ": La funcion " + simboloIzquierdo.getName() + " retorna un valor de tipo incorrecto.\nTipo a retornar: " + simboloIzquierdo.getType() + "\nTipo retornado: " + tipoAsignado)
                    : ("\n\nError en la linea " + linea + ", columna " + columna + ": En una asignación ambos lados deben tener el mismo tipo.\nLado izquierdo: " + simboloIzquierdo.getType() + "\nLado derecho: " + tipoAsignado);
        }
    }

    @Override
    public Void visitFactor(MiniPascalParser.FactorContext ctx) {
        // CASO EN QUE ES UNA VARIABLE
        if (ctx.variable() != null) {
            String variable = ctx.variable().getText();
            // Caso en que se asignara el valor a otra variable
            if (tablaSimbolos.findSimbolo(variable)) {
                Simbolo sim = tablaSimbolos.getSimbolo(variable, currentScope);
                if (sim != null) {
                    if (!(simboloIzquierdo instanceof SimboloArreglo)) {
                        // Validar que el tipo sea compatible al tipo esperado
                        if (!sim.getType().equalsIgnoreCase(simboloIzquierdo.getType()))
                            validacionTipos(sim.getType(), ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
                    }
                }
            }
            // Caso en que es el valor de retorno de una funcion
            else if (simboloIzquierdo instanceof SimboloFuncion) {
                for (Simbolo sim : ((SimboloFuncion) simboloIzquierdo).parameters) {
                    if (sim.getName().equalsIgnoreCase(variable)) {
                        if (!sim.getType().equalsIgnoreCase(simboloIzquierdo.getType())) {
                            // Validar que el tipo sea compatible al tipo esperado
                            validacionTipos(sim.getType(), ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
                        }
                        break;
                    }
                }
            }

            // Visitar el nodo de la variable
            visit(ctx.variable());
        }
        // CASO EN QUE ES UNA EXPRESION -> (a + b)
        else if (ctx.expression() != null) {
            visit(ctx.expression());
        }
        // CASO DE LLAMADO A FUNCIONES
        else if (ctx.functionDesignator() != null) {
            String nomFuncion = ctx.functionDesignator().identifier().getText();
            if (tablaSimbolos.findSimbolo(nomFuncion)) {
                Simbolo simFuncion = tablaSimbolos.getSimbolo(nomFuncion, currentScope);
                if (simFuncion != null) {
                    // Validar si los tipos son compatibles
                    if(!simboloIzquierdo.getType().equalsIgnoreCase(simFuncion.getType())) {
                        salida += "\n\nError en la linea " + ctx.getStart().getLine() + ", columna " + ctx.getStart().getCharPositionInLine() + ": En una asignación, ambos lados deben tener el mismo tipo.\nLado izquierdo: "
                                +simboloIzquierdo.getType() + "\nTipo retornado en el llamado: " + simFuncion.getType();
                    }
                }
            }
            visit(ctx.functionDesignator());
        }
        // CASO DE VALOR CONSTANTE (ej. 10, 'Hola'...)
        else if (ctx.unsignedConstant() != null) {
            String tipoConst = "Integer",
                constante = ctx.unsignedConstant().getText();
            if (constante.contains("'") && constante.length() >= 3) {
                constante = constante.substring(1, constante.indexOf("'", 1));
                tipoConst = constante.length() == 1 ? "Character" : "String";
            }
            else if (constante.equalsIgnoreCase("true") || constante.equalsIgnoreCase("false")) {
                tipoConst = "Boolean";
            }
            // Validar el tipo de la constante con el tipo a asignar
            if (!simboloIzquierdo.getType().equalsIgnoreCase(tipoConst)) {
                validacionTipos(tipoConst, ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
            }
            visit(ctx.unsignedConstant());
        }
        // CASO DE NEGACION
        else if (ctx.NOT() != null) {
            if (!simboloIzquierdo.getType().equalsIgnoreCase("Boolean")) {
                salida += "\n\nError en la linea " + ctx.getStart().getLine() + ", columna " + ctx.getStart().getCharPositionInLine() + ": El factor " + ctx.factor().getText() + " no es de tipo Boolean";
            }
            visit(ctx.factor());
        }
        // CASO DE VALOR BOOLEANO
        else if (ctx.unsignedConstant().bool_() != null) {
            if (opAritmetica) {
                salida += "\nLos operandos de expresiones aritméticas deben ser de tipo Integer";
            }
            else if (!simboloIzquierdo.getType().equalsIgnoreCase("Boolean")) {
                validacionTipos("Boolean", ctx.getStart().getLine(), ctx.getStart().getCharPositionInLine());
            }
        }
        return null;
    }

    @Override
    public Void visitFunctionDesignator(MiniPascalParser.FunctionDesignatorContext ctx) {
        String nombreFuncion = ctx.identifier().getText();

        String previousScope = currentScope;
        currentScope = nombreFuncion;
        tablaSimbolos.enterScope(currentScope);

        if (tablaSimbolos.findSimbolo(nombreFuncion)) {
            Simbolo simbolo = tablaSimbolos.getSimbolo(nombreFuncion, currentScope);
            if (simbolo != null) {
                if (simbolo instanceof SimboloFuncion) {
                    SimboloFuncion funcion = (SimboloFuncion)simbolo;
                    if (ctx.parameterList() != null) {
                        for (int i = 0; i < ctx.parameterList().actualParameter().size(); i++) {
                            String parametro = ctx.parameterList().actualParameter(i).getText();
                            String tipoRequerido = funcion.parameters.get(i).getType();
                            if (tablaSimbolos.findSimbolo(parametro)) {
                                // Es una variable
                                Simbolo simParametro = tablaSimbolos.getSimbolo(parametro, previousScope);
                                String tipoRecibido = simParametro.getType();
                                if (!tipoRequerido.equalsIgnoreCase(tipoRecibido)) {
                                    salida += "\n\nError en la linea " + ctx.getStart().getLine() + ", columna " + ctx.getStart().getCharPositionInLine()
                                            + ": Tipo de parámetro incompatible.\nTipo esperado: " + tipoRequerido + "\nTipo recibido: " + tipoRecibido + "\n";
                                }
                            }
                            else {
                                // Es una constante
                                String tipoConst = "Integer",
                                constante = funcion.parameters.get(i).getName();
                                if (constante.contains("'") && constante.length() >= 3) {
                                    constante = constante.substring(1, constante.indexOf("'", 1));
                                    tipoConst = constante.length() == 1 ? "Character" : "String";
                                }
                                else if (constante.equalsIgnoreCase("true") || constante.equalsIgnoreCase("false")) {
                                    tipoConst = "Boolean";
                                }
                                // Validar el tipo de la constante con el tipo a asignar
                                if (!simboloIzquierdo.getType().equalsIgnoreCase(tipoConst)) {
                                    salida += "\n\nError en la linea " + ctx.getStart().getLine() + ", columna " + ctx.getStart().getCharPositionInLine()
                                            + ": Tipo de parámetro incompatible.\nTipo esperado: " + tipoRequerido + "\nTipo recibido: " + tipoConst + "\n";
                                }
                            }
                        }
                    }
                }
            }
        }
        currentScope = previousScope;
        tablaSimbolos.exitScope();
        visit(ctx.parameterList());
        return null;
    }

    @Override
    public Void visitParameterList(MiniPascalParser.ParameterListContext ctx) {
        if(ctx == null){
            // No hay parametros
            return null;
        }
        // Si hay parametros
        for (MiniPascalParser.ActualParameterContext actualParameterContext : ctx.actualParameter()) {
            visit(actualParameterContext);
        }
        return null;
    }

    @Override
    public Void visitActualParameter(MiniPascalParser.ActualParameterContext ctx) {
        visit(ctx.expression());
        return null;
    }

    @Override
    public Void visitForStatement(MiniPascalParser.ForStatementContext ctx) {
        String nomVar = ctx.identifier().getText();
        simboloIzquierdo = tablaSimbolos.getSimbolo(nomVar, currentScope);
        if (simboloIzquierdo.getType().equalsIgnoreCase("Integer")) {
            String valor = ctx.forList().initialValue().getText();
            // Es una variable
            if (tablaSimbolos.findSimbolo(valor)) {
                Simbolo variable = tablaSimbolos.getSimbolo(valor, currentScope);
                // Validar que la variable sea tipo int para asignarle el valor inicial
                if (!variable.getType().equalsIgnoreCase("Integer")) {
                    salida += "\n\nError en la linea " + ctx.getStart().getLine() + ", columna " + ctx.getStart().getCharPositionInLine() + ": El valor inicial de un iterador en un ciclo for debe ser de tipo Integer.";
                }
            }
            // Es una constante
            else if (!valor.matches("\\d+")) {
                // Validar que sea un numero
                salida += "\n\nError en la linea " + ctx.getStart().getLine() + ", columna " + ctx.getStart().getCharPositionInLine() + ": El iterador en un ciclo for debe iniciar en un número entero.";
            }
        }
        else {
            salida += "\n\nError en la linea " + ctx.getStart().getLine() + ", columna " + ctx.getStart().getCharPositionInLine() + ": El iterador en un ciclo for debe ser de tipo Integer.";
        }
        visit(ctx.forList().initialValue());
        visit(ctx.forList().finalValue());
        visit(ctx.statement());
        return null;
    }
}