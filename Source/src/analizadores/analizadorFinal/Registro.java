/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.analizadorFinal;

import java.io.Serializable;

/**
 * Clase que se encarga de almacenar un "estado" en el que se encontraba la pila durante el analisis sintactico
 * @author jose_
 */
public class Registro implements Serializable{
    
    private String pila;
    private String simbolos;
    private String entrada;
    private String accion;

    /**
     * Constructor de un registor
     * @param pila estado de la pila de estados
     * @param simbolos estado de la pila de simobolos
     * @param entrada estado del listado de tokens
     * @param accion accion realizada 
     */
    public Registro(String pila, String simbolos, String entrada, String accion) {
        this.pila = pila;
        this.simbolos = simbolos;
        this.entrada = entrada;
        this.accion = accion;
    }

    /**
     *
     * @return retorn la pila de estados
     */
    public String getPila() {
        return pila;
    }

    /**
     * cambia la pila de estados
     * @param pila
     */
    public void setPila(String pila) {
        this.pila = pila;
    }

    /**
     * 
     * @return la pila de simbolos
     */
    public String getSimbolos() {
        return simbolos;
    }

    /**
     * cambia la pila de simbolos
     * @param simbolos
     */
    public void setSimbolos(String simbolos) {
        this.simbolos = simbolos;
    }

    /**
     *
     * @return los tokens de entrada
     */
    public String getEntrada() {
        return entrada;
    }

    /**
     * cambia los tokens de entrada
     * @param entrada
     */
    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    /**
     * 
     * @return accion que se realizo
     */
    public String getAccion() {
        return accion;
    }

    /**
     * cambia la accion que se realizo
     * @param accion
     */
    public void setAccion(String accion) {
        this.accion = accion;
    }

    @Override
    public String toString() {
        return "Registro{" + "\n    pila=" + pila + ", \n     simbolos=" + simbolos + ", \n     entrada=" + entrada + ", \n     accion=" + accion + "\n}";
    }
    
}
