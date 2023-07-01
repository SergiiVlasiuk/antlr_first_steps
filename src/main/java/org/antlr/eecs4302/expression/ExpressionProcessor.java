package org.antlr.eecs4302.expression;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpressionProcessor {
    List<Expression> list;
    Map<String, Integer> values; // symbol table for storing values of variables.

    public ExpressionProcessor(List<Expression> list) {
        this.list = list;
        this.values = new HashMap<>();
    }

    public List<String> getEvaluationResults() {
        List<String> evaluations = new ArrayList<>();

        for (Expression e : list) {
            if (e instanceof VariableDeclaration) {
                VariableDeclaration decl = (VariableDeclaration) e;
                values.put(decl.getId(), decl.getValue());
            } else { // e is instanceof Number, Variable, Addition, Subtraction
                String input = e.toString();
                int result = getEvalResult(e);
                evaluations.add(input + " is " + result);
            }
        }

        return evaluations;
    }

    private int getEvalResult(Expression e) {
        int result = 0;

        if (e instanceof Number) {
            Number num = (Number) e;
            result = num.getNum();
        } else if (e instanceof Variable) {
            Variable variable = (Variable) e;
            result = values.get(variable.getId());
        } else if (e instanceof Addition) {
            Addition addition = (Addition) e;
            int left = getEvalResult(addition.getLeft());
            int right = getEvalResult(addition.getRight());
            result = left + right;
        } else if (e instanceof Multiplication) {
            Multiplication multiplication = (Multiplication) e;
            int left = getEvalResult(multiplication.getLeft());
            int right = getEvalResult(multiplication.getRight());
            result = left * right;
        }
        return result;
    }
}
