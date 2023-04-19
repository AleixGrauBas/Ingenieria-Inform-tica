package practica2;

import java.util.*;

public class Practica2 {

    //Dado un iterador al inicio de una lista y un elemento elem, debe borrar las ocurrencias de elem en la
    //lista que ocupen posiciones pares. El 0 se considerará par
    public static<T> void borrarEnPares(ListIterator<T> it, T elem) {
        // TODO: EJERCICIO 1
        int i = 0;
        while (it.hasNext()){
            if (it.next().equals(elem) && ((i % 2 == 0) || i == 0)){
                it.remove();
            }
            i++;
        }
    }

    /** Invierte el orden de los elmentos de una lista.
     *
     * @param iter Un iterador de la lista. Puede estar en cualqueir posición de la lista.
     */
    static public void invierte(ListIterator<String> iter) {
        // TODO: EJERCICIO 2
        while (iter.hasNext()){
            iter.next();
        }
        List<String> l = new LinkedList<>();
        while(iter.hasPrevious()){
            l.add(iter.previous());
        }
        ListIterator<String> iter2 = l.listIterator();
        while (iter.hasNext()){
            String aux = iter2.next();
            iter.next();
            iter.set(aux);
        }
    }

    //Busca la palabra y cambia la anterior palabra en la frase por **** (mismo número de * que
    //longitud de la palabra).
    public static int changeWord (List<List<String>> mytext, String word) {
        // TODO : EJERCICIO 3
        int contador = 0;
        for (int i = 0; i < mytext.size();i++){
            List<String> aux = new LinkedList<>();
            aux = mytext.get(i);
            for (int j = 0; j < aux.size(); j++){
                if (aux.get(j).equalsIgnoreCase(word) && j > 0){
                    String repeat = "*".repeat(aux.get(j-1).length());
                    aux.set(j-1, repeat);
                    contador++;
                }
            }
        }
        return contador;
    }
 

}
