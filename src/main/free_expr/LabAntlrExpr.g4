//parser grammar Expr;
grammar LabAntlrExpr;

import LabAntlrExprLexer

//program: (stat)+ EOF;
program: stat EOF
    | def EOF
    ;
//program: (stat | def)+ EOF;

stat: ID '=' expr ';'
    | expr ';'
    ;

def : ID '(' ID (',' ID)* ')' '{' stat* '}' ;

expr: ID
    | INT
    | func
    | 'not' expr
    | expr 'and' expr
    | expr 'or' expr
    | expr ADD expr
    ;

func : ID '(' expr (',' expr)* ')' ;
