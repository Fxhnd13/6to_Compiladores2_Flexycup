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
public class GoTo implements Accion, Serializable{
    
    private String simbolo;
    private int estadoDestino;
    
    /**
     *
     * @param simbolo
     * @param destino
     */
    public GoTo(String simbolo, int destino){
        this.simbolo = simbolo;
        this.estadoDestino = destino;
    }
    
    /**
     *
     * @return
     */
    public int getValor(){ return this.estadoDestino; }

    /**
     *
     * @param destino
     */
    public void setValor(int destino){ this.estadoDestino = destino;}

    /**
     *
     * @return
     */
    public String getSimbolo(){ return this.simbolo; }

    /**
     *
     * @param simbolo
     */
    public void setSimbolO(String simbolo) { this.simbolo = simbolo; }
    public String toString(){
        return "GoTo("+estadoDestino+")";
    }
}
