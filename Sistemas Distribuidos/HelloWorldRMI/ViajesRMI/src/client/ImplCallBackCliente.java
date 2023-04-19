package client;

import common.IntCallbackCliente;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ImplCallBackCliente extends UnicastRemoteObject  implements IntCallbackCliente {
    protected ImplCallBackCliente() throws RemoteException {
        super();
    }

    @Override
    public void notifica(String message) throws RemoteException {
        System.out.println("\n"+ message);
    }
}
