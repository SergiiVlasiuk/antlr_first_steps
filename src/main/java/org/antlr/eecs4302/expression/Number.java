package org.antlr.eecs4302.expression;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Number extends Expression {
    int num;

    @Override
    public String toString() {
        return Integer.toString(num);
    }
}
