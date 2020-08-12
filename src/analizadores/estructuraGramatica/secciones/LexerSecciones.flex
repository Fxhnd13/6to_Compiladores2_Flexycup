package analizadores.estructuraGramatica.secciones;

import java_cup.runtime.Symbol;

%%

%class LexerSecciones
%public
%cup
%line
%column


%{

    private Symbol symbol(int type, int linea, int columna, String lexema){
        Symbol simbolo = new Symbol(type, linea, columna, lexema);
        System.out.println(simbolo.toString());
        return simbolo;
    }
%}

Separator = "%%"

%init{
//Codigo a ejecutarse al crearse la clase
%init}

%%

/* reglas lexicas */
<YYINITIAL> {

    /* Reserved words */
    {Separator}                { return symbol(sym.SEPARADOR, yyline, yycolumn, yytext());}
    [^{Separator}]+            {  return symbol(sym.SECCION, yyline, yycolumn, yytext());    }
}

/* error fallback */
    [^]                              { throw new Error("Illegal character <"+
                                                        yytext()+"> at (Line,Column): <"+yyline+","+yycolumn+">"); }