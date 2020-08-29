/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import analizadores.analizadorFinal.Lenguaje;
import analizadores.estructuraGramatica.LexerGramatica;
import analizadores.estructuraGramatica.ParserGramatica;
import analizadores.objetos.ErrorAnalisis;
import analizadores.objetos.componentes.lexer.Token;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.MenuElement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jose_
 */
public class EditorManager {
    
    List<Lenguaje> lenguajes;
    
    public EditorManager(){
        this.lenguajes = new ArrayList();
    }
    
    public void nuevoTab(JTabbedPane panel){
        String nombre = JOptionPane.showInputDialog(null, "¿Qué nombre desea tenga su nueva pestaña?", "Informacion", JOptionPane.QUESTION_MESSAGE);
        if(nombre != null && !nombre.isEmpty()){
            Tab tab = new Tab(nombre);
            panel.add(tab, nombre);
            panel.setSelectedIndex(panel.getComponentCount()-1);   
        }
    }
    
    public void cargarTab(JTabbedPane panel){
        File file = ArchivosManager.cargarArchivo();
        if(file == null || file.getName().equals("")){
            JOptionPane.showMessageDialog(null, "No se encontro el archivo seleccionado o no selecciono un arhivo.", "Error", JOptionPane.ERROR_MESSAGE);
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
                JOptionPane.showMessageDialog(null, "No se encontro el archivo seleccionado o no selecciono un arhivo.", "Error", JOptionPane.ERROR_MESSAGE);
            }catch(IOException ex){
                JOptionPane.showMessageDialog(null, "Ocurrio un error EditorManager/cargarTab", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    void cerrarTab(JTabbedPane tabs) {
        Tab tab = (Tab) tabs.getSelectedComponent();
        if(tab != null){
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
        }else{
            JOptionPane.showMessageDialog(null, "Debe haber al menos una pestaña activa para poder realizar esta accion.", "Error", JOptionPane.ERROR_MESSAGE);
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
    
    public boolean parsearSecciones(String texto, JTable TablaTokens, JTable TablaErrores) {
        boolean valor = false;
        try {
            LexerGramatica lexer = new LexerGramatica(new StringReader(texto));
            lexer.setTablaTokens(TablaTokens);
            ParserGramatica parser = new ParserGramatica(lexer);
            parser.parse();
            parser.getGeneradorParser().verificarIntegridad(parser.getErrores(), parser.getTablaDeER(), parser.getTablaDeSimbolosGramaticales());
            if(parser.getErrores().isEmpty()){
                parser.getGeneradorAutomata().calcularArbol();
                parser.getGeneradorAutomata().crearEstadosAutomata();
                Lenguaje lenguaje = new Lenguaje(parser.getInformacion(), parser.getGeneradorAutomata().getAutomata(), null);
                ArchivosManager.guardarLenguaje(lenguaje);
            }else{
                DefaultTableModel modelo = (DefaultTableModel) TablaErrores.getModel();
                for (ErrorAnalisis error : parser.getErrores()) {
                    modelo.addRow(new String[]{error.getTipo(), error.getValor(), error.getDescripcion(), String.valueOf(error.getLinea()), String.valueOf(error.getColumna())});
                }
                TablaErrores.setModel(modelo);
            }
            valor = parser.getErrores().isEmpty();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error EditorManager/ParsearSeccion", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return valor;
    }

    void compilarTexto(JMenu menuLenguajes, Tab tab) {
        String nombreLenguaje = null;
        for (Component menuComponent : menuLenguajes.getMenuComponents()) {
            if(((JRadioButtonMenuItem)menuComponent).isSelected()) nombreLenguaje = ((JRadioButtonMenuItem)menuComponent).getName();
        }
        if(nombreLenguaje != null){
            Lenguaje lenguaje = ArchivosManager.cargarLenguaje(nombreLenguaje);
            if(tab.getExtension().equals(lenguaje.getInformacion().getExtension())){
                lenguaje.getLexer().getAutomata().setCadena(tab.getTexto().getText());
                lenguaje.getLexer().getAutomata().analizar();
                for (Token token : lenguaje.getLexer().getAutomata().getTokens()) {
                    System.out.println(token.toString());
                }
                for (String error : lenguaje.getLexer().getAutomata().getErrores()) {
                    System.out.println(error);
                }
            }else{
                JOptionPane.showMessageDialog(null, "El texto a analizar, no pertenece al tipo de extension especificado durante la creacion del lenguaje.","Error",JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningun lenguaje del menú 'Lenguajes'.","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
}
