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
public class Aceptacion implements Accion, Serializable{

    
    @Override
    public String getSimbolo() {
        return "FinCadena";
    }

    @Override
    public int getValor() {
        return -1;
    }
    
    public String toString(){
        return "Aceptacion";
    }
}
