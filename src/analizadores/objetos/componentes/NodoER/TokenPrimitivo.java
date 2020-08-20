/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos.componentes.NodoER;

/**
 *
 * @author jose_
 */
public class TokenPrimitivo {
    
    private String identificador;
    private Nodo expresionRegular;

    public TokenPrimitivo(String id, Nodo expresion) {
        this.identificador = id;
        this.expresionRegular = expresion;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public Nodo getExpresionRegular() {
        return expresionRegular;
    }

    public void setExpresionRegular(Nodo expresionRegular) {
        this.expresionRegular = expresionRegular;
    }
    
}
