/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos.componentes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jose_
 */
public class Utilidades {

    public static final char[] ARREGLO_LETRAS = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
                                                 'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    public static final char[] ARREGLO_NUMEROS = {'0','1','2','3','4','5','6','7','8','9','0','1','2','3','4','5','6','7','8','9'};
    
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
    
    public static String escribirArreglo(int[] arreglo){
        String cadena = "[";
        for (int i = 0; i < arreglo.length; i++) {
            cadena+=arreglo[i];
            if((i+1) < arreglo.length) cadena+=",";
        }
        return (cadena+"]");
    }
    
    public static String escribirArreglo(List<Integer> arreglo){
        String cadena = "[";
        for (int i = 0; i < arreglo.size(); i++) {
            cadena+=arreglo.get(i);
            if((i+1) < arreglo.size()) cadena+=",";
        }
        return (cadena+"]");
    }
    
    public static char[] obtenerCaracteres(String rango, int opcion){
        List<String> caracteres = new ArrayList();
        rango = rango.substring(1, rango.length()-1);
        char primero = rango.split("-")[0].charAt(0);
        char ultimo = rango.split("-")[1].charAt(0);
        boolean bandera = false;
        if(primero != ultimo){
            if(opcion == 0){
                for (int i = 0; i < ARREGLO_LETRAS.length; i++) {
                    if(ARREGLO_LETRAS[i] == primero) bandera = true;
                    while(bandera){
                        caracteres.add(String.valueOf(ARREGLO_LETRAS[i++]));
                        if(ARREGLO_LETRAS[i] == ultimo){
                            bandera = false;
                            caracteres.add(String.valueOf(ARREGLO_LETRAS[i]));
                        }
                    }
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
}
