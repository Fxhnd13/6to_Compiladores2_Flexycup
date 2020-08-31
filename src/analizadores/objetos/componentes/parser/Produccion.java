/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos.componentes.parser;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jose_
 */
public class Produccion {

    private int id;
    private Simbolo simboloIzquierda;
    private List<Simbolo> simbolosDerecha;

    public Produccion(Simbolo simbolo, ArrayList<Simbolo> arrayList) {
        this.simboloIzquierda = simbolo;
        this.simbolosDerecha = arrayList;
        if(this.simbolosDerecha.isEmpty()){
            this.simboloIzquierda.setLambda(true);
        }
    }
    
    public Simbolo getSimboloIzquierda() {
        return simboloIzquierda;
    }

    public void setSimboloIzquierda(Simbolo simboloIzquierda) {
        this.simboloIzquierda = simboloIzquierda;
    }

    public List<Simbolo> getSimbolosDerecha() {
        return simbolosDerecha;
    }

    public void setSimbolosDerecha(List<Simbolo> simbolosDerecha) {
        this.simbolosDerecha = simbolosDerecha;
    }

    public int getId() {
        return id;
    }

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
