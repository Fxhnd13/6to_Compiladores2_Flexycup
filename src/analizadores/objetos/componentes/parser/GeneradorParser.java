/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos.componentes.parser;

import analizadores.objetos.TablaDeSimbolos;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jose_
 */
public class GeneradorParser {
    
    private List<Produccion> producciones;
    
    public GeneradorParser(){
        this.producciones = new ArrayList();
    }
    
    public void verificarIntegridad(List<String> errores, TablaDeSimbolos terminales, TablaDeSimbolos noTerminales){
        
    }

    public List<Produccion> getProducciones() {
        return producciones;
    }

    public void setProducciones(List<Produccion> producciones) {
        this.producciones = producciones;
    }
    
}
