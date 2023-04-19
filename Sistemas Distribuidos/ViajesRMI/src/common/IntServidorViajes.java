package common;
import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import server.ImplServidorViajes;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface IntServidorViajes extends Remote{
    JSONArray consultaViajes(String origen) throws RemoteException;
    JSONObject reservaViajes(String codviaje, String codcli) throws RemoteException;
    JSONObject anulaReserva(String codviaje, String codcli) throws RemoteException;
    JSONObject ofertaViaje(String codcli, String origen, String destino, String fecha, long precio, long numplazas) throws RemoteException;
    JSONObject borraViaje(String codviaje, String codcli) throws RemoteException;
    void cierraSesion() throws  RemoteException;
    void registraCallBack(String origen) throws RemoteException;
    void eliminaCallBack() throws RemoteException;
    }
