/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos.componentes.lexer;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jose_
 */
public class Automata {
    
    private List<String> palabrasReservadas;
    private List<Estado> estados;
    private List<Transicion> transiciones;
    int estadoActual;
    
    public Automata(){
        this.estados = new ArrayList<Estado>();
        this.transiciones = new ArrayList<Transicion>();
    }

    public List<String> getPalabrasReservadas() {
        return palabrasReservadas;
    }

    public void setPalabrasReservadas(List<String> palabrasReservadas) {
        this.palabrasReservadas = palabrasReservadas;
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

    public List<Transicion> getTransiciones() {
        return transiciones;
    }

    public void setTransiciones(List<Transicion> transiciones) {
        this.transiciones = transiciones;
    }
    
}
