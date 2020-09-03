/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flexycup;

import GUI.Editor;

/**
 *
 * @author jose_
 */
public class Flexycup {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Editor editor = new Editor();
        editor.cargarLenguajes();
        editor.setVisible(true);
    }
    
}
