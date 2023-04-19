import java.util.HashMap;
import java.util.Vector;

class MiHebra1 extends Thread{
    Vector<String> lineas;
    int miId;
    int numHebras;
    HashMap<String,Integer> mapa;
    public MiHebra1(int miId, int numHebras, Vector<String> linea, HashMap<String,Integer> mapa){
        this.miId=miId;
        this.numHebras=numHebras;
        this.lineas=linea;
        this.mapa=mapa;
    }
    public void run() {
         for (int i = miId; i < lineas.size(); i += numHebras) {
             String[] palabras = lineas.get(i).split("\\W+");
             for (int j = 0; j < palabras.length; j++) {
                 String palabraActual = palabras[j].trim();
                 if (palabraActual.length() > 0) {
                     EjemploPalabraMasUsada.contabilizaPalabra1(mapa, palabraActual);
                 }
             }
         }

    }
}