package org.antlr.eecs4302.expression;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Addition extends Expression {
    Expression left;
    Expression right;

    @Override
    public String toString() {
        return left.toString() + " + " + right.toString();
    }

}
