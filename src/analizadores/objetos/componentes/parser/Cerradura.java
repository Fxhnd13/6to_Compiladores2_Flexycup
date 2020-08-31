/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos.componentes.parser;

import analizadores.objetos.componentes.Utilidades;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jose_
 */
public class Cerradura {
 
    private String[] simbolosPreAnalisis;
    private int posicionPunto, produccion;

    public Cerradura(Cerradura cerradura){
        this.posicionPunto = cerradura.getPosicionPunto();
        this.produccion = cerradura.getProduccion();
        this.simbolosPreAnalisis = cerradura.getSimbolosPreAnalisis();
    }
    
    public Cerradura(int produccion, int posicionPunto, String[] simbolosPreAnalisis){
        this.produccion = produccion;
        this.posicionPunto = posicionPunto;
        this.simbolosPreAnalisis = simbolosPreAnalisis;
    }
    
    public int getProduccion() {
        return produccion;
    }

    public void setProduccion(int produccion) {
        this.produccion = produccion;
    }

    public String[] getSimbolosPreAnalisis() {
        return simbolosPreAnalisis;
    }

    public void setSimbolosPreAnalisis(String[] simbolosPreAnalisis) {
        this.simbolosPreAnalisis = simbolosPreAnalisis;
    }

    public int getPosicionPunto() {
        return posicionPunto;
    }

    public void setPosicionPunto(int posicionPunto) {
        this.posicionPunto = posicionPunto;
    }
    
    private void agregarSimbolosPreAnalisis(String[] simbolos){
        if(this.simbolosPreAnalisis != null){
            List<String> temporal = new ArrayList();
            for (String simbolo : this.simbolosPreAnalisis) {
                temporal.add(simbolo);
            }
            for (String simbolo : simbolos) {
                if(!Utilidades.existe(simbolo, temporal)) temporal.add(simbolo);
            }
            this.simbolosPreAnalisis = new String[temporal.size()];
            for (int i = 0; i < temporal.size(); i++) {
                this.simbolosPreAnalisis[i] = temporal.get(i);
            }
        }else{
            this.simbolosPreAnalisis = simbolos;
        }
    }

    @Override
    public String toString() {
        String cadenaSimbolos = "[";
        for (int i = 0; i < simbolosPreAnalisis.length; i++) {
            cadenaSimbolos+=simbolosPreAnalisis[i];
            if((i+1)< simbolosPreAnalisis.length) cadenaSimbolos+=",";
        }
        return "Cerradura{" + "simbolosPreAnalisis=" + cadenaSimbolos + "], posicionPunto=" + posicionPunto + ", produccion=" + produccion + '}';
    }
    
}
