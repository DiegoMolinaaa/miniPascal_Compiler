package antlr4;

public class ErrorCompilacion {
    int fila;
    int columna;
//    String instruccion;
    String descripcion;
    ErrorTipo tipo;

    public ErrorCompilacion(int fila, int columna, String descripcion, ErrorTipo tipo) {
        this.fila = fila;
        this.columna = columna;
//        this.instruccion = instruccion;
        this.descripcion = descripcion;
        this.tipo = tipo;
    }

    public enum ErrorTipo {
        Léxico,
        Sintáctico,
        Semántico
    }
    @Override
    public String toString() {
        return
                "Error " + tipo + ":\n" +
                "Fila " + fila +
                ", columna " + columna +
//                "\nInstrucción: '" + instruccion + '\'' +
                "\nDescripción: '" + descripcion + '\'' + '\n' ;
    }
}
