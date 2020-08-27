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
public class Cerradura {
 
    private Produccion produccion;
    private String[] simbolosPreAnalisis;
    private int posicionPunto;

    public Produccion getProduccion() {
        return produccion;
    }

    public void setProduccion(Produccion produccion) {
        this.produccion = produccion;
    }

    public String[] getSimbolosPreAnalisis() {
        return simbolosPreAnalisis;
    }

    public void setSimbolosPreAnalisis(String[] simbolosPreAnalisis) {
        this.simbolosPreAnalisis = simbolosPreAnalisis;
    }

    public int getPosicionPunto() {
        return posicionPunto;
    }

    public void setPosicionPunto(int posicionPunto) {
        this.posicionPunto = posicionPunto;
    }
    
}
