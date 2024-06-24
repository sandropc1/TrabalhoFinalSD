package br.data.crud;

import br.data.model.ItemReserva;
import br.data.model.Mesa;
import jakarta.ejb.Stateful;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import java.util.ArrayList;
import java.util.Random;

@Stateful
@WebService(endpointInterface = "br.data.crud.RemoteInterface")
public class EjbReserva implements RemoteInterface {

    private ArrayList<ItemReserva> lreserva;
    private ArrayList<ItemReserva> lfila;
    private ArrayList<String> log;
    private ArrayList<String> lusuarios;
    private ArrayList<ItemReserva> lcommit;
    private String usuario = "ph";

    public EjbReserva() {
        lreserva = new ArrayList<>();
        lfila = new ArrayList<>();
        lcommit = new ArrayList<>();
        log = new ArrayList<>();
        lusuarios = new ArrayList<>();
        lusuarios.add("Thomas");
        lusuarios.add("Bruno");
        lusuarios.add("José");
    }
    
    @WebMethod 
    @Override
    public void thread() {
        Random random = new Random();
        this.usuario = lusuarios.get(random.nextInt(lusuarios.size()));
        log.add("Vendedor " + this.usuario + " está realizando operações aleatorias");

        // Escolhe uma mesa aleatoriamente para operações
        int mesaId = random.nextInt(100) + 1;
        int mesaNum = random.nextInt(4) + 1;
        
        Mesa mesa = new Mesa(mesaNum, "Mesa "+mesaNum, mesaId);
        
        int opcao = random.nextInt(3) + 1;
        
        log.add("Opção: "+ opcao);
        
        switch(opcao){
            case 1:
                read(mesa); // Simula leitura
                break;
            case 2:
                 add(mesa);  // Simula adição
                break;
            case 3:
                free(mesa); // Simula liberação
                break;
        }
       
        
        
    }

    @Override
    @WebMethod
    public boolean setUsuario(String string) {
        this.usuario = string;
        log.add("Vendedor " + this.usuario + " está pronto para realizar operações");
        return true;
    }

    @Override
    @WebMethod
    public void read(Mesa mesa) {
        boolean achou = false;
        mesa.setOp(2);
        if (!this.usuario.equals("ph")) {
            mesa.setUsuario(this.usuario);
            for (ItemReserva itemRead : lreserva) {
                if (itemRead.getMesa().getNumero() == mesa.getNumero()) {
                    if (itemRead.getQuantidade() != 0) {
                        log.add("<" + this.usuario + ">" + itemRead.getMesa().getDescricao() + " está reservada para o cliente " + itemRead.getMesa().getId());
                        if (itemRead.getQuantidade() != 0) {
                            lfila.add(new ItemReserva(mesa, 0));
                            log.add("<" + this.usuario + ">Reserva " + mesa.getId() + " entrou na fila para verificar a disponibilidade da " + mesa.getDescricao());
                        } else {
                            break;
                        }
                    }
                    achou = true;
                    break;
                }
            }
            if (!achou) {
                lreserva.add(new ItemReserva(mesa, 2));
                log.add("<" + this.usuario + ">" + mesa.getDescricao() + " está disponivel");
            }
        }
    }

    @Override
    @WebMethod
    public void add(Mesa mesa) {
        boolean achou = false;
        int check = 0;
        mesa.setOp(1);
        if (!this.usuario.equals("ph")) {
            mesa.setUsuario(this.usuario);
            for (ItemReserva itemReserva : lreserva) {
                if (itemReserva.getMesa().getNumero() == mesa.getNumero()) {
                    log.add("<" + this.usuario + ">Reserva " + mesa.getId() + " tentando ser feita sobre a " + mesa.getDescricao());
                    if (itemReserva.getQuantidade() != 0) {
                        if (itemReserva.getMesa().getUsuario().equals(mesa.getUsuario()) && itemReserva.getQuantidade() == 2) {
                            for (ItemReserva itemCheck : lreserva) {
                                if (itemCheck.getQuantidade() == 1 && itemCheck.getMesa().getNumero() == mesa.getNumero()) {
                                    check++;
                                }
                            }
                            if (check == 0) {
                                break;
                            }
                        }

                        lfila.add(new ItemReserva(mesa, 0));
                        log.add("<" + this.usuario + ">Reserva " + mesa.getId() + " entrou na fila para a " + mesa.getDescricao());
                    }
                    achou = true;
                    break;
                }
            }
            if (!achou) {
                lreserva.add(new ItemReserva(mesa, 1));
                log.add("<" + this.usuario + ">Reserva " + mesa.getId() + " feita sobre a " + mesa.getDescricao());
            }
        }
    }

    @Override
    @WebMethod
    public void free(Mesa mesa) {
        for (ItemReserva itemReserva : lreserva) {
            if (itemReserva.getMesa().getNumero() == mesa.getNumero()) {
                if (itemReserva.getQuantidade() != 0) {
                    lreserva.remove(itemReserva);//aqui
                    log.add("<" + this.usuario + ">Reserva " + itemReserva.getMesa().getId() + " liberou a " + itemReserva.getMesa().getDescricao());
                    for (ItemReserva itemFila : lfila) {
                        if (itemFila.getMesa().getNumero() == mesa.getNumero()) {
                            if (itemFila.getQuantidade() == 0) {
                                lfila.remove(itemFila);
                                itemFila.setQuantidade(1);
                                lreserva.add(itemFila);
                                log.add("<" + this.usuario + ">Reserva " + itemFila.getMesa().getId() + " feita sobre a  " + mesa.getDescricao());
                                break;
                            }
                        }
                    }
                }
                break;
            }
        }
    }

    @Override
    @WebMethod
    public ArrayList<ItemReserva> getAll() {
        return lreserva;
    }

    @Override
    @WebMethod
    public ArrayList<String> getUsers() {
        return lusuarios;
    }

    @Override
    @WebMethod
    public ArrayList<ItemReserva> getFila() {
        return lfila;
    }

    @Override
    @WebMethod
    public ArrayList<String> getLog() {
        return log;
    }
    
    @Override
    @WebMethod
    public ArrayList<ItemReserva> getCommit() {
        return lcommit;
    }


    @Override
    @WebMethod
    public void clearLog() {
        log = new ArrayList<>();
    }

    @Override
    @WebMethod
    public void commit() {
        ArrayList<ItemReserva> laux = new ArrayList<>();
        for (ItemReserva itemCommit : lreserva) {
            if (this.usuario.equals(itemCommit.getMesa().getUsuario())) {
                laux.add(itemCommit);
            }
        }
        for (ItemReserva itemAux : laux) {
            lcommit.add(itemAux);
            free(itemAux.getMesa());
        }
    }

    @Override
    @WebMethod
    public void abort() {
        
        ArrayList<ItemReserva> lauxiliar = new ArrayList<>();
        for (ItemReserva itemAbort : lfila) {
          log.add("ItemAbort:"+itemAbort);
            if (this.usuario.equals(itemAbort.getMesa().getUsuario())) {
                lauxiliar.add(itemAbort);
            }
        }
        log.add("Lista auxiliar 1:" + lauxiliar.toString());
        for (ItemReserva itemAux : lauxiliar) {
            lfila.remove(itemAux);
        }
        
        ArrayList<ItemReserva> laux = new ArrayList<>();
        for (ItemReserva itemAbort1 : lreserva) {
            if (this.usuario.equals(itemAbort1.getMesa().getUsuario())) {
                laux.add(itemAbort1);
            }
        }
        for (ItemReserva itemAux : laux) {
            free(itemAux.getMesa());
        }
    }
}
