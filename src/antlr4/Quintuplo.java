package antlr4;

public class Quintuplo {
    int id;
    String operador;
    String arg1;
    String arg2;
    String resultado;

    public Quintuplo(int id, String operador, String arg1, String arg2, String resultado) {
        this.id = id;
        this.operador = operador;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.resultado = resultado;
    }
    public Quintuplo(int id, String operador, String arg1, String arg2) {
        this.id = id;
        this.operador = operador;
        this.arg1 = arg1;
        this.arg2 = arg2;
        this.resultado = "";
    }
    public Quintuplo(int id, String operador, String arg1) {
        this.id = id;
        this.operador = operador;
        this.arg1 = arg1;
        this.arg2 = "";
        this.resultado = "";
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getOperador() {
        return operador;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public String getArg1() {
        return arg1;
    }

    public void setArg1(String arg1) {
        this.arg1 = arg1;
    }

    public String getArg2() {
        return arg2;
    }

    public void setArg2(String arg2) {
        this.arg2 = arg2;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }



    @Override
    public String toString() {
        return "(" + id + ", " + operador + ", " + arg1 + ", " + arg2 + ", " + resultado + ")";
    }
}
