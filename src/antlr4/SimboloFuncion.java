package antlr4;

import java.util.ArrayList;

public class SimboloFuncion extends Simbolo {
    ArrayList<Simbolo> parameters; // Parámetros de entrada

    public SimboloFuncion(String name, String type, String scope, ArrayList<Simbolo> parameters) {
        super(name, type, scope, null, "function");
        this.parameters = parameters;
        this.type = type;
    }

    @Override
    public String toString() {
        return String.format("Name: %s, ReturnType: %s, Scope: %s, Value: %s, Category: %s, Parameters: %s",
                name, type, scope, value, category, parameters);
    }
}