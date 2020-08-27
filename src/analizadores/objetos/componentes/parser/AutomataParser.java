/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos.componentes.parser;

import analizadores.objetos.componentes.parser.acciones.Accion;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author jose_
 */
public class AutomataParser {
    
    private Accion[][] acciones;
    private List<Estado> estados;
    private Stack<Integer> pilaEstados;
    private Stack<String> pilaSimbolos;
    
}
