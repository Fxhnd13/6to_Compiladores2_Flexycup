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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getLanzamiento() {
        return lanzamiento;
    }

    public void setLanzamiento(String lanzamiento) {
        this.lanzamiento = lanzamiento;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
    
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
}
