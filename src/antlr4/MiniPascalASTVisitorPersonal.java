package antlr4;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

public class MiniPascalASTVisitorPersonal extends MiniPascalBaseVisitor<ASTNode> {

    @Override
    public ASTNode visitProgram(MiniPascalParser.ProgramContext ctx) {
        ASTNode node = new ASTNode("Program");
        node.addChild(visit(ctx.block()));
        return node;
    }

    @Override
    public ASTNode visitBlock(MiniPascalParser.BlockContext ctx) {
        ASTNode node = new ASTNode("Block");
        for (MiniPascalParser.DeclarationContext decl : ctx.declaration()) {
            node.addChild(visit(decl));
        }
        node.addChild(visit(ctx.compoundStatement()));
        return node;
    }

    @Override
    public ASTNode visitCompoundStatement(MiniPascalParser.CompoundStatementContext ctx) {
        ASTNode node = new ASTNode("CompoundStatement");
        for (MiniPascalParser.StatementContext stmt : ctx.statement()) {
            node.addChild(visit(stmt));
        }
        return node;
    }

    @Override
    public ASTNode visitAssignmentStatement(MiniPascalParser.AssignmentStatementContext ctx) {
        ASTNode node = new ASTNode("AssignmentStatement");
        node.addChild(visit(ctx.variable()));
        node.addChild(visit(ctx.expression()));
        return node;
    }

    @Override
    public ASTNode visitVariable(MiniPascalParser.VariableContext ctx) {
        ASTNode node = new ASTNode("Variable");
        node.addChild(new ASTNode(ctx.IDENT().getText()));
        return node;
    }

    @Override
    public ASTNode visitExpression(MiniPascalParser.ExpressionContext ctx) {
        ASTNode node = new ASTNode("Expression");
        node.addChild(visit(ctx.simpleExpression(0)));
        if (ctx.relationaloperator() != null) {
            node.addChild(new ASTNode(ctx.relationaloperator().getText()));
            node.addChild(visit(ctx.simpleExpression(1)));
        }
        return node;
    }

    @Override
    public ASTNode visitSimpleExpression(MiniPascalParser.SimpleExpressionContext ctx) {
        ASTNode node = new ASTNode("SimpleExpression");
        node.addChild(visit(ctx.term(0)));
        if (ctx.additiveoperator() != null) {
            node.addChild(new ASTNode(ctx.additiveoperator().getText()));
            node.addChild(visit(ctx.term(1)));
        }
        return node;
    }

    @Override
    public ASTNode visitTerm(MiniPascalParser.TermContext ctx) {
        ASTNode node = new ASTNode("Term");
        node.addChild(visit(ctx.signedFactor()));
        if (ctx.multiplicativeoperator() != null) {
            node.addChild(new ASTNode(ctx.multiplicativeoperator().getText()));
            node.addChild(visit(ctx.signedFactor()));
        }
        return node;
    }

    @Override
    public ASTNode visitSignedFactor(MiniPascalParser.SignedFactorContext ctx) {
        ASTNode node = new ASTNode("SignedFactor");
        if (ctx.PLUS() != null) {
            node.addChild(new ASTNode(ctx.PLUS().getText()));
        } else if (ctx.MINUS() != null) {
            node.addChild(new ASTNode(ctx.MINUS().getText()));
        }
        node.addChild(visit(ctx.factor()));
        return node;
    }

    @Override
    public ASTNode visitFactor(MiniPascalParser.FactorContext ctx) {
        ASTNode node = new ASTNode("Factor");
        if (ctx.variable() != null) {
            node.addChild(visit(ctx.variable()));
        } else if (ctx.expression() != null) {
            node.addChild(visit(ctx.expression()));
        } else if (ctx.unsignedConstant() != null) {
            node.addChild(visit(ctx.unsignedConstant()));
        } else if (ctx.NOT() != null) {
            node.addChild(new ASTNode(ctx.NOT().getText()));
            node.addChild(visit(ctx.factor()));
        } else if (ctx.bool_() != null) {
            node.addChild(new ASTNode(ctx.bool_().getText()));
        }
        return node;
    }

    @Override
    public ASTNode visitUnsignedConstant(MiniPascalParser.UnsignedConstantContext ctx) {
        ASTNode node = new ASTNode("UnsignedConstant");
        if (ctx.NUM_INT() != null) {
            node.addChild(new ASTNode(ctx.NUM_INT().getText()));
        } else if (ctx.NUM_REAL() != null) {
            node.addChild(new ASTNode(ctx.NUM_REAL().getText()));
        } else if (ctx.constantChr() != null) {
            node.addChild(new ASTNode(ctx.constantChr().getText()));
        } else if (ctx.string() != null) {
            node.addChild(new ASTNode(ctx.string().getText()));
        } else if (ctx.NIL() != null) {
            node.addChild(new ASTNode(ctx.NIL().getText()));
        }
        return node;
    }

    @Override
    public ASTNode visitWriteStatement(MiniPascalParser.WriteStatementContext ctx) {
        ASTNode node = new ASTNode("WriteStatement");
        for (ParseTree child : ctx.children) {
            if (child instanceof TerminalNode) {
                node.addChild(new ASTNode(child.getText()));
            } else {
                node.addChild(visit(child));
            }
        }
        return node;
    }

    @Override
    public ASTNode visitReadStatement(MiniPascalParser.ReadStatementContext ctx) {
        ASTNode node = new ASTNode("ReadStatement");
        node.addChild(visit(ctx.variable()));
        return node;
    }
}