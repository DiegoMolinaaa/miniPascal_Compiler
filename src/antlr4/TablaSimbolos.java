package antlr4;

import java.util.*;

public class TablaSimbolos {
    private final ArrayList<Simbolo> simbolos = new ArrayList<>();

    // Stack para manejar scopes anidados
    private final Stack<String> scopeStack = new Stack<>();

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
                        if(q.getOperador().equals(":=")) {
                            if (q.getResultado().equals(simboloFuncion.getName())) {
                                q.setResultado("result");
                            }
                        }
                        sb.append("  ").append(q.toString()).append("\n");
                    }
                    if(simbolo.getType().equalsIgnoreCase("integer")){
                        sb.append("  ret i32 ").append("%result").append("\n");
                    } else if(simbolo.getType().equalsIgnoreCase("string")){
                        sb.append("  ret i8* ").append("%result").append("\n");
                    } else if(simbolo.getType().equalsIgnoreCase("character")){
                        sb.append("  ret i8 ").append("%result").append("\n");
                    } else if(simbolo.getType().equalsIgnoreCase("boolean")){
                        sb.append("  ret i1 ").append("%result").append("\n");
                    } else if(simbolo.getType().equalsIgnoreCase("procedure")){
                        sb.append("  ret void\n");
                    }
                    sb.append("}\n");
                }

            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
