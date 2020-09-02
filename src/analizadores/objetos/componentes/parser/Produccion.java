/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos.componentes.parser;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jose_
 */
public class Produccion implements Serializable{

    private int id;
    private Simbolo simboloIzquierda;
    private List<Simbolo> simbolosDerecha;

    /**
     *
     * @param simbolo
     * @param arrayList
     */
    public Produccion(Simbolo simbolo, ArrayList<Simbolo> arrayList) {
        this.simboloIzquierda = simbolo;
        this.simbolosDerecha = arrayList;
        if(this.simbolosDerecha.isEmpty()){
            this.simboloIzquierda.setLambda(true);
        }
    }
    
    /**
     *
     * @return
     */
    public Simbolo getSimboloIzquierda() {
        return simboloIzquierda;
    }

    /**
     *
     * @param simboloIzquierda
     */
    public void setSimboloIzquierda(Simbolo simboloIzquierda) {
        this.simboloIzquierda = simboloIzquierda;
    }

    /**
     *
     * @return
     */
    public List<Simbolo> getSimbolosDerecha() {
        return simbolosDerecha;
    }

    /**
     *
     * @param simbolosDerecha
     */
    public void setSimbolosDerecha(List<Simbolo> simbolosDerecha) {
        this.simbolosDerecha = simbolosDerecha;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }
    
    @Override
    public String toString() {
        String simbolosDerechaCadena = "{";
        for (Simbolo simbolo : simbolosDerecha) {
            simbolosDerechaCadena+=simbolo.toString();
        }
        return "Produccion{" + "simboloIzquierda{" + simboloIzquierda.toString() + "},\n simbolosDerecha=" + simbolosDerechaCadena + "}\n}";
    }
    
}
