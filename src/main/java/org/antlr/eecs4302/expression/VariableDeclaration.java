package org.antlr.eecs4302.expression;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class VariableDeclaration extends Expression {
    String id;
    String type;
    int value;

}
