grammar MiniPascal;

options { caseInsensitive = true; }

program
   : programHeading block DOT EOF
   ;

programHeading
   : PROGRAM identifier (L_PAREN identifierList R_PAREN)? SEMICOLON
   ;

identifier
   : ID
   ;

block
   : (constantDefinitionPart | variableDeclarationPart | procedureAndFunctionDeclarationPart)* compoundStatement
   ;

constantDefinitionPart
   : CONST (constantDefinition SEMICOLON)+
   ;

constantDefinition
   : identifier EQUAL constant
   ;

constantChr
   : CHR L_PAREN unsignedInteger R_PAREN
   ;

constant
   : unsignedNumber
   | sign unsignedNumber
   | string
   | char
   | constantChr
   ;

varType
    : arrayType
    | stringR_
    | charR_
    | integerR_
    ;

arrayType
 : ARRAY L_BRACK indexRanges R_BRACK OF varType
 ;

arrayValue
: identifier L_BRACK (integer|identifier) R_BRACK
;

indexRanges
   : indexRange (COMMA indexRange)*
   ;

indexRange
   : unsignedInteger DOUBLE_DOT unsignedInteger
   ;

stringR_: STRING_;
charR_: CHAR_;
integerR_: INTEGER_;

unsignedNumber
   : unsignedInteger
   ;

unsignedInteger
   : INTEGER
   ;

sign
   : PLUS
   | MINUS
   ;

bool_
   : TRUE
   | FALSE
   ;

string
   : STRING
   ;

char
   : CHAR
   ;

boolean
    : BOOLEAN
    ;
integer
   : INTEGER
   ;

type_
   : simpleType
   | arrayType
   ;

simpleType
   : scalarType
   | subrangeType
   | typeIdentifier
   ;

scalarType
   : L_PAREN identifierList R_PAREN
   ;

subrangeType
   : constant DOUBLE_DOT constant
   ;

typeIdentifier
   : identifier
   | (CHAR_ | BOOLEAN_ | INTEGER_ | STRING_)
   ;

variableDeclarationPart
   : VAR variableDeclaration (SEMICOLON variableDeclaration)* SEMICOLON
   ;

variableDeclaration
   : identifierList COLON type_
   ;

procedureAndFunctionDeclarationPart
   : procedureOrFunctionDeclaration SEMICOLON
   ;

procedureOrFunctionDeclaration
   : functionDeclaration
   | procedureDeclaration
   ;

formalParameterList
   : L_PAREN formalParameterSection (SEMICOLON formalParameterSection)* R_PAREN
   ;

formalParameterSection
   : parameterGroup
   | VAR parameterGroup
   ;

parameterGroup
   : identifierList COLON varType
   | emptyStatement_
   ;

identifierList
   : identifier (COMMA identifier)*
   ;
functionDeclaration
   : FUNCTION identifier formalParameterList COLON varType SEMICOLON block
   ;

procedureDeclaration
    : PROCEDURE identifier formalParameterList SEMICOLON block
    ;

statement
   : writeStatement
   | readStatement
   | unlabelledStatement
   ;

writeStatement
   : write L_PAREN writeParam2 (COMMA writeParam)? R_PAREN
   ;

write: WRITE | WRITELN;

writeParam2
   : string
   ;

writeParam
   : varValue
   | identifier
   | arrayValue
   ;

varValue
   : string
   | char
   | integer
   | boolean
   ;

readStatement: read L_PAREN readParam R_PAREN;

read: READ | READLN;

readParam
   : identifier
   ;

unlabelledStatement
   : simpleStatement
   | structuredStatement
   ;

simpleStatement
   : assignmentStatement
   | emptyStatement_
   ;

assignmentStatement
   : variable ASSIGN expression
   ;

variable
   : identifier (L_BRACK expressionList R_BRACK | DOT identifier)*
   ;

expressionList
   : expression (COMMA expression)*
   ;

expression
   : simpleExpression (relationaloperator expression)?
   ;

relationaloperator
   : EQUAL
   | NOT_EQUAL
   | LT
   | LE
   | GE
   | GT
   ;

simpleExpression
   : term (additiveoperator simpleExpression)?
   ;

additiveoperator
   : PLUS
   | MINUS
   | OR
   ;

term
   : signedFactor (multiplicativeoperator term)?
   ;

multiplicativeoperator
   : MULT
   | DIV
   | MOD
   ;

signedFactor
   : (PLUS | MINUS)? factor
   ;

factor
   : variable
   | L_PAREN expression R_PAREN
   | functionDesignator
   | unsignedConstant
   | NOT factor
   | bool_
   ;

unsignedConstant
   : unsignedNumber
   | constantChr
   | string
   | NIL
   ;

functionDesignator
   : identifier L_PAREN parameterList R_PAREN
   ;

parameterList
   : actualParameter (COMMA actualParameter)*
   ;

actualParameter
   : expression
   ;

emptyStatement_
   :
   ;

structuredStatement
   : compoundStatement
   | conditionalStatement
   | repetitiveStatement
   ;

compoundStatement
   : BEGIN statements END
   ;

statements
   : statement (SEMICOLON statement)*
   ;

conditionalStatement
   : ifStatement
   ;

ifStatement
   : IF expression THEN statement (ELSE statement)?
   ;

repetitiveStatement
   : whileStatement
   | repeatStatement
   | forStatement
   ;

whileStatement
   : WHILE expression DO statement
   ;

repeatStatement
   : REPEAT statements UNTIL expression
   ;

forStatement
   : FOR identifier ASSIGN forList DO statement
   ;

forList
   : initialValue (TO | DOWNTO) finalValue
   ;

initialValue
   : expression
   ;

finalValue
   : expression
   ;

// Tokens and Lexer Rules

BEGIN: 'begin';
END: 'end';

WS: [ \t\n\r] -> skip;
COMMENT: '{' .*? '}' -> skip;

INTEGER: [0-9]+;
BOOLEAN: 'true' | 'false';
CHAR: '\'' ('\'\'' | ~('\''))? '\'';
STRING: '\'' ('\'\'' | ~('\''))* '\'';

ASSIGN: ':=';
PLUS: '+';
MINUS: '-';
DIV: 'div' | '/';
MULT: '*';
MOD: 'mod';
AND: 'and';
NOT: 'not';
OR: 'or';

GT: '>';
LT: '<';
LE: '<=';
GE: '>=';
EQUAL: '=';
NOT_EQUAL: '<>';

L_PAREN: '(';
R_PAREN: ')';
L_BRACK: '[';
R_BRACK: ']';
COMMA: ',';
SEMICOLON: ';';
COLON: ':';
DOT: '.';
DOUBLE_DOT: '..';

PROGRAM: 'program';
FUNCTION: 'function';
PROCEDURE: 'procedure';

IF: 'if';
THEN: 'then';
ELSE: 'else';
UNTIL: 'until';
WHILE: 'while';
FOR: 'for';
REPEAT: 'repeat';
TO: 'to';
DO: 'do';
DOWNTO: 'downto';
VAR: 'var';

ARRAY: 'Array';
OF: 'of';

INTEGER_: 'INTEGER';
BOOLEAN_: 'BOOLEAN';
CHAR_: 'CHAR';
STRING_: 'STRING';

NIL: 'NIL';
CONST: 'CONST';
CHR: 'CHR';
AT: 'AT';

READLN: 'READLN';
READ: 'READ';
WRITELN: 'WRITELN';
WRITE: 'WRITE';

ID: [a-zA-Z][a-zA-Z0-9_]*;

