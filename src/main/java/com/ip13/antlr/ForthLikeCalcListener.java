// Generated from java-escape by ANTLR 4.11.1
package com.ip13.antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ForthLikeCalcParser}.
 */
public interface ForthLikeCalcListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ForthLikeCalcParser#sum}.
	 * @param ctx the parse tree
	 */
	void enterSum(ForthLikeCalcParser.SumContext ctx);
	/**
	 * Exit a parse tree produced by {@link ForthLikeCalcParser#sum}.
	 * @param ctx the parse tree
	 */
	void exitSum(ForthLikeCalcParser.SumContext ctx);
}