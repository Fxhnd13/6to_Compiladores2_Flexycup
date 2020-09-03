/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos.componentes;

/**
 * clase que almacena un dato para la informacion de un lenguaje
 * @author jose_
 */
public class Dato {
    
    private String tipo, valor;

    /**
     * constructor basico
     * @param tipo del dato
     * @param valor del dato
     */
    public Dato(String tipo, String valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

    /**
     *
     */
    public Dato() {}
    
    /**
     *
     * @return el tipo del dato
     */
    public String getTipo() {
        return tipo;
    }

    /**
     *
     * @param tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     *
     * @return
     */
    public String getValor() {
        return valor;
    }

    /**
     *
     * @param valor
     */
    public void setValor(String valor) {
        this.valor = valor;
    }
    
}
