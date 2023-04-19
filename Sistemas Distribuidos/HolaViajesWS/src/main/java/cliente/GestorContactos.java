package cliente;


import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.WebApplicationException;


public class GestorContactos {

	// URI del recurso que permite acceder al juego
	final private String baseURI = "http://localhost:8080/HolaWs/servicios/agenda/";
	Client cliente = null;


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor de la clase
	 */
	public GestorContactos()  {
		this.cliente = ClientBuilder.newClient();
	}

	/**
	 * Crea un nuevo conteacto
	 * @param nombre	nombre del contacto
	 * @param telefono	teléfono del contacto
	 * @throws WebApplicationException	cuando ha fallado la petición HTTP
	 */
	public void nuevoContacto(String nombre, String telefono)
			throws WebApplicationException {

		String cadenaContacto = nombre + "#" + telefono;

		Response response = cliente.target(baseURI)
				.request().post(Entity.text(cadenaContacto));

		if (response.getStatus() != 201) {
			response.close();
			throw new WebApplicationException("Error al crear el contacto");
		}
		// Obtiene la informació sobre el URI del nuevo recurso contacto de la cabecera 'Location' en la respuesta
		String recursoContacto = response.getLocation().toString();
		response.close();

		System.out.println("Instancio un nuevo contacto con URI: " + recursoContacto);
	}

	/**
	 * Borra un contacto dado si id
	 * @param idContacto	identificador del contacto a borrar
	 * @return				valor lógico indicando si se ha borrado el contacto
	 * @throws WebApplicationException	cuando ha fallado la petición HTTP
	 */
	public boolean borraContacto(int idContacto)
			throws  WebApplicationException {
		Response response = cliente.target(baseURI).path(""+idContacto)
				.request().delete();
		int estado = response.getStatus();
		response.close();
		if (estado == 200)
			return true;
		else if (estado == 404)
			return false;
		else
			throw new WebApplicationException("Error detectado al borrar contacto: " + idContacto );
	}

	/**
	 * actualiza los datos de un contacto existente
	 * @param idContacto	identificador del contacto a modificar
	 * @param nombre		nuevo nombre
	 * @param telefono		nuevo teléfono
	 * @return				valor lógico indicando si se ha podido actualizar el contacto
	 * @throws WebApplicationException	cuando ha fallado la petición HTTP
	 */
	public boolean actualizaContacto( int idContacto, String nombre, String telefono)
			throws WebApplicationException {
		String cadenaContacto = nombre + "#" + telefono;
		Response response = cliente.target(baseURI).path(""+idContacto)
				.queryParam("nombre", nombre)
				.queryParam("telefono", telefono)
				.request()
				.put(Entity.text(cadenaContacto));
		int estado = response.getStatus();
		response.close();
		if ( estado == 200)
			return true;
		else if (estado == 404)
			return false;
		else
			throw new WebApplicationException("Error detectado al actualizar contacto: " + idContacto );
	}

	/**
	 * Obtiene los datos de un contacto
	 * @param idContacto	identificador del contacto
	 * @return	cadena con los datos del contacto separados por #. Vacía si no se ha encontrado
	 * @throws WebApplicationException	cuando ha fallado la petición HTTP
	 */
	public String getContacto(int idContacto)
			throws WebApplicationException {
		Response response = cliente.target(baseURI).path(""+idContacto)
				.request(MediaType.TEXT_PLAIN).get();	
		int estado = response.getStatus();
		if ( estado == 200) {
			String cadenaContacto = response.readEntity(String.class);
			response.close();
			return cadenaContacto;
		} else if (estado == 404) {
			response.close();
			return "";
		} else {
			response.close();
			throw new WebApplicationException("Error detectado al obtener contacto: " + idContacto );
		}	
	}

	/**
	 * Obtiene una cadena con todos los contactos
	 * Realiza una peticion GET a la URI {baseURI}
	 * @return			vector de cadenas, cada una con un contacto "idContacto: nombre#telefono"
	 * @throws	WebApplicationException cuando ha fallado la petición HTTP
	 */
	public String[] getListaContactos( ) throws WebApplicationException  {
		Response response = cliente.target(baseURI)
				.request(MediaType.TEXT_PLAIN).get();

		if (response.getStatus() == 200) {
			// Lee la cadena con los contactos del cuerpo (Entity) del mensaje
			String cadenaContactos = response.readEntity(String.class);
			String[] vectorContactos = cadenaContactos.split(";");
			response.close();
			return vectorContactos;
		}
		else  {// != OK
			response.close();
			throw new WebApplicationException("ERROR al intentar obtener la lista de contactos");
		}
	}


} // fin clase
