/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.analizadorFinal;

import analizadores.objetos.componentes.Informacion;
import analizadores.objetos.componentes.lexer.Automata;
import analizadores.objetos.componentes.parser.AutomataParser;
import java.io.Serializable;

/**
 *
 * @author jose_
 */
public class Lenguaje implements Serializable {
    
    private String nombre;
    private Informacion informacion;
    private Lexer lexer = new Lexer();
    private Parser parser = new Parser();

    public Lenguaje(Informacion informacion, Automata automata, AutomataParser parserAutomata) {
        this.informacion = informacion;
        this.nombre = informacion.getNombre();
        this.lexer.setAutomata(automata);
        this.parser.setAutomata(parserAutomata);
    }

    public Lexer getLexer() {
        return lexer;
    }

    public void setLexer(Lexer lexer) {
        this.lexer = lexer;
    }

    public Parser getParser() {
        return parser;
    }

    public void setParser(Parser parser) {
        this.parser = parser;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Informacion getInformacion() {
        return informacion;
    }

    public void setInformacion(Informacion informacion) {
        this.informacion = informacion;
    }
    
}
