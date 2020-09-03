/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos.componentes.lexer;

/**
 * Clase que almacena la información para algunas busquedas
 * @author jose_
 */
class ParametroBusqueda {
    
    private boolean valorRetorno = false, encontrado = false;
    private char valor;

    public ParametroBusqueda(char valor) {
        this.valor = valor;
    }

    public boolean isValorRetorno() {
        return valorRetorno;
    }

    public void setValorRetorno(boolean valorRetorno) {
        this.valorRetorno = valorRetorno;
    }

    public boolean isEncontrado() {
        return encontrado;
    }

    public void setEncontrado(boolean encontrado) {
        this.encontrado = encontrado;
    }

    public char getValor() {
        return valor;
    }

    public void setValor(char valor) {
        this.valor = valor;
    }
    
}
