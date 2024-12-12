package antlr4;

import org.antlr.v4.runtime.tree.ParseTree;

import java.sql.SQLOutput;
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

    // Metodos movidos

    @Override
    public Void visitAssignmentStatement(MiniPascalParser.AssignmentStatementContext ctx) {
        String expression = ctx.expression().getText();
        String variable = ctx.variable().getText();
        salida += '\n' + ("Asignación: '" + ctx.variable().getText() + "' = " + ctx.expression().getText());
//        if (tablaSimbolos.findSimbolo(expression)) {
//            // Caso en que es una asignacion directa de una variable a otra, ej. x = y
//            tablaSimbolos.getSimbolo(variable, currentScope).setValue(tablaSimbolos.getSimbolo(expression, currentScope).getValue());
//        }
//        else {
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
//        }
        visit(ctx.expression());
        return null;
    }

    // Operaciones entre tipos compatibles
    @Override
    public Void visitExpression(MiniPascalParser.ExpressionContext ctx) {
//        salida += '\n' + ("    Expresion:");
//        salida += '\n' + ("    Expresion simple: " + ctx.simpleExpression().getText());
        visit(ctx.simpleExpression());
        if(ctx.relationaloperator() != null){
            opAritmetica = false;
//            salida += '\n' + ("      Op relacional: " + ctx.relationaloperator().getText());
            visit(ctx.simpleExpression());
        }
        return null;
    }

    @Override
    public Void visitSimpleExpression(MiniPascalParser.SimpleExpressionContext ctx) {
        visit(ctx.term()); // Visitar el nodo del término
        if (ctx.additiveoperator() != null) {
//            salida += ("\nOperador Aditivo: " + ctx.additiveoperator().getText());
            opAritmetica = true;
            opAditivo = ctx.additiveoperator().getText();
            visit(ctx.simpleExpression()); // Visitar el nodo de la expresión simple
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

    @Override
    public Void visitFactor(MiniPascalParser.FactorContext ctx) {
        if (ctx.variable() != null) {
            // CASO EN QUE ES UNA VARIABLE
            String variable = ctx.variable().getText();
            if (tablaSimbolos.findSimbolo(variable)) {
                Simbolo sim = tablaSimbolos.getSimbolo(variable, currentScope);
                if (sim != null) {
//                if (!sim.getCategory().equalsIgnoreCase(simboloIzquierdo.getCategory())) {
//                    salida += "\nError: En una asignación, ambos lados deben tener la misma categoría.\nLado izquierdo: "+ simboloIzquierdo.getCategory() + "\nLado derecho: " + sim.getCategory();
//                }
                    if (!sim.getType().equalsIgnoreCase(simboloIzquierdo.getType())) {
                        if (simboloIzquierdo instanceof SimboloFuncion){
                            salida += "\nError: En una función, se debe retornar un valor del tipo correcto.\nTipo a retornar: "+simboloIzquierdo.getType() + "\nTipo retornado: " + sim.getType();
                        }
                        else {
                            salida += "\nError: En una asignación, ambos lados deben tener el mismo tipo.\nLado izquierdo: "+simboloIzquierdo.getType() + "\nLado derecho: " + sim.getType();
                        }
                    }
                    else {
                        if (opAritmetica) {
//                            if (simboloIzquierdo.getValue() instanceof Integer) {
//                                System.out.println("SIMBOLO IZ ES INT " + simboloIzquierdo.getName());
//                            }
//                            if (sim.getValue() instanceof Integer) {
//                                System.out.println("SIMBOLO ES INT");
//                            }
//                            if (simboloIzquierdo.getValue() instanceof String) {
//                                System.out.println("SIMBOLO IZ ES STRIN " + simboloIzquierdo.getName());
//                            }
//                            if (sim.getValue() instanceof String) {
//                                System.out.println("SIMBOLO ES STRING " + sim.getName());
//                            }

                            if (simboloIzquierdo.getValue() instanceof Integer && sim.getValue() instanceof Integer) {
                                simboloIzquierdo.setValue((Integer)simboloIzquierdo.getValue() + (Integer)sim.getValue());
                            }
                            else if (simboloIzquierdo.getValue() instanceof String && sim.getValue() instanceof String) {
                                int simIzValue = Integer.parseInt((String) simboloIzquierdo.getValue());
                                int simValue = Integer.parseInt((String) sim.getValue());
                                simboloIzquierdo.setValue(simIzValue+simValue);
                            }
                        }
                        else {
                            System.out.println("SIMBOLO IZ ES " + simboloIzquierdo.getName() + " = " + simboloIzquierdo.getValue());
                            simboloIzquierdo.setValue(sim.getValue());
                        }
                    }
                }
            }
            visit(ctx.variable()); // Visitar el nodo de la variable
        } else if (ctx.expression() != null) {
            // CASO EN QUE UN TERMINO ES OTRA EXPRESION -> (a + b)
            visit(ctx.expression());
        } else if (ctx.functionDesignator() != null) {
            // CASO DE NUMERO LITERAL
//            if (!simboloIzquierdo.getType().equalsIgnoreCase("Integer")) {
//                salida += "\nError: En una asignación, ambos lados deben tener el mismo tipo.\nLado izquierdo: "+simboloIzquierdo.getType() + "\nLado derecho: Integer";
//            }
//            else {
//            }
                simboloIzquierdo.setValue(ctx.functionDesignator().getText() + " funcDesig");
            visit(ctx.functionDesignator());
        } else if (ctx.unsignedConstant() != null) {
            // CASO DE CONSTANTE
            // revisar caso en que sea un arreglo, asignar el int a arr[indice]
            if (!simboloIzquierdo.getType().equalsIgnoreCase("Integer")) {
                salida += "\nError: En una asignación, ambos lados deben tener el mismo tipo.\nLado izquierdo: "+simboloIzquierdo.getType() + "\nLado derecho: Integer";
            }
            else {
                int nuevo = Integer.parseInt(ctx.unsignedConstant().getText());
                if (opAritmetica) {
                    // Hacer la suma/resta con el valor ya almacenado
                    simboloIzquierdo.setValue((Integer)simboloIzquierdo.getValue() + (opAditivo.equals("+") ? + nuevo : - nuevo));
                }
                else {
                    simboloIzquierdo.setValue(nuevo);
                }
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
        } else if (ctx.bool_() != null) {
            if (opAritmetica) {
                salida += "\nLos operandos de expresiones aritméticas deben ser de tipo Integer";
            }
            else {
                simboloIzquierdo.setValue(ctx.bool_().getText() + " bool");
            }
        }
        return null;
    }

}

//
//            if (sim.getCategory().equalsIgnoreCase("array")) {
////                    El índice de una expresión de array debe ser mayor a cero
//                String indice = ctx.variable().getText();
//                indice = indice.substring(indice.indexOf('[')+1, indice.indexOf(']'));
//
//                // Arreglo de dos dimensiones
//                if (indice.contains(",")) {
//                    String[] parts = indice.split(",");
//                    String indice1 = parts[0], indice2 = parts[1];
//                    if (Integer.parseInt(indice1) == 0 || Integer.parseInt(indice2) == 0) {
//                        salida += "\nError: El índice de una expresión de array debe ser mayor a cero.";
//                    }
//                }
//                // Arreglo de una dimensión
//                else {
//                    if (Integer.parseInt(indice) == 0) {
//                        salida += "\nError: El índice de una expresión de array debe ser mayor a cero.";
//                    }
//                }
//            }


//simboloIzquierdo.setValue(ctx.variable().getText());
// CASO EN QUE UN TERMINO ES VARIABLE
//            salida += "\n" + variable + " a";
//String variable = ctx.variable().getText();
//            if (variable.contains("[")) {
////                    El índice de una expresión de array debe ser mayor a cero
//                String indice = ctx.variable().getText();
//                indice = indice.substring(indice.indexOf('[')+1, indice.indexOf(']'));
//
//                // Arreglo de dos dimensiones
//                if (indice.contains(",")) {
//                    String[] parts = indice.split(",");
//                    String indice1 = parts[0], indice2 = parts[1];
//                    if (!tablaSimbolos.findSimbolo(indice1)) {
//                        if (Integer.parseInt(indice1) == 0 || Integer.parseInt(indice2) == 0) {
//                            salida += "\nError: El índice de una expresión de array debe ser mayor a cero.";
//                        }
//                    }
//                    else {
//                        if (tablaSimbolos.getSimbolo(indice1, currentScope).getType() == "Integer"
//                                && tablaSimbolos.getSimbolo(indice2, currentScope).getType() == "Integer") {
//                            if ((Integer) tablaSimbolos.getSimbolo(indice1, currentScope).getValue() > 0) {
//
//                            }
//                        }
//                    }
//                }
//                // Arreglo de una dimensión
//                else {
//                    if (!tablaSimbolos.findSimbolo(indice)) {
//                        if (Integer.parseInt(indice) == 0) {
//                            salida += "\nError: El índice de una expresión de array debe ser mayor a cero.";
//                        }
//                    }
//                }
//            }