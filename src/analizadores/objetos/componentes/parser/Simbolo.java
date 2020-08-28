/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos.componentes.parser;

/**
 *
 * @author jose_
 */
public class Simbolo {
    
    private String[] primeros;
    private String simbolo;
    private int linea, columna;
    private boolean lambda = false, terminal = false;

    public Simbolo(String id, int linea, int columna){
        this.linea = linea;
        this.columna = columna;
        this.simbolo = id;
    }
    
    public Simbolo(String id, boolean b, int linea, int columna) {
        this.linea = linea;
        this.columna = columna;
        this.simbolo = simbolo;
        this.terminal = terminal;
    }
    
    public String[] getPrimeros() {
        return primeros;
    }

    public void setPrimeros(String[] primeros) {
        this.primeros = primeros;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public boolean isLambda() {
        return lambda;
    }

    public void setLambda(boolean lambda) {
        this.lambda = lambda;
    }

    public boolean isTerminal() {
        return terminal;
    }

    public void setTerminal(boolean terminal) {
        this.terminal = terminal;
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
    
}
