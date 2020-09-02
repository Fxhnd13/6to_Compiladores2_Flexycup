/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos.componentes.lexer;

import java.io.Serializable;

/**
 *
 * @author jose_
 */
public class Token implements Serializable {
    
    private int linea, columna;
    private String lexema, tipo;

    /**
     *
     * @param linea
     * @param columna
     * @param lexema
     * @param tipo
     */
    public Token(int linea, int columna, String lexema, String tipo){
        this.linea = linea;
        this.columna = columna;
        this.lexema = lexema;
        this.tipo = tipo;
    }
    
    /**
     *
     * @return
     */
    public int getLinea() {
        return linea;
    }

    /**
     *
     * @param linea
     */
    public void setLinea(int linea) {
        this.linea = linea;
    }

    /**
     *
     * @return
     */
    public int getColumna() {
        return columna;
    }

    /**
     *
     * @param columna
     */
    public void setColumna(int columna) {
        this.columna = columna;
    }

    /**
     *
     * @return
     */
    public String getLexema() {
        return lexema;
    }

    /**
     *
     * @param lexema
     */
    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    /**
     *
     * @return
     */
    public String getTipo() {
        return tipo;
    }

    /**
     *
     * @param tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Token{" + "linea=" + linea + ", columna=" + columna + ", lexema=" + lexema + ", tipo=" + tipo + '}';
    }
    
}
