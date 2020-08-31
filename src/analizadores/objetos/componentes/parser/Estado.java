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
    @Override
    public String toString() {
        String cadenaCerraduras = "";
        for (int i = 0; i < cerraduras.size(); i++) {
            cadenaCerraduras+="          "+cerraduras.get(i).toString();
            if((i+1) < cerraduras.size()) cadenaCerraduras+="\n";
        }
        return "Estado{" + "    cerraduras=" + cerraduras + "\n}";
    }
    
    
}
