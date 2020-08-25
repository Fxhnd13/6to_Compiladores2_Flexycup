/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos.componentes.NodoER;

import analizadores.objetos.componentes.Utilidades;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jose_
 */
public class NodoHoja implements Nodo{

    private char valor;
    private int id;
    private int[] siguientes = new int[0];
    private String tipoToken;
    
    public NodoHoja(NodoHoja nuevo){
        this.valor = nuevo.getValor();
    }
    public NodoHoja(int id, char valor){
        this.id = id;
        this.valor = valor;
    }
    public NodoHoja(char valor) {
        this.valor = valor;
    }
    public NodoHoja() {}

    public char getValor() {
        return valor;
    }

    public void setValor(char valor) {
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int[] getSiguientes() {
        return siguientes;
    }

    public void setSiguientes(int[] siguientes) {
        this.siguientes = siguientes;
    }
    
    @Override
    public int[] primeros() {
        int[] primeros = {id};
        return primeros;
    }

    @Override
    public int[] ultimos() {
        int[] ultimos = {id};
        return ultimos;
    }

    @Override
    public int[] siguientes(int id) {
        if(id == this.id){
            return siguientes;
        }else{
            return null;
        }
    }

    @Override
    public boolean isAnulable() {
        return false;
    }

    @Override
    public void agregarSiguientes(int id, int[] ids) {
        if(siguientes != null){
            ArrayList<Integer> temporal = new ArrayList<Integer>();
            for (int siguiente : siguientes) {
                temporal.add(siguiente);
            }
            for (int i : ids) {
                if(!Utilidades.existe(i, siguientes)) temporal.add(i);
            }
            siguientes = new int[temporal.size()];
            for (int i = 0; i < temporal.size(); i++) {
                siguientes[i] = temporal.get(i);
            }
        }else{
            siguientes = ids;
        }
    }

    public String getTipoToken() {
        return tipoToken;
    }

    public void setTipoToken(String tipoToken) {
        this.tipoToken = tipoToken;
    }
    
}
