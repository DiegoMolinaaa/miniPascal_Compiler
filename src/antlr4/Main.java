package antlr4;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {


        try {
            String inputFile = "src/inputFile.txt";
            CharStream input = CharStreams.fromPath(Paths.get(inputFile));
            MiniPascalLexer lexer = new MiniPascalLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            MiniPascalParser parser = new MiniPascalParser(tokens);

            ParseTree tree = parser.program();
            System.out.println("Compilación exitosa!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
