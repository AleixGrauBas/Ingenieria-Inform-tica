package practica1;

import javax.print.attribute.HashPrintServiceAttributeSet;
import java.util.*;

public class Practica1 {

    //Calcula la diferencia simétrica de s1 y s2 en s1. s2 contiene los elementos que están en ambos conjuntos
    public static <T> void difSimetrica (Set<T> s1, Set<T> s2) {
       // TODO: EJERCICIO 1
        Set<T> aux = new HashSet<>();
        aux.addAll(s2); //creamos un vector auxiliar con todos los elementos repetidos
        aux.retainAll(s1); // hacemos la interseccion de este con el vector unicos para saber cuales estan repetidos
        s1.addAll(s2); //añadimos todos los repetidos y por la propiedad de la funcion solo se añadiran los no repetidos
        s2.removeAll(s2); // vaciamos el conjunto repetidos
        s2.addAll(aux); //  añadimos los repetidos que hemos obtenido de la interseccion anterior
        s1.removeAll(aux); // le quitamos los repetidos al conjunto unicos
    }

    //Calcula y devuelve la diferencia simétrica de una colección de conjuntos
    public static <T> Set<T> difSimetricaCol (Collection<Set<T>> col) {
        // TODO: EJERCICIO 2
        Set<T> ret = new HashSet<>(); //Creamos un conjunto que devolveremos como resultado
        for (Set<T> x : col){
            Set<T> aux = new HashSet<>(); //creamos un aux al que se iran añadiendo los conjuntos
            aux.addAll(ret);
            difSimetrica(aux, x);
            ret.removeAll(ret); //en cada iteracion borramos el resultado entero y le añadimos la diferencia simetrica calculada en aux
            ret.addAll(aux);
        }
        return ret;
    }

    //Dado un iterador a una colleción de elementos, devolver un conjunto con los elementos que no se repiten
    //en la colección inicial deben quedar solo los elementos repetidos
    public static <T> Set<T> unicos (Iterator<T> it) {
       // TODO: EJERCICIO 3
        //suponemos que el iterador que nos pasan es el primero
        T aux;
        Set<T> rep = new HashSet<>(); //creamos un conjunto que devolveremos como resultado
        while (it.hasNext()){ //recorremos la coleccion añadiendo todos los elementos y luego borrando de uno en uno hasta quedaarnos solo con los repetidos
            aux = it.next();
            if(!rep.contains(aux)){
                rep.add(aux);
            }
            else{
                rep.remove(aux);
            }
        }
        return rep;
    }

    //Dada una colección y un conjunto de elementos del mismo tipo, devuelve cuántas veces ocurre el conjunto
    //en la colección (teniendo en cuenta que cada elemento de la colección solo puede ser usado una vez)
    public static <T> int  numOcurrecias  (Collection<T> col, Set<T> s) {
       // TODO: EJERCICIO 4
        int contador = 0;
        int max = 0;
        boolean pVez = false;
        if(col.size() == 0 || s.size() > col.size()){ // si la coleccion esta vacia o el conjunto es mas grande devolvemos 0
            return 0;
        } else {
            if(s.size() == col.size()){ //Si el tamaño de la coleccion y el conjunto es igual, comprobamos cuantas veces aparecen los elementos
                for(T i : s){
                    Iterator<T> it = col.iterator();
                    while (it.hasNext()){
                        if (it.next().equals(i)){
                            contador++;
                        }
                    }
                }
                contador = contador - (s.size()-1); // luego le restamos la cantidad de elementos que hay en el conjunto
                return contador;
            }
            else { // si el tamaño no es el mismo tenemos que mirar cuantass veces aparece cada elemento del conjunto y establecer el numero menor de veces que aparece un elemento de ese conjunto como maximo
                for (T i : s) {
                    contador = 0;
                    Iterator<T> it = col.iterator();
                    while (it.hasNext()){
                        if (it.next().equals(i)){
                            contador++;
                        }
                    }
                    if (!pVez) {
                        max = contador;
                        pVez = true;
                    }
                    if (contador < max){
                        max = contador;
                    }
                }
            }
        }
        return max;
    }

    //Dividir una colección de elementos en conjuntos, según el orden de los elementos en la colección
    public static <T> Collection<Set<T>>  split  (Collection<T> col) {
       // TODO: EJERCICIO 5
        Collection<Set<T>> resultado = new LinkedList<>(); //creamos una coleccion que devolveremos como resultado
        if (col.isEmpty()){ //En el caso de que la coleccion este vacia devolvemos un conjunto vacio
            Set<T> vacio = new HashSet<>();
            resultado.add(vacio);
            return resultado;
        }
        boolean primero = true;
        Set<T> aux2 = new HashSet<>();
        for (T x : col){ //vamos recorriendo los elementos de la coleccion
            if (primero){ //para crear el primer conjunto
                aux2.add(x);
                primero = false;
            } else{
                if (aux2.contains(x)){ //cuando el elemento ya esta en el conjunto creamos uno nuevo
                    resultado.add(aux2);
                    aux2 = new HashSet<>();
                }
                aux2.add(x);
            }
        }
        if (!aux2.isEmpty()) {
            resultado.add(aux2);
        }
        return resultado;
    }
}
