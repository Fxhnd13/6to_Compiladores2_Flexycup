/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos.componentes;

/**
 *
 * @author jose_
 */
public class Dato {
    
    private String tipo, valor;

    public Dato(String tipo, String valor) {
        this.tipo = tipo;
        this.valor = valor;
    }

    public Dato() {}
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
    
}