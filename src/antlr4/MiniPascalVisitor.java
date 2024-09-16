// Generated from C:/Users/diego/OneDrive - Universidad Tecnologica Centroamericana/Trabajos Diego Unitec/CLASES Q3 2024/COMPILADORES 1/MiniPascal_Compiler/src/antlr4/MiniPascal.g4 by ANTLR 4.13.1
package antlr4;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MiniPascalParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MiniPascalVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(MiniPascalParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#programHeading}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgramHeading(MiniPascalParser.ProgramHeadingContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#identifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifier(MiniPascalParser.IdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(MiniPascalParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#constantDefinitionPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantDefinitionPart(MiniPascalParser.ConstantDefinitionPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#constantDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantDefinition(MiniPascalParser.ConstantDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#constantChr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstantChr(MiniPascalParser.ConstantChrContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(MiniPascalParser.ConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#varType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarType(MiniPascalParser.VarTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#arrayType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayType(MiniPascalParser.ArrayTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#arrayValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayValue(MiniPascalParser.ArrayValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#indexRanges}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexRanges(MiniPascalParser.IndexRangesContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#indexRange}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexRange(MiniPascalParser.IndexRangeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#stringR_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringR_(MiniPascalParser.StringR_Context ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#charR_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCharR_(MiniPascalParser.CharR_Context ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#integerR_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerR_(MiniPascalParser.IntegerR_Context ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#unsignedNumber}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnsignedNumber(MiniPascalParser.UnsignedNumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#unsignedInteger}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnsignedInteger(MiniPascalParser.UnsignedIntegerContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#sign}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSign(MiniPascalParser.SignContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#bool_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBool_(MiniPascalParser.Bool_Context ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(MiniPascalParser.StringContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#char}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChar(MiniPascalParser.CharContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#integer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInteger(MiniPascalParser.IntegerContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#type_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_(MiniPascalParser.Type_Context ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#simpleType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleType(MiniPascalParser.SimpleTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#scalarType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScalarType(MiniPascalParser.ScalarTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#subrangeType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSubrangeType(MiniPascalParser.SubrangeTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#typeIdentifier}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeIdentifier(MiniPascalParser.TypeIdentifierContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#variableDeclarationPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclarationPart(MiniPascalParser.VariableDeclarationPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#variableDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclaration(MiniPascalParser.VariableDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#procedureAndFunctionDeclarationPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedureAndFunctionDeclarationPart(MiniPascalParser.ProcedureAndFunctionDeclarationPartContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#procedureOrFunctionDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedureOrFunctionDeclaration(MiniPascalParser.ProcedureOrFunctionDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#formalParameterList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormalParameterList(MiniPascalParser.FormalParameterListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#formalParameterSection}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormalParameterSection(MiniPascalParser.FormalParameterSectionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#parameterGroup}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterGroup(MiniPascalParser.ParameterGroupContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#identifierList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierList(MiniPascalParser.IdentifierListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#functionDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDeclaration(MiniPascalParser.FunctionDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#procedureDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProcedureDeclaration(MiniPascalParser.ProcedureDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(MiniPascalParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#writeStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWriteStatement(MiniPascalParser.WriteStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#write}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWrite(MiniPascalParser.WriteContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#writeParam2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWriteParam2(MiniPascalParser.WriteParam2Context ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#writeParam}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWriteParam(MiniPascalParser.WriteParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#varValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarValue(MiniPascalParser.VarValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#readStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReadStatement(MiniPascalParser.ReadStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#read}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRead(MiniPascalParser.ReadContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#readParam}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReadParam(MiniPascalParser.ReadParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#unlabelledStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnlabelledStatement(MiniPascalParser.UnlabelledStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#simpleStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleStatement(MiniPascalParser.SimpleStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#assignmentStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignmentStatement(MiniPascalParser.AssignmentStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(MiniPascalParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#expressionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionList(MiniPascalParser.ExpressionListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(MiniPascalParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#relationaloperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationaloperator(MiniPascalParser.RelationaloperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#simpleExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSimpleExpression(MiniPascalParser.SimpleExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#additiveoperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveoperator(MiniPascalParser.AdditiveoperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#term}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTerm(MiniPascalParser.TermContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#multiplicativeoperator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicativeoperator(MiniPascalParser.MultiplicativeoperatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#signedFactor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSignedFactor(MiniPascalParser.SignedFactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#factor}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFactor(MiniPascalParser.FactorContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#unsignedConstant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnsignedConstant(MiniPascalParser.UnsignedConstantContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#functionDesignator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDesignator(MiniPascalParser.FunctionDesignatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#parameterList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterList(MiniPascalParser.ParameterListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#actualParameter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActualParameter(MiniPascalParser.ActualParameterContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#emptyStatement_}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEmptyStatement_(MiniPascalParser.EmptyStatement_Context ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#structuredStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStructuredStatement(MiniPascalParser.StructuredStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#compoundStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompoundStatement(MiniPascalParser.CompoundStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#statements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatements(MiniPascalParser.StatementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#conditionalStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionalStatement(MiniPascalParser.ConditionalStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(MiniPascalParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#repetitiveStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepetitiveStatement(MiniPascalParser.RepetitiveStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#whileStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhileStatement(MiniPascalParser.WhileStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#repeatStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRepeatStatement(MiniPascalParser.RepeatStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#forStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForStatement(MiniPascalParser.ForStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#forList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForList(MiniPascalParser.ForListContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#initialValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInitialValue(MiniPascalParser.InitialValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link MiniPascalParser#finalValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFinalValue(MiniPascalParser.FinalValueContext ctx);
}