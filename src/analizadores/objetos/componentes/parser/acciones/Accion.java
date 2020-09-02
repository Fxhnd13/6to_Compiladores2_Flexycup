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
public interface Accion{
    
    /**
     *
     * @return
     */
    public String getSimbolo();

    /**
     *
     * @return
     */
    public int getValor();

    /**
     *
     * @return
     */
    public String toString();
}
