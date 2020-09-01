/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.analizadorFinal;

import analizadores.objetos.componentes.lexer.Token;
import analizadores.objetos.componentes.parser.AutomataParser;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author jose_
 */
public class Parser implements Serializable{
    
    List<Token> tokens;
    AutomataParser automata;

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    public AutomataParser getAutomata() {
        return automata;
    }

    public void setAutomata(AutomataParser automata) {
        this.automata = automata;
    }
}
