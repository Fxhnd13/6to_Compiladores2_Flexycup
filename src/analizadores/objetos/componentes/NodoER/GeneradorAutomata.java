/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos.componentes.NodoER;

import analizadores.objetos.componentes.Utilidades;
import java.util.List;

/**
 *
 * @author jose_
 */
public class GeneradorAutomata {
    
    List<Nodo> expresionesRegulares;
    
    public int agregarRango(int id, Nodo raiz, int opcion){
        char[] caracteres = (opcion == 0)? Utilidades.ARREGLO_LETRAS : Utilidades.ARREGLO_NUMEROS;
        Nodo nodoActual = new NodoDis();
        raiz = nodoActual;
        for (int i = 0; i < caracteres.length; i++) {
            if((i+2) < caracteres.length){ //si no estamos en la ultima letra
                Nodo izquierdo = new NodoHoja(id++, caracteres[i]); //creamos el nodo que guardara el caracter a la izquierda
                ((NodoDis)nodoActual).setIzquierdo(izquierdo); //asignamos al hijo izquierdo del nodo que estamos evaluando el nodo hoja recién creado
                Nodo nuevo = new NodoDis(); //creamos el nuevo nodo disyuncion que tendrá el hijo derecho del nodo que estamos evaluando
                ((NodoDis)nodoActual).setDerecho(nuevo); //asignamos al hijo derecho del nodo que estamos evaluando el nodo disyuncion recien creado
                nodoActual = nuevo; //el nuevo nodo que vamos a evaluar, va a ser el nodo disyuncion que recien creamos
            }else{
                Nodo izquierdo = new NodoHoja(id++, caracteres[i++]); //creamos el nodo que guardara el caracter a la izquierda y adelantamos uno
                Nodo derecho = new NodoHoja(id++, caracteres[i]); //creamos el nodo que guardará el último caracter
                Nodo nuevo = new NodoDis(izquierdo, derecho); //creamos el nuevo nodo disyuncion que tendrá el hijo derecho del nodo que estamos evaluando
                ((NodoDis)nodoActual).setDerecho(nuevo);
            }
        }
        return id;
    }
    
    public int agregarcadena(int id, Nodo raiz, String cadena){
        char[] caracteres = cadena.toCharArray();
        Nodo nodoActual = new NodoConcat();
        raiz = nodoActual;
        for (int i = 0; i < caracteres.length; i++) {
            if((i+2) < caracteres.length){ //si no estamos en la ultima letra
                Nodo izquierdo = new NodoHoja(id++, caracteres[i]); //creamos el nodo que guardara el caracter a la izquierda
                ((NodoConcat)nodoActual).setIzquierdo(izquierdo); //asignamos al hijo izquierdo del nodo que estamos evaluando el nodo hoja recién creado
                Nodo nuevo = new NodoConcat(); //creamos el nuevo nodo disyuncion que tendrá el hijo derecho del nodo que estamos evaluando
                ((NodoConcat)nodoActual).setDerecho(nuevo); //asignamos al hijo derecho del nodo que estamos evaluando el nodo disyuncion recien creado
                nodoActual = nuevo; //el nuevo nodo que vamos a evaluar, va a ser el nodo disyuncion que recien creamos
            }else{
                Nodo izquierdo = new NodoHoja(id++, caracteres[i++]); //creamos el nodo que guardara el caracter a la izquierda y adelantamos uno
                Nodo derecho = new NodoHoja(id++, caracteres[i]); //creamos el nodo que guardará el último caracter
                Nodo nuevo = new NodoConcat(izquierdo, derecho); //creamos el nuevo nodo disyuncion que tendrá el hijo derecho del nodo que estamos evaluando
                ((NodoConcat)nodoActual).setDerecho(nuevo);
            }
        }
        return id;
    }
}
