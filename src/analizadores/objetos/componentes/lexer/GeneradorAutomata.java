/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos.componentes.lexer;

import analizadores.objetos.componentes.NodoER.Nodo;
import analizadores.objetos.componentes.NodoER.NodoAst;
import analizadores.objetos.componentes.NodoER.NodoConcat;
import analizadores.objetos.componentes.NodoER.NodoDis;
import analizadores.objetos.componentes.NodoER.NodoHoja;
import analizadores.objetos.componentes.NodoER.NodoMas;
import analizadores.objetos.componentes.NodoER.NodoQuiza;
import analizadores.objetos.componentes.Utilidades;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author jose_
 */
public class GeneradorAutomata {
    
    private Nodo expresionRegular;
    private Automata automata;
    
    public GeneradorAutomata(){
        this.automata = new Automata();
    }

    public Automata getAutomata() {
        return automata;
    }

    public void setAutomata(Automata automata) {
        this.automata = automata;
    }
    
    public void setExpresionRegular(Nodo expresion){
        this.expresionRegular = expresion;
    }
    
    public Nodo getExpresionRegular(){ return expresionRegular; }
    
    public void calcularArbol(){
        int idAceptacion = numerarNodosHoja(1, expresionRegular);
        Nodo nodo = new NodoConcat(expresionRegular, new NodoHoja(idAceptacion, (char)3));
        expresionRegular = nodo;
        calcularSiguientes(expresionRegular);
    }
    
    public void crearEstadosAutomata(){
        int indiceEstadoActual = 0;
        int idFinal = ((NodoHoja)((NodoConcat)expresionRegular).getDerecho()).getId();
        Estado estado = new Estado(expresionRegular.primeros());
        if(estado.getIdNodosComponentes()[estado.getIdNodosComponentes().length-1] == idFinal){
            estado.setEstadoFinal(true);
        }
        automata.getEstados().add(estado);
        while(indiceEstadoActual < automata.getEstados().size()){
            
            estado = automata.getEstados().get(indiceEstadoActual);
            int [] primerosAnalizados  = new int[estado.getIdNodosComponentes().length]; //agregamos a este arreglo los id, que ya han sido analizados
            int indicePrimerosAnalizados = 0; //Para saber en que indice agregamos el siguiente primero que fue analizado
            for (int i = 0; i < estado.getIdNodosComponentes().length; i++) { //por cada id que se encuentre en la composición del estado
                
                if(estado.getIdNodosComponentes()[i] != (idFinal)){
                    if(!Utilidades.existe(estado.getIdNodosComponentes()[i], primerosAnalizados)){

                        ArrayList<Integer> composicionNuevoEstado = new ArrayList<Integer>(); 
                        char caracter = obtenerCaracterDelNodo(estado.getIdNodosComponentes()[i], expresionRegular); //obtenemos el caracter
                        composicionNuevoEstado.add(estado.getIdNodosComponentes()[i]); //añadimos los id que conforman a Ui
                        primerosAnalizados[indicePrimerosAnalizados++] = estado.getIdNodosComponentes()[i]; //añadimos el id a los ya analizados
                        for (int j = i+1; j < estado.getIdNodosComponentes().length; j++) { //para cada id que este por delante del actual estudiado

                            if(estado.getIdNodosComponentes()[j] != (idFinal)){
                                if(!Utilidades.existe(estado.getIdNodosComponentes()[j], primerosAnalizados)){
                                    ParametroBusqueda parametro = new ParametroBusqueda(caracter);
                                    idPoseeCaracter(estado.getIdNodosComponentes()[j], parametro, expresionRegular);
                                    if(parametro.isValorRetorno()){ //revisamos si posee el caracter
                                        composicionNuevoEstado.add(estado.getIdNodosComponentes()[j]); //agregamos el id al conjunto de id que componen Ui
                                        primerosAnalizados[indicePrimerosAnalizados++] = estado.getIdNodosComponentes()[j]; //agregamos al conjunto de id ya analizados
                                    }

                                }
                            }

                        }
                        
                        Utilidades.ordenar(composicionNuevoEstado); //ordenamos el arreglo, dado que el tipo del id al inicio, será el token que retornará
                        //vamos a crear nuestro nuevo estado, con la union de todos los siguientes que tienen todos los id's en composicionNuevoEstado
                        ArrayList<Integer> composicionRealNuevoEstado = new ArrayList<Integer>();
                        for (Integer id : composicionNuevoEstado) {
                            agregarSiguientesDe(expresionRegular.siguientes(id), composicionRealNuevoEstado);
                        }
                        int indiceEstado = indiceEstadoConComposicion(composicionRealNuevoEstado);
                        if( indiceEstado == -1){
                            automata.getEstados().add(new Estado(idFinal, composicionRealNuevoEstado, obtenerTipoTokenDe(composicionRealNuevoEstado.get(0), expresionRegular)));
                            automata.getEstados().get(indiceEstadoActual).getTransiciones().add(new Transicion(automata.getEstados().size()-1, caracter));
                        }else{
                            automata.getEstados().get(indiceEstadoActual).getTransiciones().add(new Transicion(indiceEstado, caracter));
                        }

                    }
                
                }
                
            }
            
            indiceEstadoActual++;
            
        }
    }
    
    public void asignarTipoToken(String token, Nodo expresion){
        if(expresion instanceof NodoConcat){
            asignarTipoToken(token, ((NodoConcat) expresion).getIzquierdo());
            asignarTipoToken(token, ((NodoConcat) expresion).getDerecho());
        }else if(expresion instanceof NodoDis){
            asignarTipoToken(token, ((NodoDis) expresion).getIzquierdo());
            asignarTipoToken(token, ((NodoDis) expresion).getDerecho());
        }else if(expresion instanceof NodoQuiza){
            asignarTipoToken(token, ((NodoQuiza) expresion).getHijo());
        }else if(expresion instanceof NodoAst){
            asignarTipoToken(token, ((NodoAst) expresion).getHijo());
        }else if(expresion instanceof NodoMas){
            asignarTipoToken(token, ((NodoMas) expresion).getHijo());
        }else if(expresion instanceof NodoHoja){
            ((NodoHoja) expresion).setTipoToken(token);
        }
    }
    
    public int numerarNodosHoja(int id, Nodo expresion){
        if(expresion instanceof NodoConcat){
            id = numerarNodosHoja(id, ((NodoConcat) expresion).getIzquierdo());
            id = numerarNodosHoja(id, ((NodoConcat) expresion).getDerecho());
        }else if(expresion instanceof NodoDis){
            id = numerarNodosHoja(id, ((NodoDis) expresion).getIzquierdo());
            id = numerarNodosHoja(id, ((NodoDis) expresion).getDerecho());
        }else if(expresion instanceof NodoQuiza){
            id = numerarNodosHoja(id, ((NodoQuiza) expresion).getHijo());
        }else if(expresion instanceof NodoAst){
            id = numerarNodosHoja(id, ((NodoAst) expresion).getHijo());
        }else if(expresion instanceof NodoMas){
            id = numerarNodosHoja(id, ((NodoMas) expresion).getHijo());
        }else if(expresion instanceof NodoHoja){
            ((NodoHoja) expresion).setId(id++);
        }
        return id;
    }
    
    public void calcularSiguientes(Nodo expresion){
        if(expresion instanceof NodoConcat){
            calcularSiguientes(((NodoConcat) expresion).getIzquierdo());
            calcularSiguientes(((NodoConcat) expresion).getDerecho());
            for (int ultimo : ((NodoConcat) expresion).getIzquierdo().ultimos()) {
                expresion.agregarSiguientes(ultimo, ((NodoConcat) expresion).getDerecho().primeros());
            }
        }else if(expresion instanceof NodoDis){
            calcularSiguientes(((NodoDis) expresion).getIzquierdo());
            calcularSiguientes(((NodoDis) expresion).getDerecho());
        }else if(expresion instanceof NodoQuiza){
            calcularSiguientes(((NodoQuiza) expresion).getHijo());
        }else if(expresion instanceof NodoAst){
            calcularSiguientes(((NodoAst) expresion).getHijo());
            for (int ultimo : ((NodoAst) expresion).getHijo().ultimos()) {
                expresion.agregarSiguientes(ultimo, ((NodoAst) expresion).getHijo().primeros());
            }
        }else if(expresion instanceof NodoMas){
            calcularSiguientes(((NodoMas) expresion).getHijo());
            for (int ultimo : ((NodoMas) expresion).getHijo().ultimos()) {
                expresion.agregarSiguientes(ultimo, ((NodoMas) expresion).getHijo().primeros());
            }
        }
    }
    
    public Nodo agregarRango(String rango, int opcion){
        Nodo raiz = new NodoDis();
        char[] caracteres = Utilidades.obtenerCaracteres(rango, opcion);
        for (int i = 0; i < caracteres.length; i++) {
            if(i == 0){
                Nodo izquierdo = new NodoHoja(caracteres[i++]);
                Nodo derecho = new NodoHoja(caracteres[i]);
                ((NodoDis) raiz).setIzquierdo(izquierdo);
                ((NodoDis) raiz).setDerecho(derecho);
            }else{
                Nodo derecho = new NodoHoja(caracteres[i]);
                Nodo nuevo = new NodoDis(raiz, derecho);
                raiz = nuevo;
            }
        }
        return raiz;
    }
    
    public Nodo agregarCadena(String cadena){
        Nodo raiz = new NodoConcat();
        char[] caracteres = cadena.toCharArray();
        if(caracteres.length > 2){
            for (int i = 0; i < caracteres.length; i++) {
                if(i == 0){
                    Nodo izquierdo = new NodoHoja(caracteres[i++]);
                    Nodo derecho = new NodoHoja(caracteres[i]);
                    ((NodoConcat) raiz).setIzquierdo(izquierdo);
                    ((NodoConcat) raiz).setDerecho(derecho);
                }else{
                    Nodo derecho = new NodoHoja(caracteres[i]);
                    Nodo nuevo = new NodoConcat(raiz, derecho);
                    raiz = nuevo;
                }
        }
        }else{
            if(caracteres.length == 2){
                Nodo izquierdo = new NodoHoja(cadena.charAt(0));
                Nodo derecho = new NodoHoja(cadena.charAt(1));
                ((NodoConcat) raiz).setIzquierdo(izquierdo);
                ((NodoConcat) raiz).setDerecho(derecho);
            }else{
                raiz = new NodoHoja(cadena.charAt(0));
            }
        }
        return raiz;
    }

    private char obtenerCaracterDelNodo(int id, Nodo expresion) {
        char caracter = 5;
        if(expresion instanceof NodoConcat){
            caracter = obtenerCaracterDelNodo(id, ((NodoConcat)expresion).getIzquierdo());
            if(caracter == 5) caracter = obtenerCaracterDelNodo(id, ((NodoConcat)expresion).getDerecho());
        }else if(expresion instanceof NodoDis){
            caracter = obtenerCaracterDelNodo(id, ((NodoDis)expresion).getIzquierdo());
            if(caracter == 5) caracter = obtenerCaracterDelNodo(id, ((NodoDis)expresion).getDerecho());
        }else if(expresion instanceof NodoQuiza){
            caracter = obtenerCaracterDelNodo(id, ((NodoQuiza)expresion).getHijo());
        }else if(expresion instanceof NodoAst){
            caracter = obtenerCaracterDelNodo(id, ((NodoAst)expresion).getHijo());
        }else if(expresion instanceof NodoMas){
            caracter = obtenerCaracterDelNodo(id, ((NodoMas)expresion).getHijo());
        }else if(expresion instanceof NodoHoja){
            if(((NodoHoja) expresion).getId() == id) caracter = ((NodoHoja) expresion).getValor();
        }
        return caracter;
    }
    
    private String obtenerTipoTokenDe(int id, Nodo expresion) {
        String caracter = null;
        if(expresion instanceof NodoConcat){
            caracter = obtenerTipoTokenDe(id, ((NodoConcat)expresion).getIzquierdo());
            if(caracter == null) caracter = obtenerTipoTokenDe(id, ((NodoConcat)expresion).getDerecho());
        }else if(expresion instanceof NodoDis){
            caracter = obtenerTipoTokenDe(id, ((NodoDis)expresion).getIzquierdo());
            if(caracter == null) caracter = obtenerTipoTokenDe(id, ((NodoDis)expresion).getDerecho());
        }else if(expresion instanceof NodoQuiza){
            caracter = obtenerTipoTokenDe(id, ((NodoQuiza)expresion).getHijo());
        }else if(expresion instanceof NodoAst){
            caracter = obtenerTipoTokenDe(id, ((NodoAst)expresion).getHijo());
        }else if(expresion instanceof NodoMas){
            caracter = obtenerTipoTokenDe(id, ((NodoMas)expresion).getHijo());
        }else if(expresion instanceof NodoHoja){
            if(((NodoHoja) expresion).getId() == id) caracter = ((NodoHoja) expresion).getTipoToken();
        }
        return caracter;
    }

    private void idPoseeCaracter(int id, ParametroBusqueda parametro, Nodo expresion) {
        if(expresion instanceof NodoConcat){
            if(!parametro.isEncontrado()) idPoseeCaracter(id, parametro, ((NodoConcat)expresion).getIzquierdo());
            if(!parametro.isEncontrado()) idPoseeCaracter(id, parametro, ((NodoConcat)expresion).getDerecho());
        }else if(expresion instanceof NodoDis){
            if(!parametro.isEncontrado()) idPoseeCaracter(id, parametro, ((NodoDis)expresion).getIzquierdo());
            if(!parametro.isEncontrado()) idPoseeCaracter(id, parametro, ((NodoDis)expresion).getDerecho());
        }else if(expresion instanceof NodoQuiza){
            if(!parametro.isEncontrado()) idPoseeCaracter(id, parametro, ((NodoQuiza)expresion).getHijo());
        }else if(expresion instanceof NodoAst){
            if(!parametro.isEncontrado()) idPoseeCaracter(id, parametro, ((NodoAst)expresion).getHijo());
        }else if(expresion instanceof NodoMas){
            if(!parametro.isEncontrado()) idPoseeCaracter(id, parametro, ((NodoMas)expresion).getHijo());
        }else if(expresion instanceof NodoHoja){
            if(id == ((NodoHoja) expresion).getId()){
                parametro.setEncontrado(true);
                if(parametro.getValor() == ((NodoHoja) expresion).getValor()){
                    parametro.setValorRetorno(true);
                }
            }
        }
    }

    private void agregarSiguientesDe(int[] siguientes, ArrayList<Integer> composicionRealNuevoEstado) {
        for (int siguiente : siguientes) {
            if(!Utilidades.existe(siguiente, composicionRealNuevoEstado)) composicionRealNuevoEstado.add(siguiente);
        }
        Utilidades.ordenar(composicionRealNuevoEstado);
    }

    private int indiceEstadoConComposicion(ArrayList<Integer> composicionNuevoEstado) {
        int indice = -1;
        for (int i = 0; i < automata.getEstados().size(); i++) {
            int[] nuevo = new int[composicionNuevoEstado.size()];
            for (int j = 0; j < composicionNuevoEstado.size(); j++) {
                nuevo[j] = composicionNuevoEstado.get(j);
            }
            if(Arrays.equals(automata.getEstados().get(i).getIdNodosComponentes(), nuevo)) indice = i;
        }
        return indice;
    }
}
