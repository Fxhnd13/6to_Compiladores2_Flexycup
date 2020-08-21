/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos.componentes.NodoER;

import analizadores.objetos.componentes.Utilidades;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jose_
 */
public class GeneradorAutomata {
    
    private List<TokenPrimitivo> tokens;
    
    public GeneradorAutomata(){
        tokens = new ArrayList<TokenPrimitivo>();
    }
    
    public List<TokenPrimitivo> getTokens(){return tokens; }
    
    public void calcularTodosLosSiguientes(){
        for (TokenPrimitivo token : tokens) {
            calcularSiguientes(token.getExpresionRegular());
        }
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
    
    public int agregarRango(int id, Nodo raiz, int opcion){
        char[] caracteres = (opcion == 0)? Utilidades.ARREGLO_LETRAS : Utilidades.ARREGLO_NUMEROS;
        for (int i = 0; i < caracteres.length; i++) {
            if(i == 0){
                Nodo izquierdo = new NodoHoja(id++, caracteres[i++]);
                Nodo derecho = new NodoHoja(id++, caracteres[i]);
                raiz = new NodoDis(izquierdo, derecho);
            }else{
                Nodo derecho = new NodoHoja(id++, caracteres[i]);
                Nodo nuevo = new NodoDis(raiz, derecho);
                raiz = nuevo;
            }
        }
        return id;
    }
    
    public int agregarCadena(int id, Nodo raiz, String cadena){
        char[] caracteres = cadena.toCharArray();
        for (int i = 0; i < caracteres.length; i++) {
            if(caracteres.length > 2){
                if(i == 0){
                    Nodo izquierdo = new NodoHoja(id++, caracteres[i++]);
                    Nodo derecho = new NodoHoja(id++, caracteres[i]);
                    raiz = new NodoConcat(izquierdo, derecho);
                }else{
                    Nodo derecho = new NodoHoja(id++, caracteres[i]);
                    Nodo nuevo = new NodoConcat(raiz, derecho);
                    raiz = nuevo;
                }
            }else{
                if(caracteres.length == 2){
                    Nodo izquierdo = new NodoHoja(id++, caracteres[i++]);
                    Nodo derecho = new NodoHoja(id++, caracteres[i]);
                    raiz = new NodoConcat(izquierdo, derecho);
                }else{
                    raiz = new NodoHoja(id++, caracteres[i]);
                }
            }
        }
        return id;
    }
}
