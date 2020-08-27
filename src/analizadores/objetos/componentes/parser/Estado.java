/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos.componentes.parser;

import java.util.List;

/**
 *
 * @author jose_
 */
public class Estado {
    
    List<Cerradura> cerraduras;

    public List<Cerradura> getCerraduras() {
        return cerraduras;
    }

    public void setCerraduras(List<Cerradura> cerraduras) {
        this.cerraduras = cerraduras;
    }
    
    public Cerradura getNucleo(){
        if(this.cerraduras!= null && !this.cerraduras.isEmpty()){
            return this.cerraduras.get(0);
        }else{
            return null;
        }
    }
}
