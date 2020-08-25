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
public class NodoConcat implements Nodo {

    private Nodo izquierdo, derecho;
    
    public NodoConcat() {}
    public NodoConcat(Nodo izquierdo){
        this.izquierdo = izquierdo;
    }
    public NodoConcat(Nodo izquierdo, Nodo derecho){
        this.izquierdo = izquierdo;
        this.derecho = derecho;
    }
    public NodoConcat(NodoConcat nuevo){
        if(nuevo.getIzquierdo() instanceof NodoConcat){
            this.izquierdo = new NodoConcat((NodoConcat) nuevo.getIzquierdo());
        }else if(nuevo.getIzquierdo() instanceof NodoDis){
            this.izquierdo = new NodoDis((NodoDis) nuevo.getIzquierdo());
        }else if(nuevo.getIzquierdo()instanceof NodoMas){
            this.izquierdo = new NodoMas((NodoMas) nuevo.getIzquierdo());
        }else if(nuevo.getIzquierdo()instanceof NodoQuiza){
            this.izquierdo = new NodoQuiza((NodoQuiza)nuevo.getIzquierdo());
        }else if(nuevo.getIzquierdo() instanceof NodoHoja){
            this.izquierdo = new NodoHoja((NodoHoja) nuevo.getIzquierdo());
        }
        if(nuevo.getDerecho() instanceof NodoConcat){
            this.derecho = new NodoConcat((NodoConcat) nuevo.getDerecho());
        }else if(nuevo.getDerecho()instanceof NodoDis){
            this.derecho = new NodoDis((NodoDis) nuevo.getDerecho());
        }else if(nuevo.getDerecho()instanceof NodoMas){
            this.derecho = new NodoMas((NodoMas) nuevo.getDerecho());
        }else if(nuevo.getDerecho()instanceof NodoQuiza){
            this.derecho = new NodoQuiza((NodoQuiza)nuevo.getDerecho());
        }else if(nuevo.getDerecho()instanceof NodoHoja){
            this.derecho = new NodoHoja((NodoHoja) nuevo.getDerecho());
        }
    }

    public Nodo getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(Nodo izquierdo) {
        this.izquierdo = izquierdo;
    }

    public Nodo getDerecho() {
        return derecho;
    }

    public void setDerecho(Nodo derecho) {
        this.derecho = derecho;
    }
    
    @Override
    public int[] primeros() {
        int[] primeros = null;
        int[] primerosIzquierda = izquierdo.primeros();
        if(izquierdo.isAnulable()){
            int[] primerosDerecha = derecho.primeros();
            primeros = new int[primerosIzquierda.length + primerosDerecha.length];
            System.arraycopy(primerosIzquierda, 0, primeros, 0, primerosIzquierda.length);
            System.arraycopy(primerosDerecha  , 0, primeros, primerosIzquierda.length, primerosDerecha.length);
            primeros = Utilidades.ordenar(primeros);
        }else{
            primeros = primerosIzquierda;
        }
        return primeros;
    }

    @Override
    public int[] ultimos() {
        int[] ultimos = null;
        int[] ultimosDerecha = derecho.ultimos();
        if(derecho.isAnulable()){
            int[] ultimosIzquierda = izquierdo.ultimos();
            ultimos = new int[ultimosIzquierda.length + ultimosDerecha.length];
            System.arraycopy(ultimosIzquierda, 0, ultimos, 0, ultimosIzquierda.length);
            System.arraycopy(ultimosDerecha  , 0, ultimos, ultimosIzquierda.length, ultimosDerecha.length);
            ultimos = Utilidades.ordenar(ultimos);
        }else{
            ultimos = ultimosDerecha;
        }
        return ultimos;
    }

    @Override
    public int[] siguientes(int id) {
        if(izquierdo.siguientes(id) != null){
            return izquierdo.siguientes(id);
        }else{
            return derecho.siguientes(id);
        }
    }

    @Override
    public boolean isAnulable() {
        return (izquierdo.isAnulable() && derecho.isAnulable());
    }

    @Override
    public void agregarSiguientes(int id, int[] ids) {
        if(izquierdo.siguientes(id) != null){
            izquierdo.agregarSiguientes(id, ids);
        }else{
            derecho.agregarSiguientes(id, ids);
        }
    }
    
}
