/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos.componentes.lexer;

import analizadores.objetos.componentes.NodoER.Nodo;
import analizadores.objetos.componentes.NodoER.NodoAst;
import analizadores.objetos.componentes.NodoER.NodoConcat;
import analizadores.objetos.componentes.NodoER.NodoDis;
import analizadores.objetos.componentes.NodoER.NodoHoja;
import analizadores.objetos.componentes.NodoER.NodoMas;
import analizadores.objetos.componentes.NodoER.NodoQuiza;
import analizadores.objetos.componentes.Utilidades;

/**
 *
 * @author jose_
 */
public class GeneradorAutomata {
    
    private Nodo expresionRegular;
    private Automata automata;
    
    public GeneradorAutomata(){
        this.automata = new Automata();
    }

    public Automata getAutomata() {
        return automata;
    }

    public void setAutomata(Automata automata) {
        this.automata = automata;
    }
    
    public void setExpresionRegular(Nodo expresion){
        this.expresionRegular = expresion;
    }
    
    public Nodo getExpresionRegular(){ return expresionRegular; }
    
    public void calcularArbol(){
        numerarNodosHoja(1, expresionRegular);
        calcularSiguientes(expresionRegular);
    }
    
    public void crearEstadosAutomata(){
        int indiceEstadoActual = 0;
        
    }
    
    public int numerarNodosHoja(int id, Nodo expresion){
        if(expresion instanceof NodoConcat){
            id = numerarNodosHoja(id, ((NodoConcat) expresion).getIzquierdo());
            id = numerarNodosHoja(id, ((NodoConcat) expresion).getDerecho());
        }else if(expresion instanceof NodoDis){
            id = numerarNodosHoja(id, ((NodoConcat) expresion).getIzquierdo());
            id = numerarNodosHoja(id, ((NodoConcat) expresion).getDerecho());
        }else if(expresion instanceof NodoQuiza){
            id = numerarNodosHoja(id, ((NodoQuiza) expresion).getHijo());
        }else if(expresion instanceof NodoAst){
            id = numerarNodosHoja(id, ((NodoAst) expresion).getHijo());
        }else if(expresion instanceof NodoMas){
            id = numerarNodosHoja(id, ((NodoMas) expresion).getHijo());
        }else if(expresion instanceof NodoHoja){
            ((NodoHoja) expresion).setId(id++);
        }
        return id;
    }
    
    public void calcularSiguientes(Nodo expresion){
        if(expresion instanceof NodoConcat){
            calcularSiguientes(((NodoConcat) expresion).getIzquierdo());
            calcularSiguientes(((NodoConcat) expresion).getDerecho());
            for (int ultimo : ((NodoConcat) expresion).getIzquierdo().ultimos()) {
                expresion.agregarSiguientes(ultimo, ((NodoConcat) expresion).getDerecho().primeros());
            }
        }else if(expresion instanceof NodoDis){
            calcularSiguientes(((NodoConcat) expresion).getIzquierdo());
            calcularSiguientes(((NodoConcat) expresion).getDerecho());
        }else if(expresion instanceof NodoQuiza){
            calcularSiguientes(((NodoQuiza) expresion).getHijo());
        }else if(expresion instanceof NodoAst){
            calcularSiguientes(((NodoAst) expresion).getHijo());
            for (int ultimo : ((NodoAst) expresion).getHijo().ultimos()) {
                expresion.agregarSiguientes(ultimo, ((NodoAst) expresion).getHijo().primeros());
            }
        }else if(expresion instanceof NodoMas){
            calcularSiguientes(((NodoMas) expresion).getHijo());
            for (int ultimo : ((NodoMas) expresion).getHijo().ultimos()) {
                expresion.agregarSiguientes(ultimo, ((NodoMas) expresion).getHijo().primeros());
            }
        }
    }
    
    public void agregarRango(Nodo raiz, int opcion){
        char[] caracteres = (opcion == 0)? Utilidades.ARREGLO_LETRAS : Utilidades.ARREGLO_NUMEROS;
        for (int i = 0; i < caracteres.length; i++) {
            if(i == 0){
                Nodo izquierdo = new NodoHoja(caracteres[i++]);
                Nodo derecho = new NodoHoja(caracteres[i]);
                raiz = new NodoDis(izquierdo, derecho);
            }else{
                Nodo derecho = new NodoHoja(caracteres[i]);
                Nodo nuevo = new NodoDis(raiz, derecho);
                raiz = nuevo;
            }
        }
    }
    
    public void agregarCadena(Nodo raiz, String cadena){
        char[] caracteres = cadena.toCharArray();
        for (int i = 0; i < caracteres.length; i++) {
            if(caracteres.length > 2){
                if(i == 0){
                    Nodo izquierdo = new NodoHoja(caracteres[i++]);
                    Nodo derecho = new NodoHoja(caracteres[i]);
                    raiz = new NodoConcat(izquierdo, derecho);
                }else{
                    Nodo derecho = new NodoHoja(caracteres[i]);
                    Nodo nuevo = new NodoConcat(raiz, derecho);
                    raiz = nuevo;
                }
            }else{
                if(caracteres.length == 2){
                    Nodo izquierdo = new NodoHoja(caracteres[i++]);
                    Nodo derecho = new NodoHoja(caracteres[i]);
                    raiz = new NodoConcat(izquierdo, derecho);
                }else{
                    raiz = new NodoHoja(caracteres[i]);
                }
            }
        }
    }
}
