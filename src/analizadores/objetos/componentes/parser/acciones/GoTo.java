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
    
    public GoTo(String simbolo, int destino){
        this.simbolo = simbolo;
        this.estadoDestino = destino;
    }
    
    public int getValor(){ return this.estadoDestino; }
    public void setValor(int destino){ this.estadoDestino = destino;}
    public String getSimbolo(){ return this.simbolo; }
    public void setSimbolO(String simbolo) { this.simbolo = simbolo; }
    public String toString(){
        return "GoTo("+estadoDestino+")";
    }
}
