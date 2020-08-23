/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos.componentes.lexer;

/**
 *
 * @author jose_
 */
public class Transicion {
    
    int idSalida, idDestino;
    char caracter;

    Transicion(int salida, int destino, char caracter) {
        this.idSalida = salida;
        this.idDestino = destino;
        this.caracter = caracter;
    }

    public int getIdSalida() {
        return idSalida;
    }

    public void setIdSalida(int idSalida) {
        this.idSalida = idSalida;
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
        return "Salida: "+idSalida+"|||Destino: "+idDestino+"|||Caracter: '"+caracter+"'";
    }
}
