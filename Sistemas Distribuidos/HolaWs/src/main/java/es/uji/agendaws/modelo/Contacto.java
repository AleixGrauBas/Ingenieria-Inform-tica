package es.uji.agendaws.modelo;

public class Contacto {	
	/**
	 * Clase para guardar la informacion de un Contacto de la agenda
	 */

	private String nombre,	
				telefono; 

	
	/**
	 * Constructor por defecto. No hace nada
	 */
	public Contacto() { 
		super();
	}
	
	/**
	 * Constructor con argumentos
	 * @param f				fila del barco
	 * @param c				columna del barco
	 * @param orientacion	orientacion (vertical u horizontal)
	 * @param tamanyo		tamanyo del barco
	 */
	public Contacto(String nombre, String telefono) {
		super();
		this.nombre = nombre;
		this.telefono = telefono;
	}

	
	@Override
	public String toString() {
		return nombre + "#" + telefono;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


} // end class Barco
