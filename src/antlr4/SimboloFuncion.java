package antlr4;

import java.util.ArrayList;

public class SimboloFuncion extends Simbolo {
    ArrayList<Simbolo> parameters; // Parámetros de entrada
    String returnType;       // Tipo de retorno (null para procedimientos)

    public SimboloFuncion(String name, String returnType, String scope, ArrayList<Simbolo> parameters) {
        super(name, returnType, scope, null, "function");
        this.parameters = parameters;
        this.returnType = returnType;
    }

    @Override
    public String toString() {
        return String.format("Name: %s, ReturnType: %s, Scope: %s, Value: %s, Category: %s, Parameters: %s",
                name, returnType, scope, value, category, parameters);
    }
}