/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos;

import java.io.Serializable;

/**
 * Clase variable, utilizada para almacenar la informacion de una variable
 * @author jose_
 */
public class Variable implements Serializable{
    
    String id;
    Object valor;

    /**
     * constructor basico
     * @param id id de la variable
     * @param valor de la variable
     */
    public Variable(String id, Object valor){
        this.id = id;
        this.valor = valor;
    }
    
    /**
     *
     * @return el id de la variable
     */
    public String getId() {
        return id;
    }

    /**
     * cambia el id de la variable
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return el valor de la variable
     */
    public Object getValor() {
        return valor;
    }

    /**
     * cambia el valor de la variable
     * @param valor
     */
    public void setValor(Object valor) {
        this.valor = valor;
    }
    
}
