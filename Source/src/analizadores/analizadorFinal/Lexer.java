/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.analizadorFinal;

import analizadores.objetos.componentes.lexer.Automata;
import java.io.Serializable;

/**
 * clase que se encarga de hacer el analisis lexico
 * @author jose_
 */
public class Lexer implements Serializable {
    
    private String cadena; 
    private Automata automata = new Automata();

    /**
     *
     * @return la cadena que se este analizando
     */
    public String getCadena() {
        return cadena;
    }

    /**
     * cambia la cadena que se esta analizando
     * @param cadena
     */
    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    /**
     *
     * @return el automata del lexer
     */
    public Automata getAutomata() {
        return automata;
    }

    /**
     * retorna el automata del lexer
     * @param automata
     */
    public void setAutomata(Automata automata) {
        this.automata = automata;
    }
    
}
