/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos.componentes.parser;

import analizadores.objetos.TablaDeSimbolos;
import analizadores.objetos.componentes.lexer.Token;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jose_
 */
public class AutomataParser implements Serializable{
    
    private List<Estado> estados;
    private TablaDeSimbolos simbolos;
    private List<Produccion> producciones;

    /**
     *
     */
    public AutomataParser(){
        this.estados = new ArrayList();
    }

    /**
     *
     * @return
     */
    public List<Estado> getEstados() {
        return estados;
    }

    /**
     *
     * @param estados
     */
    public void setEstados(List<Estado> estados) {
        this.estados = estados;
    }

    /**
     *
     * @return
     */
    public TablaDeSimbolos getSimbolos() {
        return simbolos;
    }

    /**
     *
     * @param simbolos
     */
    public void setSimbolos(TablaDeSimbolos simbolos) {
        this.simbolos = simbolos;
    }

    /**
     *
     * @return
     */
    public List<Produccion> getProducciones() {
        return producciones;
    }

    /**
     *
     * @param producciones
     */
    public void setProducciones(List<Produccion> producciones) {
        this.producciones = producciones;
    }
}
