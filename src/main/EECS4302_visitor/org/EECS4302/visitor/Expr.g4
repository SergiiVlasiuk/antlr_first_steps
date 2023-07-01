grammar Expr;

/* the grammar name and file name must match */
import CommonLexerRules; // includes all rules from CommonLexerRules.g4

/** The start variable; begin parsing here. */
prog: (decl | expr)+ EOF        # Program
    ;

decl: ID ':' INT_TYPE '=' NUM   # Declaration
    ;
expr: expr '*' expr             # Multiplication
    | expr '+' expr             # Addition
    | NUM                       # Number
    | ID                        # Variable
    ;

/* ANTLR resolves amgbiguities in favor of the given alternative first. */
MUL: '*'; // assigns token name to '*' used above in grammar
DIV: '/';
ADD: '+';
SUB: '-';
