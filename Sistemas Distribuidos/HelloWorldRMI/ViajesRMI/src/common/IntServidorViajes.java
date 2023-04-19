package common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface IntServidorViajes extends Remote{
    JSONArray consultaViajes(String origen) throws RemoteException;
     JSONObject reservaViajes(String codviaje, String codcli) throws RemoteException;
     JSONObject anulaReserva(String codviaje, String codcli) throws RemoteException;
     JSONObject ofertaViaje(String codcli, String origen, String destino, String fecha, long precio, long numplazas) throws RemoteException;
     JSONObject borraViaje(String codviaje, String codcli) throws RemoteException;
     void cierraSesion() throws  RemoteException;
     boolean registerForCallBack(IntCallbackCliente codcli, String origen) throws RemoteException;
     boolean unregisterForCallBack(IntCallbackCliente codcli, String origen)throws  RemoteException;
    }
