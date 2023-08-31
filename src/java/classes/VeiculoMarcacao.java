/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classes;

/**
 *
 * @author Daniel Furtado
 */
public class VeiculoMarcacao {
    
    int id, id_cliente, id_servico, id_oficina;
    String data_entrada, data_saida, estado, matricula, descricao, info_riscos_entrada;

    public VeiculoMarcacao(int id, int id_cliente, int id_servico, int id_oficina, String data_entrada, String data_saida, String estado, String matricula, String descricao, String info_riscos_entrada) {
        this.id = id;
        this.id_cliente = id_cliente;
        this.id_servico = id_servico;
        this.id_oficina = id_oficina;
        this.data_entrada = data_entrada;
        this.data_saida = data_saida;
        this.estado = estado;
        this.matricula = matricula;
        this.descricao = descricao;
        this.info_riscos_entrada = info_riscos_entrada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_servico() {
        return id_servico;
    }

    public void setId_servico(int id_servico) {
        this.id_servico = id_servico;
    }

    public int getId_oficina() {
        return id_oficina;
    }

    public void setId_oficina(int id_oficina) {
        this.id_oficina = id_oficina;
    }

    public String getData_entrada() {
        return data_entrada;
    }

    public void setData_entrada(String data_entrada) {
        this.data_entrada = data_entrada;
    }

    public String getData_saida() {
        return data_saida;
    }

    public void setData_saida(String data_saida) {
        this.data_saida = data_saida;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getInfo_riscos_entrada() {
        return info_riscos_entrada;
    }

    public void setInfo_riscos_entrada(String info_riscos_entrada) {
        this.info_riscos_entrada = info_riscos_entrada;
    }
    
    
}
