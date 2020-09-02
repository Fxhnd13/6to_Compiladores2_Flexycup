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
public interface Nodo {
    
    /**
     *
     * @return
     */
    public int[] primeros();
    
    /**
     *
     * @return
     */
    public int[] ultimos();
    
    /**
     *
     * @param id
     * @return
     */
    public int[] siguientes(int id);
    
    /**
     *
     * @param id
     * @param ids
     */
    public void agregarSiguientes(int id, int[] ids);
    
    /**
     *
     * @return
     */
    public boolean isAnulable();
}
