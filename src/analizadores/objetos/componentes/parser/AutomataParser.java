/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos.componentes.parser;

import analizadores.objetos.TablaDeSimbolos;
import analizadores.objetos.componentes.parser.acciones.Accion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author jose_
 */
public class AutomataParser implements Serializable{
    
    private List<Estado> estados;
    private TablaDeSimbolos simbolos;
    private List<Produccion> producciones;
    private Stack<Integer> pilaEstados;
    private Stack<String> pilaSimbolos;

    public AutomataParser(){
        this.estados = new ArrayList();
        this.pilaEstados = new Stack();
        this.pilaSimbolos = new Stack();
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

    public Stack<Integer> getPilaEstados() {
        return pilaEstados;
    }

    public void setPilaEstados(Stack<Integer> pilaEstados) {
        this.pilaEstados = pilaEstados;
    }

    public Stack<String> getPilaSimbolos() {
        return pilaSimbolos;
    }

    public void setPilaSimbolos(Stack<String> pilaSimbolos) {
        this.pilaSimbolos = pilaSimbolos;
    }

    public TablaDeSimbolos getSimbolos() {
        return simbolos;
    }

    public void setSimbolos(TablaDeSimbolos simbolos) {
        this.simbolos = simbolos;
    }

    public List<Produccion> getProducciones() {
        return producciones;
    }

    public void setProducciones(List<Produccion> producciones) {
        this.producciones = producciones;
    }
    
}
