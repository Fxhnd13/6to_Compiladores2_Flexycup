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
public class NodoAst implements Nodo{

    private Nodo hijo;
    
    /**
     *
     * @param nuevo
     */
    public NodoAst(NodoAst nuevo){
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

    /**
     *
     * @param hijo
     */
    public NodoAst(Nodo hijo){
        this.hijo = hijo;
    }

    /**
     *
     */
    public NodoAst() {}

    /**
     *
     * @return
     */
    public Nodo getHijo() {
        return hijo;
    }

    /**
     *
     * @param hijo
     */
    public void setHijo(Nodo hijo) {
        this.hijo = hijo;
    }
    
    /**
     *
     * @return
     */
    @Override
    public int[] primeros() {
        return hijo.primeros();
    }

    /**
     *
     * @return
     */
    @Override
    public int[] ultimos() {
        return hijo.ultimos();
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public int[] siguientes(int id) {
        return hijo.siguientes(id);
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isAnulable() {
        return true;
    }

    /**
     *
     * @param id
     * @param ids
     */
    @Override
    public void agregarSiguientes(int id, int[] ids) {
        hijo.agregarSiguientes(id, ids);
    }
    
}
