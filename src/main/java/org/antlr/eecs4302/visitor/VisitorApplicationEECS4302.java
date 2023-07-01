package org.antlr.eecs4302.visitor;

import lombok.extern.slf4j.Slf4j;
import org.EECS4302.visitor.ExprLexer;
import org.EECS4302.visitor.ExprParser;
import org.antlr.eecs4302.expression.ExprErrorListener;
import org.antlr.eecs4302.expression.ExpressionProcessor;
import org.antlr.eecs4302.expression.Program;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

@Slf4j
public class VisitorApplicationEECS4302 {

    public static void main(String[] args) {
        if (args.length != 1) {
            log.error("Usage: file name");
        } else {
            String fileName = args[0];
            ExprParser parser = getParser(fileName);

            // tell ANTLR to build a parse tree. Parse from start symbol 'prog'
            ParseTree antlrAST = parser.prog();

            if (ExprErrorListener.hasError) {
                /*
                 * let syntax error to be reported
                 */
            } else {
                // create visitor for converting the parse tree into Program/Expression object
                AntlrToProgram progVisitor = new AntlrToProgram();
                Program prog = progVisitor.visit(antlrAST);

                if (progVisitor.semanticErrors.isEmpty()) {
                    ExpressionProcessor ep = new ExpressionProcessor(prog.getExpressions());
                    for (String evaluation : ep.getEvaluationResults()) {
                        log.info(evaluation);
                    }
                } else {
                    for (String err : progVisitor.getSemanticErrors()) {
                        log.error("SemanticError: {}", err);
                    }
                }
            }
        }

    }

    /*
     * Here are types of parser and lexer are specific to the grammar name Expr.g4.
     */
    public static ExprParser getParser(String fileName) {
        ExprParser parser = null;

        try {
            CharStream input = CharStreams.fromFileName(fileName);
            ExprLexer lexer = new ExprLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            parser = new ExprParser(tokens);

            //syntax error handling
            parser.removeErrorListeners();
            parser.addErrorListener(new ExprErrorListener());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return parser;
    }
}
