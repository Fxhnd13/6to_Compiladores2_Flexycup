/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.analizadorFinal;

import analizadores.objetos.componentes.lexer.Token;
import analizadores.objetos.componentes.parser.AutomataParser;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author jose_
 */
public class Parser implements Serializable{
    
    List<Token> tokens;
    AutomataParser automata;
    
}
