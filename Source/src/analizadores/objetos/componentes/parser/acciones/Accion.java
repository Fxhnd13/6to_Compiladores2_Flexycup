/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos.componentes.parser.acciones;

/**
 * Interfaz para implementar en las acciones shift, reduce, goto, y aceptacion
 * @author jose_
 */
public interface Accion{
    
    /**
     * 
     * @return el simbolo con el que se realiza la accion
     */
    public String getSimbolo();

    /**
     *
     * @return el estado destino, generalmente
     */
    public int getValor();

    public String toString();
}
