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
    private List<Transicion> transiciones;
    private List<Token> tokens;
    private int estadoActual;
    private char[] caracteres;
    private Stack<Estado> estadosRecorridos;
    
    public Automata(){
        this.estados = new ArrayList<Estado>();
        this.transiciones = new ArrayList<Transicion>();
        this.palabrasReservadas = new ArrayList<PR>();
    }
    
    public void analizar(String cadena){
        estadosRecorridos = new Stack<Estado>();
        this.caracteres = cadena.toCharArray();
        tokens = new ArrayList<Token>();
        estadoActual = 0;
        int linea = 0, columna = 0, caracteresLeidos = 0;
        for (int i = 0; i < caracteres.length; i++) {
            if(existeTransicion(caracteres[i]) != (-1)){
                
            }else{
                
            }
        }
    }

    private int existeTransicion(char caracter){
        int valor = -1;
        for (int i = 0; i < transiciones.size(); i++) {
            if(transiciones.get(i).getIdSalida() == estadoActual){
                if(transiciones.get(i).getCaracter() == caracter) valor = i;
                break;
            }
        }
        return valor;
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

    public List<Transicion> getTransiciones() {
        return transiciones;
    }

    public void setTransiciones(List<Transicion> transiciones) {
        this.transiciones = transiciones;
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
        }
        cadena+="--------------------------Transiciones-------------------------------\n";
        for (Transicion transicion : transiciones) {
            cadena+=transicion.toString()+"\n";
        }
        return cadena;
    }
}
