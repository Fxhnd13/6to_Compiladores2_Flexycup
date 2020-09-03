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
 * Clase que se encarga de almacenar las variables creadas durante la generación de un nuevo lenguaje
 * @author jose_
 */
public class TablaDeSimbolos implements Serializable{
    
    List<Variable> variables = new ArrayList();
    
    /**
     * verifica si el nombre enviado de parametro ya existe en la tabla de simbolos
     * @param nuevo nombre de la variable que vamos a verificar si existe
     * @return si el existe o no existe
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
     *  agrega una nueva variable a la tabla de simbolos
     * @param variable variable a agregar
     * @return si se pudo o no agregar la variable
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
     * metodo para obtener una variable de la tabla de simbolos
     * @param id es el id de la variable
     * @return la variable, si existe, sino retorna null
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
     * @return las variables
     */
    public List<Variable> getVariables() {
        return variables;
    }

    /**
     * cambia las variables
     * @param variables
     */
    public void setVariables(List<Variable> variables) {
        this.variables = variables;
    }
    
}
