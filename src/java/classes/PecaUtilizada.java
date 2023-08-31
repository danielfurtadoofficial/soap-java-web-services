/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Daniel Furtado
 */
public class PecaUtilizada implements Serializable{
    
    int id, quantidade, id_tarefa, id_peca;
    String nome_peca;

    public PecaUtilizada(int id, int quantidade, int id_tarefa, int id_peca, String nome_peca) {
        this.id = id;
        this.quantidade = quantidade;
        this.id_tarefa = id_tarefa;
        this.id_peca = id_peca;
        this.nome_peca = nome_peca;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getId_tarefa() {
        return id_tarefa;
    }

    public void setId_tarefa(int id_tarefa) {
        this.id_tarefa = id_tarefa;
    }

    public int getId_peca() {
        return id_peca;
    }

    public void setId_peca(int id_peca) {
        this.id_peca = id_peca;
    }

    public String getNome_peca() {
        return nome_peca;
    }

    public void setNome_peca(String nome_peca) {
        this.nome_peca = nome_peca;
    }
    
    
}
