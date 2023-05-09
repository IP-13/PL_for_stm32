// Generated from /home/ivan/IdeaProjects/Forth_like_MOVA_for_stm32/src/main/java/com/ip13/antlr/Itmova.g4 by ANTLR 4.12.0
package com.ip13.antlr;

    import com.ip13.compiler.SuperClass;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ItmovaParser}.
 */
public interface ItmovaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ItmovaParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(ItmovaParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link ItmovaParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(ItmovaParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link ItmovaParser#statements}.
	 * @param ctx the parse tree
	 */
	void enterStatements(ItmovaParser.StatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ItmovaParser#statements}.
	 * @param ctx the parse tree
	 */
	void exitStatements(ItmovaParser.StatementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ItmovaParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(ItmovaParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link ItmovaParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(ItmovaParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link ItmovaParser#entry_point}.
	 * @param ctx the parse tree
	 */
	void enterEntry_point(ItmovaParser.Entry_pointContext ctx);
	/**
	 * Exit a parse tree produced by {@link ItmovaParser#entry_point}.
	 * @param ctx the parse tree
	 */
	void exitEntry_point(ItmovaParser.Entry_pointContext ctx);
	/**
	 * Enter a parse tree produced by {@link ItmovaParser#from_cycle}.
	 * @param ctx the parse tree
	 */
	void enterFrom_cycle(ItmovaParser.From_cycleContext ctx);
	/**
	 * Exit a parse tree produced by {@link ItmovaParser#from_cycle}.
	 * @param ctx the parse tree
	 */
	void exitFrom_cycle(ItmovaParser.From_cycleContext ctx);
	/**
	 * Enter a parse tree produced by {@link ItmovaParser#lower_border}.
	 * @param ctx the parse tree
	 */
	void enterLower_border(ItmovaParser.Lower_borderContext ctx);
	/**
	 * Exit a parse tree produced by {@link ItmovaParser#lower_border}.
	 * @param ctx the parse tree
	 */
	void exitLower_border(ItmovaParser.Lower_borderContext ctx);
	/**
	 * Enter a parse tree produced by {@link ItmovaParser#upper_border}.
	 * @param ctx the parse tree
	 */
	void enterUpper_border(ItmovaParser.Upper_borderContext ctx);
	/**
	 * Exit a parse tree produced by {@link ItmovaParser#upper_border}.
	 * @param ctx the parse tree
	 */
	void exitUpper_border(ItmovaParser.Upper_borderContext ctx);
	/**
	 * Enter a parse tree produced by {@link ItmovaParser#step}.
	 * @param ctx the parse tree
	 */
	void enterStep(ItmovaParser.StepContext ctx);
	/**
	 * Exit a parse tree produced by {@link ItmovaParser#step}.
	 * @param ctx the parse tree
	 */
	void exitStep(ItmovaParser.StepContext ctx);
	/**
	 * Enter a parse tree produced by {@link ItmovaParser#if_operator}.
	 * @param ctx the parse tree
	 */
	void enterIf_operator(ItmovaParser.If_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link ItmovaParser#if_operator}.
	 * @param ctx the parse tree
	 */
	void exitIf_operator(ItmovaParser.If_operatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link ItmovaParser#bool_expr}.
	 * @param ctx the parse tree
	 */
	void enterBool_expr(ItmovaParser.Bool_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link ItmovaParser#bool_expr}.
	 * @param ctx the parse tree
	 */
	void exitBool_expr(ItmovaParser.Bool_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link ItmovaParser#func_def}.
	 * @param ctx the parse tree
	 */
	void enterFunc_def(ItmovaParser.Func_defContext ctx);
	/**
	 * Exit a parse tree produced by {@link ItmovaParser#func_def}.
	 * @param ctx the parse tree
	 */
	void exitFunc_def(ItmovaParser.Func_defContext ctx);
	/**
	 * Enter a parse tree produced by {@link ItmovaParser#func_params}.
	 * @param ctx the parse tree
	 */
	void enterFunc_params(ItmovaParser.Func_paramsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ItmovaParser#func_params}.
	 * @param ctx the parse tree
	 */
	void exitFunc_params(ItmovaParser.Func_paramsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ItmovaParser#func_param}.
	 * @param ctx the parse tree
	 */
	void enterFunc_param(ItmovaParser.Func_paramContext ctx);
	/**
	 * Exit a parse tree produced by {@link ItmovaParser#func_param}.
	 * @param ctx the parse tree
	 */
	void exitFunc_param(ItmovaParser.Func_paramContext ctx);
	/**
	 * Enter a parse tree produced by {@link ItmovaParser#func_call}.
	 * @param ctx the parse tree
	 */
	void enterFunc_call(ItmovaParser.Func_callContext ctx);
	/**
	 * Exit a parse tree produced by {@link ItmovaParser#func_call}.
	 * @param ctx the parse tree
	 */
	void exitFunc_call(ItmovaParser.Func_callContext ctx);
	/**
	 * Enter a parse tree produced by {@link ItmovaParser#func_args}.
	 * @param ctx the parse tree
	 */
	void enterFunc_args(ItmovaParser.Func_argsContext ctx);
	/**
	 * Exit a parse tree produced by {@link ItmovaParser#func_args}.
	 * @param ctx the parse tree
	 */
	void exitFunc_args(ItmovaParser.Func_argsContext ctx);
	/**
	 * Enter a parse tree produced by {@link ItmovaParser#func_arg}.
	 * @param ctx the parse tree
	 */
	void enterFunc_arg(ItmovaParser.Func_argContext ctx);
	/**
	 * Exit a parse tree produced by {@link ItmovaParser#func_arg}.
	 * @param ctx the parse tree
	 */
	void exitFunc_arg(ItmovaParser.Func_argContext ctx);
	/**
	 * Enter a parse tree produced by {@link ItmovaParser#return_value}.
	 * @param ctx the parse tree
	 */
	void enterReturn_value(ItmovaParser.Return_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link ItmovaParser#return_value}.
	 * @param ctx the parse tree
	 */
	void exitReturn_value(ItmovaParser.Return_valueContext ctx);
	/**
	 * Enter a parse tree produced by {@link ItmovaParser#return_type}.
	 * @param ctx the parse tree
	 */
	void enterReturn_type(ItmovaParser.Return_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link ItmovaParser#return_type}.
	 * @param ctx the parse tree
	 */
	void exitReturn_type(ItmovaParser.Return_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link ItmovaParser#var_def}.
	 * @param ctx the parse tree
	 */
	void enterVar_def(ItmovaParser.Var_defContext ctx);
	/**
	 * Exit a parse tree produced by {@link ItmovaParser#var_def}.
	 * @param ctx the parse tree
	 */
	void exitVar_def(ItmovaParser.Var_defContext ctx);
	/**
	 * Enter a parse tree produced by {@link ItmovaParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(ItmovaParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link ItmovaParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(ItmovaParser.LiteralContext ctx);
}