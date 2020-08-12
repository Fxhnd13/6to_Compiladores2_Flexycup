package analizadores.estructuraGramaticas;

import analizadores.objetos.*;
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
WhiteSpace = [\t\f]+

/* identifiers */
L = [a-zA-Z_]

/* integer literals */
Digito = [0-9]
IntegerLiteral = 0 | [1-9][0-9]*

%{
//Codigo de usuario
%}

%init{
//Codigo a ejecutarse al crearse la clase
%init}

%%

/* reglas lexicas */
<YYINITIAL> {

    /* Reserved words */
    "nombre"                    { return symbol("nombre", PR_NOMBRE); }
    "autor"                     { return symbol("autor", PR_AUTOR);}
    "version"                   { return symbol("version", PR_VERSION);}
    "lanzamiento"               { return symbol("lanzamiento", PR_LANZAMIENTO);}
    "extension"                 { return symbol("extension", PR_EXTENSION);}
    "%%"                        { return symbol(SEPARADOR_SECCCION);}
    "+"                         { return symbol(UNA_O_MAS_VECES);}
    "*"                         { return symbol(CERO_O_MAS_VECES);}
    "?"                         { return symbol(PUEDE_O_NO_PUEDE);}
    "|"                         { return symbol(O);}
    "["                         { return symbol(COR_A);}
    "]"                         { return symbol(COR_C);}
    "("                         { return symbol(PAR_A);}
    ")"                         { return symbol(PAR_C);}
    "\n"                        { return symbol(SALTO_DE_LINEA);}
    "\t"                        { return symbol(TABULACION);}
    "\b"                        { return symbol(RETORNO);}
    "&"                         { return symbol(
    {WhiteSpace} 	{   /* Ignora los espacios en blanco */  }

}

/* error fallback */
[^]                              {   error(yytext());    }