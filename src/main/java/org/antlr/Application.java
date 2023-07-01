package org.antlr;

import org.antlr.calc.CalcLexer;
import org.antlr.calc.CalcParser;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class Application {
    public static void main(String[] args) throws IOException {
        String inputFile = (args.length > 0) ? args[0] : null;

        InputStream inputStream = (inputFile == null) ? System.in : new FileInputStream(inputFile);
        ANTLRInputStream input = new ANTLRInputStream(inputStream);
        CalcLexer lexer = new CalcLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CalcParser parser = new CalcParser(tokens);
        ParseTree tree = parser.program(); // parse; start at prog

        System.out.println(tree.toStringTree(parser)); // print tree as text
    }
}
