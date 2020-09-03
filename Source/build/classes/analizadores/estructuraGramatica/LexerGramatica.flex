package analizadores.estructuraGramatica;

import analizadores.objetos.ErrorAnalisis;
import java.util.ArrayList;
import java.util.List;
import java_cup.runtime.Symbol;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

%%

%class LexerGramatica
%public
%cup
%line
%column
%states JAVACODE, EXPRESIONES_REGULARES, DECLARACION_SIMBOLOS, REGLAS_SEMANTICAS

/* special chars */
LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]
WhiteSpace = [ \t\f]+

/* identifiers */
L = [a-zA-Z]
Lmin = [a-z]
Lmay = [A-Z]

/* integer literals */
Digito = [0-9]
IntegerLiteral = 0 | [1-9]{Digito}*

/* comments */
TraditionalComment   = "/*" [^*] ~"*/"
EndOfLineComment     = "//" {InputCharacter}* {LineTerminator}?
Comment = {TraditionalComment} | {EndOfLineComment}

/* cadenas de texto */
Cadena               = "\"" [^\"] ~"\""
JavaCode             = "{" [^}] ~"}"

%{
    
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

    public List<ErrorAnalisis> getErrores(){
        return errores;
    }

    public boolean isAnalizando(){
        return leyendo;
    }
    
    public void setTablaTokens(JTable tablaTokens){
        this.tablaTokens = (DefaultTableModel) tablaTokens.getModel();
    }
    
    public JTable getTablaTokens(){ return new JTable(tablaTokens); }

%}

%init{
    errores = new ArrayList<ErrorAnalisis>();
%init}

%eof{
    leyendo = false;
%eof}

%%

/* reglas lexicas */
<YYINITIAL> {

    /* Reserved words */
    "%%"                                    { yybegin(JAVACODE); return symbol(yyline+1, yycolumn+1, yytext(), sym.SEPARADOR);}
    "nombre"                                { return symbol(yyline+1, yycolumn+1, "nombre", sym.PR_NOMBRE); }
    "autor"                                 { return symbol(yyline+1, yycolumn+1, "autor", sym.PR_AUTOR);}
    "version"                               { return symbol(yyline+1, yycolumn+1, "version", sym.PR_VERSION);}
    "lanzamiento"                           { return symbol(yyline+1, yycolumn+1, "lanzamiento", sym.PR_LANZAMIENTO);}
    "extension"                             { return symbol(yyline+1, yycolumn+1, "extension", sym.PR_EXTENSION);}
    ":"                                     { return symbol(yyline+1, yycolumn+1, yytext(), sym.ASIGNACION_INF);}
    "entero"                                { return symbol(yyline+1, yycolumn+1, "entero", sym.PR_ENTERO);}
    "real"                                  { return symbol(yyline+1, yycolumn+1, "real", sym.PR_REAL);}
    "cadena"                                { return symbol(yyline+1, yycolumn+1, "cadena", sym.PR_CADENA);}
    "no"                                    { return symbol(yyline+1, yycolumn+1, "no", sym.PR_NO);}
    "terminal"                              { return symbol(yyline+1, yycolumn+1, "terminal", sym.PR_TERMINAL);}
    ";"                                     { return symbol(yyline+1, yycolumn+1, yytext(), sym.FIN_DE_LINEA);}
    ({L}|("\_"))({L}|{Digito}|("\_"))*      { return symbol(yyline+1, yycolumn+1, yytext(), sym.ID);}
    {IntegerLiteral}                        { return symbol(yyline+1, yycolumn+1, yytext(), sym.ENTERO);}
    ({IntegerLiteral}("\."{Digito})*)       { return symbol(yyline+1, yycolumn+1, yytext(), sym.VERSION);}
    {Comment}                               { /* se ignoran los comentarios */}
    {LineTerminator}                        { /* se ignoran los saltos de linea */}
    {WhiteSpace}                            {   /* Ignora los espacios en blanco */  }

/* error fallback */
    [^]                                     { errores.add(new ErrorAnalisis("Lexico",yytext(),"Caracter no aceptado",yyline+1, yycolumn+1));}
}

<JAVACODE>{
    "%%"                                    { yybegin(EXPRESIONES_REGULARES); return symbol(yyline+1, yycolumn+1, yytext(), sym.SEPARADOR);}
    [^]                                     { /*return symbol(yyline+1, yycolumn+1, yytext(), sym.JAVA_CODE);*/}
}

<EXPRESIONES_REGULARES>{

    "%%"                                    { yybegin(DECLARACION_SIMBOLOS); return symbol(yyline+1, yycolumn+1, yytext(), sym.SEPARADOR);}
    ("["{L}"-"{L}"]")                       { return symbol(yyline+1, yycolumn+1, yytext(), sym.RANGO_LETRAS_MIN);}
    ("["{Digito}"-"{Digito}"]")             { return symbol(yyline+1, yycolumn+1, yytext(), sym.RANGO_NUMEROS);}
    {Cadena}                                { return symbol(yyline+1, yycolumn+1, yytext(), sym.CADENA);}
    "="                                     { return symbol(yyline+1, yycolumn+1, yytext(), sym.ASIGNACION_ER); }
    "+"                                     { return symbol(yyline+1, yycolumn+1, yytext(), sym.UNA_O_MAS_VECES);}
    "*"                                     { return symbol(yyline+1, yycolumn+1, yytext(), sym.CERO_O_MAS_VECES);}
    "?"                                     { return symbol(yyline+1, yycolumn+1, yytext(), sym.PUEDE_O_NO_PUEDE);}
    "|"                                     { return symbol(yyline+1, yycolumn+1, yytext(), sym.O);}
    "["                                     { return symbol(yyline+1, yycolumn+1, yytext(), sym.COR_A);}
    "]"                                     { return symbol(yyline+1, yycolumn+1, yytext(), sym.COR_C);}
    "("                                     { return symbol(yyline+1, yycolumn+1, yytext(), sym.PAR_A);}
    ")"                                     { return symbol(yyline+1, yycolumn+1, yytext(), sym.PAR_C);}
    ";"                                     { return symbol(yyline+1, yycolumn+1, yytext(), sym.FIN_DE_LINEA);}
    "\\n"                                   { return symbol(yyline+1, yycolumn+1, yytext(), sym.SALTO_DE_LINEA);}
    "\\t"                                   { return symbol(yyline+1, yycolumn+1, yytext(), sym.TABULACION);}
    "\\b"                                   { return symbol(yyline+1, yycolumn+1, yytext(), sym.RETORNO);}
    "&"                                     { return symbol(yyline+1, yycolumn+1, yytext(), sym.IGNORAR);}
    {IntegerLiteral}                        { return symbol(yyline+1, yycolumn+1, yytext(), sym.ENTERO);}
    ({IntegerLiteral}("\."{Digito})*)       { return symbol(yyline+1, yycolumn+1, yytext(), sym.VERSION);}
    ({Lmin}|("\_"))({Lmin}|{Digito}|("\_"))*      { return symbol(yyline+1, yycolumn+1, yytext(), sym.ID_T);}
    {Comment}                               { /* se ignoran los comentarios */}
    {LineTerminator}                        { /* se ignoran los saltos de linea */}
    {WhiteSpace}                            {   /* Ignora los espacios en blanco */  }
    [^]                                     { return symbol(yyline+1, yycolumn+1, yytext(), sym.CARACTER_EXPLICITO);}

/* error fallback */
}

<DECLARACION_SIMBOLOS>{
    
    "%%"                                    { yybegin(REGLAS_SEMANTICAS); return symbol(yyline+1, yycolumn+1, yytext(), sym.SEPARADOR);}
    "entero"                                { return symbol(yyline+1, yycolumn+1, "entero", sym.PR_ENTERO);}
    "real"                                  { return symbol(yyline+1, yycolumn+1, "real", sym.PR_REAL);}
    "cadena"                                { return symbol(yyline+1, yycolumn+1, "cadena", sym.PR_CADENA);}
    "no"                                    { return symbol(yyline+1, yycolumn+1, "no", sym.PR_NO);}
    "terminal"                              { return symbol(yyline+1, yycolumn+1, "terminal", sym.PR_TERMINAL);}
    ","                                     { return symbol(yyline+1, yycolumn+1, yytext(), sym.COMA);}
    ";"                                     { return symbol(yyline+1, yycolumn+1, yytext(), sym.FIN_DE_LINEA);}
    ({Lmin}|("\_"))({Lmin}|{Digito}|("\_"))* { return symbol(yyline+1, yycolumn+1, yytext(), sym.ID_T);}
    ({Lmay}|("\_"))({Lmay}|{Digito}|("\_"))* { return symbol(yyline+1, yycolumn+1, yytext(), sym.ID_NT);}
    {Comment}                               { /* se ignoran los comentarios */}
    {LineTerminator}                        { /* se ignoran los saltos de linea */}
    {WhiteSpace}                            {   /* Ignora los espacios en blanco */  }
    
/* error fallback */
    [^]                                     { errores.add(new ErrorAnalisis("Lexico",yytext(),"Caracter no aceptado",yyline+1, yycolumn+1)); }
}

<REGLAS_SEMANTICAS>{

    "::"                                    { return symbol(yyline+1, yycolumn+1, yytext(), sym.ASIGNACION_GRAMA);}
    ";"                                     { return symbol(yyline+1, yycolumn+1, yytext(), sym.FIN_DE_LINEA);}
    ":"                                     { return symbol(yyline+1, yycolumn+1, yytext(), sym.ASIGNACION_INF);}
    ({L}|("\_"))({L}|{Digito}|("\_"))*      { return symbol(yyline+1, yycolumn+1, yytext(), sym.ID);}
    {JavaCode}                              { return symbol(yyline+1, yycolumn+1, yytext(), sym.JAVA_CODE);}
    {Comment}                               { /* se ignoran los comentarios */}
    {LineTerminator}                        { /* se ignoran los saltos de linea */}
    {WhiteSpace}                            {   /* Ignora los espacios en blanco */  }

/* error fallback */
    [^]                                     { errores.add(new ErrorAnalisis("Lexico",yytext(),"Caracter no aceptado",yyline+1, yycolumn+1)); }
}
