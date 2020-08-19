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
    
    public int[] primeros();
    
    public int[] ultimos();
    
    public int[] siguientes(int id);
    
    public void agregarSiguientes(int id, int[] ids);
    
    public boolean isAnulable();
    
}
