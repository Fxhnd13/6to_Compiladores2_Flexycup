/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import analizadores.analizadorFinal.Lenguaje;
import analizadores.analizadorFinal.Registro;
import analizadores.estructuraGramatica.LexerGramatica;
import analizadores.estructuraGramatica.ParserGramatica;
import analizadores.objetos.ErrorAnalisis;
import analizadores.objetos.Variable;
import analizadores.objetos.componentes.Utilidades;
import analizadores.objetos.componentes.lexer.Token;
import analizadores.objetos.componentes.parser.Simbolo;
import java.awt.Component;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * manejador del editor, hace todas las funcionalidades que el editor debe realizar
 * @author jose_
 */
public class EditorManager {
    
    private String textoCompilado = null;
    
    /**
     *
     */
    public EditorManager(){
    }
    
    /**
     * agrega un nuevo tab, al editor
     * @param panel panel al que agregaremos el nuevo tab
     */
    public void nuevoTab(JTabbedPane panel){
        String nombre = JOptionPane.showInputDialog(null, "¿Qué nombre desea tenga su nueva pestaña?", "Informacion", JOptionPane.QUESTION_MESSAGE);
        if(nombre != null && !nombre.isEmpty()){
            Tab tab = new Tab(nombre);
            panel.add(tab, nombre);
            panel.setSelectedIndex(panel.getComponentCount()-1);   
        }
    }
    
    /**
     * agrega un nuevo tab al editor a partir de cargar un archivo
     * @param panel panel al que agregaremos el nuevo tab
     */
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
        if(tabs.getSelectedComponent() != null){
            Tab tab = (Tab) tabs.getSelectedComponent();
            if(tab.getOrigen() != null){
                ArchivosManager.guardarArchivo(tab, tab.getOrigen());
            }else{
                ArchivosManager.guardarComo(tab, true);
            }
        }else{
            JOptionPane.showMessageDialog(null, "No se tiene ninguna pestaña activa", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Analisa lexica y sintacticamente la entrada, para poder generar un lenguaje
     * @param texto texto a analizar
     * @param TablaTokens listado de los tokens detectados por el analizador lexico
     * @param TablaErrores tabla de errores (si los hay) durante el analalisis
     * @return valor, si es que se encontraron o no errores
     */
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
                parser.getGeneradorParser().generarEstados(parser.getErrores());
                if(parser.getErrores().isEmpty()){
                    valor = true;
                    parser.getGeneradorParser().getAutomata().setProducciones(parser.getGeneradorParser().getProducciones());
                    parser.getGeneradorParser().getAutomata().setSimbolos(parser.getGeneradorParser().getSimbolos());
                    Lenguaje lenguaje = new Lenguaje(parser.getInformacion(), parser.getGeneradorAutomata().getAutomata(), parser.getGeneradorParser().getAutomata());
                    ArchivosManager.guardarLenguaje(lenguaje, true);
                }else{
                    DefaultTableModel modelo = (DefaultTableModel) TablaErrores.getModel();
                    for (ErrorAnalisis error : parser.getErrores()) {
                        modelo.addRow(new String[]{error.getTipo(), error.getValor(), error.getDescripcion(), String.valueOf(error.getLinea()), String.valueOf(error.getColumna())});
                    }
                    TablaErrores.setModel(modelo);
                }
            }else{
                DefaultTableModel modelo = (DefaultTableModel) TablaErrores.getModel();
                for (ErrorAnalisis error : parser.getErrores()) {
                    modelo.addRow(new String[]{error.getTipo(), error.getValor(), error.getDescripcion(), String.valueOf(error.getLinea()), String.valueOf(error.getColumna())});
                }
                TablaErrores.setModel(modelo);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error EditorManager/ParsearSeccion", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return valor;
    }

    /**
     * carga el lenguaje seleccionado por el usuario, para posteriormente utilizarlo para analizar la cadena de entrada
     * @param menuLenguajes lenguaje seleccionado
     * @param tab texto a analizar
     * @param TablaErrores tabla en la que registraremos todos los errores
     */
    public void compilarTexto(JMenu menuLenguajes, Tab tab, JTable TablaErrores) {
        String nombreLenguaje = null;
        for (Component menuComponent : menuLenguajes.getMenuComponents()) {
            if(((JRadioButtonMenuItem)menuComponent).isSelected()) nombreLenguaje = ((JRadioButtonMenuItem)menuComponent).getName();
        }
        if(nombreLenguaje != null){
            Lenguaje lenguaje = ArchivosManager.cargarLenguaje(nombreLenguaje);
            boolean evaluar = false;
            if(lenguaje.getInformacion().getExtension() == null){
                evaluar = true;
            }else if(tab.getExtension() == null){
                evaluar = true;
            }else if(tab.getExtension().equals(lenguaje.getInformacion().getExtension())) evaluar = true;
            if(evaluar){
                lenguaje.getLexer().getAutomata().setCadena(tab.getTexto().getText());
                lenguaje.getLexer().getAutomata().analizar();
                lenguaje.getParser().setErrores(lenguaje.getLexer().getAutomata().getErrores());
                lenguaje.getParser().setTokens(lenguaje.getLexer().getAutomata().getTokens());
                lenguaje.getParser().analizar();
                if(lenguaje.getParser().getErrores().isEmpty()){
                    ArchivosManager.guardarLenguaje(lenguaje, false);
                }else{
                    DefaultTableModel modelo = (DefaultTableModel) TablaErrores.getModel();
                    for (ErrorAnalisis error : lenguaje.getParser().getErrores()) {
                        modelo.addRow(new String[]{error.getTipo(),error.getValor(),error.getDescripcion(),String.valueOf(error.getLinea()),String.valueOf(error.getColumna())});
                    }
                }
            }else{
                JOptionPane.showMessageDialog(null, "El texto a analizar, no pertenece al tipo de extension especificado durante la creacion del lenguaje "+lenguaje.getNombre()+".","Error",JOptionPane.ERROR_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningun lenguaje del menú 'Lenguajes'.","Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * genera la tabla de analisis sintactico del lenguaje seleccionado en el editor
     * @param menuLenguajes lenguaje seleccionado
     * @param TablaDeAnalisisSintactico tabla en la que se anotarán todas las acciones
     */
    public void crearTablaDeAnalisisSintactico(JMenu menuLenguajes, JTable TablaDeAnalisisSintactico) {
        String nombreLenguaje = null;
        for (Component menuComponent : menuLenguajes.getMenuComponents()) {
            if(((JRadioButtonMenuItem)menuComponent).isSelected()) nombreLenguaje = ((JRadioButtonMenuItem)menuComponent).getName();
        }
        if(nombreLenguaje != null){
            Lenguaje lenguaje = ArchivosManager.cargarLenguaje(nombreLenguaje);
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("Estado");
            for(Variable variable : lenguaje.getParser().getAutomata().getSimbolos().getVariables()) {
                Simbolo simbolo = (Simbolo) variable.getValor();
                if(simbolo.isTerminal()) modelo.addColumn(simbolo.getSimbolo());
            }
            for(Variable variable : lenguaje.getParser().getAutomata().getSimbolos().getVariables()) {
                Simbolo simbolo = (Simbolo) variable.getValor();
                if(!simbolo.isTerminal() && !simbolo.getSimbolo().equals("InicioCadena")) modelo.addColumn(simbolo.getSimbolo());
            }
            for (int i = 0; i < lenguaje.getParser().getAutomata().getEstados().size(); i++) {
                modelo.setRowCount(modelo.getRowCount()+1);
                modelo.setValueAt(i, i, 0);
                for (int j = 0; j < modelo.getColumnCount(); j++) {
                    String valor = Utilidades.obtenerAccionDe(modelo.getColumnName(j), lenguaje.getParser().getAutomata().getEstados().get(i).getAcciones());
                    if(valor != null){
                        modelo.setValueAt(valor, i, j);
                    }
                }
            }
            TablaDeAnalisisSintactico.setModel(modelo);
        }else{
            JOptionPane.showMessageDialog(null, "No hay lenguajes en el repositorio del programa.","Error",JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * muestra al usuario un mensaje con la informacion del lenguaje seleccionado
     * @param menuLenguajes lenguaje seleccionado
     */
    public void mostrarInformacionDelLenguaje(JMenu menuLenguajes) {
        String nombreLenguaje = null;
        for (Component menuComponent : menuLenguajes.getMenuComponents()) {
            if(((JRadioButtonMenuItem)menuComponent).isSelected()) nombreLenguaje = ((JRadioButtonMenuItem)menuComponent).getName();
        }
        if(nombreLenguaje != null){
            Lenguaje lenguaje = ArchivosManager.cargarLenguaje(nombreLenguaje);
            JOptionPane.showMessageDialog(null, lenguaje.getInformacion().toString(), "Informacion del lenguaje", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * crea la tabla donde se muestra en pantalla el proceso que el analizador sintactico del lenguaje seleccionado, las acciones y los pasos 
     * @param menuLenguajes lenguaje seleccionado
     * @param TablaDeAnalisisSintactico tabla en la que se iran añadiendo todos los registros realizados
     */
    public void crearTablaDeAnalisisCadena(JMenu menuLenguajes, JTable TablaDeAnalisisSintactico) {
        String nombreLenguaje = null;
        for (Component menuComponent : menuLenguajes.getMenuComponents()) {
            if(((JRadioButtonMenuItem)menuComponent).isSelected()) nombreLenguaje = ((JRadioButtonMenuItem)menuComponent).getName();
        }
        if(nombreLenguaje != null){
            Lenguaje lenguaje = ArchivosManager.cargarLenguaje(nombreLenguaje);
            DefaultTableModel modelo = new DefaultTableModel();
            modelo.addColumn("Pila de Estados");
            modelo.addColumn("Pila de simbolos");
            modelo.addColumn("Tokens de entrada");
            modelo.addColumn("Accion realizada");
            TablaDeAnalisisSintactico.setModel(modelo);
            for (Registro registro : lenguaje.getParser().getHistorial()) {
                modelo.addRow(new String[]{registro.getPila(),registro.getSimbolos(),registro.getEntrada(), registro.getAccion()});
            }
            TablaDeAnalisisSintactico.setModel(modelo);
        }else{
            JOptionPane.showMessageDialog(null, "No hay lenguajes en el repositorio del programa.","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
}
