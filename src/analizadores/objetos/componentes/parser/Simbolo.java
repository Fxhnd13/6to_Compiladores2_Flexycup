/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos.componentes.parser;

import java.io.Serializable;

/**
 *
 * @author jose_
 */
public class Simbolo implements Serializable{
    
    private String[] primeros;
    private String simbolo;
    private int linea, columna, precedencia;
    private boolean lambda = false, terminal = false;

    /**
     *
     * @param id
     * @param linea
     * @param columna
     */
    public Simbolo(String id, int linea, int columna){
        this.linea = linea;
        this.columna = columna;
        this.simbolo = id;
    }
    
    /**
     *
     * @param id
     * @param linea
     * @param columna
     * @param precedencia
     */
    public Simbolo(String id, int linea, int columna, int precedencia){
        this(id, linea, columna);
        this.precedencia = precedencia;
    }
    
    /**
     *
     * @return
     */
    public String[] getPrimeros() {
        return primeros;
    }

    /**
     *
     * @param primeros
     */
    public void setPrimeros(String[] primeros) {
        this.primeros = primeros;
    }

    /**
     *
     * @return
     */
    public String getSimbolo() {
        return simbolo;
    }

    /**
     *
     * @param simbolo
     */
    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    /**
     *
     * @return
     */
    public boolean isLambda() {
        return lambda;
    }

    /**
     *
     * @param lambda
     */
    public void setLambda(boolean lambda) {
        this.lambda = lambda;
    }

    /**
     *
     * @return
     */
    public boolean isTerminal() {
        return terminal;
    }

    /**
     *
     * @param terminal
     */
    public void setTerminal(boolean terminal) {
        this.terminal = terminal;
        if(terminal){
            this.primeros = new String[1];
            this.primeros[0] = this.simbolo;
        }
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
    public int getPrecedencia() {
        return precedencia;
    }

    /**
     *
     * @param precedencia
     */
    public void setPrecedencia(int precedencia) {
        this.precedencia = precedencia;
    }

    @Override
    public String toString() {
        String cadenaPrimeros ="[";
        for (int i = 0; i < primeros.length; i++) {
            cadenaPrimeros+=primeros[i];
            if((i+1)<primeros.length)cadenaPrimeros+=", ";
        }
        return "Simbolo{" + "simbolo=" + simbolo + ", linea=" + linea + ", columna=" + columna + ", precedencia=" + precedencia + ", lambda=" + lambda + ", terminal=" + terminal + ", primeros = "+cadenaPrimeros+ "]}";
    }
    
}
