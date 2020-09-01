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
public class Shift implements Accion, Serializable{
    
    private int indiceProduccion;
    private String simbolo;
    
    public Shift(String simbolo, int indiceProduccion){
        this.simbolo = simbolo;
        this.indiceProduccion = indiceProduccion;
    }
    
    public void setSimbolo(String simbolo) { this.simbolo = simbolo; }
    public String getSimbolo(){ return this.simbolo; }
    public void setIndiceProduccion(int indice){ this.indiceProduccion = indice; }
    public int getValor(){ return this.indiceProduccion; }
    public String toString(){
        return "Shift("+indiceProduccion+")";
    }
}
