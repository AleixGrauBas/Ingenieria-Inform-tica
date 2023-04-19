package client;

import common.IntCallbackCliente;
import common.IntServidorViajes;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;


import java.io.IOException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.util.Scanner;


public class ClienteViajesRMI {

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
        System.out.println("6. Subscribirse a un origen");
        System.out.println("7. Quitar suscripción de un origen");
        do {
            System.out.print("\nElige una opcion (0..7): ");
            opcion = teclado.nextInt();
        } while ( (opcion<0) || (opcion>7) );
        teclado.nextLine(); // Elimina retorno de carro del buffer de entrada
        return opcion;
    }


    /**
     * Programa principal. Muestra el menú repetidamente y atiende las peticiones del cliente.
     *
     * @param args	no se usan argumentos de entrada al programa principal
     */
    public static void main(String[] args) throws IOException {

        Scanner teclado = new Scanner(System.in);
        try {
        // Crea un gestor de viajes
            int RMIPortNum = 3;
            String hostName = "localhost";
            String registryURL = "rmi://" + hostName + ":" + RMIPortNum + "/viaje";
            IntServidorViajes gestor = (IntServidorViajes) Naming.lookup(registryURL);


            System.out.print("Introduce tu codigo de cliente: ");
            String codcli = teclado.nextLine();
            IntCallbackCliente callbackCliente = new ImplCallBackCliente();


            int opcion;
            do {
                opcion = menu(teclado);
                switch (opcion) {
                    case 0 -> { // Guardar los datos en el fichero y salir del programa

                        // POR IMPLEMENTAR

                        gestor.cierraSesion();
                        System.out.println("Se cierra la sesión");
                    }
                    case 1 -> { // Consultar viajes con un origen dado

                        // POR IMPLEMENTAR
                        System.out.print("Introduce el origen del viaje: ");
                        String origen = teclado.nextLine();
                        JSONArray res = gestor.consultaViajes(origen);
                        if (res.isEmpty()) {
                            System.out.println("No se ha encontrado viajes desde este origen");
                        } else {
                            System.out.println("Viajes disponibles");
                            for (Object viaje : res)
                                System.out.println(viaje.toString());
                        }
                    }
                    case 2 -> { // Reservar un viaje

                        // POR IMPLEMENTAR
                        System.out.print("Introduce el viaje: ");
                        String viaje = teclado.nextLine();
                        JSONObject res = gestor.reservaViajes(viaje, codcli);
                        if (res.isEmpty())
                            System.out.println("No existe el viaje: ");
                        else
                            System.out.println("Datos del viaje reservado: " + res.toJSONString());

                    }
                    case 3 -> { // Anular una reserva

                        // POR IMPLEMENTAR
                        System.out.print("Introduce el viaje: ");
                        String viaje = teclado.nextLine();
                        JSONObject res = gestor.anulaReserva(viaje, codcli);
                        if (!res.isEmpty())
                            System.out.println("Datos de la reserva anulada: " + res.toJSONString());
                        else
                            System.out.println("No existe la reserva.");
                    }
                    case 4 -> { // Ofertar un viaje

                        // POR IMPLEMENTAR
                        System.out.println("Introduce el origen");
                        String origen = teclado.nextLine();
                        System.out.println("Introduce el destino");
                        String destino = teclado.nextLine();
                        System.out.println("Introduce la fecha 'DD-MM-YYYY'");
                        String fecha = teclado.nextLine();
                        System.out.println("Introduce el precio");
                        long precio = teclado.nextLong();
                        System.out.println("Introduce el numero de plazas");
                        long plazas = teclado.nextLong();

                        JSONObject ofertado = gestor.ofertaViaje(codcli, origen, destino, fecha, precio, plazas);
                        if (ofertado.isEmpty()) {
                            System.out.println("No existe el viaje. ");
                        } else
                            System.out.println("Datos del viaje Ofertado: " + ofertado.toJSONString());

                    }
                    case 5 -> { // Borrar un viaje ofertado

                        // POR IMPLEMENTAR
                        System.out.print("Introduce el viaje a borrar: ");
                        String viaje = teclado.nextLine();
                        JSONObject res = gestor.borraViaje(viaje, codcli);
                        if (res.isEmpty())
                            System.out.println("No existe el viaje a borrar ");
                        else
                            System.out.println("Datos del viaje borrado: " + res.toJSONString());
                    }
                    case 6 -> { // Subscribirse
                        System.out.println("Introduce el origen donde deseas suscribirte: ");
                        String origen = teclado.nextLine();
                        boolean added = gestor.registerForCallBack(callbackCliente, origen);
                        if (added) {
                            System.out.println("\nSuscrito en " + origen);
                        } else {
                            System.out.println("\nYa estas suscrito en " + origen);
                        }
                    }
                    case 7 -> { // Eliminarse
                        System.out.println("Introduce el origen donde deseas quitar la suscripción: ");
                        String origen = teclado.next();
                        boolean removed = gestor.unregisterForCallBack(callbackCliente, origen);
                        if (removed) {
                            System.out.println("\nYa no estás suscrito en " + origen);
                        } else {
                            System.out.println("\nNo estas suscrito en " + origen);
                        }

                    }
                }     // fin switch

            } while (opcion != 0);
        } catch (NotBoundException e) {
            throw new RuntimeException(e);
        }
    } // fin de main


    // Modifícala para que instancie un objeto de la clase AuxiliarClienteViajes

    // Modifica todas las llamadas al objeto de la clase GestorViajes
    // por llamadas al objeto de la clase AuxiliarClienteViajes.
    // Los métodos a llamar tendrán la misma signatura.

} // fin class // fin class