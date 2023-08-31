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
public class Mecanico implements Serializable{
    
    int id, bi, efetivo, id_user;
    
    String nome, username, email, telefone;

    public Mecanico(int id, int bi, int efetivo, int id_user, String nome, String username, String email, String telefone) {
        this.id = id;
        this.bi = bi;
        this.efetivo = efetivo;
        this.id_user = id_user;
        this.nome = nome;
        this.username = username;
        this.email = email;
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBi() {
        return bi;
    }

    public void setBi(int bi) {
        this.bi = bi;
    }

    public int getEfetivo() {
        return efetivo;
    }

    public void setEfetivo(int efetivo) {
        this.efetivo = efetivo;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    
    
}
