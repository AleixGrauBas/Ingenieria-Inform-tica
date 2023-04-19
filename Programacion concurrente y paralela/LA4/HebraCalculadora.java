package Ej2;

import javax.swing.*;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.atomic.AtomicBoolean;

import static Ej2.GUISecuenciaPrimos.*;

public class HebraCalculadora extends Thread{

     boolean fin=false;
     long num=0;
     ZonaIntercambio z;
    JTextField txMensajes;
    HebraCalculadora(JTextField  txfMensajes, ZonaIntercambio z){

        this.txMensajes=txfMensajes;
        this.z = z;
    }

    public void run() {
        try {
            while (!fin) {
                if (esPrimo(num)) {
                    sleep(z.getTiempo());
                    cambiaTexto(num);
                }
                num++;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void cambiaTexto(long num){
        SwingUtilities.invokeLater(
                new Runnable() {
                public void run() {
                    txMensajes.setText(Long.valueOf(num).toString());
                }
        });

    }

    public void cambiaValor(){
        if(!fin){
            fin=true;
        }else{
            fin=false;
            num=0;
        }
    }
}

