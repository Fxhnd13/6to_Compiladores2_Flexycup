/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos.componentes.NodoER;

import analizadores.objetos.componentes.Utilidades;


/**
 *
 * @author jose_
 */
public class NodoDis implements Nodo{

    private Nodo izquierdo, derecho;
    
    public NodoDis(Nodo izquierdo, Nodo derecho){
        this.izquierdo = izquierdo;
        this.derecho = derecho;
    }
    
    @Override
    public int[] primeros() {
        int[] primeros = null;
        int[] primerosIzquierda = izquierdo.primeros();
        int[] primerosDerecha = derecho.primeros();
        System.arraycopy(primerosIzquierda, 0, primeros, 0, primerosIzquierda.length);
        System.arraycopy(primerosDerecha  , 0, primeros, primerosIzquierda.length, primerosDerecha.length);
        primeros = Utilidades.ordenar(primeros);
        return primeros;
    }

    @Override
    public int[] ultimos() {
        int[] ultimos = null;
        int[] ultimosIzquierda = izquierdo.ultimos();
        int[] ultimosDerecha = derecho.ultimos();
        System.arraycopy(ultimosIzquierda, 0, ultimos, 0, ultimosIzquierda.length);
        System.arraycopy(ultimosDerecha  , 0, ultimos, ultimosIzquierda.length, ultimosDerecha.length);
        ultimos = Utilidades.ordenar(ultimos);
        return ultimos;
    }

    @Override
    public int[] siguientes(int id) {
        if(Utilidades.existe(id, izquierdo.primeros())||Utilidades.existe(id, izquierdo.ultimos())){
            return izquierdo.siguientes(id);
        }else{
            return derecho.siguientes(id);
        }
    }

    @Override
    public boolean isAnulable() {
        return (izquierdo.isAnulable() || derecho.isAnulable());
    }

    @Override
    public void agregarSiguientes(int id, int[] ids) {
        if(Utilidades.existe(id, izquierdo.primeros())||Utilidades.existe(id, izquierdo.ultimos())){
            izquierdo.agregarSiguientes(id, ids);
        }else{
            derecho.agregarSiguientes(id, ids);
        }
    }
    
}
