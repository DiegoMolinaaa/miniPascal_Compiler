package antlr4;

import org.antlr.v4.runtime.tree.ParseTree;

import java.util.ArrayList;

public class MiniPascalASTVisitorTAC extends MiniPascalBaseVisitor<Object>{

    Codigo3Direcciones codigo3Direcciones = new Codigo3Direcciones();
    TablaSimbolos tablaSimbolos = new TablaSimbolos();
    String currentScope = "global";
    String previousScope = "";

    MiniPascalASTVisitorTAC(Codigo3Direcciones codigo3Direcciones ,TablaSimbolos tablaSimbolos){
        this.codigo3Direcciones = codigo3Direcciones;
        this.tablaSimbolos = tablaSimbolos;
    }

    public String generarSalida(ParseTree arbol){
        System.out.println("\n\nGenerando Salida 3 direcciones");
        visit(arbol);
        codigo3Direcciones.imprimirQuintuplos();
        return "";
    }

    @Override
    public Void visitProgram(MiniPascalParser.ProgramContext ctx) {
        visit(ctx.block());
        return null;
    }

    @Override
    public Void visitProcedureOrFunctionDeclaration(MiniPascalParser.ProcedureOrFunctionDeclarationContext ctx) {
        if(ctx == null){
            return null;
        }
        if(ctx.procedureDeclaration() != null){
            visit(ctx.procedureDeclaration());
        } else if(ctx.functionDeclaration() != null){
            visit(ctx.functionDeclaration());
        }
        return null;
    }

    @Override
    public Void visitProcedureDeclaration(MiniPascalParser.ProcedureDeclarationContext ctx) {
        String nombreProcedimiento = ctx.identifier().getText();
        previousScope = currentScope;
        currentScope = nombreProcedimiento;
        tablaSimbolos.enterScope(currentScope);
        if(tablaSimbolos.findSimbolo(nombreProcedimiento)){
            visit(ctx.block());

        }
        tablaSimbolos.exitScope();
        currentScope = previousScope;
        return null;
    }

    @Override
    public Void visitFunctionDeclaration(MiniPascalParser.FunctionDeclarationContext ctx) {
        String nombreFuncion = ctx.identifier().getText();
        previousScope = currentScope;
        currentScope = nombreFuncion;
        tablaSimbolos.enterScope(currentScope);
        if(tablaSimbolos.findSimbolo(nombreFuncion)){
            visit(ctx.block());
        }
        tablaSimbolos.exitScope();
        currentScope = previousScope;
        return null;
    }

    @Override
    public Void visitBlock(MiniPascalParser.BlockContext ctx) {
        if(ctx == null){
            return null;
        }
        else{
            for(MiniPascalParser.ProcedureAndFunctionDeclarationPartContext ProcedureandFunctionDContext : ctx.procedureAndFunctionDeclarationPart()){
                visit(ProcedureandFunctionDContext);
            }
            visit(ctx.compoundStatement());
        }
        return null;
    }

    @Override
    public Void visitCompoundStatement(MiniPascalParser.CompoundStatementContext ctx) {
        if(ctx == null){
            return null;
        }
        else{
            for(MiniPascalParser.StatementContext statementContext : ctx.statements().statement()){
                System.out.println("Statement: " + statementContext.getText());
                visit(statementContext);
            }

        }
        return null;
    }

    @Override
    public Void visitStatement(MiniPascalParser.StatementContext ctx) {
        if(ctx == null){
            return null;
        }
        else{
            if(ctx.writeStatement() != null){
                visit(ctx.writeStatement());
            }
            if(ctx.readStatement() != null){
                visit(ctx.readStatement());
            }
            if(ctx.unlabelledStatement() != null){
                if(ctx.unlabelledStatement().simpleStatement() != null){
                    if(ctx.unlabelledStatement().simpleStatement().assignmentStatement() != null){
                        visit(ctx.unlabelledStatement().simpleStatement().assignmentStatement());
                    }
                }
                if(ctx.unlabelledStatement().structuredStatement() != null){
                    if(ctx.unlabelledStatement().structuredStatement().conditionalStatement() != null){
                        if(ctx.unlabelledStatement().structuredStatement().conditionalStatement().ifStatement() != null){
                            visit(ctx.unlabelledStatement().structuredStatement().conditionalStatement().ifStatement());
                        }
                    }
                    if(ctx.unlabelledStatement().structuredStatement().repetitiveStatement() != null){
                        if(ctx.unlabelledStatement().structuredStatement().repetitiveStatement().whileStatement() != null){
                            visit(ctx.unlabelledStatement().structuredStatement().repetitiveStatement().whileStatement());
                        }
                        if(ctx.unlabelledStatement().structuredStatement().repetitiveStatement().forStatement() != null){
                            visit(ctx.unlabelledStatement().structuredStatement().repetitiveStatement().forStatement());
                        }
                    }
                    if(ctx.unlabelledStatement().structuredStatement().compoundStatement() != null){
                        visit(ctx.unlabelledStatement().structuredStatement().compoundStatement());
                    }
                }

            }
        }
        return null;
    }


    //Assignment
    @Override
    public Void visitAssignmentStatement(MiniPascalParser.AssignmentStatementContext ctx) {
        System.out.println("Llega a AssignmentStatement");

        // Obtener la variable de la asignación
        String variable = ctx.variable().getText();
        if(variable.contains("[")){
            variable = variable.substring(0, variable.indexOf("["));
        }
        // Verificar si la variable está declarada
        System.out.println("Current Scope: " + currentScope);
        if (!tablaSimbolos.findSimbolo(variable)) {
            throw new RuntimeException("Error: Variable '" + variable + "' no declarada.");
        }

        // Procesar la expresión del lado derecho
        String tempOrValue = processExpression(ctx.expression());
        if(!currentScope.equalsIgnoreCase( "global")){
            if(tablaSimbolos.getSimbolo(currentScope, currentScope) != null){
                SimboloFuncion temporal = (SimboloFuncion) tablaSimbolos.getSimbolo(currentScope, currentScope);
                temporal.tacFunciones.agregarQuintuplo(":=", tempOrValue, variable);
            }

        }else{
            // Generar el quíntuplo para la asignación
            codigo3Direcciones.agregarQuintuplo(":=", tempOrValue, variable);
        }

        return null;
    }
    private String processExpression(MiniPascalParser.ExpressionContext ctx) {
        // Caso 1: Expresión relacional
        if (ctx.relationaloperator() != null) {
            // Process left side, checking for global variables
            String left = processSimpleExpression(ctx.simpleExpression());

            // Check if left is a global variable and needs loading
            if (isGlobalVariable(left)) {
                if(!currentScope.equalsIgnoreCase("global")) {
                    if(tablaSimbolos.getSimbolo(currentScope, currentScope) != null){
                        SimboloFuncion temporal = (SimboloFuncion) tablaSimbolos.getSimbolo(currentScope, currentScope);
                        String tempLeft = temporal.tacFunciones.nuevoTemporal();
                        temporal.tacFunciones.agregarQuintuplo("load", left, tempLeft);
                        left = tempLeft;
                    }
                } else {
                    String tempLeft = codigo3Direcciones.nuevoTemporal();
                    codigo3Direcciones.agregarQuintuplo("load", left, tempLeft);
                    left = tempLeft;
                }
            }

            // Process right side, checking for global variables
            String right = processExpression(ctx.expression());
            if (isGlobalVariable(right)) {
                if(!currentScope.equalsIgnoreCase("global")) {
                    if(tablaSimbolos.getSimbolo(currentScope, currentScope) != null){
                        SimboloFuncion temporal = (SimboloFuncion) tablaSimbolos.getSimbolo(currentScope, currentScope);
                        String tempRight = temporal.tacFunciones.nuevoTemporal();
                        temporal.tacFunciones.agregarQuintuplo("load", right, tempRight);
                        right = tempRight;
                    }
                } else {
                    String tempRight = codigo3Direcciones.nuevoTemporal();
                    codigo3Direcciones.agregarQuintuplo("load", right, tempRight);
                    right = tempRight;
                }
            }

            String op = ctx.relationaloperator().getText();

            if(!currentScope.equalsIgnoreCase("global")){
                if(tablaSimbolos.getSimbolo(currentScope, currentScope) != null){
                    SimboloFuncion temporal = (SimboloFuncion) tablaSimbolos.getSimbolo(currentScope, currentScope);
                    String temp = temporal.tacFunciones.nuevoTemporal();
                    temporal.tacFunciones.agregarQuintuplo(op, left, right, temp);

                    return temp;
                }
            } else {
                // Crear un temporal para guardar el resultado
                String temp = codigo3Direcciones.nuevoTemporal();
                codigo3Direcciones.agregarQuintuplo(op, left, right, temp);

                return temp;
            }
        }

        // Caso 2: Expresión simple
        return processSimpleExpression(ctx.simpleExpression());
    }

    private String processSimpleExpression(MiniPascalParser.SimpleExpressionContext ctx) {
        // Caso 1: Operación aditiva
        if (ctx.additiveoperator() != null) {
            // Process left side, checking for global variables
            String left = processTerm(ctx.term());

            // Check if left is a global variable and needs loading
            if (isGlobalVariable(left)) {
                if(!currentScope.equalsIgnoreCase("global")) {
                    if(tablaSimbolos.getSimbolo(currentScope, currentScope) != null){
                        SimboloFuncion temporal = (SimboloFuncion) tablaSimbolos.getSimbolo(currentScope, currentScope);
                        String tempLeft = temporal.tacFunciones.nuevoTemporal();
                        temporal.tacFunciones.agregarQuintuplo("load", left, tempLeft);
                        left = tempLeft;
                    }
                } else {
                    String tempLeft = codigo3Direcciones.nuevoTemporal();
                    codigo3Direcciones.agregarQuintuplo("load", left, tempLeft);
                    left = tempLeft;
                }
            }

            // Process right side, checking for global variables
            String right = processSimpleExpression(ctx.simpleExpression());
            if (isGlobalVariable(right)) {
                if(!currentScope.equalsIgnoreCase("global")) {
                    if(tablaSimbolos.getSimbolo(currentScope, currentScope) != null){
                        SimboloFuncion temporal = (SimboloFuncion) tablaSimbolos.getSimbolo(currentScope, currentScope);
                        String tempRight = temporal.tacFunciones.nuevoTemporal();
                        temporal.tacFunciones.agregarQuintuplo("load", right, tempRight);
                        right = tempRight;
                    }
                } else {
                    String tempRight = codigo3Direcciones.nuevoTemporal();
                    codigo3Direcciones.agregarQuintuplo("load", right, tempRight);
                    right = tempRight;
                }
            }

            String op = ctx.additiveoperator().getText();

            if(!currentScope.equalsIgnoreCase("global")){
                if(tablaSimbolos.getSimbolo(currentScope, currentScope) != null){
                    SimboloFuncion temporal = (SimboloFuncion) tablaSimbolos.getSimbolo(currentScope, currentScope);
                    String temp = temporal.tacFunciones.nuevoTemporal();
                    temporal.tacFunciones.agregarQuintuplo(op, left, right, temp);

                    return temp;
                }
            } else {
                // Crear un temporal para guardar el resultado
                String temp = codigo3Direcciones.nuevoTemporal();
                codigo3Direcciones.agregarQuintuplo(op, left, right, temp);

                return temp;
            }
        }

        // Caso 2: Término único
        return processTerm(ctx.term());
    }

    private String processTerm(MiniPascalParser.TermContext ctx) {
        // Caso 1: Operación multiplicativa
        if (ctx.multiplicativeoperator() != null) {
            String left = processFactor(ctx.signedFactor());
            String right = processTerm(ctx.term());
            String op = ctx.multiplicativeoperator().getText();

            if(!currentScope.equalsIgnoreCase( "global")) {
                if (tablaSimbolos.getSimbolo(currentScope, currentScope) != null) {
                    SimboloFuncion temporal = (SimboloFuncion) tablaSimbolos.getSimbolo(currentScope, currentScope);
                    String temp = temporal.tacFunciones.nuevoTemporal();
                    temporal.tacFunciones.agregarQuintuplo(op, left, right, temp);

                    return temp;
                }
            }else {
                    // Crear un temporal para guardar el resultado
                    String temp = codigo3Direcciones.nuevoTemporal();
                    codigo3Direcciones.agregarQuintuplo(op, left, right, temp);

                    return temp;
                }
        }

        // Caso 2: Factor único
        return processFactor(ctx.signedFactor());
    }

    private String processFactor(MiniPascalParser.SignedFactorContext ctx) {
        // Caso 1: Factor con signo
        if (ctx.PLUS() != null || ctx.MINUS() != null) {
            String sign = ctx.PLUS() != null ? "+" : "-";
            String factor = processUnsignedFactor(ctx.factor());

            if(!currentScope.equalsIgnoreCase( "global")) {
                if (tablaSimbolos.getSimbolo(currentScope, currentScope) != null) {
                    SimboloFuncion temporal = (SimboloFuncion) tablaSimbolos.getSimbolo(currentScope, currentScope);
                    String temp = temporal.tacFunciones.nuevoTemporal();
                    temporal.tacFunciones.agregarQuintuplo(sign, factor, temp);

                    return temp;
                }
            }else {

                // Crear un temporal para guardar el resultado del signo
                String temp = codigo3Direcciones.nuevoTemporal();
                codigo3Direcciones.agregarQuintuplo(sign, factor, temp);

                return temp;
            }
        }

        // Caso 2: Factor sin signo
        return processUnsignedFactor(ctx.factor());
    }

    private String processUnsignedFactor(MiniPascalParser.FactorContext ctx) {
        // Caso 1: Variable
        if (ctx.variable() != null) {
            String variable = ctx.variable().getText();
            if(variable.contains("[")){
                variable = variable.substring(0, variable.indexOf("["));
            }

            // Check if it's a global variable
            if (isGlobalVariable(variable)) {
                // Generate a LOAD_GLOBAL quintuplo
                if(!currentScope.equalsIgnoreCase("global")) {
                    if(tablaSimbolos.getSimbolo(currentScope, currentScope) != null){
                        SimboloFuncion temporal = (SimboloFuncion) tablaSimbolos.getSimbolo(currentScope, currentScope);
                        String tempLoaded = temporal.tacFunciones.nuevoTemporal();
                        temporal.tacFunciones.agregarQuintuplo("load", variable, tempLoaded);
                        return tempLoaded;
                    }
                } else {
                    String tempLoaded = codigo3Direcciones.nuevoTemporal();
                    codigo3Direcciones.agregarQuintuplo("load", variable, tempLoaded);
                    return tempLoaded;
                }
            }

            return variable;
        }

        // Caso 2: Constante
        if (ctx.unsignedConstant() != null) {
            return ctx.unsignedConstant().getText();
        }

        // Caso 3: Llamada a función
        if (ctx.functionDesignator() != null) {
            return processFunctionDesignator(ctx.functionDesignator());
        }

        // Caso 4: Expresión entre paréntesis
        if (ctx.L_PAREN() != null) {
            return processExpression(ctx.expression());
        }

        throw new RuntimeException("Error: Factor no reconocido.");
    }
    private String processFunctionDesignator(MiniPascalParser.FunctionDesignatorContext ctx) {
        // Obtener el nombre de la función
        String functionName = ctx.identifier().getText();

        // Procesar los parámetros actuales
        ArrayList<String> argumentos = new ArrayList<>();
        if (ctx.parameterList() != null) { // Verificar si hay parámetros
            for (MiniPascalParser.ActualParameterContext param : ctx.parameterList().actualParameter()) {
                // Procesar cada expresión de parámetro
                argumentos.add(processExpression(param.expression()));
            }
        }

        if(!currentScope.equalsIgnoreCase( "global")) {
            if (tablaSimbolos.getSimbolo(currentScope, currentScope) != null) {
                SimboloFuncion temporal = (SimboloFuncion) tablaSimbolos.getSimbolo(currentScope, currentScope);
                String temp = temporal.tacFunciones.nuevoTemporal();
                temporal.tacFunciones.agregarQuintuplo("call", functionName, String.join(", ", argumentos), temp);

                return temp;
            }
        }else {
            // Generar un temporal para el resultado de la función
            String temp = codigo3Direcciones.nuevoTemporal();

            // Crear un quíntuplo para la llamada a la función
            codigo3Direcciones.agregarQuintuplo("call", functionName, String.join(", ", argumentos), temp);

            return temp;
        }
        return "";
    }

    private boolean isGlobalVariable(String variable) {
        // Logic to check if the variable is in the global scope
        return tablaSimbolos.getSimbolo(variable, "global") != null;
    }
        //Write
    @Override
    public Void visitWriteStatement(MiniPascalParser.WriteStatementContext ctx) {
        // Determinar si es write o writeln
        String writeType = ctx.write().getText().toLowerCase(); // "write" o "writeln"

        // Obtener el primer parámetro (debe ser una cadena)
        String cadena = ctx.writeParam2().getText();

        // Validar el segundo parámetro si existe
        String segundoParametro = "_"; // Si no existe, usamos un valor predeterminado
        if (ctx.writeParam() != null) {
            segundoParametro = processWriteParam(ctx.writeParam());
        }
        if(!currentScope.equalsIgnoreCase( "global")) {
            if (tablaSimbolos.getSimbolo(currentScope, currentScope) != null) {
                SimboloFuncion temporal = (SimboloFuncion) tablaSimbolos.getSimbolo(currentScope, currentScope);
                if(!segundoParametro.equals("_")) {
                    if(tablaSimbolos.getSimbolo(segundoParametro, currentScope) != null) {
                        String temp = temporal.tacFunciones.nuevoTemporal();
                        temporal.tacFunciones.agregarQuintuplo("load", cadena, temp);
                    }
                }
                temporal.tacFunciones.agregarQuintuplo(writeType, cadena, segundoParametro, "_");
            }
        }else {
            // Generar el quíntuplo para la operación write/writeln
            if (!segundoParametro.equals("_")) {
                if (tablaSimbolos.getSimbolo(segundoParametro, currentScope) != null) {
                    Simbolo s = tablaSimbolos.getSimbolo(segundoParametro, currentScope);
                    String temp = codigo3Direcciones.nuevoTemporal();
                    codigo3Direcciones.agregarQuintuplo("load", segundoParametro, temp);
                    codigo3Direcciones.agregarQuintuplo(writeType, cadena, temp, s.getType());
                } else {
                    codigo3Direcciones.agregarQuintuplo(writeType, cadena, segundoParametro, "_");
                }
            } else {
                codigo3Direcciones.agregarQuintuplo(writeType, cadena, segundoParametro, "_");
            }
        }

        return null;
    }

    private String processWriteParam(MiniPascalParser.WriteParamContext ctx) {
        // Caso 1: varValue (puede ser string, char, integer o boolean)
        if (ctx.varValue() != null) {
            return processVarValue(ctx.varValue());
        }

        // Caso 2: identifier
        if (ctx.identifier() != null) {
            String nombreIdentificador = ctx.identifier().getText();
            validarIdentificador(nombreIdentificador); // Validación en tabla de símbolos
            return nombreIdentificador;
        }

        // Caso 3: arrayValue
        if (ctx.arrayValue() != null) {
            return processArrayValue(ctx.arrayValue());
        }

        throw new RuntimeException("Error: writeParam no reconocido.");
    }

    private String processVarValue(MiniPascalParser.VarValueContext ctx) {
        // Verificar el tipo de valor (string, char, integer o boolean)
        if (ctx.string() != null) {
            return ctx.string().getText();
        }
        if (ctx.char_() != null) {
            return ctx.char_().getText();
        }
        if (ctx.integer() != null) {
            return ctx.integer().getText();
        }
        if (ctx.boolean_() != null) {
            return ctx.boolean_().getText();
        }

        throw new RuntimeException("Error: varValue no reconocido.");
    }

    private String processArrayValue(MiniPascalParser.ArrayValueContext ctx) {
        // Manejar acceso a arreglos (por ejemplo: array[i])
        String nombreArreglo = ctx.identifier(0).getText();
        validarIdentificador(nombreArreglo); // Validación del arreglo en la tabla de símbolos

        String indice = "";

        // El índice puede ser un entero o un identificador
        if (ctx.integer() != null) {
            indice = ctx.integer().getText();
        } else if (ctx.identifier() != null) {
            String indiceIdentificador = ctx.identifier(1).getText();
            validarIdentificador(indiceIdentificador); // Validación del índice
            indice = indiceIdentificador;
        } else {
            throw new RuntimeException("Error: Índice de arreglo no válido.");
        }

        // Retornar el acceso al arreglo en formato: "arreglo[indice]"
        return nombreArreglo + "[" + indice + "]";
    }

    private void validarIdentificador(String nombreIdentificador) {
        // Verificar que el identificador exista en la tabla de símbolos
        if (!tablaSimbolos.findSimbolo(nombreIdentificador)) {
            throw new RuntimeException("Error: El identificador '" + nombreIdentificador + "' no ha sido declarado.");
        }

        // Verificar que el tipo sea compatible con la instrucción write (ej., no puede ser un tipo no soportado)
        String tipo = tablaSimbolos.getSimbolo(nombreIdentificador, currentScope).getType();
        if (!esTipoCompatibleConWrite(tipo)) {
            throw new RuntimeException("Error: El identificador '" + nombreIdentificador + "' tiene un tipo no compatible con write: " + tipo);
        }
    }

    private boolean esTipoCompatibleConWrite(String tipo) {
        // Los tipos compatibles con write: string, char, integer
        return tipo.equalsIgnoreCase("string") || tipo.equalsIgnoreCase("char") || tipo.equalsIgnoreCase("integer") || tipo.equalsIgnoreCase("boolean") || tipo.equalsIgnoreCase("array");
    }

    //Read
    @Override
    public Void visitReadStatement(MiniPascalParser.ReadStatementContext ctx) {
        // Obtener el identificador de la variable a leer
        String variable = ctx.readParam().getText();

        // Verificar si el identificador es un arreglo o una variable simple
        if (variable.contains("[")) {
            // Es un acceso a un arreglo
            String nombreArreglo = variable.substring(0, variable.indexOf("["));
            String indice = variable.substring(variable.indexOf("[") + 1, variable.indexOf("]"));

            // Verificar si el arreglo está declarado
            if (!tablaSimbolos.findSimbolo(nombreArreglo)) {
                throw new RuntimeException("Error: Arreglo '" + nombreArreglo + "' no declarado.");
            }

            // Verificar si el índice es válido (debe ser un entero o identificador declarado)
            if (!esIndiceValido(indice)) {
                throw new RuntimeException("Error: Índice '" + indice + "' no válido.");
            }

            // Verificar que el arreglo es de tipo compatible con "read" (por ejemplo, que sea un arreglo de enteros, cadenas, etc.)
            String tipoArreglo = tablaSimbolos.getSimbolo(nombreArreglo, currentScope).getType();
            if (!esTipoCompatibleConRead(tipoArreglo)) {
                throw new RuntimeException("Error: El arreglo '" + nombreArreglo + "' no es de un tipo compatible con read.");
            }

            if(!currentScope.equalsIgnoreCase( "global")) {
                if (tablaSimbolos.getSimbolo(currentScope, currentScope) != null) {
                    SimboloFuncion temporal = (SimboloFuncion) tablaSimbolos.getSimbolo(currentScope, currentScope);
                    temporal.tacFunciones.agregarQuintuplo("read", nombreArreglo + "[" + indice + "]", "_", "_");
                }
            }else {
                // Generar el quíntuplo para leer un elemento del arreglo
                codigo3Direcciones.agregarQuintuplo("read", nombreArreglo + "[" + indice + "]", "_", "_");
            }
        } else {
            // Caso cuando la variable es una variable simple
            // Verificar si la variable está declarada
            if (!tablaSimbolos.findSimbolo(variable)) {
                throw new RuntimeException("Error: Variable '" + variable + "' no declarada.");
            }

            // Verificar que la variable sea de un tipo compatible con "read"
            String tipo = tablaSimbolos.getSimbolo(variable, currentScope).getType();
            if (!esTipoCompatibleConRead(tipo)) {
                throw new RuntimeException("Error: El identificador '" + variable + "' no es de un tipo compatible con read: " + tipo);
            }

            if(!currentScope.equalsIgnoreCase( "global")) {
                if (tablaSimbolos.getSimbolo(currentScope, currentScope) != null) {
                    SimboloFuncion temporal = (SimboloFuncion) tablaSimbolos.getSimbolo(currentScope, currentScope);
                    temporal.tacFunciones.agregarQuintuplo("read", variable, "_", "_");
                }
            }else {
                // Generar el quíntuplo para la operación read
                codigo3Direcciones.agregarQuintuplo("read", variable, "_", "_");
            }
        }

        return null;
    }

    private boolean esIndiceValido(String indice) {
        // El índice puede ser un número entero o un identificador
        if (indice.matches("\\d+")) {  // Es un número entero
            return true;
        }

        // Si es un identificador, debe estar declarado
        return tablaSimbolos.findSimbolo(indice);
    }

    private boolean esTipoCompatibleConRead(String tipo) {
        // Los tipos compatibles con read suelen ser: integer, string, char, boolean
        return tipo.equalsIgnoreCase("integer") || tipo.equalsIgnoreCase("string") || tipo.equalsIgnoreCase("char") || tipo.equalsIgnoreCase("boolean") || tipo.equalsIgnoreCase("array");
    }

    //Repetitive Statements
    @Override
    public Void visitWhileStatement(MiniPascalParser.WhileStatementContext ctx) {
        if(!currentScope.equalsIgnoreCase( "global")){
            if(tablaSimbolos.getSimbolo(currentScope, currentScope) != null){
                SimboloFuncion temporal = (SimboloFuncion) tablaSimbolos.getSimbolo(currentScope, currentScope);
                // Crear etiquetas para el inicio y la salida del ciclo
                String inicioEtiqueta = temporal.tacFunciones.nuevaEtiqueta(); // Etiqueta para evaluar la condición
                String trueEtiqueta = temporal.tacFunciones.nuevaEtiqueta(); // Etiqueta para el bloque de instrucciones
                String salidaEtiqueta = temporal.tacFunciones.nuevaEtiqueta(); // Etiqueta para salir del ciclo

                temporal.tacFunciones.agregarQuintuplo("goto", inicioEtiqueta, "", "");

                // Etiqueta de inicio del ciclo
                temporal.tacFunciones.agregarQuintuplo("label", inicioEtiqueta, "", "");

                // Procesar la condición del ciclo
                String condicionTemporal = processExpression(ctx.expression());

                // Saltar a la salida si la condición es falsa
                temporal.tacFunciones.agregarQuintuplo("ifFalse", condicionTemporal, trueEtiqueta, salidaEtiqueta);

                temporal.tacFunciones.agregarQuintuplo("label", trueEtiqueta, "", "");
                // Procesar el bloque de instrucciones del ciclo
                visit(ctx.statement());

                // Regresar al inicio del ciclo para reevaluar la condición
                temporal.tacFunciones.agregarQuintuplo("goto", inicioEtiqueta, "", "");

                // Etiqueta para la salida del ciclo
                temporal.tacFunciones.agregarQuintuplo("label", salidaEtiqueta, "", "");
            }

        }else {

            // Crear etiquetas para el inicio y la salida del ciclo
            String inicioEtiqueta = codigo3Direcciones.nuevaEtiqueta(); // Etiqueta para evaluar la condición
            String trueEtiqueta = codigo3Direcciones.nuevaEtiqueta(); // Etiqueta para el bloque de instrucciones
            String salidaEtiqueta = codigo3Direcciones.nuevaEtiqueta(); // Etiqueta para salir del ciclo

            codigo3Direcciones.agregarQuintuplo("goto", inicioEtiqueta, "", "");
            // Etiqueta de inicio del ciclo
            codigo3Direcciones.agregarQuintuplo("label", inicioEtiqueta, "", "");

            // Procesar la condición del ciclo
            String condicionTemporal = processExpression(ctx.expression());

            // Saltar a la salida si la condición es falsa
            codigo3Direcciones.agregarQuintuplo("ifFalse", condicionTemporal, trueEtiqueta, salidaEtiqueta);

            codigo3Direcciones.agregarQuintuplo("label", trueEtiqueta, "", "");
            // Procesar el bloque de instrucciones del ciclo
            visit(ctx.statement());

            // Regresar al inicio del ciclo para reevaluar la condición
            codigo3Direcciones.agregarQuintuplo("goto", inicioEtiqueta, "", "");

            // Etiqueta para la salida del ciclo
            codigo3Direcciones.agregarQuintuplo("label", salidaEtiqueta, "", "");
        }
        return null;
    }

    @Override
    public Void visitForStatement(MiniPascalParser.ForStatementContext ctx) {
        if(!currentScope.equalsIgnoreCase( "global")) {
            if (tablaSimbolos.getSimbolo(currentScope, currentScope) != null) {
                SimboloFuncion temporal = (SimboloFuncion) tablaSimbolos.getSimbolo(currentScope, currentScope);
                // 1. Validar el identificador en la tabla de símbolos
                String id = ctx.identifier().getText();
                Simbolo simbolo = tablaSimbolos.getSimbolo(id, currentScope);
                if (simbolo == null) {
                    throw new RuntimeException("Error: La variable '" + id + "' no está declarada.");
                }
                if (!simbolo.getType().equalsIgnoreCase("integer")) {
                    throw new RuntimeException("Error: La variable del for debe ser de tipo integer. Variable: '" + id + "'");
                }

                // 2. Procesar initialValue y finalValue
                String valorInicial = processExpression(ctx.forList().initialValue().expression());
                String valorFinal = processExpression(ctx.forList().finalValue().expression());

                // Generar código para inicializar el iterador
                temporal.tacFunciones.agregarQuintuplo(":=", valorInicial, id);

                // Generar etiquetas
                String inicioCiclo = temporal.tacFunciones.nuevaEtiqueta();
                String trueCiclo = temporal.tacFunciones.nuevaEtiqueta();
                String finCiclo = temporal.tacFunciones.nuevaEtiqueta();

                temporal.tacFunciones.agregarQuintuplo("goto", inicioCiclo, "", "");

                // Etiqueta de inicio del ciclo
                temporal.tacFunciones.agregarQuintuplo("label", inicioCiclo, "");

                // Evaluar condición del ciclo
                String condicion;
                if (ctx.forList().TO() != null) {
                    // Ciclo ascendente: id <= valorFinal
                    if(tablaSimbolos.getSimbolo(id, currentScope) != null){
                        String temporalId = temporal.tacFunciones.nuevoTemporal();
                        temporal.tacFunciones.agregarQuintuplo("load", id, temporalId);
                        id = temporalId;
                    }
                    if(tablaSimbolos.getSimbolo(valorFinal, currentScope) != null){
                        String temporalValorFinal = temporal.tacFunciones.nuevoTemporal();
                        temporal.tacFunciones.agregarQuintuplo("load", valorFinal, temporalValorFinal);
                        valorFinal = temporalValorFinal;
                    }
                    condicion = temporal.tacFunciones.nuevoTemporal();
                    temporal.tacFunciones.agregarQuintuplo("<=", id, valorFinal, condicion);
                } else if (ctx.forList().DOWNTO() != null) {
                    // Ciclo descendente: id >= valorFinal
                    if(tablaSimbolos.getSimbolo(id, currentScope) != null){
                        String temporalId = temporal.tacFunciones.nuevoTemporal();
                        temporal.tacFunciones.agregarQuintuplo("load", id, temporalId);
                        id = temporalId;
                    }
                    if(tablaSimbolos.getSimbolo(valorFinal, currentScope) != null){
                        String temporalValorFinal = temporal.tacFunciones.nuevoTemporal();
                        temporal.tacFunciones.agregarQuintuplo("load", valorFinal, temporalValorFinal);
                        valorFinal = temporalValorFinal;
                    }
                    condicion = temporal.tacFunciones.nuevoTemporal();
                    temporal.tacFunciones.agregarQuintuplo(">=", id, valorFinal, condicion);
                } else {
                    throw new RuntimeException("Error: Se esperaba TO o DOWNTO en el ciclo for.");
                }

                // Salto al fin del ciclo si la condición es falsa
                temporal.tacFunciones.agregarQuintuplo("ifFalse", condicion, trueCiclo, finCiclo);

                temporal.tacFunciones.agregarQuintuplo("label", trueCiclo, "");

                // Procesar el cuerpo del ciclo
                visit(ctx.statement());

                // Incremento o decremento del iterador
                String incremento = temporal.tacFunciones.nuevoTemporal();
                if (ctx.forList().TO() != null) {
                    // Incremento: id = id + 1
                    temporal.tacFunciones.agregarQuintuplo("+", id, "1", incremento);
                } else {
                    // Decremento: id = id - 1
                    temporal.tacFunciones.agregarQuintuplo("-", id, "1", incremento);
                }
                temporal.tacFunciones.agregarQuintuplo(":=", incremento, id);

                // Salto al inicio del ciclo
                temporal.tacFunciones.agregarQuintuplo("goto", inicioCiclo, "");

                // Etiqueta de fin del ciclo
                temporal.tacFunciones.agregarQuintuplo("label", finCiclo, "");
            }
        }else {
            // 1. Validar el identificador en la tabla de símbolos
            String idRetornar = ctx.identifier().getText();
            String id = ctx.identifier().getText();
            Simbolo simbolo = tablaSimbolos.getSimbolo(id, currentScope);
            if (simbolo == null) {
                throw new RuntimeException("Error: La variable '" + id + "' no está declarada.");
            }
            if (!simbolo.getType().equalsIgnoreCase("integer")) {
                throw new RuntimeException("Error: La variable del for debe ser de tipo integer. Variable: '" + id + "'");
            }

            // 2. Procesar initialValue y finalValue
            String valorInicial = processExpression(ctx.forList().initialValue().expression());
            String valorFinal = processExpression(ctx.forList().finalValue().expression());

            // Generar código para inicializar el iterador
            codigo3Direcciones.agregarQuintuplo(":=", valorInicial, id);

            // Generar etiquetas
            String inicioCiclo = codigo3Direcciones.nuevaEtiqueta();
            String trueCiclo = codigo3Direcciones.nuevaEtiqueta();
            String finCiclo = codigo3Direcciones.nuevaEtiqueta();

            // Etiqueta de inicio del ciclo
            codigo3Direcciones.agregarQuintuplo("goto", inicioCiclo, "", "");

            codigo3Direcciones.agregarQuintuplo("label", inicioCiclo, "");

            // Evaluar condición del ciclo
            String condicion;
            if (ctx.forList().TO() != null) {
                // Ciclo ascendente: id <= valorFinal
                if(tablaSimbolos.getSimbolo(id, currentScope) != null){
                    String temporalId = codigo3Direcciones.nuevoTemporal();
                    codigo3Direcciones.agregarQuintuplo("load", id, temporalId);
                    id = temporalId;
                }
                if(tablaSimbolos.getSimbolo(valorFinal, currentScope) != null){
                    String temporalValorFinal = codigo3Direcciones.nuevoTemporal();
                    codigo3Direcciones.agregarQuintuplo("load", valorFinal, temporalValorFinal);
                    valorFinal = temporalValorFinal;
                }
                condicion = codigo3Direcciones.nuevoTemporal();
                codigo3Direcciones.agregarQuintuplo("<=", id, valorFinal, condicion);
            } else if (ctx.forList().DOWNTO() != null) {
                // Ciclo descendente: id >= valorFinal
                if(tablaSimbolos.getSimbolo(id, currentScope) != null){
                    String temporalId = codigo3Direcciones.nuevoTemporal();
                    codigo3Direcciones.agregarQuintuplo("load", id, temporalId);
                    id = temporalId;
                }
                if(tablaSimbolos.getSimbolo(valorFinal, currentScope) != null){
                    String temporalValorFinal = codigo3Direcciones.nuevoTemporal();
                    codigo3Direcciones.agregarQuintuplo("load", valorFinal, temporalValorFinal);
                    valorFinal = temporalValorFinal;
                }
                condicion = codigo3Direcciones.nuevoTemporal();
                codigo3Direcciones.agregarQuintuplo(">=", id, valorFinal, condicion);
            } else {
                throw new RuntimeException("Error: Se esperaba TO o DOWNTO en el ciclo for.");
            }

            // Salto al fin del ciclo si la condición es falsa
            codigo3Direcciones.agregarQuintuplo("ifFalse", condicion, trueCiclo,finCiclo);

            codigo3Direcciones.agregarQuintuplo("label", trueCiclo, "");
            // Procesar el cuerpo del ciclo
            visit(ctx.statement());

            // Incremento o decremento del iterador
            String incremento = codigo3Direcciones.nuevoTemporal();
            if (ctx.forList().TO() != null) {
                // Incremento: id = id + 1
                codigo3Direcciones.agregarQuintuplo("+", id, "1", incremento);
            } else {
                // Decremento: id = id - 1
                codigo3Direcciones.agregarQuintuplo("-", id, "1", incremento);
            }
            codigo3Direcciones.agregarQuintuplo(":=", incremento, idRetornar);

            // Salto al inicio del ciclo
            codigo3Direcciones.agregarQuintuplo("goto", inicioCiclo, "");

            // Etiqueta de fin del ciclo
            codigo3Direcciones.agregarQuintuplo("label", finCiclo, "");
        }

        return null;
    }
    //Condicional
    @Override
    public Void visitIfStatement(MiniPascalParser.IfStatementContext ctx) {
        if(!currentScope.equalsIgnoreCase( "global")) {
            if (tablaSimbolos.getSimbolo(currentScope, currentScope) != null) {
                SimboloFuncion temporal = (SimboloFuncion) tablaSimbolos.getSimbolo(currentScope, currentScope);
                // 1. Procesar la expresión condicional
                String condicion = processExpression(ctx.expression());

                // Validar que la condición sea de tipo boolean
                /*if (!esBoolean(ctx.expression())) {
                    throw new RuntimeException("Error: La condición en el IF debe ser de tipo boolean.");
                }*/

                // 2. Crear etiquetas para manejar el flujo
                String etiquetaTrue = temporal.tacFunciones.nuevaEtiqueta();
                String etiquetaFalso = temporal.tacFunciones.nuevaEtiqueta();
                String etiquetaFin = temporal.tacFunciones.nuevaEtiqueta();

                // Generar quíntuplo para la condición
                temporal.tacFunciones.agregarQuintuplo("ifFalse", condicion, etiquetaTrue,etiquetaFalso);

                temporal.tacFunciones.agregarQuintuplo("label", etiquetaTrue, "");
                // 3. Procesar el bloque THEN
                visit(ctx.statement(0));

                // Generar salto al final del IF, si hay un bloque ELSE
                if (ctx.ELSE() != null) {
                    temporal.tacFunciones.agregarQuintuplo("goto", etiquetaFin, "");
                }

                // Etiqueta para el bloque ELSE o fin del bloque THEN
                temporal.tacFunciones.agregarQuintuplo("label", etiquetaFalso, "");

                // 4. Procesar el bloque ELSE, si existe
                if (ctx.ELSE() != null) {
                    visit(ctx.statement(1));
                }
                // Despues de procesar el else, salta al final del if
                temporal.tacFunciones.agregarQuintuplo("goto", etiquetaFin, "");
                // Etiqueta para el fin del IF
                temporal.tacFunciones.agregarQuintuplo("label", etiquetaFin, "");
            }
        }else {

            // 1. Procesar la expresión condicional
            String condicion = processExpression(ctx.expression());

            // Validar que la condición sea de tipo boolean
        /*if (!esBoolean(ctx.expression())) {
            throw new RuntimeException("Error: La condición en el IF debe ser de tipo boolean.");
        }*/

            // 2. Crear etiquetas para manejar el flujo
            String etiquetaTrue = codigo3Direcciones.nuevaEtiqueta();
            String etiquetaFalso = codigo3Direcciones.nuevaEtiqueta();
            String etiquetaFin = codigo3Direcciones.nuevaEtiqueta();

            // Generar quíntuplo para la condición
            codigo3Direcciones.agregarQuintuplo("ifFalse", condicion, etiquetaTrue,etiquetaFalso);

            codigo3Direcciones.agregarQuintuplo("label", etiquetaTrue, "");
            // 3. Procesar el bloque THEN
            visit(ctx.statement(0));

            // Generar salto al final del IF, si hay un bloque ELSE
            if (ctx.ELSE() != null) {
                codigo3Direcciones.agregarQuintuplo("goto", etiquetaFin, "");
            }

            // Etiqueta para el bloque ELSE o fin del bloque THEN
            codigo3Direcciones.agregarQuintuplo("label", etiquetaFalso, "");

            // 4. Procesar el bloque ELSE, si existe
            if (ctx.ELSE() != null) {
                visit(ctx.statement(1));
            }
            // Despues de procesar el else, salta al final del if
            codigo3Direcciones.agregarQuintuplo("goto", etiquetaFin, "");

            // Etiqueta para el fin del IF
            codigo3Direcciones.agregarQuintuplo("label", etiquetaFin, "");
        }
        return null;
    }

//    public boolean esBoolean(MiniPascalParser.ExpressionContext ctx) {
//        // Caso 1: La expresión es una constante booleana
//        if (ctx.getText().equals("true") || ctx.getText().equals("false")) {
//            return true;
//        }
//
//        // Caso 2: La expresión es una operación relacional (e.g., x > y)
//        if (ctx.relationaloperator() != null) {
//            return true; // Las operaciones relacionales siempre producen valores booleanos
//        }
//
//        // Caso 3: Verificar si es una operación lógica (OR, NOT)
//        if (ctx.simpleExpression() != null) {
//            MiniPascalParser.SimpleExpressionContext simpleExpr = ctx.simpleExpression();
//
//            if (simpleExpr.additiveoperator() != null && simpleExpr.additiveoperator().getText().equals("OR")) {
//                // Validar que ambas subexpresiones sean booleanas
//                    if (!esBoolean(simpleExpr.term().expression())) {
//                        throw new RuntimeException("Error: OR aplicado a expresiones no booleanas.");
//                    }
//                return true;
//            }
//        }
//
//        // Caso 4: Operadores lógicos con "NOT"
//        if (ctx.simpleExpression().term().signedFactor().factor() != null && ctx.simpleExpression().term().signedFactor().factor().NOT() != null) {
//            return esBoolean(ctx.simpleExpression().term().signedFactor().factor().expression());
//        }
//
//        // Caso 5: La expresión es una variable
//        if (ctx.simpleExpression().term().signedFactor().factor() != null && ctx.simpleExpression().term().signedFactor().factor().variable() != null) {
//            String nombreVariable = ctx.simpleExpression().term().signedFactor().factor().variable().getText();
//            String tipo = tablaSimbolos.getSimbolo(nombreVariable, currentScope).getType(); // Consulta en la tabla de símbolos
//
//            if (tipo == null) {
//                throw new RuntimeException("Error: La variable '" + nombreVariable + "' no está declarada.");
//            }
//            return tipo.equals("boolean");
//        }
//
//        // Caso 6: Expresión anidada entre paréntesis
//        if (ctx.simpleExpression().term().signedFactor().factor() != null && ctx.simpleExpression().term().signedFactor().factor().expression() != null) {
//            return esBoolean(ctx.simpleExpression().term().signedFactor().factor().expression());
//        }
//
//        // Caso 7: La expresión es un llamado a función
//        if (ctx.simpleExpression().term().signedFactor().factor() != null && ctx.simpleExpression().term().signedFactor().factor().functionDesignator() != null) {
//            String nombreFuncion = ctx.simpleExpression().term().signedFactor().factor().functionDesignator().identifier().getText();
//            String tipoRetorno = tablaSimbolos.getSimbolo(nombreFuncion, nombreFuncion).getType(); // Consulta el tipo de la función
//
//            if (tipoRetorno == null) {
//                throw new RuntimeException("Error: La función '" + nombreFuncion + "' no está declarada.");
//            }
//            return tipoRetorno.equals("boolean");
//        }
//
//        // Si no cumple ninguna de las condiciones anteriores, no es booleana
//        return false;
//    }



}
