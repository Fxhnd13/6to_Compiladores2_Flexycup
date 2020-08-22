package analizadores.estructuraGramatica;

import java.util.ArrayList;
import java.util.List;
import java_cup.runtime.Symbol;

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

/* integer literals */
Digito = [0-9]
IntegerLiteral = 0 | [1-9]{Digito}*

/* comments */
TraditionalComment   = "/*" [^*] ~"*/"
EndOfLineComment     = "//" {InputCharacter}* {LineTerminator}?
Comment = {TraditionalComment} | {EndOfLineComment}

/* cadenas de texto */
Cadena               = "\"" [^*] ~"\""
JavaCode             = "{" [^*] ~"}"

%{
    
    private List<String> errores;
    private boolean leyendo = true;

    private Symbol symbol(int linea, int columna, String lexema, int type){
        Symbol simbolo = new Symbol(type, linea, columna, lexema);
        //System.out.println(type+"|"+lexema+"|<"+linea+","+columna+">");
        return simbolo;
    }

    private Symbol symbol(int linea, int columna, int type){
        Symbol simbolo = new Symbol(type,linea,columna);
        //System.out.println(type+"|"+yytext()+"|<"+linea+","+columna+">");
        return simbolo;
    }

    private void error(String lexema){
        errores.add("Se encontro un caracter/simbolo desconocido en la linea: "+(yyline+1)+", columna: "+(yycolumn+1)+" con el simbolo "+lexema);
    }

    public List<String> getErrores(){
        return errores;
    }

    public boolean isAnalizando(){
        return leyendo;
    }

%}

%init{
    errores = new ArrayList<String>();
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
    [^]                                     { System.out.println("Error");}
}

<JAVACODE>{
    "%%"                                    { yybegin(EXPRESIONES_REGULARES); return symbol(yyline+1, yycolumn+1, yytext(), sym.SEPARADOR);}
    [^]                                     { /*return symbol(yyline+1, yycolumn+1, yytext(), sym.JAVA_CODE);*/}
}

<EXPRESIONES_REGULARES>{

    "%%"                                    { yybegin(DECLARACION_SIMBOLOS); return symbol(yyline+1, yycolumn+1, yytext(), sym.SEPARADOR);}
    "[a-z]"                                 { return symbol(yyline+1, yycolumn+1, yytext(), sym.RANGO_LETRAS_MIN);}
    "[0-9]"                                 { return symbol(yyline+1, yycolumn+1, yytext(), sym.RANGO_NUMEROS);}
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
    ({L}|("\_"))({L}|{Digito}|("\_"))*      { return symbol(yyline+1, yycolumn+1, yytext(), sym.ID);}
    {Cadena}                                { return symbol(yyline+1, yycolumn+1, yytext(), sym.CADENA);}
    {Comment}                               { /* se ignoran los comentarios */}
    {LineTerminator}                        { /* se ignoran los saltos de linea */}
    {WhiteSpace}                            {   /* Ignora los espacios en blanco */  }

/* error fallback */
    [^]                                     { System.out.println("Error");}
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
    ({L}|("\_"))({L}|{Digito}|("\_"))*      { return symbol(yyline+1, yycolumn+1, yytext(), sym.ID);}
    {Comment}                               { /* se ignoran los comentarios */}
    {LineTerminator}                        { /* se ignoran los saltos de linea */}
    {WhiteSpace}                            {   /* Ignora los espacios en blanco */  }
    
/* error fallback */
    [^]                                     { System.out.println("Error");}
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
    [^]                                     { System.out.println("Error");}
}
