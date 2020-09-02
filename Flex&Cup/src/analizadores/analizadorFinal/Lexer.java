/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.analizadorFinal;

import analizadores.objetos.componentes.lexer.GeneradorAutomata;
import analizadores.objetos.componentes.lexer.Automata;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jose_
 */
public class Lexer implements Serializable {
    
    private String cadena; 
    private Automata automata = new Automata();

    /**
     *
     * @return
     */
    public String getCadena() {
        return cadena;
    }

    /**
     *
     * @param cadena
     */
    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    /**
     *
     * @return
     */
    public Automata getAutomata() {
        return automata;
    }

    /**
     *
     * @param automata
     */
    public void setAutomata(Automata automata) {
        this.automata = automata;
    }
    
}
