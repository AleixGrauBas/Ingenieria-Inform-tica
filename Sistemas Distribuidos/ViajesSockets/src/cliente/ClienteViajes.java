package cliente;

import java.io.IOException;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;


public class ClienteViajes {

    /**
     * Muestra el menu de opciones y lee repetidamente de teclado hasta obtener una opcion valida
     * @param teclado	stream para leer la opción elegida de teclado
     * @return			opción elegida
     */
    public static int menu(Scanner teclado) {
        int opcion;
        System.out.println("\n\n");
        System.out.println("=====================================================");
        System.out.println("============            MENU        =================");
        System.out.println("=====================================================");
        System.out.println("0. Salir");
        System.out.println("1. Consultar viajes con un origen dado");
        System.out.println("2. Reservar un viaje");
        System.out.println("3. Anular una reserva");
        System.out.println("4. Ofertar un viaje");
        System.out.println("5. Borrar un viaje");
        do {
            System.out.print("\nElige una opcion (0..5): ");
            opcion = teclado.nextInt();
        } while ( (opcion<0) || (opcion>5) );
        teclado.nextLine(); // Elimina retorno de carro del buffer de entrada
        return opcion;
    }


    /**
     * Programa principal. Muestra el menú repetidamente y atiende las peticiones del cliente.
     *
     * @param args	no se usan argumentos de entrada al programa principal
     */
    public static void main(String[] args) throws IOException, ParseException {

        Scanner teclado = new Scanner(System.in);

        // Crea un gestor de viajes
        AuxiliarClienteViajes gestor = new AuxiliarClienteViajes("localhost", "12345");

        System.out.print("Introduce tu codigo de cliente: ");
        String codcli = teclado.nextLine();

        int opcion;
        do {
            opcion = menu(teclado);
            switch (opcion) {
                case 0: // Guardar los datos en el fichero y salir del programa

                    // POR IMPLEMENTAR


                    gestor.cierraSesion();
                    System.out.println("Se cierra la sesión");
                    break;

                case 1: { // Consultar viajes con un origen dado

                    // POR IMPLEMENTAR
                    System.out.print("Introduce el origen del viaje: ");
                    Scanner origen = new Scanner(System.in);
                    String origenAux = origen.nextLine();
                    JSONArray res = gestor.consultaViajes(origenAux);
                    if(res.isEmpty()) {
                        System.out.println("No se ha encontrado viajes desde este origen");
                    }else {
                        System.out.println("Viajes disponibles");
                        for(Object viaje: res)
                            System.out.println(viaje.toString());
                    }
                    break;
                }

                case 2: { // Reservar un viaje

                    // POR IMPLEMENTAR
                    System.out.print("Introduce el viaje: ");
                    Scanner viaje = new Scanner(System.in);
                    String viajeAux = viaje.nextLine();
                    JSONObject res = gestor.reservaViaje(viajeAux,codcli);
                    if(res.isEmpty())
                        System.out.println("No existe el viaje: ");
                    else
                        System.out.println("Datos del viaje reservado: " + res.toJSONString());

                    break;
                }

                case 3: { // Anular una reserva

                    // POR IMPLEMENTAR
                    System.out.print("Introduce el viaje: ");
                    Scanner viaje = new Scanner(System.in);
                    String viajeAux = viaje.nextLine();
                    JSONObject res = gestor.anulaReserva(viajeAux, codcli);
                    if(!res.isEmpty())
                        System.out.println("Datos de la reserva anulada: " + res.toJSONString());
                    else
                        System.out.println("No existe la reserva.");
                    break;
                }

                case 4: { // Ofertar un viaje

                    // POR IMPLEMENTAR
                    Scanner viaje = new Scanner(System.in);
                    System.out.println("Introduce el origen");
                    String origen = viaje.nextLine();
                    System.out.println("Introduce el destino");
                    String destino = viaje.nextLine();
                    System.out.println("Introduce la fecha 'DD-MM-YYYY'");
                    String fecha = viaje.nextLine();
                    System.out.println("Introduce el precio");
                    Long precio = viaje.nextLong();
                    System.out.println("Introduce el numero de plazas");
                    Long plazas = viaje.nextLong();

                    JSONObject ofertado = gestor.ofertaViaje(codcli,origen,destino,fecha,precio,plazas);
                    if(ofertado.isEmpty()){
                        System.out.println("No existe el viaje. ");
                    }else
                        System.out.println("Datos del viaje Ofertado: " + ofertado.toJSONString());

                }
                break;

                case 5: { // Borrar un viaje ofertado

                    // POR IMPLEMENTAR
                    System.out.print("Introduce el viaje a borrar: ");
                    Scanner viaje = new Scanner(System.in);
                    String viajeAux = viaje.nextLine();
                    JSONObject res = gestor.borraViaje(viajeAux,codcli);
                    if(res.isEmpty())
                        System.out.println("No existe el viaje a borrar ");
                    else
                        System.out.println("Datos del viaje borrado: " + res.toJSONString());

                    break;
                }

            } // fin switch

        } while (opcion != 0);

    } // fin de main


    // Modifícala para que instancie un objeto de la clase AuxiliarClienteViajes

    // Modifica todas las llamadas al objeto de la clase GestorViajes
    // por llamadas al objeto de la clase AuxiliarClienteViajes.
    // Los métodos a llamar tendrán la misma signatura.

} // fin class // fin class