package antlr4;

import org.antlr.v4.runtime.tree.ParseTree;
import java.util.ArrayList;

public class MiniPascalASTVisitorSemantico extends MiniPascalBaseVisitor<Void> {
    private final TablaSimbolos tablaSimbolos;
    private final ArrayList<ErrorCompilacion> errores;
    private String currentScope = "global";
    private Simbolo simboloIzquierdo = null;
    private Boolean opAritmetica = false;
    private String opAditivo = "";

    public MiniPascalASTVisitorSemantico(TablaSimbolos tablaSimbolos, ArrayList<ErrorCompilacion> errores) {
        this.tablaSimbolos = tablaSimbolos;
        this.errores = errores;
    }

    String salida = "\n\nErrores Visitor Semantico";
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
        salida += ("\n\nAsignación: '" + ctx.variable().getText() + "' = " + ctx.expression().getText());
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

    // Operaciones entre tipos compatibles
//    @Override
//    public Void visitExpression(MiniPascalParser.ExpressionContext ctx) {
////        salida += '\n' + ("    Expresion:");
////        salida += '\n' + ("    Expresion simple: " + ctx.simpleExpression().getText());
//        visit(ctx.simpleExpression());
//        if(ctx.relationaloperator() != null){
//            opAritmetica = false;
////            salida += '\n' + ("      Op relacional: " + ctx.relationaloperator().getText());
//            visit(ctx.simpleExpression());
//        }
//        return null;
//    }

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
//            salida += '\n' + ("\nOperador Multiplicativo: " + ctx.multiplicativeoperator().getText());
            visit(ctx.term()); // Visitar el nodo del término
        }
        return null;
    }

    public void validacionTipos(String tipoAsignado) {
        if (opAritmetica) {
            salida += "\nError: Los operandos de expresiones aritméticas deben ser de tipo Integer";
        }
        else {
            if (simboloIzquierdo instanceof SimboloFuncion){
                salida += "\nError: En una función se debe retornar un valor del tipo correcto.\nTipo a retornar: "+simboloIzquierdo.getType() + "\nTipo retornado: " + tipoAsignado;
            }
            else {
                salida += "\nError: En una asignación ambos lados deben tener el mismo tipo.\nLado izquierdo: "+simboloIzquierdo.getType() + "\nLado derecho: " + tipoAsignado;
            }
        }
    }

    @Override
    public Void visitFactor(MiniPascalParser.FactorContext ctx) {
        if (ctx.variable() != null) {
            // CASO EN QUE ES UNA VARIABLE
            String variable = ctx.variable().getText();
            if (tablaSimbolos.findSimbolo(variable)) {
                Simbolo sim = tablaSimbolos.getSimbolo(variable, currentScope);
                if (sim != null) {
                    if (!(simboloIzquierdo instanceof SimboloArreglo)) {
                        if (sim.getType().equalsIgnoreCase(simboloIzquierdo.getType())) {
                            // Realizar la suma/resta de los valores
                            if (opAritmetica) {
                                int valorActual = 0, valorVariable = 0;
                                if (simboloIzquierdo.getValue() instanceof Integer && sim.getValue() instanceof Integer) {
                                    valorActual = (Integer)simboloIzquierdo.getValue();
                                    valorVariable = (Integer)sim.getValue();
                                }
                                else if (simboloIzquierdo.getValue() instanceof String && sim.getValue() instanceof String) {
                                    valorActual = Integer.parseInt((String)simboloIzquierdo.getValue());
                                    valorVariable = Integer.parseInt((String)sim.getValue());
                                }
                                simboloIzquierdo.setValue(valorActual + (opAditivo.equals("+") ? +valorVariable : -valorVariable));
                            }
                            // Realizar la asignacion del valor solito ej. x = y
                            else {
                                simboloIzquierdo.setValue(sim.getValue());
                            }
                        }
                        else {
                            validacionTipos(sim.getType());
                        }
                        visit(ctx.variable());
                    }
                }
            }
             // Visitar el nodo de la variable
        } else if (ctx.expression() != null) {
            // CASO EN QUE UN TERMINO ES OTRA EXPRESION -> (a + b)
            visit(ctx.expression());
        } else if (ctx.functionDesignator() != null) {
            // CASO DE LLAMADO A FUNCIONES
//            String nomFuncion = ctx.functionDesignator().identifier().getText();
//            if (tablaSimbolos.findSimbolo(nomFuncion)) {
//                Simbolo sim = tablaSimbolos.getSimbolo(nomFuncion, currentScope);
//                if (sim != null) {
//                    if (!(simboloIzquierdo instanceof SimboloFuncion)) {
//                        SimboloFuncion funcion = (SimboloFuncion)sim;
//                        if(!simboloIzquierdo.getType().equalsIgnoreCase(funcion.getType())) {
//                            salida += "\nError: En una asignación, ambos lados deben tener el mismo tipo.\nLado izquierdo: "+simboloIzquierdo.getType() + "\nValor retornado en el llamado: Integer";
//                        }
//                        else {
//                            visit(ctx.functionDesignator());
//                            simboloIzquierdo.setValue(funcion.getValue());
//                        }
//                    }
//                }
//            }

            visit(ctx.functionDesignator());
        } else if (ctx.unsignedConstant() != null) {
            // CASO DE VALOR CONSTANTE (ej. 10, 'Hola'...)
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
            if (simboloIzquierdo.getType().equalsIgnoreCase(tipoConst)
                    && !(simboloIzquierdo instanceof SimboloArreglo)) {
                if (simboloIzquierdo.getType().equalsIgnoreCase("Integer")) {
                    // Caso en que sea un Integer
                    int numero = Integer.parseInt(constante);
                    if (opAritmetica) {
                        int valorActual = simboloIzquierdo.getValue() == null ? 0 : (Integer)simboloIzquierdo.getValue();
                        // Hacer la suma/resta con el valor ya almacenado
                        simboloIzquierdo.setValue(valorActual + (opAditivo.equals("+") ? + numero : - numero));
                    }
                    else {
                        // Cuando no es operacion aritmetica, solo se asigna el valor
                        simboloIzquierdo.setValue(numero);
                    }
                }
                else {
                    // Casos en que es un String, Char o Boolean
                    simboloIzquierdo.setValue(constante);
                }
            }
            else {
                validacionTipos(tipoConst);
            }
            visit(ctx.unsignedConstant());
        } else if (ctx.NOT() != null) {
            if (opAritmetica) {
                salida += "\nError: Los operandos de expresiones aritméticas deben ser de tipo Integer";
            }
            else {
                simboloIzquierdo.setValue(ctx.NOT().getText() + " NOT");
            }
            visit(ctx.factor());
        }
        else if (ctx.unsignedConstant().bool_() != null) {
            if (opAritmetica) {
                salida += "\nLos operandos de expresiones aritméticas deben ser de tipo Integer";
            }
            else {
                simboloIzquierdo.setValue(ctx.unsignedConstant().bool_().getText() + " bool");
            }
        }
        return null;
    }

    @Override
    public Void visitFunctionDesignator(MiniPascalParser.FunctionDesignatorContext ctx) {
        String nombreFuncion = ctx.identifier().getText();
        salida += "\n\nLlamado a la función " + nombreFuncion;
        if (tablaSimbolos.findSimbolo(nombreFuncion)) {
            Simbolo simbolo = tablaSimbolos.getSimbolo(nombreFuncion, nombreFuncion);
            System.out.println("existo " + simbolo.getScope());
            if (simbolo != null) {
                if (simbolo instanceof SimboloFuncion) {
                    SimboloFuncion funcion = (SimboloFuncion)simbolo;
                    if (ctx.parameterList() != null) {
                        for (int i = 0; i < ctx.parameterList().actualParameter().size(); i++) {
                            String parametro = ctx.parameterList().actualParameter(i).getText();
                            if (tablaSimbolos.findSimbolo(parametro)) {
                                // Es una variable
                                System.out.println("param " + parametro);
                                Simbolo param = tablaSimbolos.getSimbolo(parametro, nombreFuncion);
                                String tipoRequerido = funcion.parameters.get(i).getType(),
                                    tipoRecibido = param.getType();
                                if (!tipoRequerido.equalsIgnoreCase(tipoRecibido)) {
                                    salida += "\nError: Tipo de parámetro incompatible.\nTipo esperado: " + tipoRequerido + "\nTipo recibido: " + tipoRecibido + "\n";
                                    return null;
                                }
                            }
                        }
                    }
                }
            }
        }
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
        if (tablaSimbolos.findSimbolo(nomVar)) {
            Simbolo variable = tablaSimbolos.getSimbolo(nomVar, currentScope);
            // Validar que la variable sea tipo int para asignarle el valor inicial
            if (variable.getType().equalsIgnoreCase("Integer")) {
                // Validar que sea un numero
                String valor = ctx.forList().initialValue().getText();
                if (valor.matches("\\d+")) {
                    // Asignar valor inicial a la variable
                    variable.setValue(ctx.forList().initialValue().getText());
                    visit(ctx.forList().initialValue());
                    visit(ctx.forList().finalValue());
                    visit(ctx.statement());
                }
                else if (tablaSimbolos.findSimbolo(valor)) {
                    Simbolo varAsignar = tablaSimbolos.getSimbolo(valor, currentScope);
                    if (varAsignar.getType().equalsIgnoreCase("Integer")) {
                        variable.setValue(varAsignar.getValue());
                    }
                }
                else {
                    salida += "\nError: El iterador en un ciclo for debe iniciar en un número entero.";
                }
            }
            else {
                salida += "\nError: En una asignación ambos lados deben tener el mismo tipo.\nLado izquierdo: "+ variable.getType() + "\nLado derecho: Integer";
            }
        }
        return null;
    }
}