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
public class Tarefa implements Serializable{
    
    int id;
    String descricao;
    String estado;
    Date data_conclusao;
    Date data_entrega;
    int veiculo_marcacao;
    int id_mecanico;

    public Tarefa(int id, String descricao, String estado, Date data_conclusao, Date data_entrega, int veiculo_marcacao, int id_mecanico) {
        this.id = id;
        this.descricao = descricao;
        this.estado = estado;
        this.data_conclusao = data_conclusao;
        this.data_entrega = data_entrega;
        this.veiculo_marcacao = veiculo_marcacao;
        this.id_mecanico = id_mecanico;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getData_conclusao() {
        return data_conclusao;
    }

    public void setData_conclusao(Date data_conclusao) {
        this.data_conclusao = data_conclusao;
    }

    public Date getData_entrega() {
        return data_entrega;
    }

    public void setData_entrega(Date data_entrega) {
        this.data_entrega = data_entrega;
    }

    public int getVeiculo_marcacao() {
        return veiculo_marcacao;
    }

    public void setVeiculo_marcacao(int veiculo_marcacao) {
        this.veiculo_marcacao = veiculo_marcacao;
    }

    public int getId_mecanico() {
        return id_mecanico;
    }

    public void setId_mecanico(int id_mecanico) {
        this.id_mecanico = id_mecanico;
    }
    

    
    
}
