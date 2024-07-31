package antlr4;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
public class Main {
    public static void main(String[] args) throws IOException {
        String inputFile = "src/inputFile.pas";
        CharStream input = CharStreams.fromPath(Paths.get(inputFile));

        MiniPascalLexer lexer = new MiniPascalLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MiniPascalParser parser = new MiniPascalParser(tokens);
        ParseTree tree = parser.program();

        // Puedes agregar un listener o visitor para análisis adicional
        System.out.println(tree.toStringTree(parser));
    }
}