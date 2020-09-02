/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.analizadorFinal;

import java.io.Serializable;

/**
 *
 * @author jose_
 */
public class Registro implements Serializable{
    
    private String pila;
    private String simbolos;
    private String entrada;
    private String accion;

    /**
     *
     * @param pila
     * @param simbolos
     * @param entrada
     * @param accion
     */
    public Registro(String pila, String simbolos, String entrada, String accion) {
        this.pila = pila;
        this.simbolos = simbolos;
        this.entrada = entrada;
        this.accion = accion;
    }

    /**
     *
     * @return
     */
    public String getPila() {
        return pila;
    }

    /**
     *
     * @param pila
     */
    public void setPila(String pila) {
        this.pila = pila;
    }

    /**
     *
     * @return
     */
    public String getSimbolos() {
        return simbolos;
    }

    /**
     *
     * @param simbolos
     */
    public void setSimbolos(String simbolos) {
        this.simbolos = simbolos;
    }

    /**
     *
     * @return
     */
    public String getEntrada() {
        return entrada;
    }

    /**
     *
     * @param entrada
     */
    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    /**
     *
     * @return
     */
    public String getAccion() {
        return accion;
    }

    /**
     *
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
