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
    
    private List<PR> palabrasReservadas;
    private List<Estado> estados;
    private List<Transicion> transiciones;
    int estadoActual;
    
    public Automata(){
        this.estados = new ArrayList<Estado>();
        this.transiciones = new ArrayList<Transicion>();
    }

    public List<PR> getPalabrasReservadas() {
        return palabrasReservadas;
    }

    public void setPalabrasReservadas(List<PR> palabrasReservadas) {
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
    
    @Override
    public String toString(){
        String cadena = "----------------Palabras Reservadas detectadas-------------------------";
        for (PR palabraReservada : palabrasReservadas) {
            cadena+=palabraReservada+"\n";
        }
        cadena+="--------------------------Estados------------------------------------";
        for (Estado estado : estados) {
            cadena+=estado.toString()+"\n";
        }
        cadena+="--------------------------Transiciones-------------------------------";
        for (Transicion transicion : transiciones) {
            cadena+=transicion.toString()+"\n";
        }
        return cadena;
    }
}
