package practica2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static practica2.Practica2.*;

public class TestPractica2 {
    private String[] ltest1={"uno"};
    private String[] ltest2={"uno", "uno"};
    private String[] ltest3={"uno", "uno", "uno"};
    private String[] ltest4={"uno", "dos", "tres", "cuatro", "cinco", "seis"};
    private String[] ltest5={"uno", "dos", "uno", "cuatro", "uno", "seis", "uno"};
    private String[] ltest6={"cero", "uno", "dos", "uno", "cuatro", "uno", "seis", "uno"};
    private String[] sol11={"uno"};
    private String[] sol12={"uno", "uno"};
    private String[] sol121={"uno"};
    private String[] sol13={"uno", "uno"};
    private String[] sol14={"uno", "dos", "tres", "cuatro", "cinco", "seis"};
    private String[] sol14a={"dos", "tres", "cuatro", "cinco", "seis"};
    private String[] sol14b={"uno", "dos", "tres", "cuatro", "seis"};
    private String[] sol15={"dos", "cuatro", "seis"};
    private String[] sol16={"uno", "uno", "dos", "dos", "tres", "tres", "cuatro", "cuatro", "cinco", "cinco", "seis", "seis"};

    private static List<List<String>> readAFile(String fileName) throws FileNotFoundException {
        List<List<String>> content = new ArrayList<>();

        File f = new File(fileName);
        Scanner scan = new Scanner(f);

        while (scan.hasNextLine()) {
            String linea = scan.nextLine();
            Scanner lineBust = new Scanner(linea);
            ArrayList<String> listaLinea=new ArrayList<>();
            while (lineBust.hasNext()) {
                listaLinea.add(lineBust.next());
            }
            lineBust.close();
            content.add(listaLinea);
            //content.add("\n");
        }
        scan.close();

        return content;
    }
    private static void printText(List<List<String>> texto) {
        ListIterator<List<String>> it= texto.listIterator();
        while (it.hasNext()) {
            ListIterator<String> it2=it.next().listIterator();
            while (it2.hasNext()) {
                System.out.print(it2.next()+" ");
            }
            System.out.println();
        }
    }


    @org.junit.Test
    public void borrarPosParesTest() {
        List<String> c = new LinkedList<String>();

        //TEst1: borrar de una lista vacía
        System.out.println("Entrada: "+c.toString());
        System.out.println("Salida esperada: "+c.toString());
        List<String> cprueba = new LinkedList<>(c);
        borrarEnPares(cprueba.listIterator(),"uno");
        System.out.println("Resultado obtenido: "+cprueba.toString());

        assertEquals("borrarPosPares: RESPUESTA INCORRECTA", c, cprueba);
        System.out.println("-----------------------------");

        //TEst2: borrar de una lista con un solo elemento. Opción 1: elemento está
        c= new LinkedList<>();
        for (int i=0; i<ltest1.length;i++)
            c.add(ltest1[i]);
        System.out.println("Entrada: "+c.toString());
        List<String> vacia=new LinkedList<>();
        System.out.println("Salida esperada: "+vacia.toString());
        cprueba = new LinkedList<>(c);
        borrarEnPares(cprueba.listIterator(),"uno");
        System.out.println("Resultado obtenido: "+cprueba.toString());

        assertEquals("borrarPosPares: RESPUESTA INCORRECTA", vacia, cprueba);
        System.out.println("-----------------------------");

        //TEst3: borrar de una lista con un solo elemento. Opción 2: elemento no está
        c= new LinkedList<>();
        for (int i=0; i<ltest1.length;i++)
            c.add(ltest1[i]);
        System.out.println("Entrada: "+c.toString());
        System.out.println("Salida esperada: "+c.toString());
        cprueba = new LinkedList<>(c);
        borrarEnPares(cprueba.listIterator(),"dos");
        System.out.println("Resultado obtenido: "+cprueba.toString());

        assertEquals("borrarPosPares 3: RESPUESTA INCORRECTA", c, cprueba);
        System.out.println("-----------------------------");

        //TEst5: borrar de una lista con dos elementos iguales
        c= new LinkedList<>();
        for (int i=0; i<ltest2.length;i++)
            c.add(ltest2[i]);
        System.out.println("Entrada: "+c.toString());
        List<String> res=new LinkedList<>(c);
        res.remove(0);
        System.out.println("Salida esperada: "+res.toString());
        cprueba = new LinkedList<>(c);
        borrarEnPares(cprueba.listIterator(),"uno");
        System.out.println("Resultado obtenido: "+cprueba.toString());
        assertEquals("borrarPosPares: RESPUESTA INCORRECTA 5", res, cprueba);
        System.out.println("-----------------------------");


        //TEst6: borrar de una lista con tres elementos iguales. El elemento está.
        c= new LinkedList<>();
        for (int i=0; i<ltest3.length;i++)
            c.add(ltest3[i]);
        System.out.println("Entrada: "+c.toString());
        res=new LinkedList<>();
        res.add("uno");
        System.out.println("Salida esperada: "+res.toString());
        cprueba = new LinkedList<>(c);
        borrarEnPares(cprueba.listIterator(),"uno");
        System.out.println("Resultado obtenido: "+cprueba.toString());

        assertEquals("borrarPosPares: RESPUESTA INCORRECTA", res, cprueba);
        System.out.println("-----------------------------");

        //TEst7: borrar de una lista con tres elementos iguales. El elemeto no esta
        c= new LinkedList<>();
        for (int i=0; i<ltest3.length;i++)
            c.add(ltest3[i]);
        System.out.println("Entrada: "+c.toString());
        System.out.println("Salida esperada: "+c.toString());
        cprueba = new LinkedList<>(c);
        borrarEnPares(cprueba.listIterator(),"dos");
        System.out.println("Resultado obtenido (7): "+cprueba.toString());

        assertEquals("borrarPosPares: RESPUESTA INCORRECTA", c, cprueba);
        System.out.println("-----------------------------");

        //TEst8: borrar de una lista, diferentes opciones - elemento no esta
        c= new LinkedList<>();
        for (int i=0; i<ltest4.length;i++)
            c.add(ltest4[i]);
        System.out.println("Entrada: "+c.toString());
        res=new LinkedList<>();
        for (int i=0; i<sol14.length; i++)
            res.add(sol14[i]);
        System.out.println("Salida esperada: "+res.toString());
        cprueba = new LinkedList<>(c);
        borrarEnPares(cprueba.listIterator(),"siete");
        System.out.println("Resultado obtenido: "+cprueba.toString());

        assertEquals("borrarPosPares: RESPUESTA INCORRECTA", res, cprueba);
        System.out.println("-----------------------------");

        //TEst9: borrar de una lista, diferentes opciones. Elemento en posición impar
        c= new LinkedList<>();
        for (int i=0; i<ltest4.length;i++)
            c.add(ltest4[i]);
        System.out.println("Entrada: "+c.toString());
        res=new LinkedList<>();
        for (int i=0; i<sol14.length; i++)
            res.add(sol14[i]);
        System.out.println("Salida esperada: "+res.toString());
        cprueba = new LinkedList<>(c);
        borrarEnPares(cprueba.listIterator(),"dos");
        System.out.println("Resultado obtenido: "+cprueba.toString());

        assertEquals("borrarPosPares: RESPUESTA INCORRECTA", res, cprueba);
        System.out.println("-----------------------------");

        //TEst9: borrar de una lista, diferentes opciones. Elemento en posición impar
        c= new LinkedList<>();
        for (int i=0; i<ltest4.length;i++)
            c.add(ltest4[i]);
        System.out.println("Entrada: "+c.toString());
        res=new LinkedList<>();
        for (int i=0; i<sol14.length; i++)
            res.add(sol14[i]);
        System.out.println("Salida esperada: "+res.toString());
        cprueba = new LinkedList<>(c);
        borrarEnPares(cprueba.listIterator(),"seis");
        System.out.println("Resultado obtenido: "+cprueba.toString());

        assertEquals("borrarPosPares: RESPUESTA INCORRECTA", res, cprueba);
        System.out.println("-----------------------------");

        //TEst10: borrar de una lista, diferentes opciones. Borrar el primero
        c= new LinkedList<>();
        for (int i=0; i<ltest4.length;i++)
            c.add(ltest4[i]);
        System.out.println("Entrada: "+c.toString());
        res=new LinkedList<>();
        for (int i=0; i<sol14a.length; i++)
            res.add(sol14a[i]);
        System.out.println("Salida esperada: "+res.toString());
        cprueba = new LinkedList<>(c);
        borrarEnPares(cprueba.listIterator(),"uno");
        System.out.println("Resultado obtenido: "+cprueba.toString());

        assertEquals("borrarPosPares: RESPUESTA INCORRECTA", res, cprueba);
        System.out.println("-----------------------------");

        //TEst11: borrar de una lista, diferentes opciones. Borrar en otra posición
        c= new LinkedList<>();
        for (int i=0; i<ltest4.length;i++)
            c.add(ltest4[i]);
        System.out.println("Entrada: "+c.toString());
        res=new LinkedList<>();
        for (int i=0; i<sol14b.length; i++)
            res.add(sol14b[i]);
        System.out.println("Salida esperada: "+res.toString());
        cprueba = new LinkedList<>(c);
        borrarEnPares(cprueba.listIterator(),"cinco");
        System.out.println("Resultado obtenido: "+cprueba.toString());

        assertEquals("borrarPosPares: RESPUESTA INCORRECTA", res, cprueba);
        System.out.println("-----------------------------");

        //TEst11: borrar de una lista, diferentes opciones. Borrar en otra posición
        c= new LinkedList<>();
        for (int i=0; i<ltest5.length;i++)
            c.add(ltest5[i]);
        System.out.println("Entrada: "+c.toString());
        res=new LinkedList<>();
        for (int i=0; i<sol15.length; i++)
            res.add(sol15[i]);
        System.out.println("Salida esperada: "+res.toString());
        cprueba = new LinkedList<>(c);
        borrarEnPares(cprueba.listIterator(),"uno");
        System.out.println("Resultado obtenido: "+cprueba.toString());

        assertEquals("borrarPosPares: RESPUESTA INCORRECTA", res, cprueba);
        System.out.println("-----------------------------");

        //TEst11: borrar de una lista, diferentes opciones. Borrar en otra posición
        c= new LinkedList<>();
        for (int i=0; i<ltest6.length;i++)
            c.add(ltest6[i]);
        System.out.println("Entrada: "+c.toString());
        res=new LinkedList<>(c);

        System.out.println("Salida esperada: "+res.toString());
        cprueba = new LinkedList<>(c);
        borrarEnPares(cprueba.listIterator(),"uno");
        System.out.println("Resultado obtenido: "+cprueba.toString());

        assertEquals("borrarPosPares: RESPUESTA INCORRECTA", res, cprueba);
        System.out.println("-----------------------------");
    }


    @org.junit.Test
    public void changeWordTest() {

        //Test1: empty file
        List<List<String>> texto= null;
        String filename= "texto1";
        int m=0;
        try {
            texto = readAFile(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<List<String>> res= new LinkedList<>();

        List<List<String>> probar=new LinkedList<>(texto);

        int n=changeWord(probar,"any");
        System.out.println("Salida esperada: " + res.toString());
        System.out.println(m+" cambios");
        System.out.println("Resultado obtenido: " + probar.toString());
        System.out.println(n+" cambios");

        assertEquals("changeWord: RESPUESTA INCORRECTA", res, probar);
        assertEquals("changeWord: RESPUESTA INCORRECTA", m, n);
        System.out.println("-----------------------------");

        texto= null;
        filename= "texto2";
        try {
            texto = readAFile(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String output= "out2";
        m=9;
        res=null;
        try {
            res = readAFile(output);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        probar=new LinkedList<>(texto);
        //probar.addAll(texto);
        n=changeWord(probar,"not");
        System.out.println("Salida esperada: " );
        printText(res);
        System.out.println(m+" cambios");
        System.out.println("---------------");
        System.out.println("Resultado obtenido: ");
        printText(probar);

        assertEquals("changeWord: RESPUESTA INCORRECTA", res, probar);
        assertEquals("changeWord: RESPUESTA INCORRECTA", m, n);
        System.out.println("-----------------------------------------------");

        texto= null;
        filename= "texto2";
        try {
            texto = readAFile(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        output= "out22";
        m=0;
        res=new LinkedList<>();
        try {
            res = readAFile(output);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        probar=new LinkedList<>(texto);
        n=changeWord(probar,"any");
        System.out.println("Salida esperada: " );
        printText(res);
        System.out.println(m+" cambios");
        System.out.println("---------------");
        System.out.println("Resultado obtenido: ");
        printText(probar);
        System.out.println(n+" cambios");

        assertEquals("changeWord: RESPUESTA INCORRECTA", res, probar);
        assertEquals("changeWord: RESPUESTA INCORRECTA", m, n);
        System.out.println("-----------------------------");

        texto= null;
        filename= "texto2";
        try {
            texto = readAFile(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        output= "out23";
        m=0;
        res=new LinkedList<>();
        try {
            res = readAFile(output);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        probar=new LinkedList<>(texto);
        n=changeWord(probar,"I");
        System.out.println("Salida esperada: " );
        printText(res);
        System.out.println(m+" cambios");
        System.out.println("---------------");
        System.out.println("Resultado obtenido: ");
        printText(probar);
        System.out.println(n+" cambios");

        assertEquals("changeWord: RESPUESTA INCORRECTA", res, probar);
        assertEquals("changeWord: RESPUESTA INCORRECTA", m, n);
        System.out.println("-----------------------------");

        texto= null;
        filename= "texto2";
        try {
            texto = readAFile(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        output= "out24";
        m=7;
        res=new LinkedList<>();
        try {
            res = readAFile(output);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        probar=new LinkedList<>(texto);
        n=changeWord(probar,"a");
        System.out.println("Salida esperada: " );
        printText(res);
        System.out.println(m+" cambios");
        System.out.println("---------------");
        System.out.println("Resultado obtenido: ");
        printText(probar);
        System.out.println(n+" cambios");

        assertEquals("changeWord: RESPUESTA INCORRECTA", res, probar);
        assertEquals("changeWord: RESPUESTA INCORRECTA", m, n);
        System.out.println("-----------------------------");

        texto= null;
        filename= "texto3";
        try {
            texto = readAFile(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        output= "out3";
        m=6;
        res=new LinkedList<>();
        try {
            res = readAFile(output);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        probar=new LinkedList<>(texto);
        n=changeWord(probar,"not");
        System.out.println("Salida esperada: " );
        printText(res);
        System.out.println(m+" cambios");
        System.out.println("---------------");
        System.out.println("Resultado obtenido: ");
        printText(probar);
        System.out.println(n+" cambios");

        assertEquals("changeWord: RESPUESTA INCORRECTA", res, probar);
        assertEquals("changeWord: RESPUESTA INCORRECTA", m, n);
        System.out.println("-----------------------------");

        texto= null;
        filename= "texto4";
        try {
            texto = readAFile(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        output= "out4";
        m=10;
        res=new LinkedList<>();
        try {
            res = readAFile(output);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        probar=new LinkedList<>(texto);
        n=changeWord(probar,"not");
        System.out.println("Salida esperada: " );
        printText(res);
        System.out.println(m+" cambios");
        System.out.println("---------------");
        System.out.println("Resultado obtenido: ");
        printText(probar);
        System.out.println(n+" cambios");

        assertEquals("changeWord: RESPUESTA INCORRECTA", res, probar);
        assertEquals("changeWord: RESPUESTA INCORRECTA", m, n);
        System.out.println("-----------------------------");


        texto= null;
        filename= "texto5";
        m=2;
        try {
            texto = readAFile(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        output= "out5";
        res=new LinkedList<>();
        try {
            res = readAFile(output);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        probar=new LinkedList<>(texto);
        n=changeWord(probar,"not");
        System.out.println("Salida esperada: " );
        printText(res);
        System.out.println(m+" cambios");
        System.out.println("---------------");
        System.out.println("Resultado obtenido: ");
        printText(probar);
        System.out.println(n+" cambios");

        assertEquals("changeWord: RESPUESTA INCORRECTA", res, probar);
        assertEquals("changeWord: RESPUESTA INCORRECTA", m, n);
        System.out.println("-----------------------------");

    }

    static private boolean[] crearMascara(int talla){
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
            for(int i= 0; i < mascara.length; i++) {
                if (mascara[i])
                    aux.add(semilla.get(i));
            }
            resultado.add(aux);

        } while(incrementaMascara(mascara));

        return resultado;

    }

    static private <T> List<List<T>> permutaciones(List<T> vec) {
        List<List<T>> resultado = new LinkedList<>();

        if (vec.size() > 0) {
            List<T> aux = new LinkedList<>();
            aux.add(vec.get(0));
            resultado.add(aux);

            for (int i = 1; i < vec.size(); i++) {
                while (resultado.get(0).size() == i) {
                    for (int k = 0; k <= resultado.get(0).size(); k++) {
                        aux = new LinkedList<>(resultado.get(0));
                        aux.add(k, vec.get(i));
                        resultado.add(aux);
                    }
                    resultado.remove(0);
                }
            }
        } else
            resultado.add(new LinkedList<>());
        return resultado;
    }

    static private <T> List<List<T>> permutacionesCompletas(List<T> semilla){
        List<List<T>> resultado = new LinkedList<>();

        List<List<T>> sublistas = todasSublistas(semilla);
        for(List<T> sub: sublistas)
            resultado.addAll(permutaciones(sub));


        return resultado;
    }


    private String[][] vSemillasInvierteTest = {{"A"}, {"B", "C"}, {"D", "E", "F"}, {"A", "B", "D"}};

    static private <T> List<List<T>> convertirMatriz(T[][] vec)
    {
        List<List<T>> resultado = new LinkedList<>();
        for (T[] elem: vec)
            resultado.add(Arrays.asList(elem));

        return resultado;
    }

    static private List<String> invertirTest(List<String> l) {
        List<String> resultado = new LinkedList<>();
        for (String elem: l)
            resultado.add(0,elem);

        return resultado;
    }

    static private <T> List<T> aplanar(List<List<T>> listas) {
        List<T> resultado = new LinkedList<>();

        for(List<T> l: listas)
            resultado.addAll(l);

        return resultado;
    }
    @org.junit.Test
    public void invierteTest () {
        List<List<String>> aux = convertirMatriz(vSemillasInvierteTest);

        List<List<List<String>>> permutaciones = permutacionesCompletas(aux);

        int cuenta = 0;
        for (List<List<String>> perm: permutaciones) {
            List<String> caso = aplanar(perm);
            List<String> esperado = invertirTest(caso);

            for(int i = 0; i <= caso.size(); i++) {
                List<String> actual = new LinkedList<>(caso);
                ListIterator<String> iter = actual.listIterator(i);

                System.out.println("\nPrueba " + cuenta);
                System.out.println("Entrada");
                System.out.println("  Iterador de  " + actual + " en posicion " + iter.nextIndex());
                System.out.println("Salida esperada");
                System.out.println("  " + esperado);

                invierte(iter);
                System.out.println("Salida:");
                System.out.println("  " + actual);

                assertEquals(esperado, actual);
                cuenta++;
            }
        }

        System.out.println("Se han probado " + cuenta + " casos\nFIN");
    }

}
