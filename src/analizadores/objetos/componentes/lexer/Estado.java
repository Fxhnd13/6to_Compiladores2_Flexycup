/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos.componentes.lexer;

import analizadores.objetos.componentes.Utilidades;
import java.util.ArrayList;

/**
 *
 * @author jose_
 */
public class Estado {
 
    boolean estadoFinal = false;
    int[] idNodosComponentes;

    Estado(int[] primeros) {
        this.idNodosComponentes = primeros;
    }

    Estado(ArrayList<Integer> composicionRealNuevoEstado) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    Estado(int idFinal, ArrayList<Integer> composicionRealNuevoEstado) {
        if(Utilidades.existe(idFinal, composicionRealNuevoEstado)) this.estadoFinal = true;
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
    
    public String toString(){
        return "Es_Final: "+this.estadoFinal+"|||Id's que componen el estado: "+this.idNodosComponentes.toString();
    }
}
