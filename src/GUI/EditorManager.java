/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;

/**
 *
 * @author jose_
 */
public class EditorManager {
    
    public void nuevoTab(JTabbedPane panel){
        String nombre = JOptionPane.showInputDialog(null, "¿Qué nombre desea tenga su nueva pestaña?", "Informacion", JOptionPane.QUESTION_MESSAGE);
        if(!nombre.isEmpty()){
            Tab tab = new Tab(nombre);
            panel.add(tab, nombre);
            panel.setSelectedIndex(panel.getComponentCount()-1);   
        }
    }
    
    public void cargarTab(JTabbedPane panel){
        File file = ArchivosManager.cargarArchivo();
        if(file == null || file.getName().equals("")){
            //no seleccionó un archivo
        }else{
            try{
                String cadena, contenido = "";
                FileReader f = new FileReader(file);
                BufferedReader b = new BufferedReader(f);
                while((cadena = b.readLine())!= null){
                    contenido += cadena+"\n";
                }
                contenido = contenido.substring(0,contenido.length()-1);
                int indice = file.getName().lastIndexOf(".");
                Tab tab = new Tab(file.getName().substring(0, indice), file,file.getName().substring(indice+1, file.getName().length()), contenido);
                panel.add(tab, tab.getNombre());
                panel.setSelectedIndex(panel.getComponentCount()-1);
            }catch(FileNotFoundException ex){
                ex.printStackTrace();
            }catch(IOException ex){
                ex.printStackTrace();
            }
        }
        for (Component component : panel.getComponents()) {
            Tab tab = (Tab) component;
            System.out.println(tab.toString());
            System.out.println("------------------------------------------------");
        }
    }

    void cerrarTab(JTabbedPane tabs) {
        Tab tab = (Tab) tabs.getSelectedComponent();
        if(tab.isModificado()){
            int resultado = JOptionPane.showConfirmDialog(null, "¿Este archivo fue 'modificado', desea guardar los cambios antes de cerrarlo?", "Cerrar Archivo", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.WARNING_MESSAGE);
            switch(resultado){
                case 0:{//si
                    if(tab.getOrigen() != null){
                        ArchivosManager.guardarArchivo(tab);
                    }else{
                        ArchivosManager.guardarComo(tab);
                    }
                    break;
                }
                case 1:{//no
                    tabs.remove(tabs.getSelectedIndex());
                    break;
                }
            }
        }else{
            tabs.remove(tabs.getSelectedIndex());
        }
    }

    void guardarTab(JTabbedPane tabs) {
        Tab tab = (Tab) tabs.getSelectedComponent();
        if(tab.getOrigen() != null){
            ArchivosManager.guardarArchivo(tab);
        }else{
            ArchivosManager.guardarComo(tab);
        }
    }
}
