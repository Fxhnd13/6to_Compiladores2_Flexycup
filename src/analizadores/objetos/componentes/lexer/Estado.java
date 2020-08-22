/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos.componentes.lexer;

/**
 *
 * @author jose_
 */
public class Estado {
 
    boolean estadoFinal = false;
    int[] idNodosComponentes;
    
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
    
}
