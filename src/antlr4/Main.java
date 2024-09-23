package antlr4;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.antlr.v4.runtime.BaseErrorListener;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.IOException;

public class Main {
    public static String traducirMensaje(String msg) {
        String msgTraducido = msg.replace("no viable alternative at input", "ninguna alternativa viable en la entrada");
        msgTraducido = msgTraducido.replace("token recognition error at:","error de reconocimiento de token en:");
        msgTraducido = msgTraducido.replace("extraneous input","entrada ajena");
        msgTraducido = msgTraducido.replace("expecting","se esperaba");
        msgTraducido = msgTraducido.replace("missing","falta");
        msgTraducido = msgTraducido.replace("mismatched input","entrada incorrecta");
        msgTraducido = msgTraducido.replace("at","en");
        msgTraducido = msgTraducido.replace("alterneniva","alternativa");
        return msgTraducido;
    }
    public static void main(String[] args) throws IOException {
        String nombreArchivo = "";
        File archivo = null;

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
                    MiniPascalASTVisitorPersonal visitor = new MiniPascalASTVisitorPersonal();
                    visitor.visit(tree);
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



    }
}
