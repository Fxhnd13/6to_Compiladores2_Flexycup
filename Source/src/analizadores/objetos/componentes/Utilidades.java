/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos.componentes;

import analizadores.objetos.componentes.parser.acciones.Accion;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase con métodos utiles para TODAS las demás clases
 * @author jose_
 */
public class Utilidades {

    /**
     * arreglo de letras
     */
    public static final char[] ARREGLO_LETRAS = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
                                                 'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};

    /**
     * arreglo de numeros
     */
    public static final char[] ARREGLO_NUMEROS = {'0','1','2','3','4','5','6','7','8','9','0','1','2','3','4','5','6','7','8','9'};
    
    /**
     * ordena un arreglo
     * @param arreglo arreglo a ordenar
     * @return arreglo ordenado
     */
    public static int[] ordenar(int[] arreglo){
        for (int i = 1; i < arreglo.length; i++) {
            for (int j = 0; j < arreglo.length-i; j++) {
                if(arreglo[j] > arreglo[j+1]){
                    int aux = arreglo[j];
                    arreglo[j] = arreglo[j+1];
                    arreglo[j+1] = aux;
                }
            }
        }
        return arreglo;
    }
    
    /**
     * ordena una lista
     * @param arreglo
     */
    public static void ordenar(List<Integer> arreglo){
        for (int i = 1; i < arreglo.size(); i++) {
            for (int j = 0; j < arreglo.size()-i; j++) {
                if(arreglo.get(j) > arreglo.get(j+1)){
                    int aux = arreglo.get(j);
                    arreglo.set(j, arreglo.get(j+1));
                    arreglo.set(j+1, aux);
                }
            }
        }
    }
    
    /**
     * verifica si existe un numero, dentro del arreglo
     * @param numero a buscar
     * @param arreglo donde buscar
     * @return si existe o no
     */
    public static boolean existe(int numero, int[] arreglo){
        boolean valor = false;
        for (int n : arreglo) {
            if(numero == n){
                valor = true;
                break;
            }
        }
        return valor;
    }
    
    /**
     * verifica si existe un numero, dentro del arreglo
     * @param numero a buscar
     * @param arreglo donde buscar
     * @return si existe o no
     */
    public static boolean existe(int numero, List<Integer> arreglo){
        boolean valor = false;
        for (int n : arreglo) {
            if(numero == n){
                valor = true;
                break;
            }
        }
        return valor;
    }
    
    /**
     *  escribe el arreglo en consola
     * @param arreglo que deseamos escribir
     * @return la cadena a escribir
     */
    public static String escribirArreglo(int[] arreglo){
        String cadena = "[";
        for (int i = 0; i < arreglo.length; i++) {
            cadena+=arreglo[i];
            if((i+1) < arreglo.length) cadena+=",";
        }
        return (cadena+"]");
    }
    
    /**
     *  escribe el arreglo en consola
     * @param arreglo que deseamos escribir
     * @return la cadena a escribir
     */
    public static String escribirArreglo(List<Integer> arreglo){
        String cadena = "[";
        for (int i = 0; i < arreglo.size(); i++) {
            cadena+=arreglo.get(i);
            if((i+1) < arreglo.size()) cadena+=",";
        }
        return (cadena+"]");
    }
    
    /**
     * obtenemos el arreglo de caracteres
     * @param rango rango de las letras que deseamos
     * @param opcion si queremos letras o numeros
     * @return
     */
    public static char[] obtenerCaracteres(String rango, int opcion){
        List<String> caracteres = new ArrayList();
        rango = rango.substring(1, rango.length()-1);
        char primero = rango.split("-")[0].charAt(0);
        char ultimo = rango.split("-")[1].charAt(0);
        boolean bandera = false;
        if(primero != ultimo){
            if(opcion == 0){
                primero = (String.valueOf(primero).toLowerCase().charAt(0));
                ultimo = (String.valueOf(ultimo).toLowerCase().charAt(0));
                for (int i = 0; i < ARREGLO_LETRAS.length; i++) {
                    if(ARREGLO_LETRAS[i] == primero) bandera = true;
                    while(bandera){
                        caracteres.add(String.valueOf(ARREGLO_LETRAS[i++]));
                        if(ARREGLO_LETRAS[i] == ultimo){
                            bandera = false;
                            caracteres.add(String.valueOf(ARREGLO_LETRAS[i]));
                        }
                    }
                    break;
                }
            }else{
                for (int i = 0; i < ARREGLO_NUMEROS.length; i++) {
                    if(ARREGLO_NUMEROS[i] == primero) bandera = true;
                    while(bandera){
                        caracteres.add(String.valueOf(ARREGLO_NUMEROS[i++]));
                        if(ARREGLO_NUMEROS[i] == ultimo){
                            bandera = false;
                            caracteres.add(String.valueOf(ARREGLO_NUMEROS[i]));
                        }
                    }
                    break;
                }
            }
        }else{
            caracteres.add(String.valueOf(primero));
        }
        char[] retorno = new char[caracteres.size()];
        for (int i = 0; i < caracteres.size(); i++) {
            retorno[i] = caracteres.get(i).charAt(0);
        }
        return retorno;
    }

    /**
     * verifica si existe el simbolo en el arreglo de simbolos
     * @param simbolo a buscar
     * @param arreglo en donde buscar
     * @return
     */
    public static boolean existe(String simbolo, List<String> arreglo) {
        boolean valor = false;
        for (String n : arreglo) {
            if(n.equals(simbolo)){
                valor = true;
                break;
            }
        }
        return valor;
    }

    /**
     * verifica si existe una cerradura dentro del arreglo de cerraduras
     * @param i limite
     * @param cerradurasEvaluadas arreglo a evaluar
     * @param indiceCerradurasEvaluadas cerradura a buscar
     * @return si existe o no
     */
    public static boolean existe(int i, int[] cerradurasEvaluadas, int indiceCerradurasEvaluadas) {
        for (int j = 0; j < indiceCerradurasEvaluadas; j++) {
            if(i == cerradurasEvaluadas[j]) return true;
        }
        return false;
    }

    /**
     * verifica si existe el simbolo en el arreglo de simbolos
     * @param simbolo a buscar
     * @param simbolosPreAnalisisT arreglo a evaluar
     * @return si existe  o no
     */
    public static boolean existe(String simbolo, String[] simbolosPreAnalisisT) {
        for (String n : simbolosPreAnalisisT) {
            if(n.equals(simbolo)) return true;
        }
        return false;
    }

    /**
     * obtener la accion al tener cierto simbolo de entrada
     * @param columnName simbolo de entrada
     * @param acciones acciones a evaluar
     * @return la cadena de la accion a realizar
     */
    public static String obtenerAccionDe(String columnName, List<Accion> acciones) {
        for (Accion accion : acciones) {
            if(columnName.equals(accion.getSimbolo())) return accion.toString();
        }
        return null;
    }
}
