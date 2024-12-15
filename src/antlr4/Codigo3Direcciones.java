package antlr4;

import java.util.ArrayList;
public class Codigo3Direcciones {

    ArrayList<Quintuplo> listaQuintuplos;
    private int contadorTemporales = 0;
    private int contadorQuintuplos = 0;
    private int contadorEtiquetas = 0;

    public Codigo3Direcciones() {
        listaQuintuplos = new ArrayList<Quintuplo>();
    }

    public String nuevoTemporal() {
        return "t" + (contadorTemporales++);
    }

    public void agregarQuintuplo(String operador, String arg1, String arg2, String resultado) {
        listaQuintuplos.add(new Quintuplo(contadorQuintuplos++, operador, arg1, arg2, resultado));
    }

    public void agregarQuintuplo(String operador, String arg1, String resultado) {
        listaQuintuplos.add(new Quintuplo(contadorQuintuplos++, operador, arg1, "", resultado));
    }

    public Quintuplo getQuintuplo(int index) {
        return listaQuintuplos.get(index);
    }

    public void imprimirQuintuplos() {
        for (Quintuplo q : listaQuintuplos) {
            System.out.println(q);
        }
    }

    public int getContadorTemporales() {
        return contadorTemporales;
    }

    public int getContadorQuintuplos() {
        return contadorQuintuplos;
    }

    public String nuevaEtiqueta() {
        return "L" + (contadorEtiquetas++);
    }

    public String convertirALLVM(Quintuplo q) {
        StringBuilder sb = new StringBuilder();
        String operador = q.getOperador();
        String arg1 = q.getArg1();
        String arg2 = q.getArg2();
        String resultado = q.getResultado();

        if(operador.equals(":=")){


        }
        return sb.toString();
    }

}
