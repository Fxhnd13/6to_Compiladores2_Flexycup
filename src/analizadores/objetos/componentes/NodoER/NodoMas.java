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
public class NodoMas implements Nodo{

    private Nodo hijo;
    
    public NodoMas() {}
    public NodoMas(NodoMas nuevo){
        if(nuevo.getHijo() instanceof NodoConcat){
            this.hijo = new NodoConcat((NodoConcat) nuevo.getHijo());
        }else if(nuevo.getHijo() instanceof NodoDis){
            this.hijo = new NodoDis((NodoDis) nuevo.getHijo());
        }else if(nuevo.getHijo() instanceof NodoMas){
            this.hijo = new NodoMas((NodoMas) nuevo.getHijo());
        }else if(nuevo.getHijo() instanceof NodoQuiza){
            this.hijo = new NodoQuiza((NodoQuiza)nuevo.getHijo());
        }else if(nuevo.getHijo() instanceof NodoHoja){
            this.hijo = new NodoHoja((NodoHoja) nuevo.getHijo());
        }    
    }
    public NodoMas(Nodo hijo){
        this.hijo = hijo;
    }

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
        return hijo.isAnulable();
    }

    @Override
    public void agregarSiguientes(int id, int[] ids) {
        hijo.agregarSiguientes(id, ids);
    }
    
}
