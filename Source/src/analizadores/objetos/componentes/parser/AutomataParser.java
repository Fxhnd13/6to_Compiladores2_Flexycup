/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos.componentes.parser;

import analizadores.objetos.TablaDeSimbolos;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que almacena todos los datos de un automata para el analisis sintactico, es decir el LR(1)
 * @author jose_
 */
public class AutomataParser implements Serializable{
    
    private List<Estado> estados;
    private TablaDeSimbolos simbolos;
    private List<Produccion> producciones;

    public AutomataParser(){
        this.estados = new ArrayList();
    }

    public List<Estado> getEstados() {
        return estados;
    }


    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

    public TablaDeSimbolos getSimbolos() {
        return simbolos;
    }

    public void setSimbolos(TablaDeSimbolos simbolos) {
        this.simbolos = simbolos;
    }

    public List<Produccion> getProducciones() {
        return producciones;
    }

    public void setProducciones(List<Produccion> producciones) {
        this.producciones = producciones;
    }
}
