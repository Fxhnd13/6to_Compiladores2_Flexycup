/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos.componentes.lexer;

import java.io.Serializable;

/**
 * Clase que almacena los datos de una transicion, con que caracter y cual es el estado destino al que
 * lleva
 * @author jose_
 */
public class Transicion implements Serializable {
    
    int idDestino;
    char caracter;

    Transicion(int salida, int destino, char caracter) {
        this.idDestino = destino;
        this.caracter = caracter;
    }

    Transicion(int destino, char caracter) {
        this.idDestino = destino;
        this.caracter = caracter;
    }

    public int getIdDestino() {
        return idDestino;
    }

    public void setIdDestino(int idDestino) {
        this.idDestino = idDestino;
    }

    public char getCaracter() {
        return caracter;
    }

    public void setCaracter(char caracter) {
        this.caracter = caracter;
    }
    
    public String toString(){
        return "Destino: "+idDestino+"|||Caracter: '"+caracter+"'";
    }
}
