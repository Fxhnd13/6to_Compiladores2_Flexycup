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
 * clase que se encarga del analisis sintactico
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
     * Constructor vacio del parser, inicializa variables
     */
    public Parser(){
        this.pilaEstados = new ArrayList();
        this.pilaSimbolos = new ArrayList();
        this.historial = new ArrayList();
    }
    
    /**
     *
     * @return los tokens que se estan analizando
     */
    public List<Token> getTokens() {
        return tokens;
    }

    /**
     *
     * @return el historial de la pila durante el analisis
     */
    public List<Registro> getHistorial() {
        return historial;
    }

    /**
     *
     * @return los errores encontrados durante el analisis sintactico
     */
    public List<ErrorAnalisis> getErrores() {
        return errores;
    }

    /**
     * cambia el listado de errores
     * @param errores
     */
    public void setErrores(List<ErrorAnalisis> errores) {
        this.errores = errores;
    }

    /**
     * cambia el listado de tokens
     * @param tokens
     */
    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    /**
     *
     * @return el automata que se encarga del manejod e estados
     */
    public AutomataParser getAutomata() {
        return automata;
    }

    /**
     * cambia el automata que se encarga del manejo de estados
     * @param automata
     */
    public void setAutomata(AutomataParser automata) {
        this.automata = automata;
    }

    /**
     * agrega un nuevo registro al historial que tiene la pila durante el analisis sintactico
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
     * analisis mismo del listado de tokens generado por el analizador lexico, aqui verificamos que se encuentren correctamente
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
