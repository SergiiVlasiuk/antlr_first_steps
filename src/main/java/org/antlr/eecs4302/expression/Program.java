package org.antlr.eecs4302.expression;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Program {
    private List<Expression> expressions;

    public Program() {
        expressions = new ArrayList<>();
    }

    public void addExpression(Expression exp) {
        expressions.add(exp);
    }
}
