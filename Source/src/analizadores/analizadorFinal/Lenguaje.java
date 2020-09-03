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
 * Clase que se utiliza para guardar los lenguajes generados
 * @author jose_
 */
public class Lenguaje implements Serializable {
    
    private String nombre;
    private Informacion informacion;
    private Lexer lexer = new Lexer();
    private Parser parser = new Parser();

    /**
     * Constructor general de un lenguaje, con un lexer y parser
     * @param informacion informacion del lenguaje
     * @param automata automata del lexer
     * @param parserAutomata automata del parser
     */
    public Lenguaje(Informacion informacion, Automata automata, AutomataParser parserAutomata) {
        this.informacion = informacion;
        this.nombre = informacion.getNombre();
        this.lexer.setAutomata(automata);
        this.parser.setAutomata(parserAutomata);
    }

    /**
     *
     * @return el lexer del lenguaje
     */
    public Lexer getLexer() {
        return lexer;
    }

    /**
     * cambia el lexer de la clase
     * @param lexer
     */
    public void setLexer(Lexer lexer) {
        this.lexer = lexer;
    }

    /**
     *
     * @return el parser del lenguaje
     */
    public Parser getParser() {
        return parser;
    }

    /**
     *
     * @param parser
     */
    public void setParser(Parser parser) {
        this.parser = parser;
    }

    /**
     * 
     * @return el nombre del lenguaje
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * cambia el nombre del lenguaje
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @return la informacion del lenguaje
     */
    public Informacion getInformacion() {
        return informacion;
    }

    /**
     *  cambia la informacion del lenguaje
     * @param informacion
     */
    public void setInformacion(Informacion informacion) {
        this.informacion = informacion;
    }
    
}
