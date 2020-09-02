/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos;

import java.io.Serializable;

/**
 *
 * @author jose_
 */
public class Variable implements Serializable{
    
    String id;
    Object valor;

    /**
     *
     * @param id
     * @param valor
     */
    public Variable(String id, Object valor){
        this.id = id;
        this.valor = valor;
    }
    
    /**
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public Object getValor() {
        return valor;
    }

    /**
     *
     * @param valor
     */
    public void setValor(Object valor) {
        this.valor = valor;
    }
    
}
