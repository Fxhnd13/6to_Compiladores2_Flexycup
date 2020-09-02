/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jose_
 */
public class TablaDeSimbolos implements Serializable{
    
    List<Variable> variables = new ArrayList<Variable>();
    
    /**
     *
     * @param nuevo
     * @return
     */
    public boolean verificarDisponibilidad(String nuevo){
        boolean valor = true;
        for (Variable variable : variables) {
            if(variable.getId().equals(nuevo)){
                valor = false;
                break;
            } 
        }
        return valor;
    }
    
    /**
     *
     * @param variable
     * @return
     */
    public boolean addVariable(Variable variable){
        if(verificarDisponibilidad(variable.getId())){
            variables.add(variable);
            return true;
        }else{
            return false;
        }
    }
    
    /**
     *
     * @param id
     * @return
     */
    public Variable getVariable(String id){
        Variable retorno = null;
        if(!verificarDisponibilidad(id)){
            for (Variable variable : variables) {
                if(variable.getId().equals(id)){
                    retorno = variable;
                }
            }
        }
        return retorno;
    }

    /**
     *
     * @return
     */
    public List<Variable> getVariables() {
        return variables;
    }

    /**
     *
     * @param variables
     */
    public void setVariables(List<Variable> variables) {
        this.variables = variables;
    }
    
}
