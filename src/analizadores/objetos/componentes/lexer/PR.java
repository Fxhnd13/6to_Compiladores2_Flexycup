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
public class PR {
    
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
