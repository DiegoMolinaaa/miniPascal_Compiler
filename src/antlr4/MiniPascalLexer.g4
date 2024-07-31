lexer grammar MiniPascalLexer;

COMMENT: '{' .*? '}';
WS: [ \t\r\n]+ -> skip;

TYPE: 'integer' | 'char' | 'string' | 'boolean';
VAR: 'var';
FUNCTION: 'function';
PROGRAM: 'program';
BEGIN: 'begin';
END: 'end';
IF: 'if';
THEN: 'then';
ELSE: 'else';
FOR: 'for';
TO: 'to';
DO: 'do';
WHILE: 'while';
REPEAT: 'repeat';
UNTIL: 'until';
READ: 'read';
WRITE: 'write';
ARRAY: 'array';
OF: 'of';
DOWNTO: 'downto';
MOD: 'mod';
DIV: 'div';
NOT: 'not';
AND: 'and';
OR: 'or';

ASSIGN: ':=';
COLON: ':';
SEMICOLON: ';';
COMMA: ',';
DOT: '.';
LPAREN: '(';
RPAREN: ')';
LBRACK: '[';
RBRACK: ']';

PLUS: '+';
MINUS: '-';
STAR: '*';
SLASH: '/';

EQ: '=';
NEQ: '<>';
LT: '<';
GT: '>';
LE: '<=';
GE: '>=';

IDENTIFIER: [a-zA-Z_][a-zA-Z0-9_]*;
NUMBER: [0-9]+;
CHAR_LITERAL: '\'' . '\'';
STRING_LITERAL: '\'' .*? '\'';
