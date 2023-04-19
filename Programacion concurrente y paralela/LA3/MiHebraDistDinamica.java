import java.util.concurrent.atomic.AtomicInteger;

class MiHebraDistDinamica extends Thread {
        long v[];
        int miId;
        AtomicInteger indice;

        MiHebraDistDinamica(long v[], int miId, AtomicInteger indice) {
            this.miId = miId;
            this.v = v;
            this.indice = indice;
        }

        public void run() {
            int pos = indice.getAndIncrement();
            while (pos < v.length) {
                if (esPrimo(v[pos])) {
                    System.out.println("Encontrado es primo: " + v[pos] + " En la hebra: " + miId);
                }
                pos = indice.getAndIncrement();
            }
        }
    boolean esPrimo( long num ) {
            boolean primo;
            if( num < 2 ) {
                primo = false;
            } else {
                primo = true;
                long i = 2;
                while( ( i < num )&&( primo ) ) {
                    primo = ( num % i != 0 );
                    i++;
                }
            }
            return( primo );
        }
}