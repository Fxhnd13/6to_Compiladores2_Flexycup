/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos.componentes.lexer;

import java.io.Serializable;

/**
 *
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

    /**
     *
     * @return
     */
    public int getIdDestino() {
        return idDestino;
    }

    /**
     *
     * @param idDestino
     */
    public void setIdDestino(int idDestino) {
        this.idDestino = idDestino;
    }

    /**
     *
     * @return
     */
    public char getCaracter() {
        return caracter;
    }

    /**
     *
     * @param caracter
     */
    public void setCaracter(char caracter) {
        this.caracter = caracter;
    }
    
    public String toString(){
        return "Destino: "+idDestino+"|||Caracter: '"+caracter+"'";
    }
}
