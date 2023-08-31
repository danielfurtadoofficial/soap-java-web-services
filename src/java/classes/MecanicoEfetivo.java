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
public class MecanicoEfetivo implements Serializable{
    
    int id, id_mecanico;
    
    String n_inps, nif;
    
    float salario;

    public MecanicoEfetivo(int id, int id_mecanico, String n_inps, String nif, float salario) {
        this.id = id;
        this.id_mecanico = id_mecanico;
        this.n_inps = n_inps;
        this.nif = nif;
        this.salario = salario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_mecanico() {
        return id_mecanico;
    }

    public void setId_mecanico(int id_mecanico) {
        this.id_mecanico = id_mecanico;
    }

    public String getN_inps() {
        return n_inps;
    }

    public void setN_inps(String n_inps) {
        this.n_inps = n_inps;
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }
    
    
    
}
