/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos.componentes.parser;

import analizadores.objetos.ErrorAnalisis;
import analizadores.objetos.TablaDeSimbolos;
import analizadores.objetos.Variable;
import analizadores.objetos.componentes.parser.acciones.Accion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jose_
 */
public class GeneradorParser {
    
    private List<Produccion> producciones;
    private List<Accion[]> acciones, irA;
    private TablaDeSimbolos simbolos;
    
    public GeneradorParser(){
        this.producciones = new ArrayList();
        this.simbolos = new TablaDeSimbolos();
    }
    
    public void verificarIntegridad(List<ErrorAnalisis> errores, TablaDeSimbolos expresionRegulares, TablaDeSimbolos simbolos){
        //verificar que cada simbolo terminal, exista una expresion regular
        for (Variable variable : simbolos.getVariables()) {
            Simbolo simbolo = (Simbolo) variable.getValor();
            if(simbolo.isTerminal()){
                if(expresionRegulares.getVariable(simbolo.getSimbolo()) == null) errores.add(new ErrorAnalisis("Semantico",simbolo.getSimbolo(),"El simbolo terminal declarado no hace match con ningún token declarado anteriormente.",simbolo.getLinea(),simbolo.getColumna()));
            }
        }
        //verificar que cada simbolo en producciones, exista en simbolos declarados
        for (Produccion produccion : this.producciones) {
            if(simbolos.getVariable(produccion.getSimboloIzquierda().getSimbolo()) == null){
                errores.add(new ErrorAnalisis("Semantico",produccion.getSimboloIzquierda().getSimbolo(),"El simbolo utilizado nunca fue declarado.", produccion.getSimboloIzquierda().getLinea(), produccion.getSimboloIzquierda().getColumna()));
            }
            for (Simbolo simbolo : produccion.getSimbolosDerecha()) {
                if(simbolos.getVariable(simbolo.getSimbolo()) == null){
                errores.add(new ErrorAnalisis("Semantico",simbolo.getSimbolo(),"El simbolo utilizado nunca fue declarado.", simbolo.getLinea(), simbolo.getColumna()));
                }
            }
        }
        if(errores.isEmpty()) this.simbolos = simbolos;
    }

    public List<Simbolo> getSimbolosT_NT(int opcion){
        List<Simbolo> simbolos = new ArrayList();
        for (Variable variable : this.simbolos.getVariables()) {
            Simbolo simbolo = (Simbolo) variable.getValor();
            if(opcion == 0){
                if(simbolo.isTerminal()) simbolos.add(simbolo);
            }else{
                if(!simbolo.isTerminal()) simbolos.add(simbolo);
            }
        }
        return simbolos;
    }
    
    public List<Produccion> getProducciones() {
        return producciones;
    }

    public void setProducciones(List<Produccion> producciones) {
        this.producciones = producciones;
    }

    public void escribirSimbolos() {
        for (Variable variable : simbolos.getVariables()) {
            System.out.println(((Simbolo)variable.getValor()).toString());
        }
    }
    
}
