/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import analizadores.estructuraGramatica.secciones.LexerSecciones;
import analizadores.estructuraGramatica.secciones.ParserSecciones;
import analizadores.estructuraGramatica.LexerGramatica;
import analizadores.estructuraGramatica.ParserGramatica;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
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
                        ArchivosManager.guardarArchivo(tab, tab.getOrigen());
                    }else{
                        ArchivosManager.guardarComo(tab, false);
                    }
                    tabs.remove(tabs.getSelectedIndex());
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
            ArchivosManager.guardarArchivo(tab, tab.getOrigen());
        }else{
            ArchivosManager.guardarComo(tab, true);
        }
    }

    void parsearSecciones(String texto){
        try {
            LexerSecciones lexer = new LexerSecciones(new StringReader(texto));
            ParserSecciones parser = new ParserSecciones(lexer);
            List<String> secciones = (List<String>) parser.parse().value;
//            for (int i = 0; i < secciones.size(); i++) {
//                switch(i){
//                    case 0:{
//                        System.out.println("---------------------------------------------------------------------------------------");
//                        System.out.println("Seccion de informacion:   ");
//                        System.out.println(secciones.get(0));
//                        break;
//                    }
//                    case 1:{
//                        System.out.println("---------------------------------------------------------------------------------------");
//                        System.out.println("Seccion de Codigo:   ");
//                        System.out.println(secciones.get(1));
//                        break;
//                    }
//                    case 2:{
//                        System.out.println("---------------------------------------------------------------------------------------");
//                        System.out.println("Seccion de Expresiones Regulares:   ");
//                        System.out.println(secciones.get(2));
//                        break;
//                    }
//                    case 3:{
//                        System.out.println("---------------------------------------------------------------------------------------");
//                        System.out.println("Seccion de Simbolos:   ");
//                        System.out.println(secciones.get(3));
//                        break;
//                    }
//                    case 4:{
//                        System.out.println("---------------------------------------------------------------------------------------");
//                        System.out.println("Seccion de reglas gramaticales:   ");
//                        System.out.println(secciones.get(4));
//                        break;
//                    }
//                }
//            }
            String cadena = "";
            for (int i = 0; i < secciones.size(); i++) {
                if(i!=1) {
                    if(i>1 && i<secciones.size()) cadena+="%%";
                    cadena+=secciones.get(i);
                }
            }
//            System.out.println(cadena);
            LexerGramatica lexer2 = new LexerGramatica(new StringReader(cadena));
            ParserGramatica parser2 = new ParserGramatica(lexer2);
            parser2.debug_parse();
        } catch (Exception ex) {
            Logger.getLogger(EditorManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
