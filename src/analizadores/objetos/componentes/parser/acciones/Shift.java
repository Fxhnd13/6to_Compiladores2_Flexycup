/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos.componentes.parser.acciones;

/**
 *
 * @author jose_
 */
public class Shift implements Accion{
    
    private int indiceProduccion;
    
    public Shift(int indiceProduccion){
        this.indiceProduccion = indiceProduccion;
    }
    
    public void setIndiceProduccion(int indice){ this.indiceProduccion = indice; }
    public int getIndiceProduccion(){ return this.indiceProduccion; }
}
