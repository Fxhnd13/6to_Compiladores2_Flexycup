/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos.componentes.lexer;

import analizadores.objetos.componentes.Utilidades;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jose_
 */
public class Estado implements Serializable {
 
    private boolean estadoFinal = false;
    private String tipoToken;
    private int[] idNodosComponentes;
    private List<Transicion> transiciones = new ArrayList<Transicion>();

    Estado(int[] primeros) {
        this.idNodosComponentes = primeros;
    }

    Estado(int idFinal, ArrayList<Integer> composicionRealNuevoEstado, String tipoToken) {
        if(Utilidades.existe(idFinal, composicionRealNuevoEstado)){
            this.estadoFinal = true;
            this.tipoToken = tipoToken;
        }
        this.idNodosComponentes = new int[composicionRealNuevoEstado.size()];
        for (int i = 0; i < composicionRealNuevoEstado.size(); i++) {
            idNodosComponentes[i] = composicionRealNuevoEstado.get(i);
        }
    }
    
    public boolean isEstadoFinal() {
        return estadoFinal;
    }

    public void setEstadoFinal(boolean estadoFinal) {
        this.estadoFinal = estadoFinal;
    }

    public int[] getIdNodosComponentes() {
        return idNodosComponentes;
    }

    public void setIdNodosComponentes(int[] idNodosComponentes) {
        this.idNodosComponentes = idNodosComponentes;
    }

    public String getTipoToken() {
        return tipoToken;
    }

    public void setTipoToken(String tipoToken) {
        this.tipoToken = tipoToken;
    }

    public List<Transicion> getTransiciones() {
        return transiciones;
    }

    public void setTransiciones(List<Transicion> transiciones) {
        this.transiciones = transiciones;
    }
    
    public String toString(){
        String arreglo ="[";
        for (int i = 0; i < idNodosComponentes.length; i++) {
            arreglo+= idNodosComponentes[i];
            if((i+1)< idNodosComponentes.length) arreglo+=",";
        }
        return "Es_Final: "+this.estadoFinal+"|||Id's que componen el estado: "+arreglo+"]";
    }
}
