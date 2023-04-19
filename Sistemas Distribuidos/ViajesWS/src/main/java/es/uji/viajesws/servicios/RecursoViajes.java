package es.uji.viajesws.servicios;


import jakarta.ws.rs.*;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;

import es.uji.viajesws.modelo.GestorViajes;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Vector;

@Path("viajes")
public class RecursoViajes {

	private GestorViajes gestor = null;

	/**
	 * Constructor por defecto
	 */
	public RecursoViajes() {
		super();
		gestor = new GestorViajes();
		System.out.println("construyo RecursoViajes");
	}

	/**
	 * Cuando cada cliente cierra su sesion volcamos los datos en el fichero para mantenerlos actualizados
	 */
	@PUT
	@Path("/guardaDatos")
	public synchronized Response guardaDatos() {
		gestor.guardaDatos();
		return Response.ok().build();
	}


	/**
	 * Devuelve los viajes disponibles con un origen dado
	 *
	 * @param origen
	 * @return JSONArray de viajes con un origen dado. Vacío si no hay viajes disponibles con ese origen
	 */

	@GET
	@Path("/consultaViajes")
	@Produces("application/json")
	public synchronized Response consultaViajes(@QueryParam("origen") String origen) { // CABECERA POR COMPLETAR
		final JSONArray res = gestor.consultaViajes(origen);
		if (res == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			ResponseBuilder builder = Response.ok(res);
			return builder.build();
		}
	}

	/**
	 * El cliente codcli reserva el viaje codviaje
	 *
	 * @param codviaje
	 * @param codcli
	 * @return JSONObject con la información del viaje. Vacío si no existe o no está disponible
	 */

	@PUT
	@Path("/reservaViaje/{codViaje}")
	@Produces("application/json")
	public synchronized Response reservaViaje(@PathParam("codViaje") String codviaje, @QueryParam("codCli") String codcli) { // CABECERA POR COMPLETAR
		final JSONObject res = gestor.reservaViaje(codviaje, codcli);
		if (res == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			ResponseBuilder builder = Response.ok(res);
			return builder.build();
		}
	}

	/**
	 * El cliente codcli anula su reserva del viaje codviaje
	 *
	 * @param codviaje codigo del viaje a anular
	 * @param codcli   codigo del cliente
	 * @return JSON del viaje en que se ha anulado la reserva. JSON vacio si no se ha anulado
	 */
	/* FALTAN ANOTACIONES JAX-RS */
	@DELETE
	@Path("/anulaReserva/{codViaje}")
	@Produces("application/json")
	public synchronized Response anulaReserva(@PathParam("codViaje") String codviaje, @QueryParam("codCli") String codcli) { // CABECERA POR COMPLETAR
		final JSONObject res = gestor.anulaReserva(codviaje, codcli);
		if (res == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			ResponseBuilder builder = Response.ok(res);
			return builder.build();
		}
	}

	/**
	 * El cliente codcli oferta un Viaje
	 *
	 * @param codcli
	 * @param origen
	 * @param destino
	 * @param fecha
	 * @param precio
	 * @param numplazas
	 * @return JSONObject con los datos del viaje ofertado. Vacio si no se oferta
	 */
	@POST
	@Path("/ofertaViaje")
	@Produces("application/json")
	public synchronized Response ofertaViaje(@QueryParam("codCli") String codcli,     @QueryParam("origen") String origen,
											 @QueryParam("destino") String destino,   @QueryParam("fecha") String fecha,
											 @QueryParam("precio") long precio,       @QueryParam("numPlazas") long numplazas) { // CABECERA POR COMPLETAR

		final JSONObject res = gestor.ofertaViaje(codcli, origen, destino, fecha, precio, numplazas);
		if (res == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			ResponseBuilder builder = Response.ok(res);
			return builder.build();
		}
	}




	/**
	 * El cliente codcli borra un viaje que ha ofertado
	 *
	 * @param codviaje	codigo del viaje a borrar
	 * @param codcli	codigo del cliente
	 * @return	JSONObject del viaje borrado. JSON vacio si no se ha borrado
	 */

	@DELETE
	@Path("/borraViaje/{codViaje}")
	@Produces("application/json")
	public synchronized Response borraViaje(@PathParam("codViaje") String codviaje,@QueryParam("codCli")  String codcli) {
		final JSONObject res = gestor.borraViaje(codviaje, codcli);
		if (res == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		} else {
			ResponseBuilder builder = Response.ok(res);
			return builder.build();
		}
	}

}