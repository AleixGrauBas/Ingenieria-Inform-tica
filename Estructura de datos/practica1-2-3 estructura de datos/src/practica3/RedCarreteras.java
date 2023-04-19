package practica3;
import java.security.Key;
import java.util.*;
import java.security.InvalidParameterException;
public class RedCarreteras {
	private Map<String, Map<String, Integer>> red;
	/**
	 * Constructor
	 * 
	 * Crea una red de carreteras vacías.
	 */
	public RedCarreteras() {
		//TO DO
		red = new HashMap<>();
	}

	/**
	 * Valida que un tramo sea correcto.
	 * 
	 * Es decir, que ninguna de las dos ciudades sea null, y que la distancia sea
	 * mayor de cero. No se admiten tramos de una ciudad con sigo misma. En el
	 * caso de que un tramo no sea válido produce una excepción.
	 * 
	 * @param una,
	 *            una ciudad
	 * @param otra,
	 *            la otra ciudad
	 * @param distancia,
	 *            la distancia del tramo
	 * @throws InvalidParameterException
	 *             si el tramo no es válido.
	 */
	private void validarTramo(String una, String otra, int distancia) throws InvalidParameterException {
		if (una == null || otra==null || distancia == 0 || una.equals(otra)){
			throw new InvalidParameterException();
		}
		// TO DO
	}

	/**
	 * Devuelve un conjunto con todas las ciudades de la red.
	 */
	public Set<String> ciudades() {
		return red.keySet();
	}

	/**
	 * Añade un tramo a la red.
	 * 
	 * Valida que el tramo sea valido. Si alguna de las dos ciudades no existiá
	 * previamente en la red, la añade. El tramo se añadirá dos veces, una para
	 * cada ciudad. En caso de que el tramo ya existiera remplazará el valor de
	 * la distancia.
	 * 
	 * @return Distancia previa del tramo, -1 si el tramo no exitía.
	 * @throws //InvalidParamenterException
	 *             si el tramo no es válido.
	 */
	public int nuevoTramo(String una, String otra, int distancia) {
		// TO DO
		validarTramo(una,otra,distancia);
		Integer res = -1;
		boolean nuevo = false;
		if (!red.containsKey(una)){
			Map<String, Integer> aux = new HashMap<>();
			aux.put(otra, distancia);
			red.put(una, aux);
			nuevo = true;
		}
		if (!red.containsKey(otra)){
			Map<String, Integer> aux2 = new HashMap<>();
			aux2.put(una, distancia);
			red.put(otra, aux2);
			nuevo = true;
		}
		if (existeTramo(una,otra) != -1 && !nuevo ){
			red.get(una).put(otra,distancia);
			red.get(otra).put(una,distancia);
			res = distancia;
		}
		if (existeTramo(una,otra) == -1 && !nuevo){
			red.get(una).put(otra,distancia);
			red.get(otra).put(una,distancia);
			res = -1;
		}

		return res;
	}

	/**
	 * Comprueba si existe un tramo entre dos ciudades.
	 * 
	 * @return La distancia del tramo, o -1 si no existe
	 */
	public int existeTramo(String una, String otra) {
		// TO DO
		int res = -1;
		if (red.containsKey(una)){
			if (red.get(una).containsKey(otra)){
				res = red.get(una).get(otra);
			}
		}
		if (red.containsKey(otra)){
			if (red.get(otra).containsKey(una)){
				res = red.get(otra).get(una);
			}
		}

		return res;
	}

	/**
	 * Borra el tramo entre dos ciudades si existe.
	 * 
	 * @return La distancia del tramo, o -1 si no existía.
	 */
	public int borraTramo(String una, String otra) {
		int res = existeTramo(una,otra);
		if (res != -1){
			red.get(una).remove(otra);
			red.get(otra).remove(una);
		}

		return res;
	}

	/**
	 * Comprueba si un camino es posible.
	 * 
	 * Un camino es una lista de ciudades. El camino es posible si existe un
	 * tramo entre cada para de ciudades consecutivas. Si dos ciudades
	 * consecutivas del camino son la misma el camino es posible y la distancia
	 * añadida es 0. Si el camino incluye una sóla ciudad de la red el resultado es 0.
	 * 
	 * @return La distancia total del camino, es decir la suma de las distancias
	 *         de los tramos, o -1 si el camino no es posible o no incluye ninguna ciudad.
	 */
	public int compruebaCamino(List<String> camino) {
		// TODO
		int suma = 0;
		boolean posible = true;
		if (camino.isEmpty()){
			return -1;
		}
		if (camino.size() == 1){
			return suma;
		}
		for (int i = 1; i < camino.size();i++){
			if (camino.get(i) == null) {
				return -1;
			}
			if (!camino.get(i).equals(camino.get(i-1))){
				int distancia = existeTramo(camino.get(i), camino.get(i-1));
				if (distancia == -1){
					return -1;
				}
				suma += distancia;
			}
		}

		return suma;
	}

}
