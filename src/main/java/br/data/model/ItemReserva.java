
package br.data.model;

import lombok.Data;
import java.io.Serializable;
import lombok.Getter;

@Data
@Getter
public class ItemReserva implements Serializable{

    private Mesa mesa;
    private int quantidade = 0;

    public ItemReserva() {
    }

    public ItemReserva(Mesa mesa, int quantidade) {
        this.mesa = mesa;
        this.quantidade = quantidade;
    }
}
