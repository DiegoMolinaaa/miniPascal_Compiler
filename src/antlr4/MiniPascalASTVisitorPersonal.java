package antlr4;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

public class MiniPascalASTVisitorPersonal extends MiniPascalBaseVisitor<Object> {

    @Override
    public Void visitProgram(MiniPascalParser.ProgramContext ctx) {
        System.out.println("Program:");
        System.out.println("  Program Heading:");
        visit(ctx.programHeading());
        System.out.println("  Block:");
        visit(ctx.block());
        System.out.println("End of Program");
        return null;
    }

    @Override
    public Void visitProgramHeading(MiniPascalParser.ProgramHeadingContext ctx) {
        System.out.println("      PROGRAM " + ctx.identifier().getText());
        return null;
    }


    @Override
    public Void visitVariableDeclarationPart(MiniPascalParser.VariableDeclarationPartContext ctx) {
        if(ctx == null){
            return null;
        }
        System.out.println("  Variable Declaration Part:");
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
        System.out.println("        Parameter Group:");
        if (ctx.identifierList() != null) {
            System.out.println("          Identifiers:");
            for (MiniPascalParser.IdentifierContext identifier : ctx.identifierList().identifier()) {
                System.out.println("            " + identifier.getText());
            }
        } else {
            System.out.println("          No identifiers");
        }
        System.out.println("          Type:");
        if (ctx.type_() != null) {
            visit(ctx.type_());
        } else {
            System.out.println("          No type");
        }
        return null;
    }

    @Override
    public Void visitType_(MiniPascalParser.Type_Context ctx) {
        if (ctx.getChildCount() == 1) {
            System.out.println("        " + ctx.getChild(0).getText());
        } else {
            System.out.println("        ARRAY");
            System.out.println("          " + ctx.getChild(1).getText());
            System.out.println("          " + ctx.getChild(3).getText());
        }
        return null;
    }

    @Override
    public Void visitConstantDefinitionPart(MiniPascalParser.ConstantDefinitionPartContext ctx) {
        if(ctx == null){
            return null;
        }
        System.out.println("  Constant Definition Part:");
        for (MiniPascalParser.ConstantDefinitionContext constantDefinitionContext : ctx.constantDefinition()) {
            visit(constantDefinitionContext);
        }
        return null;
    }

    @Override
    public Void visitConstantDefinition(MiniPascalParser.ConstantDefinitionContext ctx) {
        System.out.println("    Constant Definition:");
        System.out.println("      Identifier:");
        System.out.println("        " + ctx.identifier().getText());
        System.out.println("      Value:");
        System.out.println("        " + ctx.constant().getText());
        return null;
    }

    @Override
    public Void visitProcedureAndFunctionDeclarationPart(MiniPascalParser.ProcedureAndFunctionDeclarationPartContext ctx) {
        if(ctx == null){
            return null;
        }
        System.out.println("  Procedure and Function Declaration Part:");
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
        System.out.println("    Procedure Declaration:");
        System.out.println("      Procedure Heading:");
        System.out.println("      " + ctx.identifier().getText());
        System.out.println("      Parameters:");
        visit(ctx.formalParameterList());
        return null;
    }

    @Override
    public Void visitFunctionDeclaration(MiniPascalParser.FunctionDeclarationContext ctx) {
        System.out.println("    Function Declaration:");
        System.out.println("      Function Heading:");
        System.out.println("      " + ctx.identifier().getText());
        System.out.println("      Parameters:");
        visit(ctx.formalParameterList());
        System.out.println("      Return Type:");
        System.out.println("      " + ctx.varType().getText());
        return null;
    }

    @Override
    public Void visitFormalParameterList(MiniPascalParser.FormalParameterListContext ctx) {
        if(ctx == null){
            System.out.println("        No parameters");
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
        System.out.println("        Parameter Group:");
        if (ctx.identifierList() != null) {
            System.out.println("          Identifiers:");
            for (MiniPascalParser.IdentifierContext identifier : ctx.identifierList().identifier()) {
                System.out.println("            " + identifier.getText());
            }
        } else {
            System.out.println("          No identifiers");
        }
        System.out.println("          Type:");
        if (ctx.varType() != null) {
            visit(ctx.varType());
        } else {
            System.out.println("          No type");
        }
        return null;
    }

    @Override
    public Void visitVarType(MiniPascalParser.VarTypeContext ctx) {
        if (ctx.getChildCount() == 1) {
            System.out.println("        " + ctx.getChild(0).getText());
        } else {
            System.out.println("        ARRAY");
            System.out.println("          " + ctx.getChild(1).getText());
            System.out.println("          " + ctx.getChild(3).getText());
        }
        return null;
    }

    @Override
    public Void visitCompoundStatement(MiniPascalParser.CompoundStatementContext ctx) {
        System.out.println("  Compound Statement:");
        visit(ctx.statements());
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
    public Void visitAssignmentStatement(MiniPascalParser.AssignmentStatementContext ctx) {
        System.out.println("    Assignment Statement:");
        System.out.println("        Assigning value of " + ctx.expression().getText() + " to variable " + ctx.variable().getText());
        return null;
    }

    @Override
    public Void visitIfStatement(MiniPascalParser.IfStatementContext ctx) {
        System.out.println("    If Statement:");
        System.out.println("      Condition:");
        System.out.println("        " + ctx.expression().getText());
        System.out.println("      Then:");
        visit(ctx.statement(0));
        if(ctx.ELSE() != null){
            System.out.println("      Else:");
            visit(ctx.statement(1));
        }
        return null;
    }

    @Override
    public Void visitWhileStatement(MiniPascalParser.WhileStatementContext ctx) {
        System.out.println("    While Statement:");
        System.out.println("      Condition:");
        System.out.println("        " + ctx.expression().getText());
        System.out.println("      Do:");
        visit(ctx.statement());
        return null;
    }

    @Override
    public Void visitForStatement(MiniPascalParser.ForStatementContext ctx) {
        System.out.println("    For Statement:");
        System.out.println("      Identifier:");
        System.out.println("        " + ctx.identifier().getText());
        System.out.println("      Initial Value:");
        visit(ctx.forList().initialValue());
        System.out.println("      Final Value:");
        visit(ctx.forList().finalValue());
        System.out.println("      Do:");
        visit(ctx.statement());
        return null;
    }

    @Override
    public Void visitInitialValue(MiniPascalParser.InitialValueContext ctx) {
        System.out.println("        " + ctx.getText());
        return null;
    }

    @Override
    public Void visitFinalValue(MiniPascalParser.FinalValueContext ctx) {
        System.out.println("        " + ctx.getText());
        return null;
    }

    @Override
    public Void visitWriteStatement(MiniPascalParser.WriteStatementContext ctx) {
        System.out.println("    Write Statement:");
        System.out.print("      " + ctx.writeParam2().getText());
        if(ctx.writeParam() != null){
            visit(ctx.writeParam());
        }
        return null;
    }

    @Override
    public Void visitWriteParam(MiniPascalParser.WriteParamContext ctx) {
        if (ctx.varValue() != null) {
            visit(ctx.varValue());
        } else if (ctx.identifier() != null) {
            System.out.println("Variable Identifier: " + ctx.identifier().getText());
        }
        return null;
    }

    @Override
    public Void visitVarValue(MiniPascalParser.VarValueContext ctx) {
        if (ctx.string() != null) {
            System.out.println("String Value: " + ctx.string().getText());
            visit(ctx.string());
        } else if (ctx.boolean_() != null) {
            System.out.println("Boolean Value: " + ctx.boolean_());
            visit(ctx.boolean_());
        } else if (ctx.char_() != null) {
            System.out.println("Char Value: " + ctx.char_().getText());
            visit(ctx.char_());
        } else if (ctx.integer() != null) {
            System.out.println("Integer Value: " + ctx.integer().getText());
            visit(ctx.integer());
        }
        return null;
    }

    @Override
    public Void visitString(MiniPascalParser.StringContext ctx) {
        System.out.println("        " + ctx.getText());
        return null;
    }

    @Override
    public Void visitBoolean(MiniPascalParser.BooleanContext ctx) {
        System.out.println("        " + ctx.getText());
        return null;
    }

    @Override
    public Void visitChar(MiniPascalParser.CharContext ctx) {
        System.out.println("        " + ctx.getText());
        return null;
    }

    @Override
    public Void visitInteger(MiniPascalParser.IntegerContext ctx) {
        System.out.println("        " + ctx.getText());
        return null;
    }

    @Override
    public Void visitReadStatement(MiniPascalParser.ReadStatementContext ctx) {
        System.out.println("    Read Statement:");
        visit(ctx.readParam());
        return null;
    }

    @Override
    public Void visitReadParam(MiniPascalParser.ReadParamContext ctx) {
        System.out.println("      " + ctx.identifier().getText());
        return null;
    }

    @Override
    public Void visitFunctionDesignator(MiniPascalParser.FunctionDesignatorContext ctx) {
        System.out.println("    Function Designator:");
        System.out.println("      " + ctx.identifier().getText());
        System.out.println("      Parameters:");
        visit(ctx.parameterList());
        return null;
    }

    @Override
    public Void visitParameterList(MiniPascalParser.ParameterListContext ctx) {
        if(ctx == null){
            System.out.println("        No parameters");
            return null;
        }
        for (MiniPascalParser.ActualParameterContext actualParameterContext : ctx.actualParameter()) {
            visit(actualParameterContext);
        }
        return null;
    }

    @Override
    public Void visitActualParameter(MiniPascalParser.ActualParameterContext ctx) {
        System.out.println("        " + ctx.getText());
        return null;
    }

    @Override
    public Void visitExpression(MiniPascalParser.ExpressionContext ctx) {
        System.out.println("    Expression:");
        visit(ctx.simpleExpression());
        if(ctx.relationaloperator() != null){
            System.out.println("      " + ctx.relationaloperator().getText());
            visit(ctx.simpleExpression());
        }
        return null;
    }
}