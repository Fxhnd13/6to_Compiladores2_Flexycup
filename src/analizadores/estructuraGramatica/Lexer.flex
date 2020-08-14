package analizadores.estructuraGramaticas;

import java.util.ArrayList;
import java.util.List;
import java_cup.runtime.Symbol;

%%

%class Lexer
%public
%cup
%line
%column

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
Cadena               = "\"" [^]* ~"\""
JavaCode             = "{" [^}* ~"}"

%{
    
    private List<String> errores;

    private Symbol symbol(int type, int linea, int columna, String lexema){
        Symbol simbolo = new Symbol(type, linea, columna, lexema);
        System.out.println(simbolo.toString());
        return simbolo;
    }

    private Symbol symbol(int type, int linea, int columna){
        Symbol simbolo = new Symbol(type,linea,columna);
        System.out.println(simbolo.toString());
        return simbolo;
    }

    private void error(String lexema){
        errorsList.add("Se encontro un caracter/simbolo desconocido en la linea: "+(yyline+1)+", columna: "+(yycolumn+1)+" con el simbolo "+lexeme);
    }

    private List<String> getErrores(){
        return errores;
    }

%}

%init{
    errores = new ArrayList<String>();
%init}

%%

/* reglas lexicas */
<YYINITIAL> {

    /* Reserved words */
    "nombre"                                { return symbol("nombre", sym.PR_NOMBRE); }
    "autor"                                 { return symbol("autor", sym.PR_AUTOR);}
    "version"                               { return symbol("version", sym.PR_VERSION);}
    "lanzamiento"                           { return symbol("lanzamiento", sym.PR_LANZAMIENTO);}
    "extension"                             { return symbol("extension", sym.PR_EXTENSION);}
    ":"                                     { return symbol(sym.ASIGNACION_INF);}
    "::"                                    { return symbol(sym.ASIGNACION_GRAMA);}
    "="                                     { return symbol(sym.ASIGNACION_ER); }
    "%%"                                    { return symbol(sym.SEPARADOR_SECCCION);}
    "+"                                     { return symbol(sym.UNA_O_MAS_VECES);}
    "*"                                     { return symbol(sym.CERO_O_MAS_VECES);}
    "?"                                     { return symbol(sym.PUEDE_O_NO_PUEDE);}
    "|"                                     { return symbol(sym.O);}
    "["                                     { return symbol(sym.COR_A);}
    "]"                                     { return symbol(sym.COR_C);}
    "("                                     { return symbol(sym.PAR_A);}
    ")"                                     { return symbol(sym.PAR_C);}
    ";"                                     { return symbol(sym.FIN_DE_LINEA);}
    "\\n"                                   { return symbol(sym.SALTO_DE_LINEA);}
    "\\t"                                   { return symbol(sym.TABULACION);}
    "\\b"                                   { return symbol(sym.RETORNO);}
    "&"                                     { return symbol(sym.IGNORAR);}
    {cadena}                                { return symbol(yytext(), sym.CADENA);}
    {IntegerLiteral}                        { return symbol(yytext(), sym.ENTERO);}
    ({IntegerLiteral}("\."{Digito})*)       { return symbol(yytext(), sym.VERSION);}
    ({L}|("\_"))({L}|{Digito}|("\_"))*      { return symbol(yytext(), sym.ID);}
    {Comment}                               { /* se ignoran los comentarios */}
    {LineTerminator}                        { /* se ignoran los saltos de linea */}
    {WhiteSpace}                            {   /* Ignora los espacios en blanco */  }

}

/* error fallback */
[^]                              {   error(yytext());    }