package server;
import java.rmi.server.UnicastRemoteObject;

import common.IntServidorViajes;
import gestor.GestorViajes;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.rmi.RemoteException;
import java.util.Map;
import java.util.Vector;

public class ImplServidorViajes extends UnicastRemoteObject implements IntServidorViajes {
    GestorViajes gestor;
    Map<String, Vector> listaEspera;
    public ImplServidorViajes() throws RemoteException{
        super();
        gestor = new GestorViajes();
    }
    @Override
    public JSONArray consultaViajes(String origen) throws RemoteException {
        return gestor.consultaViajes(origen);
    }

    @Override
    public JSONObject reservaViajes(String codviaje, String codcli) throws RemoteException {
        return gestor.reservaViaje(codviaje,codcli);
    }

    @Override
    public JSONObject anulaReserva(String codviaje, String codcli) throws RemoteException {
        return gestor.anulaReserva(codviaje,codcli);
    }

    @Override
    public JSONObject ofertaViaje(String codcli, String origen, String destino, String fecha, long precio, long numplazas) throws RemoteException {
        return gestor.ofertaViaje(codcli, origen, destino, fecha, precio, numplazas);
    }

    @Override
    public JSONObject borraViaje(String codviaje, String codcli) throws RemoteException {
        return gestor.borraViaje(codviaje,codcli);
    }

    @Override
    public void cierraSesion() throws RemoteException {
        gestor.guardaDatos();
    }

    @Override
    public void registraCallBack(String origen) throws RemoteException {
    }

    @Override
    public void eliminaCallBack() throws RemoteException {

    }
}
