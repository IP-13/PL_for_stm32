grammar ITMOVA;
program :

    ;

func :
    func_def func_body SEMICOLON;

func_body :
    START operations FINISH
    ;

func_def : COLON FUNC_NAME OPEN_BRACE params_for_func_def CLOSE_BRACE
    ;

params_for_func_def :
    param_for_func_def
    |
    params_for_func_def COMMA param_for_func_def
    |

    ;

param_for_func_def :
    VAR_NAME EXCLAMATION_MARK TYPE
    ;

func_call :
    CALL FUNC_NAME OPEN_BRACE params_for_func_call CLOSE_BRACE SEMICOLON
    ;

params_for_func_call :
    param_for_func_call
    |
    params_for_func_call COMMA param_for_func_call
    |

    ;

param_for_func_call :
    VAR_NAME
    |
    expr
    ;

operations :
    operation
    |
    operations operation
    |

    ;

operation :
    var_def
    // everything SEMICOLON
    ;

var_def :
   VAR_NAME EXCLAMATION_MARK TYPE expr SEMICOLON
   ;

expr :
    bool_expr
    |
    float_expr
    |
    string_expr
    ;

bool_expr : // TODO add logical expression
    BOOL
    ;

float_expr :
    FLOAT
    |
    float_expr ARITH_SIGN FLOAT
    ;

string_expr :
    STRING
    |
    string_expr STRING
    ;


//COMMENT_START : 'COMMENT: ';
//COMMENT_FINISH : 'NO_COMMENTS';

// key words
CALL : 'CALL';
START : 'START'; // start of the function
FINISH : 'FINISH'; // finish of the function
TYPE : 'bool' | 'float' | 'string';

// key symbols
COLON : ':';
SEMICOLON : ';';
COMMA : ',';
EXCLAMATION_MARK : '!';
ARITH_SIGN : '+' | '-' | '*' | '/';
OPEN_BRACE : '(';
CLOSE_BRACE : ')';

// types start
BOOL : 'true' | 'TRUE' | 'false' | 'FALSE';


// spy
FUNC_NAME : ([a-z] | '_') ([a-z0-9] | '_' )*;
VAR_NAME : [A-Z][A-Z0-9]*;
// spy


// types finish
FLOAT : (('-')? [0-9]+) | (('-')?[0-9]+ (('.' | ',')? [0-9]*)?);
STRING : '"'[a-zA-Z0-9]+'"';

WS : [ \t\r\n]+ -> skip;
