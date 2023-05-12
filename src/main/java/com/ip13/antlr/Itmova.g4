grammar Itmova;

@header {
    import com.ip13.compiler.SuperClass;
    import com.ip13.compiler.Type;
}

program :
    statements
    ;

statements :
    statement
    |
    statements statement
    |
    // empty
    ;

statement : // everything, that end with SEMICOLON or FINISH and entry point
    func_call SEMICOLON
    |
    var_def
    |
    from_cycle
    |
    if_operator
    |
    func_def
    |
    entry_point
    |
    return_value
    ;

entry_point :
    ENTRY_POINT {SuperClass.entryPoint($ENTRY_POINT.line);}
    ;

from_cycle :
    FROM lower_border TO upper_border WITH step COLON START statements FINISH
    ;

step :
    INT {SuperClass.step($INT.text);}
    ;

upper_border :
    INT {SuperClass.upperBorder($INT.text);}
    ;

lower_border :
    INT {SuperClass.lowerBorder($INT.text);}
    ;

if_operator :
    IF bool_expr COLON START statements FINISH {SuperClass.ifOperator();}
    ;

bool_expr :
    func_call {SuperClass.boolExpr();}
    ;

func_def :
    FUNC_NAME OPEN_BRACE func_params CLOSE_BRACE COLON return_type START statements FINISH {SuperClass.funcDef($FUNC_NAME.text, $return_type.text);}
    ;

return_type :
    TYPE
    |
    VOID
    ;

return_value :
    RETURN literal SEMICOLON {SuperClass.returnValueLiteral();}
    |
    RETURN VAR_NAME SEMICOLON {SuperClass.returnValueVariable($VAR_NAME.text);}
    |
    RETURN func_call SEMICOLON {SuperClass.returnValueFuncCall();}
    ;

func_params :
    func_param
    |
    func_params COMMA func_param
    |
    {SuperClass.funcParams();} // func has none params
    ;

func_param :
    VAR_NAME COLON TYPE {SuperClass.funcParam($VAR_NAME.text, $TYPE.text);}
    ;

func_call :
    FUNC_NAME OPEN_BRACE func_args CLOSE_BRACE {SuperClass.funcCall($FUNC_NAME.text);}
    ;

func_args :
    func_arg
    |
    func_args COMMA func_arg
    |
    // empty
    ;

func_arg :
    literal {SuperClass.funcArgLiteral();}
    |
    VAR_NAME {SuperClass.funcArgVariable($VAR_NAME.text);}
    |
    func_call {SuperClass.funcArgFuncCall();}
    ;

var_def :
   VAR_NAME COLON TYPE SEMICOLON {SuperClass.varDef($VAR_NAME.text, $TYPE.text);}
   ;

literal :
    BOOL {SuperClass.literal($BOOL.text, Type.BLN, $BOOL.line);}
    |
    INT {SuperClass.literal($INT.text, Type.INT, $INT.line);}
    |
    FLOAT {SuperClass.literal($FLOAT.text, Type.FLT, $FLOAT.line);}
    |
    STRING {SuperClass.literal($STRING.text, Type.STR, $STRING.line);}
    ;


// key words
START : 'START'; // start of the function, FROM-cycle, IF-conditiion
FINISH : 'FINISH'; // finish of the function, FROM-cycle, IF-condition
RETURN : 'RETURN';
FROM : 'FROM';
TO : 'TO';
WITH : 'WITH';
IF : 'IF';
TYPE : 'bool' | 'int' | 'float' | 'string' | 'pointer';
ENTRY_POINT : 'MAIN';

// special symbols
COLON : ':';
SEMICOLON : ';';
COMMA : ',';
EXCLAMATION_MARK : '!';
OPEN_BRACE : '(';
CLOSE_BRACE : ')';
DOUBLE_QUOTE : '"';


// types
BOOL : 'TRUE' | 'FALSE';
INT : (('-')? [0-9]+);
FLOAT : (('-')? [0-9]+) | (('-')?[0-9]+ ('.' | ',') [0-9]+);
STRING : '"'(.)*'"';
VOID : 'void';

FUNC_NAME : [a-z] ([a-z0-9] | '_' )*;
VAR_NAME : [A-Z]([A-Z0-9] | '_')*;

WS : [ \t\r\n]+ -> skip;
LINE_COMMENT : '//' .*? '\r'? '\n' -> skip ;
BLOCK_COMMENT      : '/*' .*? '*/' -> skip ;