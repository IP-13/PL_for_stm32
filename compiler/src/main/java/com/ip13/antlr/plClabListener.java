// Generated from /home/ivan/IdeaProjects/PL_for_stm32/compiler/src/main/java/com/ip13/antlr/plClab.g4 by ANTLR 4.12.0
package com.ip13.antlr;

    import com.ip13.compiler.SuperClass;
    import com.ip13.compiler.ByteCodeCommands;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link plClabParser}.
 */
public interface plClabListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link plClabParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(plClabParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link plClabParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(plClabParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link plClabParser#statements}.
	 * @param ctx the parse tree
	 */
	void enterStatements(plClabParser.StatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link plClabParser#statements}.
	 * @param ctx the parse tree
	 */
	void exitStatements(plClabParser.StatementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link plClabParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(plClabParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link plClabParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(plClabParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link plClabParser#entry_point}.
	 * @param ctx the parse tree
	 */
	void enterEntry_point(plClabParser.Entry_pointContext ctx);
	/**
	 * Exit a parse tree produced by {@link plClabParser#entry_point}.
	 * @param ctx the parse tree
	 */
	void exitEntry_point(plClabParser.Entry_pointContext ctx);
	/**
	 * Enter a parse tree produced by {@link plClabParser#from_cycle}.
	 * @param ctx the parse tree
	 */
	void enterFrom_cycle(plClabParser.From_cycleContext ctx);
	/**
	 * Exit a parse tree produced by {@link plClabParser#from_cycle}.
	 * @param ctx the parse tree
	 */
	void exitFrom_cycle(plClabParser.From_cycleContext ctx);
	/**
	 * Enter a parse tree produced by {@link plClabParser#step}.
	 * @param ctx the parse tree
	 */
	void enterStep(plClabParser.StepContext ctx);
	/**
	 * Exit a parse tree produced by {@link plClabParser#step}.
	 * @param ctx the parse tree
	 */
	void exitStep(plClabParser.StepContext ctx);
	/**
	 * Enter a parse tree produced by {@link plClabParser#upper_border}.
	 * @param ctx the parse tree
	 */
	void enterUpper_border(plClabParser.Upper_borderContext ctx);
	/**
	 * Exit a parse tree produced by {@link plClabParser#upper_border}.
	 * @param ctx the parse tree
	 */
	void exitUpper_border(plClabParser.Upper_borderContext ctx);
	/**
	 * Enter a parse tree produced by {@link plClabParser#lower_border}.
	 * @param ctx the parse tree
	 */
	void enterLower_border(plClabParser.Lower_borderContext ctx);
	/**
	 * Exit a parse tree produced by {@link plClabParser#lower_border}.
	 * @param ctx the parse tree
	 */
	void exitLower_border(plClabParser.Lower_borderContext ctx);
	/**
	 * Enter a parse tree produced by {@link plClabParser#if_operator}.
	 * @param ctx the parse tree
	 */
	void enterIf_operator(plClabParser.If_operatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link plClabParser#if_operator}.
	 * @param ctx the parse tree
	 */
	void exitIf_operator(plClabParser.If_operatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link plClabParser#bool_expr}.
	 * @param ctx the parse tree
	 */
	void enterBool_expr(plClabParser.Bool_exprContext ctx);
	/**
	 * Exit a parse tree produced by {@link plClabParser#bool_expr}.
	 * @param ctx the parse tree
	 */
	void exitBool_expr(plClabParser.Bool_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link plClabParser#func_def}.
	 * @param ctx the parse tree
	 */
	void enterFunc_def(plClabParser.Func_defContext ctx);
	/**
	 * Exit a parse tree produced by {@link plClabParser#func_def}.
	 * @param ctx the parse tree
	 */
	void exitFunc_def(plClabParser.Func_defContext ctx);
	/**
	 * Enter a parse tree produced by {@link plClabParser#return_type}.
	 * @param ctx the parse tree
	 */
	void enterReturn_type(plClabParser.Return_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link plClabParser#return_type}.
	 * @param ctx the parse tree
	 */
	void exitReturn_type(plClabParser.Return_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link plClabParser#return_value}.
	 * @param ctx the parse tree
	 */
	void enterReturn_value(plClabParser.Return_valueContext ctx);
	/**
	 * Exit a parse tree produced by {@link plClabParser#return_value}.
	 * @param ctx the parse tree
	 */
	void exitReturn_value(plClabParser.Return_valueContext ctx);
	/**
	 * Enter a parse tree produced by {@link plClabParser#func_params}.
	 * @param ctx the parse tree
	 */
	void enterFunc_params(plClabParser.Func_paramsContext ctx);
	/**
	 * Exit a parse tree produced by {@link plClabParser#func_params}.
	 * @param ctx the parse tree
	 */
	void exitFunc_params(plClabParser.Func_paramsContext ctx);
	/**
	 * Enter a parse tree produced by {@link plClabParser#func_param}.
	 * @param ctx the parse tree
	 */
	void enterFunc_param(plClabParser.Func_paramContext ctx);
	/**
	 * Exit a parse tree produced by {@link plClabParser#func_param}.
	 * @param ctx the parse tree
	 */
	void exitFunc_param(plClabParser.Func_paramContext ctx);
	/**
	 * Enter a parse tree produced by {@link plClabParser#func_call}.
	 * @param ctx the parse tree
	 */
	void enterFunc_call(plClabParser.Func_callContext ctx);
	/**
	 * Exit a parse tree produced by {@link plClabParser#func_call}.
	 * @param ctx the parse tree
	 */
	void exitFunc_call(plClabParser.Func_callContext ctx);
	/**
	 * Enter a parse tree produced by {@link plClabParser#func_args}.
	 * @param ctx the parse tree
	 */
	void enterFunc_args(plClabParser.Func_argsContext ctx);
	/**
	 * Exit a parse tree produced by {@link plClabParser#func_args}.
	 * @param ctx the parse tree
	 */
	void exitFunc_args(plClabParser.Func_argsContext ctx);
	/**
	 * Enter a parse tree produced by {@link plClabParser#func_arg}.
	 * @param ctx the parse tree
	 */
	void enterFunc_arg(plClabParser.Func_argContext ctx);
	/**
	 * Exit a parse tree produced by {@link plClabParser#func_arg}.
	 * @param ctx the parse tree
	 */
	void exitFunc_arg(plClabParser.Func_argContext ctx);
	/**
	 * Enter a parse tree produced by {@link plClabParser#var_def}.
	 * @param ctx the parse tree
	 */
	void enterVar_def(plClabParser.Var_defContext ctx);
	/**
	 * Exit a parse tree produced by {@link plClabParser#var_def}.
	 * @param ctx the parse tree
	 */
	void exitVar_def(plClabParser.Var_defContext ctx);
	/**
	 * Enter a parse tree produced by {@link plClabParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(plClabParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link plClabParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(plClabParser.LiteralContext ctx);
}