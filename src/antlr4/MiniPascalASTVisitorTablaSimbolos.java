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

    String salida = "";
    public String generarSalida(ParseTree tree) {
        visit(tree);
        return salida;
    }

    @Override
    public Void visitProgram(MiniPascalParser.ProgramContext ctx) {
        tablaSimbolos.enterScope(currentScope);
        salida += '\n' + ("Program:");
        salida += '\n' + ("  Encabezado Programa:");
        visit(ctx.programHeading());
        salida += '\n' + ("  Block:");
        visit(ctx.block());
        salida += '\n' + ("\nFin de Programa");
        return null;
    }

    @Override
    public Void visitProgramHeading(MiniPascalParser.ProgramHeadingContext ctx) {
        salida += '\n' + ("      PROGRAM " + ctx.identifier().getText());
        return null;
    }


    @Override
    public Void visitVariableDeclarationPart(MiniPascalParser.VariableDeclarationPartContext ctx) {
        if(ctx == null){
            return null;
        }
        salida += '\n' + ("  Declaracion de Variables:");
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
        //son"        Grupo de Parametros:");
        String tipo = ctx.type_().getText();
        if (ctx.identifierList() != null) {
            salida += '\n' + ("          Identificadores:");
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
                        salida += '\n' +("Error: Arreglo '" + nombre + "' ya declarado en el ámbito '" + currentScope + "'.");
                    } else {
                        salida += '\n' + ("Arreglo '" + nombre + "' de tipo '" + tipoArreglo + "' añadido.");
                    }
                }else {
                    if (!tablaSimbolos.addSimbolo(new Simbolo(nombre, tipo, currentScope, null, "variable"))) { // Añadir a la tabla
                        salida += '\n' +("Error: Variable '" + nombre + "' ya declarada en el ámbito '" + currentScope + "'.");
                    } else {
                        salida += '\n' + ("Variable '" + nombre + "' de tipo '" + tipo + "' añadida.");
                    }
                }
            }
        } else {
            //son"          Sin identificadores");
        }
        //son);
        //son"          Tipo: ");
        if (ctx.type_() != null) {
            visit(ctx.type_());
        } else {
            //son"          Sin Tipo");
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
        salida += '\n' + ("  Definicion de Constantes:");
        for (MiniPascalParser.ConstantDefinitionContext constantDefinitionContext : ctx.constantDefinition()) {
            visit(constantDefinitionContext);
        }
        return null;
    }

    @Override
    public Void visitConstantDefinition(MiniPascalParser.ConstantDefinitionContext ctx) {
        String name = ctx.identifier().getText();
        String value = ctx.constant().getText();
        String tipo = "Integer";
        if (value.contains("'") && value.length() >= 3) {
            value = value.substring(1, value.indexOf("'", 1));
            if (value.length() == 1) {
                tipo = "Character";
            }
            else {
                tipo = "String";
            }
        }
        salida += '\n' + ("value " + value);

        if (!tablaSimbolos.addSimbolo(new Simbolo(name, tipo, currentScope, value, "constant"))) {
            salida += '\n' +("Error: Constante '" + name + "' ya declarada.");
        } else {
            salida += '\n' + ("Constante '" + name + "' con valor '" + value + "' añadida.");
        }
        return null;
    }

    @Override
    public Void visitProcedureAndFunctionDeclarationPart(MiniPascalParser.ProcedureAndFunctionDeclarationPartContext ctx) {
        if(ctx == null){
            return null;
        }
        salida += '\n' + ("  Declaracion de Procedure y Function:");
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
        salida += '\n' + ("    Declaracion de Function:");
        salida += '\n' + ("      Encabezado de Function:");
        salida += '\n' + ("      " + ctx.identifier().getText());
        String functionName = ctx.identifier().getText();
        if (ctx.formalParameterList() != null) {
            salida += '\n' + ("  Parámetros:");
            for (MiniPascalParser.FormalParameterSectionContext paramCtx : ctx.formalParameterList().formalParameterSection()) {
                if(paramCtx.parameterGroup().identifierList() == null){
                    salida += '\n' + ("Sin Parametros");
                }
                else{
                    salida += '\n' + ("Encuentra Parametros");
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
        //son"      Tipo Retorno:");
        //son"      " + ctx.varType().getText());
        String returnType = ctx.varType().getText();
        if(!tablaSimbolos.addSimbolo(new SimboloFuncion(functionName, returnType, currentScope, parametros))) {
            salida += '\n' +("Error: Funcion '" + functionName + "' ya declarada en el ámbito '" + previousScope + "'.");
        }
        currentScope = previousScope;
        tablaSimbolos.exitScope();
        return null;
    }

    @Override


    public Void visitProcedureDeclaration(MiniPascalParser.ProcedureDeclarationContext ctx) {
        ArrayList<Simbolo> parametrosProcedimiento = new ArrayList();
        String procedureName = ctx.identifier().getText();
        salida += '\n' + ("Entrando en el procedimiento: " + procedureName);

        // Cambiar de ámbito
        String previousScope = currentScope;
        currentScope = procedureName;
        tablaSimbolos.enterScope(currentScope);
        salida += '\n' + ("    Declaracion de Procedure:");
        salida += '\n' + ("      Encabezado de Procedure:");
        // Añadir el procedimiento como símbolo


        if (ctx.formalParameterList().formalParameterSection() != null) {
            salida += '\n' + ("  Parámetros:");
            for (MiniPascalParser.FormalParameterSectionContext paramCtx : ctx.formalParameterList().formalParameterSection()) {
                // Extraemos la lista de identificadores (pueden ser múltiples) y su tipo
                if(paramCtx.parameterGroup().identifierList() == null){
                    //son"Sin Parametros");
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
        if (!tablaSimbolos.addSimbolo(new SimboloFuncion( procedureName, "procedure", currentScope, parametrosProcedimiento))) {
            salida += '\n' +("Error: Procedimiento '" + procedureName + "' ya declarado en el ámbito '" + previousScope + "'.");
        }
        // Volver al ámbito anterior
        currentScope = previousScope;
        tablaSimbolos.exitScope();
        return null;
    }

    @Override
    public Void visitFormalParameterList(MiniPascalParser.FormalParameterListContext ctx) {
        if(ctx == null){
            //son"        Sin Parametros");
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
        /*//son"        Grupo de Parametros:");
        if (ctx.identifierList() != null) {
            son"          Identificadores:");
            System.out.print("            ");
            for (MiniPascalParser.IdentifierContext identifier : ctx.identifierList().identifier()) {
                if(identifier == ctx.identifierList().identifier().get(ctx.identifierList().identifier().size()-1)){
                    System.out.print(identifier.getText());
                }else {
                    System.out.print(identifier.getText() + ", ");
                }
            }
        } else {
            //son"          Sin identificadores");
        }
        son);
        son"          Tipo: ");
        if (ctx.varType() != null) {
            visit(ctx.varType());
        } else {
            //son"          Sin Tipo");
        }*/
        return null;
    }

    @Override
    public Void visitVarType(MiniPascalParser.VarTypeContext ctx) {
        if (ctx.getChildCount() == 1) {
            //son"            "+ctx.getChild(0).getText());
        } else {
            /*son"            ARREGLO ");
            son"          " + ctx.getChild(1).getText());
            son"          " + ctx.getChild(3).getText());*/
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
        //son"  Compound Statement:");
        visit(ctx.statements());
        return null;
    }

    // Sem: Declaración de variables antes del uso
    @Override
    public Void visitAssignmentStatement(MiniPascalParser.AssignmentStatementContext ctx) {
        String variable = ctx.variable().getText();
        int indiceLlave = variable.indexOf('[', 0);
        if(indiceLlave > 0) {
            variable = variable.substring(0, indiceLlave);
        }
        String expression = ctx.expression().getText();

        if (!tablaSimbolos.findSimbolo(variable)) {
            salida += '\n' +("Error: La variable '" + variable + "' no está declarada en el ámbito '" + currentScope + "'.");
        } else {
            salida += '\n' + ("Asignación: '" + variable + "' = " + expression);
        }
        return null;
    }

    @Override
    public Void visitIfStatement(MiniPascalParser.IfStatementContext ctx) {
        salida += '\n' + ("    If Statement:");
        salida += '\n' + ("      Condition:");
        salida += '\n' + ("        " + ctx.expression().getText());
        salida += '\n' + ("      Then:");
        visit(ctx.statement(0));
        if(ctx.ELSE() != null){
            salida += '\n' + ("      Else:");
            visit(ctx.statement(1));
        }
        return null;
    }

    @Override
    public Void visitWhileStatement(MiniPascalParser.WhileStatementContext ctx) {
        salida += '\n' + ("    While Statement:");
        salida += '\n' + ("      Condition:");
        salida += '\n' + ("        " + ctx.expression().getText());
        salida += '\n' + ("      Do:");
        visit(ctx.statement());
        return null;
    }

    // Sem: Declaración de variables antes del uso en un for
    @Override
    public Void visitForStatement(MiniPascalParser.ForStatementContext ctx) {
        salida += '\n' + ("    For Statement:");
        salida += '\n' + ("      Identificador:");
        salida += '\n' + ("        " + ctx.identifier().getText());
        salida += '\n' + ("      Valor Inicial:");
        visit(ctx.forList().initialValue());
        salida += '\n' + ("      Valor Final:");
        visit(ctx.forList().finalValue());
        salida += '\n' + ("      Do:");
        visit(ctx.statement());

        // Sem: Validar que la variable exista
        if (!tablaSimbolos.findSimbolo(ctx.identifier().getText())) {
            salida += '\n' +("Error: La variable '" + ctx.identifier().getText() + "' no está declarada en el ámbito '" + currentScope + "'.");
        }
        return null;
    }

    @Override
    public Void visitInitialValue(MiniPascalParser.InitialValueContext ctx) {
        salida += '\n' + ("        " + ctx.getText());
        return null;
    }

    @Override
    public Void visitFinalValue(MiniPascalParser.FinalValueContext ctx) {
        salida += '\n' + ("        " + ctx.getText());
        return null;
    }

    @Override
    public Void visitWriteStatement(MiniPascalParser.WriteStatementContext ctx) {
        salida += '\n' + ("Write Statement:");
        System.out.print("  Write ");
        if (ctx.write() == null) {
            salida += '\n' + ("Statement:");
        } else {
            salida += '\n' + ("Line:");
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
            salida += '\n' + ("Identificador Variable: " + ctx.identifier().getText());
        }
        return null;
    }

    @Override
    public Void visitVarValue(MiniPascalParser.VarValueContext ctx) {
        if (ctx.string() != null) {
            salida += '\n' + ("String Value: " + ctx.string().getText());
            visit(ctx.string());
        } else if (ctx.boolean_() != null) {
            salida += '\n' + ("Boolean Value: " + ctx.boolean_());
            visit(ctx.boolean_());
        } else if (ctx.char_() != null) {
            salida += '\n' + ("Char Value: " + ctx.char_().getText());
            visit(ctx.char_());
        } else if (ctx.integer() != null) {
            salida += '\n' + ("Integer Value: " + ctx.integer().getText());
            visit(ctx.integer());
        }
        return null;
    }

    @Override
    public Void visitString(MiniPascalParser.StringContext ctx) {
        salida += '\n' + ("        " + ctx.getText());
        return null;
    }

    @Override
    public Void visitBoolean(MiniPascalParser.BooleanContext ctx) {
        salida += '\n' + ("        " + ctx.getText());
        return null;
    }

    @Override
    public Void visitChar(MiniPascalParser.CharContext ctx) {
        salida += '\n' + ("        " + ctx.getText());
        return null;
    }

    @Override
    public Void visitInteger(MiniPascalParser.IntegerContext ctx) {
        salida += '\n' + ("        " + ctx.getText());
        return null;
    }

    @Override
    public Void visitReadStatement(MiniPascalParser.ReadStatementContext ctx) {
        salida += '\n' + ("    Read Statement:");
        visit(ctx.readParam());
        return null;
    }

    @Override
    public Void visitReadParam(MiniPascalParser.ReadParamContext ctx) {
        salida += '\n' + ("      " + ctx.identifier().getText());
        return null;
    }

    @Override
    public Void visitFunctionDesignator(MiniPascalParser.FunctionDesignatorContext ctx) {
        salida += '\n' + ("Function Designator:");
        salida += '\n' + ("Identificador de Funcion: " + ctx.identifier().getText());
        if (ctx.parameterList() != null) {
            salida += '\n' + ("Parametros:");
            visit(ctx.parameterList()); // Visitar el nodo de la lista de parámetros
        }
        return null;
    }

    @Override
    public Void visitParameterList(MiniPascalParser.ParameterListContext ctx) {
        if(ctx == null){
            //son"        Sin Parametros");
            return null;
        }
        for (MiniPascalParser.ActualParameterContext actualParameterContext : ctx.actualParameter()) {
            visit(actualParameterContext);
        }
        return null;
    }

    @Override
    public Void visitActualParameter(MiniPascalParser.ActualParameterContext ctx) {
        salida += '\n' + ("        " + ctx.getText());
        return null;
    }

    @Override
    public Void visitExpression(MiniPascalParser.ExpressionContext ctx) {
        salida += '\n' + ("    Expresion:");
        visit(ctx.simpleExpression());
        if(ctx.relationaloperator() != null){
            salida += '\n' + ("      " + ctx.relationaloperator().getText());
            visit(ctx.simpleExpression());
        }
        return null;
    }

    @Override
    public Void visitSimpleExpression(MiniPascalParser.SimpleExpressionContext ctx) {
        visit(ctx.term()); // Visitar el nodo del término
        if (ctx.additiveoperator() != null) {
            salida += '\n' + ("Operador Aditivo: " + ctx.additiveoperator().getText());
            visit(ctx.simpleExpression()); // Visitar el nodo de la expresión simple
        }
        return null;
    }

    @Override
    public Void visitTerm(MiniPascalParser.TermContext ctx) {
        //son"Term:");
        visit(ctx.signedFactor());
        if (ctx.multiplicativeoperator() != null) {
            salida += '\n' + ("Operador Multiplicativo: " + ctx.multiplicativeoperator().getText());
            visit(ctx.term()); // Visitar el nodo del término
        }
        return null;
    }
    public Void visitFactor(MiniPascalParser.FactorContext ctx) {
        //son"Factor:");
        if (ctx.variable() != null) {
            visit(ctx.variable()); // Visitar el nodo de la variable
        } else if (ctx.expression() != null) {
            visit(ctx.expression()); // Visitar el nodo de la expresión
        } else if (ctx.functionDesignator() != null) {
            visit(ctx.functionDesignator()); // Visitar el nodo del designador de función
        } else if (ctx.unsignedConstant() != null) {
            visit(ctx.unsignedConstant()); // Visitar el nodo de la constante no firmada
        }else if (ctx.NOT() != null) {
            salida += '\n' + ("NOT");
            visit(ctx.factor()); // Visitar el nodo del factor
        } else if (ctx.unsignedConstant().bool_() != null) {
            salida += '\n' + ("Valor booleano: " + ctx.unsignedConstant().bool_().getText());
        }
        return null;
    }

    @Override
    public Void visitUnsignedConstant(MiniPascalParser.UnsignedConstantContext ctx) {
        if (ctx.unsignedNumber() != null) {
            salida += '\n' + ("Numero Unsigned: " + ctx.unsignedNumber().getText());
        } else if (ctx.constantChr() != null) {
            salida += '\n' + ("Char constante: " + ctx.constantChr().getText());
        } else if (ctx.string() != null) {
            salida += '\n' + ("Valor String: " + ctx.string().getText());
        } else if (ctx.NIL() != null) {
            salida += '\n' + ("NIL");
        }
        return null;
    }

    @Override
    public Void visitIdentifierList(MiniPascalParser.IdentifierListContext ctx) {
        for (MiniPascalParser.IdentifierContext ctx2: ctx.identifier()) {
            salida += '\n' + ("    Identifier: " + ctx2.getText());
        }
        return null;
    }


}
