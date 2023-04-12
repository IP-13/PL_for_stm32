grammar ForthLikeCalc;
//calc :
//    sum
//    |
//    sub
//    |
//    mul
//    |
//    div
//    ;
sum : INT '+' INT;
//sub : INT '-' INT;
//mul : INT '*' INT;
//div : INT '/' INT;
INT : [0-9]+ ;
WS : [ \t\r\n]+ -> skip ;