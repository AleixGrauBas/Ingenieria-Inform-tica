class MiHebraPrimoDistPorBloques extends Thread {
    long v[];
    int miId;
    int numHebras;

    MiHebraPrimoDistPorBloques(long v[], int miId, int numHebras) {
        this.miId = miId;
        this.v = v;
        this.numHebras = numHebras;
    }

    public void run() {
        int tamB = (v.length + numHebras - 1) / numHebras;
        int iniElem = miId * tamB;
        int finElem = Math.min(iniElem + tamB, v.length);
        for (int i = iniElem; i < finElem; i++) {
            if (esPrimo(v[i]))
                System.out.println("Encontrado es primo: " + v[i] + " En la hebra: "+miId);
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