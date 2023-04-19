package server;
import java.rmi.server.UnicastRemoteObject;

import common.IntCallbackCliente;
import common.IntServidorViajes;
import gestor.GestorViajes;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.rmi.RemoteException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class ImplServidorViajes extends UnicastRemoteObject implements IntServidorViajes {
    GestorViajes gestor;
    Map<String, Set<IntCallbackCliente>> clientes;
    public ImplServidorViajes() throws RemoteException{
        super();
        this.gestor = new GestorViajes();
        this.clientes = new ConcurrentHashMap<>();
    }
    @Override
    public synchronized JSONArray consultaViajes(String origen) throws RemoteException {
        return gestor.consultaViajes(origen);
    }

    @Override
    public synchronized JSONObject reservaViajes(String codviaje, String codcli) throws RemoteException {
        return gestor.reservaViaje(codviaje,codcli);
    }

    @Override
    public synchronized JSONObject anulaReserva(String codviaje, String codcli) throws RemoteException {
        return gestor.anulaReserva(codviaje,codcli);
    }

    @Override
    public synchronized JSONObject ofertaViaje(String codcli, String origen, String destino, String fecha, long precio, long numplazas) throws RemoteException {
        if(!gestor.ofertaViaje(codcli, origen, destino, fecha, precio, numplazas).isEmpty()){
            notificar(origen);
        }return gestor.ofertaViaje(codcli, origen, destino, fecha, precio, numplazas);
    }

    @Override
    public synchronized JSONObject borraViaje(String codviaje, String codcli) throws RemoteException {
        return gestor.borraViaje(codviaje,codcli);
    }

    @Override
    public synchronized void cierraSesion() throws RemoteException {
        gestor.guardaDatos();
    }

    @Override
    public synchronized boolean registerForCallBack(IntCallbackCliente codcli, String origen) throws RemoteException {
        origen = origen.toUpperCase();
        if (!clientes.containsKey(origen)){
            Set<IntCallbackCliente> clientesCallBack = new HashSet<>();
            clientesCallBack.add(codcli);
            clientes.put(origen, clientesCallBack);
        } else {
            Set<IntCallbackCliente> clientesCallBack = clientes.get(origen);
            if (!clientesCallBack.contains(codcli)) {
                clientesCallBack.add(codcli);
                return true;
            }
            return false;
        }
        return true;
    }

    @Override
    public synchronized boolean unregisterForCallBack(IntCallbackCliente codcli, String origen) throws RemoteException {
        origen = origen.toUpperCase();
        Set<IntCallbackCliente> clientesCallBack = clientes.get(origen);
        if(clientes.containsKey(origen)){
            if (clientesCallBack != null) {
                clientesCallBack.remove(codcli);
                return true;
            }
        }

        return false;
    }
    private synchronized void notificar(String origen) throws RemoteException {
        origen = origen.toUpperCase();
        Set<IntCallbackCliente> clientesCallBack = clientes.get(origen);
        if (clientesCallBack != null) {
            Iterator<IntCallbackCliente> it = clientesCallBack.iterator();
            while (it.hasNext()){
                IntCallbackCliente client = it.next();
                try {
                    client.notifica("Se ha creado el viaje " + origen);
                } catch (RemoteException e) {
                    System.out.println("Detectado un cliente inactivo. Borrado de la lista");
                    it.remove();
                }
            }
        }
    }
}
