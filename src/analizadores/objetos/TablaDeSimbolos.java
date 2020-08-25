/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jose_
 */
public class TablaDeSimbolos {
    
    List<Variable> variables = new ArrayList<Variable>();
    
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
    
    public boolean addVariable(Variable variable){
        if(verificarDisponibilidad(variable.getId())){
            variables.add(variable);
            return true;
        }else{
            return false;
        }
    }
    
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
    
}
