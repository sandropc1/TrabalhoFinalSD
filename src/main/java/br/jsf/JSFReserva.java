package br.jsf;

import br.data.crud.RemoteInterface;
import br.data.model.ItemReserva;
import br.data.model.Mesa;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Named(value = "JSFReserva")
@SessionScoped
public class JSFReserva implements Serializable {

    private RemoteInterface ejbReserva;

    public JSFReserva() {
        try {
            Properties props = new Properties();
            props.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
            props.setProperty(Context.URL_PKG_PREFIXES, "com.sun.enterprise.naming");
            props.setProperty(Context.STATE_FACTORIES, "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");

            Context context = new InitialContext(props);
            ejbReserva = (RemoteInterface) context.lookup("java:global/ProjetoFinalSD-1.0-SNAPSHOT/EjbReserva!br.data.crud.RemoteInterface");
        } catch (NamingException e) {
        }
    }
    
    public void thread() {
        try {
            ejbReserva.thread();
        } catch (RemoteException e) {
        }
    }
    
    public void read(Mesa mesa) {
        try {
            ejbReserva.read(mesa);
        } catch (RemoteException e) {
        }
    }

    public void add(Mesa mesa) {
        try {
            ejbReserva.add(mesa);
        } catch (RemoteException e) {
        }
    }
    
    public void free(Mesa mesa) {
        try {
            ejbReserva.free(mesa);
        } catch (RemoteException e) {
        }
    }

    public ArrayList<ItemReserva> getAll() {
        try {
            return ejbReserva.getAll();
        } catch (RemoteException e) {
            return new ArrayList<>();
        }
    }
    
    public ArrayList<ItemReserva> getFila() {
        try {
            return ejbReserva.getFila();
        } catch (RemoteException e) {
            return new ArrayList<>();
        }
    }
    
     public ArrayList<ItemReserva> getCommit() {
        try {
            return ejbReserva.getCommit();
        } catch (RemoteException e) {
            return new ArrayList<>();
        }
    }
    
    public ArrayList<String> getUsers() {
        try {
            return ejbReserva.getUsers();
        } catch (RemoteException e) {
            return new ArrayList<>();
        }
    }
     
    public boolean setUsuario(String string) {
        try {
            return ejbReserva.setUsuario(string);
        } catch (RemoteException e) {
            return false;
        }
    }
       
    public ArrayList<String> getLog() {
        try {
            return ejbReserva.getLog();
        } catch (RemoteException e) {
            return new ArrayList<>();
        }
    }
     
    public void clearLog() {
        try {
            ejbReserva.clearLog();
        } catch (RemoteException e) {
        }
    }
    
    
    public void commit(){
        try {
            ejbReserva.commit();
        } catch (RemoteException e) {
        }
    }
    
     public void abort (){
        try {
            ejbReserva.abort();
        } catch (RemoteException e) {
        }
    }
}
