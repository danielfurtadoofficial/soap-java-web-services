/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

import java.io.Serializable;

/**
 *
 * @author Daniel Furtado
 */
public class Fatura implements Serializable{
    
    int id, id_veiculo_marcacao;
    float custo_total;
    String descricao;

    public Fatura(int id, int id_veiculo_marcacao, float custo_total, String descricao) {
        this.id = id;
        this.id_veiculo_marcacao = id_veiculo_marcacao;
        this.custo_total = custo_total;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_veiculo_marcacao() {
        return id_veiculo_marcacao;
    }

    public void setId_veiculo_marcacao(int id_veiculo_marcacao) {
        this.id_veiculo_marcacao = id_veiculo_marcacao;
    }

    public float getCusto_total() {
        return custo_total;
    }

    public void setCusto_total(float custo_total) {
        this.custo_total = custo_total;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
    
}
