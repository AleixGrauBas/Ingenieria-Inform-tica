import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;


public class HeapsTest {
    private boolean checkHeap(int[] heap, boolean isMin) {
        for (int i = 0; i < heap.length / 2; i++) {
            int hi = i * 2 + 1;
            int hd = i * 2 + 2;

            if (isMin && hi < heap.length && heap[i]  > heap[hi])
                return false;

            if (isMin && hd < heap.length && heap[i] > heap[hd])
                return false;

            if (!isMin && hi < heap.length && heap[i] < heap[hi])
                return false;

            if (!isMin && hd < heap.length && heap[i] < heap[hd])
                return false;
        }

        return true;
    }

    static <T> List<List<T>> permutaciones(List<T> list, int limite) {
        List<List<T>> resultado = new ArrayList<>();

        int[] factoriales = new int[list.size() + 1];
        factoriales[0] = 1;
        for (int i = 1; i <= list.size(); i++) {
            factoriales[i] = factoriales[i - 1] * i;
        }

        if (limite < 0 || limite > factoriales[list.size()])
            limite = factoriales[list.size()];

        for (int i = 0; i < limite; i++) {
            List<T> caso = new ArrayList<>();
            List<T> aux = new LinkedList<>(list);

            int positionCode = i;
            for (int position = aux.size(); position > 0; position--) {
                int selected = positionCode / factoriales[position - 1];
                caso.add(aux.get(selected));
                positionCode = positionCode % factoriales[position - 1];
                aux.remove(selected);
            }

            if (i < limite)
                resultado.add(caso);
            else break;

        }

        return resultado;
    }


    static private boolean[] crearMascara(int talla) {
        return new boolean[talla];
    }

    static private boolean incrementaMascara(boolean mascara[]) {
        boolean propagar = false;
        int pos = 0;
        do {
            if (mascara[pos] == true) {
                mascara[pos] = false;
                propagar = true;
            } else {
                mascara[pos] = true;
                propagar = false;
            }
            pos++;
        } while (propagar && (pos < mascara.length));

        return (!propagar || pos != mascara.length);
    }

    static private <T> List<List<T>> todasSublistas(List<T> semilla) {
        List<List<T>> resultado = new LinkedList<>();

        boolean mascara[] = crearMascara(semilla.size());

        do {
            List<T> aux = new LinkedList<>();
            for (int i = 0; i < mascara.length; i++) {
                if (mascara[i])
                    aux.add(semilla.get(i));
            }
            resultado.add(aux);

        } while (incrementaMascara(mascara));

        return resultado;

    }

    static private <T> List<List<T>> permutacionesCompletas(List<T> semilla) {
        List<List<T>> resultado = new LinkedList<>();

        List<List<T>> sublistas = todasSublistas(semilla);
        for (List<T> sub : sublistas)
            resultado.addAll(permutaciones(sub, -1));


        return resultado;
    }

    static private <T> List<T> aplanar(List<List<T>> listas) {
        List<T> resultado = new LinkedList<>();

        for (List<T> l : listas)
            resultado.addAll(l);

        return resultado;
    }

    static private <T> List<List<T>> convertirMatriz(T[][] vec) {
        List<List<T>> resultado = new LinkedList<>();
        for (T[] elem : vec)
            resultado.add(Arrays.asList(elem));

        return resultado;
    }

    static private Integer[][] vSemillas = {{3, 5, 9, 10, 12}, {0, 4, 7}, {11, 15}, {0, 2,}, {6, 14, 20}};

    private static <T> List<List<T>> generarCasos(T[][] semillas) {
        List<List<T>> resultado = new ArrayList<>();
        List<List<T>> aux = convertirMatriz(semillas);

        List<List<List<T>>> permutaciones = permutacionesCompletas(aux);

        for (List<List<T>> perm : permutaciones)
            resultado.add(aplanar(perm));

        return resultado;
    }

    private static int[] prepararCaso(List<Integer> caso, boolean isMin) {
        int[] res = new int[caso.size()];
        for (int idx = 0; idx < caso.size(); idx++) {
            int elem = caso.get(idx);
            res[idx] = elem;
            int i = idx;
            int p = (idx - 1)/ 2;
            while ((i > 0) && (isMin ? (res[p] >  res[i]) : res[p] < res[i])) {
                int x = res[i];
                res[i] = res[p];
                res[p] = x;
                i = p;
                p = (p - 1) / 2;
            }
        }
        return res;
    }

    @Test
    public void maxHeapfyTest() {
        List<List<Integer>> casos = generarCasos(vSemillas);

        int cuenta = 1;
        for (List<Integer> caso : casos) {
            System.out.println("\nPRUEBA " + cuenta);
            int[] heap = new int[caso.size()];
            for (int i = 0; i < caso.size(); i++) heap[i] = caso.get(i);
            System.out.println(caso);
            System.out.println("RESULTADO OBTENIDO");
            Heaps.maxHeapify(heap);
            System.out.println(Heaps.toString(heap));

            if (!checkHeap(heap, false)) {
                System.out.println("No es un montículo a máximos");
                Assert.fail();
            }
            cuenta++;
        }
    }

    public boolean corrupt(int[] heap) {
        if (heap.length < 5)
            return false;
        else {
            // swap(1, 3)
            int x = heap[1];
            heap[1] = heap [3];
            heap[3] = x;
        }
        return true;
    }


    @Test
    public void typeOfHeapTest() {
        List<List<Integer>> casos = generarCasos(vSemillas);

        int cuenta = 1;
        for (List<Integer> caso : casos) {
            System.out.println("\nPRUEBA " + cuenta +  " :minHeap");
            int[] minHeap = prepararCaso(caso, true);
            System.out.println(Heaps.toString(minHeap));
            int esperado = 0;
            if (caso.size() != 0)
                esperado = -1;

            System.out.println("RESULTADO ESPERADO:\n  " + esperado);

            int obtenido = Heaps.typeOfHeap(minHeap);
            System.out.println("RESULTADO OBTENIDO");
            System.out.println("  " + obtenido);
            assertEquals(esperado, obtenido);

            cuenta++;

            if (esperado == 0)
                continue;

            if (corrupt(minHeap)) {
                System.out.println("\nPRUEBA " + cuenta + " :minHeap corrupto");

                System.out.println(Heaps.toString(minHeap));
                esperado = 0;

                System.out.println("RESULTADO ESPERADO:\n  " + esperado);

                obtenido = Heaps.typeOfHeap(minHeap);
                System.out.println("RESULTADO OBTENIDO");
                System.out.println("  " + obtenido);
                assertEquals(esperado, obtenido);

                cuenta++;
            }

            System.out.println("\nPRUEBA " + cuenta +  " :maxHeap");
            int[] maxHeap = prepararCaso(caso, false);
            System.out.println(Heaps.toString(maxHeap));
            esperado = 1;

            System.out.println("RESULTADO ESPERADO:\n  " + esperado);

            obtenido = Heaps.typeOfHeap(maxHeap);
            System.out.println("RESULTADO OBTENIDO");
            System.out.println("  " + obtenido);
            assertEquals(esperado, obtenido);

            cuenta++;

            if (corrupt(maxHeap)) {
                System.out.println("\nPRUEBA " + cuenta + " :maxHeap corrupto");

                System.out.println(Heaps.toString(maxHeap));
                esperado = 0;

                System.out.println("RESULTADO ESPERADO:\n  " + esperado);

                obtenido = Heaps.typeOfHeap(maxHeap);
                System.out.println("RESULTADO OBTENIDO");
                System.out.println("  " + obtenido);
                assertEquals(esperado, obtenido);

                cuenta++;
            }


        }
    }

    private int[] toArrary(List<Integer> l) {
        int resultado[] = new int[l.size()];

        for (int i = 0; i < l.size(); i++)
            resultado[i] = l.get(i);

        return resultado;
    }

    private boolean estaOrdenado(int v[]) {
        for (int i = 0; i < v.length-1; i++)
            if (v[i] > v[i + 1]) return false;

        return true;
    }



    @Test
    public void heapsortTest() {
        List<List<Integer>> casos = generarCasos(vSemillas);

        int cuenta = 1;
        for (List<Integer> caso : casos) {
            System.out.println("\nPRUEBA " + cuenta);
            int v[] = toArrary(caso);
            System.out.println("Vector: " + Arrays.toString(v));

            System.out.println("RESULTADO OBTENIDO");
            Heaps.heapsort(v);

            System.out.println("Vector: " + Arrays.toString(v));
            if (!estaOrdenado(v)){
                System.out.println("El vector no esta ordenado");
                Assert.fail();
            }

            cuenta++;
        }
    }
}
