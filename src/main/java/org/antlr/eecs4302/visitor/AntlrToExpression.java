package org.antlr.eecs4302.visitor;

import org.EECS4302.visitor.ExprBaseVisitor;
import org.EECS4302.visitor.ExprParser;
import org.antlr.eecs4302.expression.*;
import org.antlr.eecs4302.expression.Number;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;
import java.util.List;

public class AntlrToExpression extends ExprBaseVisitor<Expression> {

    /*
     * Given that all visit_* methods are called in a top-down fashion,
     * we can be sure that the order in which we add declared variables in the `vars`,
     * is identical to how they declared in the input program.
     */
    private List<String> vars; // stores all variables declared in the program so far
    /*
     * 1. duplicate declaration.
     * 2. reference to undeclared variables
     * // Note that semantic errors are different from syntax errors.
     */
    private List<String> semanticErrors;

    public AntlrToExpression(List<String> semanticErrors) {
        this.semanticErrors = semanticErrors;
        vars = new ArrayList<>();
    }

    @Override
    public Expression visitDeclaration(ExprParser.DeclarationContext ctx) {
        /*
         * decl
         * |
         * i : INT = 5
         * | | |   | |
         * 0 1 2   3 4
         */
        // ID() is a method generated to correspond to the token ID in the source grammar.
        Token idToken = ctx.ID().getSymbol(); // equivalent to ctx.getChild(0).getSymbol()
        int line = idToken.getLine();
        int column = idToken.getCharPositionInLine() + 1;
        String id = ctx.getChild(0).getText();
        if (vars.contains(id)) {
            semanticErrors.add("Error: variable " + id + " already declared (" + line + ", " + column + ")");
        } else {
            vars.add(id);
        }
        String type = ctx.getChild(2).getText();
        int value = Integer.parseInt(ctx.NUM().getText());
        return new VariableDeclaration(id, type, value);
    }

    @Override
    public Expression visitMultiplication(ExprParser.MultiplicationContext ctx) {
        Expression left = visit(ctx.getChild(0)); // visit(..) - recursively visit the left subtree of the current Multiplication node
        Expression right = visit(ctx.getChild(2));
        return new Multiplication(left, right);
    }

    @Override
    public Expression visitAddition(ExprParser.AdditionContext ctx) {
        Expression left = visit(ctx.getChild(0)); // visit(..) - recursively visit the left subtree of the current Addition node
        Expression right = visit(ctx.getChild(2));
        return new Addition(left, right);
    }

    @Override
    public Expression visitVariable(ExprParser.VariableContext ctx) {
        Token idToken = ctx.ID().getSymbol();
        int line = idToken.getLine();
        int column = idToken.getCharPositionInLine() + 1;

        String id = ctx.getChild(0).getText(); // the same to ctx.ID().getSymbol()
        if (!vars.contains(id)) {
            semanticErrors.add("Error: variable " + id + " not declared (" + line + ", " + column + ")");
        }
        return new Variable(id);
    }

    @Override
    public Expression visitNumber(ExprParser.NumberContext ctx) {
        String numText = ctx.getChild(0).getText();
        int num = Integer.parseInt(numText);
        return new Number(num);
    }
}
