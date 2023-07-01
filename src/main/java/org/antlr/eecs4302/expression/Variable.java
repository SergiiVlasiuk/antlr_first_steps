package org.antlr.eecs4302.expression;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Variable extends Expression {

    public String id;

}
