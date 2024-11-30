package antlr4;

import java.util.Arrays;

public class SimboloArreglo extends Simbolo {
    int dimensions; // Número de dimensiones (1 para unidimensional, 2 para bidimensional)
    int[] sizes;    // Tamaño de cada dimensión (ejemplo: [10, 20] para un arreglo 10x20)

    public SimboloArreglo(String name, String type, String scope, int dimensions, int[] sizes) {
        super(name, type, scope, null, "array");
        this.dimensions = dimensions;
        this.sizes = sizes;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(", Dimensions: %d, Sizes: %s",
                dimensions, Arrays.toString(sizes));
    }
}