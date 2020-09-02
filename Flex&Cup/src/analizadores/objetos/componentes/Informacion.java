/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos.componentes;

import analizadores.objetos.ErrorAnalisis;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author jose_
 */
public class Informacion implements Serializable {
 
    private String nombre, autor, version, lanzamiento, extension;

    /**
     *
     * @return
     */
    public String getNombre() {
        return nombre;
    }

    /**
     *
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     *
     * @return
     */
    public String getAutor() {
        return autor;
    }

    /**
     *
     * @param autor
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }

    /**
     *
     * @return
     */
    public String getVersion() {
        return version;
    }

    /**
     *
     * @param version
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     *
     * @return
     */
    public String getLanzamiento() {
        return lanzamiento;
    }

    /**
     *
     * @param lanzamiento
     */
    public void setLanzamiento(String lanzamiento) {
        this.lanzamiento = lanzamiento;
    }

    /**
     *
     * @return
     */
    public String getExtension() {
        return extension;
    }

    /**
     *
     * @param extension
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }
    
    /**
     *
     * @param dato
     * @param linea
     * @param columna
     * @param errores
     */
    public void agregarDato(Dato dato, int linea, int columna, List<ErrorAnalisis> errores){
        switch(dato.getTipo()){
            case "Nombre":{
                if(this.nombre != null){
                    errores.add(new ErrorAnalisis("Semantico","<sin cadena>","Ya se declaro anteriormente un valor para el nombre que tendra el lenguaje.",linea,columna));
                }else{
                    this.nombre = dato.getValor();
                }
                break;
            }
            case "Lanzamiento":{
                if(this.lanzamiento != null){
                    errores.add(new ErrorAnalisis("Semantico","<sin cadena>","Ya se declaro anteriormente un valor para el lanzamiento que tuvo el lenguaje.",linea,columna));
                }else{
                    this.lanzamiento = dato.getValor();
                }
                break;
            }
            case "Autor":{
                if(this.autor != null){
                    errores.add(new ErrorAnalisis("Semantico","<sin cadena>","Ya se declaro anteriormente un valor para el autor que tendra el lenguaje.",linea,columna));
                }else{
                    this.autor = dato.getValor();
                }
                break;
            }
            case "Extension":{
                if(this.extension != null){
                    errores.add(new ErrorAnalisis("Semantico","<sin cadena>","Ya se declaro anteriormente un valor para la extension que tendra el lenguaje.",linea,columna));
                }else{
                    this.extension = dato.getValor();
                }
                break;
            }
            case "Version":{
                if(this.version != null){
                    errores.add(new ErrorAnalisis("Semantico","<sin cadena>","Ya se declaro anteriormente un valor para la version que tendra el lenguaje.",linea,columna));
                }else{
                    this.version = dato.getValor();
                }
                break;
            }
        }
    }

    @Override
    public String toString() {
        String cadena = "Nombre: "+this.nombre;
        if(this.extension != null) cadena+="\nExtension: "+this.extension;
        if(this.version != null) cadena+="\nVersion: "+this.version;
        if(this.autor != null) cadena+="\nAutor: "+this.autor;
        if(this.lanzamiento != null) cadena+="\nLanzamiento: "+this.lanzamiento;
        return cadena;
    }
}
