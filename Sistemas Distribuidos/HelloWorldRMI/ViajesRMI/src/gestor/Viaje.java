package gestor;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

public class Viaje implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Clase Viaje ofertado
     */
    private String codviaje;	// codigo del viaje construido como codprop[0]+origen[0]+destino[0]+fecha[0:1]
    private String codprop;     // código del propietario
    private String origen;   	// ciudad de origen del viaje
    private String destino;     // ciudad de destino del viaje
    private String fecha;		// fecha del viaje en formato dd-mm-aaaa
    private long precio;     	// precio por plaza
    private long numplazas;		// número de plazas disponibles
    private Vector<String> pasajeros;	// códigos de los pasajeros

    /**
     *
     * Contructor con los atributos de la clase, salvo el codviaje, que se contruye a partir de los otros
     *
     * @param codprop
     * @param origen
     * @param destino
     * @param fecha
     * @param precio
     * @param numplazas
     */
    public Viaje(String codprop, String origen, String destino, String fecha, long precio, long numplazas) {
        super();
        this.codprop = codprop;
        this.origen = origen;
        this.destino = destino;
        this.fecha = fecha;
        this.precio = precio;
        this.numplazas = numplazas;
        this.pasajeros = new Vector<String>();

        this.codviaje = this.construyeCodviaje();

    }

    /**
     * Contructor de un Viaje a partir de su representación en formato JSON
     *
     * @param jsonViaje
     */
    public Viaje(JSONObject jsonViaje) {
        super();

        this.codprop = (String) jsonViaje.get("codprop");
        this.origen = (String) jsonViaje.get("origen");
        this.destino = (String) jsonViaje.get("destino");
        this.fecha = (String) jsonViaje.get("fecha");
        this.precio = (long) jsonViaje.get("precio");
        this.numplazas = (long) jsonViaje.get("numplazas");

        //Recorremos la lista del JSONObject y la añadimos a una lista auxiliar que finalmente usaremos como pasajeros
        Vector<String> aux = new Vector<String>();
        JSONArray a = (JSONArray) jsonViaje.get("pasajeros");
        for (int i = 0; i < a.size(); i++)
            aux.add((String) a.get(i));
        this.pasajeros = aux;

        this.codviaje = this.construyeCodviaje();
        // POR IMPLEMENTAR
    }

    /**
     * Devuelve los datos del Viaje en formato JSON
     *
     * @return	cadena en formato JSON con los datos del viaje
     */
    @Override
    public String toString() {
        return this.toJSON().toJSONString();
    }

    /**
     * Devuelve un objeto JSON con los datos del viaje
     *
     * @return	objeto JSON con los datos del Viaje
     */
    public JSONObject toJSON() {
        // POR IMPLEMENTAR
        JSONObject obj = new JSONObject();

        obj.put("codprop",this.codprop);
        obj.put("origen",this.origen);
        obj.put("destino",this.destino);
        obj.put("fecha",this.fecha);
        obj.put("precio",this.precio);
        obj.put("numplazas",this.numplazas);

        //Creamos una lista en la que añadimos los pasajeros
        JSONArray listaPasajeros = new JSONArray();
        listaPasajeros.addAll(this.pasajeros);

        obj.put("pasajeros", listaPasajeros);

        obj.put("codviaje",this.construyeCodviaje());

        return obj; // MODIFICAR
    }

    /**
     * Indica si quedan plazas libres en el Viaje
     *
     * @return	valor lógico indicando si quedan plazas
     */
    public boolean quedanPlazas() {
        return numplazas > 0;
    }


    /**
     * Indica si la fecha del viaje ya ha pasado
     *
     * @return	valor lógico indicando si el viaje es de una fecha pasada
     */
    public boolean finalizado() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate dia = LocalDate.parse(fecha, formatter);
        LocalDate hoy = LocalDate.now();

        return dia.isBefore(hoy);

    }

    /**
     * Añade un pasajero al viaje.
     * Si no quedan plazas libres, devuelve false
     *
     * @param pasajero
     * @return	valor lógico indicando si se ha podido añadir el pasajero al Viaje
     */
    public boolean anyadePasajero(String pasajero) {
        if (quedanPlazas()) {
            pasajeros.add(pasajero);
            numplazas--;
            return true;
        }
        return false;
    }

    /**
     * Borra un pasajero del Viaje.
     * Si no puede borrarlo, devuelve false.
     *
     * @param pasajero
     * @return	valor lógico indicando si se ha podido borrar el pasajero del Viaje
     */
    public boolean borraPasajero(String pasajero) {
        if (pasajeros.contains(pasajero)) {
            pasajeros.remove(pasajero);
            numplazas++;
            return true;
        }
        return false;
    }

    public String getCodviaje() {
        return codviaje;
    }

    /**
     * Contruye el código de un viaje a partir de otros atributos
     *
     * @return	código del Viaje construido
     */
    private String construyeCodviaje() {
        return codprop.substring(0,1) + origen.substring(0,1) + destino.substring(0,1) + fecha.substring(0,2);
    }

    public String getCodprop() {
        return codprop;
    }

    public void setCodprop(String codprop) {
        this.codprop = codprop;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public long getPrecio() {
        return precio;
    }

    public void setPrecio(long precio) {
        this.precio = precio;
    }

    public long getNumplazas() {
        return numplazas;
    }

    public void setNumplazas(long numplazas) {
        this.numplazas = numplazas;
    }

    public Vector<String> getPasajeros() {
        return pasajeros;
    }

    // Sustituye esta clase por tu versión de la misma de la práctica 1
} // fin clase Viaje