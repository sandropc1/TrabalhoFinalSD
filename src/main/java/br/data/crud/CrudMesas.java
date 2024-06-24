
package br.data.crud;

import br.data.model.Mesa;

import java.util.ArrayList;

public class CrudMesas {
public ArrayList<Mesa> getAll(){
ArrayList<Mesa> lmesa = new ArrayList<>();
    lmesa.add(new Mesa(1, "Mesa 1"));
    lmesa.add(new Mesa(2, "Mesa 2"));
    lmesa.add(new Mesa(3, "Mesa 3"));
    lmesa.add(new Mesa(4, "Mesa 4"));

return lmesa;
}
}