package antlr4;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        String nombreArchivo = "";
        File archivo = null;
        TablaSimbolos tablaSimbolos = new TablaSimbolos();
        // Seleccionar el programa cno filechooser
        try {
            JFileChooser fileChooser = new JFileChooser("./src/");
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivo de Texto", "txt");
            fileChooser.setFileFilter(filtro);
            int seleccion = fileChooser.showOpenDialog(null);
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                archivo = fileChooser.getSelectedFile();
                nombreArchivo = archivo.getAbsolutePath();
            }

            // Proceso de compilacion
            try {
                CharStream input = CharStreams.fromPath(Paths.get(nombreArchivo));
                MiniPascalLexer lexer = new MiniPascalLexer(input);
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                MiniPascalParser parser = new MiniPascalParser(tokens);
                ArrayList<ErrorCompilacion> erroresEncontrados = new ArrayList<>();

                // Personalizar listener de errores Lexicos
                lexer.removeErrorListeners();
                lexer.addErrorListener(new BaseErrorListener()
                   {
                       @Override
                       public void syntaxError(Recognizer<?, ?> recognizer, Object simboloIncorrecto, int linea, int posicionEnLinea, String msg, RecognitionException e)
                       {
                           erroresEncontrados.add(new ErrorCompilacion(linea, posicionEnLinea, traducirMensaje(msg), ErrorCompilacion.ErrorTipo.Léxico));
                       }
                   }
                );

                // Personalizar listener de errores Sintacticos
                parser.removeErrorListeners();
                parser.addErrorListener(new BaseErrorListener()
                    {
                        @Override
                        public void syntaxError(Recognizer<?, ?> recognizer, Object simboloIncorrecto, int linea, int posicionEnLinea, String msg, RecognitionException e)
                        {
                            erroresEncontrados.add(new ErrorCompilacion(linea, posicionEnLinea, traducirMensaje(msg), ErrorCompilacion.ErrorTipo.Sintáctico));
                        }
                    }
                );

                // Arbolito
                ParseTree tree = parser.program();

                // Imprimir resultado de la compilacion
                if (erroresEncontrados.isEmpty()) {
                    //Analisis Lexico y Sintactico
                    MiniPascalASTVisitorPersonal visitor1 = new MiniPascalASTVisitorPersonal();
                    visitor1.visit(tree);


                    //Tabla de Simbolos
                    MiniPascalASTVisitorTablaSimbolos visitorTablaSimbolos = new MiniPascalASTVisitorTablaSimbolos(tablaSimbolos);
                    visitorTablaSimbolos.visit(tree);
                    tablaSimbolos.printTablaSimbolos();

                    //Analisis Semantico -- Pamela


                    //Generacion de Codigo Intermedio (3 Direcciones)
                    System.out.println("\nCompilación exitosa!");
                }
                else {
                    System.out.println("\nErrores encontrados (" + erroresEncontrados.size() + "):\n");
                    for (ErrorCompilacion error : erroresEncontrados) {
                        System.err.println(error);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



        Frame ventana = new Frame();
        ventana.setVisible(true);
    }
}
