/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos.componentes.parser;

import analizadores.objetos.componentes.parser.acciones.Accion;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author jose_
 */
public class AutomataParser {
    
    private List<Accion[]> acciones, irA;
    private List<Estado> estados;
    private Stack<Integer> pilaEstados;
    private Stack<String> pilaSimbolos;

    public AutomataParser(){
        this.acciones = new ArrayList();
        this.irA = new ArrayList();
        this.estados = new ArrayList();
        this.pilaEstados = new Stack();
        this.pilaSimbolos = new Stack();
    }
    public List<Accion[]> getAcciones() {
        return acciones;
    }

    public void setAcciones(List<Accion[]> acciones) {
        this.acciones = acciones;
    }

    public List<Accion[]> getIrA() {
        return irA;
    }

    public void setIrA(List<Accion[]> irA) {
        this.irA = irA;
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
    
    
}
