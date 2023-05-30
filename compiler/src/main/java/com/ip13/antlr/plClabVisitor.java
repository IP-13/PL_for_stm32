// Generated from /home/ivan/IdeaProjects/PL_for_stm32/compiler/src/main/java/com/ip13/antlr/plClab.g4 by ANTLR 4.12.0
package com.ip13.antlr;

    import com.ip13.compiler.SuperClass;
    import com.ip13.compiler.ByteCodeCommands;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link plClabParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface plClabVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link plClabParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(plClabParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link plClabParser#statements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatements(plClabParser.StatementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link plClabParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(plClabParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link plClabParser#entry_point}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEntry_point(plClabParser.Entry_pointContext ctx);
	/**
	 * Visit a parse tree produced by {@link plClabParser#from_cycle}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFrom_cycle(plClabParser.From_cycleContext ctx);
	/**
	 * Visit a parse tree produced by {@link plClabParser#step}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStep(plClabParser.StepContext ctx);
	/**
	 * Visit a parse tree produced by {@link plClabParser#upper_border}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpper_border(plClabParser.Upper_borderContext ctx);
	/**
	 * Visit a parse tree produced by {@link plClabParser#lower_border}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLower_border(plClabParser.Lower_borderContext ctx);
	/**
	 * Visit a parse tree produced by {@link plClabParser#if_operator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIf_operator(plClabParser.If_operatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link plClabParser#bool_expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBool_expr(plClabParser.Bool_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link plClabParser#func_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc_def(plClabParser.Func_defContext ctx);
	/**
	 * Visit a parse tree produced by {@link plClabParser#return_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn_type(plClabParser.Return_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link plClabParser#return_value}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturn_value(plClabParser.Return_valueContext ctx);
	/**
	 * Visit a parse tree produced by {@link plClabParser#func_params}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc_params(plClabParser.Func_paramsContext ctx);
	/**
	 * Visit a parse tree produced by {@link plClabParser#func_param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc_param(plClabParser.Func_paramContext ctx);
	/**
	 * Visit a parse tree produced by {@link plClabParser#func_call}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc_call(plClabParser.Func_callContext ctx);
	/**
	 * Visit a parse tree produced by {@link plClabParser#func_args}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc_args(plClabParser.Func_argsContext ctx);
	/**
	 * Visit a parse tree produced by {@link plClabParser#func_arg}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunc_arg(plClabParser.Func_argContext ctx);
	/**
	 * Visit a parse tree produced by {@link plClabParser#var_def}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_def(plClabParser.Var_defContext ctx);
	/**
	 * Visit a parse tree produced by {@link plClabParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(plClabParser.LiteralContext ctx);
}