// Generated from C:/Users/diego/OneDrive - Universidad Tecnologica Centroamericana/Trabajos Diego Unitec/CLASES Q3 2024/COMPILADORES 1/MiniPascal_Compiler/src/antlr4/MiniPascal.g4 by ANTLR 4.13.1
package antlr4;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class MiniPascalParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		BEGIN=1, END=2, WS=3, COMMENT=4, INTEGER=5, BOOLEAN=6, CHAR=7, STRING=8, 
		ASSIGN=9, PLUS=10, MINUS=11, DIV=12, MULT=13, MOD=14, AND=15, NOT=16, 
		OR=17, GT=18, LT=19, LE=20, GE=21, EQUAL=22, NOT_EQUAL=23, L_PAREN=24, 
		R_PAREN=25, L_BRACK=26, R_BRACK=27, COMMA=28, SEMICOLON=29, COLON=30, 
		DOT=31, DOUBLE_DOT=32, PROGRAM=33, FUNCTION=34, PROCEDURE=35, IF=36, THEN=37, 
		ELSE=38, UNTIL=39, WHILE=40, FOR=41, REPEAT=42, TO=43, DO=44, DOWNTO=45, 
		VAR=46, ARRAY=47, OF=48, INTEGER_=49, BOOLEAN_=50, CHAR_=51, STRING_=52, 
		NIL=53, CONST=54, CHR=55, AT=56, READLN=57, READ=58, WRITELN=59, WRITE=60, 
		ID=61, TRUE=62, FALSE=63;
	public static final int
		RULE_program = 0, RULE_programHeading = 1, RULE_identifier = 2, RULE_block = 3, 
		RULE_constantDefinitionPart = 4, RULE_constantDefinition = 5, RULE_constantChr = 6, 
		RULE_constant = 7, RULE_varType = 8, RULE_arrayType = 9, RULE_arrayValue = 10, 
		RULE_indexRanges = 11, RULE_indexRange = 12, RULE_stringR_ = 13, RULE_charR_ = 14, 
		RULE_integerR_ = 15, RULE_unsignedNumber = 16, RULE_unsignedInteger = 17, 
		RULE_sign = 18, RULE_bool_ = 19, RULE_string = 20, RULE_char = 21, RULE_boolean = 22, 
		RULE_integer = 23, RULE_type_ = 24, RULE_simpleType = 25, RULE_scalarType = 26, 
		RULE_subrangeType = 27, RULE_typeIdentifier = 28, RULE_variableDeclarationPart = 29, 
		RULE_variableDeclaration = 30, RULE_procedureAndFunctionDeclarationPart = 31, 
		RULE_procedureOrFunctionDeclaration = 32, RULE_formalParameterList = 33, 
		RULE_formalParameterSection = 34, RULE_parameterGroup = 35, RULE_identifierList = 36, 
		RULE_functionDeclaration = 37, RULE_procedureDeclaration = 38, RULE_statement = 39, 
		RULE_writeStatement = 40, RULE_write = 41, RULE_writeParam2 = 42, RULE_writeParam = 43, 
		RULE_varValue = 44, RULE_readStatement = 45, RULE_read = 46, RULE_readParam = 47, 
		RULE_unlabelledStatement = 48, RULE_simpleStatement = 49, RULE_assignmentStatement = 50, 
		RULE_variable = 51, RULE_expressionList = 52, RULE_expression = 53, RULE_relationaloperator = 54, 
		RULE_simpleExpression = 55, RULE_additiveoperator = 56, RULE_term = 57, 
		RULE_multiplicativeoperator = 58, RULE_signedFactor = 59, RULE_factor = 60, 
		RULE_unsignedConstant = 61, RULE_functionDesignator = 62, RULE_parameterList = 63, 
		RULE_actualParameter = 64, RULE_emptyStatement_ = 65, RULE_structuredStatement = 66, 
		RULE_compoundStatement = 67, RULE_statements = 68, RULE_conditionalStatement = 69, 
		RULE_ifStatement = 70, RULE_repetitiveStatement = 71, RULE_whileStatement = 72, 
		RULE_repeatStatement = 73, RULE_forStatement = 74, RULE_forList = 75, 
		RULE_initialValue = 76, RULE_finalValue = 77;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "programHeading", "identifier", "block", "constantDefinitionPart", 
			"constantDefinition", "constantChr", "constant", "varType", "arrayType", 
			"arrayValue", "indexRanges", "indexRange", "stringR_", "charR_", "integerR_", 
			"unsignedNumber", "unsignedInteger", "sign", "bool_", "string", "char", 
			"boolean", "integer", "type_", "simpleType", "scalarType", "subrangeType", 
			"typeIdentifier", "variableDeclarationPart", "variableDeclaration", "procedureAndFunctionDeclarationPart", 
			"procedureOrFunctionDeclaration", "formalParameterList", "formalParameterSection", 
			"parameterGroup", "identifierList", "functionDeclaration", "procedureDeclaration", 
			"statement", "writeStatement", "write", "writeParam2", "writeParam", 
			"varValue", "readStatement", "read", "readParam", "unlabelledStatement", 
			"simpleStatement", "assignmentStatement", "variable", "expressionList", 
			"expression", "relationaloperator", "simpleExpression", "additiveoperator", 
			"term", "multiplicativeoperator", "signedFactor", "factor", "unsignedConstant", 
			"functionDesignator", "parameterList", "actualParameter", "emptyStatement_", 
			"structuredStatement", "compoundStatement", "statements", "conditionalStatement", 
			"ifStatement", "repetitiveStatement", "whileStatement", "repeatStatement", 
			"forStatement", "forList", "initialValue", "finalValue"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'begin'", "'end'", null, null, null, null, null, null, "':='", 
			"'+'", "'-'", null, "'*'", "'mod'", "'and'", "'not'", "'or'", "'>'", 
			"'<'", "'<='", "'>='", "'='", "'<>'", "'('", "')'", "'['", "']'", "','", 
			"';'", "':'", "'.'", "'..'", "'program'", "'function'", "'procedure'", 
			"'if'", "'then'", "'else'", "'until'", "'while'", "'for'", "'repeat'", 
			"'to'", "'do'", "'downto'", "'var'", "'Array'", "'of'", "'INTEGER'", 
			"'BOOLEAN'", "'CHAR'", "'STRING'", "'NIL'", "'CONST'", "'CHR'", "'AT'", 
			"'READLN'", "'READ'", "'WRITELN'", "'WRITE'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "BEGIN", "END", "WS", "COMMENT", "INTEGER", "BOOLEAN", "CHAR", 
			"STRING", "ASSIGN", "PLUS", "MINUS", "DIV", "MULT", "MOD", "AND", "NOT", 
			"OR", "GT", "LT", "LE", "GE", "EQUAL", "NOT_EQUAL", "L_PAREN", "R_PAREN", 
			"L_BRACK", "R_BRACK", "COMMA", "SEMICOLON", "COLON", "DOT", "DOUBLE_DOT", 
			"PROGRAM", "FUNCTION", "PROCEDURE", "IF", "THEN", "ELSE", "UNTIL", "WHILE", 
			"FOR", "REPEAT", "TO", "DO", "DOWNTO", "VAR", "ARRAY", "OF", "INTEGER_", 
			"BOOLEAN_", "CHAR_", "STRING_", "NIL", "CONST", "CHR", "AT", "READLN", 
			"READ", "WRITELN", "WRITE", "ID", "TRUE", "FALSE"
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
	public String getGrammarFileName() { return "MiniPascal.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MiniPascalParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public ProgramHeadingContext programHeading() {
			return getRuleContext(ProgramHeadingContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode DOT() { return getToken(MiniPascalParser.DOT, 0); }
		public TerminalNode EOF() { return getToken(MiniPascalParser.EOF, 0); }
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			programHeading();
			setState(157);
			block();
			setState(158);
			match(DOT);
			setState(159);
			match(EOF);
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
	public static class ProgramHeadingContext extends ParserRuleContext {
		public TerminalNode PROGRAM() { return getToken(MiniPascalParser.PROGRAM, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(MiniPascalParser.SEMICOLON, 0); }
		public TerminalNode L_PAREN() { return getToken(MiniPascalParser.L_PAREN, 0); }
		public IdentifierListContext identifierList() {
			return getRuleContext(IdentifierListContext.class,0);
		}
		public TerminalNode R_PAREN() { return getToken(MiniPascalParser.R_PAREN, 0); }
		public ProgramHeadingContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_programHeading; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterProgramHeading(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitProgramHeading(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitProgramHeading(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramHeadingContext programHeading() throws RecognitionException {
		ProgramHeadingContext _localctx = new ProgramHeadingContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_programHeading);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			match(PROGRAM);
			setState(162);
			identifier();
			setState(167);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==L_PAREN) {
				{
				setState(163);
				match(L_PAREN);
				setState(164);
				identifierList();
				setState(165);
				match(R_PAREN);
				}
			}

			setState(169);
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
	public static class IdentifierContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(MiniPascalParser.ID, 0); }
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_identifier);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171);
			match(ID);
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
	public static class BlockContext extends ParserRuleContext {
		public CompoundStatementContext compoundStatement() {
			return getRuleContext(CompoundStatementContext.class,0);
		}
		public List<ConstantDefinitionPartContext> constantDefinitionPart() {
			return getRuleContexts(ConstantDefinitionPartContext.class);
		}
		public ConstantDefinitionPartContext constantDefinitionPart(int i) {
			return getRuleContext(ConstantDefinitionPartContext.class,i);
		}
		public List<VariableDeclarationPartContext> variableDeclarationPart() {
			return getRuleContexts(VariableDeclarationPartContext.class);
		}
		public VariableDeclarationPartContext variableDeclarationPart(int i) {
			return getRuleContext(VariableDeclarationPartContext.class,i);
		}
		public List<ProcedureAndFunctionDeclarationPartContext> procedureAndFunctionDeclarationPart() {
			return getRuleContexts(ProcedureAndFunctionDeclarationPartContext.class);
		}
		public ProcedureAndFunctionDeclarationPartContext procedureAndFunctionDeclarationPart(int i) {
			return getRuleContext(ProcedureAndFunctionDeclarationPartContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(178);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & 18084818793267200L) != 0)) {
				{
				setState(176);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case CONST:
					{
					setState(173);
					constantDefinitionPart();
					}
					break;
				case VAR:
					{
					setState(174);
					variableDeclarationPart();
					}
					break;
				case FUNCTION:
				case PROCEDURE:
					{
					setState(175);
					procedureAndFunctionDeclarationPart();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(180);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(181);
			compoundStatement();
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
	public static class ConstantDefinitionPartContext extends ParserRuleContext {
		public TerminalNode CONST() { return getToken(MiniPascalParser.CONST, 0); }
		public List<ConstantDefinitionContext> constantDefinition() {
			return getRuleContexts(ConstantDefinitionContext.class);
		}
		public ConstantDefinitionContext constantDefinition(int i) {
			return getRuleContext(ConstantDefinitionContext.class,i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(MiniPascalParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(MiniPascalParser.SEMICOLON, i);
		}
		public ConstantDefinitionPartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constantDefinitionPart; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterConstantDefinitionPart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitConstantDefinitionPart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitConstantDefinitionPart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantDefinitionPartContext constantDefinitionPart() throws RecognitionException {
		ConstantDefinitionPartContext _localctx = new ConstantDefinitionPartContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_constantDefinitionPart);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			match(CONST);
			setState(187); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(184);
				constantDefinition();
				setState(185);
				match(SEMICOLON);
				}
				}
				setState(189); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );
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
	public static class ConstantDefinitionContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode EQUAL() { return getToken(MiniPascalParser.EQUAL, 0); }
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public ConstantDefinitionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constantDefinition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterConstantDefinition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitConstantDefinition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitConstantDefinition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantDefinitionContext constantDefinition() throws RecognitionException {
		ConstantDefinitionContext _localctx = new ConstantDefinitionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_constantDefinition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191);
			identifier();
			setState(192);
			match(EQUAL);
			setState(193);
			constant();
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
	public static class ConstantChrContext extends ParserRuleContext {
		public TerminalNode CHR() { return getToken(MiniPascalParser.CHR, 0); }
		public TerminalNode L_PAREN() { return getToken(MiniPascalParser.L_PAREN, 0); }
		public UnsignedIntegerContext unsignedInteger() {
			return getRuleContext(UnsignedIntegerContext.class,0);
		}
		public TerminalNode R_PAREN() { return getToken(MiniPascalParser.R_PAREN, 0); }
		public ConstantChrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constantChr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterConstantChr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitConstantChr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitConstantChr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantChrContext constantChr() throws RecognitionException {
		ConstantChrContext _localctx = new ConstantChrContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_constantChr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(195);
			match(CHR);
			setState(196);
			match(L_PAREN);
			setState(197);
			unsignedInteger();
			setState(198);
			match(R_PAREN);
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
	public static class ConstantContext extends ParserRuleContext {
		public UnsignedNumberContext unsignedNumber() {
			return getRuleContext(UnsignedNumberContext.class,0);
		}
		public SignContext sign() {
			return getRuleContext(SignContext.class,0);
		}
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public CharContext char_() {
			return getRuleContext(CharContext.class,0);
		}
		public ConstantChrContext constantChr() {
			return getRuleContext(ConstantChrContext.class,0);
		}
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterConstant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitConstant(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitConstant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_constant);
		try {
			setState(207);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INTEGER:
				enterOuterAlt(_localctx, 1);
				{
				setState(200);
				unsignedNumber();
				}
				break;
			case PLUS:
			case MINUS:
				enterOuterAlt(_localctx, 2);
				{
				setState(201);
				sign();
				setState(202);
				unsignedNumber();
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 3);
				{
				setState(204);
				string();
				}
				break;
			case CHAR:
				enterOuterAlt(_localctx, 4);
				{
				setState(205);
				char_();
				}
				break;
			case CHR:
				enterOuterAlt(_localctx, 5);
				{
				setState(206);
				constantChr();
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
	public static class VarTypeContext extends ParserRuleContext {
		public ArrayTypeContext arrayType() {
			return getRuleContext(ArrayTypeContext.class,0);
		}
		public StringR_Context stringR_() {
			return getRuleContext(StringR_Context.class,0);
		}
		public CharR_Context charR_() {
			return getRuleContext(CharR_Context.class,0);
		}
		public IntegerR_Context integerR_() {
			return getRuleContext(IntegerR_Context.class,0);
		}
		public VarTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterVarType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitVarType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitVarType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarTypeContext varType() throws RecognitionException {
		VarTypeContext _localctx = new VarTypeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_varType);
		try {
			setState(213);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ARRAY:
				enterOuterAlt(_localctx, 1);
				{
				setState(209);
				arrayType();
				}
				break;
			case STRING_:
				enterOuterAlt(_localctx, 2);
				{
				setState(210);
				stringR_();
				}
				break;
			case CHAR_:
				enterOuterAlt(_localctx, 3);
				{
				setState(211);
				charR_();
				}
				break;
			case INTEGER_:
				enterOuterAlt(_localctx, 4);
				{
				setState(212);
				integerR_();
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
	public static class ArrayTypeContext extends ParserRuleContext {
		public TerminalNode ARRAY() { return getToken(MiniPascalParser.ARRAY, 0); }
		public TerminalNode L_BRACK() { return getToken(MiniPascalParser.L_BRACK, 0); }
		public IndexRangesContext indexRanges() {
			return getRuleContext(IndexRangesContext.class,0);
		}
		public TerminalNode R_BRACK() { return getToken(MiniPascalParser.R_BRACK, 0); }
		public TerminalNode OF() { return getToken(MiniPascalParser.OF, 0); }
		public VarTypeContext varType() {
			return getRuleContext(VarTypeContext.class,0);
		}
		public ArrayTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterArrayType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitArrayType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitArrayType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayTypeContext arrayType() throws RecognitionException {
		ArrayTypeContext _localctx = new ArrayTypeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_arrayType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(215);
			match(ARRAY);
			setState(216);
			match(L_BRACK);
			setState(217);
			indexRanges();
			setState(218);
			match(R_BRACK);
			setState(219);
			match(OF);
			setState(220);
			varType();
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
	public static class ArrayValueContext extends ParserRuleContext {
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public TerminalNode L_BRACK() { return getToken(MiniPascalParser.L_BRACK, 0); }
		public TerminalNode R_BRACK() { return getToken(MiniPascalParser.R_BRACK, 0); }
		public IntegerContext integer() {
			return getRuleContext(IntegerContext.class,0);
		}
		public ArrayValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterArrayValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitArrayValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitArrayValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayValueContext arrayValue() throws RecognitionException {
		ArrayValueContext _localctx = new ArrayValueContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_arrayValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(222);
			identifier();
			setState(223);
			match(L_BRACK);
			setState(226);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INTEGER:
				{
				setState(224);
				integer();
				}
				break;
			case ID:
				{
				setState(225);
				identifier();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(228);
			match(R_BRACK);
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
	public static class IndexRangesContext extends ParserRuleContext {
		public List<IndexRangeContext> indexRange() {
			return getRuleContexts(IndexRangeContext.class);
		}
		public IndexRangeContext indexRange(int i) {
			return getRuleContext(IndexRangeContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MiniPascalParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MiniPascalParser.COMMA, i);
		}
		public IndexRangesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexRanges; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterIndexRanges(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitIndexRanges(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitIndexRanges(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexRangesContext indexRanges() throws RecognitionException {
		IndexRangesContext _localctx = new IndexRangesContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_indexRanges);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(230);
			indexRange();
			setState(235);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(231);
				match(COMMA);
				setState(232);
				indexRange();
				}
				}
				setState(237);
				_errHandler.sync(this);
				_la = _input.LA(1);
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
	public static class IndexRangeContext extends ParserRuleContext {
		public List<UnsignedIntegerContext> unsignedInteger() {
			return getRuleContexts(UnsignedIntegerContext.class);
		}
		public UnsignedIntegerContext unsignedInteger(int i) {
			return getRuleContext(UnsignedIntegerContext.class,i);
		}
		public TerminalNode DOUBLE_DOT() { return getToken(MiniPascalParser.DOUBLE_DOT, 0); }
		public IndexRangeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexRange; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterIndexRange(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitIndexRange(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitIndexRange(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexRangeContext indexRange() throws RecognitionException {
		IndexRangeContext _localctx = new IndexRangeContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_indexRange);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(238);
			unsignedInteger();
			setState(239);
			match(DOUBLE_DOT);
			setState(240);
			unsignedInteger();
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
	public static class StringR_Context extends ParserRuleContext {
		public TerminalNode STRING_() { return getToken(MiniPascalParser.STRING_, 0); }
		public StringR_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringR_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterStringR_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitStringR_(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitStringR_(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringR_Context stringR_() throws RecognitionException {
		StringR_Context _localctx = new StringR_Context(_ctx, getState());
		enterRule(_localctx, 26, RULE_stringR_);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(242);
			match(STRING_);
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
	public static class CharR_Context extends ParserRuleContext {
		public TerminalNode CHAR_() { return getToken(MiniPascalParser.CHAR_, 0); }
		public CharR_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_charR_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterCharR_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitCharR_(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitCharR_(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CharR_Context charR_() throws RecognitionException {
		CharR_Context _localctx = new CharR_Context(_ctx, getState());
		enterRule(_localctx, 28, RULE_charR_);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(244);
			match(CHAR_);
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
	public static class IntegerR_Context extends ParserRuleContext {
		public TerminalNode INTEGER_() { return getToken(MiniPascalParser.INTEGER_, 0); }
		public IntegerR_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integerR_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterIntegerR_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitIntegerR_(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitIntegerR_(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntegerR_Context integerR_() throws RecognitionException {
		IntegerR_Context _localctx = new IntegerR_Context(_ctx, getState());
		enterRule(_localctx, 30, RULE_integerR_);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(246);
			match(INTEGER_);
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
	public static class UnsignedNumberContext extends ParserRuleContext {
		public UnsignedIntegerContext unsignedInteger() {
			return getRuleContext(UnsignedIntegerContext.class,0);
		}
		public UnsignedNumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unsignedNumber; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterUnsignedNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitUnsignedNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitUnsignedNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnsignedNumberContext unsignedNumber() throws RecognitionException {
		UnsignedNumberContext _localctx = new UnsignedNumberContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_unsignedNumber);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(248);
			unsignedInteger();
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
	public static class UnsignedIntegerContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(MiniPascalParser.INTEGER, 0); }
		public UnsignedIntegerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unsignedInteger; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterUnsignedInteger(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitUnsignedInteger(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitUnsignedInteger(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnsignedIntegerContext unsignedInteger() throws RecognitionException {
		UnsignedIntegerContext _localctx = new UnsignedIntegerContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_unsignedInteger);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(250);
			match(INTEGER);
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
	public static class SignContext extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(MiniPascalParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(MiniPascalParser.MINUS, 0); }
		public SignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterSign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitSign(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitSign(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SignContext sign() throws RecognitionException {
		SignContext _localctx = new SignContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_sign);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(252);
			_la = _input.LA(1);
			if ( !(_la==PLUS || _la==MINUS) ) {
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
	public static class Bool_Context extends ParserRuleContext {
		public TerminalNode TRUE() { return getToken(MiniPascalParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(MiniPascalParser.FALSE, 0); }
		public Bool_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bool_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterBool_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitBool_(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitBool_(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Bool_Context bool_() throws RecognitionException {
		Bool_Context _localctx = new Bool_Context(_ctx, getState());
		enterRule(_localctx, 38, RULE_bool_);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(254);
			_la = _input.LA(1);
			if ( !(_la==TRUE || _la==FALSE) ) {
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
	public static class StringContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(MiniPascalParser.STRING, 0); }
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitString(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitString(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_string);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(256);
			match(STRING);
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
	public static class CharContext extends ParserRuleContext {
		public TerminalNode CHAR() { return getToken(MiniPascalParser.CHAR, 0); }
		public CharContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_char; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterChar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitChar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitChar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CharContext char_() throws RecognitionException {
		CharContext _localctx = new CharContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_char);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(258);
			match(CHAR);
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
	public static class BooleanContext extends ParserRuleContext {
		public TerminalNode BOOLEAN() { return getToken(MiniPascalParser.BOOLEAN, 0); }
		public BooleanContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolean; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterBoolean(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitBoolean(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitBoolean(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BooleanContext boolean_() throws RecognitionException {
		BooleanContext _localctx = new BooleanContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_boolean);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(260);
			match(BOOLEAN);
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
	public static class IntegerContext extends ParserRuleContext {
		public TerminalNode INTEGER() { return getToken(MiniPascalParser.INTEGER, 0); }
		public IntegerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_integer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterInteger(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitInteger(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitInteger(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntegerContext integer() throws RecognitionException {
		IntegerContext _localctx = new IntegerContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_integer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(262);
			match(INTEGER);
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
	public static class Type_Context extends ParserRuleContext {
		public SimpleTypeContext simpleType() {
			return getRuleContext(SimpleTypeContext.class,0);
		}
		public ArrayTypeContext arrayType() {
			return getRuleContext(ArrayTypeContext.class,0);
		}
		public Type_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterType_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitType_(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitType_(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Type_Context type_() throws RecognitionException {
		Type_Context _localctx = new Type_Context(_ctx, getState());
		enterRule(_localctx, 48, RULE_type_);
		try {
			setState(266);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INTEGER:
			case CHAR:
			case STRING:
			case PLUS:
			case MINUS:
			case L_PAREN:
			case INTEGER_:
			case BOOLEAN_:
			case CHAR_:
			case STRING_:
			case CHR:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(264);
				simpleType();
				}
				break;
			case ARRAY:
				enterOuterAlt(_localctx, 2);
				{
				setState(265);
				arrayType();
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
	public static class SimpleTypeContext extends ParserRuleContext {
		public ScalarTypeContext scalarType() {
			return getRuleContext(ScalarTypeContext.class,0);
		}
		public SubrangeTypeContext subrangeType() {
			return getRuleContext(SubrangeTypeContext.class,0);
		}
		public TypeIdentifierContext typeIdentifier() {
			return getRuleContext(TypeIdentifierContext.class,0);
		}
		public SimpleTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterSimpleType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitSimpleType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitSimpleType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SimpleTypeContext simpleType() throws RecognitionException {
		SimpleTypeContext _localctx = new SimpleTypeContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_simpleType);
		try {
			setState(271);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case L_PAREN:
				enterOuterAlt(_localctx, 1);
				{
				setState(268);
				scalarType();
				}
				break;
			case INTEGER:
			case CHAR:
			case STRING:
			case PLUS:
			case MINUS:
			case CHR:
				enterOuterAlt(_localctx, 2);
				{
				setState(269);
				subrangeType();
				}
				break;
			case INTEGER_:
			case BOOLEAN_:
			case CHAR_:
			case STRING_:
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(270);
				typeIdentifier();
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
	public static class ScalarTypeContext extends ParserRuleContext {
		public TerminalNode L_PAREN() { return getToken(MiniPascalParser.L_PAREN, 0); }
		public IdentifierListContext identifierList() {
			return getRuleContext(IdentifierListContext.class,0);
		}
		public TerminalNode R_PAREN() { return getToken(MiniPascalParser.R_PAREN, 0); }
		public ScalarTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scalarType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterScalarType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitScalarType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitScalarType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScalarTypeContext scalarType() throws RecognitionException {
		ScalarTypeContext _localctx = new ScalarTypeContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_scalarType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(273);
			match(L_PAREN);
			setState(274);
			identifierList();
			setState(275);
			match(R_PAREN);
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
	public static class SubrangeTypeContext extends ParserRuleContext {
		public List<ConstantContext> constant() {
			return getRuleContexts(ConstantContext.class);
		}
		public ConstantContext constant(int i) {
			return getRuleContext(ConstantContext.class,i);
		}
		public TerminalNode DOUBLE_DOT() { return getToken(MiniPascalParser.DOUBLE_DOT, 0); }
		public SubrangeTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_subrangeType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterSubrangeType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitSubrangeType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitSubrangeType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SubrangeTypeContext subrangeType() throws RecognitionException {
		SubrangeTypeContext _localctx = new SubrangeTypeContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_subrangeType);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277);
			constant();
			setState(278);
			match(DOUBLE_DOT);
			setState(279);
			constant();
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
	public static class TypeIdentifierContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode CHAR_() { return getToken(MiniPascalParser.CHAR_, 0); }
		public TerminalNode BOOLEAN_() { return getToken(MiniPascalParser.BOOLEAN_, 0); }
		public TerminalNode INTEGER_() { return getToken(MiniPascalParser.INTEGER_, 0); }
		public TerminalNode STRING_() { return getToken(MiniPascalParser.STRING_, 0); }
		public TypeIdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeIdentifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterTypeIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitTypeIdentifier(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitTypeIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeIdentifierContext typeIdentifier() throws RecognitionException {
		TypeIdentifierContext _localctx = new TypeIdentifierContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_typeIdentifier);
		int _la;
		try {
			setState(283);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(281);
				identifier();
				}
				break;
			case INTEGER_:
			case BOOLEAN_:
			case CHAR_:
			case STRING_:
				enterOuterAlt(_localctx, 2);
				{
				setState(282);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 8444249301319680L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
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
	public static class VariableDeclarationPartContext extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(MiniPascalParser.VAR, 0); }
		public List<VariableDeclarationContext> variableDeclaration() {
			return getRuleContexts(VariableDeclarationContext.class);
		}
		public VariableDeclarationContext variableDeclaration(int i) {
			return getRuleContext(VariableDeclarationContext.class,i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(MiniPascalParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(MiniPascalParser.SEMICOLON, i);
		}
		public VariableDeclarationPartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclarationPart; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterVariableDeclarationPart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitVariableDeclarationPart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitVariableDeclarationPart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableDeclarationPartContext variableDeclarationPart() throws RecognitionException {
		VariableDeclarationPartContext _localctx = new VariableDeclarationPartContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_variableDeclarationPart);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(285);
			match(VAR);
			setState(286);
			variableDeclaration();
			setState(291);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(287);
					match(SEMICOLON);
					setState(288);
					variableDeclaration();
					}
					} 
				}
				setState(293);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			}
			setState(294);
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
	public static class VariableDeclarationContext extends ParserRuleContext {
		public IdentifierListContext identifierList() {
			return getRuleContext(IdentifierListContext.class,0);
		}
		public TerminalNode COLON() { return getToken(MiniPascalParser.COLON, 0); }
		public Type_Context type_() {
			return getRuleContext(Type_Context.class,0);
		}
		public VariableDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterVariableDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitVariableDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitVariableDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableDeclarationContext variableDeclaration() throws RecognitionException {
		VariableDeclarationContext _localctx = new VariableDeclarationContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_variableDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(296);
			identifierList();
			setState(297);
			match(COLON);
			setState(298);
			type_();
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
	public static class ProcedureAndFunctionDeclarationPartContext extends ParserRuleContext {
		public ProcedureOrFunctionDeclarationContext procedureOrFunctionDeclaration() {
			return getRuleContext(ProcedureOrFunctionDeclarationContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(MiniPascalParser.SEMICOLON, 0); }
		public ProcedureAndFunctionDeclarationPartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_procedureAndFunctionDeclarationPart; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterProcedureAndFunctionDeclarationPart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitProcedureAndFunctionDeclarationPart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitProcedureAndFunctionDeclarationPart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProcedureAndFunctionDeclarationPartContext procedureAndFunctionDeclarationPart() throws RecognitionException {
		ProcedureAndFunctionDeclarationPartContext _localctx = new ProcedureAndFunctionDeclarationPartContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_procedureAndFunctionDeclarationPart);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(300);
			procedureOrFunctionDeclaration();
			setState(301);
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
	public static class ProcedureOrFunctionDeclarationContext extends ParserRuleContext {
		public FunctionDeclarationContext functionDeclaration() {
			return getRuleContext(FunctionDeclarationContext.class,0);
		}
		public ProcedureDeclarationContext procedureDeclaration() {
			return getRuleContext(ProcedureDeclarationContext.class,0);
		}
		public ProcedureOrFunctionDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_procedureOrFunctionDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterProcedureOrFunctionDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitProcedureOrFunctionDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitProcedureOrFunctionDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProcedureOrFunctionDeclarationContext procedureOrFunctionDeclaration() throws RecognitionException {
		ProcedureOrFunctionDeclarationContext _localctx = new ProcedureOrFunctionDeclarationContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_procedureOrFunctionDeclaration);
		try {
			setState(305);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FUNCTION:
				enterOuterAlt(_localctx, 1);
				{
				setState(303);
				functionDeclaration();
				}
				break;
			case PROCEDURE:
				enterOuterAlt(_localctx, 2);
				{
				setState(304);
				procedureDeclaration();
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
	public static class FormalParameterListContext extends ParserRuleContext {
		public TerminalNode L_PAREN() { return getToken(MiniPascalParser.L_PAREN, 0); }
		public List<FormalParameterSectionContext> formalParameterSection() {
			return getRuleContexts(FormalParameterSectionContext.class);
		}
		public FormalParameterSectionContext formalParameterSection(int i) {
			return getRuleContext(FormalParameterSectionContext.class,i);
		}
		public TerminalNode R_PAREN() { return getToken(MiniPascalParser.R_PAREN, 0); }
		public List<TerminalNode> SEMICOLON() { return getTokens(MiniPascalParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(MiniPascalParser.SEMICOLON, i);
		}
		public FormalParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formalParameterList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterFormalParameterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitFormalParameterList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitFormalParameterList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormalParameterListContext formalParameterList() throws RecognitionException {
		FormalParameterListContext _localctx = new FormalParameterListContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_formalParameterList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(307);
			match(L_PAREN);
			setState(308);
			formalParameterSection();
			setState(313);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEMICOLON) {
				{
				{
				setState(309);
				match(SEMICOLON);
				setState(310);
				formalParameterSection();
				}
				}
				setState(315);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(316);
			match(R_PAREN);
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
	public static class FormalParameterSectionContext extends ParserRuleContext {
		public ParameterGroupContext parameterGroup() {
			return getRuleContext(ParameterGroupContext.class,0);
		}
		public TerminalNode VAR() { return getToken(MiniPascalParser.VAR, 0); }
		public FormalParameterSectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formalParameterSection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterFormalParameterSection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitFormalParameterSection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitFormalParameterSection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FormalParameterSectionContext formalParameterSection() throws RecognitionException {
		FormalParameterSectionContext _localctx = new FormalParameterSectionContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_formalParameterSection);
		try {
			setState(321);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case R_PAREN:
			case SEMICOLON:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(318);
				parameterGroup();
				}
				break;
			case VAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(319);
				match(VAR);
				setState(320);
				parameterGroup();
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
	public static class ParameterGroupContext extends ParserRuleContext {
		public IdentifierListContext identifierList() {
			return getRuleContext(IdentifierListContext.class,0);
		}
		public TerminalNode COLON() { return getToken(MiniPascalParser.COLON, 0); }
		public VarTypeContext varType() {
			return getRuleContext(VarTypeContext.class,0);
		}
		public EmptyStatement_Context emptyStatement_() {
			return getRuleContext(EmptyStatement_Context.class,0);
		}
		public ParameterGroupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterGroup; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterParameterGroup(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitParameterGroup(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitParameterGroup(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterGroupContext parameterGroup() throws RecognitionException {
		ParameterGroupContext _localctx = new ParameterGroupContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_parameterGroup);
		try {
			setState(328);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(323);
				identifierList();
				setState(324);
				match(COLON);
				setState(325);
				varType();
				}
				break;
			case R_PAREN:
			case SEMICOLON:
				enterOuterAlt(_localctx, 2);
				{
				setState(327);
				emptyStatement_();
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
	public static class IdentifierListContext extends ParserRuleContext {
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MiniPascalParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MiniPascalParser.COMMA, i);
		}
		public IdentifierListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifierList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterIdentifierList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitIdentifierList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitIdentifierList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentifierListContext identifierList() throws RecognitionException {
		IdentifierListContext _localctx = new IdentifierListContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_identifierList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(330);
			identifier();
			setState(335);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(331);
				match(COMMA);
				setState(332);
				identifier();
				}
				}
				setState(337);
				_errHandler.sync(this);
				_la = _input.LA(1);
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
	public static class FunctionDeclarationContext extends ParserRuleContext {
		public TerminalNode FUNCTION() { return getToken(MiniPascalParser.FUNCTION, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public FormalParameterListContext formalParameterList() {
			return getRuleContext(FormalParameterListContext.class,0);
		}
		public TerminalNode COLON() { return getToken(MiniPascalParser.COLON, 0); }
		public VarTypeContext varType() {
			return getRuleContext(VarTypeContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(MiniPascalParser.SEMICOLON, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public FunctionDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterFunctionDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitFunctionDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitFunctionDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionDeclarationContext functionDeclaration() throws RecognitionException {
		FunctionDeclarationContext _localctx = new FunctionDeclarationContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_functionDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(338);
			match(FUNCTION);
			setState(339);
			identifier();
			setState(340);
			formalParameterList();
			setState(341);
			match(COLON);
			setState(342);
			varType();
			setState(343);
			match(SEMICOLON);
			setState(344);
			block();
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
	public static class ProcedureDeclarationContext extends ParserRuleContext {
		public TerminalNode PROCEDURE() { return getToken(MiniPascalParser.PROCEDURE, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public FormalParameterListContext formalParameterList() {
			return getRuleContext(FormalParameterListContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(MiniPascalParser.SEMICOLON, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ProcedureDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_procedureDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterProcedureDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitProcedureDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitProcedureDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProcedureDeclarationContext procedureDeclaration() throws RecognitionException {
		ProcedureDeclarationContext _localctx = new ProcedureDeclarationContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_procedureDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(346);
			match(PROCEDURE);
			setState(347);
			identifier();
			setState(348);
			formalParameterList();
			setState(349);
			match(SEMICOLON);
			setState(350);
			block();
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
	public static class StatementContext extends ParserRuleContext {
		public WriteStatementContext writeStatement() {
			return getRuleContext(WriteStatementContext.class,0);
		}
		public ReadStatementContext readStatement() {
			return getRuleContext(ReadStatementContext.class,0);
		}
		public UnlabelledStatementContext unlabelledStatement() {
			return getRuleContext(UnlabelledStatementContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_statement);
		try {
			setState(355);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case WRITELN:
			case WRITE:
				enterOuterAlt(_localctx, 1);
				{
				setState(352);
				writeStatement();
				}
				break;
			case READLN:
			case READ:
				enterOuterAlt(_localctx, 2);
				{
				setState(353);
				readStatement();
				}
				break;
			case BEGIN:
			case END:
			case SEMICOLON:
			case IF:
			case ELSE:
			case UNTIL:
			case WHILE:
			case FOR:
			case REPEAT:
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(354);
				unlabelledStatement();
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
	public static class WriteStatementContext extends ParserRuleContext {
		public WriteContext write() {
			return getRuleContext(WriteContext.class,0);
		}
		public TerminalNode L_PAREN() { return getToken(MiniPascalParser.L_PAREN, 0); }
		public WriteParam2Context writeParam2() {
			return getRuleContext(WriteParam2Context.class,0);
		}
		public TerminalNode R_PAREN() { return getToken(MiniPascalParser.R_PAREN, 0); }
		public TerminalNode COMMA() { return getToken(MiniPascalParser.COMMA, 0); }
		public WriteParamContext writeParam() {
			return getRuleContext(WriteParamContext.class,0);
		}
		public WriteStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_writeStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterWriteStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitWriteStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitWriteStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WriteStatementContext writeStatement() throws RecognitionException {
		WriteStatementContext _localctx = new WriteStatementContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_writeStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(357);
			write();
			setState(358);
			match(L_PAREN);
			setState(359);
			writeParam2();
			setState(362);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(360);
				match(COMMA);
				setState(361);
				writeParam();
				}
			}

			setState(364);
			match(R_PAREN);
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
	public static class WriteContext extends ParserRuleContext {
		public TerminalNode WRITE() { return getToken(MiniPascalParser.WRITE, 0); }
		public TerminalNode WRITELN() { return getToken(MiniPascalParser.WRITELN, 0); }
		public WriteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_write; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterWrite(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitWrite(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitWrite(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WriteContext write() throws RecognitionException {
		WriteContext _localctx = new WriteContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_write);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(366);
			_la = _input.LA(1);
			if ( !(_la==WRITELN || _la==WRITE) ) {
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
	public static class WriteParam2Context extends ParserRuleContext {
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public WriteParam2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_writeParam2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterWriteParam2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitWriteParam2(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitWriteParam2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WriteParam2Context writeParam2() throws RecognitionException {
		WriteParam2Context _localctx = new WriteParam2Context(_ctx, getState());
		enterRule(_localctx, 84, RULE_writeParam2);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(368);
			string();
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
	public static class WriteParamContext extends ParserRuleContext {
		public VarValueContext varValue() {
			return getRuleContext(VarValueContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ArrayValueContext arrayValue() {
			return getRuleContext(ArrayValueContext.class,0);
		}
		public WriteParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_writeParam; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterWriteParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitWriteParam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitWriteParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WriteParamContext writeParam() throws RecognitionException {
		WriteParamContext _localctx = new WriteParamContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_writeParam);
		try {
			setState(373);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(370);
				varValue();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(371);
				identifier();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(372);
				arrayValue();
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
	public static class VarValueContext extends ParserRuleContext {
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public CharContext char_() {
			return getRuleContext(CharContext.class,0);
		}
		public IntegerContext integer() {
			return getRuleContext(IntegerContext.class,0);
		}
		public BooleanContext boolean_() {
			return getRuleContext(BooleanContext.class,0);
		}
		public VarValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterVarValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitVarValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitVarValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarValueContext varValue() throws RecognitionException {
		VarValueContext _localctx = new VarValueContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_varValue);
		try {
			setState(379);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STRING:
				enterOuterAlt(_localctx, 1);
				{
				setState(375);
				string();
				}
				break;
			case CHAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(376);
				char_();
				}
				break;
			case INTEGER:
				enterOuterAlt(_localctx, 3);
				{
				setState(377);
				integer();
				}
				break;
			case BOOLEAN:
				enterOuterAlt(_localctx, 4);
				{
				setState(378);
				boolean_();
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
	public static class ReadStatementContext extends ParserRuleContext {
		public ReadContext read() {
			return getRuleContext(ReadContext.class,0);
		}
		public TerminalNode L_PAREN() { return getToken(MiniPascalParser.L_PAREN, 0); }
		public ReadParamContext readParam() {
			return getRuleContext(ReadParamContext.class,0);
		}
		public TerminalNode R_PAREN() { return getToken(MiniPascalParser.R_PAREN, 0); }
		public ReadStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_readStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterReadStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitReadStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitReadStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReadStatementContext readStatement() throws RecognitionException {
		ReadStatementContext _localctx = new ReadStatementContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_readStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(381);
			read();
			setState(382);
			match(L_PAREN);
			setState(383);
			readParam();
			setState(384);
			match(R_PAREN);
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
	public static class ReadContext extends ParserRuleContext {
		public TerminalNode READ() { return getToken(MiniPascalParser.READ, 0); }
		public TerminalNode READLN() { return getToken(MiniPascalParser.READLN, 0); }
		public ReadContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_read; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterRead(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitRead(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitRead(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReadContext read() throws RecognitionException {
		ReadContext _localctx = new ReadContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_read);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(386);
			_la = _input.LA(1);
			if ( !(_la==READLN || _la==READ) ) {
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
	public static class ReadParamContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public ReadParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_readParam; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterReadParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitReadParam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitReadParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ReadParamContext readParam() throws RecognitionException {
		ReadParamContext _localctx = new ReadParamContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_readParam);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(388);
			identifier();
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
	public static class UnlabelledStatementContext extends ParserRuleContext {
		public SimpleStatementContext simpleStatement() {
			return getRuleContext(SimpleStatementContext.class,0);
		}
		public StructuredStatementContext structuredStatement() {
			return getRuleContext(StructuredStatementContext.class,0);
		}
		public UnlabelledStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unlabelledStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterUnlabelledStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitUnlabelledStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitUnlabelledStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnlabelledStatementContext unlabelledStatement() throws RecognitionException {
		UnlabelledStatementContext _localctx = new UnlabelledStatementContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_unlabelledStatement);
		try {
			setState(392);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case END:
			case SEMICOLON:
			case ELSE:
			case UNTIL:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(390);
				simpleStatement();
				}
				break;
			case BEGIN:
			case IF:
			case WHILE:
			case FOR:
			case REPEAT:
				enterOuterAlt(_localctx, 2);
				{
				setState(391);
				structuredStatement();
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
	public static class SimpleStatementContext extends ParserRuleContext {
		public AssignmentStatementContext assignmentStatement() {
			return getRuleContext(AssignmentStatementContext.class,0);
		}
		public EmptyStatement_Context emptyStatement_() {
			return getRuleContext(EmptyStatement_Context.class,0);
		}
		public SimpleStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterSimpleStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitSimpleStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitSimpleStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SimpleStatementContext simpleStatement() throws RecognitionException {
		SimpleStatementContext _localctx = new SimpleStatementContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_simpleStatement);
		try {
			setState(396);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(394);
				assignmentStatement();
				}
				break;
			case END:
			case SEMICOLON:
			case ELSE:
			case UNTIL:
				enterOuterAlt(_localctx, 2);
				{
				setState(395);
				emptyStatement_();
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
	public static class AssignmentStatementContext extends ParserRuleContext {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(MiniPascalParser.ASSIGN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AssignmentStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignmentStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterAssignmentStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitAssignmentStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitAssignmentStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentStatementContext assignmentStatement() throws RecognitionException {
		AssignmentStatementContext _localctx = new AssignmentStatementContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_assignmentStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(398);
			variable();
			setState(399);
			match(ASSIGN);
			setState(400);
			expression();
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
	public static class VariableContext extends ParserRuleContext {
		public List<IdentifierContext> identifier() {
			return getRuleContexts(IdentifierContext.class);
		}
		public IdentifierContext identifier(int i) {
			return getRuleContext(IdentifierContext.class,i);
		}
		public List<TerminalNode> L_BRACK() { return getTokens(MiniPascalParser.L_BRACK); }
		public TerminalNode L_BRACK(int i) {
			return getToken(MiniPascalParser.L_BRACK, i);
		}
		public List<ExpressionListContext> expressionList() {
			return getRuleContexts(ExpressionListContext.class);
		}
		public ExpressionListContext expressionList(int i) {
			return getRuleContext(ExpressionListContext.class,i);
		}
		public List<TerminalNode> R_BRACK() { return getTokens(MiniPascalParser.R_BRACK); }
		public TerminalNode R_BRACK(int i) {
			return getToken(MiniPascalParser.R_BRACK, i);
		}
		public List<TerminalNode> DOT() { return getTokens(MiniPascalParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(MiniPascalParser.DOT, i);
		}
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_variable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(402);
			identifier();
			setState(411);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==L_BRACK || _la==DOT) {
				{
				setState(409);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case L_BRACK:
					{
					setState(403);
					match(L_BRACK);
					setState(404);
					expressionList();
					setState(405);
					match(R_BRACK);
					}
					break;
				case DOT:
					{
					setState(407);
					match(DOT);
					setState(408);
					identifier();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(413);
				_errHandler.sync(this);
				_la = _input.LA(1);
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
	public static class ExpressionListContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MiniPascalParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MiniPascalParser.COMMA, i);
		}
		public ExpressionListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterExpressionList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitExpressionList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitExpressionList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionListContext expressionList() throws RecognitionException {
		ExpressionListContext _localctx = new ExpressionListContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_expressionList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(414);
			expression();
			setState(419);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(415);
				match(COMMA);
				setState(416);
				expression();
				}
				}
				setState(421);
				_errHandler.sync(this);
				_la = _input.LA(1);
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
	public static class ExpressionContext extends ParserRuleContext {
		public SimpleExpressionContext simpleExpression() {
			return getRuleContext(SimpleExpressionContext.class,0);
		}
		public RelationaloperatorContext relationaloperator() {
			return getRuleContext(RelationaloperatorContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_expression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(422);
			simpleExpression();
			setState(426);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 16515072L) != 0)) {
				{
				setState(423);
				relationaloperator();
				setState(424);
				expression();
				}
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
	public static class RelationaloperatorContext extends ParserRuleContext {
		public TerminalNode EQUAL() { return getToken(MiniPascalParser.EQUAL, 0); }
		public TerminalNode NOT_EQUAL() { return getToken(MiniPascalParser.NOT_EQUAL, 0); }
		public TerminalNode LT() { return getToken(MiniPascalParser.LT, 0); }
		public TerminalNode LE() { return getToken(MiniPascalParser.LE, 0); }
		public TerminalNode GE() { return getToken(MiniPascalParser.GE, 0); }
		public TerminalNode GT() { return getToken(MiniPascalParser.GT, 0); }
		public RelationaloperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_relationaloperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterRelationaloperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitRelationaloperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitRelationaloperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RelationaloperatorContext relationaloperator() throws RecognitionException {
		RelationaloperatorContext _localctx = new RelationaloperatorContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_relationaloperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(428);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 16515072L) != 0)) ) {
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
	public static class SimpleExpressionContext extends ParserRuleContext {
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public AdditiveoperatorContext additiveoperator() {
			return getRuleContext(AdditiveoperatorContext.class,0);
		}
		public SimpleExpressionContext simpleExpression() {
			return getRuleContext(SimpleExpressionContext.class,0);
		}
		public SimpleExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simpleExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterSimpleExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitSimpleExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitSimpleExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SimpleExpressionContext simpleExpression() throws RecognitionException {
		SimpleExpressionContext _localctx = new SimpleExpressionContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_simpleExpression);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(430);
			term();
			setState(434);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 134144L) != 0)) {
				{
				setState(431);
				additiveoperator();
				setState(432);
				simpleExpression();
				}
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
	public static class AdditiveoperatorContext extends ParserRuleContext {
		public TerminalNode PLUS() { return getToken(MiniPascalParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(MiniPascalParser.MINUS, 0); }
		public TerminalNode OR() { return getToken(MiniPascalParser.OR, 0); }
		public AdditiveoperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additiveoperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterAdditiveoperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitAdditiveoperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitAdditiveoperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AdditiveoperatorContext additiveoperator() throws RecognitionException {
		AdditiveoperatorContext _localctx = new AdditiveoperatorContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_additiveoperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(436);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 134144L) != 0)) ) {
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
	public static class TermContext extends ParserRuleContext {
		public SignedFactorContext signedFactor() {
			return getRuleContext(SignedFactorContext.class,0);
		}
		public MultiplicativeoperatorContext multiplicativeoperator() {
			return getRuleContext(MultiplicativeoperatorContext.class,0);
		}
		public TermContext term() {
			return getRuleContext(TermContext.class,0);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterTerm(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitTerm(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitTerm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_term);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(438);
			signedFactor();
			setState(442);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 28672L) != 0)) {
				{
				setState(439);
				multiplicativeoperator();
				setState(440);
				term();
				}
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
	public static class MultiplicativeoperatorContext extends ParserRuleContext {
		public TerminalNode MULT() { return getToken(MiniPascalParser.MULT, 0); }
		public TerminalNode DIV() { return getToken(MiniPascalParser.DIV, 0); }
		public TerminalNode MOD() { return getToken(MiniPascalParser.MOD, 0); }
		public MultiplicativeoperatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicativeoperator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterMultiplicativeoperator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitMultiplicativeoperator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitMultiplicativeoperator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultiplicativeoperatorContext multiplicativeoperator() throws RecognitionException {
		MultiplicativeoperatorContext _localctx = new MultiplicativeoperatorContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_multiplicativeoperator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(444);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 28672L) != 0)) ) {
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
	public static class SignedFactorContext extends ParserRuleContext {
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public TerminalNode PLUS() { return getToken(MiniPascalParser.PLUS, 0); }
		public TerminalNode MINUS() { return getToken(MiniPascalParser.MINUS, 0); }
		public SignedFactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_signedFactor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterSignedFactor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitSignedFactor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitSignedFactor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SignedFactorContext signedFactor() throws RecognitionException {
		SignedFactorContext _localctx = new SignedFactorContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_signedFactor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(447);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PLUS || _la==MINUS) {
				{
				setState(446);
				_la = _input.LA(1);
				if ( !(_la==PLUS || _la==MINUS) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(449);
			factor();
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
	public static class FactorContext extends ParserRuleContext {
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public TerminalNode L_PAREN() { return getToken(MiniPascalParser.L_PAREN, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode R_PAREN() { return getToken(MiniPascalParser.R_PAREN, 0); }
		public FunctionDesignatorContext functionDesignator() {
			return getRuleContext(FunctionDesignatorContext.class,0);
		}
		public UnsignedConstantContext unsignedConstant() {
			return getRuleContext(UnsignedConstantContext.class,0);
		}
		public TerminalNode NOT() { return getToken(MiniPascalParser.NOT, 0); }
		public FactorContext factor() {
			return getRuleContext(FactorContext.class,0);
		}
		public Bool_Context bool_() {
			return getRuleContext(Bool_Context.class,0);
		}
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterFactor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitFactor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitFactor(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_factor);
		try {
			setState(461);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,30,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(451);
				variable();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(452);
				match(L_PAREN);
				setState(453);
				expression();
				setState(454);
				match(R_PAREN);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(456);
				functionDesignator();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(457);
				unsignedConstant();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(458);
				match(NOT);
				setState(459);
				factor();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(460);
				bool_();
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
	public static class UnsignedConstantContext extends ParserRuleContext {
		public UnsignedNumberContext unsignedNumber() {
			return getRuleContext(UnsignedNumberContext.class,0);
		}
		public ConstantChrContext constantChr() {
			return getRuleContext(ConstantChrContext.class,0);
		}
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public TerminalNode NIL() { return getToken(MiniPascalParser.NIL, 0); }
		public UnsignedConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unsignedConstant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterUnsignedConstant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitUnsignedConstant(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitUnsignedConstant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnsignedConstantContext unsignedConstant() throws RecognitionException {
		UnsignedConstantContext _localctx = new UnsignedConstantContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_unsignedConstant);
		try {
			setState(467);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INTEGER:
				enterOuterAlt(_localctx, 1);
				{
				setState(463);
				unsignedNumber();
				}
				break;
			case CHR:
				enterOuterAlt(_localctx, 2);
				{
				setState(464);
				constantChr();
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 3);
				{
				setState(465);
				string();
				}
				break;
			case NIL:
				enterOuterAlt(_localctx, 4);
				{
				setState(466);
				match(NIL);
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
	public static class FunctionDesignatorContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode L_PAREN() { return getToken(MiniPascalParser.L_PAREN, 0); }
		public ParameterListContext parameterList() {
			return getRuleContext(ParameterListContext.class,0);
		}
		public TerminalNode R_PAREN() { return getToken(MiniPascalParser.R_PAREN, 0); }
		public FunctionDesignatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_functionDesignator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterFunctionDesignator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitFunctionDesignator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitFunctionDesignator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionDesignatorContext functionDesignator() throws RecognitionException {
		FunctionDesignatorContext _localctx = new FunctionDesignatorContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_functionDesignator);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(469);
			identifier();
			setState(470);
			match(L_PAREN);
			setState(471);
			parameterList();
			setState(472);
			match(R_PAREN);
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
	public static class ParameterListContext extends ParserRuleContext {
		public List<ActualParameterContext> actualParameter() {
			return getRuleContexts(ActualParameterContext.class);
		}
		public ActualParameterContext actualParameter(int i) {
			return getRuleContext(ActualParameterContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(MiniPascalParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(MiniPascalParser.COMMA, i);
		}
		public ParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parameterList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterParameterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitParameterList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitParameterList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParameterListContext parameterList() throws RecognitionException {
		ParameterListContext _localctx = new ParameterListContext(_ctx, getState());
		enterRule(_localctx, 126, RULE_parameterList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(474);
			actualParameter();
			setState(479);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(475);
				match(COMMA);
				setState(476);
				actualParameter();
				}
				}
				setState(481);
				_errHandler.sync(this);
				_la = _input.LA(1);
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
	public static class ActualParameterContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ActualParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_actualParameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterActualParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitActualParameter(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitActualParameter(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ActualParameterContext actualParameter() throws RecognitionException {
		ActualParameterContext _localctx = new ActualParameterContext(_ctx, getState());
		enterRule(_localctx, 128, RULE_actualParameter);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(482);
			expression();
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
	public static class EmptyStatement_Context extends ParserRuleContext {
		public EmptyStatement_Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_emptyStatement_; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterEmptyStatement_(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitEmptyStatement_(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitEmptyStatement_(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EmptyStatement_Context emptyStatement_() throws RecognitionException {
		EmptyStatement_Context _localctx = new EmptyStatement_Context(_ctx, getState());
		enterRule(_localctx, 130, RULE_emptyStatement_);
		try {
			enterOuterAlt(_localctx, 1);
			{
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
	public static class StructuredStatementContext extends ParserRuleContext {
		public CompoundStatementContext compoundStatement() {
			return getRuleContext(CompoundStatementContext.class,0);
		}
		public ConditionalStatementContext conditionalStatement() {
			return getRuleContext(ConditionalStatementContext.class,0);
		}
		public RepetitiveStatementContext repetitiveStatement() {
			return getRuleContext(RepetitiveStatementContext.class,0);
		}
		public StructuredStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_structuredStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterStructuredStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitStructuredStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitStructuredStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StructuredStatementContext structuredStatement() throws RecognitionException {
		StructuredStatementContext _localctx = new StructuredStatementContext(_ctx, getState());
		enterRule(_localctx, 132, RULE_structuredStatement);
		try {
			setState(489);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BEGIN:
				enterOuterAlt(_localctx, 1);
				{
				setState(486);
				compoundStatement();
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 2);
				{
				setState(487);
				conditionalStatement();
				}
				break;
			case WHILE:
			case FOR:
			case REPEAT:
				enterOuterAlt(_localctx, 3);
				{
				setState(488);
				repetitiveStatement();
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
	public static class CompoundStatementContext extends ParserRuleContext {
		public TerminalNode BEGIN() { return getToken(MiniPascalParser.BEGIN, 0); }
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public TerminalNode END() { return getToken(MiniPascalParser.END, 0); }
		public CompoundStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compoundStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterCompoundStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitCompoundStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitCompoundStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CompoundStatementContext compoundStatement() throws RecognitionException {
		CompoundStatementContext _localctx = new CompoundStatementContext(_ctx, getState());
		enterRule(_localctx, 134, RULE_compoundStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(491);
			match(BEGIN);
			setState(492);
			statements();
			setState(493);
			match(END);
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
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<TerminalNode> SEMICOLON() { return getTokens(MiniPascalParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(MiniPascalParser.SEMICOLON, i);
		}
		public StatementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterStatements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitStatements(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitStatements(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementsContext statements() throws RecognitionException {
		StatementsContext _localctx = new StatementsContext(_ctx, getState());
		enterRule(_localctx, 136, RULE_statements);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(495);
			statement();
			setState(500);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SEMICOLON) {
				{
				{
				setState(496);
				match(SEMICOLON);
				setState(497);
				statement();
				}
				}
				setState(502);
				_errHandler.sync(this);
				_la = _input.LA(1);
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
	public static class ConditionalStatementContext extends ParserRuleContext {
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
		}
		public ConditionalStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditionalStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterConditionalStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitConditionalStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitConditionalStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionalStatementContext conditionalStatement() throws RecognitionException {
		ConditionalStatementContext _localctx = new ConditionalStatementContext(_ctx, getState());
		enterRule(_localctx, 138, RULE_conditionalStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(503);
			ifStatement();
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
	public static class IfStatementContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(MiniPascalParser.IF, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode THEN() { return getToken(MiniPascalParser.THEN, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode ELSE() { return getToken(MiniPascalParser.ELSE, 0); }
		public IfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterIfStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitIfStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitIfStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfStatementContext ifStatement() throws RecognitionException {
		IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
		enterRule(_localctx, 140, RULE_ifStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(505);
			match(IF);
			setState(506);
			expression();
			setState(507);
			match(THEN);
			setState(508);
			statement();
			setState(511);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,35,_ctx) ) {
			case 1:
				{
				setState(509);
				match(ELSE);
				setState(510);
				statement();
				}
				break;
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
	public static class RepetitiveStatementContext extends ParserRuleContext {
		public WhileStatementContext whileStatement() {
			return getRuleContext(WhileStatementContext.class,0);
		}
		public RepeatStatementContext repeatStatement() {
			return getRuleContext(RepeatStatementContext.class,0);
		}
		public ForStatementContext forStatement() {
			return getRuleContext(ForStatementContext.class,0);
		}
		public RepetitiveStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_repetitiveStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterRepetitiveStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitRepetitiveStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitRepetitiveStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RepetitiveStatementContext repetitiveStatement() throws RecognitionException {
		RepetitiveStatementContext _localctx = new RepetitiveStatementContext(_ctx, getState());
		enterRule(_localctx, 142, RULE_repetitiveStatement);
		try {
			setState(516);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case WHILE:
				enterOuterAlt(_localctx, 1);
				{
				setState(513);
				whileStatement();
				}
				break;
			case REPEAT:
				enterOuterAlt(_localctx, 2);
				{
				setState(514);
				repeatStatement();
				}
				break;
			case FOR:
				enterOuterAlt(_localctx, 3);
				{
				setState(515);
				forStatement();
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
	public static class WhileStatementContext extends ParserRuleContext {
		public TerminalNode WHILE() { return getToken(MiniPascalParser.WHILE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode DO() { return getToken(MiniPascalParser.DO, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public WhileStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterWhileStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitWhileStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitWhileStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final WhileStatementContext whileStatement() throws RecognitionException {
		WhileStatementContext _localctx = new WhileStatementContext(_ctx, getState());
		enterRule(_localctx, 144, RULE_whileStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(518);
			match(WHILE);
			setState(519);
			expression();
			setState(520);
			match(DO);
			setState(521);
			statement();
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
	public static class RepeatStatementContext extends ParserRuleContext {
		public TerminalNode REPEAT() { return getToken(MiniPascalParser.REPEAT, 0); }
		public StatementsContext statements() {
			return getRuleContext(StatementsContext.class,0);
		}
		public TerminalNode UNTIL() { return getToken(MiniPascalParser.UNTIL, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public RepeatStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_repeatStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterRepeatStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitRepeatStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitRepeatStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RepeatStatementContext repeatStatement() throws RecognitionException {
		RepeatStatementContext _localctx = new RepeatStatementContext(_ctx, getState());
		enterRule(_localctx, 146, RULE_repeatStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(523);
			match(REPEAT);
			setState(524);
			statements();
			setState(525);
			match(UNTIL);
			setState(526);
			expression();
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
	public static class ForStatementContext extends ParserRuleContext {
		public TerminalNode FOR() { return getToken(MiniPascalParser.FOR, 0); }
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(MiniPascalParser.ASSIGN, 0); }
		public ForListContext forList() {
			return getRuleContext(ForListContext.class,0);
		}
		public TerminalNode DO() { return getToken(MiniPascalParser.DO, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public ForStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterForStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitForStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitForStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForStatementContext forStatement() throws RecognitionException {
		ForStatementContext _localctx = new ForStatementContext(_ctx, getState());
		enterRule(_localctx, 148, RULE_forStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(528);
			match(FOR);
			setState(529);
			identifier();
			setState(530);
			match(ASSIGN);
			setState(531);
			forList();
			setState(532);
			match(DO);
			setState(533);
			statement();
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
	public static class ForListContext extends ParserRuleContext {
		public InitialValueContext initialValue() {
			return getRuleContext(InitialValueContext.class,0);
		}
		public FinalValueContext finalValue() {
			return getRuleContext(FinalValueContext.class,0);
		}
		public TerminalNode TO() { return getToken(MiniPascalParser.TO, 0); }
		public TerminalNode DOWNTO() { return getToken(MiniPascalParser.DOWNTO, 0); }
		public ForListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterForList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitForList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitForList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForListContext forList() throws RecognitionException {
		ForListContext _localctx = new ForListContext(_ctx, getState());
		enterRule(_localctx, 150, RULE_forList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(535);
			initialValue();
			setState(536);
			_la = _input.LA(1);
			if ( !(_la==TO || _la==DOWNTO) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(537);
			finalValue();
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
	public static class InitialValueContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public InitialValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_initialValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterInitialValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitInitialValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitInitialValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InitialValueContext initialValue() throws RecognitionException {
		InitialValueContext _localctx = new InitialValueContext(_ctx, getState());
		enterRule(_localctx, 152, RULE_initialValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(539);
			expression();
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
	public static class FinalValueContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public FinalValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_finalValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).enterFinalValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MiniPascalListener ) ((MiniPascalListener)listener).exitFinalValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MiniPascalVisitor ) return ((MiniPascalVisitor<? extends T>)visitor).visitFinalValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FinalValueContext finalValue() throws RecognitionException {
		FinalValueContext _localctx = new FinalValueContext(_ctx, getState());
		enterRule(_localctx, 154, RULE_finalValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(541);
			expression();
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

	public static final String _serializedATN =
		"\u0004\u0001?\u0220\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0002\u0018\u0007\u0018"+
		"\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a\u0002\u001b\u0007\u001b"+
		"\u0002\u001c\u0007\u001c\u0002\u001d\u0007\u001d\u0002\u001e\u0007\u001e"+
		"\u0002\u001f\u0007\u001f\u0002 \u0007 \u0002!\u0007!\u0002\"\u0007\"\u0002"+
		"#\u0007#\u0002$\u0007$\u0002%\u0007%\u0002&\u0007&\u0002\'\u0007\'\u0002"+
		"(\u0007(\u0002)\u0007)\u0002*\u0007*\u0002+\u0007+\u0002,\u0007,\u0002"+
		"-\u0007-\u0002.\u0007.\u0002/\u0007/\u00020\u00070\u00021\u00071\u0002"+
		"2\u00072\u00023\u00073\u00024\u00074\u00025\u00075\u00026\u00076\u0002"+
		"7\u00077\u00028\u00078\u00029\u00079\u0002:\u0007:\u0002;\u0007;\u0002"+
		"<\u0007<\u0002=\u0007=\u0002>\u0007>\u0002?\u0007?\u0002@\u0007@\u0002"+
		"A\u0007A\u0002B\u0007B\u0002C\u0007C\u0002D\u0007D\u0002E\u0007E\u0002"+
		"F\u0007F\u0002G\u0007G\u0002H\u0007H\u0002I\u0007I\u0002J\u0007J\u0002"+
		"K\u0007K\u0002L\u0007L\u0002M\u0007M\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0003\u0001\u00a8\b\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0005\u0003"+
		"\u00b1\b\u0003\n\u0003\f\u0003\u00b4\t\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0004\u0004\u00bc\b\u0004\u000b"+
		"\u0004\f\u0004\u00bd\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0001"+
		"\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003"+
		"\u0007\u00d0\b\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0003\b\u00d6\b\b"+
		"\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0003\n\u00e3\b\n\u0001\n\u0001\n\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0005\u000b\u00ea\b\u000b\n\u000b\f\u000b\u00ed\t\u000b"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\u000e\u0001\u000e"+
		"\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011"+
		"\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014"+
		"\u0001\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017"+
		"\u0001\u0018\u0001\u0018\u0003\u0018\u010b\b\u0018\u0001\u0019\u0001\u0019"+
		"\u0001\u0019\u0003\u0019\u0110\b\u0019\u0001\u001a\u0001\u001a\u0001\u001a"+
		"\u0001\u001a\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001b\u0001\u001c"+
		"\u0001\u001c\u0003\u001c\u011c\b\u001c\u0001\u001d\u0001\u001d\u0001\u001d"+
		"\u0001\u001d\u0005\u001d\u0122\b\u001d\n\u001d\f\u001d\u0125\t\u001d\u0001"+
		"\u001d\u0001\u001d\u0001\u001e\u0001\u001e\u0001\u001e\u0001\u001e\u0001"+
		"\u001f\u0001\u001f\u0001\u001f\u0001 \u0001 \u0003 \u0132\b \u0001!\u0001"+
		"!\u0001!\u0001!\u0005!\u0138\b!\n!\f!\u013b\t!\u0001!\u0001!\u0001\"\u0001"+
		"\"\u0001\"\u0003\"\u0142\b\"\u0001#\u0001#\u0001#\u0001#\u0001#\u0003"+
		"#\u0149\b#\u0001$\u0001$\u0001$\u0005$\u014e\b$\n$\f$\u0151\t$\u0001%"+
		"\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001%\u0001&\u0001&\u0001"+
		"&\u0001&\u0001&\u0001&\u0001\'\u0001\'\u0001\'\u0003\'\u0164\b\'\u0001"+
		"(\u0001(\u0001(\u0001(\u0001(\u0003(\u016b\b(\u0001(\u0001(\u0001)\u0001"+
		")\u0001*\u0001*\u0001+\u0001+\u0001+\u0003+\u0176\b+\u0001,\u0001,\u0001"+
		",\u0001,\u0003,\u017c\b,\u0001-\u0001-\u0001-\u0001-\u0001-\u0001.\u0001"+
		".\u0001/\u0001/\u00010\u00010\u00030\u0189\b0\u00011\u00011\u00031\u018d"+
		"\b1\u00012\u00012\u00012\u00012\u00013\u00013\u00013\u00013\u00013\u0001"+
		"3\u00013\u00053\u019a\b3\n3\f3\u019d\t3\u00014\u00014\u00014\u00054\u01a2"+
		"\b4\n4\f4\u01a5\t4\u00015\u00015\u00015\u00015\u00035\u01ab\b5\u00016"+
		"\u00016\u00017\u00017\u00017\u00017\u00037\u01b3\b7\u00018\u00018\u0001"+
		"9\u00019\u00019\u00019\u00039\u01bb\b9\u0001:\u0001:\u0001;\u0003;\u01c0"+
		"\b;\u0001;\u0001;\u0001<\u0001<\u0001<\u0001<\u0001<\u0001<\u0001<\u0001"+
		"<\u0001<\u0001<\u0003<\u01ce\b<\u0001=\u0001=\u0001=\u0001=\u0003=\u01d4"+
		"\b=\u0001>\u0001>\u0001>\u0001>\u0001>\u0001?\u0001?\u0001?\u0005?\u01de"+
		"\b?\n?\f?\u01e1\t?\u0001@\u0001@\u0001A\u0001A\u0001B\u0001B\u0001B\u0003"+
		"B\u01ea\bB\u0001C\u0001C\u0001C\u0001C\u0001D\u0001D\u0001D\u0005D\u01f3"+
		"\bD\nD\fD\u01f6\tD\u0001E\u0001E\u0001F\u0001F\u0001F\u0001F\u0001F\u0001"+
		"F\u0003F\u0200\bF\u0001G\u0001G\u0001G\u0003G\u0205\bG\u0001H\u0001H\u0001"+
		"H\u0001H\u0001H\u0001I\u0001I\u0001I\u0001I\u0001I\u0001J\u0001J\u0001"+
		"J\u0001J\u0001J\u0001J\u0001J\u0001K\u0001K\u0001K\u0001K\u0001L\u0001"+
		"L\u0001M\u0001M\u0001M\u0000\u0000N\u0000\u0002\u0004\u0006\b\n\f\u000e"+
		"\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*,.02468:<>@BDF"+
		"HJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086\u0088\u008a\u008c"+
		"\u008e\u0090\u0092\u0094\u0096\u0098\u009a\u0000\t\u0001\u0000\n\u000b"+
		"\u0001\u0000>?\u0001\u000014\u0001\u0000;<\u0001\u00009:\u0001\u0000\u0012"+
		"\u0017\u0002\u0000\n\u000b\u0011\u0011\u0001\u0000\f\u000e\u0002\u0000"+
		"++--\u0209\u0000\u009c\u0001\u0000\u0000\u0000\u0002\u00a1\u0001\u0000"+
		"\u0000\u0000\u0004\u00ab\u0001\u0000\u0000\u0000\u0006\u00b2\u0001\u0000"+
		"\u0000\u0000\b\u00b7\u0001\u0000\u0000\u0000\n\u00bf\u0001\u0000\u0000"+
		"\u0000\f\u00c3\u0001\u0000\u0000\u0000\u000e\u00cf\u0001\u0000\u0000\u0000"+
		"\u0010\u00d5\u0001\u0000\u0000\u0000\u0012\u00d7\u0001\u0000\u0000\u0000"+
		"\u0014\u00de\u0001\u0000\u0000\u0000\u0016\u00e6\u0001\u0000\u0000\u0000"+
		"\u0018\u00ee\u0001\u0000\u0000\u0000\u001a\u00f2\u0001\u0000\u0000\u0000"+
		"\u001c\u00f4\u0001\u0000\u0000\u0000\u001e\u00f6\u0001\u0000\u0000\u0000"+
		" \u00f8\u0001\u0000\u0000\u0000\"\u00fa\u0001\u0000\u0000\u0000$\u00fc"+
		"\u0001\u0000\u0000\u0000&\u00fe\u0001\u0000\u0000\u0000(\u0100\u0001\u0000"+
		"\u0000\u0000*\u0102\u0001\u0000\u0000\u0000,\u0104\u0001\u0000\u0000\u0000"+
		".\u0106\u0001\u0000\u0000\u00000\u010a\u0001\u0000\u0000\u00002\u010f"+
		"\u0001\u0000\u0000\u00004\u0111\u0001\u0000\u0000\u00006\u0115\u0001\u0000"+
		"\u0000\u00008\u011b\u0001\u0000\u0000\u0000:\u011d\u0001\u0000\u0000\u0000"+
		"<\u0128\u0001\u0000\u0000\u0000>\u012c\u0001\u0000\u0000\u0000@\u0131"+
		"\u0001\u0000\u0000\u0000B\u0133\u0001\u0000\u0000\u0000D\u0141\u0001\u0000"+
		"\u0000\u0000F\u0148\u0001\u0000\u0000\u0000H\u014a\u0001\u0000\u0000\u0000"+
		"J\u0152\u0001\u0000\u0000\u0000L\u015a\u0001\u0000\u0000\u0000N\u0163"+
		"\u0001\u0000\u0000\u0000P\u0165\u0001\u0000\u0000\u0000R\u016e\u0001\u0000"+
		"\u0000\u0000T\u0170\u0001\u0000\u0000\u0000V\u0175\u0001\u0000\u0000\u0000"+
		"X\u017b\u0001\u0000\u0000\u0000Z\u017d\u0001\u0000\u0000\u0000\\\u0182"+
		"\u0001\u0000\u0000\u0000^\u0184\u0001\u0000\u0000\u0000`\u0188\u0001\u0000"+
		"\u0000\u0000b\u018c\u0001\u0000\u0000\u0000d\u018e\u0001\u0000\u0000\u0000"+
		"f\u0192\u0001\u0000\u0000\u0000h\u019e\u0001\u0000\u0000\u0000j\u01a6"+
		"\u0001\u0000\u0000\u0000l\u01ac\u0001\u0000\u0000\u0000n\u01ae\u0001\u0000"+
		"\u0000\u0000p\u01b4\u0001\u0000\u0000\u0000r\u01b6\u0001\u0000\u0000\u0000"+
		"t\u01bc\u0001\u0000\u0000\u0000v\u01bf\u0001\u0000\u0000\u0000x\u01cd"+
		"\u0001\u0000\u0000\u0000z\u01d3\u0001\u0000\u0000\u0000|\u01d5\u0001\u0000"+
		"\u0000\u0000~\u01da\u0001\u0000\u0000\u0000\u0080\u01e2\u0001\u0000\u0000"+
		"\u0000\u0082\u01e4\u0001\u0000\u0000\u0000\u0084\u01e9\u0001\u0000\u0000"+
		"\u0000\u0086\u01eb\u0001\u0000\u0000\u0000\u0088\u01ef\u0001\u0000\u0000"+
		"\u0000\u008a\u01f7\u0001\u0000\u0000\u0000\u008c\u01f9\u0001\u0000\u0000"+
		"\u0000\u008e\u0204\u0001\u0000\u0000\u0000\u0090\u0206\u0001\u0000\u0000"+
		"\u0000\u0092\u020b\u0001\u0000\u0000\u0000\u0094\u0210\u0001\u0000\u0000"+
		"\u0000\u0096\u0217\u0001\u0000\u0000\u0000\u0098\u021b\u0001\u0000\u0000"+
		"\u0000\u009a\u021d\u0001\u0000\u0000\u0000\u009c\u009d\u0003\u0002\u0001"+
		"\u0000\u009d\u009e\u0003\u0006\u0003\u0000\u009e\u009f\u0005\u001f\u0000"+
		"\u0000\u009f\u00a0\u0005\u0000\u0000\u0001\u00a0\u0001\u0001\u0000\u0000"+
		"\u0000\u00a1\u00a2\u0005!\u0000\u0000\u00a2\u00a7\u0003\u0004\u0002\u0000"+
		"\u00a3\u00a4\u0005\u0018\u0000\u0000\u00a4\u00a5\u0003H$\u0000\u00a5\u00a6"+
		"\u0005\u0019\u0000\u0000\u00a6\u00a8\u0001\u0000\u0000\u0000\u00a7\u00a3"+
		"\u0001\u0000\u0000\u0000\u00a7\u00a8\u0001\u0000\u0000\u0000\u00a8\u00a9"+
		"\u0001\u0000\u0000\u0000\u00a9\u00aa\u0005\u001d\u0000\u0000\u00aa\u0003"+
		"\u0001\u0000\u0000\u0000\u00ab\u00ac\u0005=\u0000\u0000\u00ac\u0005\u0001"+
		"\u0000\u0000\u0000\u00ad\u00b1\u0003\b\u0004\u0000\u00ae\u00b1\u0003:"+
		"\u001d\u0000\u00af\u00b1\u0003>\u001f\u0000\u00b0\u00ad\u0001\u0000\u0000"+
		"\u0000\u00b0\u00ae\u0001\u0000\u0000\u0000\u00b0\u00af\u0001\u0000\u0000"+
		"\u0000\u00b1\u00b4\u0001\u0000\u0000\u0000\u00b2\u00b0\u0001\u0000\u0000"+
		"\u0000\u00b2\u00b3\u0001\u0000\u0000\u0000\u00b3\u00b5\u0001\u0000\u0000"+
		"\u0000\u00b4\u00b2\u0001\u0000\u0000\u0000\u00b5\u00b6\u0003\u0086C\u0000"+
		"\u00b6\u0007\u0001\u0000\u0000\u0000\u00b7\u00bb\u00056\u0000\u0000\u00b8"+
		"\u00b9\u0003\n\u0005\u0000\u00b9\u00ba\u0005\u001d\u0000\u0000\u00ba\u00bc"+
		"\u0001\u0000\u0000\u0000\u00bb\u00b8\u0001\u0000\u0000\u0000\u00bc\u00bd"+
		"\u0001\u0000\u0000\u0000\u00bd\u00bb\u0001\u0000\u0000\u0000\u00bd\u00be"+
		"\u0001\u0000\u0000\u0000\u00be\t\u0001\u0000\u0000\u0000\u00bf\u00c0\u0003"+
		"\u0004\u0002\u0000\u00c0\u00c1\u0005\u0016\u0000\u0000\u00c1\u00c2\u0003"+
		"\u000e\u0007\u0000\u00c2\u000b\u0001\u0000\u0000\u0000\u00c3\u00c4\u0005"+
		"7\u0000\u0000\u00c4\u00c5\u0005\u0018\u0000\u0000\u00c5\u00c6\u0003\""+
		"\u0011\u0000\u00c6\u00c7\u0005\u0019\u0000\u0000\u00c7\r\u0001\u0000\u0000"+
		"\u0000\u00c8\u00d0\u0003 \u0010\u0000\u00c9\u00ca\u0003$\u0012\u0000\u00ca"+
		"\u00cb\u0003 \u0010\u0000\u00cb\u00d0\u0001\u0000\u0000\u0000\u00cc\u00d0"+
		"\u0003(\u0014\u0000\u00cd\u00d0\u0003*\u0015\u0000\u00ce\u00d0\u0003\f"+
		"\u0006\u0000\u00cf\u00c8\u0001\u0000\u0000\u0000\u00cf\u00c9\u0001\u0000"+
		"\u0000\u0000\u00cf\u00cc\u0001\u0000\u0000\u0000\u00cf\u00cd\u0001\u0000"+
		"\u0000\u0000\u00cf\u00ce\u0001\u0000\u0000\u0000\u00d0\u000f\u0001\u0000"+
		"\u0000\u0000\u00d1\u00d6\u0003\u0012\t\u0000\u00d2\u00d6\u0003\u001a\r"+
		"\u0000\u00d3\u00d6\u0003\u001c\u000e\u0000\u00d4\u00d6\u0003\u001e\u000f"+
		"\u0000\u00d5\u00d1\u0001\u0000\u0000\u0000\u00d5\u00d2\u0001\u0000\u0000"+
		"\u0000\u00d5\u00d3\u0001\u0000\u0000\u0000\u00d5\u00d4\u0001\u0000\u0000"+
		"\u0000\u00d6\u0011\u0001\u0000\u0000\u0000\u00d7\u00d8\u0005/\u0000\u0000"+
		"\u00d8\u00d9\u0005\u001a\u0000\u0000\u00d9\u00da\u0003\u0016\u000b\u0000"+
		"\u00da\u00db\u0005\u001b\u0000\u0000\u00db\u00dc\u00050\u0000\u0000\u00dc"+
		"\u00dd\u0003\u0010\b\u0000\u00dd\u0013\u0001\u0000\u0000\u0000\u00de\u00df"+
		"\u0003\u0004\u0002\u0000\u00df\u00e2\u0005\u001a\u0000\u0000\u00e0\u00e3"+
		"\u0003.\u0017\u0000\u00e1\u00e3\u0003\u0004\u0002\u0000\u00e2\u00e0\u0001"+
		"\u0000\u0000\u0000\u00e2\u00e1\u0001\u0000\u0000\u0000\u00e3\u00e4\u0001"+
		"\u0000\u0000\u0000\u00e4\u00e5\u0005\u001b\u0000\u0000\u00e5\u0015\u0001"+
		"\u0000\u0000\u0000\u00e6\u00eb\u0003\u0018\f\u0000\u00e7\u00e8\u0005\u001c"+
		"\u0000\u0000\u00e8\u00ea\u0003\u0018\f\u0000\u00e9\u00e7\u0001\u0000\u0000"+
		"\u0000\u00ea\u00ed\u0001\u0000\u0000\u0000\u00eb\u00e9\u0001\u0000\u0000"+
		"\u0000\u00eb\u00ec\u0001\u0000\u0000\u0000\u00ec\u0017\u0001\u0000\u0000"+
		"\u0000\u00ed\u00eb\u0001\u0000\u0000\u0000\u00ee\u00ef\u0003\"\u0011\u0000"+
		"\u00ef\u00f0\u0005 \u0000\u0000\u00f0\u00f1\u0003\"\u0011\u0000\u00f1"+
		"\u0019\u0001\u0000\u0000\u0000\u00f2\u00f3\u00054\u0000\u0000\u00f3\u001b"+
		"\u0001\u0000\u0000\u0000\u00f4\u00f5\u00053\u0000\u0000\u00f5\u001d\u0001"+
		"\u0000\u0000\u0000\u00f6\u00f7\u00051\u0000\u0000\u00f7\u001f\u0001\u0000"+
		"\u0000\u0000\u00f8\u00f9\u0003\"\u0011\u0000\u00f9!\u0001\u0000\u0000"+
		"\u0000\u00fa\u00fb\u0005\u0005\u0000\u0000\u00fb#\u0001\u0000\u0000\u0000"+
		"\u00fc\u00fd\u0007\u0000\u0000\u0000\u00fd%\u0001\u0000\u0000\u0000\u00fe"+
		"\u00ff\u0007\u0001\u0000\u0000\u00ff\'\u0001\u0000\u0000\u0000\u0100\u0101"+
		"\u0005\b\u0000\u0000\u0101)\u0001\u0000\u0000\u0000\u0102\u0103\u0005"+
		"\u0007\u0000\u0000\u0103+\u0001\u0000\u0000\u0000\u0104\u0105\u0005\u0006"+
		"\u0000\u0000\u0105-\u0001\u0000\u0000\u0000\u0106\u0107\u0005\u0005\u0000"+
		"\u0000\u0107/\u0001\u0000\u0000\u0000\u0108\u010b\u00032\u0019\u0000\u0109"+
		"\u010b\u0003\u0012\t\u0000\u010a\u0108\u0001\u0000\u0000\u0000\u010a\u0109"+
		"\u0001\u0000\u0000\u0000\u010b1\u0001\u0000\u0000\u0000\u010c\u0110\u0003"+
		"4\u001a\u0000\u010d\u0110\u00036\u001b\u0000\u010e\u0110\u00038\u001c"+
		"\u0000\u010f\u010c\u0001\u0000\u0000\u0000\u010f\u010d\u0001\u0000\u0000"+
		"\u0000\u010f\u010e\u0001\u0000\u0000\u0000\u01103\u0001\u0000\u0000\u0000"+
		"\u0111\u0112\u0005\u0018\u0000\u0000\u0112\u0113\u0003H$\u0000\u0113\u0114"+
		"\u0005\u0019\u0000\u0000\u01145\u0001\u0000\u0000\u0000\u0115\u0116\u0003"+
		"\u000e\u0007\u0000\u0116\u0117\u0005 \u0000\u0000\u0117\u0118\u0003\u000e"+
		"\u0007\u0000\u01187\u0001\u0000\u0000\u0000\u0119\u011c\u0003\u0004\u0002"+
		"\u0000\u011a\u011c\u0007\u0002\u0000\u0000\u011b\u0119\u0001\u0000\u0000"+
		"\u0000\u011b\u011a\u0001\u0000\u0000\u0000\u011c9\u0001\u0000\u0000\u0000"+
		"\u011d\u011e\u0005.\u0000\u0000\u011e\u0123\u0003<\u001e\u0000\u011f\u0120"+
		"\u0005\u001d\u0000\u0000\u0120\u0122\u0003<\u001e\u0000\u0121\u011f\u0001"+
		"\u0000\u0000\u0000\u0122\u0125\u0001\u0000\u0000\u0000\u0123\u0121\u0001"+
		"\u0000\u0000\u0000\u0123\u0124\u0001\u0000\u0000\u0000\u0124\u0126\u0001"+
		"\u0000\u0000\u0000\u0125\u0123\u0001\u0000\u0000\u0000\u0126\u0127\u0005"+
		"\u001d\u0000\u0000\u0127;\u0001\u0000\u0000\u0000\u0128\u0129\u0003H$"+
		"\u0000\u0129\u012a\u0005\u001e\u0000\u0000\u012a\u012b\u00030\u0018\u0000"+
		"\u012b=\u0001\u0000\u0000\u0000\u012c\u012d\u0003@ \u0000\u012d\u012e"+
		"\u0005\u001d\u0000\u0000\u012e?\u0001\u0000\u0000\u0000\u012f\u0132\u0003"+
		"J%\u0000\u0130\u0132\u0003L&\u0000\u0131\u012f\u0001\u0000\u0000\u0000"+
		"\u0131\u0130\u0001\u0000\u0000\u0000\u0132A\u0001\u0000\u0000\u0000\u0133"+
		"\u0134\u0005\u0018\u0000\u0000\u0134\u0139\u0003D\"\u0000\u0135\u0136"+
		"\u0005\u001d\u0000\u0000\u0136\u0138\u0003D\"\u0000\u0137\u0135\u0001"+
		"\u0000\u0000\u0000\u0138\u013b\u0001\u0000\u0000\u0000\u0139\u0137\u0001"+
		"\u0000\u0000\u0000\u0139\u013a\u0001\u0000\u0000\u0000\u013a\u013c\u0001"+
		"\u0000\u0000\u0000\u013b\u0139\u0001\u0000\u0000\u0000\u013c\u013d\u0005"+
		"\u0019\u0000\u0000\u013dC\u0001\u0000\u0000\u0000\u013e\u0142\u0003F#"+
		"\u0000\u013f\u0140\u0005.\u0000\u0000\u0140\u0142\u0003F#\u0000\u0141"+
		"\u013e\u0001\u0000\u0000\u0000\u0141\u013f\u0001\u0000\u0000\u0000\u0142"+
		"E\u0001\u0000\u0000\u0000\u0143\u0144\u0003H$\u0000\u0144\u0145\u0005"+
		"\u001e\u0000\u0000\u0145\u0146\u0003\u0010\b\u0000\u0146\u0149\u0001\u0000"+
		"\u0000\u0000\u0147\u0149\u0003\u0082A\u0000\u0148\u0143\u0001\u0000\u0000"+
		"\u0000\u0148\u0147\u0001\u0000\u0000\u0000\u0149G\u0001\u0000\u0000\u0000"+
		"\u014a\u014f\u0003\u0004\u0002\u0000\u014b\u014c\u0005\u001c\u0000\u0000"+
		"\u014c\u014e\u0003\u0004\u0002\u0000\u014d\u014b\u0001\u0000\u0000\u0000"+
		"\u014e\u0151\u0001\u0000\u0000\u0000\u014f\u014d\u0001\u0000\u0000\u0000"+
		"\u014f\u0150\u0001\u0000\u0000\u0000\u0150I\u0001\u0000\u0000\u0000\u0151"+
		"\u014f\u0001\u0000\u0000\u0000\u0152\u0153\u0005\"\u0000\u0000\u0153\u0154"+
		"\u0003\u0004\u0002\u0000\u0154\u0155\u0003B!\u0000\u0155\u0156\u0005\u001e"+
		"\u0000\u0000\u0156\u0157\u0003\u0010\b\u0000\u0157\u0158\u0005\u001d\u0000"+
		"\u0000\u0158\u0159\u0003\u0006\u0003\u0000\u0159K\u0001\u0000\u0000\u0000"+
		"\u015a\u015b\u0005#\u0000\u0000\u015b\u015c\u0003\u0004\u0002\u0000\u015c"+
		"\u015d\u0003B!\u0000\u015d\u015e\u0005\u001d\u0000\u0000\u015e\u015f\u0003"+
		"\u0006\u0003\u0000\u015fM\u0001\u0000\u0000\u0000\u0160\u0164\u0003P("+
		"\u0000\u0161\u0164\u0003Z-\u0000\u0162\u0164\u0003`0\u0000\u0163\u0160"+
		"\u0001\u0000\u0000\u0000\u0163\u0161\u0001\u0000\u0000\u0000\u0163\u0162"+
		"\u0001\u0000\u0000\u0000\u0164O\u0001\u0000\u0000\u0000\u0165\u0166\u0003"+
		"R)\u0000\u0166\u0167\u0005\u0018\u0000\u0000\u0167\u016a\u0003T*\u0000"+
		"\u0168\u0169\u0005\u001c\u0000\u0000\u0169\u016b\u0003V+\u0000\u016a\u0168"+
		"\u0001\u0000\u0000\u0000\u016a\u016b\u0001\u0000\u0000\u0000\u016b\u016c"+
		"\u0001\u0000\u0000\u0000\u016c\u016d\u0005\u0019\u0000\u0000\u016dQ\u0001"+
		"\u0000\u0000\u0000\u016e\u016f\u0007\u0003\u0000\u0000\u016fS\u0001\u0000"+
		"\u0000\u0000\u0170\u0171\u0003(\u0014\u0000\u0171U\u0001\u0000\u0000\u0000"+
		"\u0172\u0176\u0003X,\u0000\u0173\u0176\u0003\u0004\u0002\u0000\u0174\u0176"+
		"\u0003\u0014\n\u0000\u0175\u0172\u0001\u0000\u0000\u0000\u0175\u0173\u0001"+
		"\u0000\u0000\u0000\u0175\u0174\u0001\u0000\u0000\u0000\u0176W\u0001\u0000"+
		"\u0000\u0000\u0177\u017c\u0003(\u0014\u0000\u0178\u017c\u0003*\u0015\u0000"+
		"\u0179\u017c\u0003.\u0017\u0000\u017a\u017c\u0003,\u0016\u0000\u017b\u0177"+
		"\u0001\u0000\u0000\u0000\u017b\u0178\u0001\u0000\u0000\u0000\u017b\u0179"+
		"\u0001\u0000\u0000\u0000\u017b\u017a\u0001\u0000\u0000\u0000\u017cY\u0001"+
		"\u0000\u0000\u0000\u017d\u017e\u0003\\.\u0000\u017e\u017f\u0005\u0018"+
		"\u0000\u0000\u017f\u0180\u0003^/\u0000\u0180\u0181\u0005\u0019\u0000\u0000"+
		"\u0181[\u0001\u0000\u0000\u0000\u0182\u0183\u0007\u0004\u0000\u0000\u0183"+
		"]\u0001\u0000\u0000\u0000\u0184\u0185\u0003\u0004\u0002\u0000\u0185_\u0001"+
		"\u0000\u0000\u0000\u0186\u0189\u0003b1\u0000\u0187\u0189\u0003\u0084B"+
		"\u0000\u0188\u0186\u0001\u0000\u0000\u0000\u0188\u0187\u0001\u0000\u0000"+
		"\u0000\u0189a\u0001\u0000\u0000\u0000\u018a\u018d\u0003d2\u0000\u018b"+
		"\u018d\u0003\u0082A\u0000\u018c\u018a\u0001\u0000\u0000\u0000\u018c\u018b"+
		"\u0001\u0000\u0000\u0000\u018dc\u0001\u0000\u0000\u0000\u018e\u018f\u0003"+
		"f3\u0000\u018f\u0190\u0005\t\u0000\u0000\u0190\u0191\u0003j5\u0000\u0191"+
		"e\u0001\u0000\u0000\u0000\u0192\u019b\u0003\u0004\u0002\u0000\u0193\u0194"+
		"\u0005\u001a\u0000\u0000\u0194\u0195\u0003h4\u0000\u0195\u0196\u0005\u001b"+
		"\u0000\u0000\u0196\u019a\u0001\u0000\u0000\u0000\u0197\u0198\u0005\u001f"+
		"\u0000\u0000\u0198\u019a\u0003\u0004\u0002\u0000\u0199\u0193\u0001\u0000"+
		"\u0000\u0000\u0199\u0197\u0001\u0000\u0000\u0000\u019a\u019d\u0001\u0000"+
		"\u0000\u0000\u019b\u0199\u0001\u0000\u0000\u0000\u019b\u019c\u0001\u0000"+
		"\u0000\u0000\u019cg\u0001\u0000\u0000\u0000\u019d\u019b\u0001\u0000\u0000"+
		"\u0000\u019e\u01a3\u0003j5\u0000\u019f\u01a0\u0005\u001c\u0000\u0000\u01a0"+
		"\u01a2\u0003j5\u0000\u01a1\u019f\u0001\u0000\u0000\u0000\u01a2\u01a5\u0001"+
		"\u0000\u0000\u0000\u01a3\u01a1\u0001\u0000\u0000\u0000\u01a3\u01a4\u0001"+
		"\u0000\u0000\u0000\u01a4i\u0001\u0000\u0000\u0000\u01a5\u01a3\u0001\u0000"+
		"\u0000\u0000\u01a6\u01aa\u0003n7\u0000\u01a7\u01a8\u0003l6\u0000\u01a8"+
		"\u01a9\u0003j5\u0000\u01a9\u01ab\u0001\u0000\u0000\u0000\u01aa\u01a7\u0001"+
		"\u0000\u0000\u0000\u01aa\u01ab\u0001\u0000\u0000\u0000\u01abk\u0001\u0000"+
		"\u0000\u0000\u01ac\u01ad\u0007\u0005\u0000\u0000\u01adm\u0001\u0000\u0000"+
		"\u0000\u01ae\u01b2\u0003r9\u0000\u01af\u01b0\u0003p8\u0000\u01b0\u01b1"+
		"\u0003n7\u0000\u01b1\u01b3\u0001\u0000\u0000\u0000\u01b2\u01af\u0001\u0000"+
		"\u0000\u0000\u01b2\u01b3\u0001\u0000\u0000\u0000\u01b3o\u0001\u0000\u0000"+
		"\u0000\u01b4\u01b5\u0007\u0006\u0000\u0000\u01b5q\u0001\u0000\u0000\u0000"+
		"\u01b6\u01ba\u0003v;\u0000\u01b7\u01b8\u0003t:\u0000\u01b8\u01b9\u0003"+
		"r9\u0000\u01b9\u01bb\u0001\u0000\u0000\u0000\u01ba\u01b7\u0001\u0000\u0000"+
		"\u0000\u01ba\u01bb\u0001\u0000\u0000\u0000\u01bbs\u0001\u0000\u0000\u0000"+
		"\u01bc\u01bd\u0007\u0007\u0000\u0000\u01bdu\u0001\u0000\u0000\u0000\u01be"+
		"\u01c0\u0007\u0000\u0000\u0000\u01bf\u01be\u0001\u0000\u0000\u0000\u01bf"+
		"\u01c0\u0001\u0000\u0000\u0000\u01c0\u01c1\u0001\u0000\u0000\u0000\u01c1"+
		"\u01c2\u0003x<\u0000\u01c2w\u0001\u0000\u0000\u0000\u01c3\u01ce\u0003"+
		"f3\u0000\u01c4\u01c5\u0005\u0018\u0000\u0000\u01c5\u01c6\u0003j5\u0000"+
		"\u01c6\u01c7\u0005\u0019\u0000\u0000\u01c7\u01ce\u0001\u0000\u0000\u0000"+
		"\u01c8\u01ce\u0003|>\u0000\u01c9\u01ce\u0003z=\u0000\u01ca\u01cb\u0005"+
		"\u0010\u0000\u0000\u01cb\u01ce\u0003x<\u0000\u01cc\u01ce\u0003&\u0013"+
		"\u0000\u01cd\u01c3\u0001\u0000\u0000\u0000\u01cd\u01c4\u0001\u0000\u0000"+
		"\u0000\u01cd\u01c8\u0001\u0000\u0000\u0000\u01cd\u01c9\u0001\u0000\u0000"+
		"\u0000\u01cd\u01ca\u0001\u0000\u0000\u0000\u01cd\u01cc\u0001\u0000\u0000"+
		"\u0000\u01cey\u0001\u0000\u0000\u0000\u01cf\u01d4\u0003 \u0010\u0000\u01d0"+
		"\u01d4\u0003\f\u0006\u0000\u01d1\u01d4\u0003(\u0014\u0000\u01d2\u01d4"+
		"\u00055\u0000\u0000\u01d3\u01cf\u0001\u0000\u0000\u0000\u01d3\u01d0\u0001"+
		"\u0000\u0000\u0000\u01d3\u01d1\u0001\u0000\u0000\u0000\u01d3\u01d2\u0001"+
		"\u0000\u0000\u0000\u01d4{\u0001\u0000\u0000\u0000\u01d5\u01d6\u0003\u0004"+
		"\u0002\u0000\u01d6\u01d7\u0005\u0018\u0000\u0000\u01d7\u01d8\u0003~?\u0000"+
		"\u01d8\u01d9\u0005\u0019\u0000\u0000\u01d9}\u0001\u0000\u0000\u0000\u01da"+
		"\u01df\u0003\u0080@\u0000\u01db\u01dc\u0005\u001c\u0000\u0000\u01dc\u01de"+
		"\u0003\u0080@\u0000\u01dd\u01db\u0001\u0000\u0000\u0000\u01de\u01e1\u0001"+
		"\u0000\u0000\u0000\u01df\u01dd\u0001\u0000\u0000\u0000\u01df\u01e0\u0001"+
		"\u0000\u0000\u0000\u01e0\u007f\u0001\u0000\u0000\u0000\u01e1\u01df\u0001"+
		"\u0000\u0000\u0000\u01e2\u01e3\u0003j5\u0000\u01e3\u0081\u0001\u0000\u0000"+
		"\u0000\u01e4\u01e5\u0001\u0000\u0000\u0000\u01e5\u0083\u0001\u0000\u0000"+
		"\u0000\u01e6\u01ea\u0003\u0086C\u0000\u01e7\u01ea\u0003\u008aE\u0000\u01e8"+
		"\u01ea\u0003\u008eG\u0000\u01e9\u01e6\u0001\u0000\u0000\u0000\u01e9\u01e7"+
		"\u0001\u0000\u0000\u0000\u01e9\u01e8\u0001\u0000\u0000\u0000\u01ea\u0085"+
		"\u0001\u0000\u0000\u0000\u01eb\u01ec\u0005\u0001\u0000\u0000\u01ec\u01ed"+
		"\u0003\u0088D\u0000\u01ed\u01ee\u0005\u0002\u0000\u0000\u01ee\u0087\u0001"+
		"\u0000\u0000\u0000\u01ef\u01f4\u0003N\'\u0000\u01f0\u01f1\u0005\u001d"+
		"\u0000\u0000\u01f1\u01f3\u0003N\'\u0000\u01f2\u01f0\u0001\u0000\u0000"+
		"\u0000\u01f3\u01f6\u0001\u0000\u0000\u0000\u01f4\u01f2\u0001\u0000\u0000"+
		"\u0000\u01f4\u01f5\u0001\u0000\u0000\u0000\u01f5\u0089\u0001\u0000\u0000"+
		"\u0000\u01f6\u01f4\u0001\u0000\u0000\u0000\u01f7\u01f8\u0003\u008cF\u0000"+
		"\u01f8\u008b\u0001\u0000\u0000\u0000\u01f9\u01fa\u0005$\u0000\u0000\u01fa"+
		"\u01fb\u0003j5\u0000\u01fb\u01fc\u0005%\u0000\u0000\u01fc\u01ff\u0003"+
		"N\'\u0000\u01fd\u01fe\u0005&\u0000\u0000\u01fe\u0200\u0003N\'\u0000\u01ff"+
		"\u01fd\u0001\u0000\u0000\u0000\u01ff\u0200\u0001\u0000\u0000\u0000\u0200"+
		"\u008d\u0001\u0000\u0000\u0000\u0201\u0205\u0003\u0090H\u0000\u0202\u0205"+
		"\u0003\u0092I\u0000\u0203\u0205\u0003\u0094J\u0000\u0204\u0201\u0001\u0000"+
		"\u0000\u0000\u0204\u0202\u0001\u0000\u0000\u0000\u0204\u0203\u0001\u0000"+
		"\u0000\u0000\u0205\u008f\u0001\u0000\u0000\u0000\u0206\u0207\u0005(\u0000"+
		"\u0000\u0207\u0208\u0003j5\u0000\u0208\u0209\u0005,\u0000\u0000\u0209"+
		"\u020a\u0003N\'\u0000\u020a\u0091\u0001\u0000\u0000\u0000\u020b\u020c"+
		"\u0005*\u0000\u0000\u020c\u020d\u0003\u0088D\u0000\u020d\u020e\u0005\'"+
		"\u0000\u0000\u020e\u020f\u0003j5\u0000\u020f\u0093\u0001\u0000\u0000\u0000"+
		"\u0210\u0211\u0005)\u0000\u0000\u0211\u0212\u0003\u0004\u0002\u0000\u0212"+
		"\u0213\u0005\t\u0000\u0000\u0213\u0214\u0003\u0096K\u0000\u0214\u0215"+
		"\u0005,\u0000\u0000\u0215\u0216\u0003N\'\u0000\u0216\u0095\u0001\u0000"+
		"\u0000\u0000\u0217\u0218\u0003\u0098L\u0000\u0218\u0219\u0007\b\u0000"+
		"\u0000\u0219\u021a\u0003\u009aM\u0000\u021a\u0097\u0001\u0000\u0000\u0000"+
		"\u021b\u021c\u0003j5\u0000\u021c\u0099\u0001\u0000\u0000\u0000\u021d\u021e"+
		"\u0003j5\u0000\u021e\u009b\u0001\u0000\u0000\u0000%\u00a7\u00b0\u00b2"+
		"\u00bd\u00cf\u00d5\u00e2\u00eb\u010a\u010f\u011b\u0123\u0131\u0139\u0141"+
		"\u0148\u014f\u0163\u016a\u0175\u017b\u0188\u018c\u0199\u019b\u01a3\u01aa"+
		"\u01b2\u01ba\u01bf\u01cd\u01d3\u01df\u01e9\u01f4\u01ff\u0204";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}