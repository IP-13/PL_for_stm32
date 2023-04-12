package com.ip13;

import com.ip13.antlr.ForthLikeCalcBaseListener;
import com.ip13.antlr.ForthLikeCalcParser;

import static com.ip13.antlr.ForthLikeCalcParser.*;

public class MyClass extends ForthLikeCalcBaseListener {
    @Override
    public void enterSum(SumContext ctx) {
        System.out.println(Integer.valueOf(ctx.INT().get(0).getText()) + Integer.valueOf(ctx.INT().get(1).getText()));
    }

    @Override
    public void exitSum(SumContext ctx) {
        System.out.println("Exit sum");
    }
}
