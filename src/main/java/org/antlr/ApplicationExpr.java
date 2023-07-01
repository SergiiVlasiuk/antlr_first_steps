package org.antlr;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.demo.ExprLexer;
import org.demo.ExprParser;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public final class ApplicationExpr {
    public static void main(String[] args) throws IOException {
        String inputFile = (args.length > 0) ? args[0] : null;

        InputStream inputStream = (inputFile == null) ? System.in : new FileInputStream(inputFile);
        ANTLRInputStream input = new ANTLRInputStream(inputStream);
        ExprLexer lexer = new ExprLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        ExprParser parser = new ExprParser(tokens);
        ParseTree tree = parser.prog(); // parse; start at prog

        System.out.println(tree.toStringTree(parser)); // print tree as text
    }
}
