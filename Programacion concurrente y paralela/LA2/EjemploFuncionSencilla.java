// ============================================================================
class EjemploFuncionSencilla {
  // ============================================================================
  static class MiHebra1 extends Thread{
    int numHebras;
    int n;
    int miId;
    double vectorX[];
    double vectorY[];
    private MiHebra1(int numHebras,int n, int miId, double vectorX[], double vectorY[]){
      this.numHebras = numHebras;
      this.n = n;
      this.miId = miId;
      this.vectorX = vectorX;
      this.vectorY = vectorY;
    }
    public void run(){
      for( int i = miId; i < n; i += numHebras ) {
        vectorY[ i ] = evaluaFuncion( vectorX[ i ] );
      }
    }
  }
  static class MiHebra2 extends Thread{
    int numHebras;
    int n;
    int miId;
    double vectorX[];
    double vectorY[];
    private MiHebra2(int numHebras,int n, int miId, double vectorX[], double vectorY[]){
      this.numHebras = numHebras;
      this.n = n;
      this.miId = miId;
      this.vectorX = vectorX;
      this.vectorY = vectorY;
    }
    public void run(){
      for( int i = miId * (n/numHebras) ; i < (miId + 1) * (n/numHebras) ; i++ ) {
        vectorY[ i ] = evaluaFuncion( vectorX[ i ] );
      }
    }
  }
  // --------------------------------------------------------------------------
  public static void main(String args[]) {
    int n;
    int numHebras;

    if( args.length != 2 ) {
      System.err.println( "Uso: java programa <numHebras> <tamanyo>" );
      System.exit( -1 );
    }
    try {
      numHebras = Integer.parseInt( args[ 0 ] );
      n         = Integer.parseInt( args[ 1 ] );
    } catch( NumberFormatException ex ) {
      numHebras = -1;
      n         = -1;
      System.out.println( "ERROR: Argumentos numericos incorrectos." );
      System.exit( -1 );
    }
    long    t1, t2;
    double  sumaX, sumaY,sumaX1,sumaY1,sumaX2,sumaY2, ts, tc, tb;

    // Comprobacion y extraccion de los argumentos de entrada.


    // Crea los vectores.
    double vectorX[] = new double[ n ];
    double vectorY[] = new double[ n ];
    double vectorX1[] = new double[ n ];
    double vectorY1[] = new double[ n ];
    double vectorX2[] = new double[ n ];
    double vectorY2[] = new double[ n ];


    //
    // Implementacion secuencial (sin temporizar).
    //
    inicializaVectorX( vectorX );
    inicializaVectorY( vectorY );
    for( int i = 0; i < n; i++ ) {
      vectorY[ i ] = evaluaFuncion( vectorX[ i ] );
    }

    //
    // Implementacion secuencial.
    //
    inicializaVectorX( vectorX );
    inicializaVectorY( vectorY );
    t1 = System.nanoTime();
    for( int i = 0; i < n; i++ ) {
      vectorY[ i ] = evaluaFuncion( vectorX[ i ] );
    }
    t2 = System.nanoTime();
    ts = ( ( double ) ( t2 - t1 ) ) / 1.0e9;
    System.out.println( "Tiempo secuencial (seg.):                    " + ts );
    //// imprimeResultado( vectorX, vectorY );
    // Comprueba el resultado.
    sumaX = sumaVector( vectorX );
    sumaY = sumaVector( vectorY );
    System.out.println( "Suma del vector X:          " + sumaX );
    System.out.println( "Suma del vector Y:          " + sumaY );

    //
    // Implementacion paralela ciclica.
    //
    inicializaVectorX( vectorX1 );
    inicializaVectorY( vectorY1 );
    t1 = System.nanoTime();
    // Gestion de hebras para la implementacion paralela ciclica
    // ....
    MiHebra1 v[] = new MiHebra1[numHebras];
    for (int i = 0; i < numHebras; i++)
      v[i] = new MiHebra1(numHebras,n,i,vectorX1,vectorY1);
    for (int i = 0; i < numHebras; i++)
      v[i].start();
    for (int i = 0; i < numHebras; i++){
      try{
        v[i].join();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
    t2 = System.nanoTime();
    tc = ( ( double ) ( t2 - t1 ) ) / 1.0e9;
    System.out.println( "Tiempo paralela ciclica (seg.):              " + tc );
    System.out.println( "Incremento paralela ciclica:                 " + (ts/tc));
    //// imprimeResultado( vectorX, vectorY );
    // Comprueba el resultado.
    sumaX1 = sumaVector( vectorX1 );
    sumaY1 = sumaVector( vectorY1 );
    System.out.println( "Suma del vector X:          " + sumaX1 );
    System.out.println( "Suma del vector Y:          " + sumaY1 );
    //
    // Implementacion paralela por bloques.
    inicializaVectorX( vectorX2 );
    inicializaVectorY( vectorY2 );
    t1 = System.nanoTime();
    // Gestion de hebras para la implementacion paralela ciclica
    // ....
    MiHebra2 v1[] = new MiHebra2[numHebras];
    for (int i = 0; i < numHebras; i++)
      v1[i] = new MiHebra2(numHebras,n,i,vectorX2,vectorY2);
    for (int i = 0; i < numHebras; i++)
      v1[i].start();
    for (int i = 0; i < numHebras; i++){
      try{
        v1[i].join();
      } catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }
    t2 = System.nanoTime();
    tc = ( ( double ) ( t2 - t1 ) ) / 1.0e9;
    System.out.println( "Tiempo paralela por bloques (seg.):              " + tc );
    System.out.println( "Incremento paralela por bloques:                 " + (ts/tc));
    //// imprimeResultado( vectorX, vectorY );
    // Comprueba el resultado.
    sumaX2 = sumaVector( vectorX2 );
    sumaY2 = sumaVector( vectorY2 );
    System.out.println( "Suma del vector X:          " + sumaX2 );
    System.out.println( "Suma del vector Y:          " + sumaY2);
    // ....
    //

    System.out.println( "Fin del programa." );
  }

  // --------------------------------------------------------------------------

  static void inicializaVectorX( double vectorX[] ) {
    if( vectorX.length == 1 ) {
      vectorX[ 0 ] = 0.0;
    } else {
      for( int i = 0; i < vectorX.length; i++ ) {
        vectorX[ i ] = 10.0 * ( double ) i / ( ( double ) vectorX.length - 1 );
      }
    }
  }

  // --------------------------------------------------------------------------
  static void inicializaVectorY( double vectorY[] ) {
    for( int i = 0; i < vectorY.length; i++ ) {
      vectorY[ i ] = 0.0;
    }
  }

  // --------------------------------------------------------------------------
  static double sumaVector( double vector[] ) {
    double  suma = 0.0;
    for( int i = 0; i < vector.length; i++ ) {
      suma += vector[ i ];
    }
    return suma;
  }

  // --------------------------------------------------------------------------
  static double evaluaFuncion( double x ) {
    return 3.5 * x ;
  }

  // --------------------------------------------------------------------------
  static void imprimeVector( double vector[] ) {
    for( int i = 0; i < vector.length; i++ ) {
      System.out.println( " vector[ " + i + " ] = " + vector[ i ] );
    }
  }

  // --------------------------------------------------------------------------
  static void imprimeResultado( double vectorX[], double vectorY[] ) {
    for( int i = 0; i < Math.min( vectorX.length, vectorY.length ); i++ ) {
      System.out.println( "  i: " + i +
              "  x: " + vectorX[ i ] +
              "  y: " + vectorY[ i ] );
    }
  }

}
