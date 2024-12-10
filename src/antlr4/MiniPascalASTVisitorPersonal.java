package antlr4;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

public class MiniPascalASTVisitorPersonal extends MiniPascalBaseVisitor<Object> {
    String salida = "";
    public String generarSalida(ParseTree tree) {
        visit(tree);
        return salida;
    }

    @Override
    public Void visitProgram(MiniPascalParser.ProgramContext ctx) {
        salida += "Program:\n  Encabezado Programa:";
        visit(ctx.programHeading());
        salida += "\n  Block:";
        visit(ctx.block());
        salida += "\nFin de Programa";
        return null;
    }

    @Override
    public Void visitProgramHeading(MiniPascalParser.ProgramHeadingContext ctx) {
        salida += "      PROGRAM " + ctx.identifier().getText();
        return null;
    }


    @Override
    public Void visitVariableDeclarationPart(MiniPascalParser.VariableDeclarationPartContext ctx) {
        if(ctx == null){
            return null;
        }
        salida += "\n" + ("  Declaracion de Variables:");
        for (MiniPascalParser.VariableDeclarationContext variableDeclarationContext : ctx.variableDeclaration()) {
            visit(variableDeclarationContext);
        }
        return null;
    }

    @Override
    public Void visitVariableDeclaration(MiniPascalParser.VariableDeclarationContext ctx) {
        if (ctx == null) {
            return null;
        }
        //salida += "\n" + ("        Grupo de Parametros:");
        if (ctx.identifierList() != null) {
            salida += "\n" + ("          Identificadores:");
            salida += ("            ");
            for (MiniPascalParser.IdentifierContext identifier : ctx.identifierList().identifier()) {
                if(identifier == ctx.identifierList().identifier().get(ctx.identifierList().identifier().size()-1)){
                    salida += (identifier.getText());
                }else {
                    salida += (identifier.getText() + ", ");
                }
            }
        } else {
            //salida += "\n" + ("          Sin identificadores");
        }
        salida += "\n";
        salida += "\n" + ("          Tipo: ");
        if (ctx.type_() != null) {
            visit(ctx.type_());
        } else {
            //salida += "\n" + ("          Sin Tipo");
        }
        return null;
    }

    @Override
    public Void visitType_(MiniPascalParser.Type_Context ctx) {
        if (ctx.getChildCount() == 1) {
            salida += "\n" + ("            "+ctx.getChild(0).getText());
        } else {
            salida += "\n" + ("        Arreglo ");
            salida += "\n" + ("          " + ctx.getChild(1).getText());
            salida += "\n" + ("          " + ctx.getChild(3).getText());
        }
        return null;
    }

    @Override
    public Void visitConstantDefinitionPart(MiniPascalParser.ConstantDefinitionPartContext ctx) {
        if(ctx == null){
            return null;
        }
        salida += "\n" + ("  Definicion de Constantes:");
        for (MiniPascalParser.ConstantDefinitionContext constantDefinitionContext : ctx.constantDefinition()) {
            visit(constantDefinitionContext);
        }
        return null;
    }

    @Override
    public Void visitConstantDefinition(MiniPascalParser.ConstantDefinitionContext ctx) {
        //salida += "\n" + ("    Definir Constante:");
        salida += "\n" + ("      Identificador:");
        salida += "\n" + ("        " + ctx.identifier().getText());
        salida += "\n" + ("      Valor:");
        salida += "\n" + ("        " + ctx.constant().getText());
        return null;
    }

    @Override
    public Void visitProcedureAndFunctionDeclarationPart(MiniPascalParser.ProcedureAndFunctionDeclarationPartContext ctx) {
        if(ctx == null){
            return null;
        }
        salida += "\n" + ("  Declaracion de Procedure y Function:");
        visit(ctx.procedureOrFunctionDeclaration());
        return null;
    }

    @Override
    public Void visitProcedureOrFunctionDeclaration(MiniPascalParser.ProcedureOrFunctionDeclarationContext ctx) {
        if(ctx == null){
            return null;
        }
        if(ctx.procedureDeclaration() != null){
            visit(ctx.procedureDeclaration());
        } else {
            visit(ctx.functionDeclaration());
        }
        return null;
    }

    @Override
    public Void visitProcedureDeclaration(MiniPascalParser.ProcedureDeclarationContext ctx) {
        salida += "\n" + ("    Declaracion de Procedure:");
        salida += "\n" + ("      Encabezado de Procedure:");
        salida += "\n" + ("      " + ctx.identifier().getText());
        if(ctx.formalParameterList() != null){
            salida += "\n" + ("      Parametros:");
            visit(ctx.formalParameterList());
        }
        return null;
    }

    @Override
    public Void visitFunctionDeclaration(MiniPascalParser.FunctionDeclarationContext ctx) {
        salida += "\n" + ("    Declaracion de Function:");
        salida += "\n" + ("      Encabezado de Function:");
        salida += "\n" + ("      " + ctx.identifier().getText());
        if(ctx.formalParameterList() != null){
            salida += "\n" + ("      Parametros:");
            visit(ctx.formalParameterList());
        }
        salida += "\n" + ("      Tipo Retorno:");
        salida += "\n" + ("      " + ctx.varType().getText());
        return null;
    }

    @Override
    public Void visitFormalParameterList(MiniPascalParser.FormalParameterListContext ctx) {
        if(ctx == null){
            //salida += "\n" + ("        Sin Parametros");
            return null;
        }
        for (MiniPascalParser.FormalParameterSectionContext formalParameterSectionContext : ctx.formalParameterSection()) {
            visit(formalParameterSectionContext);
        }
        return null;
    }

    @Override
    public Void visitFormalParameterSection(MiniPascalParser.FormalParameterSectionContext ctx) {
        if(ctx == null){
            return null;
        }
        else {
            visit(ctx.parameterGroup());
            return null;
        }
    }

    @Override
    public Void visitParameterGroup(MiniPascalParser.ParameterGroupContext ctx) {
        if (ctx == null) {
            return null;
        }
        //salida += "\n" + ("        Grupo de Parametros:");
        if (ctx.identifierList() != null) {
            salida += "\n" + ("          Identificadores:");
            salida += ("            ");
            for (MiniPascalParser.IdentifierContext identifier : ctx.identifierList().identifier()) {
                if(identifier == ctx.identifierList().identifier().get(ctx.identifierList().identifier().size()-1)){
                    salida += (identifier.getText());
                }else {
                    salida += (identifier.getText() + ", ");
                }
            }
        } else {
            //salida += "\n" + ("          Sin identificadores");
        }
        salida += "\n";
        salida += "\n" + ("          Tipo: ");
        if (ctx.varType() != null) {
            visit(ctx.varType());
        } else {
            //salida += "\n" + ("          Sin Tipo");
        }
        return null;
    }

    @Override
    public Void visitVarType(MiniPascalParser.VarTypeContext ctx) {
        if (ctx.getChildCount() == 1) {
            salida += "\n" + ("            "+ctx.getChild(0).getText());
        } else {
            salida += "\n" + ("            ARREGLO ");
            salida += "\n" + ("          " + ctx.getChild(1).getText());
            salida += "\n" + ("          " + ctx.getChild(3).getText());
        }
        return null;
    }


    @Override
    public Void visitStatements(MiniPascalParser.StatementsContext ctx) {
        if(ctx == null){
            return null;
        }
        for (MiniPascalParser.StatementContext statementContext : ctx.statement()) {
            visit(statementContext);
        }
        return null;
    }

    @Override
    public Void visitStatement(MiniPascalParser.StatementContext ctx) {
        if(ctx == null){
            return null;
        }
        visit(ctx.getChild(0));
        return null;
    }
    @Override
    public Void visitCompoundStatement(MiniPascalParser.CompoundStatementContext ctx) {
        //salida += "\n" + ("  Compound Statement:");
        visit(ctx.statements());
        return null;
    }
    @Override
    public Void visitAssignmentStatement(MiniPascalParser.AssignmentStatementContext ctx) {
        salida += "\n" + ("    Assignment Statement:");
        salida += "\n" + ("        Se asigna el valor de " + ctx.expression().getText() + " a la variable " + ctx.variable().getText());
        return null;
    }

    @Override
    public Void visitIfStatement(MiniPascalParser.IfStatementContext ctx) {
        salida += "\n" + ("    If Statement:");
        salida += "\n" + ("      Condition:");
        salida += "\n" + ("        " + ctx.expression().getText());
        salida += "\n" + ("      Then:");
        visit(ctx.statement(0));
        if(ctx.ELSE() != null){
            salida += "\n" + ("      Else:");
            visit(ctx.statement(1));
        }
        return null;
    }

    @Override
    public Void visitWhileStatement(MiniPascalParser.WhileStatementContext ctx) {
        salida += "\n" + ("    While Statement:");
        salida += "\n" + ("      Condition:");
        salida += "\n" + ("        " + ctx.expression().getText());
        salida += "\n" + ("      Do:");
        visit(ctx.statement());
        return null;
    }

    @Override
    public Void visitForStatement(MiniPascalParser.ForStatementContext ctx) {
        salida += "\n" + ("    For Statement:");
        salida += "\n" + ("      Identificador:");
        salida += "\n" + ("        " + ctx.identifier().getText());
        salida += "\n" + ("      Valor Inicial:");
        visit(ctx.forList().initialValue());
        salida += "\n" + ("      Valor Final:");
        visit(ctx.forList().finalValue());
        salida += "\n" + ("      Do:");
        visit(ctx.statement());
        return null;
    }

    @Override
    public Void visitInitialValue(MiniPascalParser.InitialValueContext ctx) {
        salida += "\n" + ("        " + ctx.getText());
        return null;
    }

    @Override
    public Void visitFinalValue(MiniPascalParser.FinalValueContext ctx) {
        salida += "\n" + ("        " + ctx.getText());
        return null;
    }

    @Override
    public Void visitWriteStatement(MiniPascalParser.WriteStatementContext ctx) {
        salida += "\n" + ("Write Statement:");
        salida += ("  Write ");
        if (ctx.write() == null) {
            salida += "\n" + ("Statement:");
        } else {
            salida += "\n" + ("Line:");
        }
        if(ctx.writeParam()!=null){
            visit(ctx.writeParam2());
            visit(ctx.writeParam());
        }
        else
            visit(ctx.writeParam2());
        return null;
    }

    @Override
    public Void visitWriteParam(MiniPascalParser.WriteParamContext ctx) {
        if (ctx.varValue() != null) {
            visit(ctx.varValue());
        } else if (ctx.identifier() != null) {
            salida += "\n" + ("Identificador Variable: " + ctx.identifier().getText());
        }
        return null;
    }

    @Override
    public Void visitVarValue(MiniPascalParser.VarValueContext ctx) {
        if (ctx.string() != null) {
            salida += "\n" + ("String Value: " + ctx.string().getText());
            visit(ctx.string());
        } else if (ctx.boolean_() != null) {
            salida += "\n" + ("Boolean Value: " + ctx.boolean_());
            visit(ctx.boolean_());
        } else if (ctx.char_() != null) {
            salida += "\n" + ("Char Value: " + ctx.char_().getText());
            visit(ctx.char_());
        } else if (ctx.integer() != null) {
            salida += "\n" + ("Integer Value: " + ctx.integer().getText());
            visit(ctx.integer());
        }
        return null;
    }

    @Override
    public Void visitString(MiniPascalParser.StringContext ctx) {
        salida += "\n" + ("        " + ctx.getText());
        return null;
    }

    @Override
    public Void visitBoolean(MiniPascalParser.BooleanContext ctx) {
        salida += "\n" + ("        " + ctx.getText());
        return null;
    }

    @Override
    public Void visitChar(MiniPascalParser.CharContext ctx) {
        salida += "\n" + ("        " + ctx.getText());
        return null;
    }

    @Override
    public Void visitInteger(MiniPascalParser.IntegerContext ctx) {
        salida += "\n" + ("        " + ctx.getText());
        return null;
    }

    @Override
    public Void visitReadStatement(MiniPascalParser.ReadStatementContext ctx) {
        salida += "\n" + ("    Read Statement:");
        visit(ctx.readParam());
        return null;
    }

    @Override
    public Void visitReadParam(MiniPascalParser.ReadParamContext ctx) {
        salida += "\n" + ("      " + ctx.identifier().getText());
        return null;
    }

    @Override
    public Void visitFunctionDesignator(MiniPascalParser.FunctionDesignatorContext ctx) {
        salida += "\n" + ("Function Designator:");
        salida += "\n" + ("Identificador de Funcion: " + ctx.identifier().getText());
        if (ctx.parameterList() != null) {
            salida += "\n" + ("Parametros:");
            visit(ctx.parameterList()); // Visitar el nodo de la lista de parámetros
        }
        return null;
    }

    @Override
    public Void visitParameterList(MiniPascalParser.ParameterListContext ctx) {
        if(ctx == null){
            //salida += "\n" + ("        Sin Parametros");
            return null;
        }
        for (MiniPascalParser.ActualParameterContext actualParameterContext : ctx.actualParameter()) {
            visit(actualParameterContext);
        }
        return null;
    }

    @Override
    public Void visitActualParameter(MiniPascalParser.ActualParameterContext ctx) {
        salida += "\n" + ("        " + ctx.getText());
        return null;
    }

    @Override
    public Void visitExpression(MiniPascalParser.ExpressionContext ctx) {
        salida += "\n" + ("    Expresion:");
        visit(ctx.simpleExpression());
        if(ctx.relationaloperator() != null){
            salida += "\n" + ("      " + ctx.relationaloperator().getText());
            visit(ctx.simpleExpression());
        }
        return null;
    }

    @Override
    public Void visitSimpleExpression(MiniPascalParser.SimpleExpressionContext ctx) {
        visit(ctx.term()); // Visitar el nodo del término
        if (ctx.additiveoperator() != null) {
            salida += "\n" + ("Operador Aditivo: " + ctx.additiveoperator().getText());
            visit(ctx.simpleExpression()); // Visitar el nodo de la expresión simple
        }
        return null;
    }

    @Override
    public Void visitTerm(MiniPascalParser.TermContext ctx) {
        //salida += "\n" + ("Term:");
        visit(ctx.signedFactor());
        if (ctx.multiplicativeoperator() != null) {
            salida += "\n" + ("Operador Multiplicativo: " + ctx.multiplicativeoperator().getText());
            visit(ctx.term()); // Visitar el nodo del término
        }
        return null;
    }
    public Void visitFactor(MiniPascalParser.FactorContext ctx) {
        //salida += "\n" + ("Factor:");
        if (ctx.variable() != null) {
            visit(ctx.variable()); // Visitar el nodo de la variable
        } else if (ctx.expression() != null) {
            visit(ctx.expression()); // Visitar el nodo de la expresión
        } else if (ctx.functionDesignator() != null) {
            visit(ctx.functionDesignator()); // Visitar el nodo del designador de función
        } else if (ctx.unsignedConstant() != null) {
            visit(ctx.unsignedConstant()); // Visitar el nodo de la constante no firmada
        }else if (ctx.NOT() != null) {
            salida += "\n" + ("NOT");
            visit(ctx.factor()); // Visitar el nodo del factor
        } else if (ctx.bool_() != null) {
            salida += "\n" + ("Valor booleano: " + ctx.bool_().getText());
        }
        return null;
    }

    @Override
    public Void visitUnsignedConstant(MiniPascalParser.UnsignedConstantContext ctx) {
        if (ctx.unsignedNumber() != null) {
            salida += "\n" + ("Numero Unsigned: " + ctx.unsignedNumber().getText());
        } else if (ctx.constantChr() != null) {
            salida += "\n" + ("Char constante: " + ctx.constantChr().getText());
        } else if (ctx.string() != null) {
            salida += "\n" + ("Valor String: " + ctx.string().getText());
        } else if (ctx.NIL() != null) {
            salida += "\n" + ("NIL");
        }
        return null;
    }

    @Override
    public Void visitIdentifierList(MiniPascalParser.IdentifierListContext ctx) {
        for (MiniPascalParser.IdentifierContext ctx2: ctx.identifier()) {
            salida += "\n" + ("    Identifier: " + ctx2.getText());
        }
        return null;
    }


}