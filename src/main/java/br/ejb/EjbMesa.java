package br.ejb;

import br.data.crud.CrudMesas;
import br.data.model.Mesa;
import jakarta.ejb.LocalBean;
import java.util.ArrayList;
import jakarta.ejb.Stateless;

@Stateless

@LocalBean
public class EjbMesa {

    public ArrayList<Mesa> getAll() {
        return new CrudMesas().getAll();
    }
}
