/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.data.model;

import java.io.Serializable;
import java.util.Random;
import lombok.Data;

@Data
public class Mesa implements Serializable {
    private static final long serialVersionUID = 1L;

    private int numero;
    private String descricao;
    private String usuario;
    private int id;
    private int op;
    
    public Mesa(int numero, String descricao, int id) {
        this.numero = numero;
        this.descricao = descricao;
        this.id = id;
    }
    
    public Mesa(int numero, String descricao) {
        this.numero = numero;
        this.descricao = descricao;
        Random rand = new Random();
        this.id = rand.nextInt(256);
    }
    
    public Mesa() {
    }
}
