/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos.componentes.lexer;

import java.io.Serializable;

/**
 * Clase que almacena los datos de un token detectado para el analizador lexico del lenguaje generado
 * @author jose_
 */
public class Token implements Serializable {
    
    private int linea, columna;
    private String lexema, tipo;

    public Token(int linea, int columna, String lexema, String tipo){
        this.linea = linea;
        this.columna = columna;
        this.lexema = lexema;
        this.tipo = tipo;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }
    
    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }
    
    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Token{" + "linea=" + linea + ", columna=" + columna + ", lexema=" + lexema + ", tipo=" + tipo + '}';
    }
    
}
