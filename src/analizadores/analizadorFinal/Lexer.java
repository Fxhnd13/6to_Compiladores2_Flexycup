/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.analizadorFinal;

import analizadores.objetos.componentes.lexer.GeneradorAutomata;
import analizadores.objetos.componentes.lexer.Automata;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jose_
 */
public class Lexer {
    
    private String cadena; 
    private Automata automata = new Automata();

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }
    
}
