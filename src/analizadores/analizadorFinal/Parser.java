/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.analizadorFinal;

import analizadores.objetos.ErrorAnalisis;
import analizadores.objetos.componentes.lexer.Token;
import analizadores.objetos.componentes.parser.AutomataParser;
import analizadores.objetos.componentes.parser.Produccion;
import analizadores.objetos.componentes.parser.acciones.Accion;
import analizadores.objetos.componentes.parser.acciones.Aceptacion;
import analizadores.objetos.componentes.parser.acciones.Reduce;
import analizadores.objetos.componentes.parser.acciones.Shift;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jose_
 */
public class Parser implements Serializable{
    
    private List<Token> tokens;
    private AutomataParser automata;
    private List<Integer> pilaEstados;
    private List<String> pilaSimbolos;
    private List<Registro> historial;
    private List<ErrorAnalisis> errores;

    /**
     *
     */
    public Parser(){
        this.pilaEstados = new ArrayList();
        this.pilaSimbolos = new ArrayList();
        this.historial = new ArrayList();
    }
    
    /**
     *
     * @return
     */
    public List<Token> getTokens() {
        return tokens;
    }

    /**
     *
     * @return
     */
    public List<Registro> getHistorial() {
        return historial;
    }

    /**
     *
     * @param historial
     */
    public void setHistorial(List<Registro> historial) {
        this.historial = historial;
    }

    /**
     *
     * @return
     */
    public List<ErrorAnalisis> getErrores() {
        return errores;
    }

    /**
     *
     * @param errores
     */
    public void setErrores(List<ErrorAnalisis> errores) {
        this.errores = errores;
    }

    /**
     *
     * @param tokens
     */
    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    /**
     *
     * @return
     */
    public AutomataParser getAutomata() {
        return automata;
    }

    /**
     *
     * @param automata
     */
    public void setAutomata(AutomataParser automata) {
        this.automata = automata;
    }

    /**
     *
     * @param accion
     */
    public void agregarRegistro(Accion accion){
        String pila = "";
        for (int i = 0; i < this.pilaEstados.size(); i++) {
            pila+=this.pilaEstados.get(i);
            if((i+1) < this.pilaEstados.size()) pila+="<<";
        }
        String pilaSimbolos ="";
        for (int i = 0; i < this.pilaSimbolos.size(); i++) {
            pilaSimbolos+=this.pilaSimbolos.get(i);
            if((i+1) < this.pilaSimbolos.size()) pilaSimbolos+="<<";
        }
        String pilaTokens = "";
        for (int i = 0; i < this.tokens.size(); i++) {
            pilaTokens+=this.tokens.get(i).getTipo();
            if((i+1) < this.tokens.size()) pilaTokens+=">>";
        }
        historial.add(new Registro(pila, pilaSimbolos,pilaTokens, accion.toString()));
    }
    
    /**
     *
     */
    public void analizar() {
        this.historial.clear();
        pilaEstados.clear();
        pilaSimbolos.clear();
        pilaEstados.add(0);
        while(!this.tokens.isEmpty()){
            String simbolo = tokens.get(0).getTipo();
            Accion accion = obtenerAccion(simbolo);
            if(accion != null){
                agregarRegistro(accion);
                if(accion instanceof Shift){
                    tokens.remove(0);
                    pilaSimbolos.add(simbolo);
                    pilaEstados.add(accion.getValor());
                }else if(accion instanceof Reduce){
                    Produccion produccion = this.automata.getProducciones().get(accion.getValor());
                    for (int i = 0; i < produccion.getSimbolosDerecha().size(); i++) {
                        pilaSimbolos.remove(pilaSimbolos.size()-1);
                        pilaEstados.remove(pilaEstados.size()-1);
                    }
                    pilaSimbolos.add(produccion.getSimboloIzquierda().getSimbolo());
                    simbolo = pilaSimbolos.get(pilaSimbolos.size()-1);
                    accion = obtenerAccion(simbolo);
                    agregarRegistro(accion);
                    pilaEstados.add(accion.getValor());
                }else if(accion instanceof Aceptacion){
                    break;
                }
            }else{
                errores.add(new ErrorAnalisis("Sintactico",tokens.get(0).getLexema(),"No se esperaba un "+simbolo,tokens.get(0).getLinea(),tokens.get(0).getColumna()));
                while(accion == null){
                    if(!tokens.isEmpty()){
                        simbolo = tokens.get(0).getTipo();
                        tokens.remove(0);
                        accion = obtenerAccion(simbolo);
                    }else{
                        break;
                    }
                }
            }
        }
    }
    
    private Accion obtenerAccion(String simbolo){
        Accion accion = null;
        int estadoActual = pilaEstados.get(pilaEstados.size()-1);
        for (Accion accionT : this.automata.getEstados().get(estadoActual).getAcciones()) {
            if(accionT.getSimbolo().equals(simbolo)) accion = accionT;
        }
        return accion;
    }
}
