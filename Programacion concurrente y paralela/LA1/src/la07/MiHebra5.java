package la07;

import java.util.Hashtable;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MiHebra5 extends Thread{
    Vector<String> lineas;
    int miId;
    int numHebras;
    ConcurrentHashMap<String,AtomicInteger> table;
    public MiHebra5(int miId, int numHebras, Vector<String> linea, ConcurrentHashMap<String, AtomicInteger> table){
        this.miId=miId;
        this.numHebras=numHebras;
        this.lineas=linea;
        this.table=table;

    }

    public void run() {
        for (int i = miId; i < lineas.size(); i += numHebras) {
            String[] palabras = lineas.get(i).split("\\W+");
            for (int j = 0; j < palabras.length; j++) {
                String palabraActual = palabras[j].trim();
                if (palabraActual.length() > 0) {
                    EjemploPalabraMasUsada.contabilizaPalabra5(table, palabraActual);
                }
            }

        }
    }
}
