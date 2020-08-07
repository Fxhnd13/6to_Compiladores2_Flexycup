/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author jose_
 */
public class ArchivosManager {
    
    public static File cargarArchivo(){
        File file = null;
        
        JFileChooser filechooser = new JFileChooser();
        int resultado = filechooser.showOpenDialog(null);
        if(resultado == JFileChooser.APPROVE_OPTION){
            file = filechooser.getSelectedFile();
        }
        
        return file;
    }
    
    public static void guardarArchivo(Tab tab, File origen){
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(origen);
            pw = new PrintWriter(fichero);

            pw.print(tab.getTexto().getText());
            tab.setModificado(false);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }
    }
    
    public static void guardarComo(Tab tab){
        JFileChooser filechooser = new JFileChooser();
        filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int resultado = filechooser.showOpenDialog(null);
        if(resultado == JFileChooser.APPROVE_OPTION){
            File temp = filechooser.getSelectedFile();
            if(temp != null){
                String extension = JOptionPane.showInputDialog("Por favor ingrese la extension del archivo:");
                if(extension.isEmpty() || extension == null){
                    JOptionPane.showMessageDialog(null, "No se ingreso una extension para el archivo, no se guardaron los cambios.", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    File file = new File(temp+"/"+tab.getNombre()+"."+extension);
                    guardarArchivo(tab, file);
                }
            }
        }
    }
}
