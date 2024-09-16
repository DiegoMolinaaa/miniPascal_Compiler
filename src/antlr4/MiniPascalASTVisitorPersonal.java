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







}