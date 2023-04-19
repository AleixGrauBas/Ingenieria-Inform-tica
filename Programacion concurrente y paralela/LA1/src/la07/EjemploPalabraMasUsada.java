package la07;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

// ============================================================================
class EjemploPalabraMasUsada {
// ============================================================================

  // -------------------------------------------------------------------------
  public static void main( String args[] ) {
    long                     t1, t2;
    double                   tt, tc;
    int                      numHebras;
    String                   nombreFichero, palabraActual;
    Vector<String>           vectorLineas;
    HashMap<String,Integer>  hmCuentaPalabras;
    numHebras = 4;
    nombreFichero = "C:\\Users\\ferra\\IdeaProjects\\PCP\\src\\la07\\f0.txt";
  /*  // Comprobacion y extraccion de los argumentos de entrada.
    if( args.length != 2 ) {
      System.err.println( "Uso: java programa <numHebras> <fichero>" );
      System.exit( -1 );
    }
    try {
      numHebras     = Integer.parseInt( args[ 0 ] );
      nombreFichero = args[ 1 ];
    } catch( NumberFormatException ex ) {
      numHebras = -1;
      nombreFichero = "";
      System.out.println( "ERROR: Argumentos numericos incorrectos." );
      System.exit( -1 );
    }*/

    // Lectura y carga de lineas en "vectorLineas".
    vectorLineas = readFile( nombreFichero );
    System.out.println( "Numero de lineas leidas: " + vectorLineas.size() );
    System.out.println();

    //
    // Implementacion secuencial sin temporizar.
    //
    hmCuentaPalabras = new HashMap<String,Integer>( 1000, 0.75F );
    for( int i = 0; i < vectorLineas.size(); i++ ) {
      // Procesa la linea "i".
      String[] palabras = vectorLineas.get( i ).split( "\\W+" );
      for( int j = 0; j < palabras.length; j++ ) {
        // Procesa cada palabra de la linea "i", si es distinta de blanco.
        palabraActual = palabras[ j ].trim();
        if( palabraActual.length() > 0 ) {
          contabilizaPalabra( hmCuentaPalabras, palabraActual );
        }
      }
    }

    //
    // Implementacion secuencial.
    //
    t1 = System.nanoTime();
    hmCuentaPalabras = new HashMap<String,Integer>( 1000, 0.75F );
    for( int i = 0; i < vectorLineas.size(); i++ ) {
      // Procesa la linea "i".
      String[] palabras = vectorLineas.get( i ).split( "\\W+" );
      for( int j = 0; j < palabras.length; j++ ) {
        // Procesa cada palabra de la linea "i", si es distinta de blanco.
        palabraActual = palabras[ j ].trim();
        if( palabraActual.length() > 0 ) {
          contabilizaPalabra( hmCuentaPalabras, palabraActual );
        }
      }
    }
    t2 = System.nanoTime();
    tc = ( ( double ) ( t2 - t1 ) ) / 1.0e9;
    System.out.print( "Implementacion secuencial: " );
    imprimePalabraMasUsadaYVeces( hmCuentaPalabras );
    System.out.println( " Tiempo(s): " + tc );
    System.out.println( "Num. elems. tabla hash: " + hmCuentaPalabras.size() );
    System.out.println();


    //
    // Implementacion paralela 1: Uso de synchronizedMap.
    //
    t1 = System.nanoTime();

    Map<String, Integer> maCuentaPalabras = Collections.synchronizedMap(new HashMap<String,Integer>());

    MiHebra1[] v = new MiHebra1[numHebras];
    for(int i=0;i<numHebras;i++){
      v[i] = new MiHebra1(i,numHebras,vectorLineas,maCuentaPalabras); v[i].start();
    }
    for (int i = 0; i < v.length; i++){
      try {
        v[i].join();
      }catch (InterruptedException e) {
        throw new RuntimeException(e);
      }}
    t2 = System.nanoTime();
    tt = ( ( double ) ( t2 - t1 ) ) / 1.0e9;
    System.out.print( "Implementacion paralela 1: " );
    imprimePalabraMasUsadaYVeces(maCuentaPalabras);
    System.out.println( " Tiempo(s): " + tt + " , Incremento " +tc/tt); System.out.println( "Num. elems. tabla hash: " + maCuentaPalabras.size() );
    System.out.println();


    //
    // Implementacion paralela 2: Uso de Hashtable.
    //
    // ...
    t1 = System.nanoTime();
    Hashtable<String,Integer> hmCuentaPalabras2 = new Hashtable<String,Integer>(1000, 0.75F);
    MiHebra2 v2[]=new MiHebra2[numHebras];
    for(int i=0;i<numHebras;i++){
      v2[i]=new MiHebra2(i,numHebras,vectorLineas,hmCuentaPalabras2);
      v2[i].start();
    }
    for (int i = 0; i < v2.length; i++){
      try {
        v2[i].join();
      }catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }

    t2 = System.nanoTime();
    tt = ( ( double ) ( t2 - t1 ) ) / 1.0e9;
    System.out.print( "Implementacion paralela 2: " );
    imprimePalabraMasUsadaYVeces( hmCuentaPalabras2 );
    System.out.println( " Tiempo(s): " + tt  + " , Incremento " +tc/tt);
    System.out.println( "Num. elems. tabla hash: " + hmCuentaPalabras2.size() );
    System.out.println();

    //
    // Implementacion paralela 3: Uso de ConcurrentHashMap
    //
    // ...
    t1 = System.nanoTime();
    ConcurrentHashMap<String,Integer> hmCuentaPalabras3 = new ConcurrentHashMap<>(1000,0.75F);
    MiHebra3 v3[]=new MiHebra3[numHebras];
    for(int i=0;i<numHebras;i++){
      v3[i]=new MiHebra3(i,numHebras,vectorLineas,hmCuentaPalabras3);
      v3[i].start();
    }
    for (int i = 0; i < v3.length; i++){
      try {
        v3[i].join();
      }catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }

    t2 = System.nanoTime();
    tt = ( ( double ) ( t2 - t1 ) ) / 1.0e9;
    System.out.print( "Implementacion paralela 3: " );
    imprimePalabraMasUsadaYVeces( hmCuentaPalabras3 );
    System.out.println( " Tiempo(s): " + tt  + " , Incremento " +tc/tt);
    System.out.println( "Num. elems. tabla hash: " + hmCuentaPalabras3.size() );
    System.out.println();
    //
    // Implementacion paralela 4: Uso de ConcurrentHashMap 
    //
    // ...
    t1 = System.nanoTime();
    ConcurrentHashMap<String,Integer> hmCuentaPalabras4 = new ConcurrentHashMap<>(1000, 0.75F);
    MiHebra4 v4[] = new MiHebra4[numHebras];
    for(int i = 0;i < numHebras;i++){
      v4[i]=new MiHebra4(i,numHebras,vectorLineas,hmCuentaPalabras4);
      v4[i].start();
    }
    for (int i = 0; i < v4.length; i++){
      try {
        v4[i].join();
      }catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }

    t2 = System.nanoTime();
    tt = ( ( double ) ( t2 - t1 ) ) / 1.0e9;
    System.out.print( "Implementacion paralela 4: " );
    imprimePalabraMasUsadaYVeces( hmCuentaPalabras4 );
    System.out.println( " Tiempo(s): " + tt  + " , Incremento " +tc/tt);
    System.out.println( "Num. elems. tabla hash: " + hmCuentaPalabras4.size() );
    System.out.println();
    //
    // Implementacion paralela 5: Uso de ConcurrentHashMap
    //
    // ...
    t1 = System.nanoTime();
    ConcurrentHashMap<String, AtomicInteger> hmCuentaPalabras5 = new ConcurrentHashMap<>(1000, 0.75F);
    MiHebra5 v5[] = new MiHebra5[numHebras];
    for(int i = 0;i < numHebras;i++){
      v5[i] = new MiHebra5(i,numHebras,vectorLineas,hmCuentaPalabras5);
      v5[i].start();
    }
    for (int i = 0; i < v5.length; i++){
      try {
        v5[i].join();
      }catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }

    t2 = System.nanoTime();
    tt = ( ( double ) ( t2 - t1 ) ) / 1.0e9;
    System.out.print( "Implementacion paralela 5: " );
    imprimePalabraMasUsadaYVeces1( hmCuentaPalabras5 );
    System.out.println( " Tiempo(s): " + tt  + " , Incremento " +tc/tt);
    System.out.println( "Num. elems. tabla hash: " + hmCuentaPalabras5.size() );
    System.out.println();
    //
    // Implementacion paralela 6: Uso de ConcurrentHashMap 
    //
    // ...
    t1 = System.nanoTime();
    ConcurrentHashMap<String, AtomicInteger> hmCuentaPalabras6 = new ConcurrentHashMap<>(1000, 0.75F, 256);
    MiHebra5 v6[] = new MiHebra5[numHebras];
    for(int i = 0;i < numHebras;i++){
      v6[i] = new MiHebra5(i,numHebras,vectorLineas,hmCuentaPalabras6);
      v6[i].start();
    }
    for (int i = 0; i < v5.length; i++){
      try {
        v6[i].join();
      }catch (InterruptedException e) {
        throw new RuntimeException(e);
      }
    }

    t2 = System.nanoTime();
    tt = ( ( double ) ( t2 - t1 ) ) / 1.0e9;
    System.out.print( "Implementacion paralela 6: " );
    imprimePalabraMasUsadaYVeces1( hmCuentaPalabras6 );
    System.out.println( " Tiempo(s): " + tt  + " , Incremento " +tc/tt);
    System.out.println( "Num. elems. tabla hash: " + hmCuentaPalabras6.size() );
    System.out.println();

     //Implementacion paralela 7: Uso de Streams
     t1 = System.nanoTime();
     Map<String,Long> stCuentaPalabras = vectorLineas.parallelStream()
                                           .filter( s -> s != null )
                                           .map( s -> s.split( "\\W+" ) )
                                           .flatMap( Arrays::stream )
                                           .map( String::trim )
                                           .filter( s -> (s.length() > 0) )
                                           .collect( groupingBy (s -> s, counting()));
     t2 = System.nanoTime();
    tt = ( ( double ) ( t2 - t1 ) ) / 1.0e9;
    System.out.print( "Implementacion paralela 7: " );
    imprimePalabraMasUsadaYVeces2( stCuentaPalabras );
    System.out.println( " Tiempo(s): " + tt  + " , Incremento " +tc/tt);
    System.out.println( "Num. elems. tabla hash: " + stCuentaPalabras.size() );
    System.out.println();
    System.out.println( "Fin de programa." );
  }

  // -------------------------------------------------------------------------
  public static Vector<String> readFile( String fileName ) {
    BufferedReader br; 
    String         linea;
    Vector<String> data = new Vector<String>();

    try {
      br = new BufferedReader( new FileReader( fileName ) );
      while( ( linea = br.readLine() ) != null ) {
        //// System.out.println( "Leida linea: " + linea );
        data.add( linea );
      }
      br.close(); 
    } catch( FileNotFoundException ex ) {
      ex.printStackTrace();
    } catch( IOException ex ) {
      ex.printStackTrace();
    }
    return data;
  }

  // -------------------------------------------------------------------------
  public static void contabilizaPalabra( 
                         HashMap<String,Integer> cuentaPalabras,
                         String palabra ) {
    Integer numVeces = cuentaPalabras.get( palabra );
    if( numVeces != null ) {
      cuentaPalabras.put( palabra, numVeces+1 );
    } else {
      cuentaPalabras.put( palabra, 1 );
    }
  }
  public static void contabilizaPalabra1(
          Map<String,Integer> cuentaPalabras,
          String palabra ) {
    synchronized (cuentaPalabras) {
      Integer numVeces = cuentaPalabras.get(palabra);
      if (numVeces != null) {
        cuentaPalabras.put(palabra, numVeces + 1);
      } else {
        cuentaPalabras.put(palabra, 1);
      }
    }
  }
  public static void contabilizaPalabra2(
          Hashtable<String,Integer> cuentaPalabras,
          String palabra ) {
    synchronized (cuentaPalabras) {
      Integer numVeces = cuentaPalabras.get(palabra);
      if (numVeces != null) {
        cuentaPalabras.put(palabra, numVeces + 1);
      } else {
        cuentaPalabras.put(palabra, 1);
      }
    }
  }
  public static void contabilizaPalabra4(
          ConcurrentHashMap<String,Integer> cuentaPalabras,
          String palabra ) {
    boolean sustit;
    Integer valorIni = cuentaPalabras.putIfAbsent(palabra, 1);
    if (valorIni != null){
      int valorAct = valorIni;
      while (true){
        sustit = cuentaPalabras.replace(palabra,valorAct, valorAct + 1);
        if (sustit) break;
        valorAct = cuentaPalabras.get(palabra);
      }
    }
  }

  public static void contabilizaPalabra3(
          ConcurrentHashMap<String,Integer> cuentaPalabras,
          String palabra ) {
    synchronized (cuentaPalabras) {
      Integer numVeces = cuentaPalabras.get(palabra);
      if (numVeces != null) {
        cuentaPalabras.put(palabra, numVeces + 1);
      } else {
        cuentaPalabras.put(palabra, 1);
      }
    }
  }
    public static void contabilizaPalabra5(
            ConcurrentHashMap<String,AtomicInteger> cuentaPalabras,
            String palabra ) {
      AtomicInteger a = cuentaPalabras.putIfAbsent(palabra, new AtomicInteger(1));
      if ( a != null){
        AtomicInteger aux = cuentaPalabras.get(palabra);
        aux.addAndGet(1);
        cuentaPalabras.put(palabra, aux);
      }
    }
  // --------------------------------------------------------------------------
  static void imprimePalabraMasUsadaYVeces(
                  Map<String,Integer> cuentaPalabras ) {
    Vector<Map.Entry> lista = 
        new Vector<Map.Entry>( cuentaPalabras.entrySet() );

    String palabraMasUsada = "";
    int    numVecesPalabraMasUsada = 0;
    // Calcula la palabra mas usada.
    for( int i = 0; i < lista.size(); i++ ) {
      String palabra = ( String ) lista.get( i ).getKey();
      int numVeces = ( Integer ) lista.get( i ).getValue();
      if( i == 0 ) {
        palabraMasUsada = palabra;
        numVecesPalabraMasUsada = numVeces;
      } else if( numVecesPalabraMasUsada < numVeces ) {
        palabraMasUsada = palabra;
        numVecesPalabraMasUsada = numVeces;
      }
    }
    // Imprime resultado.
    System.out.print( "( Palabra: '" + palabraMasUsada + "' " + 
                         "veces: " + numVecesPalabraMasUsada + " )" );
  }
  static void imprimePalabraMasUsadaYVeces1(
          Map<String,AtomicInteger> cuentaPalabras ) {
    Vector<Map.Entry> lista =
            new Vector<Map.Entry>( cuentaPalabras.entrySet() );

    String palabraMasUsada = "";
    int    numVecesPalabraMasUsada = 0;
    // Calcula la palabra mas usada.
    for( int i = 0; i < lista.size(); i++ ) {
      String palabra = ( String ) lista.get( i ).getKey();
      AtomicInteger numVecesAtomic = ( AtomicInteger ) lista.get( i ).getValue();
      int numVeces = numVecesAtomic.get();
      if( i == 0 ) {
        palabraMasUsada = palabra;
        numVecesPalabraMasUsada = numVeces;
      } else if( numVecesPalabraMasUsada < numVeces ) {
        palabraMasUsada = palabra;
        numVecesPalabraMasUsada = numVeces;
      }
    }
    // Imprime resultado.
    System.out.print( "( Palabra: '" + palabraMasUsada + "' " +
            "veces: " + numVecesPalabraMasUsada + " )" );
  }
  static void imprimePalabraMasUsadaYVeces2(
          Map<String,Long> cuentaPalabras ) {
    Vector<Map.Entry> lista =
            new Vector<Map.Entry>( cuentaPalabras.entrySet() );

    String palabraMasUsada = "";
    Long    numVecesPalabraMasUsada = Long.valueOf(0);
    // Calcula la palabra mas usada.
    for( int i = 0; i < lista.size(); i++ ) {
      String palabra = ( String ) lista.get( i ).getKey();
      Long numVeces = ( Long ) lista.get( i ).getValue();
      if( i == 0 ) {
        palabraMasUsada = palabra;
        numVecesPalabraMasUsada = numVeces;
      } else if( numVecesPalabraMasUsada < numVeces ) {
        palabraMasUsada = palabra;
        numVecesPalabraMasUsada = numVeces;
      }
    }
    // Imprime resultado.
    System.out.print( "( Palabra: '" + palabraMasUsada + "' " +
            "veces: " + numVecesPalabraMasUsada + " )" );
  }
  // --------------------------------------------------------------------------
  static void printCuentaPalabrasOrdenadas(
                  HashMap<String,Integer> cuentaPalabras ) {
    int             i, numVeces;
    List<Map.Entry> list = new Vector<Map.Entry>( cuentaPalabras.entrySet() );

    // Ordena por valor.
    Collections.sort( 
        list,
        new Comparator<Map.Entry>() {
            public int compare( Map.Entry e1, Map.Entry e2 ) {
              Integer i1 = ( Integer ) e1.getValue();
              Integer i2 = ( Integer ) e2.getValue();
              return i2.compareTo( i1 );
            }
        }
    );
    // Muestra contenido.
    i = 1;
    System.out.println( "Veces Palabra" );
    System.out.println( "-----------------" );
    for( Map.Entry e : list ) {
      numVeces = ( ( Integer ) e.getValue () ).intValue();
      System.out.println( i + " " + e.getKey() + " " + numVeces );
      i++;
    }
    System.out.println( "-----------------" );
  }


}



