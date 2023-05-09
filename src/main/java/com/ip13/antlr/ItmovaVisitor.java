// Generated from /home/ivan/IdeaProjects/Forth_like_MOVA_for_stm32/src/main/java/com/ip13/antlr/Itmova.g4 by ANTLR 4.12.0
package com.ip13.antlr;

    import com.ip13.compiler.SuperClass;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ItmovaParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ItmovaVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ItmovaParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(ItmovaParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link ItmovaParser#statements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatements(ItmovaParser.StatementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ItmovaParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(ItmovaParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link ItmovaParser#entry_point}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEntry_point(ItmovaParser.Entry_pointContext ctx);
	/**
	 * Visit a parse tree produced by {@link ItmovaParser#from_cycle}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFrom_cycle(ItmovaParser.From_cycleContext ctx);
	/**
	 * Visit a parse tree produced by {@link ItmovaParser#lower_border}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLower_border(ItmovaParser.Lower_borderContext ctx);
	/**
	 * Visit a parse tree produced by {@link ItmovaParser#upper_border}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpper_border(ItmovaParser.Upper_borderContext ctx);
	/**
	 * Visit a parse tree produced by {@link ItmovaParser#step}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStep(ItmovaParser.StepContext ctx);
	/**
	 * Visit a parse tree produced by {@link ItmovaParser#if_operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_operator(ItmovaParser.If_operatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link ItmovaParser#bool_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBool_expr(ItmovaParser.Bool_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link ItmovaParser#func_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc_def(ItmovaParser.Func_defContext ctx);
	/**
	 * Visit a parse tree produced by {@link ItmovaParser#func_params}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc_params(ItmovaParser.Func_paramsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ItmovaParser#func_param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc_param(ItmovaParser.Func_paramContext ctx);
	/**
	 * Visit a parse tree produced by {@link ItmovaParser#func_call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc_call(ItmovaParser.Func_callContext ctx);
	/**
	 * Visit a parse tree produced by {@link ItmovaParser#func_args}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc_args(ItmovaParser.Func_argsContext ctx);
	/**
	 * Visit a parse tree produced by {@link ItmovaParser#func_arg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc_arg(ItmovaParser.Func_argContext ctx);
	/**
	 * Visit a parse tree produced by {@link ItmovaParser#return_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn_value(ItmovaParser.Return_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link ItmovaParser#return_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn_type(ItmovaParser.Return_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link ItmovaParser#var_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_def(ItmovaParser.Var_defContext ctx);
	/**
	 * Visit a parse tree produced by {@link ItmovaParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(ItmovaParser.LiteralContext ctx);
}