// Generated from java-escape by ANTLR 4.11.1
package com.ip13.antlr;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ForthLikeCalcParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ForthLikeCalcVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ForthLikeCalcParser#sum}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSum(ForthLikeCalcParser.SumContext ctx);
}