// Generated from /home/ivan/IdeaProjects/Forth_like_MOVA_for_stm32/src/main/java/com/ip13/antlr/Itmova.g4 by ANTLR 4.12.0
package com.ip13.antlr;

    import com.ip13.compiler.SuperClass;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class ItmovaParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		START=1, FINISH=2, RETURN=3, FROM=4, TO=5, WITH=6, IF=7, TYPE=8, ENTRY_POINT=9, 
		COLON=10, SEMICOLON=11, COMMA=12, EXCLAMATION_MARK=13, OPEN_BRACE=14, 
		CLOSE_BRACE=15, DOUBLE_QUOTE=16, BOOL=17, INT=18, FLOAT=19, STRING=20, 
		VOID=21, FUNC_NAME=22, VAR_NAME=23, WS=24, LINE_COMMENT=25, BLOCK_COMMENT=26;
	public static final int
		RULE_program = 0, RULE_statements = 1, RULE_statement = 2, RULE_entry_point = 3, 
		RULE_from_cycle = 4, RULE_lower_border = 5, RULE_upper_border = 6, RULE_step = 7, 
		RULE_if_operator = 8, RULE_bool_expr = 9, RULE_func_def = 10, RULE_func_params = 11, 
		RULE_func_param = 12, RULE_func_call = 13, RULE_func_args = 14, RULE_func_arg = 15, 
		RULE_return_value = 16, RULE_return_type = 17, RULE_var_def = 18, RULE_literal = 19;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "statements", "statement", "entry_point", "from_cycle", "lower_border", 
			"upper_border", "step", "if_operator", "bool_expr", "func_def", "func_params", 
			"func_param", "func_call", "func_args", "func_arg", "return_value", "return_type", 
			"var_def", "literal"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'START'", "'FINISH'", "'RETURN'", "'FROM'", "'TO'", "'WITH'", 
			"'IF'", null, "'MAIN'", "':'", "';'", "','", "'!'", "'('", "')'", "'\"'", 
			null, null, null, null, "'void'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "START", "FINISH", "RETURN", "FROM", "TO", "WITH", "IF", "TYPE", 
			"ENTRY_POINT", "COLON", "SEMICOLON", "COMMA", "EXCLAMATION_MARK", "OPEN_BRACE", 
			"CLOSE_BRACE", "DOUBLE_QUOTE", "BOOL", "INT", "FLOAT", "STRING", "VOID", 
			"FUNC_NAME", "VAR_NAME", "WS", "LINE_COMMENT", "BLOCK_COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Itmova.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ItmovaParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ItmovaListener ) ((ItmovaListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ItmovaListener ) ((ItmovaListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ItmovaVisitor ) return ((ItmovaVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
			statements(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementsContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public StatementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ItmovaListener ) ((ItmovaListener)listener).enterStatements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ItmovaListener ) ((ItmovaListener)listener).exitStatements(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ItmovaVisitor ) return ((ItmovaVisitor<? extends T>)visitor).visitStatements(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementsContext statements() throws RecognitionException {
		return statements(0);
	}

	private StatementsContext statements(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		StatementsContext _localctx = new StatementsContext(_ctx, _parentState);
		StatementsContext _prevctx = _localctx;
		int _startState = 2;
		enterRecursionRule(_localctx, 2, RULE_statements, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				setState(43);
				statement();
				}
				break;
			case 2:
				{
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(51);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new StatementsContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_statements);
					setState(47);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(48);
					statement();
					}
					} 
				}
				setState(53);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public Func_callContext func_call() {
			return getRuleContext(Func_callContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(ItmovaParser.SEMICOLON, 0); }
		public Var_defContext var_def() {
			return getRuleContext(Var_defContext.class,0);
		}
		public From_cycleContext from_cycle() {
			return getRuleContext(From_cycleContext.class,0);
		}
		public If_operatorContext if_operator() {
			return getRuleContext(If_operatorContext.class,0);
		}
		public Func_defContext func_def() {
			return getRuleContext(Func_defContext.class,0);
		}
		public Entry_pointContext entry_point() {
			return getRuleContext(Entry_pointContext.class,0);
		}
		public Return_valueContext return_value() {
			return getRuleContext(Return_valueContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ItmovaListener ) ((ItmovaListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ItmovaListener ) ((ItmovaListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ItmovaVisitor ) return ((ItmovaVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_statement);
		try {
			setState(63);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(54);
				func_call();
				setState(55);
				match(SEMICOLON);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(57);
				var_def();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(58);
				from_cycle();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(59);
				if_operator();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(60);
				func_def();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(61);
				entry_point();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(62);
				return_value();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Entry_pointContext extends ParserRuleContext {
		public Token ENTRY_POINT;
		public TerminalNode ENTRY_POINT() { return getToken(ItmovaParser.ENTRY_POINT, 0); }
		public Entry_pointContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entry_point; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ItmovaListener ) ((ItmovaListener)listener).enterEntry_point(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ItmovaListener ) ((ItmovaListener)listener).exitEntry_point(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ItmovaVisitor ) return ((ItmovaVisitor<? extends T>)visitor).visitEntry_point(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Entry_pointContext entry_point() throws RecognitionException {
		Entry_pointContext _localctx = new Entry_pointContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_entry_point);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			((Entry_pointContext)_localctx).ENTRY_POINT = match(ENTRY_POINT);
			SuperClass.entryPoint((((Entry_pointContext)_localctx).ENTRY_POINT!=null?((Entry_pointContext)_localctx).ENTRY_POINT.getLine():0));
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class From_cycleContext extends ParserRuleContext {
		public TerminalNode FROM() { return getToken(ItmovaParser.FROM, 0); }
		public Lower_borderContext lower_border() {
			return getRuleContext(Lower_borderContext.class,0);
		}
		public TerminalNode TO() { return getToken(ItmovaParser.TO, 0); }
		public Upper_borderContext upper_border() {
			return getRuleContext(Upper_borderContext.class,0);
		}
		public TerminalNode WITH() { return getToken(ItmovaParser.WITH, 0); }
		public StepContext step() {
			return getRuleContext(StepContext.class,0);
		}
		public TerminalNode COLON() { return getToken(ItmovaParser.COLON, 0); }
		public TerminalNode START() { return getToken(ItmovaParser.START, 0); }
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public TerminalNode FINISH() { return getToken(ItmovaParser.FINISH, 0); }
		public From_cycleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_from_cycle; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ItmovaListener ) ((ItmovaListener)listener).enterFrom_cycle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ItmovaListener ) ((ItmovaListener)listener).exitFrom_cycle(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ItmovaVisitor ) return ((ItmovaVisitor<? extends T>)visitor).visitFrom_cycle(this);
			else return visitor.visitChildren(this);
		}
	}

	public final From_cycleContext from_cycle() throws RecognitionException {
		From_cycleContext _localctx = new From_cycleContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_from_cycle);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			match(FROM);
			setState(69);
			lower_border();
			setState(70);
			match(TO);
			setState(71);
			upper_border();
			setState(72);
			match(WITH);
			setState(73);
			step();
			setState(74);
			match(COLON);
			setState(75);
			match(START);
			setState(76);
			statements(0);
			setState(77);
			match(FINISH);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Lower_borderContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(ItmovaParser.INT, 0); }
		public TerminalNode VAR_NAME() { return getToken(ItmovaParser.VAR_NAME, 0); }
		public Lower_borderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lower_border; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ItmovaListener ) ((ItmovaListener)listener).enterLower_border(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ItmovaListener ) ((ItmovaListener)listener).exitLower_border(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ItmovaVisitor ) return ((ItmovaVisitor<? extends T>)visitor).visitLower_border(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Lower_borderContext lower_border() throws RecognitionException {
		Lower_borderContext _localctx = new Lower_borderContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_lower_border);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(79);
			_la = _input.LA(1);
			if ( !(_la==INT || _la==VAR_NAME) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Upper_borderContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(ItmovaParser.INT, 0); }
		public TerminalNode VAR_NAME() { return getToken(ItmovaParser.VAR_NAME, 0); }
		public Upper_borderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_upper_border; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ItmovaListener ) ((ItmovaListener)listener).enterUpper_border(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ItmovaListener ) ((ItmovaListener)listener).exitUpper_border(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ItmovaVisitor ) return ((ItmovaVisitor<? extends T>)visitor).visitUpper_border(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Upper_borderContext upper_border() throws RecognitionException {
		Upper_borderContext _localctx = new Upper_borderContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_upper_border);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(81);
			_la = _input.LA(1);
			if ( !(_la==INT || _la==VAR_NAME) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StepContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(ItmovaParser.INT, 0); }
		public TerminalNode VAR_NAME() { return getToken(ItmovaParser.VAR_NAME, 0); }
		public StepContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_step; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ItmovaListener ) ((ItmovaListener)listener).enterStep(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ItmovaListener ) ((ItmovaListener)listener).exitStep(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ItmovaVisitor ) return ((ItmovaVisitor<? extends T>)visitor).visitStep(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StepContext step() throws RecognitionException {
		StepContext _localctx = new StepContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_step);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			_la = _input.LA(1);
			if ( !(_la==INT || _la==VAR_NAME) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class If_operatorContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(ItmovaParser.IF, 0); }
		public Bool_exprContext bool_expr() {
			return getRuleContext(Bool_exprContext.class,0);
		}
		public TerminalNode COLON() { return getToken(ItmovaParser.COLON, 0); }
		public TerminalNode START() { return getToken(ItmovaParser.START, 0); }
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public TerminalNode FINISH() { return getToken(ItmovaParser.FINISH, 0); }
		public If_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ItmovaListener ) ((ItmovaListener)listener).enterIf_operator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ItmovaListener ) ((ItmovaListener)listener).exitIf_operator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ItmovaVisitor ) return ((ItmovaVisitor<? extends T>)visitor).visitIf_operator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final If_operatorContext if_operator() throws RecognitionException {
		If_operatorContext _localctx = new If_operatorContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_if_operator);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(85);
			match(IF);
			setState(86);
			bool_expr();
			setState(87);
			match(COLON);
			setState(88);
			match(START);
			setState(89);
			statements(0);
			setState(90);
			match(FINISH);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Bool_exprContext extends ParserRuleContext {
		public Func_callContext func_call() {
			return getRuleContext(Func_callContext.class,0);
		}
		public Bool_exprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bool_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ItmovaListener ) ((ItmovaListener)listener).enterBool_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ItmovaListener ) ((ItmovaListener)listener).exitBool_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ItmovaVisitor ) return ((ItmovaVisitor<? extends T>)visitor).visitBool_expr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Bool_exprContext bool_expr() throws RecognitionException {
		Bool_exprContext _localctx = new Bool_exprContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_bool_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			func_call();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Func_defContext extends ParserRuleContext {
		public Token FUNC_NAME;
		public Return_typeContext return_type;
		public TerminalNode FUNC_NAME() { return getToken(ItmovaParser.FUNC_NAME, 0); }
		public TerminalNode OPEN_BRACE() { return getToken(ItmovaParser.OPEN_BRACE, 0); }
		public Func_paramsContext func_params() {
			return getRuleContext(Func_paramsContext.class,0);
		}
		public TerminalNode CLOSE_BRACE() { return getToken(ItmovaParser.CLOSE_BRACE, 0); }
		public TerminalNode COLON() { return getToken(ItmovaParser.COLON, 0); }
		public Return_typeContext return_type() {
			return getRuleContext(Return_typeContext.class,0);
		}
		public TerminalNode START() { return getToken(ItmovaParser.START, 0); }
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public TerminalNode FINISH() { return getToken(ItmovaParser.FINISH, 0); }
		public Func_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_def; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ItmovaListener ) ((ItmovaListener)listener).enterFunc_def(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ItmovaListener ) ((ItmovaListener)listener).exitFunc_def(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ItmovaVisitor ) return ((ItmovaVisitor<? extends T>)visitor).visitFunc_def(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Func_defContext func_def() throws RecognitionException {
		Func_defContext _localctx = new Func_defContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_func_def);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			((Func_defContext)_localctx).FUNC_NAME = match(FUNC_NAME);
			setState(95);
			match(OPEN_BRACE);
			setState(96);
			func_params(0);
			setState(97);
			match(CLOSE_BRACE);
			setState(98);
			match(COLON);
			setState(99);
			((Func_defContext)_localctx).return_type = return_type();
			setState(100);
			match(START);
			setState(101);
			statements(0);
			setState(102);
			match(FINISH);
			SuperClass.funcDef((((Func_defContext)_localctx).FUNC_NAME!=null?((Func_defContext)_localctx).FUNC_NAME.getText():null), (((Func_defContext)_localctx).return_type!=null?_input.getText(((Func_defContext)_localctx).return_type.start,((Func_defContext)_localctx).return_type.stop):null));
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Func_paramsContext extends ParserRuleContext {
		public Func_paramContext func_param() {
			return getRuleContext(Func_paramContext.class,0);
		}
		public Func_paramsContext func_params() {
			return getRuleContext(Func_paramsContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(ItmovaParser.COMMA, 0); }
		public Func_paramsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_params; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ItmovaListener ) ((ItmovaListener)listener).enterFunc_params(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ItmovaListener ) ((ItmovaListener)listener).exitFunc_params(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ItmovaVisitor ) return ((ItmovaVisitor<? extends T>)visitor).visitFunc_params(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Func_paramsContext func_params() throws RecognitionException {
		return func_params(0);
	}

	private Func_paramsContext func_params(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Func_paramsContext _localctx = new Func_paramsContext(_ctx, _parentState);
		Func_paramsContext _prevctx = _localctx;
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_func_params, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(106);
				func_param();
				}
				break;
			case 2:
				{
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(115);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Func_paramsContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_func_params);
					setState(110);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(111);
					match(COMMA);
					setState(112);
					func_param();
					}
					} 
				}
				setState(117);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Func_paramContext extends ParserRuleContext {
		public Token VAR_NAME;
		public Token TYPE;
		public TerminalNode VAR_NAME() { return getToken(ItmovaParser.VAR_NAME, 0); }
		public TerminalNode COLON() { return getToken(ItmovaParser.COLON, 0); }
		public TerminalNode TYPE() { return getToken(ItmovaParser.TYPE, 0); }
		public Func_paramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ItmovaListener ) ((ItmovaListener)listener).enterFunc_param(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ItmovaListener ) ((ItmovaListener)listener).exitFunc_param(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ItmovaVisitor ) return ((ItmovaVisitor<? extends T>)visitor).visitFunc_param(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Func_paramContext func_param() throws RecognitionException {
		Func_paramContext _localctx = new Func_paramContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_func_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			((Func_paramContext)_localctx).VAR_NAME = match(VAR_NAME);
			setState(119);
			match(COLON);
			setState(120);
			((Func_paramContext)_localctx).TYPE = match(TYPE);
			SuperClass.funcParam((((Func_paramContext)_localctx).VAR_NAME!=null?((Func_paramContext)_localctx).VAR_NAME.getText():null), (((Func_paramContext)_localctx).TYPE!=null?((Func_paramContext)_localctx).TYPE.getText():null));
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Func_callContext extends ParserRuleContext {
		public TerminalNode FUNC_NAME() { return getToken(ItmovaParser.FUNC_NAME, 0); }
		public TerminalNode OPEN_BRACE() { return getToken(ItmovaParser.OPEN_BRACE, 0); }
		public Func_argsContext func_args() {
			return getRuleContext(Func_argsContext.class,0);
		}
		public TerminalNode CLOSE_BRACE() { return getToken(ItmovaParser.CLOSE_BRACE, 0); }
		public Func_callContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_call; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ItmovaListener ) ((ItmovaListener)listener).enterFunc_call(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ItmovaListener ) ((ItmovaListener)listener).exitFunc_call(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ItmovaVisitor ) return ((ItmovaVisitor<? extends T>)visitor).visitFunc_call(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Func_callContext func_call() throws RecognitionException {
		Func_callContext _localctx = new Func_callContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_func_call);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
			match(FUNC_NAME);
			setState(124);
			match(OPEN_BRACE);
			setState(125);
			func_args(0);
			setState(126);
			match(CLOSE_BRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Func_argsContext extends ParserRuleContext {
		public Func_argContext func_arg() {
			return getRuleContext(Func_argContext.class,0);
		}
		public Func_argsContext func_args() {
			return getRuleContext(Func_argsContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(ItmovaParser.COMMA, 0); }
		public Func_argsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_args; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ItmovaListener ) ((ItmovaListener)listener).enterFunc_args(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ItmovaListener ) ((ItmovaListener)listener).exitFunc_args(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ItmovaVisitor ) return ((ItmovaVisitor<? extends T>)visitor).visitFunc_args(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Func_argsContext func_args() throws RecognitionException {
		return func_args(0);
	}

	private Func_argsContext func_args(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		Func_argsContext _localctx = new Func_argsContext(_ctx, _parentState);
		Func_argsContext _prevctx = _localctx;
		int _startState = 28;
		enterRecursionRule(_localctx, 28, RULE_func_args, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				{
				setState(129);
				func_arg();
				}
				break;
			case 2:
				{
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(138);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Func_argsContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_func_args);
					setState(133);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(134);
					match(COMMA);
					setState(135);
					func_arg();
					}
					} 
				}
				setState(140);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Func_argContext extends ParserRuleContext {
		public TerminalNode VAR_NAME() { return getToken(ItmovaParser.VAR_NAME, 0); }
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public Func_callContext func_call() {
			return getRuleContext(Func_callContext.class,0);
		}
		public Func_argContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_arg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ItmovaListener ) ((ItmovaListener)listener).enterFunc_arg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ItmovaListener ) ((ItmovaListener)listener).exitFunc_arg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ItmovaVisitor ) return ((ItmovaVisitor<? extends T>)visitor).visitFunc_arg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Func_argContext func_arg() throws RecognitionException {
		Func_argContext _localctx = new Func_argContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_func_arg);
		try {
			setState(144);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VAR_NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(141);
				match(VAR_NAME);
				}
				break;
			case BOOL:
			case INT:
			case FLOAT:
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(142);
				literal();
				}
				break;
			case FUNC_NAME:
				enterOuterAlt(_localctx, 3);
				{
				setState(143);
				func_call();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Return_valueContext extends ParserRuleContext {
		public TerminalNode RETURN() { return getToken(ItmovaParser.RETURN, 0); }
		public TerminalNode SEMICOLON() { return getToken(ItmovaParser.SEMICOLON, 0); }
		public TerminalNode VAR_NAME() { return getToken(ItmovaParser.VAR_NAME, 0); }
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public Func_callContext func_call() {
			return getRuleContext(Func_callContext.class,0);
		}
		public Return_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ItmovaListener ) ((ItmovaListener)listener).enterReturn_value(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ItmovaListener ) ((ItmovaListener)listener).exitReturn_value(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ItmovaVisitor ) return ((ItmovaVisitor<? extends T>)visitor).visitReturn_value(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Return_valueContext return_value() throws RecognitionException {
		Return_valueContext _localctx = new Return_valueContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_return_value);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			match(RETURN);
			setState(150);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VAR_NAME:
				{
				setState(147);
				match(VAR_NAME);
				}
				break;
			case BOOL:
			case INT:
			case FLOAT:
			case STRING:
				{
				setState(148);
				literal();
				}
				break;
			case FUNC_NAME:
				{
				setState(149);
				func_call();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(152);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Return_typeContext extends ParserRuleContext {
		public TerminalNode TYPE() { return getToken(ItmovaParser.TYPE, 0); }
		public TerminalNode VOID() { return getToken(ItmovaParser.VOID, 0); }
		public Return_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ItmovaListener ) ((ItmovaListener)listener).enterReturn_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ItmovaListener ) ((ItmovaListener)listener).exitReturn_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ItmovaVisitor ) return ((ItmovaVisitor<? extends T>)visitor).visitReturn_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Return_typeContext return_type() throws RecognitionException {
		Return_typeContext _localctx = new Return_typeContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_return_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154);
			_la = _input.LA(1);
			if ( !(_la==TYPE || _la==VOID) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Var_defContext extends ParserRuleContext {
		public Token VAR_NAME;
		public Token TYPE;
		public TerminalNode VAR_NAME() { return getToken(ItmovaParser.VAR_NAME, 0); }
		public TerminalNode COLON() { return getToken(ItmovaParser.COLON, 0); }
		public TerminalNode TYPE() { return getToken(ItmovaParser.TYPE, 0); }
		public TerminalNode SEMICOLON() { return getToken(ItmovaParser.SEMICOLON, 0); }
		public Var_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_def; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ItmovaListener ) ((ItmovaListener)listener).enterVar_def(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ItmovaListener ) ((ItmovaListener)listener).exitVar_def(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ItmovaVisitor ) return ((ItmovaVisitor<? extends T>)visitor).visitVar_def(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Var_defContext var_def() throws RecognitionException {
		Var_defContext _localctx = new Var_defContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_var_def);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			((Var_defContext)_localctx).VAR_NAME = match(VAR_NAME);
			setState(157);
			match(COLON);
			setState(158);
			((Var_defContext)_localctx).TYPE = match(TYPE);
			setState(159);
			match(SEMICOLON);
			SuperClass.varDef((((Var_defContext)_localctx).VAR_NAME!=null?((Var_defContext)_localctx).VAR_NAME.getText():null), (((Var_defContext)_localctx).TYPE!=null?((Var_defContext)_localctx).TYPE.getText():null));
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode BOOL() { return getToken(ItmovaParser.BOOL, 0); }
		public TerminalNode INT() { return getToken(ItmovaParser.INT, 0); }
		public TerminalNode FLOAT() { return getToken(ItmovaParser.FLOAT, 0); }
		public TerminalNode STRING() { return getToken(ItmovaParser.STRING, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ItmovaListener ) ((ItmovaListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ItmovaListener ) ((ItmovaListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ItmovaVisitor ) return ((ItmovaVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_literal);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(162);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1966080L) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 1:
			return statements_sempred((StatementsContext)_localctx, predIndex);
		case 11:
			return func_params_sempred((Func_paramsContext)_localctx, predIndex);
		case 14:
			return func_args_sempred((Func_argsContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean statements_sempred(StatementsContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean func_params_sempred(Func_paramsContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean func_args_sempred(Func_argsContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u001a\u00a5\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007"+
		"\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007"+
		"\u0012\u0002\u0013\u0007\u0013\u0001\u0000\u0001\u0000\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0003\u0001.\b\u0001\u0001\u0001\u0001\u0001\u0005"+
		"\u00012\b\u0001\n\u0001\f\u00015\t\u0001\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0003\u0002@\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005\u0001"+
		"\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001\b"+
		"\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001\t\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000bm\b\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\u000b\u0005\u000br\b\u000b\n\u000b\f\u000bu\t\u000b"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u0084\b\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0005\u000e\u0089\b\u000e\n\u000e"+
		"\f\u000e\u008c\t\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0003\u000f"+
		"\u0091\b\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010"+
		"\u0097\b\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0013"+
		"\u0001\u0013\u0001\u0013\u0000\u0003\u0002\u0016\u001c\u0014\u0000\u0002"+
		"\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e"+
		" \"$&\u0000\u0003\u0002\u0000\u0012\u0012\u0017\u0017\u0002\u0000\b\b"+
		"\u0015\u0015\u0001\u0000\u0011\u0014\u00a0\u0000(\u0001\u0000\u0000\u0000"+
		"\u0002-\u0001\u0000\u0000\u0000\u0004?\u0001\u0000\u0000\u0000\u0006A"+
		"\u0001\u0000\u0000\u0000\bD\u0001\u0000\u0000\u0000\nO\u0001\u0000\u0000"+
		"\u0000\fQ\u0001\u0000\u0000\u0000\u000eS\u0001\u0000\u0000\u0000\u0010"+
		"U\u0001\u0000\u0000\u0000\u0012\\\u0001\u0000\u0000\u0000\u0014^\u0001"+
		"\u0000\u0000\u0000\u0016l\u0001\u0000\u0000\u0000\u0018v\u0001\u0000\u0000"+
		"\u0000\u001a{\u0001\u0000\u0000\u0000\u001c\u0083\u0001\u0000\u0000\u0000"+
		"\u001e\u0090\u0001\u0000\u0000\u0000 \u0092\u0001\u0000\u0000\u0000\""+
		"\u009a\u0001\u0000\u0000\u0000$\u009c\u0001\u0000\u0000\u0000&\u00a2\u0001"+
		"\u0000\u0000\u0000()\u0003\u0002\u0001\u0000)\u0001\u0001\u0000\u0000"+
		"\u0000*+\u0006\u0001\uffff\uffff\u0000+.\u0003\u0004\u0002\u0000,.\u0001"+
		"\u0000\u0000\u0000-*\u0001\u0000\u0000\u0000-,\u0001\u0000\u0000\u0000"+
		".3\u0001\u0000\u0000\u0000/0\n\u0002\u0000\u000002\u0003\u0004\u0002\u0000"+
		"1/\u0001\u0000\u0000\u000025\u0001\u0000\u0000\u000031\u0001\u0000\u0000"+
		"\u000034\u0001\u0000\u0000\u00004\u0003\u0001\u0000\u0000\u000053\u0001"+
		"\u0000\u0000\u000067\u0003\u001a\r\u000078\u0005\u000b\u0000\u00008@\u0001"+
		"\u0000\u0000\u00009@\u0003$\u0012\u0000:@\u0003\b\u0004\u0000;@\u0003"+
		"\u0010\b\u0000<@\u0003\u0014\n\u0000=@\u0003\u0006\u0003\u0000>@\u0003"+
		" \u0010\u0000?6\u0001\u0000\u0000\u0000?9\u0001\u0000\u0000\u0000?:\u0001"+
		"\u0000\u0000\u0000?;\u0001\u0000\u0000\u0000?<\u0001\u0000\u0000\u0000"+
		"?=\u0001\u0000\u0000\u0000?>\u0001\u0000\u0000\u0000@\u0005\u0001\u0000"+
		"\u0000\u0000AB\u0005\t\u0000\u0000BC\u0006\u0003\uffff\uffff\u0000C\u0007"+
		"\u0001\u0000\u0000\u0000DE\u0005\u0004\u0000\u0000EF\u0003\n\u0005\u0000"+
		"FG\u0005\u0005\u0000\u0000GH\u0003\f\u0006\u0000HI\u0005\u0006\u0000\u0000"+
		"IJ\u0003\u000e\u0007\u0000JK\u0005\n\u0000\u0000KL\u0005\u0001\u0000\u0000"+
		"LM\u0003\u0002\u0001\u0000MN\u0005\u0002\u0000\u0000N\t\u0001\u0000\u0000"+
		"\u0000OP\u0007\u0000\u0000\u0000P\u000b\u0001\u0000\u0000\u0000QR\u0007"+
		"\u0000\u0000\u0000R\r\u0001\u0000\u0000\u0000ST\u0007\u0000\u0000\u0000"+
		"T\u000f\u0001\u0000\u0000\u0000UV\u0005\u0007\u0000\u0000VW\u0003\u0012"+
		"\t\u0000WX\u0005\n\u0000\u0000XY\u0005\u0001\u0000\u0000YZ\u0003\u0002"+
		"\u0001\u0000Z[\u0005\u0002\u0000\u0000[\u0011\u0001\u0000\u0000\u0000"+
		"\\]\u0003\u001a\r\u0000]\u0013\u0001\u0000\u0000\u0000^_\u0005\u0016\u0000"+
		"\u0000_`\u0005\u000e\u0000\u0000`a\u0003\u0016\u000b\u0000ab\u0005\u000f"+
		"\u0000\u0000bc\u0005\n\u0000\u0000cd\u0003\"\u0011\u0000de\u0005\u0001"+
		"\u0000\u0000ef\u0003\u0002\u0001\u0000fg\u0005\u0002\u0000\u0000gh\u0006"+
		"\n\uffff\uffff\u0000h\u0015\u0001\u0000\u0000\u0000ij\u0006\u000b\uffff"+
		"\uffff\u0000jm\u0003\u0018\f\u0000km\u0001\u0000\u0000\u0000li\u0001\u0000"+
		"\u0000\u0000lk\u0001\u0000\u0000\u0000ms\u0001\u0000\u0000\u0000no\n\u0002"+
		"\u0000\u0000op\u0005\f\u0000\u0000pr\u0003\u0018\f\u0000qn\u0001\u0000"+
		"\u0000\u0000ru\u0001\u0000\u0000\u0000sq\u0001\u0000\u0000\u0000st\u0001"+
		"\u0000\u0000\u0000t\u0017\u0001\u0000\u0000\u0000us\u0001\u0000\u0000"+
		"\u0000vw\u0005\u0017\u0000\u0000wx\u0005\n\u0000\u0000xy\u0005\b\u0000"+
		"\u0000yz\u0006\f\uffff\uffff\u0000z\u0019\u0001\u0000\u0000\u0000{|\u0005"+
		"\u0016\u0000\u0000|}\u0005\u000e\u0000\u0000}~\u0003\u001c\u000e\u0000"+
		"~\u007f\u0005\u000f\u0000\u0000\u007f\u001b\u0001\u0000\u0000\u0000\u0080"+
		"\u0081\u0006\u000e\uffff\uffff\u0000\u0081\u0084\u0003\u001e\u000f\u0000"+
		"\u0082\u0084\u0001\u0000\u0000\u0000\u0083\u0080\u0001\u0000\u0000\u0000"+
		"\u0083\u0082\u0001\u0000\u0000\u0000\u0084\u008a\u0001\u0000\u0000\u0000"+
		"\u0085\u0086\n\u0002\u0000\u0000\u0086\u0087\u0005\f\u0000\u0000\u0087"+
		"\u0089\u0003\u001e\u000f\u0000\u0088\u0085\u0001\u0000\u0000\u0000\u0089"+
		"\u008c\u0001\u0000\u0000\u0000\u008a\u0088\u0001\u0000\u0000\u0000\u008a"+
		"\u008b\u0001\u0000\u0000\u0000\u008b\u001d\u0001\u0000\u0000\u0000\u008c"+
		"\u008a\u0001\u0000\u0000\u0000\u008d\u0091\u0005\u0017\u0000\u0000\u008e"+
		"\u0091\u0003&\u0013\u0000\u008f\u0091\u0003\u001a\r\u0000\u0090\u008d"+
		"\u0001\u0000\u0000\u0000\u0090\u008e\u0001\u0000\u0000\u0000\u0090\u008f"+
		"\u0001\u0000\u0000\u0000\u0091\u001f\u0001\u0000\u0000\u0000\u0092\u0096"+
		"\u0005\u0003\u0000\u0000\u0093\u0097\u0005\u0017\u0000\u0000\u0094\u0097"+
		"\u0003&\u0013\u0000\u0095\u0097\u0003\u001a\r\u0000\u0096\u0093\u0001"+
		"\u0000\u0000\u0000\u0096\u0094\u0001\u0000\u0000\u0000\u0096\u0095\u0001"+
		"\u0000\u0000\u0000\u0097\u0098\u0001\u0000\u0000\u0000\u0098\u0099\u0005"+
		"\u000b\u0000\u0000\u0099!\u0001\u0000\u0000\u0000\u009a\u009b\u0007\u0001"+
		"\u0000\u0000\u009b#\u0001\u0000\u0000\u0000\u009c\u009d\u0005\u0017\u0000"+
		"\u0000\u009d\u009e\u0005\n\u0000\u0000\u009e\u009f\u0005\b\u0000\u0000"+
		"\u009f\u00a0\u0005\u000b\u0000\u0000\u00a0\u00a1\u0006\u0012\uffff\uffff"+
		"\u0000\u00a1%\u0001\u0000\u0000\u0000\u00a2\u00a3\u0007\u0002\u0000\u0000"+
		"\u00a3\'\u0001\u0000\u0000\u0000\t-3?ls\u0083\u008a\u0090\u0096";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}