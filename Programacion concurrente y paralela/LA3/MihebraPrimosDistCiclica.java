class MiHebraPrimoDistCiclica extends Thread {
    long v[];
    int miId;
    int numHebras;

    MiHebraPrimoDistCiclica(long v[], int miId, int numHebras) {
        this.miId = miId;
        this.v = v;
        this.numHebras = numHebras;
    }

    public void run() {
        for (int i = miId; i < v.length; i += numHebras) {
            if (esPrimo(v[i]))
                System.out.println("Encontrado es primo: " + v[i]);
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
