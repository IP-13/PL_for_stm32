// Generated from /home/ivan/IdeaProjects/PL_for_stm32/compiler/src/main/java/com/ip13/antlr/plClab.g4 by ANTLR 4.12.0
package com.ip13.antlr;

    import com.ip13.compiler.SuperClass;
    import com.ip13.compiler.ByteCodeCommands;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class plClabParser extends Parser {
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
		RULE_from_cycle = 4, RULE_step = 5, RULE_upper_border = 6, RULE_lower_border = 7, 
		RULE_if_operator = 8, RULE_bool_expr = 9, RULE_func_def = 10, RULE_return_type = 11, 
		RULE_return_value = 12, RULE_func_params = 13, RULE_func_param = 14, RULE_func_call = 15, 
		RULE_func_args = 16, RULE_func_arg = 17, RULE_var_def = 18, RULE_literal = 19;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "statements", "statement", "entry_point", "from_cycle", "step", 
			"upper_border", "lower_border", "if_operator", "bool_expr", "func_def", 
			"return_type", "return_value", "func_params", "func_param", "func_call", 
			"func_args", "func_arg", "var_def", "literal"
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
	public String getGrammarFileName() { return "plClab.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public plClabParser(TokenStream input) {
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
			if ( listener instanceof plClabListener ) ((plClabListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof plClabListener ) ((plClabListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof plClabVisitor ) return ((plClabVisitor<? extends T>)visitor).visitProgram(this);
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
			SuperClass.program();
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
			if ( listener instanceof plClabListener ) ((plClabListener)listener).enterStatements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof plClabListener ) ((plClabListener)listener).exitStatements(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof plClabVisitor ) return ((plClabVisitor<? extends T>)visitor).visitStatements(this);
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
			setState(46);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
			case 1:
				{
				setState(44);
				statement();
				}
				break;
			case 2:
				{
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(52);
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
					setState(48);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(49);
					statement();
					}
					} 
				}
				setState(54);
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
		public TerminalNode SEMICOLON() { return getToken(plClabParser.SEMICOLON, 0); }
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
			if ( listener instanceof plClabListener ) ((plClabListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof plClabListener ) ((plClabListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof plClabVisitor ) return ((plClabVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_statement);
		try {
			setState(64);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(55);
				func_call();
				setState(56);
				match(SEMICOLON);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(58);
				var_def();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(59);
				from_cycle();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(60);
				if_operator();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(61);
				func_def();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(62);
				entry_point();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(63);
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
		public TerminalNode ENTRY_POINT() { return getToken(plClabParser.ENTRY_POINT, 0); }
		public Entry_pointContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_entry_point; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof plClabListener ) ((plClabListener)listener).enterEntry_point(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof plClabListener ) ((plClabListener)listener).exitEntry_point(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof plClabVisitor ) return ((plClabVisitor<? extends T>)visitor).visitEntry_point(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Entry_pointContext entry_point() throws RecognitionException {
		Entry_pointContext _localctx = new Entry_pointContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_entry_point);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
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
		public TerminalNode FROM() { return getToken(plClabParser.FROM, 0); }
		public Lower_borderContext lower_border() {
			return getRuleContext(Lower_borderContext.class,0);
		}
		public TerminalNode TO() { return getToken(plClabParser.TO, 0); }
		public Upper_borderContext upper_border() {
			return getRuleContext(Upper_borderContext.class,0);
		}
		public TerminalNode WITH() { return getToken(plClabParser.WITH, 0); }
		public StepContext step() {
			return getRuleContext(StepContext.class,0);
		}
		public TerminalNode COLON() { return getToken(plClabParser.COLON, 0); }
		public TerminalNode START() { return getToken(plClabParser.START, 0); }
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public TerminalNode FINISH() { return getToken(plClabParser.FINISH, 0); }
		public From_cycleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_from_cycle; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof plClabListener ) ((plClabListener)listener).enterFrom_cycle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof plClabListener ) ((plClabListener)listener).exitFrom_cycle(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof plClabVisitor ) return ((plClabVisitor<? extends T>)visitor).visitFrom_cycle(this);
			else return visitor.visitChildren(this);
		}
	}

	public final From_cycleContext from_cycle() throws RecognitionException {
		From_cycleContext _localctx = new From_cycleContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_from_cycle);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			match(FROM);
			setState(70);
			lower_border();
			setState(71);
			match(TO);
			setState(72);
			upper_border();
			setState(73);
			match(WITH);
			setState(74);
			step();
			setState(75);
			match(COLON);
			setState(76);
			match(START);
			setState(77);
			statements(0);
			setState(78);
			match(FINISH);
			SuperClass.fromCycle();
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
		public Token INT;
		public Token VAR_NAME;
		public TerminalNode INT() { return getToken(plClabParser.INT, 0); }
		public TerminalNode VAR_NAME() { return getToken(plClabParser.VAR_NAME, 0); }
		public StepContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_step; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof plClabListener ) ((plClabListener)listener).enterStep(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof plClabListener ) ((plClabListener)listener).exitStep(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof plClabVisitor ) return ((plClabVisitor<? extends T>)visitor).visitStep(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StepContext step() throws RecognitionException {
		StepContext _localctx = new StepContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_step);
		try {
			setState(85);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(81);
				((StepContext)_localctx).INT = match(INT);
				SuperClass.stepInt((((StepContext)_localctx).INT!=null?((StepContext)_localctx).INT.getText():null));
				}
				break;
			case VAR_NAME:
				enterOuterAlt(_localctx, 2);
				{
				setState(83);
				((StepContext)_localctx).VAR_NAME = match(VAR_NAME);
				SuperClass.stepVar((((StepContext)_localctx).VAR_NAME!=null?((StepContext)_localctx).VAR_NAME.getText():null));
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
	public static class Upper_borderContext extends ParserRuleContext {
		public Token INT;
		public Token VAR_NAME;
		public TerminalNode INT() { return getToken(plClabParser.INT, 0); }
		public TerminalNode VAR_NAME() { return getToken(plClabParser.VAR_NAME, 0); }
		public Upper_borderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_upper_border; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof plClabListener ) ((plClabListener)listener).enterUpper_border(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof plClabListener ) ((plClabListener)listener).exitUpper_border(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof plClabVisitor ) return ((plClabVisitor<? extends T>)visitor).visitUpper_border(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Upper_borderContext upper_border() throws RecognitionException {
		Upper_borderContext _localctx = new Upper_borderContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_upper_border);
		try {
			setState(91);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(87);
				((Upper_borderContext)_localctx).INT = match(INT);
				SuperClass.upperBorderInt((((Upper_borderContext)_localctx).INT!=null?((Upper_borderContext)_localctx).INT.getText():null));
				}
				break;
			case VAR_NAME:
				enterOuterAlt(_localctx, 2);
				{
				setState(89);
				((Upper_borderContext)_localctx).VAR_NAME = match(VAR_NAME);
				SuperClass.upperBorderVar((((Upper_borderContext)_localctx).VAR_NAME!=null?((Upper_borderContext)_localctx).VAR_NAME.getText():null));
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
	public static class Lower_borderContext extends ParserRuleContext {
		public Token INT;
		public Token VAR_NAME;
		public TerminalNode INT() { return getToken(plClabParser.INT, 0); }
		public TerminalNode VAR_NAME() { return getToken(plClabParser.VAR_NAME, 0); }
		public Lower_borderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lower_border; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof plClabListener ) ((plClabListener)listener).enterLower_border(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof plClabListener ) ((plClabListener)listener).exitLower_border(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof plClabVisitor ) return ((plClabVisitor<? extends T>)visitor).visitLower_border(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Lower_borderContext lower_border() throws RecognitionException {
		Lower_borderContext _localctx = new Lower_borderContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_lower_border);
		try {
			setState(97);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(93);
				((Lower_borderContext)_localctx).INT = match(INT);
				SuperClass.lowerBorderInt((((Lower_borderContext)_localctx).INT!=null?((Lower_borderContext)_localctx).INT.getText():null));
				}
				break;
			case VAR_NAME:
				enterOuterAlt(_localctx, 2);
				{
				setState(95);
				((Lower_borderContext)_localctx).VAR_NAME = match(VAR_NAME);
				SuperClass.lowerBorderVar((((Lower_borderContext)_localctx).VAR_NAME!=null?((Lower_borderContext)_localctx).VAR_NAME.getText():null));
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
	public static class If_operatorContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(plClabParser.IF, 0); }
		public Bool_exprContext bool_expr() {
			return getRuleContext(Bool_exprContext.class,0);
		}
		public TerminalNode COLON() { return getToken(plClabParser.COLON, 0); }
		public TerminalNode START() { return getToken(plClabParser.START, 0); }
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public TerminalNode FINISH() { return getToken(plClabParser.FINISH, 0); }
		public If_operatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_operator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof plClabListener ) ((plClabListener)listener).enterIf_operator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof plClabListener ) ((plClabListener)listener).exitIf_operator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof plClabVisitor ) return ((plClabVisitor<? extends T>)visitor).visitIf_operator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final If_operatorContext if_operator() throws RecognitionException {
		If_operatorContext _localctx = new If_operatorContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_if_operator);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			match(IF);
			setState(100);
			bool_expr();
			setState(101);
			match(COLON);
			setState(102);
			match(START);
			setState(103);
			statements(0);
			setState(104);
			match(FINISH);
			SuperClass.ifOperator();
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
			if ( listener instanceof plClabListener ) ((plClabListener)listener).enterBool_expr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof plClabListener ) ((plClabListener)listener).exitBool_expr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof plClabVisitor ) return ((plClabVisitor<? extends T>)visitor).visitBool_expr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Bool_exprContext bool_expr() throws RecognitionException {
		Bool_exprContext _localctx = new Bool_exprContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_bool_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
			func_call();
			SuperClass.boolExpr();
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
		public TerminalNode FUNC_NAME() { return getToken(plClabParser.FUNC_NAME, 0); }
		public TerminalNode OPEN_BRACE() { return getToken(plClabParser.OPEN_BRACE, 0); }
		public Func_paramsContext func_params() {
			return getRuleContext(Func_paramsContext.class,0);
		}
		public TerminalNode CLOSE_BRACE() { return getToken(plClabParser.CLOSE_BRACE, 0); }
		public TerminalNode COLON() { return getToken(plClabParser.COLON, 0); }
		public Return_typeContext return_type() {
			return getRuleContext(Return_typeContext.class,0);
		}
		public TerminalNode START() { return getToken(plClabParser.START, 0); }
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public TerminalNode FINISH() { return getToken(plClabParser.FINISH, 0); }
		public Func_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_def; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof plClabListener ) ((plClabListener)listener).enterFunc_def(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof plClabListener ) ((plClabListener)listener).exitFunc_def(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof plClabVisitor ) return ((plClabVisitor<? extends T>)visitor).visitFunc_def(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Func_defContext func_def() throws RecognitionException {
		Func_defContext _localctx = new Func_defContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_func_def);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			((Func_defContext)_localctx).FUNC_NAME = match(FUNC_NAME);
			setState(111);
			match(OPEN_BRACE);
			setState(112);
			func_params(0);
			setState(113);
			match(CLOSE_BRACE);
			setState(114);
			match(COLON);
			setState(115);
			((Func_defContext)_localctx).return_type = return_type();
			setState(116);
			match(START);
			setState(117);
			statements(0);
			setState(118);
			match(FINISH);
			SuperClass.funcDef((((Func_defContext)_localctx).FUNC_NAME!=null?((Func_defContext)_localctx).FUNC_NAME.getText():null), (((Func_defContext)_localctx).return_type!=null?_input.getText(((Func_defContext)_localctx).return_type.start,((Func_defContext)_localctx).return_type.stop):null), (((Func_defContext)_localctx).FUNC_NAME!=null?((Func_defContext)_localctx).FUNC_NAME.getLine():0));
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
		public TerminalNode TYPE() { return getToken(plClabParser.TYPE, 0); }
		public TerminalNode VOID() { return getToken(plClabParser.VOID, 0); }
		public Return_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof plClabListener ) ((plClabListener)listener).enterReturn_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof plClabListener ) ((plClabListener)listener).exitReturn_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof plClabVisitor ) return ((plClabVisitor<? extends T>)visitor).visitReturn_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Return_typeContext return_type() throws RecognitionException {
		Return_typeContext _localctx = new Return_typeContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_return_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
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
	public static class Return_valueContext extends ParserRuleContext {
		public Token VAR_NAME;
		public TerminalNode RETURN() { return getToken(plClabParser.RETURN, 0); }
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(plClabParser.SEMICOLON, 0); }
		public TerminalNode VAR_NAME() { return getToken(plClabParser.VAR_NAME, 0); }
		public Func_callContext func_call() {
			return getRuleContext(Func_callContext.class,0);
		}
		public Return_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_return_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof plClabListener ) ((plClabListener)listener).enterReturn_value(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof plClabListener ) ((plClabListener)listener).exitReturn_value(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof plClabVisitor ) return ((plClabVisitor<? extends T>)visitor).visitReturn_value(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Return_valueContext return_value() throws RecognitionException {
		Return_valueContext _localctx = new Return_valueContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_return_value);
		try {
			setState(137);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(123);
				match(RETURN);
				setState(124);
				literal();
				setState(125);
				match(SEMICOLON);
				SuperClass.returnValueLiteral();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(128);
				match(RETURN);
				setState(129);
				((Return_valueContext)_localctx).VAR_NAME = match(VAR_NAME);
				setState(130);
				match(SEMICOLON);
				SuperClass.returnValueVariable((((Return_valueContext)_localctx).VAR_NAME!=null?((Return_valueContext)_localctx).VAR_NAME.getText():null));
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(132);
				match(RETURN);
				setState(133);
				func_call();
				setState(134);
				match(SEMICOLON);
				SuperClass.returnValueFuncCall();
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
	public static class Func_paramsContext extends ParserRuleContext {
		public Func_paramContext func_param() {
			return getRuleContext(Func_paramContext.class,0);
		}
		public Func_paramsContext func_params() {
			return getRuleContext(Func_paramsContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(plClabParser.COMMA, 0); }
		public Func_paramsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_params; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof plClabListener ) ((plClabListener)listener).enterFunc_params(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof plClabListener ) ((plClabListener)listener).exitFunc_params(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof plClabVisitor ) return ((plClabVisitor<? extends T>)visitor).visitFunc_params(this);
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
		int _startState = 26;
		enterRecursionRule(_localctx, 26, RULE_func_params, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				setState(140);
				func_param();
				}
				break;
			case 2:
				{
				SuperClass.funcParams();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(149);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Func_paramsContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_func_params);
					setState(144);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(145);
					match(COMMA);
					setState(146);
					func_param();
					}
					} 
				}
				setState(151);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
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
		public TerminalNode VAR_NAME() { return getToken(plClabParser.VAR_NAME, 0); }
		public TerminalNode COLON() { return getToken(plClabParser.COLON, 0); }
		public TerminalNode TYPE() { return getToken(plClabParser.TYPE, 0); }
		public Func_paramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof plClabListener ) ((plClabListener)listener).enterFunc_param(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof plClabListener ) ((plClabListener)listener).exitFunc_param(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof plClabVisitor ) return ((plClabVisitor<? extends T>)visitor).visitFunc_param(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Func_paramContext func_param() throws RecognitionException {
		Func_paramContext _localctx = new Func_paramContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_func_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
			((Func_paramContext)_localctx).VAR_NAME = match(VAR_NAME);
			setState(153);
			match(COLON);
			setState(154);
			((Func_paramContext)_localctx).TYPE = match(TYPE);
			SuperClass.funcParam((((Func_paramContext)_localctx).VAR_NAME!=null?((Func_paramContext)_localctx).VAR_NAME.getText():null), (((Func_paramContext)_localctx).TYPE!=null?((Func_paramContext)_localctx).TYPE.getText():null), (((Func_paramContext)_localctx).VAR_NAME!=null?((Func_paramContext)_localctx).VAR_NAME.getLine():0));
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
		public Token FUNC_NAME;
		public TerminalNode FUNC_NAME() { return getToken(plClabParser.FUNC_NAME, 0); }
		public TerminalNode OPEN_BRACE() { return getToken(plClabParser.OPEN_BRACE, 0); }
		public Func_argsContext func_args() {
			return getRuleContext(Func_argsContext.class,0);
		}
		public TerminalNode CLOSE_BRACE() { return getToken(plClabParser.CLOSE_BRACE, 0); }
		public Func_callContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_call; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof plClabListener ) ((plClabListener)listener).enterFunc_call(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof plClabListener ) ((plClabListener)listener).exitFunc_call(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof plClabVisitor ) return ((plClabVisitor<? extends T>)visitor).visitFunc_call(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Func_callContext func_call() throws RecognitionException {
		Func_callContext _localctx = new Func_callContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_func_call);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157);
			((Func_callContext)_localctx).FUNC_NAME = match(FUNC_NAME);
			setState(158);
			match(OPEN_BRACE);
			setState(159);
			func_args(0);
			setState(160);
			match(CLOSE_BRACE);
			SuperClass.funcCall((((Func_callContext)_localctx).FUNC_NAME!=null?((Func_callContext)_localctx).FUNC_NAME.getText():null), (((Func_callContext)_localctx).FUNC_NAME!=null?((Func_callContext)_localctx).FUNC_NAME.getLine():0));
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
		public TerminalNode COMMA() { return getToken(plClabParser.COMMA, 0); }
		public Func_argsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_args; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof plClabListener ) ((plClabListener)listener).enterFunc_args(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof plClabListener ) ((plClabListener)listener).exitFunc_args(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof plClabVisitor ) return ((plClabVisitor<? extends T>)visitor).visitFunc_args(this);
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
		int _startState = 32;
		enterRecursionRule(_localctx, 32, RULE_func_args, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(166);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
			case 1:
				{
				setState(164);
				func_arg();
				}
				break;
			case 2:
				{
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(173);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new Func_argsContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_func_args);
					setState(168);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(169);
					match(COMMA);
					setState(170);
					func_arg();
					}
					} 
				}
				setState(175);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
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
		public Token VAR_NAME;
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public TerminalNode VAR_NAME() { return getToken(plClabParser.VAR_NAME, 0); }
		public Func_callContext func_call() {
			return getRuleContext(Func_callContext.class,0);
		}
		public Func_argContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func_arg; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof plClabListener ) ((plClabListener)listener).enterFunc_arg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof plClabListener ) ((plClabListener)listener).exitFunc_arg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof plClabVisitor ) return ((plClabVisitor<? extends T>)visitor).visitFunc_arg(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Func_argContext func_arg() throws RecognitionException {
		Func_argContext _localctx = new Func_argContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_func_arg);
		try {
			setState(184);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOL:
			case INT:
			case FLOAT:
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(176);
				literal();
				SuperClass.funcArgLiteral();
				}
				break;
			case VAR_NAME:
				enterOuterAlt(_localctx, 2);
				{
				setState(179);
				((Func_argContext)_localctx).VAR_NAME = match(VAR_NAME);
				SuperClass.funcArgVariable((((Func_argContext)_localctx).VAR_NAME!=null?((Func_argContext)_localctx).VAR_NAME.getText():null));
				}
				break;
			case FUNC_NAME:
				enterOuterAlt(_localctx, 3);
				{
				setState(181);
				func_call();
				SuperClass.funcArgFuncCall();
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
	public static class Var_defContext extends ParserRuleContext {
		public Token VAR_NAME;
		public Token TYPE;
		public TerminalNode VAR_NAME() { return getToken(plClabParser.VAR_NAME, 0); }
		public TerminalNode COLON() { return getToken(plClabParser.COLON, 0); }
		public TerminalNode TYPE() { return getToken(plClabParser.TYPE, 0); }
		public TerminalNode SEMICOLON() { return getToken(plClabParser.SEMICOLON, 0); }
		public Var_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var_def; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof plClabListener ) ((plClabListener)listener).enterVar_def(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof plClabListener ) ((plClabListener)listener).exitVar_def(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof plClabVisitor ) return ((plClabVisitor<? extends T>)visitor).visitVar_def(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Var_defContext var_def() throws RecognitionException {
		Var_defContext _localctx = new Var_defContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_var_def);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(186);
			((Var_defContext)_localctx).VAR_NAME = match(VAR_NAME);
			setState(187);
			match(COLON);
			setState(188);
			((Var_defContext)_localctx).TYPE = match(TYPE);
			setState(189);
			match(SEMICOLON);
			SuperClass.varDef((((Var_defContext)_localctx).VAR_NAME!=null?((Var_defContext)_localctx).VAR_NAME.getText():null), (((Var_defContext)_localctx).TYPE!=null?((Var_defContext)_localctx).TYPE.getText():null), (((Var_defContext)_localctx).VAR_NAME!=null?((Var_defContext)_localctx).VAR_NAME.getLine():0));
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
		public Token BOOL;
		public Token INT;
		public Token FLOAT;
		public Token STRING;
		public TerminalNode BOOL() { return getToken(plClabParser.BOOL, 0); }
		public TerminalNode INT() { return getToken(plClabParser.INT, 0); }
		public TerminalNode FLOAT() { return getToken(plClabParser.FLOAT, 0); }
		public TerminalNode STRING() { return getToken(plClabParser.STRING, 0); }
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof plClabListener ) ((plClabListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof plClabListener ) ((plClabListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof plClabVisitor ) return ((plClabVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_literal);
		try {
			setState(200);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOL:
				enterOuterAlt(_localctx, 1);
				{
				setState(192);
				((LiteralContext)_localctx).BOOL = match(BOOL);
				SuperClass.literal((((LiteralContext)_localctx).BOOL!=null?((LiteralContext)_localctx).BOOL.getText():null), ByteCodeCommands.BOOL, (((LiteralContext)_localctx).BOOL!=null?((LiteralContext)_localctx).BOOL.getLine():0));
				}
				break;
			case INT:
				enterOuterAlt(_localctx, 2);
				{
				setState(194);
				((LiteralContext)_localctx).INT = match(INT);
				SuperClass.literal((((LiteralContext)_localctx).INT!=null?((LiteralContext)_localctx).INT.getText():null), ByteCodeCommands.INT, (((LiteralContext)_localctx).INT!=null?((LiteralContext)_localctx).INT.getLine():0));
				}
				break;
			case FLOAT:
				enterOuterAlt(_localctx, 3);
				{
				setState(196);
				((LiteralContext)_localctx).FLOAT = match(FLOAT);
				SuperClass.literal((((LiteralContext)_localctx).FLOAT!=null?((LiteralContext)_localctx).FLOAT.getText():null), ByteCodeCommands.FLT, (((LiteralContext)_localctx).FLOAT!=null?((LiteralContext)_localctx).FLOAT.getLine():0));
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 4);
				{
				setState(198);
				((LiteralContext)_localctx).STRING = match(STRING);
				SuperClass.literal((((LiteralContext)_localctx).STRING!=null?((LiteralContext)_localctx).STRING.getText():null), ByteCodeCommands.STR, (((LiteralContext)_localctx).STRING!=null?((LiteralContext)_localctx).STRING.getLine():0));
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 1:
			return statements_sempred((StatementsContext)_localctx, predIndex);
		case 13:
			return func_params_sempred((Func_paramsContext)_localctx, predIndex);
		case 16:
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
		"\u0004\u0001\u001a\u00cb\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007"+
		"\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007"+
		"\u0012\u0002\u0013\u0007\u0013\u0001\u0000\u0001\u0000\u0001\u0000\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0003\u0001/\b\u0001\u0001\u0001\u0001"+
		"\u0001\u0005\u00013\b\u0001\n\u0001\f\u00016\t\u0001\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0003\u0002A\b\u0002\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005V\b"+
		"\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006\\\b"+
		"\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007b\b"+
		"\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b"+
		"\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u008a\b\f\u0001\r\u0001\r\u0001"+
		"\r\u0003\r\u008f\b\r\u0001\r\u0001\r\u0001\r\u0005\r\u0094\b\r\n\r\f\r"+
		"\u0097\t\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0003\u0010\u00a7\b\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0005\u0010\u00ac\b\u0010\n\u0010\f\u0010\u00af"+
		"\t\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0003\u0011\u00b9\b\u0011\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0003\u0013\u00c9\b\u0013\u0001\u0013\u0000\u0003\u0002\u001a "+
		"\u0014\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018"+
		"\u001a\u001c\u001e \"$&\u0000\u0001\u0002\u0000\b\b\u0015\u0015\u00cc"+
		"\u0000(\u0001\u0000\u0000\u0000\u0002.\u0001\u0000\u0000\u0000\u0004@"+
		"\u0001\u0000\u0000\u0000\u0006B\u0001\u0000\u0000\u0000\bE\u0001\u0000"+
		"\u0000\u0000\nU\u0001\u0000\u0000\u0000\f[\u0001\u0000\u0000\u0000\u000e"+
		"a\u0001\u0000\u0000\u0000\u0010c\u0001\u0000\u0000\u0000\u0012k\u0001"+
		"\u0000\u0000\u0000\u0014n\u0001\u0000\u0000\u0000\u0016y\u0001\u0000\u0000"+
		"\u0000\u0018\u0089\u0001\u0000\u0000\u0000\u001a\u008e\u0001\u0000\u0000"+
		"\u0000\u001c\u0098\u0001\u0000\u0000\u0000\u001e\u009d\u0001\u0000\u0000"+
		"\u0000 \u00a6\u0001\u0000\u0000\u0000\"\u00b8\u0001\u0000\u0000\u0000"+
		"$\u00ba\u0001\u0000\u0000\u0000&\u00c8\u0001\u0000\u0000\u0000()\u0003"+
		"\u0002\u0001\u0000)*\u0006\u0000\uffff\uffff\u0000*\u0001\u0001\u0000"+
		"\u0000\u0000+,\u0006\u0001\uffff\uffff\u0000,/\u0003\u0004\u0002\u0000"+
		"-/\u0001\u0000\u0000\u0000.+\u0001\u0000\u0000\u0000.-\u0001\u0000\u0000"+
		"\u0000/4\u0001\u0000\u0000\u000001\n\u0002\u0000\u000013\u0003\u0004\u0002"+
		"\u000020\u0001\u0000\u0000\u000036\u0001\u0000\u0000\u000042\u0001\u0000"+
		"\u0000\u000045\u0001\u0000\u0000\u00005\u0003\u0001\u0000\u0000\u0000"+
		"64\u0001\u0000\u0000\u000078\u0003\u001e\u000f\u000089\u0005\u000b\u0000"+
		"\u00009A\u0001\u0000\u0000\u0000:A\u0003$\u0012\u0000;A\u0003\b\u0004"+
		"\u0000<A\u0003\u0010\b\u0000=A\u0003\u0014\n\u0000>A\u0003\u0006\u0003"+
		"\u0000?A\u0003\u0018\f\u0000@7\u0001\u0000\u0000\u0000@:\u0001\u0000\u0000"+
		"\u0000@;\u0001\u0000\u0000\u0000@<\u0001\u0000\u0000\u0000@=\u0001\u0000"+
		"\u0000\u0000@>\u0001\u0000\u0000\u0000@?\u0001\u0000\u0000\u0000A\u0005"+
		"\u0001\u0000\u0000\u0000BC\u0005\t\u0000\u0000CD\u0006\u0003\uffff\uffff"+
		"\u0000D\u0007\u0001\u0000\u0000\u0000EF\u0005\u0004\u0000\u0000FG\u0003"+
		"\u000e\u0007\u0000GH\u0005\u0005\u0000\u0000HI\u0003\f\u0006\u0000IJ\u0005"+
		"\u0006\u0000\u0000JK\u0003\n\u0005\u0000KL\u0005\n\u0000\u0000LM\u0005"+
		"\u0001\u0000\u0000MN\u0003\u0002\u0001\u0000NO\u0005\u0002\u0000\u0000"+
		"OP\u0006\u0004\uffff\uffff\u0000P\t\u0001\u0000\u0000\u0000QR\u0005\u0012"+
		"\u0000\u0000RV\u0006\u0005\uffff\uffff\u0000ST\u0005\u0017\u0000\u0000"+
		"TV\u0006\u0005\uffff\uffff\u0000UQ\u0001\u0000\u0000\u0000US\u0001\u0000"+
		"\u0000\u0000V\u000b\u0001\u0000\u0000\u0000WX\u0005\u0012\u0000\u0000"+
		"X\\\u0006\u0006\uffff\uffff\u0000YZ\u0005\u0017\u0000\u0000Z\\\u0006\u0006"+
		"\uffff\uffff\u0000[W\u0001\u0000\u0000\u0000[Y\u0001\u0000\u0000\u0000"+
		"\\\r\u0001\u0000\u0000\u0000]^\u0005\u0012\u0000\u0000^b\u0006\u0007\uffff"+
		"\uffff\u0000_`\u0005\u0017\u0000\u0000`b\u0006\u0007\uffff\uffff\u0000"+
		"a]\u0001\u0000\u0000\u0000a_\u0001\u0000\u0000\u0000b\u000f\u0001\u0000"+
		"\u0000\u0000cd\u0005\u0007\u0000\u0000de\u0003\u0012\t\u0000ef\u0005\n"+
		"\u0000\u0000fg\u0005\u0001\u0000\u0000gh\u0003\u0002\u0001\u0000hi\u0005"+
		"\u0002\u0000\u0000ij\u0006\b\uffff\uffff\u0000j\u0011\u0001\u0000\u0000"+
		"\u0000kl\u0003\u001e\u000f\u0000lm\u0006\t\uffff\uffff\u0000m\u0013\u0001"+
		"\u0000\u0000\u0000no\u0005\u0016\u0000\u0000op\u0005\u000e\u0000\u0000"+
		"pq\u0003\u001a\r\u0000qr\u0005\u000f\u0000\u0000rs\u0005\n\u0000\u0000"+
		"st\u0003\u0016\u000b\u0000tu\u0005\u0001\u0000\u0000uv\u0003\u0002\u0001"+
		"\u0000vw\u0005\u0002\u0000\u0000wx\u0006\n\uffff\uffff\u0000x\u0015\u0001"+
		"\u0000\u0000\u0000yz\u0007\u0000\u0000\u0000z\u0017\u0001\u0000\u0000"+
		"\u0000{|\u0005\u0003\u0000\u0000|}\u0003&\u0013\u0000}~\u0005\u000b\u0000"+
		"\u0000~\u007f\u0006\f\uffff\uffff\u0000\u007f\u008a\u0001\u0000\u0000"+
		"\u0000\u0080\u0081\u0005\u0003\u0000\u0000\u0081\u0082\u0005\u0017\u0000"+
		"\u0000\u0082\u0083\u0005\u000b\u0000\u0000\u0083\u008a\u0006\f\uffff\uffff"+
		"\u0000\u0084\u0085\u0005\u0003\u0000\u0000\u0085\u0086\u0003\u001e\u000f"+
		"\u0000\u0086\u0087\u0005\u000b\u0000\u0000\u0087\u0088\u0006\f\uffff\uffff"+
		"\u0000\u0088\u008a\u0001\u0000\u0000\u0000\u0089{\u0001\u0000\u0000\u0000"+
		"\u0089\u0080\u0001\u0000\u0000\u0000\u0089\u0084\u0001\u0000\u0000\u0000"+
		"\u008a\u0019\u0001\u0000\u0000\u0000\u008b\u008c\u0006\r\uffff\uffff\u0000"+
		"\u008c\u008f\u0003\u001c\u000e\u0000\u008d\u008f\u0006\r\uffff\uffff\u0000"+
		"\u008e\u008b\u0001\u0000\u0000\u0000\u008e\u008d\u0001\u0000\u0000\u0000"+
		"\u008f\u0095\u0001\u0000\u0000\u0000\u0090\u0091\n\u0002\u0000\u0000\u0091"+
		"\u0092\u0005\f\u0000\u0000\u0092\u0094\u0003\u001c\u000e\u0000\u0093\u0090"+
		"\u0001\u0000\u0000\u0000\u0094\u0097\u0001\u0000\u0000\u0000\u0095\u0093"+
		"\u0001\u0000\u0000\u0000\u0095\u0096\u0001\u0000\u0000\u0000\u0096\u001b"+
		"\u0001\u0000\u0000\u0000\u0097\u0095\u0001\u0000\u0000\u0000\u0098\u0099"+
		"\u0005\u0017\u0000\u0000\u0099\u009a\u0005\n\u0000\u0000\u009a\u009b\u0005"+
		"\b\u0000\u0000\u009b\u009c\u0006\u000e\uffff\uffff\u0000\u009c\u001d\u0001"+
		"\u0000\u0000\u0000\u009d\u009e\u0005\u0016\u0000\u0000\u009e\u009f\u0005"+
		"\u000e\u0000\u0000\u009f\u00a0\u0003 \u0010\u0000\u00a0\u00a1\u0005\u000f"+
		"\u0000\u0000\u00a1\u00a2\u0006\u000f\uffff\uffff\u0000\u00a2\u001f\u0001"+
		"\u0000\u0000\u0000\u00a3\u00a4\u0006\u0010\uffff\uffff\u0000\u00a4\u00a7"+
		"\u0003\"\u0011\u0000\u00a5\u00a7\u0001\u0000\u0000\u0000\u00a6\u00a3\u0001"+
		"\u0000\u0000\u0000\u00a6\u00a5\u0001\u0000\u0000\u0000\u00a7\u00ad\u0001"+
		"\u0000\u0000\u0000\u00a8\u00a9\n\u0002\u0000\u0000\u00a9\u00aa\u0005\f"+
		"\u0000\u0000\u00aa\u00ac\u0003\"\u0011\u0000\u00ab\u00a8\u0001\u0000\u0000"+
		"\u0000\u00ac\u00af\u0001\u0000\u0000\u0000\u00ad\u00ab\u0001\u0000\u0000"+
		"\u0000\u00ad\u00ae\u0001\u0000\u0000\u0000\u00ae!\u0001\u0000\u0000\u0000"+
		"\u00af\u00ad\u0001\u0000\u0000\u0000\u00b0\u00b1\u0003&\u0013\u0000\u00b1"+
		"\u00b2\u0006\u0011\uffff\uffff\u0000\u00b2\u00b9\u0001\u0000\u0000\u0000"+
		"\u00b3\u00b4\u0005\u0017\u0000\u0000\u00b4\u00b9\u0006\u0011\uffff\uffff"+
		"\u0000\u00b5\u00b6\u0003\u001e\u000f\u0000\u00b6\u00b7\u0006\u0011\uffff"+
		"\uffff\u0000\u00b7\u00b9\u0001\u0000\u0000\u0000\u00b8\u00b0\u0001\u0000"+
		"\u0000\u0000\u00b8\u00b3\u0001\u0000\u0000\u0000\u00b8\u00b5\u0001\u0000"+
		"\u0000\u0000\u00b9#\u0001\u0000\u0000\u0000\u00ba\u00bb\u0005\u0017\u0000"+
		"\u0000\u00bb\u00bc\u0005\n\u0000\u0000\u00bc\u00bd\u0005\b\u0000\u0000"+
		"\u00bd\u00be\u0005\u000b\u0000\u0000\u00be\u00bf\u0006\u0012\uffff\uffff"+
		"\u0000\u00bf%\u0001\u0000\u0000\u0000\u00c0\u00c1\u0005\u0011\u0000\u0000"+
		"\u00c1\u00c9\u0006\u0013\uffff\uffff\u0000\u00c2\u00c3\u0005\u0012\u0000"+
		"\u0000\u00c3\u00c9\u0006\u0013\uffff\uffff\u0000\u00c4\u00c5\u0005\u0013"+
		"\u0000\u0000\u00c5\u00c9\u0006\u0013\uffff\uffff\u0000\u00c6\u00c7\u0005"+
		"\u0014\u0000\u0000\u00c7\u00c9\u0006\u0013\uffff\uffff\u0000\u00c8\u00c0"+
		"\u0001\u0000\u0000\u0000\u00c8\u00c2\u0001\u0000\u0000\u0000\u00c8\u00c4"+
		"\u0001\u0000\u0000\u0000\u00c8\u00c6\u0001\u0000\u0000\u0000\u00c9\'\u0001"+
		"\u0000\u0000\u0000\r.4@U[a\u0089\u008e\u0095\u00a6\u00ad\u00b8\u00c8";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}