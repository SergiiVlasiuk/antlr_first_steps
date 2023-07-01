grammar output;

test: FILEPATH NEWLINE TITLE ;
//test: FILEPATH;

FILEPATH: [A-Za-z][:][\\/][A-Za-z0-9]+'.'[A-Za-z0-9]+ ;
NEWLINE: '\r'? '\n' ;
TITLE: ('A'..'Z'|'a'..'z'|' ')+ ;
