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
 *
 * @author jose_
 */
public class Tab extends JScrollPane{
    
    private String nombre, extension="txt";
    private File origen;
    private boolean modificado;
    private JTextArea texto;
    
    public Tab(String nombre, File origen, String extension, String contenido){
        super(new JTextArea());
        this.nombre = nombre;
        this.origen = origen;
        this.extension = extension;
        this.texto = (JTextArea)super.getViewport().getView();
        this.texto.setText(contenido);
    }
    
    public Tab(String nombre){
        super(new JTextArea());
        this.nombre = nombre;
        this.origen = null;
        this.texto = (JTextArea)super.getViewport().getView();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public File getOrigen() {
        return origen;
    }

    public void setOrigen(File origen) {
        this.origen = origen;
    }

    public boolean isModificado() {
        return modificado;
    }

    public void setModificado(boolean modificado) {
        this.modificado = modificado;
    }

    public JTextArea getTexto() {
        return texto;
    }

    public void setTexto(JTextArea texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return "Nombre: "+this.nombre+"\nExtension: "+this.extension+"\nRuta:"+this.origen.getAbsolutePath()+"\nModificado:"+this.modificado;
    }
}
