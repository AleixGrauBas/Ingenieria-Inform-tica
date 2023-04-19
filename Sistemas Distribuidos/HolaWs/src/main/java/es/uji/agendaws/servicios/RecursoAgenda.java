package es.uji.agendaws.servicios;


import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;

import es.uji.agendaws.modelo.Contacto;

import jakarta.ws.rs.core.UriBuilder;
import jakarta.ws.rs.core.UriInfo;

//import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Path("agenda")
public class RecursoAgenda {
	private Map<Integer, Contacto> agendaDB = new ConcurrentHashMap<Integer, Contacto>();
	private AtomicInteger idCounter = new AtomicInteger();

	/**
	 * Constructor por defecto
	 */	
	public RecursoAgenda() {
		System.out.println("construyo RecursoAgenda");
	}

	/**
	 * Crea un nuevo contacto y lo almacena en el diccionario
	 * @param is		input stream para leer los datos del cuerpo de la petición
	 * @param uriInfo	URI de la petición extraida del contexto
	 * @return			cuerpo vacio con la URI del nuevo recurso en la cabecera Location
	 */	
	// Usamos POST porque es el servidor el que construye y devuelve la URI del nuevo recurso
	// usando la cabecera 'Location'
	@POST
	@Consumes("text/plain")
	public Response nuevoContacto(InputStream is,
			@Context UriInfo uriInfo) {
		// Crea un contacto extrayendolo del cuerpo del mensaje accedido mediante un InputStream
		Contacto contacto = readContacto(is);
		int id = idCounter.incrementAndGet();
		agendaDB.put(id, contacto);

		// Construye la respuesta incluyendo la URI absoluta al nuevo recurso contacto
		// Obtiene la ruta absoluta de la información de contexto inyectada mediante @Context al método
		UriBuilder uriBuilder = uriInfo.getBaseUriBuilder();
		URI newURI = uriBuilder.path("agenda/" + id).build();

		// created añade el URI proporcionado a la cabecera 'Location'
		// Devuelve el estado 201 indicando que el contacto se ha CREATED con éxito
		ResponseBuilder builder = Response.created(newURI);
		return builder.build();
		//return response.status(Response.Status.CREATED).build();
	}

	/**
	 * Lee los datos de un nuevo contacto del cuerpo de la petición
	 * @param is	flujo asociado al cuerpo de la petición
	 * @return		datos del contacto leido
	 */
	protected Contacto readContacto(InputStream is ) {
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		try {
			String cadena =  br.readLine();
			String[] componentes = cadena.split("#");
			return new Contacto(componentes[0], componentes[1]);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}


	/**
	 * Borra un contacto del diccionario
	 * @param	idContacto	identificador del contacto
	 * @return				cuerpo vacío y estado indicando si se ha podido borrar el contacto
	 */		
	@DELETE
	@Path("{idContacto}")
	public Response borraContacto(@PathParam("idContacto") int idContacto) {

		if (agendaDB.remove(idContacto) == null) {
			// Si el contacto no existe devolvemos una respuesta con estado error NOT_FOUND (404)
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		else {
			ResponseBuilder builder = Response.ok();
			return builder.build();
		}
	}


	/**
	 * Actualiza contacto
	 * @param	idContacto	identificador del contacto
	 * @param	nombre		fila de la casilla
	 * @param	telefono	columna de la casilla
	 * @return				cuerpo vacío y estado indicando si se ha podido actualizar el contacto
	 */
	@PUT
	@Path("{idContacto}")
	public Response actualizaContacto( @PathParam("idContacto") int idContacto,
			@QueryParam("nombre") String nombre, 
			@QueryParam("telefono") String telefono)   {
		
		Contacto contacto = agendaDB.get(idContacto);

		if (contacto == null) {
			// Si el contacto no existe devolvemos una respuesta con estado error NOT_FOUND (404)
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		else {
			contacto.setNombre(nombre);
			contacto.setTelefono(telefono);

			ResponseBuilder builder = Response.ok();
			return builder.build();
		}
	}


	/**
	 * Obtiene los datos de un contacto.
	 * @param	idContacto		identificador del contacto
	 * @return					cuerpo conteniendo la cadena con informacion sobre el contacto "nombre#telefono"
	 */
	@GET
	@Path("{idContacto}")
	@Produces("text/plain")
	public Response getContacto( @PathParam("idContacto") int idContacto)   {

		final Contacto contacto = agendaDB.get(idContacto);

		if (contacto == null)
			// Si el contacto no existe devolvemos una respuesta con estado error NOT_FOUND (404)
			return Response.status(Response.Status.NOT_FOUND).build();
		else {
			ResponseBuilder builder = Response.ok(contacto.toString());
			return builder.build();
		}
	}

}
