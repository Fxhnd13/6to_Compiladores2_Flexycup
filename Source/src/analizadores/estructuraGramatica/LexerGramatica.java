// DO NOT EDIT
// Generated by JFlex 1.8.2 http://jflex.de/
// source: C:/Users/jose_/Desktop/Universidad/Compiladores 2/Flexycup/src/analizadores/estructuraGramatica/LexerGramatica.flex

package analizadores.estructuraGramatica;

import analizadores.objetos.ErrorAnalisis;
import java.util.ArrayList;
import java.util.List;
import java_cup.runtime.Symbol;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


// See https://github.com/jflex-de/jflex/issues/222

/**
 *
 * @author jose_
 */
@SuppressWarnings("FallThrough")
public class LexerGramatica implements java_cup.runtime.Scanner {

  /** This character denotes the end of file. */
  public static final int YYEOF = -1;

  /** Initial size of the lookahead buffer. */
  private static final int ZZ_BUFFERSIZE = 16384;

  // Lexical states.

    /**
     *
     */
  public static final int YYINITIAL = 0;

    /**
     *
     */
    public static final int JAVACODE = 2;

    /**
     *
     */
    public static final int EXPRESIONES_REGULARES = 4;

    /**
     *
     */
    public static final int DECLARACION_SIMBOLOS = 6;

    /**
     *
     */
    public static final int REGLAS_SEMANTICAS = 8;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = {
     0,  0,  1,  1,  2,  2,  3,  3,  4, 4
  };

  /**
   * Top-level table for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_TOP = zzUnpackcmap_top();

  private static final String ZZ_CMAP_TOP_PACKED_0 =
    "\1\0\u10ff\u0100";

  private static int [] zzUnpackcmap_top() {
    int [] result = new int[4352];
    int offset = 0;
    offset = zzUnpackcmap_top(ZZ_CMAP_TOP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_top(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Second-level tables for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_BLOCKS = zzUnpackcmap_blocks();

  private static final String ZZ_CMAP_BLOCKS_PACKED_0 =
    "\11\0\1\1\1\2\1\0\1\1\1\3\22\0\1\1"+
    "\1\0\1\4\2\0\1\5\1\6\1\0\1\7\1\10"+
    "\1\11\1\12\1\13\1\14\1\15\1\16\1\17\11\20"+
    "\1\21\1\22\1\0\1\23\1\0\1\24\1\0\32\25"+
    "\1\26\1\27\1\30\1\0\1\31\1\0\1\32\1\33"+
    "\1\34\1\35\1\36\3\37\1\40\2\37\1\41\1\42"+
    "\1\43\1\44\2\37\1\45\1\46\1\47\1\50\1\51"+
    "\1\37\1\52\1\37\1\53\1\54\1\55\1\56\u0182\0";

  private static int [] zzUnpackcmap_blocks() {
    int [] result = new int[512];
    int offset = 0;
    offset = zzUnpackcmap_blocks(ZZ_CMAP_BLOCKS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_blocks(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /**
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\5\0\1\1\1\2\2\3\2\1\2\4\1\5\1\6"+
    "\11\7\2\10\3\11\1\12\1\13\1\14\1\15\1\16"+
    "\1\11\1\17\1\20\1\21\1\11\1\22\1\23\1\24"+
    "\1\1\1\25\1\26\6\23\1\5\1\1\1\27\1\0"+
    "\1\30\1\0\5\7\1\31\3\7\1\32\1\0\1\33"+
    "\2\0\1\34\1\35\1\36\1\37\2\23\1\31\2\23"+
    "\1\40\2\0\2\30\1\41\11\7\1\42\2\0\4\23"+
    "\1\43\1\0\6\7\1\44\2\7\2\0\2\23\1\44"+
    "\1\23\1\45\7\7\1\46\1\47\3\23\1\50\1\51"+
    "\2\7\1\52\2\7\1\50\1\51\1\23\3\7\1\53"+
    "\1\23\2\7\2\54\1\55\2\7\1\56";

  private static int [] zzUnpackAction() {
    int [] result = new int[155];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\57\0\136\0\215\0\274\0\353\0\u011a\0\353"+
    "\0\u0149\0\u0178\0\u01a7\0\u01d6\0\u0205\0\353\0\353\0\u0234"+
    "\0\u0263\0\u0292\0\u02c1\0\u02f0\0\u031f\0\u034e\0\u037d\0\u03ac"+
    "\0\353\0\u03db\0\353\0\u040a\0\u0439\0\353\0\353\0\353"+
    "\0\353\0\353\0\u01a7\0\353\0\353\0\u0468\0\u0497\0\353"+
    "\0\u04c6\0\353\0\u04f5\0\353\0\u0524\0\u0553\0\u0582\0\u05b1"+
    "\0\u05e0\0\u060f\0\u063e\0\u066d\0\u069c\0\353\0\u06cb\0\u06fa"+
    "\0\u0729\0\u0758\0\u0787\0\u07b6\0\u07e5\0\u0814\0\u0843\0\u0872"+
    "\0\u08a1\0\u08d0\0\353\0\u08ff\0\353\0\u092e\0\u095d\0\353"+
    "\0\353\0\353\0\353\0\u098c\0\u09bb\0\u04c6\0\u09ea\0\u0a19"+
    "\0\353\0\u0a48\0\u0a77\0\353\0\u0aa6\0\u01d6\0\u0ad5\0\u0b04"+
    "\0\u0b33\0\u0b62\0\u0b91\0\u0bc0\0\u0bef\0\u0c1e\0\u0c4d\0\353"+
    "\0\u0c7c\0\u0cab\0\u0cda\0\u0d09\0\u0d38\0\u0d67\0\353\0\u0d96"+
    "\0\u0dc5\0\u0df4\0\u0e23\0\u0e52\0\u0e81\0\u0eb0\0\u0234\0\u0edf"+
    "\0\u0f0e\0\u0f3d\0\u0f6c\0\u0f9b\0\u0fca\0\u04c6\0\u0ff9\0\u0234"+
    "\0\u1028\0\u1057\0\u1086\0\u10b5\0\u10e4\0\u1113\0\u1142\0\353"+
    "\0\353\0\u1171\0\u11a0\0\u11cf\0\u0234\0\u0234\0\u11fe\0\u122d"+
    "\0\u0234\0\u125c\0\u128b\0\u04c6\0\u04c6\0\u12ba\0\u12e9\0\u1318"+
    "\0\u1347\0\u0234\0\u1376\0\u13a5\0\u13d4\0\u0234\0\u04c6\0\u0234"+
    "\0\u1403\0\u1432\0\u0234";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[155];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /**
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\6\1\7\1\10\1\11\1\6\1\12\10\6\1\13"+
    "\1\14\1\15\1\16\1\17\2\6\1\20\3\6\1\20"+
    "\1\21\1\20\1\22\1\20\1\23\2\20\1\24\1\20"+
    "\1\25\1\20\1\26\1\20\1\27\1\20\1\30\2\20"+
    "\3\6\5\31\1\32\51\31\1\33\1\7\1\10\1\11"+
    "\1\34\1\35\1\36\1\37\1\40\1\41\1\42\3\33"+
    "\1\43\1\14\1\15\1\33\1\17\1\44\1\45\1\33"+
    "\1\46\1\47\1\50\23\51\1\33\1\52\1\33\1\6"+
    "\1\7\1\10\1\11\1\6\1\53\5\6\1\54\2\6"+
    "\1\13\3\6\1\17\2\6\1\55\3\6\1\56\2\51"+
    "\1\57\1\51\1\60\4\51\1\61\1\51\1\62\1\51"+
    "\1\63\4\51\4\6\1\7\1\10\1\11\12\6\1\13"+
    "\2\6\1\64\1\17\2\6\1\20\3\6\23\20\1\65"+
    "\2\6\60\0\1\7\57\0\1\10\61\0\1\66\62\0"+
    "\1\67\4\0\1\70\55\0\1\71\56\0\1\71\1\0"+
    "\2\15\55\0\2\20\4\0\1\20\3\0\23\20\22\0"+
    "\2\20\4\0\1\20\3\0\17\20\1\72\3\20\22\0"+
    "\2\20\4\0\1\20\3\0\1\20\1\73\21\20\22\0"+
    "\2\20\4\0\1\20\3\0\12\20\1\74\6\20\1\75"+
    "\1\20\22\0\2\20\4\0\1\20\3\0\1\20\1\76"+
    "\21\20\22\0\2\20\4\0\1\20\3\0\13\20\1\77"+
    "\7\20\22\0\2\20\4\0\1\20\3\0\5\20\1\100"+
    "\15\20\22\0\2\20\4\0\1\20\3\0\5\20\1\101"+
    "\15\20\22\0\2\20\4\0\1\20\3\0\5\20\1\102"+
    "\15\20\10\0\1\103\51\0\4\104\1\0\52\104\5\0"+
    "\1\105\70\0\2\106\4\0\1\107\4\0\22\107\36\0"+
    "\1\110\7\0\1\111\3\0\1\112\26\0\2\51\10\0"+
    "\23\51\10\0\1\113\70\0\2\55\4\0\1\55\3\0"+
    "\1\55\44\0\2\56\4\0\1\55\3\0\1\56\22\51"+
    "\22\0\2\51\10\0\1\51\1\114\21\51\22\0\2\51"+
    "\10\0\12\51\1\115\10\51\22\0\2\51\10\0\13\51"+
    "\1\116\7\51\22\0\2\51\10\0\5\51\1\117\15\51"+
    "\22\0\2\51\10\0\5\51\1\120\15\51\24\0\1\121"+
    "\35\0\56\122\1\0\11\123\1\0\45\123\2\70\1\124"+
    "\1\125\53\70\17\0\2\126\55\0\2\20\4\0\1\20"+
    "\3\0\16\20\1\127\4\20\22\0\2\20\4\0\1\20"+
    "\3\0\4\20\1\130\16\20\22\0\2\20\4\0\1\20"+
    "\3\0\16\20\1\131\4\20\22\0\2\20\4\0\1\20"+
    "\3\0\16\20\1\132\4\20\22\0\2\20\4\0\1\20"+
    "\3\0\12\20\1\133\10\20\22\0\2\20\4\0\1\20"+
    "\3\0\11\20\1\134\11\20\22\0\2\20\4\0\1\20"+
    "\3\0\1\20\1\135\21\20\22\0\2\20\4\0\1\20"+
    "\3\0\14\20\1\136\6\20\22\0\2\20\4\0\1\20"+
    "\3\0\14\20\1\137\6\20\3\0\4\104\1\140\52\104"+
    "\14\0\1\141\56\0\1\142\61\0\2\51\10\0\4\51"+
    "\1\143\16\51\22\0\2\51\10\0\16\51\1\144\4\51"+
    "\22\0\2\51\10\0\1\51\1\145\21\51\22\0\2\51"+
    "\10\0\14\51\1\146\6\51\3\0\56\122\1\147\11\123"+
    "\1\150\45\123\2\0\1\124\73\0\2\20\4\0\1\20"+
    "\3\0\13\20\1\151\7\20\22\0\2\20\4\0\1\20"+
    "\3\0\5\20\1\152\15\20\22\0\2\20\4\0\1\20"+
    "\3\0\5\20\1\153\15\20\22\0\2\20\4\0\1\20"+
    "\3\0\5\20\1\154\15\20\22\0\2\20\4\0\1\20"+
    "\3\0\22\20\1\155\22\0\2\20\4\0\1\20\3\0"+
    "\2\20\1\156\20\20\22\0\2\20\4\0\1\20\3\0"+
    "\10\20\1\157\12\20\22\0\2\20\4\0\1\20\3\0"+
    "\11\20\1\160\11\20\22\0\2\20\4\0\1\20\3\0"+
    "\15\20\1\161\5\20\22\0\2\162\63\0\1\163\4\0"+
    "\22\163\22\0\2\51\10\0\5\51\1\164\15\51\22\0"+
    "\2\51\10\0\5\51\1\165\15\51\22\0\2\51\10\0"+
    "\10\51\1\166\12\51\22\0\2\51\10\0\11\51\1\167"+
    "\11\51\3\0\11\123\1\150\4\123\1\124\40\123\17\0"+
    "\2\20\4\0\1\20\3\0\14\20\1\170\6\20\22\0"+
    "\2\20\4\0\1\20\3\0\12\20\1\171\10\20\22\0"+
    "\2\20\4\0\1\20\3\0\14\20\1\172\6\20\22\0"+
    "\2\20\4\0\1\20\3\0\12\20\1\173\10\20\22\0"+
    "\2\20\4\0\1\20\3\0\1\20\1\174\21\20\22\0"+
    "\2\20\4\0\1\20\3\0\14\20\1\175\6\20\22\0"+
    "\2\20\4\0\1\20\3\0\7\20\1\176\13\20\22\0"+
    "\2\20\4\0\1\20\3\0\7\20\1\177\13\20\33\0"+
    "\1\200\56\0\1\201\45\0\2\51\10\0\12\51\1\202"+
    "\10\51\22\0\2\51\10\0\14\51\1\203\6\51\22\0"+
    "\2\51\10\0\7\51\1\204\13\51\22\0\2\20\4\0"+
    "\1\20\3\0\1\20\1\205\21\20\22\0\2\20\4\0"+
    "\1\20\3\0\13\20\1\206\7\20\22\0\2\20\4\0"+
    "\1\20\3\0\15\20\1\207\5\20\22\0\2\20\4\0"+
    "\1\20\3\0\11\20\1\210\11\20\22\0\2\20\4\0"+
    "\1\20\3\0\5\20\1\211\15\20\22\0\2\20\4\0"+
    "\1\20\3\0\12\20\1\212\10\20\22\0\2\20\4\0"+
    "\1\20\3\0\13\20\1\213\7\20\22\0\2\51\10\0"+
    "\1\51\1\214\21\51\22\0\2\51\10\0\13\51\1\215"+
    "\7\51\22\0\2\51\10\0\12\51\1\216\10\51\22\0"+
    "\2\20\4\0\1\20\3\0\7\20\1\217\13\20\22\0"+
    "\2\20\4\0\1\20\3\0\7\20\1\220\13\20\22\0"+
    "\2\20\4\0\1\20\3\0\1\20\1\221\21\20\22\0"+
    "\2\20\4\0\1\20\3\0\12\20\1\222\10\20\22\0"+
    "\2\51\10\0\1\51\1\223\21\51\22\0\2\20\4\0"+
    "\1\20\3\0\13\20\1\224\7\20\22\0\2\20\4\0"+
    "\1\20\3\0\5\20\1\225\15\20\22\0\2\20\4\0"+
    "\1\20\3\0\10\20\1\226\12\20\22\0\2\51\10\0"+
    "\10\51\1\227\12\51\22\0\2\20\4\0\1\20\3\0"+
    "\12\20\1\230\10\20\22\0\2\20\4\0\1\20\3\0"+
    "\12\20\1\231\10\20\22\0\2\20\4\0\1\20\3\0"+
    "\16\20\1\232\4\20\22\0\2\20\4\0\1\20\3\0"+
    "\13\20\1\233\7\20\3\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[5217];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** Error code for "Unknown internal scanner error". */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  /** Error code for "could not match input". */
  private static final int ZZ_NO_MATCH = 1;
  /** Error code for "pushback value was too large". */
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /**
   * Error messages for {@link #ZZ_UNKNOWN_ERROR}, {@link #ZZ_NO_MATCH}, and
   * {@link #ZZ_PUSHBACK_2BIG} respectively.
   */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state {@code aState}
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\5\0\1\11\1\1\1\11\5\1\2\11\11\1\1\11"+
    "\1\1\1\11\2\1\5\11\1\1\2\11\2\1\1\11"+
    "\1\1\1\11\1\1\1\11\11\1\1\11\1\0\1\1"+
    "\1\0\11\1\1\11\1\0\1\11\2\0\4\11\5\1"+
    "\1\11\2\0\1\11\13\1\1\11\2\0\4\1\1\11"+
    "\1\0\11\1\2\0\14\1\2\11\32\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[155];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** Input device. */
  private java.io.Reader zzReader;

  /** Current state of the DFA. */
  private int zzState;

  /** Current lexical state. */
  private int zzLexicalState = YYINITIAL;

  /**
   * This buffer contains the current text to be matched and is the source of the {@link #yytext()}
   * string.
   */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** Text position at the last accepting state. */
  private int zzMarkedPos;

  /** Current text position in the buffer. */
  private int zzCurrentPos;

  /** Marks the beginning of the {@link #yytext()} string in the buffer. */
  private int zzStartRead;

  /** Marks the last character in the buffer, that has been read from input. */
  private int zzEndRead;

  /**
   * Whether the scanner is at the end of file.
   * @see #yyatEOF
   */
  private boolean zzAtEOF;

  /**
   * The number of occupied positions in {@link #zzBuffer} beyond {@link #zzEndRead}.
   *
   * <p>When a lead/high surrogate has been read from the input stream into the final
   * {@link #zzBuffer} position, this will have a value of 1; otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /** Number of newlines encountered up to the start of the matched text. */
  private int yyline;

  /** Number of characters from the last newline up to the start of the matched text. */
  private int yycolumn;

  /** Number of characters up to the start of the matched text. */
  @SuppressWarnings("unused")
  private long yychar;

  /** Whether the scanner is currently at the beginning of a line. */
  @SuppressWarnings("unused")
  private boolean zzAtBOL = true;

  /** Whether the user-EOF-code has already been executed. */
  private boolean zzEOFDone;

  /* user code: */
    
    private List<ErrorAnalisis> errores;
    private boolean leyendo = true;
    private DefaultTableModel tablaTokens;

    private Symbol symbol(int linea, int columna, String lexema, int type){
        Symbol simbolo = new Symbol(type, linea, columna, lexema);
        tablaTokens.addRow(new String[] {sym.terminalNames[type], lexema, String.valueOf(linea), String.valueOf(columna)});
        //System.out.println(type+"|"+lexema+"|<"+linea+","+columna+">");
        return simbolo;
    }

    private Symbol symbol(int linea, int columna, int type){
        Symbol simbolo = new Symbol(type,linea,columna);
        tablaTokens.addRow(new String[] {sym.terminalNames[type],"Sin definir", String.valueOf(linea), String.valueOf(columna)});
        //System.out.println(type+"|"+yytext()+"|<"+linea+","+columna+">");
        return simbolo;
    }

    /**
     *
     * @return
     */
    public List<ErrorAnalisis> getErrores(){
        return errores;
    }

    /**
     *
     * @return
     */
    public boolean isAnalizando(){
        return leyendo;
    }
    
    /**
     *
     * @param tablaTokens
     */
    public void setTablaTokens(JTable tablaTokens){
        this.tablaTokens = (DefaultTableModel) tablaTokens.getModel();
    }
    
    /**
     *
     * @return
     */
    public JTable getTablaTokens(){ return new JTable(tablaTokens); }



  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public LexerGramatica(java.io.Reader in) {
      errores = new ArrayList<ErrorAnalisis>();
    this.zzReader = in;
  }

  /**
   * Translates raw input code points to DFA table row
   */
  private static int zzCMap(int input) {
    int offset = input & 255;
    return offset == input ? ZZ_CMAP_BLOCKS[offset] : ZZ_CMAP_BLOCKS[ZZ_CMAP_TOP[input >> 8] | offset];
  }

  /**
   * Refills the input buffer.
   *
   * @return {@code false} iff there was new input.
   * @exception java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead - zzStartRead);

      /* translate stored positions */
      zzEndRead -= zzStartRead;
      zzCurrentPos -= zzStartRead;
      zzMarkedPos -= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length * 2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException(
          "Reader returned 0 characters. See JFlex examples/zero-reader for a workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
        if (numRead == requested) { // We requested too few chars to encode a full Unicode character
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        } else {                    // There is room in the buffer for at least one more char
          int c = zzReader.read();  // Expecting to read a paired low surrogate char
          if (c == -1) {
            return true;
          } else {
            zzBuffer[zzEndRead++] = (char)c;
          }
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }


  /**
   * Closes the input reader.
   *
   * @throws java.io.IOException if the reader could not be closed.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true; // indicate end of file
    zzEndRead = zzStartRead; // invalidate buffer

    if (zzReader != null) {
      zzReader.close();
    }
  }


  /**
   * Resets the scanner to read from a new input stream.
   *
   * <p>Does not close the old reader.
   *
   * <p>All internal variables are reset, the old input stream <b>cannot</b> be reused (internal
   * buffer is discarded and lost). Lexical state is set to {@code ZZ_INITIAL}.
   *
   * <p>Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader The new input stream.
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzEOFDone = false;
    yyResetPosition();
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE) {
      zzBuffer = new char[ZZ_BUFFERSIZE];
    }
  }

  /**
   * Resets the input position.
   */
  private final void yyResetPosition() {
      zzAtBOL  = true;
      zzAtEOF  = false;
      zzCurrentPos = 0;
      zzMarkedPos = 0;
      zzStartRead = 0;
      zzEndRead = 0;
      zzFinalHighSurrogate = 0;
      yyline = 0;
      yycolumn = 0;
      yychar = 0L;
  }


  /**
   * Returns whether the scanner has reached the end of the reader it reads from.
   *
   * @return whether the scanner has reached EOF.
   */
  public final boolean yyatEOF() {
    return zzAtEOF;
  }


  /**
   * Returns the current lexical state.
   *
   * @return the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state.
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   *
   * @return the matched text.
   */
  public final String yytext() {
    return new String(zzBuffer, zzStartRead, zzMarkedPos-zzStartRead);
  }


  /**
   * Returns the character at the given position from the matched text.
   *
   * <p>It is equivalent to {@code yytext().charAt(pos)}, but faster.
   *
   * @param position the position of the character to fetch. A value from 0 to {@code yylength()-1}.
   *
   * @return the character at {@code position}.
   */
  public final char yycharat(int position) {
    return zzBuffer[zzStartRead + position];
  }


  /**
   * How many characters were matched.
   *
   * @return the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occurred while scanning.
   *
   * <p>In a well-formed scanner (no or only correct usage of {@code yypushback(int)} and a
   * match-all fallback rule) this method will only be called with things that
   * "Can't Possibly Happen".
   *
   * <p>If this method is called, something is seriously wrong (e.g. a JFlex bug producing a faulty
   * scanner etc.).
   *
   * <p>Usual syntax/scanner level error handling should be done in error fallback rules.
   *
   * @param errorCode the code of the error message to display.
   */
  private static void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    } catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * <p>They will be read again by then next call of the scanning method.
   *
   * @param number the number of characters to be read again. This number must not be greater than
   *     {@link #yylength()}.
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
    
    leyendo = false;
  yyclose();    }
  }




  /**
   * Resumes scanning until the next regular expression is matched, the end of input is encountered
   * or an I/O-Error occurs.
   *
   * @return the next token.
   * @exception java.io.IOException if any I/O-Error occurs.
   */
  @Override  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char[] zzBufferL = zzBuffer;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':  // fall through
        case '\u000C':  // fall through
        case '\u0085':  // fall through
        case '\u2028':  // fall through
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is
        // (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof)
            zzPeek = false;
          else
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMap(zzInput) ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
            zzDoEOF();
          { return new java_cup.runtime.Symbol(sym.EOF); }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1:
            { errores.add(new ErrorAnalisis("Lexico",yytext(),"Caracter no aceptado",yyline+1, yycolumn+1));
            }
            // fall through
          case 47: break;
          case 2:
            { /* Ignora los espacios en blanco */
            }
            // fall through
          case 48: break;
          case 3:
            { /* se ignoran los saltos de linea */
            }
            // fall through
          case 49: break;
          case 4:
            { return symbol(yyline+1, yycolumn+1, yytext(), sym.ENTERO);
            }
            // fall through
          case 50: break;
          case 5:
            { return symbol(yyline+1, yycolumn+1, yytext(), sym.ASIGNACION_INF);
            }
            // fall through
          case 51: break;
          case 6:
            { return symbol(yyline+1, yycolumn+1, yytext(), sym.FIN_DE_LINEA);
            }
            // fall through
          case 52: break;
          case 7:
            { return symbol(yyline+1, yycolumn+1, yytext(), sym.ID);
            }
            // fall through
          case 53: break;
          case 8:
            { /*return symbol(yyline+1, yycolumn+1, yytext(), sym.JAVA_CODE);*/
            }
            // fall through
          case 54: break;
          case 9:
            { return symbol(yyline+1, yycolumn+1, yytext(), sym.CARACTER_EXPLICITO);
            }
            // fall through
          case 55: break;
          case 10:
            { return symbol(yyline+1, yycolumn+1, yytext(), sym.IGNORAR);
            }
            // fall through
          case 56: break;
          case 11:
            { return symbol(yyline+1, yycolumn+1, yytext(), sym.PAR_A);
            }
            // fall through
          case 57: break;
          case 12:
            { return symbol(yyline+1, yycolumn+1, yytext(), sym.PAR_C);
            }
            // fall through
          case 58: break;
          case 13:
            { return symbol(yyline+1, yycolumn+1, yytext(), sym.CERO_O_MAS_VECES);
            }
            // fall through
          case 59: break;
          case 14:
            { return symbol(yyline+1, yycolumn+1, yytext(), sym.UNA_O_MAS_VECES);
            }
            // fall through
          case 60: break;
          case 15:
            { return symbol(yyline+1, yycolumn+1, yytext(), sym.ASIGNACION_ER);
            }
            // fall through
          case 61: break;
          case 16:
            { return symbol(yyline+1, yycolumn+1, yytext(), sym.PUEDE_O_NO_PUEDE);
            }
            // fall through
          case 62: break;
          case 17:
            { return symbol(yyline+1, yycolumn+1, yytext(), sym.COR_A);
            }
            // fall through
          case 63: break;
          case 18:
            { return symbol(yyline+1, yycolumn+1, yytext(), sym.COR_C);
            }
            // fall through
          case 64: break;
          case 19:
            { return symbol(yyline+1, yycolumn+1, yytext(), sym.ID_T);
            }
            // fall through
          case 65: break;
          case 20:
            { return symbol(yyline+1, yycolumn+1, yytext(), sym.O);
            }
            // fall through
          case 66: break;
          case 21:
            { return symbol(yyline+1, yycolumn+1, yytext(), sym.COMA);
            }
            // fall through
          case 67: break;
          case 22:
            { return symbol(yyline+1, yycolumn+1, yytext(), sym.ID_NT);
            }
            // fall through
          case 68: break;
          case 23:
            { yybegin(JAVACODE); return symbol(yyline+1, yycolumn+1, yytext(), sym.SEPARADOR);
            }
            // fall through
          case 69: break;
          case 24:
            { /* se ignoran los comentarios */
            }
            // fall through
          case 70: break;
          case 25:
            { return symbol(yyline+1, yycolumn+1, "no", sym.PR_NO);
            }
            // fall through
          case 71: break;
          case 26:
            { yybegin(EXPRESIONES_REGULARES); return symbol(yyline+1, yycolumn+1, yytext(), sym.SEPARADOR);
            }
            // fall through
          case 72: break;
          case 27:
            { yybegin(DECLARACION_SIMBOLOS); return symbol(yyline+1, yycolumn+1, yytext(), sym.SEPARADOR);
            }
            // fall through
          case 73: break;
          case 28:
            { return symbol(yyline+1, yycolumn+1, yytext(), sym.RETORNO);
            }
            // fall through
          case 74: break;
          case 29:
            { return symbol(yyline+1, yycolumn+1, yytext(), sym.SALTO_DE_LINEA);
            }
            // fall through
          case 75: break;
          case 30:
            { return symbol(yyline+1, yycolumn+1, yytext(), sym.TABULACION);
            }
            // fall through
          case 76: break;
          case 31:
            { yybegin(REGLAS_SEMANTICAS); return symbol(yyline+1, yycolumn+1, yytext(), sym.SEPARADOR);
            }
            // fall through
          case 77: break;
          case 32:
            { return symbol(yyline+1, yycolumn+1, yytext(), sym.ASIGNACION_GRAMA);
            }
            // fall through
          case 78: break;
          case 33:
            { return symbol(yyline+1, yycolumn+1, yytext(), sym.VERSION);
            }
            // fall through
          case 79: break;
          case 34:
            { return symbol(yyline+1, yycolumn+1, yytext(), sym.CADENA);
            }
            // fall through
          case 80: break;
          case 35:
            { return symbol(yyline+1, yycolumn+1, yytext(), sym.JAVA_CODE);
            }
            // fall through
          case 81: break;
          case 36:
            { return symbol(yyline+1, yycolumn+1, "real", sym.PR_REAL);
            }
            // fall through
          case 82: break;
          case 37:
            { return symbol(yyline+1, yycolumn+1, "autor", sym.PR_AUTOR);
            }
            // fall through
          case 83: break;
          case 38:
            { return symbol(yyline+1, yycolumn+1, yytext(), sym.RANGO_NUMEROS);
            }
            // fall through
          case 84: break;
          case 39:
            { return symbol(yyline+1, yycolumn+1, yytext(), sym.RANGO_LETRAS_MIN);
            }
            // fall through
          case 85: break;
          case 40:
            { return symbol(yyline+1, yycolumn+1, "cadena", sym.PR_CADENA);
            }
            // fall through
          case 86: break;
          case 41:
            { return symbol(yyline+1, yycolumn+1, "entero", sym.PR_ENTERO);
            }
            // fall through
          case 87: break;
          case 42:
            { return symbol(yyline+1, yycolumn+1, "nombre", sym.PR_NOMBRE);
            }
            // fall through
          case 88: break;
          case 43:
            { return symbol(yyline+1, yycolumn+1, "version", sym.PR_VERSION);
            }
            // fall through
          case 89: break;
          case 44:
            { return symbol(yyline+1, yycolumn+1, "terminal", sym.PR_TERMINAL);
            }
            // fall through
          case 90: break;
          case 45:
            { return symbol(yyline+1, yycolumn+1, "extension", sym.PR_EXTENSION);
            }
            // fall through
          case 91: break;
          case 46:
            { return symbol(yyline+1, yycolumn+1, "lanzamiento", sym.PR_LANZAMIENTO);
            }
            // fall through
          case 92: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}