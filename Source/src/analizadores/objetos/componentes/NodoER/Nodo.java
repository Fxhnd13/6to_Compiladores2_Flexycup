/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos.componentes.NodoER;

/**
 * Interfaz a implementar en todas las clases que conformen la estructura de una expresion regular
 * @author jose_
 */
public interface Nodo {
    
    /**
     * 
     * @return primeros de la expresion regular
     */
    public int[] primeros();
    
    /**
     * 
     * @return ultimos de la expresion regular
     */
    public int[] ultimos();
    
    /**
     * recorre el arbol en busca de los siguientes del nodo hoja con el id correspondiente
     * @param id del nodo a buscar
     * @return siguientes
     */
    public int[] siguientes(int id);
    
    /**
     * recorre el arbol en busca del id del nodo hoja y agrega los siguientes enviados
     * @param id del nodo a buscar
     * @param ids a agregar
     */
    public void agregarSiguientes(int id, int[] ids);
    
    /**
     *
     * @return si el nodo es anulable o no
     */
    public boolean isAnulable();
}
