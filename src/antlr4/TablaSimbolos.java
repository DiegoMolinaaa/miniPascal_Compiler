package antlr4;

import java.sql.SQLOutput;
import java.util.*;

public class TablaSimbolos {
    private ArrayList<Simbolo> simbolos = new ArrayList<>();
    // Stack para manejar scopes anidados
    private final Stack<String> scopeStack = new Stack<>();
    private String currentScope = "global";
    private String previousScope = "";

    public TablaSimbolos(){

    }
    public TablaSimbolos(ArrayList<Simbolo> simbolos){
        this.simbolos = simbolos;
    }
    // Métodos de manejo de scopes
    public void enterScope(String scope) {
        scopeStack.push(scope);
    }

    public void exitScope() {
        if (!scopeStack.isEmpty()) {
            scopeStack.pop();
        }
    }

    public String getCurrentScope() {
        return scopeStack.isEmpty() ? "global" : scopeStack.peek();
    }

    // Métodos de manejo de símbolos
    public boolean addSimbolo(Simbolo simbolo) {
        if(!findSimbolo(simbolo.getName())){
            simbolos.add(simbolo);
            return true;
        }
        return false;
    }

    public boolean findSimbolo(String name) {
        for (int i = scopeStack.size() - 1; i >= 0; i--) {
            String scope = scopeStack.get(i);
            for (Simbolo simbolo : simbolos) {
//                if (simbolo.getName().equals(name) && simbolo.getScope().equals(scope)) {
                if (simbolo.getName().equalsIgnoreCase(name) && simbolo.getScope().equalsIgnoreCase(scope)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Simbolo getSimbolo(String name, String scope) {
        for (Simbolo simbolo : simbolos) {
//            if (simbolo.getName().equals(name) && simbolo.getScope().equals(scope)) {
            if (simbolo.getName().equalsIgnoreCase(name) && simbolo.getScope().equalsIgnoreCase(scope)) {
                return simbolo;
            }
        }
        return null;
    }
    public void printTablaSimbolos() {
        System.out.println("Tabla de Simbolos:");
        for (Simbolo simbolo : simbolos) {
            System.out.println(simbolo.toString());
        }
    }

    public String generarCodigoLLVMGlobal() {
        StringBuilder sb = new StringBuilder();
        for (Simbolo simbolo : simbolos) {
            if (simbolo.getScope().equalsIgnoreCase("global")) {
                if(simbolo.getCategory().equalsIgnoreCase("variable")){
                    sb.append("@").append(simbolo.getName()).append(" = ").append("common global ");
                    if(simbolo.getType().equalsIgnoreCase("integer")){
                        sb.append("i32 0, align 4\n");
                    } else if(simbolo.getType().equalsIgnoreCase("string")){
                        sb.append("i8* null, align 8\n");
                    } else if(simbolo.getType().equalsIgnoreCase("character")){
                        sb.append("i8 0, align 1\n");
                    } else if(simbolo.getType().equalsIgnoreCase("boolean")){
                        sb.append("i1 0, align 1\n");
                    }
                }
                else if(simbolo.getCategory().equalsIgnoreCase("constant")){
                    sb.append("@").append(simbolo.getName()).append(" = ").append("constant ");
                    if(simbolo.getType().equalsIgnoreCase("integer")){
                        sb.append("i32 ").append(simbolo.getValue()).append(", align 4\n");
                    } else if(simbolo.getType().equalsIgnoreCase("string")){
                        sb.append("[").append((simbolo.getValue()).toString().length() + 1).append(" x i8] c\"").append(simbolo.getValue()).append("\\00\", align 1\n");
                    } else if(simbolo.getType().equalsIgnoreCase("character")){
                        sb.append("[2 x i8] c\"").append(simbolo.getValue()).append("\\00\", align 1\n");
                    }

                }
                else if(simbolo.getCategory().equalsIgnoreCase("array")){
                    SimboloArreglo simboloArreglo = (SimboloArreglo) simbolo;
                    int[] sizes = simboloArreglo.getSizes();
                    sb.append("@").append(simboloArreglo.getName()).append(" = ").append("global ");
                    if(simboloArreglo.getDimensions() == 1){
                        sb.append("[").append(sizes[0]).append(" x ");
                        if(simboloArreglo.getType().equalsIgnoreCase("integer")){
                            sb.append("i32 0");
                        } else if(simboloArreglo.getType().equalsIgnoreCase("character")){
                            sb.append("i8 0");
                        } else if(simboloArreglo.getType().equalsIgnoreCase("boolean")){
                            sb.append("i1 0");
                        }
                        sb.append("], align 16\n");
                    }
                    else if(simboloArreglo.getDimensions() == 2){
                        sb.append("[").append(sizes[0]).append(" x [").append(sizes[1]).append(" x ");
                        if(simboloArreglo.getType().equalsIgnoreCase("integer")){
                            sb.append("i32 0");
                        } else if(simboloArreglo.getType().equalsIgnoreCase("character")){
                            sb.append("i8 0");
                        } else if(simboloArreglo.getType().equalsIgnoreCase("boolean")){
                            sb.append("i1 0");
                        }
                        sb.append("]], align 16\n");
                    }
                }
            }
            else{
                if(simbolo.getCategory().equalsIgnoreCase("function")){

                    SimboloFuncion simboloFuncion = (SimboloFuncion) simbolo;
                    ArrayList<Simbolo> parametros = simboloFuncion.getParameters();
                    previousScope = currentScope;
                    currentScope = simboloFuncion.getName();
                    enterScope(currentScope);
                    sb.append("define ");
                    if(simboloFuncion.getType().equalsIgnoreCase("integer")){
                        sb.append("i32 ");
                    } else if(simboloFuncion.getType().equalsIgnoreCase("string")){
                        sb.append("i8* ");
                    } else if(simboloFuncion.getType().equalsIgnoreCase("character")){
                        sb.append("i8 ");
                    } else if(simboloFuncion.getType().equalsIgnoreCase("boolean")){
                        sb.append("i1 ");
                    } else if(simboloFuncion.getType().equalsIgnoreCase("procedure")){
                        sb.append("void ");
                    }
                    sb.append("@").append(simboloFuncion.getName()).append("(");
                    for (int i = 0; i < parametros.size(); i++) {
                        Simbolo parametro = parametros.get(i);
                        if(parametro.getType().equalsIgnoreCase("integer")){
                            sb.append("i32").append(" %" + parametro.getName());
                        } else if(parametro.getType().equalsIgnoreCase("string")){
                            sb.append("i8*").append(" %" + parametro.getName());
                        } else if(parametro.getType().equalsIgnoreCase("character")){
                            sb.append("i8").append(" %" + parametro.getName());
                        } else if(parametro.getType().equalsIgnoreCase("boolean")){
                            sb.append("i1").append(" %" + parametro.getName());
                        }
                        if(i < parametros.size() - 1){
                            sb.append(", ");
                        }
                    }
                    sb.append(") {\n");
                    sb.append("entry:\n");
                    for(Quintuplo q : simboloFuncion.tacFunciones.listaQuintuplos){
                        System.out.println(q.toString());
                        sb.append(convertirALLVM(q)).append("\n");
                    }
                    sb.append("}\n");
                }
                currentScope = previousScope;
                exitScope();
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public String convertirALLVM(Quintuplo q) {
        StringBuilder sb = new StringBuilder();

        SimboloFuncion simboloFuncion = (SimboloFuncion) this.getSimbolo(currentScope, currentScope);
        ArrayList<Simbolo> SimbolosFuncion = simboloFuncion.getParameters();
        TablaSimbolos tablaSimbolosFuncion = new TablaSimbolos(SimbolosFuncion);
        tablaSimbolosFuncion.enterScope(currentScope);

        String operador = q.getOperador();
        String arg1 = q.getArg1();
        String arg2 = q.getArg2();
        String resultado = q.getResultado();

        if(operador.equals(":=")){
            if(this.findSimbolo(resultado)){
                Simbolo simbolo = this.getSimbolo(resultado, currentScope);
                if(arg1.startsWith("t") && arg1.length() <= 3){
                    if(simbolo.getType().equalsIgnoreCase("integer")){
                        sb.append("  ret i32 %").append(arg1).append("\n");
                    } else if(simbolo.getType().equalsIgnoreCase("string")){
                        sb.append("  ret i8* %").append(arg1).append("\n");
                    } else if(simbolo.getType().equalsIgnoreCase("character")){
                        sb.append("  ret i8 %").append(arg1).append("\n");
                    } else if(simbolo.getType().equalsIgnoreCase("boolean")){
                        sb.append("  ret i1 %").append(arg1).append("\n");
                    }
                    else if(simbolo.getType().equalsIgnoreCase("procedure")){
                        sb.append(" ret void\n");
                    }
                }

            }
        }
        if(operador.equals("label")){
            sb.append(arg1).append(":\n");
        }
        if(operador.equals("load")){
            if(this.findSimbolo(arg1)){
                Simbolo simbolo = this.getSimbolo(arg1, currentScope);
                if(simbolo.getType().equalsIgnoreCase("integer")){
                    sb.append("  %").append(resultado).append(" = load i32, i32* @").append(arg1).append("\n");
                } else if(simbolo.getType().equalsIgnoreCase("string")){
                    sb.append("  %").append(resultado).append(" = load i8*, i8** @").append(arg1).append("\n");
                } else if(simbolo.getType().equalsIgnoreCase("character")){
                    sb.append("  %").append(resultado).append(" = load i8, i8* @").append(arg1).append("\n");
                } else if(simbolo.getType().equalsIgnoreCase("boolean")){
                    sb.append("  %").append(resultado).append(" = load i1, i1* @").append(arg1).append("\n");
                }
            }
        }
        if(operador.equals("+")){
            if((arg1.startsWith("t") && arg1.length() <= 3) && (arg2.startsWith("t") && arg2.length() <= 3)){
                sb.append("  %").append(resultado).append(" = add i32 %").append(arg1).append(", %").append(arg2).append("\n");
            }
            else if((arg1.startsWith("t") && arg1.length() <= 3) && !arg2.startsWith("t")){
                if(tablaSimbolosFuncion.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = add i32 %").append(arg1).append(", %").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = add i32 %").append(arg1).append(", ").append(arg2).append("\n");
                }
            }
            else if(!arg1.startsWith("t") && (arg2.startsWith("t") && arg2.length() <= 3)){
                if(tablaSimbolosFuncion.findSimbolo(arg1)){
                    sb.append("  %").append(resultado).append(" = add i32 %").append(arg1).append(", %").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = add i32 ").append(arg1).append(", %").append(arg2).append("\n");
                }
            }
            else{

                if(tablaSimbolosFuncion.findSimbolo(arg1) && tablaSimbolosFuncion.findSimbolo(arg2) ){
                    sb.append("  %").append(resultado).append(" = add i32 %").append(arg1).append(", %").append(arg2).append("\n");
                }
                else if(tablaSimbolosFuncion.findSimbolo(arg1) && !tablaSimbolosFuncion.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = add i32 %").append(arg1).append(", ").append(arg2).append("\n");
                }
                else if(!tablaSimbolosFuncion.findSimbolo(arg1) && tablaSimbolosFuncion.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = add i32 ").append(arg1).append(", %").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = add i32 ").append(arg1).append(", ").append(arg2).append("\n");
                }
            }

        }
        if(operador.equals("-")){
            if((arg1.startsWith("t") && arg1.length() <= 3) && (arg2.startsWith("t") && arg2.length() <= 3)){
                sb.append("  %").append(resultado).append(" = sub i32 %").append(arg1).append(", %").append(arg2).append("\n");
            }
            else if((arg1.startsWith("t") && arg1.length() <= 3) && !arg2.startsWith("t")){
                if(tablaSimbolosFuncion.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = sub i32 %").append(arg1).append(", %").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = sub i32 %").append(arg1).append(", ").append(arg2).append("\n");
                }
            }
            else if(!arg1.startsWith("t") && (arg2.startsWith("t") && arg2.length() <= 3)){
                if(tablaSimbolosFuncion.findSimbolo(arg1)){
                    sb.append("  %").append(resultado).append(" = sub i32 %").append(arg1).append(", %").append(arg2).append("\n");
                }
                else {
                    sb.append("  %").append(resultado).append(" = sub i32 ").append(arg1).append(", %").append(arg2).append("\n");
                }
            }
            else{
                if(tablaSimbolosFuncion.findSimbolo(arg1) && tablaSimbolosFuncion.findSimbolo(arg2) ){
                    sb.append("  %").append(resultado).append(" = sub i32 %").append(arg1).append(", %").append(arg2).append("\n");
                }
                else if(tablaSimbolosFuncion.findSimbolo(arg1) && !tablaSimbolosFuncion.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = sub i32 %").append(arg1).append(", ").append(arg2).append("\n");
                }
                else if(!tablaSimbolosFuncion.findSimbolo(arg1) && tablaSimbolosFuncion.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = sub i32 ").append(arg1).append(", %").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = sub i32 ").append(arg1).append(", ").append(arg2).append("\n");
                }


            }

        }
        if(operador.equals("*")){
            if((arg1.startsWith("t") && arg1.length() <= 3) && (arg2.startsWith("t") && arg2.length() <= 3)){
                sb.append("  %").append(resultado).append(" = mul i32 %").append(arg1).append(", %").append(arg2).append("\n");
            }
            else if((arg1.startsWith("t") && arg1.length() <= 3) && !arg2.startsWith("t")){
                if(tablaSimbolosFuncion.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = mul i32 %").append(arg1).append(", %").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = mul i32 %").append(arg1).append(", ").append(arg2).append("\n");
                }
            }
            else if(!arg1.startsWith("t") && (arg2.startsWith("t") && arg2.length() <= 3)){
                if(tablaSimbolosFuncion.findSimbolo(arg1)){
                    sb.append("  %").append(resultado).append(" = mul i32 %").append(arg1).append(", %").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = mul i32 ").append(arg1).append(", %").append(arg2).append("\n");
                }
            }
            else{
                if(tablaSimbolosFuncion.findSimbolo(arg1) && tablaSimbolosFuncion.findSimbolo(arg2) ){
                    sb.append("  %").append(resultado).append(" = mul i32 %").append(arg1).append(", %").append(arg2).append("\n");
                }
                else if(tablaSimbolosFuncion.findSimbolo(arg1) && !tablaSimbolosFuncion.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = mul i32 %").append(arg1).append(", ").append(arg2).append("\n");
                }
                else if(!tablaSimbolosFuncion.findSimbolo(arg1) && tablaSimbolosFuncion.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = mul i32 ").append(arg1).append(", %").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = mul i32 ").append(arg1).append(", ").append(arg2).append("\n");
                }

            }

        }
        if(operador.equals("/")){
            if((arg1.startsWith("t") && arg1.length() <= 3) && (arg2.startsWith("t") && arg2.length() <= 3)){
                sb.append("  %").append(resultado).append(" = sdiv i32 %").append(arg1).append(", %").append(arg2).append("\n");
            }
            else if((arg1.startsWith("t") && arg1.length() <= 3) && !arg2.startsWith("t")){
                if(tablaSimbolosFuncion.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = sdiv i32 %").append(arg1).append(", %").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = sdiv i32 %").append(arg1).append(", ").append(arg2).append("\n");
                }
            }
            else if(!arg1.startsWith("t") && (arg2.startsWith("t") && arg2.length() <= 3)){
                if(tablaSimbolosFuncion.findSimbolo(arg1)){
                    sb.append("  %").append(resultado).append(" = sdiv i32 %").append(arg1).append(", %").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = sdiv i32 ").append(arg1).append(", %").append(arg2).append("\n");
                }
            }
            else{
                if(tablaSimbolosFuncion.findSimbolo(arg1) && tablaSimbolosFuncion.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = sdiv i32 %").append(arg1).append(", %").append(arg2).append("\n");
                }
                else if(tablaSimbolosFuncion.findSimbolo(arg1) && !tablaSimbolosFuncion.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = sdiv i32 %").append(arg1).append(", ").append(arg2).append("\n");
                }
                else if(!tablaSimbolosFuncion.findSimbolo(arg1) && tablaSimbolosFuncion.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = sdiv i32 ").append(arg1).append(", %").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = sdiv i32 ").append(arg1).append(", ").append(arg2).append("\n");
                }

            }

        }
        if(operador.equals("mod")){
            if((arg1.startsWith("t") && arg1.length() <= 3) && (arg2.startsWith("t") && arg2.length() <= 3)){
                sb.append("  %").append(resultado).append(" = srem i32 %").append(arg1).append(", %").append(arg2).append("\n");
            }
            else if((arg1.startsWith("t") && arg1.length() <= 3) && !arg2.startsWith("t")){
                if(tablaSimbolosFuncion.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = srem i32 %").append(arg1).append(", %").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = srem i32 %").append(arg1).append(", ").append(arg2).append("\n");
                }
            }
            else if(!arg1.startsWith("t") && (arg2.startsWith("t") && arg2.length() <= 3)){
                if(tablaSimbolosFuncion.findSimbolo(arg1)){
                    sb.append("  %").append(resultado).append(" = srem i32 %").append(arg1).append(", %").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = srem i32 ").append(arg1).append(", %").append(arg2).append("\n");
                }
            }
            else{
                if(tablaSimbolosFuncion.findSimbolo(arg1) && tablaSimbolosFuncion.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = srem i32 %").append(arg1).append(", %").append(arg2).append("\n");
                }
                else if(tablaSimbolosFuncion.findSimbolo(arg1) && !tablaSimbolosFuncion.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = srem i32 %").append(arg1).append(", ").append(arg2).append("\n");
                }
                else if(!tablaSimbolosFuncion.findSimbolo(arg1) && tablaSimbolosFuncion.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = srem i32 ").append(arg1).append(", %").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = srem i32 ").append(arg1).append(", ").append(arg2).append("\n");
                }
            }

        }
        if(operador.equals(">=")){
            if((arg1.startsWith("t") && arg1.length() <= 3) && (arg2.startsWith("t") && arg2.length() <= 3)){
                sb.append("  %").append(resultado).append(" = icmp sge i32 %").append(arg1).append(", %").append(arg2).append("\n");
            }
            else if((arg1.startsWith("t") && arg1.length() <= 3) && !arg2.startsWith("t")){
                if(this.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = icmp sge i32 %").append(arg1).append(", %").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = icmp sge i32 %").append(arg1).append(", ").append(arg2).append("\n");
                }
            }
            else if(!arg1.startsWith("t") && (arg2.startsWith("t") && arg2.length() <= 3)){
                if(tablaSimbolosFuncion.findSimbolo(arg1)){
                    sb.append("  %").append(resultado).append(" = icmp sge i32 %").append(arg1).append(", %").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = icmp sge i32 ").append(arg1).append(", %").append(arg2).append("\n");
                }
            }
            else{
                if(tablaSimbolosFuncion.findSimbolo(arg1) && tablaSimbolosFuncion.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = icmp sge i32 %").append(arg1).append(", %").append(arg2).append("\n");
                }
                else if(tablaSimbolosFuncion.findSimbolo(arg1) && !tablaSimbolosFuncion.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = icmp sge i32 %").append(arg1).append(", ").append(arg2).append("\n");
                }
                else if(!tablaSimbolosFuncion.findSimbolo(arg1) && tablaSimbolosFuncion.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = icmp sge i32 ").append(arg1).append(", %").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = icmp sge i32 ").append(arg1).append(", ").append(arg2).append("\n");
                }
            }
        }
        if(operador.equals("<=")){
            if((arg1.startsWith("t") && arg1.length() <= 3) && (arg2.startsWith("t") && arg2.length() <= 3)){
                sb.append("  %").append(resultado).append(" = icmp sle i32 %").append(arg1).append(", %").append(arg2).append("\n");
            }
            else if((arg1.startsWith("t") && arg1.length() <= 3) && !arg2.startsWith("t")){
                if(tablaSimbolosFuncion.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = icmp sle i32 %").append(arg1).append(", %").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = icmp sle i32 %").append(arg1).append(", ").append(arg2).append("\n");
                }
            }
            else if(!arg1.startsWith("t") && (arg2.startsWith("t") && arg2.length() <= 3)){
                if(tablaSimbolosFuncion.findSimbolo(arg1)){
                    sb.append("  %").append(resultado).append(" = icmp sle i32 %").append(arg1).append(", %").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = icmp sle i32 ").append(arg1).append(", %").append(arg2).append("\n");
                }
            }
            else{
                if(tablaSimbolosFuncion.findSimbolo(arg1) && tablaSimbolosFuncion.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = icmp sle i32 %").append(arg1).append(", %").append(arg2).append("\n");
                }
                else if(tablaSimbolosFuncion.findSimbolo(arg1) && !tablaSimbolosFuncion.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = icmp sle i32 %").append(arg1).append(", ").append(arg2).append("\n");
                }
                else if(!tablaSimbolosFuncion.findSimbolo(arg1) && tablaSimbolosFuncion.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = icmp sle i32 ").append(arg1).append(", %").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = icmp sle i32 ").append(arg1).append(", ").append(arg2).append("\n");
                }
            }
        }
        if(operador.equals(">")){
            if((arg1.startsWith("t") && arg1.length() <= 3) && (arg2.startsWith("t") && arg2.length() <= 3)){
                sb.append("  %").append(resultado).append(" = icmp sgt i32 %").append(arg1).append(", %").append(arg2).append("\n");
            }
            else if((arg1.startsWith("t") && arg1.length() <= 3) && !arg2.startsWith("t")){
                if(tablaSimbolosFuncion.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = icmp sgt i32 %").append(arg1).append(", %").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = icmp sgt i32 %").append(arg1).append(", ").append(arg2).append("\n");
                }
            }
            else if(!arg1.startsWith("t") && (arg2.startsWith("t") && arg2.length() <= 3)){
                if(tablaSimbolosFuncion.findSimbolo(arg1)){
                    sb.append("  %").append(resultado).append(" = icmp sgt i32 %").append(arg1).append(", %").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = icmp sgt i32 ").append(arg1).append(", %").append(arg2).append("\n");
                }
            }
            else{
                if(tablaSimbolosFuncion.findSimbolo(arg1) && tablaSimbolosFuncion.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = icmp sgt i32 %").append(arg1).append(", %").append(arg2).append("\n");
                }
                else if(tablaSimbolosFuncion.findSimbolo(arg1) && !tablaSimbolosFuncion.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = icmp sgt i32 %").append(arg1).append(", ").append(arg2).append("\n");
                }
                else if(!tablaSimbolosFuncion.findSimbolo(arg1) && tablaSimbolosFuncion.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = icmp sgt i32 ").append(arg1).append(", %").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = icmp sgt i32 ").append(arg1).append(", ").append(arg2).append("\n");
                }
            }

        }
        if(operador.equals("<")){
            if((arg1.startsWith("t") && arg1.length() <= 3) && (arg2.startsWith("t") && arg2.length() <= 3)){
                sb.append("  %").append(resultado).append(" = icmp slt i32 %").append(arg1).append(", %").append(arg2).append("\n");
            }
            else if((arg1.startsWith("t") && arg1.length() <= 3) && !arg2.startsWith("t")){
                if(tablaSimbolosFuncion.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = icmp slt i32 %").append(arg1).append(", %").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = icmp slt i32 %").append(arg1).append(", ").append(arg2).append("\n");
                }
            }
            else if(!arg1.startsWith("t") && (arg2.startsWith("t") && arg2.length() <= 3)){
                if(tablaSimbolosFuncion.findSimbolo(arg1)){
                    sb.append("  %").append(resultado).append(" = icmp slt i32 %").append(arg1).append(", %").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = icmp slt i32 ").append(arg1).append(", %").append(arg2).append("\n");
                }
            }
            else{
                if(tablaSimbolosFuncion.findSimbolo(arg1) && tablaSimbolosFuncion.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = icmp slt i32 %").append(arg1).append(", %").append(arg2).append("\n");
                }
                else if(tablaSimbolosFuncion.findSimbolo(arg1) && !tablaSimbolosFuncion.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = icmp slt i32 %").append(arg1).append(", ").append(arg2).append("\n");
                }
                else if(!tablaSimbolosFuncion.findSimbolo(arg1) && tablaSimbolosFuncion.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = icmp slt i32 ").append(arg1).append(", %").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = icmp slt i32 ").append(arg1).append(", ").append(arg2).append("\n");
                }
            }

        }
        if(operador.equals("=")){
            if((arg1.startsWith("t") && arg1.length() <= 3) && (arg2.startsWith("t") && arg2.length() <= 3)){
                sb.append("  %").append(resultado).append(" = icmp eq i32 %").append(arg1).append(", %").append(arg2).append("\n");
            }
            else if((arg1.startsWith("t") && arg1.length() <= 3) && !arg2.startsWith("t")){
                if(tablaSimbolosFuncion.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = icmp eq i32 %").append(arg1).append(", %").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = icmp eq i32 %").append(arg1).append(", ").append(arg2).append("\n");
                }
            }
            else if(!arg1.startsWith("t") && (arg2.startsWith("t") && arg2.length() <= 3)){
                if(tablaSimbolosFuncion.findSimbolo(arg1)){
                    sb.append("  %").append(resultado).append(" = icmp eq i32 %").append(arg1).append(", %").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = icmp eq i32 ").append(arg1).append(", %").append(arg2).append("\n");
                }
            }
            else {
                if (tablaSimbolosFuncion.findSimbolo(arg1) && tablaSimbolosFuncion.findSimbolo(arg2)) {
                    sb.append("  %").append(resultado).append(" = icmp eq i32 %").append(arg1).append(", %").append(arg2).append("\n");
                } else if (tablaSimbolosFuncion.findSimbolo(arg1) && !tablaSimbolosFuncion.findSimbolo(arg2)) {
                    sb.append("  %").append(resultado).append(" = icmp eq i32 %").append(arg1).append(", ").append(arg2).append("\n");
                } else if (!tablaSimbolosFuncion.findSimbolo(arg1) && tablaSimbolosFuncion.findSimbolo(arg2)) {
                    sb.append("  %").append(resultado).append(" = icmp eq i32 ").append(arg1).append(", %").append(arg2).append("\n");
                } else {
                    sb.append("  %").append(resultado).append(" = icmp eq i32 ").append(arg1).append(", ").append(arg2).append("\n");
                }
            }

        }
        if(operador.equals("<>")){
            if((arg1.startsWith("t") && arg1.length() <= 3) && (arg2.startsWith("t") && arg2.length() <= 3)){
                sb.append("  %").append(resultado).append(" = icmp ne i32 %").append(arg1).append(", %").append(arg2).append("\n");
            }
            else if((arg1.startsWith("t") && arg1.length() <= 3) && !arg2.startsWith("t")){
                if(tablaSimbolosFuncion.findSimbolo(arg2)){
                    sb.append("  %").append(resultado).append(" = icmp ne i32 %").append(arg1).append(", %").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = icmp ne i32 %").append(arg1).append(", ").append(arg2).append("\n");
                }
            }
            else if(!arg1.startsWith("t") && (arg2.startsWith("t") && arg2.length() <= 3)){
                if(tablaSimbolosFuncion.findSimbolo(arg1)){
                    sb.append("  %").append(resultado).append(" = icmp ne i32 %").append(arg1).append(", %").append(arg2).append("\n");
                }
                else{
                    sb.append("  %").append(resultado).append(" = icmp ne i32 ").append(arg1).append(", %").append(arg2).append("\n");
                }
            }
            else {
                if (tablaSimbolosFuncion.findSimbolo(arg1) && tablaSimbolosFuncion.findSimbolo(arg2)) {
                    sb.append("  %").append(resultado).append(" = icmp ne i32 %").append(arg1).append(", %").append(arg2).append("\n");
                } else if (tablaSimbolosFuncion.findSimbolo(arg1) && !tablaSimbolosFuncion.findSimbolo(arg2)) {
                    sb.append("  %").append(resultado).append(" = icmp ne i32 %").append(arg1).append(", ").append(arg2).append("\n");
                } else if (!tablaSimbolosFuncion.findSimbolo(arg1) && tablaSimbolosFuncion.findSimbolo(arg2)) {
                    sb.append("  %").append(resultado).append(" = icmp ne i32 ").append(arg1).append(", %").append(arg2).append("\n");
                } else {
                    sb.append("  %").append(resultado).append(" = icmp ne i32 ").append(arg1).append(", ").append(arg2).append("\n");
                }
            }

        }
        if(operador.equals("ifFalse")){
            sb.append("  br i1 %").append(arg1).append(", label %").append(arg2).append(", label %").append(resultado).append("\n");
        }
        if(operador.equals("goto")){
            sb.append("  br label %").append(arg1).append("\n");
        }
//        if(operador.equals("call")){
//            previousScope = currentScope;
//            currentScope = arg1;
//            this.enterScope(arg1);
//            if(this.findSimbolo(arg1)){
//                SimboloFuncion simboloFuncion = (SimboloFuncion) this.getSimbolo(arg1, currentScope);
//                if(simboloFuncion.getType().equalsIgnoreCase("integer")){
//                    sb.append("  %").append(resultado).append(" = call i32 @").append(arg1).append("(");
//                } else if(simboloFuncion.getType().equalsIgnoreCase("string")){
//                    sb.append("  %").append(resultado).append(" = call i8* @").append(arg1).append("(");
//                } else if(simboloFuncion.getType().equalsIgnoreCase("character")){
//                    sb.append("  %").append(resultado).append(" = call i8 @").append(arg1).append("(");
//                } else if(simboloFuncion.getType().equalsIgnoreCase("boolean")){
//                    sb.append("  %").append(resultado).append(" = call i1 @").append(arg1).append("(");
//                } else if(simboloFuncion.getType().equalsIgnoreCase("procedure")){
//                    sb.append("  call void @").append(arg1).append("(");
//                }
//                for (int i = 0; i < simboloFuncion.parameters.size(); i++) {
//                    Simbolo parametro = simboloFuncion.parameters.get(i);
//                    String[] parametrosEnviados = arg2.split(",");
//                    if(this.findSimbolo(parametrosEnviados[i])){
//
//                        if(parametro.getType().equalsIgnoreCase("integer")){
//                            sb.append("i32 @").append(parametrosEnviados[i]);
//                        } else if(parametro.getType().equalsIgnoreCase("string")){
//                            sb.append("i8* @").append(parametrosEnviados[i]);
//                        } else if(parametro.getType().equalsIgnoreCase("character")){
//                            sb.append("i8 @").append(parametrosEnviados[i]);
//                        } else if(parametro.getType().equalsIgnoreCase("boolean")){
//                            sb.append("i1 @").append(parametrosEnviados[i]);
//                        }
//                    }
//                    else{
//                        if(parametro.getType().equalsIgnoreCase("integer")){
//                            sb.append("i32").append(" %").append(parametrosEnviados[i]);
//                        } else if(parametro.getType().equalsIgnoreCase("string")){
//                            sb.append("i8*").append(" %").append(parametrosEnviados[i]);
//                        } else if(parametro.getType().equalsIgnoreCase("character")){
//                            sb.append("i8").append(" %").append(parametrosEnviados[i]);
//                        } else if(parametro.getType().equalsIgnoreCase("boolean")){
//                            sb.append("i1").append(" %").append(parametrosEnviados[i]);
//                        }
//                    }
//
//                    if(i < simboloFuncion.parameters.size() - 1){
//                        sb.append(", ");
//                    }
//
//
//                }
//                sb.append(")\n");
//            }
//            currentScope = previousScope;
//            this.exitScope();
//
//        }
        if (operador.equals("write")) {
            // Si 'arg2' no es nulo, significa que hay algo que imprimir además de la cadena
            if (arg2 != null) {
                // Definimos la cadena temporal con % en vez de @
                String numeroArg2 = arg2.substring(1); // Eliminar '%' de 'arg2' para crear un nombre dinámico
                sb.append("  %respuesta").append(numeroArg2).append(" = alloca [")
                        .append(arg1.length() + 1)
                        .append(" x i8], align 1\n");

                sb.append("  store [")
                        .append(arg1.length() + 1)
                        .append(" x i8] c\"")
                        .append(arg1)
                        .append("\\00\", [")
                        .append(arg1.length() + 1)
                        .append(" x i8]* %respuesta").append(numeroArg2).append(", align 1\n");

                // Usamos getelementptr para obtener el puntero al primer carácter de la cadena
                sb.append("  %respuesta_ptr").append(numeroArg2).append(" = getelementptr inbounds [")
                        .append(arg1.length() + 1)
                        .append(" x i8], [")
                        .append(arg1.length() + 1)
                        .append(" x i8]* %respuesta").append(numeroArg2).append(", i32 0, i32 0\n");

                // Imprimimos la cadena 'arg1' usando el puntero obtenido
                sb.append("  call void @write_string(i8* %respuesta_ptr").append(numeroArg2).append(")\n");

                // 'resultado' contiene el tipo de la variable que se va a imprimir
                String tipo = resultado;
                if (tipo.equalsIgnoreCase("integer")) {
                    // Imprimir un entero
                    sb.append("  call void @write_int(i32 %").append(arg2).append(")\n");
                } else if (tipo.equalsIgnoreCase("character")) {
                    // Imprimir un carácter
                    sb.append("  call void @write_char(i8 %").append(arg2).append(")\n");
                } else if (tipo.equalsIgnoreCase("string")) {
                    // Imprimir una cadena
                    sb.append("  call void @write_string(i8* %").append(arg2).append(")\n");
                }
            } else {
                // Si 'arg2' es nulo, solo imprimimos la cadena 'arg1'
                String numeroArg2 = "resultado"; // Usamos "resultado" como nombre cuando 'arg2' es nulo
                sb.append("  %").append(numeroArg2).append(" = alloca [")
                        .append(arg1.length() + 1)
                        .append(" x i8], align 1\n");

                sb.append("  store [")
                        .append(arg1.length() + 1)
                        .append(" x i8] c\"")
                        .append(arg1)
                        .append("\\00\", [")
                        .append(arg1.length() + 1)
                        .append(" x i8]* %").append(numeroArg2).append(", align 1\n");

                // Usamos getelementptr para obtener el puntero al primer carácter de la cadena
                sb.append("  %").append(numeroArg2).append("_ptr = getelementptr inbounds [")
                        .append(arg1.length() + 1)
                        .append(" x i8], [")
                        .append(arg1.length() + 1)
                        .append(" x i8]* %").append(numeroArg2).append(", i32 0, i32 0\n");

                // Imprimimos la cadena 'arg1' usando el puntero obtenido
                sb.append("  call void @write_string(i8* %").append(numeroArg2).append("_ptr)\n");
            }
        }


        if(operador.equals("writeln")) {
            // Si 'arg2' no es nulo, significa que hay algo que imprimir además de la cadena
            if (arg2 != null) {
                // Definimos la cadena temporal con % en vez de @
                String numeroArg2 = arg2.substring(1); // Eliminar '%' de 'arg2' para crear un nombre dinámico
                sb.append("  %respuesta").append(numeroArg2).append(" = alloca [")
                        .append(arg1.length() + 1)
                        .append(" x i8], align 1\n");

                sb.append("  store [")
                        .append(arg1.length() + 1)
                        .append(" x i8] c\"")
                        .append(arg1)
                        .append("\\00\", [")
                        .append(arg1.length() + 1)
                        .append(" x i8]* %respuesta").append(numeroArg2).append(", align 1\n");

                // Usamos getelementptr para obtener el puntero al primer carácter de la cadena
                sb.append("  %respuesta_ptr").append(numeroArg2).append(" = getelementptr inbounds [")
                        .append(arg1.length() + 1)
                        .append(" x i8], [")
                        .append(arg1.length() + 1)
                        .append(" x i8]* %respuesta").append(numeroArg2).append(", i32 0, i32 0\n");

                // Imprimimos la cadena 'arg1' usando el puntero obtenido
                sb.append("  call void @write_string(i8* %respuesta_ptr").append(numeroArg2).append(")\n");

                // 'resultado' contiene el tipo de la variable que se va a imprimir
                String tipo = resultado;
                if (tipo.equalsIgnoreCase("integer")) {
                    // Imprimir un entero
                    sb.append("  call void @write_int(i32 %").append(arg2).append(")\n");
                } else if (tipo.equalsIgnoreCase("character")) {
                    // Imprimir un carácter
                    sb.append("  call void @write_char(i8 %").append(arg2).append(")\n");
                } else if (tipo.equalsIgnoreCase("string")) {
                    // Imprimir una cadena
                    sb.append("  call void @write_string(i8* %").append(arg2).append(")\n");
                }
            } else {
                // Si 'arg2' es nulo, solo imprimimos la cadena 'arg1'
                String numeroArg2 = "resultado"; // Usamos "resultado" como nombre cuando 'arg2' es nulo
                sb.append("  %").append(numeroArg2).append(" = alloca [")
                        .append(arg1.length() + 1)
                        .append(" x i8], align 1\n");

                sb.append("  store [")
                        .append(arg1.length() + 1)
                        .append(" x i8] c\"")
                        .append(arg1)
                        .append("\\00\", [")
                        .append(arg1.length() + 1)
                        .append(" x i8]* %").append(numeroArg2).append(", align 1\n");

                // Usamos getelementptr para obtener el puntero al primer carácter de la cadena
                sb.append("  %").append(numeroArg2).append("_ptr = getelementptr inbounds [")
                        .append(arg1.length() + 1)
                        .append(" x i8], [")
                        .append(arg1.length() + 1)
                        .append(" x i8]* %").append(numeroArg2).append(", i32 0, i32 0\n");

                // Imprimimos la cadena 'arg1' usando el puntero obtenido
                sb.append("  call void @write_string(i8* %").append(numeroArg2).append("_ptr)\n");
            }

            // Agregar salto de línea al final de la impresión
            sb.append("  %").append(resultado).append(" = call i32 (i8*, ...) @printf(i8* getelementptr inbounds ([4 x i8], [4 x i8]* @.newlineFormat, i32 0, i32 0))\n");
        }
        if (operador.equals("read")) {
            if (this.findSimbolo(resultado)) {
                Simbolo simbolo = this.getSimbolo(resultado, currentScope);
                if (simbolo.getType().equalsIgnoreCase("integer")) {
                    sb.append("  %").append(resultado).append(" = call i32 (i8*, ...) @scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.intFormat, i32 0, i32 0), i32* @").append(resultado).append(")\n");
                } else if (simbolo.getType().equalsIgnoreCase("string")) {
                    sb.append("  %").append(resultado).append(" = call i32 (i8*, ...) @scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.stringFormat, i32 0, i32 0), i8** @").append(resultado).append(")\n");
                } else if (simbolo.getType().equalsIgnoreCase("character")) {
                    sb.append("  %").append(resultado).append(" = call i32 (i8*, ...) @scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.charFormat, i32 0, i32 0), i8* @").append(resultado).append(")\n");
                } else if (simbolo.getType().equalsIgnoreCase("boolean")) {
                    sb.append("  %").append(resultado).append(" = call i32 (i8*, ...) @scanf(i8* getelementptr inbounds ([3 x i8], [3 x i8]* @.boolFormat, i32 0, i32 0), i1* @").append(resultado).append(")\n");
                }
            }
        }
        tablaSimbolosFuncion.exitScope();
        return sb.toString();
    }



}
