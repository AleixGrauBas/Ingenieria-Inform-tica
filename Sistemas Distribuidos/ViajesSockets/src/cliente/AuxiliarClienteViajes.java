package cliente;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import comun.MyStreamSocket;

/**
 * Esta clase es un modulo que proporciona la logica de aplicacion
 * para el Cliente del sevicio de viajes usando sockets de tipo stream
 */

public class AuxiliarClienteViajes {

	private final MyStreamSocket mySocket; // Socket de datos para comunicarse con el servidor
	JSONParser parser;

	/**
	 * Construye un objeto auxiliar asociado a un cliente del servicio.
	 * Crea un socket para conectar con el servidor.
	 * @param	hostName	nombre de la maquina que ejecuta el servidor
	 * @param	portNum		numero de puerto asociado al servicio en el servidor
	 */
	AuxiliarClienteViajes(String hostName, String portNum)
			throws SocketException, UnknownHostException, IOException {

		// IP del servidor
		InetAddress serverHost = InetAddress.getByName(hostName);
		// Puerto asociado al servicio en el servidor
		int serverPort = Integer.parseInt(portNum);
		//Instantiates a stream-mode socket and wait for a connection.
		this.mySocket = new MyStreamSocket(serverHost, serverPort);
		/**/  System.out.println("Hecha peticion de conexion");
		parser = new JSONParser();
	} // end constructor

	/**
	 * Devuelve los viajes ofertados desde un origen dado
	 *
	 * @param origen	origen del viaje ofertado
	 * @return array JSON de viajes desde un origen. array vacio si no hay ninguno
	 */
	public JSONArray consultaViajes(String origen) throws IOException, ParseException {
		// POR IMPLEMENTAR
		JSONObject o = new JSONObject();
		o.put("operacion","1");
		o.put("origen",origen);
		mySocket.sendMessage(o.toJSONString());
		String resp = mySocket.receiveMessage();
		JSONParser parser = new JSONParser();
		JSONArray res = (JSONArray) parser.parse(resp);
		return res; // cambiar por el retorno correcto
	} // end consultaViajes



	/**
	 * Un pasajero reserva un viaje
	 *
	 * @param codviaje		codigo del viaje
	 * @param codcliente	codigo del pasajero
	 * @return	objeto JSON con los datos del viaje. Vacio si no se ha podido reservar
	 */
	public JSONObject reservaViaje(String codviaje, String codcliente) throws IOException, ParseException {
		// POR IMPLEMENTAR
		JSONObject o = new JSONObject();
		o.put("operacion", "2");
		o.put("codviaje", codviaje);
		o.put("codcli", codcliente);

		mySocket.sendMessage(o.toJSONString());
		String resp = mySocket.receiveMessage();
		//String to JSONObject
		JSONParser parser = new JSONParser();

		return (JSONObject) parser.parse(resp);// cambiar por el retorno correcto	} // end reservaViaje
	}
	/**
	 * Un pasajero anula una reserva
	 *
	 * @param codviaje		codigo del viaje
	 * @param codcliente	codigo del pasajero
	 * @return	objeto JSON con los datos del viaje. Vacio si no se ha podido reservar
	 */
	public JSONObject anulaReserva(String codviaje, String codcliente) throws IOException, ParseException {
		// POR IMPLEMENTAR
		JSONObject o = new JSONObject();
		o.put("operacion", "3");
		o.put("codviaje", codviaje);
		o.put("codcli", codcliente);

		mySocket.sendMessage(o.toJSONString());
		String resp = mySocket.receiveMessage();
		//String to JSONObject
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(resp);

		return json;// cambiar por el retorno correcto	} // end anulaReserva
	}
	/**
	 * Un cliente oferta un nuevo viaje
	 *
	 * @param codprop	codigo del cliente que hace la oferta
	 * @param origen	origen del viaje
	 * @param destino	destino del viaje
	 * @param fecha		fecha del viaje en formato dd/mm/aaaa
	 * @param precio	precio por plaza
	 * @param numplazas	numero de plazas ofertadas
	 * @return	viaje ofertado en formatoJSON. Vacio si no se ha podido ofertar
	 */
	public JSONObject ofertaViaje(String codprop, String origen, String destino,
								  String fecha, long precio, long numplazas) throws IOException, ParseException {
		// POR IMPLEMENTAR
			JSONObject o = new JSONObject();
			o.put("operacion","4");
			o.put("codcli", codprop);
			o.put("origen", origen);
			o.put("destino", destino);
			o.put("fecha", fecha);
			o.put("precio", precio);
			o.put("numplazas",numplazas);

			mySocket.sendMessage(o.toJSONString());
			String resp = mySocket.receiveMessage();
			//String to JSONObject
			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(resp);

			return json;
	} // end ofertaViaje

	/**
	 * Un cliente borra una oferta de viaje
	 *
	 * @param codviaje		codigo del viaje
	 * @param codcliente	codigo del pasajero
	 * @return	objeto JSON con los datos del viaje. Vacio si no se ha podido reservar
	 */
	public JSONObject borraViaje(String codviaje, String codcliente) throws IOException, ParseException {
		// POR IMPLEMENTAR
		JSONObject o = new JSONObject();
		o.put("operacion","5");
		o.put("codviaje",codviaje);
		o.put("codcli", codcliente);

		mySocket.sendMessage(o.toJSONString());
		String resp = mySocket.receiveMessage();
		//String to JSONObject
		JSONParser parser = new JSONParser();
		JSONObject json = (JSONObject) parser.parse(resp);

		return json; // cambiar por el retorno correcto
	} // end borraViaje

	/**
	 * Finaliza la conexion con el servidor
	 */
	public void cierraSesion( ) throws IOException {
		JSONObject o = new JSONObject();
		o.put("operacion","0");
		mySocket.sendMessage(o.toJSONString());
		String res = mySocket.receiveMessage();
		System.out.println(res);
		mySocket.close();
	} // end done
} //end class