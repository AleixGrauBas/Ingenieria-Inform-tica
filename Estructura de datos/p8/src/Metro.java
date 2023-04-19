import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class Metro {
	
	protected class InfoLineaMetro {
		protected String nombre;  //Nombre de la línea de metro
		protected String color;   //Color asociado a la línea de metro
		protected List<String> paradas;
	}
	private HashMap<String,InfoLineaMetro> lineasMetro; //información de cada linea
	private EDTreeGraph<String, String> grafoMetro;

	
	public Metro(String filename) {
		lineasMetro = new HashMap<>();
		grafoMetro = new EDTreeGraph<>(false, true); //grafo no dirigido
		leerGrafo(filename);
	}
	
	//Lee el fichero con la informaci�n de las lineas de metro
	private void leerGrafo (String nomfich) {
		Scanner inf=null;
		try {
			inf = new Scanner (new FileInputStream(nomfich));
		} catch(FileNotFoundException e){
			System.out.println("Error al abrir el fichero");
			System.exit(0);
		}
		int NLineasMetro=inf.nextInt(); //numero de lineas de metro

		for (int i=0; i<NLineasMetro; i++) {  //Para cada linea
			String lineaId = inf.next();  //linea, etiqueta arcos
			InfoLineaMetro linea=new InfoLineaMetro();
			linea.color = inf.next();
			linea.nombre = inf.next();
			//System.out.println("color "+linea.color + " nombre "+linea.nombre);
			int nparadas = inf.nextInt(); //numero de paradas de esa linea
			//System.out.println("nparadas "+nparadas);
			inf.nextLine();
			linea.paradas=new ArrayList<>();
			String origen = inf.nextLine();
			linea.paradas.add(origen.toLowerCase());  //se añade a la información de la línea
			if (!grafoMetro.containsNode(origen.toLowerCase()))  //Se añade al grafo si no está
				grafoMetro.insertNode(origen.toLowerCase());

			String dest;
			for (int j=1; j<nparadas; j++ ) {
				dest = inf.nextLine();
				linea.paradas.add(dest.toLowerCase());  //se añade a las paradas de la línea
				if (!grafoMetro.containsNode(dest.toLowerCase()))
					grafoMetro.insertNode(dest.toLowerCase());  //Se añade al grafo si no está

				grafoMetro.insertEdge(origen.toLowerCase(),dest.toLowerCase(),lineaId); //Se añade el arco al grafo
				origen=dest;
			}
			this.lineasMetro.put(lineaId,linea);
		}
		inf.close();
	}

	public void printMetro() {
		for (String linea:this.lineasMetro.keySet()) {
			InfoLineaMetro infolinea=this.lineasMetro.get(linea);
			System.out.println (linea+" "+infolinea.color+" "+infolinea.nombre+" estaciones: ");
			System.out.println(infolinea.paradas.toString());
		}
		System.out.println("Grafo del metro: ");
		this.grafoMetro.printGraphStructure();
		//Comprobaciones
		System.out.println("Numero de estaciones: "+this.grafoMetro.getKeys().size());
	}


	//Devuelve el n�mero total de estaciones del metro
	public int getNumeroEstaciones() {
		return grafoMetro.size();
	}

	
	public Set<String> getEstaciones() {
		return grafoMetro.getKeys();
	}
	
	public void printListaEstaciones() {
		Set<String> estaciones = getEstaciones();
		for (String estacion: estaciones)
			System.out.print(estacion+", ");
		System.out.println();
	}

	//Devuelve la linea que conecta directamente 2 estaciones y null si no están conectadas directamente
	public String estacionesConectadas(String origen, String destino) {
		return this.grafoMetro.getEdgeWeight(origen, destino);
	}

	//Devuelve un conjunto con los identificadores de las líneas de metro que tienen parada en esa estación
	//Si la estación no existe, devuelve null
	public Set<String> getLineasEstacion(String estacion) {
		// TODO EJERCICIO 2
		if (grafoMetro.containsNode(estacion)) {
			Set<String> res = new HashSet<>();
			for (String lineas : lineasMetro.keySet()){
				if (lineasMetro.get(lineas).paradas.contains(estacion))
					res.add(lineas);
			}
			return res;
		}
		return null;
	}

	//Devuelve todas las estaciones de todo el recorrido de una linea dado su identificador 
	//Si dir=='f', las devolverá en sentido almacenado, si no, en sentido contrario
	//Si la línea no existe, devuelve null
	public List<String> getRecorridoLinea(String idlinea, char dir) {
		// TODO EJERCICIO 3
		if (lineasMetro.get(idlinea) != null){
			if (dir == 'f')
				return lineasMetro.get(idlinea).paradas;
			else if (dir == 'r'){
				List<String> inverso = new ArrayList<>();
				ListIterator<String> iterator = lineasMetro.get(idlinea).paradas.listIterator(lineasMetro.get(idlinea).paradas.size());
				while (iterator.hasPrevious())
					inverso.add(iterator.previous());
				return inverso;
			}

		}
		return null;

	}

	// Determina si dos estaciones se encuentran en la miusmo línea. Si es así devolver el código de la línea.
	// En caso contrario deveolverá null
	public String mismaLinea (String origen, String destino) {
		// TODO EJERCICIO 4
		Set<String> or = getLineasEstacion(origen);
		Set<String> des = getLineasEstacion(destino);
		if (or == null ||des == null)
			return null;
		for (String s : or) {
			for (String c : des) {
				if (s.equals(c))
					return s;
			}
		}
		return null;
	}


	// Calcula la ruta entre la estación origen y la estación destino. Devuelve una lista
	// de arcos con la siguiente estación en la ruta y la línea por la que se llega.
	//Si alguna de las dos estaciones (origen/destino) no existe, devuelve una lista vacía
	//Para calcular la ruta: si las estaciones están en el recorrido de una misma línea de metro,
	//se devolverá esa línea. En caso contrario, se buscará la ruta con menor número de paradas.

	public List<Edge<String, String>> ruta(String origen, String destino) {
		// TODO EJERCICIO 5
		List<Edge<String, String>> res = new ArrayList<>();
		String linea = mismaLinea(origen, destino);
		if (linea != null) {
			res = recorridoLinea(linea, origen, destino);
			return res;
		} else {
			List<String> camino = bfs(origen, destino);
			if (camino.contains(origen)) {
				for (int i = 1; i < camino.size(); i++) {
					res.add(new Edge(camino.get(i - 1), grafoMetro.getEdgeWeight(camino.get(i), camino.get(i))));
				}
				if (res.size() < 1)
					return null;
				return res;
			}
			return null;
		}
	}
		public List<String> bfs(String origen, String destino){
			List<String> visited = new ArrayList<>();
			List<String> q = new LinkedList<>();
			q.add(origen);

			while(q.size() !=  0){
				origen = q.get(q.size() -1);
				q.remove(q.size() -1);
				visited.add(origen);
				if (grafoMetro.getAdjacentNodes(origen) != null) {
					Iterator<Edge<String,String>> it = grafoMetro.getAdjacentArcs(origen).listIterator();
					while (it.hasNext()) {
						Edge<String, String> s = it.next();
						if (!visited.contains(origen)) {
							visited.add(s.getTarget());
							q.add(s.getTarget());
						}
					}
				}
			}
			return visited;

		}

		public List<Edge<String, String>> recorridoLinea(String linea, String origen, String destino){
			List<String> recorrido = getRecorridoLinea(linea, 'f');
			List<Edge<String, String>>  res = new ArrayList<>();
			boolean encontrado = false;
			for (int i = 0; i < recorrido.size(); i++){
				if (recorrido.get(i).equals(origen))
					encontrado = true;
				if (encontrado)
					res.add(new Edge(recorrido.get(i),linea));
				if (recorrido.get(i).equals(destino))
					break;
			}
			if (res.size() < 1){
				recorrido = getRecorridoLinea(linea, 'r');
				encontrado = false;
				for (int i = 0; i < recorrido.size(); i++){
					if (recorrido.get(i).equals(origen))
						encontrado = true;
					if (encontrado)
						res.add(new Edge(recorrido.get(i),linea));
					if (recorrido.get(i).equals(destino))
						break;
				}
			}
			return res;

		}

}
