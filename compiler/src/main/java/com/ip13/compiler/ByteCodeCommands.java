package com.ip13.compiler;

public enum ByteCodeCommands {
    // program flow
    exit(0),
    jmp(1), // absolute jump
    jdec(2), // relative jump
    jret(3),
    // from loop
    loop(10),
    fint(11),
    fvar(12),
    // if operator
    jt(20), // relative jump
    // func-related
    call(100),
    lit(110),
    var(111),
    ofc(112),
    rlit(120),
    rvar(121),
    rofc(122),
    // types
    BOOL(200),
    INT(201),
    FLT(202),
    STR(203),
    PTR(204),
    VOID(205),
    // core
    print(700),
    assign(701),
    // dynamic memory
    get_data(710),
    set_data(711),
    get_addr(712),
    malloc(713),
    // bool functions
    and(720),
    or(721),
    not(722),
    // str functions
    concat(725),
    substr(726),
    like(727),
    length(728),
    // math unary
    abs(735),
    inc(736),
    dec(737),
    // math binary
    pow(756),
    sum(757),
    sub(758),
    mul(759),
    div(760),
    less(762),
    greater(763),
    // special
    MAIN(999);


    private final int numberFormat;


    ByteCodeCommands(int numberFormat) {
        this.numberFormat = numberFormat;
    }


    public int getNumberFormat() {
        return numberFormat;
    }


    public static String getCore(String s) {
        try {
            return ByteCodeCommands.valueOf(s).toString();
        } catch (IllegalArgumentException ex) {
            return null;
        }
    }


    public static int getCommandInNumberFormat(String s) {
        try {
            return ByteCodeCommands.valueOf(s).numberFormat;
        } catch (IllegalArgumentException ex) {
            return -1;
        }
    }
}