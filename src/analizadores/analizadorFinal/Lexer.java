/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.analizadorFinal;

import analizadores.objetos.componentes.NodoER.GeneradorAutomata;
import analizadores.objetos.componentes.lexer.Automata;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jose_
 */
public class Lexer {
    
    private String cadena; 
    private List<Automata> automatas = new ArrayList<Automata>();
    private GeneradorAutomata generadorAutomata = new GeneradorAutomata();

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }

    public List<Automata> getAutomatas() {
        return automatas;
    }

    public void setAutomatas(List<Automata> automatas) {
        this.automatas = automatas;
    }

    public GeneradorAutomata getGeneradorAutomata() {
        return generadorAutomata;
    }

    public void setGeneradorAutomata(GeneradorAutomata generadorAutomata) {
        this.generadorAutomata = generadorAutomata;
    }
    
}
