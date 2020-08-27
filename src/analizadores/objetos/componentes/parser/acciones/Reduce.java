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
public class Reduce implements Accion{
    
    private int indiceProduccion;
    
    public Reduce(int indice){
        this.indiceProduccion = indice;
    }
    
    public void setIndiceProduccion(int indice){ this.indiceProduccion = indice; }
    public int getIndiceProduccion(){ return this.indiceProduccion; }
}
