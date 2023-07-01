package org.antlr.eecs4302.visitor;

import lombok.Getter;
import org.EECS4302.visitor.ExprBaseVisitor;
import org.EECS4302.visitor.ExprParser;
import org.antlr.eecs4302.expression.Program;

import java.util.ArrayList;
import java.util.List;

@Getter
public class AntlrToProgram extends ExprBaseVisitor<Program> {

    public List<String> semanticErrors; // to be accessed in the main application program.

    @Override
    public Program visitProgram(ExprParser.ProgramContext ctx) {
        Program prog = new Program();

        semanticErrors = new ArrayList<>();
        // a helper visitor for transforming each subtree into Expression object.
        AntlrToExpression expressionVisitor = new AntlrToExpression(semanticErrors);
        for (int i = 0; i < ctx.getChildCount(); i++) {
            if (i == ctx.getChildCount() - 1) {
                /* last child of the start symbol is EOF */
                // do not visit this child and attempt to convert it to an Expression object
            } else {
                prog.addExpression(expressionVisitor.visit(ctx.getChild(i)));
            }
        }

        return prog;
    }
}
