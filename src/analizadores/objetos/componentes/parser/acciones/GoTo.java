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
public class GoTo implements Accion{
    
    private String simbolo;
    
    public GoTo(String simbolo){
        this.simbolo = simbolo;
    }
    
    public String getSimbolo(){ return this.simbolo; }
    public void setSimbolO(String simbolo) { this.simbolo = simbolo; }
    
}
