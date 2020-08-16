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
public class NodoER {
    
    private String tipo, valor;
    private NodoER izquierdo, derecho;
    private int id;
    
    public NodoER(){}
    public NodoER(int numero, String valor, String tipo){
        this.id = numero;
        this.valor = valor;
        this.tipo = tipo;
    }
    public NodoER(String tipo, NodoER izquierdo, NodoER derecho){
        this.tipo = tipo;
        this.izquierdo = izquierdo;
        this.derecho = derecho;
    }
    
    
}
