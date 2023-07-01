lexer grammar CommonLexerRules; // note "lexer grammar"

/* TOKENS */
// all capital means TOKEN
ID: [a-zA-Z][a-zA-Z0-9_]* ; // match identifiers
NUM: '0' | '-'?[1-9][0-9]* ; // match integers
//NEWLINE: '\r'? '\n' ; // return newlines to parser (is end-statement signal)
INT_TYPE: 'INT';
COMMENT: '--' ~[\r\n]* -> skip;
WS: [ \t\n] -> skip ; // toss out whitespace