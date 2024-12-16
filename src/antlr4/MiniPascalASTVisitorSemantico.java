package antlr4;

import org.antlr.v4.runtime.tree.ParseTree;

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

    String salida = "\nVisitor Semantico";
    public String generarSalida(ParseTree tree) {
        visit(tree);
        if (salida == "\nErrores Visitor Semantico\n") {
            salida += "No se encontraron errores\nCompilación exitosa!";
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
        opAritmetica = ctx.relationaloperator() == null;
        visit(ctx.simpleExpression());
        return null;
    }

    @Override
    public Void visitSimpleExpression(MiniPascalParser.SimpleExpressionContext ctx) {
        visit(ctx.term());
        if (ctx.additiveoperator() != null) {
            opAritmetica = true;
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

    public void validacionTipos(String tipoAsignado) {
        if (opAritmetica) {
            salida += (simboloIzquierdo instanceof SimboloFuncion)
                    ? "\nError: Los operandos de expresiones aritméticas deben ser de tipo Integer.\nValor de retorno de la función '" + simboloIzquierdo.getName() + "'."
                    : "\nError: Los operandos de expresiones aritméticas deben ser de tipo Integer.\nAsignación a la variable '" + simboloIzquierdo.getName() + "'.";
        }
        else {
            salida += (simboloIzquierdo instanceof SimboloFuncion)
                ? "\nError: La funcion " + simboloIzquierdo.getName() + " retorna un valor de tipo incorrecto.\nTipo a retornar: " + simboloIzquierdo.getType() + "\nTipo retornado: " + tipoAsignado
                : "\nError: En una asignación ambos lados deben tener el mismo tipo.\nLado izquierdo: " + simboloIzquierdo.getType() + "\nLado derecho: " + tipoAsignado;
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
                            validacionTipos(sim.getType());
                    }
                }
            }
            // Caso en que es el valor de retorno de una funcion
            else if (simboloIzquierdo instanceof SimboloFuncion) {
                for (Simbolo sim : ((SimboloFuncion) simboloIzquierdo).parameters) {
                    if (sim.getName().equalsIgnoreCase(variable)) {
                        if (!sim.getType().equalsIgnoreCase(simboloIzquierdo.getType())) {
                            // Validar que el tipo sea compatible al tipo esperado
                            validacionTipos(sim.getType());
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
                        salida += "\nError: En una asignación, ambos lados deben tener el mismo tipo.\nLado izquierdo: "
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
                validacionTipos(tipoConst);
            }
            visit(ctx.unsignedConstant());
        }
        // CASO DE NEGACION
        else if (ctx.NOT() != null) {
            if (!simboloIzquierdo.getType().equalsIgnoreCase("Boolean")) {
                salida += "\nError: El factor " + ctx.factor().getText() + " no es de tipo Boolean";
            }
            visit(ctx.factor());
        }
        // CASO DE VALOR BOOLEANO
        else if (ctx.unsignedConstant().bool_() != null) {
            if (opAritmetica) {
                salida += "\nLos operandos de expresiones aritméticas deben ser de tipo Integer";
            }
            else if (!simboloIzquierdo.getType().equalsIgnoreCase("Boolean")) {
                validacionTipos("Boolean");
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
                                    salida += "\n\nLlamado a la función " + nombreFuncion;
                                    salida += "\nError: Tipo de parámetro incompatible.\nTipo esperado: " + tipoRequerido + "\nTipo recibido: " + tipoRecibido + "\n";
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
                                    salida += "\n\nLlamado a la función " + nombreFuncion;
                                    salida += "\nError: Tipo de parámetro incompatible.\nTipo esperado: " + tipoRequerido + "\nTipo recibido: " + tipoConst + "\n";
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
        System.out.println("VALOOOOR nomvar " + nomVar);
        if (simboloIzquierdo.getType().equalsIgnoreCase("Integer")) {
            String valor = ctx.forList().initialValue().getText();
            System.out.println("VALOOOOR " + valor);
            // Es una variable
            if (tablaSimbolos.findSimbolo(nomVar)) {
                Simbolo variable = tablaSimbolos.getSimbolo(nomVar, currentScope);
                // Validar que la variable sea tipo int para asignarle el valor inicial
                if (!variable.getType().equalsIgnoreCase("Integer")) {
                    salida += "\nError: El iterador en un ciclo for debe ser de tipo Integer.";
                }
            }
            // Es una constante
            else if (!valor.matches("\\d+")) {
                // Validar que sea un numero
                salida += "\nError: El iterador en un ciclo for debe iniciar en un número entero.";
            }
        }
        else {
            salida += "\nError: En una asignación ambos lados deben tener el mismo tipo.\nLado izquierdo: "+ simboloIzquierdo.getType() + "\nLado derecho: Integer";
        }
        return null;
    }
}