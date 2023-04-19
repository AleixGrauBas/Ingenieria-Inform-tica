package servidor;

import java.io.IOException;
import java.net.SocketException;

import gestor.GestorViajes;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import comun.MyStreamSocket;

/**
 * Clase ejecutada por cada hebra encargada de servir a un cliente del servicio de viajes.
 * El metodo run contiene la logica para gestionar una sesion con un cliente.
 */

class HiloServidorViajes implements Runnable {


	private final MyStreamSocket myDataSocket;
	private final GestorViajes gestor;
	String operacion;

	/**
	 * Construye el objeto a ejecutar por la hebra para servir a un cliente
	 *
	 * @param    myDataSocket    socket stream para comunicarse con el cliente
	 * @param    unGestor        gestor de viajes
	 */
	HiloServidorViajes(MyStreamSocket myDataSocket, GestorViajes unGestor) {
		// POR IMPLEMENTAR
		this.myDataSocket = myDataSocket;
		this.gestor = unGestor;
	}

	/**
	 * Gestiona una sesion con un cliente
	 */
	public void run() {
		boolean done = false;
		// ...
		try {
			while (!done) {
				// Recibe una petición del cliente
				// Extrae la operación y sus parámetros

				String peticion = myDataSocket.receiveMessage();
				JSONObject campos = toJSON(peticion);
				operacion = (String) campos.get("operacion");
				// fin switch
				switch (operacion) {// ...
					case "0":
						gestor.guardaDatos();
						myDataSocket.sendMessage("Cierro sesión");
						myDataSocket.close();
						break;
								// Consulta los viajes con un origen dado

					case "1": // Consulta los viajes con un origen dado
						JSONArray consulta = gestor.consultaViajes((String) campos.get("origen"));
						myDataSocket.sendMessage(consulta.toJSONString());
						break;

					case "2":// Reserva una plaza en un viaje
						JSONObject reserva = gestor.reservaViaje((String) campos.get("codviaje"), (String) campos.get("codcli"));
						myDataSocket.sendMessage(reserva.toJSONString());
						break;

					case "3": // Pone en venta un articulo
						JSONObject anula = gestor.anulaReserva((String) campos.get("codviaje"), (String) campos.get("codcli"));
						myDataSocket.sendMessage(anula.toJSONString());
						break;

					case "4": // Oferta un viaje
						JSONObject viajeNuevo = gestor.ofertaViaje((String) campos.get("codcli"), (String) campos.get("origen"), (String) campos.get("destino"),
								(String) campos.get("fecha"), (long) campos.get("precio"), (long) campos.get("numplazas"));
						myDataSocket.sendMessage(viajeNuevo.toJSONString());
						break;
					case "5": // Borra un viaje
						JSONObject viajeBorrado = gestor.borraViaje((String) campos.get("codviaje"), (String) campos.get("codcli"));
						myDataSocket.sendMessage(viajeBorrado.toJSONString());
						break;
				}


			} // fin while
		} // fin try
		catch (SocketException ex) {
			System.out.println("Capturada SocketException");
		} catch (IOException ex) {
			System.out.println("Capturada IOException");
		} catch (Exception ex) {
			System.out.println("Exception caught in thread: " + ex);
		} // fin catch
	} //fin run

	private JSONObject toJSON(String peticion) {
		try {
			return (JSONObject) new JSONParser().parse(peticion);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}//