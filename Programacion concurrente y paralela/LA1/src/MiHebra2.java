import java.util.Hashtable;
import java.util.Vector;

public class MiHebra2 extends Thread{
    Vector<String> lineas;
    int miId;
    int numHebras;
    Hashtable<String,Integer> table;
    public MiHebra2(int miId, int numHebras, Vector<String> linea, Hashtable<String,Integer> table){
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
                    EjemploPalabraMasUsada.contabilizaPalabra2(table, palabraActual);
                }
            }

        }
    }
}
