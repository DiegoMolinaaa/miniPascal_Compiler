package antlr4;

import org.antlr.v4.runtime.tree.ParseTree;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class MiniPascalASTVisitorSemantico extends MiniPascalBaseVisitor<Void> {
    private final TablaSimbolos tablaSimbolos;
    private final ArrayList<ErrorCompilacion> errores;
    private String currentScope = "global", currentType = "Integer";
    private Simbolo simboloIzquierdo = null;
    private Boolean opAritmetica = false;

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
        salida += "\n" + ("Se asigna el valor de " + ctx.expression().getText() + " a la variable " + ctx.variable().getText());

        String variable = ctx.variable().getText();
        if (variable.contains("[")) {
            int indiceLlave = variable.indexOf('[', 0);
            if(indiceLlave > 0) {
                variable = variable.substring(0, indiceLlave);
            }
        }
        if (tablaSimbolos.findSimbolo(variable)) {
            Simbolo sim = tablaSimbolos.getSimbolo(variable, currentScope);
            opAritmetica = false;

            if (sim != null) {
                if (sim instanceof SimboloArreglo) {
//                    salida += '\n' + ("ARREGLO " + variable + " --> " + sim.getType());
                } else if (sim instanceof SimboloFuncion) {
//                    salida += '\n' + ("FUNCION " + variable + " --> " + sim.getType());
                }
                sim.getType();
//                salida += '\n' + ("VARIABLE " + sim.getName() + " --> " + sim.getType());

                currentType = sim.getType();
                simboloIzquierdo = sim;
                visit(ctx.expression());

            }
        }
        else {
            Simbolo sim = tablaSimbolos.getSimbolo(variable, variable);
            opAritmetica = false;

            if (sim != null) {
                 if (sim instanceof SimboloFuncion) {
//                    salida += '\n' + ("FUNCION " + variable + " --> " + sim.getType());
                }
                currentType = sim.getType();
                simboloIzquierdo = sim;
                visit(ctx.expression());
            }

        }
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
            salida += '\n' + ("      Op relacional: " + ctx.relationaloperator().getText());
            visit(ctx.simpleExpression());
        }
        return null;
    }

    @Override
    public Void visitSimpleExpression(MiniPascalParser.SimpleExpressionContext ctx) {
        visit(ctx.term()); // Visitar el nodo del término
        if (ctx.additiveoperator() != null) {
            salida += ("\nOperador Aditivo: " + ctx.additiveoperator().getText());
            opAritmetica = true;
            visit(ctx.simpleExpression()); // Visitar el nodo de la expresión simple
        }

        return null;
    }

    @Override
    public Void visitTerm(MiniPascalParser.TermContext ctx) {
        visit(ctx.signedFactor());
        if (ctx.multiplicativeoperator() != null) {
            opAritmetica = false;
            salida += '\n' + ("\nOperador Multiplicativo: " + ctx.multiplicativeoperator().getText());
            visit(ctx.term()); // Visitar el nodo del término
        }
        return null;
    }
    public Void visitFactor(MiniPascalParser.FactorContext ctx) {
//        salida += "\nFactor ";
        if (ctx.variable() != null) {
            // CASO EN QUE UN TERMINO ES VARIABLE
            String variable = ctx.variable().getText();
//            salida += "\n" + variable + " a";
            Simbolo sim = tablaSimbolos.getSimbolo(variable, currentScope);
            if (sim != null) {
//                if ((sim.getCategory().equalsIgnoreCase("array")
//                        && !simboloIzquierdo.getCategory().equalsIgnoreCase("array"))
//                || (simboloIzquierdo.getCategory().equalsIgnoreCase("array")
//                && !sim.getCategory().equalsIgnoreCase("array"))) {
//                    salida += "\nError: En una asignación, ambos lados deben tener la misma categoría.\nLado izquierdo: "+ simboloIzquierdo.getCategory() + "\nLado derecho: " + sim.getCategory();
//                }
//                else
                    if (!sim.getType().equalsIgnoreCase(simboloIzquierdo.getType())) {
                    salida += "\nError: En una asignación, ambos lados deben tener el mismo tipo.\nLado izquierdo: "+simboloIzquierdo.getType() + "\nLado derecho: " + sim.getType();
                }

            }
            visit(ctx.variable()); // Visitar el nodo de la variable
        } else if (ctx.expression() != null) {
            // CASO EN QUE UN TERMINO ES OTRA EXPRESION -> (a + b)
//            salida += ctx.expression().getText() + " b";
            visit(ctx.expression()); // Visitar el nodo de la expresión
        } else if (ctx.functionDesignator() != null) {
            // CASO DE NUMERO LITERAL
//            salida += ctx.functionDesignator().getText() + " c";
            visit(ctx.functionDesignator()); // Visitar el nodo del designador de función
        } else if (ctx.unsignedConstant() != null) {
//            salida += ctx.unsignedConstant().getText() + simboloIzquierdo.getType() + " d";
            if (!simboloIzquierdo.getType().equalsIgnoreCase("Integer")) {
                salida += "\nError: En una asignación, ambos lados deben tener el mismo tipo.\nLado izquierdo: "+simboloIzquierdo.getType() + "\nLado derecho: Integer";
            }
            visit(ctx.unsignedConstant());// Visitar el nodo de la constante no firmada
        }else if (ctx.NOT() != null) {
//            salida += "\nNota: " + ctx.NOT().getText() + "e";
////            salida += '\n' + ("NOT");
            if (opAritmetica) {
                salida += "\nError: Los operandos de expresiones aritméticas deben ser de tipo Integer";
            }
            visit(ctx.factor()); // Visitar el nodo del factor
        } else if (ctx.bool_() != null) {
            if (opAritmetica) {
                salida += "\nLos operandos de expresiones aritméticas deben ser de tipo Integer";
            }
            else {

//            salida += '\n' + ("Valor booleano: " + ctx.bool_().getText());
            }
        }
        return null;
    }

    @Override
    public Void visitUnsignedConstant(MiniPascalParser.UnsignedConstantContext ctx) {
        if (ctx.unsignedNumber() != null) {
//            salida += '\n' + ("Numero Unsigned: " + ctx.unsignedNumber().getText());
        } else if (ctx.constantChr() != null) {
//            salida += '\n' + ("Char constante: " + ctx.constantChr().getText());
        } else if (ctx.string() != null) {
//            salida += '\n' + ("Valor String: " + ctx.string().getText());
        } else if (ctx.NIL() != null) {
//            salida += '\n' + ("NIL");
        }
        return null;
    }
}