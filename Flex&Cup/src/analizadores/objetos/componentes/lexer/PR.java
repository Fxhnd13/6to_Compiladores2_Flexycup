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
public class PR  implements Serializable{
    
    String tipo;
    String cadena;

    /**
     *
     * @param tipo
     * @param cadena
     */
    public PR(String tipo, String cadena) {
        this.tipo = tipo;
        this.cadena = cadena;
    }

    /**
     *
     * @return
     */
    public String getTipo() {
        return tipo;
    }

    /**
     *
     * @param tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
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
        this.cadena = cadena;
    }
    
}
