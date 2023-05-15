package com.ip13.compiler;

public class VarInfo {
    private int index;
    private ByteCodeCommands type;

    public VarInfo(int index, ByteCodeCommands type) {
        this.index = index;
        this.type = type;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public ByteCodeCommands getType() {
        return type;
    }

    public void setType(ByteCodeCommands type) {
        this.type = type;
    }
}