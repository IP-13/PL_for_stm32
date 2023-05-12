package com.ip13.compiler;

public enum ByteCodeCommands {
    exit ("0"),
    jmp ("1"),
    jdec ("2"),
    jret ("3"),
    loop ("10"),
    jt ("20"),
    call ("100"),
    lit ("110"),
    var ("111"),
    ofc ("112"), // other function call, TODO come up with a better name
    rlit ("120"),
    rvar ("121"),
    rofc ("122"),
    BLN ("200"),
    INT ("201"),
    FLT ("202"),
    STR ("203");
    // TODO itmova-core

    private final String numberFormat;

    ByteCodeCommands(String numberFormat) {
        this.numberFormat = numberFormat;
    }

    public String getNumberFormat() {
        return numberFormat;
    }
}
