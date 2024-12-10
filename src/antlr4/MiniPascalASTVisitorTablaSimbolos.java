package antlr4;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.ArrayList;

public class MiniPascalASTVisitorTablaSimbolos extends MiniPascalBaseVisitor<Object> {
    private final TablaSimbolos tablaSimbolos;

    private String currentScope = "global"; // Manejo del alcance
    private String previousScope = "";

    public MiniPascalASTVisitorTablaSimbolos(TablaSimbolos tablaSimbolos) {
        this.tablaSimbolos = tablaSimbolos;
    }

    @Override
    public Void visitProgram(MiniPascalParser.ProgramContext ctx) {
        tablaSimbolos.enterScope(currentScope);
        System.out.println("Program:");
        System.out.println("  Encabezado Programa:");
        visit(ctx.programHeading());
        System.out.println("  Block:");
        visit(ctx.block());
        System.out.println("\nFin de Programa");
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
        System.out.println("  Declaracion de Variables:");
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
        //System.out.println("        Grupo de Parametros:");
        String tipo = ctx.type_().getText();
        if (ctx.identifierList() != null) {
            System.out.println("          Identificadores:");
            System.out.print("            ");
            for (MiniPascalParser.IdentifierContext identifier : ctx.identifierList().identifier()) {
                String nombre = identifier.getText();
                if(tipo.contains("Array")){
                    String tipoArreglo;
                    int dimension = 0;
                    int[] tamanos;
                    String dimensionesString = tipo.substring(tipo.indexOf("["), tipo.indexOf("]")+1);
                    if(dimensionesString.contains(",")){
                        dimension = 2;
                        tamanos = new int[dimension];
                        for (int i = 0; i < dimensionesString.length(); i++) {
                            if(dimensionesString.charAt(i) == '.'){
                                if(dimensionesString.charAt(i+1) == '.'){
                                    tamanos[0] = Integer.parseInt(dimensionesString.substring(i+2, dimensionesString.indexOf(',')));
                                    tamanos[1] = Integer.parseInt(dimensionesString.substring(dimensionesString.indexOf(',')+4, dimensionesString.indexOf(']')));

                                    break;
                                }
                            }
                        }
                    }else{
                        dimension = 1;
                        tamanos = new int[dimension];
                        for(int i = 0; i < dimensionesString.length(); i++){
                            if(dimensionesString.charAt(i) == '.'){
                                tamanos[0] = Integer.parseInt(dimensionesString.substring(i+2, dimensionesString.indexOf(']')));
                                break;
                            }
                        }
                    }
                    tipoArreglo = tipo.substring(tipo.indexOf(']')+3);
                    if (!tablaSimbolos.addSimbolo(new SimboloArreglo(nombre, tipoArreglo, currentScope, dimension, tamanos))){ // Añadir a la tabla
                        System.err.println("Error: Arreglo '" + nombre + "' ya declarado en el ámbito '" + currentScope + "'.");
                    } else {
                        System.out.println("Arreglo '" + nombre + "' de tipo '" + tipoArreglo + "' añadido.");
                    }
                }else {
                    if (!tablaSimbolos.addSimbolo(new Simbolo(nombre, tipo, currentScope, null, "variable"))) { // Añadir a la tabla
                        System.err.println("Error: Variable '" + nombre + "' ya declarada en el ámbito '" + currentScope + "'.");
                    } else {
                        System.out.println("Variable '" + nombre + "' de tipo '" + tipo + "' añadida.");
                    }
                }
            }
        } else {
            //System.out.println("          Sin identificadores");
        }
        //System.out.println();
        //System.out.println("          Tipo: ");
        if (ctx.type_() != null) {
            visit(ctx.type_());
        } else {
            //System.out.println("          Sin Tipo");
        }
        return null;
    }

    @Override
    public Void visitType_(MiniPascalParser.Type_Context ctx) {
        if (ctx.getChildCount() == 1) {
        } else {
        }
        return null;
    }

    @Override
    public Void visitConstantDefinitionPart(MiniPascalParser.ConstantDefinitionPartContext ctx) {
        if(ctx == null){
            return null;
        }
        System.out.println("  Definicion de Constantes:");
        for (MiniPascalParser.ConstantDefinitionContext constantDefinitionContext : ctx.constantDefinition()) {
            visit(constantDefinitionContext);
        }
        return null;
    }

    @Override
    public Void visitConstantDefinition(MiniPascalParser.ConstantDefinitionContext ctx) {
        String name = ctx.identifier().getText();
        String value = ctx.constant().getText();

        if (!tablaSimbolos.addSimbolo(new Simbolo(name, "const", currentScope, null, "constant"))) {
            System.out.println("Error: Constante '" + name + "' ya declarada.");
        } else {
            System.out.println("Constante '" + name + "' con valor '" + value + "' añadida.");
        }
        return null;
    }

    @Override
    public Void visitProcedureAndFunctionDeclarationPart(MiniPascalParser.ProcedureAndFunctionDeclarationPartContext ctx) {
        if(ctx == null){
            return null;
        }
        System.out.println("  Declaracion de Procedure y Function:");
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
    public Void visitFunctionDeclaration(MiniPascalParser.FunctionDeclarationContext ctx) {

        ArrayList<Simbolo> parametros = new ArrayList();
        String procedureName = ctx.identifier().getText();
        String previousScope = currentScope;
        currentScope = procedureName;
        tablaSimbolos.enterScope(currentScope);
        System.out.println("    Declaracion de Function:");
        System.out.println("      Encabezado de Function:");
        System.out.println("      " + ctx.identifier().getText());
        String functionName = ctx.identifier().getText();
        if (ctx.formalParameterList() != null) {
            System.out.println("  Parámetros:");
            for (MiniPascalParser.FormalParameterSectionContext paramCtx : ctx.formalParameterList().formalParameterSection()) {
                if(paramCtx.parameterGroup().identifierList() == null){
                    System.out.println("Sin Parametros");
                }
                else{
                    System.out.println("Encuentra Parametros");
                    // Extraemos la lista de identificadores (pueden ser múltiples) y su tipo
                    String tipo = paramCtx.parameterGroup().varType().getText();
                    for (MiniPascalParser.IdentifierContext idCtx : paramCtx.parameterGroup().identifierList().identifier()) {
                        String nombreParametro = idCtx.getText();

                        // Agregamos cada parámetro como un símbolo
                        Simbolo parametro = new Simbolo(nombreParametro, tipo, currentScope, null, "parametro");
                        parametros.add(parametro);


                    }
                }
            }
        }
        //System.out.println("      Tipo Retorno:");
        //System.out.println("      " + ctx.varType().getText());
        String returnType = ctx.varType().getText();
        if(!tablaSimbolos.addSimbolo(new SimboloFuncion(functionName, returnType, currentScope, parametros))){
            System.out.println("Error: Funcion '" + functionName + "' ya declarado en el ámbito '" + previousScope + "'.");

        }
        currentScope = previousScope;
        tablaSimbolos.exitScope();
        return null;
    }

    @Override


    public Void visitProcedureDeclaration(MiniPascalParser.ProcedureDeclarationContext ctx) {
        ArrayList<Simbolo> parametrosProcedimiento = new ArrayList();
        String procedureName = ctx.identifier().getText();
        System.out.println("Entrando en el procedimiento: " + procedureName);

        // Cambiar de ámbito
        String previousScope = currentScope;
        currentScope = procedureName;
        tablaSimbolos.enterScope(currentScope);
        System.out.println("    Declaracion de Procedure:");
        System.out.println("      Encabezado de Procedure:");
        // Añadir el procedimiento como símbolo


        if (ctx.formalParameterList().formalParameterSection() != null) {
            System.out.println("  Parámetros:");
            for (MiniPascalParser.FormalParameterSectionContext paramCtx : ctx.formalParameterList().formalParameterSection()) {
                // Extraemos la lista de identificadores (pueden ser múltiples) y su tipo
                if(paramCtx.parameterGroup().identifierList() == null){
                    //System.out.println("Sin Parametros");
                }
                else {
                    String tipo = paramCtx.parameterGroup().varType().getText();
                    for (MiniPascalParser.IdentifierContext idCtx : paramCtx.parameterGroup().identifierList().identifier()) {

                        String nombreParametro = idCtx.getText();

                        // Agregamos cada parámetro como un símbolo
                        Simbolo parametro = new Simbolo(nombreParametro, tipo, currentScope, null, "parametro");
                        parametrosProcedimiento.add(parametro);

                    }
                }
            }
        }
        if (!tablaSimbolos.addSimbolo(new SimboloFuncion( procedureName, "procedure", previousScope, parametrosProcedimiento))) {
            System.out.println("Error: Procedimiento '" + procedureName + "' ya declarado en el ámbito '" + previousScope + "'.");
        }
        // Volver al ámbito anterior
        currentScope = previousScope;
        tablaSimbolos.exitScope();
        return null;
    }

    @Override
    public Void visitFormalParameterList(MiniPascalParser.FormalParameterListContext ctx) {
        if(ctx == null){
            //System.out.println("        Sin Parametros");
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
        /*//System.out.println("        Grupo de Parametros:");
        if (ctx.identifierList() != null) {
            System.out.println("          Identificadores:");
            System.out.print("            ");
            for (MiniPascalParser.IdentifierContext identifier : ctx.identifierList().identifier()) {
                if(identifier == ctx.identifierList().identifier().get(ctx.identifierList().identifier().size()-1)){
                    System.out.print(identifier.getText());
                }else {
                    System.out.print(identifier.getText() + ", ");
                }
            }
        } else {
            //System.out.println("          Sin identificadores");
        }
        System.out.println();
        System.out.println("          Tipo: ");
        if (ctx.varType() != null) {
            visit(ctx.varType());
        } else {
            //System.out.println("          Sin Tipo");
        }*/
        return null;
    }

    @Override
    public Void visitVarType(MiniPascalParser.VarTypeContext ctx) {
        if (ctx.getChildCount() == 1) {
            //System.out.println("            "+ctx.getChild(0).getText());
        } else {
            /*System.out.println("            ARREGLO ");
            System.out.println("          " + ctx.getChild(1).getText());
            System.out.println("          " + ctx.getChild(3).getText());*/
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
        //System.out.println("  Compound Statement:");
        visit(ctx.statements());
        return null;
    }
    @Override
    public Void visitAssignmentStatement(MiniPascalParser.AssignmentStatementContext ctx) {
        String variable = ctx.variable().getText();
        String expression = ctx.expression().getText();

        if (!tablaSimbolos.findSimbolo(variable)) {
            System.out.println("Error: La variable '" + variable + "' no está declarada en el ámbito '" + currentScope + "'.");
        } else {
            System.out.println("Asignación: '" + variable + "' = " + expression);
        }
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
        System.out.println("      Identificador:");
        System.out.println("        " + ctx.identifier().getText());
        System.out.println("      Valor Inicial:");
        visit(ctx.forList().initialValue());
        System.out.println("      Valor Final:");
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
        System.out.println("Write Statement:");
        System.out.print("  Write ");
        if (ctx.write() == null) {
            System.out.println("Statement:");
        } else {
            System.out.println("Line:");
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
            System.out.println("Identificador Variable: " + ctx.identifier().getText());
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
        System.out.println("Function Designator:");
        System.out.println("Identificador de Funcion: " + ctx.identifier().getText());
        if (ctx.parameterList() != null) {
            System.out.println("Parametros:");
            visit(ctx.parameterList()); // Visitar el nodo de la lista de parámetros
        }
        return null;
    }

    @Override
    public Void visitParameterList(MiniPascalParser.ParameterListContext ctx) {
        if(ctx == null){
            //System.out.println("        Sin Parametros");
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
        System.out.println("    Expresion:");
        visit(ctx.simpleExpression());
        if(ctx.relationaloperator() != null){
            System.out.println("      " + ctx.relationaloperator().getText());
            visit(ctx.simpleExpression());
        }
        return null;
    }

    @Override
    public Void visitSimpleExpression(MiniPascalParser.SimpleExpressionContext ctx) {
        visit(ctx.term()); // Visitar el nodo del término
        if (ctx.additiveoperator() != null) {
            System.out.println("Operador Aditivo: " + ctx.additiveoperator().getText());
            visit(ctx.simpleExpression()); // Visitar el nodo de la expresión simple
        }
        return null;
    }

    @Override
    public Void visitTerm(MiniPascalParser.TermContext ctx) {
        //System.out.println("Term:");
        visit(ctx.signedFactor());
        if (ctx.multiplicativeoperator() != null) {
            System.out.println("Operador Multiplicativo: " + ctx.multiplicativeoperator().getText());
            visit(ctx.term()); // Visitar el nodo del término
        }
        return null;
    }
    public Void visitFactor(MiniPascalParser.FactorContext ctx) {
        //System.out.println("Factor:");
        if (ctx.variable() != null) {
            visit(ctx.variable()); // Visitar el nodo de la variable
        } else if (ctx.expression() != null) {
            visit(ctx.expression()); // Visitar el nodo de la expresión
        } else if (ctx.functionDesignator() != null) {
            visit(ctx.functionDesignator()); // Visitar el nodo del designador de función
        } else if (ctx.unsignedConstant() != null) {
            visit(ctx.unsignedConstant()); // Visitar el nodo de la constante no firmada
        }else if (ctx.NOT() != null) {
            System.out.println("NOT");
            visit(ctx.factor()); // Visitar el nodo del factor
        } else if (ctx.bool_() != null) {
            System.out.println("Valor booleano: " + ctx.bool_().getText());
        }
        return null;
    }

    @Override
    public Void visitUnsignedConstant(MiniPascalParser.UnsignedConstantContext ctx) {
        if (ctx.unsignedNumber() != null) {
            System.out.println("Numero Unsigned: " + ctx.unsignedNumber().getText());
        } else if (ctx.constantChr() != null) {
            System.out.println("Char constante: " + ctx.constantChr().getText());
        } else if (ctx.string() != null) {
            System.out.println("Valor String: " + ctx.string().getText());
        } else if (ctx.NIL() != null) {
            System.out.println("NIL");
        }
        return null;
    }

    @Override
    public Void visitIdentifierList(MiniPascalParser.IdentifierListContext ctx) {
        for (MiniPascalParser.IdentifierContext ctx2: ctx.identifier()) {
            System.out.println("    Identifier: " + ctx2.getText());
        }
        return null;
    }


}
