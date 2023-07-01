grammar Expr;

import CommonLexerRules; // includes all rules from CommonLexerRules.g4

//@header {
//    package org.demo;
//}

//ID: [a-zA-Z]+ ; // match identifiers
//INT: [0-9]+ ; // match integers
//NEWLINE: '\r'? '\n' ; // return newlines to parser (is end-statement signal)
//WS: [ \t] -> skip ; // toss out whitespace

/** The start rule; begin parsing here. */
prog: stat+;

stat: expr NEWLINE # printExpr
    | ID '=' expr NEWLINE # assign
    | 'clear' NEWLINE # clear
    | NEWLINE # blank
    ;

expr: expr op=('*'|'/') expr # MulDiv
    | expr op=('+'|'-') expr # AddSub
    | INT # int
    | ID # id
    | '(' expr ')' # parens
    ;

MUL: '*'; // assigns token name to '*' used above in grammar
DIV: '/';
ADD: '+';
SUB: '-';