/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos.componentes.lexer;

import java.io.Serializable;

/**
 * Clase que almacena la informacion de las palabras reservadas que existan dentro del lenguaje declarado
 * @author jose_
 */
public class PR  implements Serializable{
    
    String tipo;
    String cadena;

    public PR(String tipo, String cadena) {
        this.tipo = tipo;
        this.cadena = cadena;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCadena() {
        return cadena;
    }

    public void setCadena(String cadena) {
        this.cadena = cadena;
    }
    
}
