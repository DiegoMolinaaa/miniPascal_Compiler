package antlr4;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import org.antlr.v4.runtime.BaseErrorListener;

public class Main {
    public static String traducirMensaje(String msg) {
        String msgTraducido = msg.replace("no viable alternative at input", "ninguna alternativa viable en la entrada");
        msgTraducido = msgTraducido.replace("token recognition error at:","error de reconocimiento de token en:");
        msgTraducido = msgTraducido.replace("extraneous input","entrada ajena");
        msgTraducido = msgTraducido.replace("expecting","se esperaba");
        msgTraducido = msgTraducido.replace("missing","falta");
        msgTraducido = msgTraducido.replace("mismatched input","entrada incorrecta");
        msgTraducido = msgTraducido.replace("at","en");

        return msgTraducido;
    }
    public static void main(String[] args) {
        try {
            String inputFile = "src/errorInputFile2.txt";
            CharStream input = CharStreams.fromPath(Paths.get(inputFile));
            MiniPascalLexer lexer = new MiniPascalLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            MiniPascalParser parser = new MiniPascalParser(tokens);
            ArrayList<ErrorCompilacion> erroresEncontrados = new ArrayList<>();

//            Personalizar listener de errores Lexicos
            lexer.removeErrorListeners();
            lexer.addErrorListener(new BaseErrorListener()
               {
                   @Override
                   public void syntaxError(Recognizer<?, ?> recognizer, Object simboloIncorrecto, int linea, int posicionEnLinea, String msg, RecognitionException e)
                   {
                       erroresEncontrados.add(new ErrorCompilacion(linea, posicionEnLinea, "Lex", traducirMensaje(msg), ErrorCompilacion.ErrorTipo.Léxico));
                   }
               }
            );

            //            Personalizar listener de errores Sintacticos
            parser.removeErrorListeners();
            parser.addErrorListener(new BaseErrorListener()
                {
                    @Override
                    public void syntaxError(Recognizer<?, ?> recognizer, Object simboloIncorrecto, int linea, int posicionEnLinea, String msg, RecognitionException e)
                    {
                        erroresEncontrados.add(new ErrorCompilacion(linea, posicionEnLinea, "Sin", traducirMensaje(msg), ErrorCompilacion.ErrorTipo.Sintáctico));
                    }
                }
            );

            ParseTree tree = parser.program();



//            Imprimir resultado de la compilacion
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
    }
}
