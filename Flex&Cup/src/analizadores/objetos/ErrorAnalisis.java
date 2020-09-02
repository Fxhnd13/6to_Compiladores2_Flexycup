/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos;

import java.io.Serializable;

/**
 *
 * @author jose_
 */
public class ErrorAnalisis implements Serializable{
    
    private String tipo, valor, descripcion;
    private int linea, columna;

    /**
     *
     * @param tipo
     * @param valor
     * @param descripcion
     * @param linea
     * @param columna
     */
    public ErrorAnalisis(String tipo, String valor, String descripcion, int linea, int columna) {
        this.tipo = tipo;
        this.valor = valor;
        this.descripcion = descripcion;
        this.linea = linea;
        this.columna = columna;
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
    public String getValor() {
        return valor;
    }

    /**
     *
     * @param valor
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     *
     * @return
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     *
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     *
     * @return
     */
    public int getLinea() {
        return linea;
    }

    /**
     *
     * @param linea
     */
    public void setLinea(int linea) {
        this.linea = linea;
    }

    /**
     *
     * @return
     */
    public int getColumna() {
        return columna;
    }

    /**
     *
     * @param columna
     */
    public void setColumna(int columna) {
        this.columna = columna;
    }
    
}
