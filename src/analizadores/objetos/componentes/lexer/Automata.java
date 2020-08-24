/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos.componentes.lexer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author jose_
 */
public class Automata {
    
    private List<PR> palabrasReservadas;
    private List<Estado> estados;
    private List<Token> tokens;
    private List<String> errores;
    private int estadoActual, buffer, linea, columna, indiceCaracter;
    private char[] caracteres;
    private Stack<Integer> estadosRecorridos;
    private String cadena;
    
    public Automata(){
        this.estados = new ArrayList<Estado>();
        this.palabrasReservadas = new ArrayList<PR>();
        this.errores = new ArrayList<String>();
    }
    
    public void analizar(){
        estadosRecorridos = new Stack<Integer>();
        caracteres = cadena.toCharArray();
        tokens = new ArrayList<Token>();
        estadoActual = 0;
        buffer = 0;
        linea = 1; columna = 1;
        for (indiceCaracter = 0; indiceCaracter < caracteres.length; indiceCaracter++) {
            if(caracteres[indiceCaracter] == '\n') {
                linea++;
                columna = 1;
            }
            Transicion transicion = null;
            if((transicion = existeTransicion(caracteres[indiceCaracter])) != null){
                buffer++;
                estadosRecorridos.add(estadoActual);
                estadoActual = transicion.getIdDestino();
            }else{
                if(estados.get(estadoActual).isEstadoFinal()){
                    tokens.add(new Token(linea, columna, cadena.substring(0, buffer), estados.get(estadoActual).getTipoToken()));
                    cadena = cadena.substring(buffer, cadena.length());
                    reiniciarAutomata();
                }else{
                    verificarRetroceso();
                }
            }
            columna++;
        }
    }

    private void verificarRetroceso(){
        if(estadosRecorridos.isEmpty()){
            errores.add("Se encontro un error en <linea: "+linea+", columna: "+columna+" con el caracter : '"+caracteres[indiceCaracter]);
        }else{
            int estadoActual = estadosRecorridos.pop();
            if(caracteres[indiceCaracter] == '\n') linea--;
            columna--; buffer--; indiceCaracter--;
            if(estados.get(estadoActual).isEstadoFinal()){
                    tokens.add(new Token(linea, columna, cadena.substring(0, buffer), estados.get(estadoActual).getTipoToken()));
                    cadena = cadena.substring(buffer, cadena.length());
                    reiniciarAutomata();
            }else{
                verificarRetroceso();
            }
        }
    }
    
    private void reiniciarAutomata(){
        estadoActual = 0;
        buffer = 0;
        estadosRecorridos.clear();
    }
    
    private Transicion existeTransicion(char caracter){
        Transicion valor = null;
        for (Transicion transicion : estados.get(estadoActual).getTransiciones()) {
            if(transicion.getCaracter() == caracter) valor = transicion;
        }
        return valor;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    public List<String> getErrores() {
        return errores;
    }

    public void setErrores(List<String> errores) {
        this.errores = errores;
    }

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }
    
    public List<PR> getPalabrasReservadas() {
        return palabrasReservadas;
    }

    public void setPalabrasReservadas(List<PR> palabrasReservadas) {
        this.palabrasReservadas = palabrasReservadas;
    }

    public List<Estado> getEstados() {
        return estados;
    }

    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }
    
    @Override
    public String toString(){
        String cadena = "----------------Palabras Reservadas detectadas-------------------------\n";
        for (PR palabraReservada : palabrasReservadas) {
            cadena+=palabraReservada+"\n";
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
