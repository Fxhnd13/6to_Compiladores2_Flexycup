/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.File;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Clase que permite añadir pestañas
 * @author jose_
 */
public class Tab extends JScrollPane{
    
    private String nombre, extension="txt";
    private File origen;
    private boolean modificado;
    private JTextArea texto;
    private NumeroLinea numeroLinea;
    
    /**
     * Constructor
     * @param nombre nombre que tendrá la pestaña
     * @param origen archivo de origen (si lo tiene)
     * @param extension extension del archivo de origen (si lo tiene)
     * @param contenido texto cargado
     */
    public Tab(String nombre, File origen, String extension, String contenido){
        super(new JTextArea());
        this.nombre = nombre;
        this.origen = origen;
        this.extension = extension;
        this.texto = (JTextArea)super.getViewport().getView();
        this.texto.setText(contenido);
        numeroLinea = new NumeroLinea(this.texto);
        this.setRowHeaderView(numeroLinea);
    }
    
    /**
     * constructor simple
     * @param nombre de la pestaña
     */
    public Tab(String nombre){
        super(new JTextArea());
        this.nombre = nombre;
        this.origen = null;
        this.texto = (JTextArea)super.getViewport().getView();
        numeroLinea = new NumeroLinea(this.texto);
        this.setRowHeaderView(numeroLinea);
    }

    /**
     *
     * @return el nombre de la pestaña
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * cambia el nombre de la pestaña
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @return la extension de la pestaña
     */
    public String getExtension() {
        return extension;
    }

    /**
     * cambia la extension de la pestaña
     * @param extension
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }

    /**
     *
     * @return el archivo de origen de la pestaña
     */
    public File getOrigen() {
        return origen;
    }

    /**
     * cambia el archivo de origen de la pestaña
     * @param origen
     */
    public void setOrigen(File origen) {
        this.origen = origen;
    }

    /**
     *
     * @return si la pestaña fue modificada
     */
    public boolean isModificado() {
        return modificado;
    }

    /**
     * cambia el estado de la pestaña, si fue modificada
     * @param modificado
     */
    public void setModificado(boolean modificado) {
        this.modificado = modificado;
    }

    /**
     * 
     * @return el texto que tiene la pestaña
     */
    public JTextArea getTexto() {
        return texto;
    }

    /**
     * cambia el texto que tienen la pestaña
     * @param texto
     */
    public void setTexto(JTextArea texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return "Nombre: "+this.nombre+"\nExtension: "+this.extension+"\nRuta:"+this.origen.getAbsolutePath()+"\nModificado:"+this.modificado;
    }
}
