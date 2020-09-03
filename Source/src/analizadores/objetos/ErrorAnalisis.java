/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos;

import java.io.Serializable;

/**
 * Clase que almacena la informacion cuando se encuentra un error durante el analisis de alguna cadena
 * @author jose_
 */
public class ErrorAnalisis implements Serializable{
    
    private String tipo, valor, descripcion;
    private int linea, columna;

    /**
     * constructor basico para un nuevo error
     * @param tipo lexico, sintactico, semantico
     * @param valor cadena que generó el error
     * @param descripcion descripcion del error encontrado
     * @param linea linea en la que se encontro
     * @param columna columna en la que se encontro
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
     * @return el tipo de error
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * cambia el tipo de error
     * @param tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     *
     * @return el valor del error
     */
    public String getValor() {
        return valor;
    }

    /**
     * cambia el valor del error
     * @param valor
     */
    public void setValor(String valor) {
        this.valor = valor;
    }

    /**
     *
     * @return la descripcion del error
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * cambia la descripcion del error
     * @param descripcion
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     *
     * @return la linea del error
     */
    public int getLinea() {
        return linea;
    }

    /**
     * cambia la linea del error
     * @param linea
     */
    public void setLinea(int linea) {
        this.linea = linea;
    }

    /**
     *
     * @return la columna del error
     */
    public int getColumna() {
        return columna;
    }

    /**
     * cambia la columna del error
     * @param columna
     */
    public void setColumna(int columna) {
        this.columna = columna;
    }
    
}
