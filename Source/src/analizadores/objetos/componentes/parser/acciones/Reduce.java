/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos.componentes.parser.acciones;

import java.io.Serializable;

/**
 *
 * @author jose_
 */
public class Reduce implements Accion, Serializable{
    
    private int indiceProduccion;
    private String simbolo;
    
    /**
     *
     * @param simbolo
     * @param indice
     */
    public Reduce(String simbolo, int indice){
        this.simbolo = simbolo;
        this.indiceProduccion = indice;
    }
    
    /**
     *
     * @param simbolo
     */
    public void setSimbolo(String simbolo) { this.simbolo = simbolo; }

    /**
     *
     * @return
     */
    public String getSimbolo(){ return this.simbolo; }

    /**
     *
     * @param indice
     */
    public void setIndiceProduccion(int indice){ this.indiceProduccion = indice; }

    /**
     *
     * @return
     */
    public int getValor(){ return this.indiceProduccion; }
    public String toString(){
        return "Reduce("+indiceProduccion+")";
    }
}
