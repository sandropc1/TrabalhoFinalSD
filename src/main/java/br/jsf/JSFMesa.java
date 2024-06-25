/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package br.jsf;

import br.data.model.Mesa;
import br.ejb.EjbMesa;
import jakarta.ejb.EJB;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import java.util.ArrayList;

/**
 *
 * @author Sandro
 */
@Named(value = "JSFMesa")
@RequestScoped
public class JSFMesa {

    @EJB
    private EjbMesa ejbMesa;
    
    public JSFMesa() {
    }
    
   public ArrayList<Mesa> getAll(){
       return ejbMesa.getAll();
   }
    
}
