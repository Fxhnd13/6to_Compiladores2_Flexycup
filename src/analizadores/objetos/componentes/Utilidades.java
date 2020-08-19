/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analizadores.objetos.componentes;

import java.util.List;

/**
 *
 * @author jose_
 */
public class Utilidades {

    public static final char[] ARREGLO_LETRAS = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','v','w','x','y','z'};
    public static final char[] ARREGLO_NUMEROS = {'0','1','2','3','4','5','6','7','8','9'};
    
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
}
