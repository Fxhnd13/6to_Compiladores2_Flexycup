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
import analizadores.objetos.componentes.parser.acciones.Accion;
import analizadores.objetos.componentes.parser.acciones.Aceptacion;
import analizadores.objetos.componentes.parser.acciones.GoTo;
import analizadores.objetos.componentes.parser.acciones.Reduce;
import analizadores.objetos.componentes.parser.acciones.Shift;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que se encarga de generar el automata del parser
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
    
    /**
     * Genera los estados del automata del analizador sintactico LR(1)
     * @param errores
     */
    public void generarEstados(List<ErrorAnalisis> errores){
        this.estadoActual = 0;
        List<Cerradura> cerraduras = new ArrayList(); //creamos la cerradura de la primer produdcion
        cerraduras.add(new Cerradura(0,0, new String[0])); //la agregamos al conjunto que tiene el "estado"
        Estado estado = crearEstadoAPartirDe(cerraduras); //generamos el estado completo a partir de esa cerradura como "nucleo"
        this.automata.getEstados().add(estado);
        while(this.estadoActual < this.automata.getEstados().size()){
            int[] cerradurasEvaluadas = new int[this.automata.getEstados().get(estadoActual).getCerraduras().size()];
            int indiceCerradurasEvaluadas = 0;
            for (int i = 0; i < this.automata.getEstados().get(estadoActual).getCerraduras().size(); i++) {
                if(!Utilidades.existe(i, cerradurasEvaluadas, indiceCerradurasEvaluadas)){
                    Cerradura cerradura = this.automata.getEstados().get(estadoActual).getCerraduras().get(i);
                    cerradurasEvaluadas[indiceCerradurasEvaluadas++] = i;
                    if(cerradura.getPosicionPunto() < this.producciones.get(cerradura.getProduccion()).getSimbolosDerecha().size()){
                        List<Cerradura> cerradurasTemporal = new ArrayList();
                        cerradurasTemporal.add(new Cerradura(cerradura));
                        Simbolo simbolo =  (Simbolo) this.simbolos.getVariable(this.producciones.get(cerradura.getProduccion()).getSimbolosDerecha().get(cerradura.getPosicionPunto()).getSimbolo()).getValor();
                        for (int j = i; j < this.automata.getEstados().get(estadoActual).getCerraduras().size(); j++) {
                            if(!Utilidades.existe(j, cerradurasEvaluadas, indiceCerradurasEvaluadas)){
                                if(this.automata.getEstados().get(estadoActual).getCerraduras().get(j).getPosicionPunto() < this.producciones.get(this.automata.getEstados().get(estadoActual).getCerraduras().get(j).getProduccion()).getSimbolosDerecha().size()){
                                    if(simbolo.getSimbolo().equals(this.producciones.get(this.automata.getEstados().get(estadoActual).getCerraduras().get(j).getProduccion()).getSimbolosDerecha().get(this.automata.getEstados().get(estadoActual).getCerraduras().get(j).getPosicionPunto()).getSimbolo())){
                                        cerradurasEvaluadas[indiceCerradurasEvaluadas++] = j;
                                        cerradurasTemporal.add(new Cerradura(this.automata.getEstados().get(estadoActual).getCerraduras().get(j)));
                                    }
                                }else{
                                    if(this.automata.getEstados().get(estadoActual).getCerraduras().get(j).getSimbolosPreAnalisis().length != 0){
                                        for (String T : this.automata.getEstados().get(estadoActual).getCerraduras().get(j).getSimbolosPreAnalisis()) {
                                            Accion accion = new Reduce(T, cerradura.getProduccion());
                                            this.automata.getEstados().get(estadoActual).getAcciones().add(accion);
                                        }
                                    }
                                }
                            }
                        }
                        for (Cerradura cerraduraT : cerradurasTemporal) {
                            cerraduraT.setPosicionPunto(cerraduraT.getPosicionPunto()+1);
                        }
                        Estado estadoNuevo = crearEstadoAPartirDe(cerradurasTemporal);
                        int indiceEstadoNuevo = existeEstado(estadoNuevo);
                        boolean isAceptacion = (estadoNuevo.getCerraduras().size()==1) && (estadoNuevo.getCerraduras().get(0).getSimbolosPreAnalisis().length ==0);
                        if(indiceEstadoNuevo == (-1)){
                            Accion accion = null;
                            if(!isAceptacion){
                                this.automata.getEstados().add(estadoNuevo);
                                accion = (simbolo.isTerminal())? new Shift(simbolo.getSimbolo(), this.automata.getEstados().size()-1): new GoTo(simbolo.getSimbolo(), this.automata.getEstados().size()-1);
                            }else{
                                accion = new Aceptacion();
                            }
                            Accion encontrada  = existeAccion(accion, this.automata.getEstados().get(estadoActual));
                            if(encontrada == null){
                                this.automata.getEstados().get(estadoActual).getAcciones().add(accion);
                            }else{
                                if(accion instanceof Shift && encontrada instanceof Shift) errores.add(new ErrorAnalisis("Ambiguedad Gramatica","<sin cadena>","Conflicto shift shift, bajo el simbolo '"+accion.getSimbolo()+"'",0,0));
                                if(accion instanceof Reduce && encontrada instanceof Reduce) errores.add(new ErrorAnalisis("Ambiguedad Gramatica","<sin cadena>","Conflicto shift shift, bajo el simbolo '"+accion.getSimbolo()+"'",0,0));
                                if((accion instanceof Shift && encontrada instanceof Reduce)){
                                    if(((Simbolo)simbolos.getVariable(accion.getSimbolo()).getValor()).getPrecedencia() < this.valorPrecedenciaDe(encontrada.getValor())){
                                        eliminarConflictoShiftReduce(this.automata.getEstados().get(estadoActual), accion);
                                    }
                                }else if(accion instanceof Reduce && encontrada instanceof Shift){
                                    if(this.valorPrecedenciaDe(accion.getValor()) > ((Simbolo)simbolos.getVariable(encontrada.getSimbolo()).getValor()).getPrecedencia()){
                                        eliminarConflictoShiftReduce(this.automata.getEstados().get(estadoActual), encontrada);
                                    }
                                }
                            }
                        }else{
                            Accion accion = (simbolo.isTerminal())? new Shift(simbolo.getSimbolo(), indiceEstadoNuevo): new GoTo(simbolo.getSimbolo(), indiceEstadoNuevo);
                            if(accion instanceof Shift){
                                if(this.automata.getEstados().get(accion.getValor()).getCerraduras().get(0).getSimbolosPreAnalisis().length == 0) accion = new Aceptacion();
                            }
                            Accion encontrada  = existeAccion(accion, this.automata.getEstados().get(estadoActual));
                            if(encontrada == null){
                                this.automata.getEstados().get(estadoActual).getAcciones().add(accion);
                            }else{
                                if(accion instanceof Shift && encontrada instanceof Shift) errores.add(new ErrorAnalisis("Ambiguedad Gramatica","<sin cadena>","Conflicto shift shift, bajo el simbolo '"+accion.getSimbolo()+"'",0,0));
                                if(accion instanceof Reduce && encontrada instanceof Reduce) errores.add(new ErrorAnalisis("Ambiguedad Gramatica","<sin cadena>","Conflicto shift shift, bajo el simbolo '"+accion.getSimbolo()+"'",0,0));
                                if((accion instanceof Shift && encontrada instanceof Reduce)){
                                    if(((Simbolo)simbolos.getVariable(accion.getSimbolo()).getValor()).getPrecedencia() < this.valorPrecedenciaDe(encontrada.getValor())){
                                        eliminarConflictoShiftReduce(this.automata.getEstados().get(estadoActual), accion);
                                    }
                                }else if(accion instanceof Reduce && encontrada instanceof Shift){
                                    if(this.valorPrecedenciaDe(accion.getValor()) > ((Simbolo)simbolos.getVariable(encontrada.getSimbolo()).getValor()).getPrecedencia()){
                                        eliminarConflictoShiftReduce(this.automata.getEstados().get(estadoActual), encontrada);
                                    }
                                }
                            }
                        }
                    }else{
                        if(cerradura.getSimbolosPreAnalisis().length != 0){
                            for (String simbolo : cerradura.getSimbolosPreAnalisis()) {
                                Accion accion = new Reduce(simbolo, cerradura.getProduccion());
                                this.automata.getEstados().get(estadoActual).getAcciones().add(accion);
                            }
                        }
                    }
                }
            }
            estadoActual++;
        }
        //generarLALR();
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
    
    private void generarCerraduraConsecuente(List<Cerradura> cerraduras, Cerradura cerradura){
        //obtenemos el simbolo que precede al punto en la cerradura que vamos a evaluar
        if(cerradura.getPosicionPunto() < this.producciones.get(cerradura.getProduccion()).getSimbolosDerecha().size()){
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
                        if(!simboloT.isTerminal() && simboloT.isLambda()){
                            preAnalisis = agregarSimbolosPreAnalisis(preAnalisis, simboloT.getPrimeros());
                            preAnalisis = agregarSimbolosPreAnalisis(preAnalisis, cerradura.getSimbolosPreAnalisis());
                        }else{
                            preAnalisis = simboloT.getPrimeros();
                        }
                    }else{
                        preAnalisis = cerradura.getSimbolosPreAnalisis();
                    }
                    Cerradura temporal = new Cerradura(produccion.getId(),0,preAnalisis);
                    if(existeCerraduraExacta(temporal, cerraduras)==(-1)){
                        cerraduras.add(temporal);
                        generarCerraduraConsecuente(cerraduras, temporal);
                    }
                }
            }
        }
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
        if(estadoP.getCerraduras().size() == estado.getCerraduras().size()){
            for (Cerradura cerradura : estadoP.getCerraduras()) {
                if(existeCerraduraExacta(cerradura, estado.getCerraduras())==(-1)) return false;
            }
        }else{
            return false;
        }
        return true;
    }
    
    private int existeCerradura(Cerradura cerradura, List<Cerradura> cerraduras){
        for (int i = 0; i < cerraduras.size(); i++) {
            if(compararCerraduras(cerradura, cerraduras.get(i))) return i;
        }
        return -1;
    }
    
    private boolean compararCerraduras(Cerradura cerraduraP, Cerradura cerradura){
        if(cerraduraP.getProduccion() == cerradura.getProduccion()){
            if(cerraduraP.getPosicionPunto() == cerradura.getPosicionPunto()) return true;
        }
        return false;
    }
    
    private int existeCerraduraExacta(Cerradura cerradura, List<Cerradura> cerraduras){
        for (int i = 0; i < cerraduras.size(); i++) {
            if(compararCerradurasExactas(cerradura, cerraduras.get(i))) return i;
        }
        return -1;
    }
    
    private boolean compararCerradurasExactas(Cerradura cerraduraP, Cerradura cerradura){
        if(cerraduraP.getProduccion() == cerradura.getProduccion()){
            if(cerraduraP.getPosicionPunto() == cerradura.getPosicionPunto()){
                if(compararSimbolosPreAnalisisExactos(cerraduraP.getSimbolosPreAnalisis(), cerradura.getSimbolosPreAnalisis())) return true;
            }
        }
        return false;
    }
    
    /**
     * metodo utilizado después de terminar el analisis del archivo de la estructura de la gramatica
     * @param errores listado de errores, para agregar si se presenta alguno
     * @param expresionRegulares listado de las expresiones regulares creadas
     * @param simbolos listado de simbolos creados
     */
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
    
    /**
     *
     * @return las producciones creadas
     */
    public List<Produccion> getProducciones() {
        return producciones;
    }

    /**
     * cambia las producciones del automata
     * @param producciones que se van a agregar
     */
    public void setProducciones(List<Produccion> producciones) {
        this.producciones = producciones;
    }

    private void extenderGramatica() {
        List<Simbolo> derechaExtension = new ArrayList();
        derechaExtension.add(this.producciones.get(0).getSimboloIzquierda());
        derechaExtension.add(new Simbolo("FinCadena",0,0));
        this.producciones.add(0, new Produccion(new Simbolo("InicioCadena",0,0), (ArrayList) derechaExtension));
        this.simbolos.addVariable(new Variable("InicioCadena",new Simbolo("InicioCadena",0,0)));
        Variable variable = new Variable("FinCadena", new Simbolo("FinCadena",0,0,10000));
        ((Simbolo)variable.getValor()).setTerminal(true);
        this.simbolos.addVariable(variable);
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

    /**
     * Metodo para calcular los primeros de cada simbolo en la tabla de simbolos
     */
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

    /**
     * metodo para mostrar los estados creados y su estructura
     */
    public void escribirEstados() {
        int estadoActual=0;
        String cadenaAcciones ="";
        for (Estado estado : this.automata.getEstados()) {
            cadenaAcciones += "Estado <"+(estadoActual++)+">{\n";
            for (Accion accion : estado.getAcciones()) {
                String tipo = "";
                if(accion instanceof Shift) tipo = "     Shift(";
                if(accion instanceof Reduce) tipo = "     Reduce(";
                if(accion instanceof GoTo) tipo = "     GoTo(";
                cadenaAcciones+=tipo+accion.getSimbolo()+","+accion.getValor()+")\n";
            }
            cadenaAcciones+="}\n";
        }
        System.out.println(cadenaAcciones);
    }

    /**
     * escribe los simbolos que existen
     */
    public void escribirSimbolos() {
        for (Variable variable : simbolos.getVariables()) {
            System.out.println(((Simbolo)variable.getValor()).toString());
        }
    }

    private Accion existeAccion(Accion accion, Estado estado) {
        for (Accion accionT : estado.getAcciones()) {
            if(accionT instanceof Reduce || accionT instanceof Shift){
                if(accionT.getSimbolo().equals(accion.getSimbolo())) return accionT;
            }
        }
        return null;
    }

    private void eliminarConflictoShiftReduce(Estado estadoNuevo, Accion accion) {
        int indice = -1;
        for (int i = 0; i < estadoNuevo.getAcciones().size(); i++) {
            if(estadoNuevo.getAcciones().get(i).getSimbolo().equals(accion.getSimbolo())){
                indice = i;
                break;
            }
        }
        if(indice != -1) estadoNuevo.getAcciones().set(indice, accion);
    }

    private boolean compararSimbolosPreAnalisisExactos(String[] simbolosPreAnalisis, String[] simbolosPreAnalisisT) {
        if(simbolosPreAnalisis.length == simbolosPreAnalisisT.length){
            for (String simbolo : simbolosPreAnalisis) {
                if(!Utilidades.existe(simbolo, simbolosPreAnalisisT)) return false;
            }
            return true;
        }else{
            return false;
        }
    }
    
    private int valorPrecedenciaDe(int produccion){
        int mayor = 0;
        if(tieneTerminales(produccion)){
            for (Simbolo simbolo : this.producciones.get(produccion).getSimbolosDerecha()) {
                if(((Simbolo)this.simbolos.getVariable(simbolo.getSimbolo()).getValor()).isTerminal()){
                    if(((Simbolo)this.simbolos.getVariable(simbolo.getSimbolo()).getValor()).getPrecedencia() > mayor) mayor = ((Simbolo)this.simbolos.getVariable(simbolo.getSimbolo()).getValor()).getPrecedencia();
                }
            }
        }else{
            mayor = 1000000;
        }
        return mayor;
    }

    /**
     *
     * @return el autoamta generado
     */
    public AutomataParser getAutomata() {
        return automata;
    }

    /**
     * cambia el automata de este generador
     * @param automata automata que deseamos cambiar
     */
    public void setAutomata(AutomataParser automata) {
        this.automata = automata;
    }

    /**
     *
     * @return la tabla de simbolos
     */
    public TablaDeSimbolos getSimbolos() {
        return simbolos;
    }

    /**
     * cambia la tabla de simbolso
     * @param simbolos simbolos a cambiar
     */
    public void setSimbolos(TablaDeSimbolos simbolos) {
        this.simbolos = simbolos;
    }

    private boolean tieneTerminales(int produccionId) {
        for (Simbolo simbolo : this.producciones.get(produccionId).getSimbolosDerecha()) {
            if(((Simbolo)this.simbolos.getVariable(simbolo.getSimbolo()).getValor()).isTerminal()) return true;
        }
        return false;
    }
    
    private void generarLALR(){
        //vamos a hacer un arreglo de arreglos
        List<List<Integer>> estadosSimilares = new ArrayList();
        List<Integer> estadosAnalizados = new ArrayList();
        List<Integer> temporal = new ArrayList();
        for (int i = 0; i < this.automata.getEstados().size(); i++) {
            if(!Utilidades.existe(i, estadosAnalizados)){
                temporal.add(i);
                for (int j = (i+1); j < this.automata.getEstados().size(); j++) {
                    if(!Utilidades.existe(j, estadosAnalizados)){
                        if(sonEstadosSimilares(temporal.get(0), j)){
                            temporal.add(j);
                        }
                    }
                }
            }
            for (Integer integer : temporal) {
                estadosAnalizados.add(integer);   
            }
            if(!temporal.isEmpty()) estadosSimilares.add(new ArrayList(temporal));
            temporal.clear();
        }
        for (List<Integer> estadoSimilar : estadosSimilares) {
            System.out.println("Similares {");
            for (Integer integer : estadoSimilar) {
                System.out.println("     "+integer);
            }
            System.out.println("}");
        }
        AutomataParser nuevoAutomata = new AutomataParser();
        boolean valorGeneral  = true;
        for (List<Integer> estadosUnir : estadosSimilares) {
            List<Estado> estadosEvaluar = new ArrayList();
            if(estadosUnir.size() > 1){
                for (int i = 0; i < estadosUnir.size(); i++) {
                    estadosEvaluar.add(this.automata.getEstados().get(estadosUnir.get(i)));
                }
                for (Variable variable : this.simbolos.getVariables()) {
                    Simbolo simbolo = (Simbolo) variable.getValor();
                    List<Accion> accionesEvaluar = new ArrayList();
                    for (Estado estado : estadosEvaluar) {
                        Accion accion = obtenerAccion(estado, simbolo);
                        if(accion != null) accionesEvaluar.add(accion);
                    }
                    if(!accionesEvaluar.isEmpty()){
                        if(valorGeneral) valorGeneral = verificarIncosistencias(accionesEvaluar, estadosSimilares);
                    }
                }
                if(valorGeneral){
                    Estado nuevoEstado = new Estado();
                    nuevoEstado.setAcciones(this.automata.getEstados().get(estadosUnir.get(0)).getAcciones());
                    nuevoAutomata.getEstados().add(nuevoEstado);
                }
            }else if(estadosUnir.size() == 1){
                Estado nuevoEstado = new Estado();
                nuevoEstado.setAcciones(this.automata.getEstados().get(estadosUnir.get(0)).getAcciones());
                nuevoAutomata.getEstados().add(nuevoEstado);
            }
        }
        if(valorGeneral){
            nuevoAutomata.setProducciones(this.automata.getProducciones());
            nuevoAutomata.setSimbolos(this.automata.getSimbolos());
            this.automata = nuevoAutomata;
        }
    }

    private boolean sonEstadosSimilares(Integer estadoBase, int estadoNuevo) {
        if(this.automata.getEstados().get(estadoBase).getCerraduras().size() == this.automata.getEstados().get(estadoNuevo).getCerraduras().size()){
            for (Cerradura cerradura : this.automata.getEstados().get(estadoBase).getCerraduras()) {
                if(existeCerradura(cerradura, this.automata.getEstados().get(estadoNuevo).getCerraduras()) == (-1)) return false;
            }
            return true;
        }
        return false;
    }

    private Accion obtenerAccion(Estado estado, Simbolo simbolo) {
        for (Accion accion : estado.getAcciones()) {
            if(simbolo.getSimbolo().equals(accion.getSimbolo())) return accion;
        }
        return null;
    }

    private boolean verificarIncosistencias(List<Accion> accionesEvaluar, List<List<Integer>> estadosSimilares) {
        boolean valor = true;
        Accion anterior = null;
        for (Accion accion : accionesEvaluar) {
            if(anterior == null){
                anterior = accion;
            }else{
                if(obtenerTipoAccion(accion) != obtenerTipoAccion(anterior)) valor = false;
            }
        }
        if(valor){
            if(accionesEvaluar.get(0) instanceof Reduce){
                anterior = null;
                for (Accion accion : accionesEvaluar) {
                    if(anterior == null){
                        anterior = accion;
                    }else{
                        if(accion.getValor() != anterior.getValor()) valor = false;
                    }
                }
            }else{
                int indiceEstadosEvaluar = -1;
                for (int i = 0; i < estadosSimilares.size(); i++) {
                    for (int j = 0; j < estadosSimilares.get(i).size(); j++) {
                        if(estadosSimilares.get(i).get(j) == accionesEvaluar.get(0).getValor()){
                            indiceEstadosEvaluar = i;
                            break;
                        }
                        if(indiceEstadosEvaluar != (-1)) break;
                    }
                }
                for (Accion accion : accionesEvaluar) {
                    if(!Utilidades.existe(accion.getValor(), estadosSimilares.get(indiceEstadosEvaluar))){
                        valor = false;
                        break;
                    }
                }
            }
        }
        return valor;
    }

    private int obtenerTipoAccion(Accion accion) {
        if(accion instanceof Shift){
            return 0;
        }else if(accion instanceof Reduce){
            return 1;
        }else if(accion instanceof GoTo){
            return 2;
        }else{
            return 3;
        }
    }
}
