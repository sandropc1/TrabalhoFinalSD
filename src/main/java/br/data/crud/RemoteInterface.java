/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.data.crud;

import br.data.model.ItemReserva;
import br.data.model.Mesa;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;


@WebService
public interface RemoteInterface extends Remote {

    @WebMethod
    void read(Mesa mesa) throws RemoteException;

    @WebMethod
    void add(Mesa mesa) throws RemoteException;

    @WebMethod
    void free(Mesa mesa) throws RemoteException;

    @WebMethod
    ArrayList<ItemReserva> getAll() throws RemoteException;

    @WebMethod
    ArrayList<ItemReserva> getFila() throws RemoteException;

    @WebMethod
    ArrayList<String> getUsers() throws RemoteException;
    
    @WebMethod
    ArrayList<ItemReserva> getCommit() throws RemoteException;

    @WebMethod
    boolean setUsuario(String string) throws RemoteException;

    @WebMethod
    ArrayList<String> getLog() throws RemoteException;

    @WebMethod
    void commit() throws RemoteException;

    @WebMethod
    void abort() throws RemoteException;

    @WebMethod
    void clearLog() throws RemoteException;
    
    @WebMethod
    void thread() throws RemoteException;
    
}
