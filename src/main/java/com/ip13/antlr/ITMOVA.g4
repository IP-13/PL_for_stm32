grammar ITMOVA;

program :
    statements
    ;

from_cycle :
    FROM lower_border TO upper_border WITH step COLON START statements FINISH
    ;

lower_border :
    INT
    |
    VAR_NAME
    ;

upper_border :
    INT
    |
    VAR_NAME
    ;

step :
    INT
    |
    VAR_NAME
    ;

if_else_operator :
    if_operator (else_if_operator)* (else_operator)?
    ;

if_operator :
    IF bool_expr COLON START statements FINISH
    ;

else_if_operator :
    ELSE IF bool_expr COLON START statements FINISH
    ;

else_operator :
    ELSE COLON START statements FINISH
    ;

bool_expr :
    func_call
    ;

func_def :
    FUNC_NAME OPEN_BRACE func_params CLOSE_BRACE COLON (TYPE | VOID) START statements FINISH
    ;

func_params :
    func_param {System.out.println($1);}
    |
    func_params COMMA func_param
    |
    // empty
    ;

func_param :
    VAR_NAME COLON TYPE
    ;

statements :
    statement
    |
    statements statement
    |
    // empty
    ;


statement : // everything, that end with SEMICOLON or FINISH
    func_call SEMICOLON
    |
    var_def SEMICOLON
    |
    from_cycle
    |
    if_else_operator
    |
    func_def
    |
    RETURN VAR_NAME SEMICOLON
    ;

func_call :
    FUNC_NAME OPEN_BRACE func_args CLOSE_BRACE
    ;

func_args :
    func_arg
    |
    func_args COMMA func_arg
    |
    // empty
    ;

func_arg :
    VAR_NAME
    |
    literal
    |
    func_call
    ;


var_def :
   VAR_NAME COLON TYPE
   ;

//COMMENT_START : 'COMMENT: ';
//COMMENT_FINISH : 'NO_COMMENTS';


literal :
    BOOL
    |
    INT
    |
    FLOAT
    |
    STRING
    ;


// lexems

// key words
START : 'START'; // start of the function, FROM-cycle, IF-conditiion
FINISH : 'FINISH'; // finish of the function, FROM-cycle, IF-condition
RETURN : 'RETURN';
FROM : 'FROM';
TO : 'TO';
WITH : 'WITH';
IF : 'IF';
ELSE : 'ELSE';
TYPE : 'bool' | 'int' | 'float' | 'string';


// special symbols
COLON : ':';
SEMICOLON : ';';
COMMA : ',';
EXCLAMATION_MARK : '!';
OPEN_BRACE : '(';
CLOSE_BRACE : ')';
DOUBLE_QUOTE : '"';


// types
BOOL : 'true' | 'TRUE' | 'false' | 'FALSE';
INT : (('-')? [0-9]+);
FLOAT : (('-')? [0-9]+) | (('-')?[0-9]+ ('.' | ',') [0-9]+);
STRING : '"'(.)*'"';
VOID : 'void';

FUNC_NAME : [a-z] ([a-z0-9] | '_' )*;
VAR_NAME : [A-Z]([A-Z0-9] | '_')*;

WS : [ \t\r\n]+ -> skip;
LINE_COMMENT : '//' .*? '\r'? '\n' -> skip ;
BLOCK_COMMENT      : '/*' .*? '*/' -> skip ;