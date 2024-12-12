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

}
