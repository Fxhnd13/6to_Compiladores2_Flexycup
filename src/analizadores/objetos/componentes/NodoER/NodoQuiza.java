/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos.componentes.NodoER;

/**
 *
 * @author jose_
 */
public class NodoQuiza implements Nodo{

    private Nodo hijo;
    
    public NodoQuiza(Nodo hijo){
        this.hijo = hijo;
    }

    public NodoQuiza() {}

    public Nodo getHijo() {
        return hijo;
    }

    public void setHijo(Nodo hijo) {
        this.hijo = hijo;
    }
    
    @Override
    public int[] primeros() {
        return hijo.primeros();
    }

    @Override
    public int[] ultimos() {
        return hijo.ultimos();
    }

    @Override
    public int[] siguientes(int id) {
        return hijo.siguientes(id);
    }

    @Override
    public boolean isAnulable() {
        return true;
    }

    @Override
    public void agregarSiguientes(int id, int[] ids) {
        hijo.agregarSiguientes(id, ids);
    }
    
}
