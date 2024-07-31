parser grammar MiniPascalParser;

options { tokenVocab=MiniPascalLexer; }

program: PROGRAM IDENTIFIER SEMICOLON block DOT;

block: declarations BEGIN statementSequence END;

declarations: (VAR varDeclaration+)?;

varDeclaration: IDENTIFIER COLON TYPE SEMICOLON;

statementSequence: statement (SEMICOLON statement)*;

statement
    : assignment
    | ifStatement
    | whileStatement
    | forStatement
    | readStatement
    | writeStatement
    | block
    ;

assignment: IDENTIFIER ASSIGN expression;

ifStatement: IF expression THEN statement (ELSE statement)?;

whileStatement: WHILE expression DO statement;

forStatement: FOR IDENTIFIER ASSIGN expression TO expression DO statement;

readStatement: READ LPAREN IDENTIFIER RPAREN;

writeStatement: WRITE LPAREN STRING_LITERAL (COMMA expression)? RPAREN;

expression
    : simpleExpression ((EQ | NEQ | LT | GT | LE | GE) simpleExpression)?
    ;

simpleExpression
    : term (PLUS | MINUS term)*
    ;

term: factor (STAR | SLASH | DIV | MOD factor)*;

factor
    : IDENTIFIER
    | NUMBER
    | CHAR_LITERAL
    | STRING_LITERAL
    | LPAREN expression RPAREN
    | NOT factor
    ;

