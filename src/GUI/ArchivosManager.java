/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import analizadores.analizadorFinal.Lenguaje;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public static void guardarComo(Tab tab, boolean cambiar){
        JFileChooser filechooser = new JFileChooser();
        filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int resultado = filechooser.showOpenDialog(null);
        if(resultado == JFileChooser.APPROVE_OPTION){
            File temp = filechooser.getSelectedFile();
            if(temp != null){
                String nombre = JOptionPane.showInputDialog("Por favor ingrese el nombre del archivo:\nNOTA: No olvide revisar la extension.", tab.getNombre()+"."+tab.getExtension());
                if(nombre == null || nombre.isEmpty() || !nombre.contains(".")){
                    JOptionPane.showMessageDialog(null, "No se ingreso un nombre valido para el archivo, no se guardaron los cambios.", "Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    File file = new File(temp+"/"+nombre);
                    if(cambiar) tab.setOrigen(file);
                    guardarArchivo(tab, file);
                }
            }
        }
    }
    
    public static Lenguaje cargarLenguaje(String nombre){
        Lenguaje lenguaje = null;
        
        File file = new File("Repositorio_Lenguajes/");
        if(!file.mkdir()){
            file = new File("Repositorio_Lenguajes/"+nombre+".lngs");
            if(file.exists()){
                try {
                    ObjectInputStream lectorObjeto = new ObjectInputStream(new FileInputStream(file));
                    lenguaje = (Lenguaje) lectorObjeto.readObject();
                } catch (FileNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "No se encontro ningun archivo de lenguajes.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error en ArchivosManager/cargarLenguajes.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error en ArchivosManager/cargarLenguajes al castear el objeto.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
            }else{
                JOptionPane.showMessageDialog(null, "No se encontro el lenguaje seleccionado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        
        return lenguaje;
    }
    
    public static void guardarLenguaje(Lenguaje lenguaje){
        File file = new File("Repositorio_Lenguajes/");
        if(!file.mkdir()){
            file = new File("Repositorio_Lenguajes/"+lenguaje.getNombre()+".lngs");
            try {
                ObjectOutputStream lectorObjeto = new ObjectOutputStream(new FileOutputStream(file));
                lectorObjeto.writeObject(lenguaje);
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Ha ocurrido un error no se encontro la ruta para guardar el archivo.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Ha ocurrido un error en ArchivosManager/guardarLenguajes.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static List<String> cargarNombresLenguajes() {
        List<String> lenguajes = new ArrayList();
        
        File file = new File("Repositorio_Lenguajes/");
        if(!file.mkdir() && file.list().length > 0){
            for (File archivo : file.listFiles()) {
                lenguajes.add(archivo.getName().substring(0, archivo.getName().length()-5));
            }
        }else{
            JOptionPane.showMessageDialog(null, "No se encontraron lenguajes guardados. ", "Informacion", JOptionPane.INFORMATION_MESSAGE);
        }
        
        return lenguajes;
    }

    static void eliminarLenguaje(String nombre) {
        File file = new File("Repositorio_Lenguajes/");
        if(!file.mkdir()){
            file = new File("Repositorio_Lenguajes/"+nombre+".lngs");
            if(file.exists()){
                if(!file.delete()){
                    JOptionPane.showMessageDialog(null, "No se logró eliminar el lenguaje seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(null, "No se encontro el lenguaje seleccionado", "Informacion", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
}
