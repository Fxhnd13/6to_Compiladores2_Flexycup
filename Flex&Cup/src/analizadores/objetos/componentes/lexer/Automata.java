/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos.componentes.lexer;

import analizadores.objetos.ErrorAnalisis;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author jose_
 */
public class Automata implements Serializable {
    
    private List<PR> palabrasReservadas; //para comparar palabras reservadas al final
    private List<Estado> estados; //conjunto de estados, donde se encuentran las transiciones que cada uno posee
    private List<Token> tokens; //listado de tokens encontrados
    private List<ErrorAnalisis> errores; //listado de errores encontrados
    private int estadoActual, buffer, linea, columna, indiceCaracter; //herramientas para la lectura
    private char[] caracteres; //caracteres para el analisis
    private Stack<Integer> estadosRecorridos; //para recuperacion de errores
    private String cadena; //cadena a analizar
    
    /**
     *
     */
    public Automata(){
        this.estados = new ArrayList();
        this.palabrasReservadas = new ArrayList();
        this.errores = new ArrayList();
    }
    
    /**
     *
     */
    public void analizar(){
        this.estadosRecorridos = new Stack();
        this.caracteres = cadena.toCharArray();
        this.tokens = new ArrayList();
        this.estadoActual = 0;
        this.buffer = 0;
        this.linea = 1; columna = 1;
        for (this.indiceCaracter = 0; this.indiceCaracter < this.caracteres.length; this.indiceCaracter++) {
            if(this.caracteres[this.indiceCaracter] == '\n') {
                this.linea++;
                this.columna = 1;
            }
            Transicion transicion = null;
            if((transicion = existeTransicion(this.caracteres[this.indiceCaracter])) != null){
                this.buffer++;
                this.estadosRecorridos.add(this.estadoActual);
                this.estadoActual = transicion.getIdDestino();
            }else{
                if(!this.estadosRecorridos.isEmpty())this.indiceCaracter--;
                if(this.estados.get(this.estadoActual).isEstadoFinal()){
                    this.verificarEstado();
                }else{
                    this.verificarRetroceso();
                }
            }
            this.columna++;
            if(((this.indiceCaracter+1) == this.caracteres.length)&&(!this.estadosRecorridos.isEmpty())) this.verificarEstado();
        }
        this.tokens.add(new Token(linea,columna,"$","FinCadena"));
    }

    private void verificarEstado(){
        String tipoToken = "", lexema = cadena.substring(0, this.buffer);
        if(this.estados.get(this.estadoActual).getTipoToken() == null){
            for (PR palabraReservada : this.palabrasReservadas) {
                if(palabraReservada.getCadena().equals(lexema)){
                    tipoToken = palabraReservada.getTipo();
                }
            }
        }else{
            tipoToken = this.estados.get(this.estadoActual).getTipoToken();
        }
        if(!tipoToken.equals("&")) this.tokens.add(new Token(this.linea, this.columna-this.buffer, lexema, tipoToken));
        this.cadena = this.cadena.substring(this.buffer, this.cadena.length());
        this.reiniciarAutomata();
    }
    
    private void verificarRetroceso(){
        if(this.estadosRecorridos.isEmpty()){
            this.errores.add(new ErrorAnalisis("Lexico",String.valueOf(this.caracteres[this.indiceCaracter]),"El caracter no pertenece a lo especificado en el archivo de entrada.",this.linea, this.columna));
            this.cadena = this.cadena.substring(1,cadena.length());
        }else{
            this.estadoActual = this.estadosRecorridos.pop();
            if(this.caracteres[this.indiceCaracter] == '\n') this.linea--;
            this.columna--; this.buffer--; this.indiceCaracter--;
            if(this.estados.get(estadoActual).isEstadoFinal()){
                this.verificarEstado();
            }else{
                this.verificarRetroceso();
            }
        }
    }
    
    private void reiniciarAutomata(){
        this.estadoActual = 0;
        this.buffer = 0;
        this.estadosRecorridos.clear();
    }
    
    private Transicion existeTransicion(char caracter){
        Transicion valor = null;
        for (Transicion transicion : this.estados.get(this.estadoActual).getTransiciones()) {
            if(transicion.getCaracter() == caracter) valor = transicion;
        }
        return valor;
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
     * @param tokens
     */
    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
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
        this.errores.clear();
        this.cadena = cadena;
    }
    
    /**
     *
     * @return
     */
    public List<PR> getPalabrasReservadas() {
        return palabrasReservadas;
    }

    /**
     *
     * @param palabrasReservadas
     */
    public void setPalabrasReservadas(List<PR> palabrasReservadas) {
        this.palabrasReservadas = palabrasReservadas;
    }

    /**
     *
     * @return
     */
    public List<Estado> getEstados() {
        return estados;
    }

    /**
     *
     * @param estados
     */
    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }
    
    @Override
    public String toString(){
        String cadena = "----------------Palabras Reservadas detectadas-------------------------\n";
        for (PR palabraReservada : palabrasReservadas) {
            cadena+="Tipo: "+palabraReservada.getTipo()+"-- Lexema: "+palabraReservada.getCadena()+"\n";
        }
        cadena+="--------------------------Estados------------------------------------\n";
        for (int i = 0; i < estados.size(); i++) {
            cadena+="No: "+i+"|||"+estados.get(i).toString()+"|||Token: "+estados.get(i).getTipoToken()+"\n";
            cadena+="Transiciones {\n";
            for (Transicion transicion : estados.get(i).getTransiciones()) {
                cadena+=transicion.toString()+"\n";
            }
            cadena+="}\n";
        }
        return cadena;
    }
}
