/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos.componentes.parser;

import analizadores.objetos.componentes.parser.acciones.Accion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jose_
 */
public class Estado implements Serializable{
    
    private List<Cerradura> cerraduras;
    private List<Accion> acciones;

    public Estado(){
        this.acciones = new ArrayList();
        this.cerraduras = new ArrayList();
    }

    public List<Accion> getAcciones() {
        return acciones;
    }

    public void setAcciones(List<Accion> acciones) {
        this.acciones = acciones;
    }
    
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
        return "Estado{" + "    cerraduras=" + cadenaCerraduras + "\n}";
    }
    
    
}
