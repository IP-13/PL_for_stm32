package com.ip13;

import com.ip13.antlr.ForthLikeCalcLexer;
import com.ip13.antlr.ForthLikeCalcParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Main {
    public static void main(String[] args) throws Exception {
        ForthLikeCalcLexer lexer = new ForthLikeCalcLexer(CharStreams.fromString("100 + 200"));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        System.out.println(tokens.getText());
        ForthLikeCalcParser parser = new ForthLikeCalcParser(tokens);
        ParseTree tree = parser.sum();
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new MyClass(), tree);
    }
}

