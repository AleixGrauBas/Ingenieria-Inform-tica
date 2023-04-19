package client;

import java.rmi.RemoteException;

public class ImpCallBackCliente {
    ImpCallBackCliente() throws RemoteException{
        super();
    }
    public String notificame(String mensaje){
        String mensajeRet = "Viaje disponible en: " + mensaje;
        System.out.println(mensajeRet);
        return mensajeRet;
    }
}
