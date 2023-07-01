grammar Expr;

/* the grammar name and file name must match */
import CommonLexerRules; // includes all rules from CommonLexerRules.g4

@header {
    package org.EECS4302.action;

    import org.antlr.v4.runtime.*;
    import java.util.*;
    // model classes
    import org.antlr.eecs4302.expression.*;
    import org.antlr.eecs4302.expression.Number;
}

// attributes or methods for the generated parser class
@members {
    // for error handling
    public List<String> vars = new ArrayList();
    public List<String> semanticErrors = new ArrayList();
    // roof for AST (of type Expression)
    public Program program;
}

/** The start variable; begin parsing here. */
prog returns [Program p]
@init{ // executed before production takes effect
    $p = new Program();
    program = $p;
}
    : (
        d=decl {
            $p.addExpression($ctx.d.d);
        }
      |
        e=expr {
            $p.addExpression($ctx.e.e);
        }
      )+ EOF
    ;

decl returns [Expression d]
    : name=ID ':' type=INT_TYPE '=' val=NUM {
        int line = $name.getLine();
        int column = $name.getCharPositionInLine() + 1;
        String id = $name.text;

        if (vars.contains(id)) {
            semanticErrors.add("Error: variable " + id + " already declared (" + line + ", " + column + ")");
        } else {
            vars.add(id);
        }
        String type = $type.getText();
        int value = $val.int;
        $d = new VariableDeclaration(id, type, value);
    }
    ;

expr returns [Expression e]
    : left=expr '*' right=expr {
        $e = new Multiplication($left.e, $right.e);
    }
    | left=expr '+' right=expr {
        $e = new Addition($left.e, $right.e);
    }
    | n=NUM { // while building subtree of an expression node contains NUM at the first child, we also build Expression object.
//        $e = new Number(Integer.parseInt($n.getText()));
        $e = new Number($n.int);
    }
    | id=ID {
        int line = $id.getLine();
        int column = $id.getCharPositionInLine() + 1;
        if (!vars.contains($id.text)) {
            semanticErrors.add("Error: variable " + $id.text + " not declared (" + line + ", " + column + ")");
        }
        $e = new Variable($id.text);
    }
    ;

/* ANTLR resolves amgbiguities in favor of the given alternative first. */
MUL: '*'; // assigns token name to '*' used above in grammar
DIV: '/';
ADD: '+';
SUB: '-';