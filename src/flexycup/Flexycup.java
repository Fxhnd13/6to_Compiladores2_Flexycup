/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flexycup;

import GUI.ArchivosManager;
import GUI.Editor;
import analizadores.analizadorFinal.Lenguaje;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButtonMenuItem;

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
        List<String> lenguajes =  ArchivosManager.cargarNombresLenguajes();
        ButtonGroup grupo = new ButtonGroup();
        for (int i = 0; i < lenguajes.size(); i++){
            JRadioButtonMenuItem item = new JRadioButtonMenuItem(lenguajes.get(i));
            if(i==0) item.setSelected(true);
            item.setName(lenguajes.get(i));
            grupo.add(item);
            editor.getMenuLenguajes().add(item);
        }
        editor.setVisible(true);
    }
    
}
