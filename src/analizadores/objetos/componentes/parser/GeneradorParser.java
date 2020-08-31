/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos.componentes.parser;

import analizadores.objetos.ErrorAnalisis;
import analizadores.objetos.TablaDeSimbolos;
import analizadores.objetos.Variable;
import analizadores.objetos.componentes.Utilidades;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author jose_
 */
public class GeneradorParser {
    
    private List<Produccion> producciones;
    private TablaDeSimbolos simbolos;
    private AutomataParser automata;
    private int estadoActual;
    
    public GeneradorParser(){
        this.producciones = new ArrayList();
        this.simbolos = new TablaDeSimbolos();
        this.automata = new AutomataParser();
    }
    
    public void generarEstados(){
        this.estadoActual = 0;
        List<Cerradura> cerraduras = new ArrayList(); //creamos la cerradura de la primer produdcion
        cerraduras.add(new Cerradura(0,0, new String[0])); //la agregamos al conjunto que tiene el "estado"
        Estado estado = crearEstadoAPartirDe(cerraduras); //generamos el estado completo a partir de esa cerradura como "nucleo"
        this.automata.getEstados().add(estado);
    }
    
    private Estado crearEstadoAPartirDe(List<Cerradura> cerraduras){
        Estado estado = new Estado(); //partimos de un estado nuevo
        for (Cerradura cerradura : cerraduras) { //por cada cerradura "nucleo" vamos a agregar las producciones que se agregan
            List<Cerradura> temporal = new ArrayList(); //tenemos un listado temporal de las cerraduras que vamos a agregar
            temporal.add(new Cerradura(cerradura));
            generarCerraduraConsecuente(temporal, new Cerradura(cerradura)); //vamos a agregar a ese listado temporal, todas las cerraduras que se generen a partir de ahí
            for (Cerradura cerraduraT : temporal) {//por cada cerradura temporal, la agregamos a las cerraduras del estado que estamos evaluando
                agregarCerraduras(estado.getCerraduras(), new Cerradura(cerraduraT));
            }
        }
        return estado;
    }
    
    private List<Cerradura> generarCerraduraConsecuente(List<Cerradura> cerraduras, Cerradura cerradura){
        //obtenemos el simbolo que precede al punto en la cerradura que vamos a evaluar
        Simbolo simbolo = (Simbolo) this.simbolos.getVariable(this.producciones.get(cerradura.getProduccion()).getSimbolosDerecha().get(cerradura.getPosicionPunto()).getSimbolo()).getValor();
        //si es un no terminal, es decir, se agregan mas cerraduras al estado
        if(!simbolo.isTerminal()){
            for (Produccion produccion : produccionesDelSimboloNoTerminal(simbolo.getSimbolo())) { //por cada produccion que tenga el simbolo
                String[] preAnalisis = null;
                //si hay simbolos suficientes para evaluar el punto
                if(this.producciones.get(cerradura.getProduccion()).getSimbolosDerecha().size() > (cerradura.getPosicionPunto()+1)){
                    //obtenemos el simbolo que se encuentra un espacio delante del punto
                    Simbolo simboloT = (Simbolo) this.simbolos.getVariable(this.producciones.get(cerradura.getProduccion()).getSimbolosDerecha().get(cerradura.getPosicionPunto()+1).getSimbolo()).getValor();
                    //si el simbolo es terminal
                    if(!simboloT.isTerminal() && simbolo.isLambda()){
                        preAnalisis = agregarSimbolosPreAnalisis(preAnalisis, simboloT.getPrimeros());
                        preAnalisis = agregarSimbolosPreAnalisis(preAnalisis, cerradura.getSimbolosPreAnalisis());
                    }else{
                        preAnalisis = simbolo.getPrimeros();
                    }
                }else{
                    preAnalisis = cerradura.getSimbolosPreAnalisis();
                }
                Cerradura temporal = new Cerradura(produccion.getId(),0,preAnalisis);
                if(existeCerradura(temporal, cerraduras)==(-1)){
                    cerraduras.add(temporal);
                    generarCerraduraConsecuente(cerraduras, temporal);
                }
            }
        }
        return cerraduras;
    }
    
    private List<Produccion> produccionesDelSimboloNoTerminal(String simbolo){
        List<Produccion> producciones = new ArrayList();
        for (Produccion produccion : this.producciones) {
            if(produccion.getSimboloIzquierda().getSimbolo().equals(simbolo)) producciones.add(produccion);
        }
        return producciones;
    }
    
    private void agregarCerraduras(List<Cerradura> cerraduras, Cerradura cerradura){
        int indiceCerradura = existeCerradura(cerradura, cerraduras);
        if(indiceCerradura==(-1)){
            cerraduras.add(cerradura);
        }else{
            cerraduras.get(indiceCerradura).setSimbolosPreAnalisis(agregarSimbolosPreAnalisis(cerraduras.get(indiceCerradura).getSimbolosPreAnalisis(),cerradura.getSimbolosPreAnalisis()));
        }
    }
    
    private int existeEstado(Estado estado){
        int valor = -1;
        for (int i=0; i < this.automata.getEstados().size(); i++) {
            if(compararEstados(this.automata.getEstados().get(i), estado)){
                valor = i;
                break;
            }
        }
        return valor;
    }
    
    private boolean compararEstados(Estado estadoP, Estado estado){
        boolean valor = true;
        if(estadoP.getCerraduras().size() == estado.getCerraduras().size()){
            for (Cerradura cerradura : estadoP.getCerraduras()) {
                if(existeCerradura(cerradura, estado.getCerraduras())==(-1)) valor = false;
            }
        }else{
            valor = false;
        }
        return valor;
    }
    
    private int existeCerradura(Cerradura cerradura, List<Cerradura> cerraduras){
        int valor = -1;
        for (int i = 0; i < cerraduras.size(); i++) {
            if(compararCerraduras(cerradura, cerraduras.get(i))) valor = i;
        }
        return valor;
    }
    
    private boolean compararCerraduras(Cerradura cerraduraP, Cerradura cerradura){
        boolean valor = false;
        if(cerraduraP.getProduccion() == cerradura.getProduccion()){
            if(cerraduraP.getPosicionPunto() == cerradura.getPosicionPunto()){
                if(Arrays.equals(cerraduraP.getSimbolosPreAnalisis(), cerradura.getSimbolosPreAnalisis())) valor = true;
            }
        }
        return valor;
    }
    
    private List<Cerradura> getNucleoDe(Estado estado){
        List<Cerradura> cerraduras = new ArrayList();
        for (Cerradura cerradura : estado.getCerraduras()) {
            if(cerradura.getPosicionPunto() != 0) cerraduras.add(new Cerradura(cerradura));
        }
        return cerraduras;
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
        List<Produccion> temporal = new ArrayList(this.producciones);
        this.producciones.clear();
        for (Produccion produccion : temporal) {
            boolean agregar = true;
            if(simbolos.getVariable(produccion.getSimboloIzquierda().getSimbolo()) == null){
                errores.add(new ErrorAnalisis("Semantico",produccion.getSimboloIzquierda().getSimbolo(),"El simbolo utilizado nunca fue declarado.", produccion.getSimboloIzquierda().getLinea(), produccion.getSimboloIzquierda().getColumna()));
                agregar = false;
            }
            for (Simbolo simbolo : produccion.getSimbolosDerecha()) {
                if(simbolos.getVariable(simbolo.getSimbolo()) == null){
                    errores.add(new ErrorAnalisis("Semantico",simbolo.getSimbolo(),"El simbolo utilizado nunca fue declarado.", simbolo.getLinea(), simbolo.getColumna()));
                    agregar = false;
                }
            }
            if(produccion.getSimbolosDerecha().isEmpty()){
                ((Simbolo)simbolos.getVariable(produccion.getSimboloIzquierda().getSimbolo()).getValor()).setLambda(true);
                agregar = false;
            }
            if(agregar) this.producciones.add(produccion);
        }
        if(errores.isEmpty()){
            this.simbolos = simbolos;
            extenderGramatica();
            calcularPrimeros();
        }
    }

    private List<Simbolo> getSimbolosT_NT(int opcion){
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

    private void extenderGramatica() {
        List<Simbolo> derechaExtension = new ArrayList();
        derechaExtension.add(this.producciones.get(0).getSimboloIzquierda());
        derechaExtension.add(new Simbolo("FinCadena",0,0));
        this.producciones.add(0, new Produccion(new Simbolo("InicioCadena",0,0), (ArrayList) derechaExtension));
        for (int i = 0; i < this.producciones.size(); i++) {
            this.producciones.get(i).setId(i);
        }
    }

    private String[] agregarSimbolosPreAnalisis(String[] simbolosOrigen, String[] simbolosAgregar) {
       List<String> temporal = new ArrayList();
        for (String string : simbolosOrigen) {
            temporal.add(string);
        }
        for (String string : simbolosAgregar) {
            if(!Utilidades.existe(string, temporal)) temporal.add(string);
        }
        simbolosOrigen = new String[temporal.size()];
        for (int i = 0; i < temporal.size(); i++) {
            simbolosOrigen[i] = temporal.get(i);
        }
        return simbolosOrigen;
    }

    public void calcularPrimeros() {
        for (Variable variable : this.simbolos.getVariables()) {
            Simbolo simbolo = (Simbolo) variable.getValor();
            if(!simbolo.isTerminal()){
                simbolo.setPrimeros(primerosDe(simbolo, simbolo, simbolo));
            }
        }
    }
    
    private String[] primerosDe(Simbolo raiz, Simbolo raizActual, Simbolo simbolo){
        String[] primeros = new String[0]; 
        if(!((Simbolo)this.simbolos.getVariable(simbolo.getSimbolo()).getValor()).isTerminal()){
            List<Simbolo> simbolosEvaluados = new ArrayList();
            List<Produccion> producciones = this.produccionesDelSimboloNoTerminal(simbolo.getSimbolo());
            for (Produccion produccion : producciones) {
                List<Simbolo> simboloDerecha = produccion.getSimbolosDerecha();
                for (Simbolo simboloTemp : simboloDerecha) {
                    Simbolo simboloT = (Simbolo) this.simbolos.getVariable(simboloTemp.getSimbolo()).getValor();
                    if((!raizActual.getSimbolo().equals(simboloT.getSimbolo())&&(!raiz.getSimbolo().equals(simboloT.getSimbolo())))){
                        if(!existeSimbolo(simboloT, simbolosEvaluados)) simbolosEvaluados.add(simboloT);
                        if(!simboloT.isLambda()) break;
                    }else{
                        if(!simboloT.isLambda()) break;
                    }
                }
            }
            for (Simbolo simboloEvaluado : simbolosEvaluados) {
                primeros = agregarSimbolosPreAnalisis(primeros, primerosDe(raiz, simboloEvaluado, simboloEvaluado));
            }
        }else{
            primeros = ((Simbolo)this.simbolos.getVariable(simbolo.getSimbolo()).getValor()).getPrimeros();
        }
        return primeros;
    }

    private boolean existeSimbolo(Simbolo simboloT, List<Simbolo> simbolosEvaluados) {
        for (Simbolo simbolosEvaluado : simbolosEvaluados) {
            if(simbolosEvaluado.getSimbolo().equals(simboloT.getSimbolo())) return true;
        }
        return false;
    }

    public void escribirEstados() {
        for (Estado estado : this.automata.getEstados()) {
            System.out.println(estado.toString());
        }
    }

    public void escribirSimbolos() {
        for (Variable variable : simbolos.getVariables()) {
            System.out.println(((Simbolo)variable.getValor()).toString());
        }
    }
}
