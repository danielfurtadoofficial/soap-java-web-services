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
public class SystemUser implements Serializable{
    
    int id;
    String username;
    String password;
    String email;
    String telefone;
    String nome;
    String tipo_login;
    int id_oficina;

    public SystemUser(int id, String username, String password, String email, String telefone, String nome, String tipo_login, int id_oficina) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.telefone = telefone;
        this.nome = nome;
        this.tipo_login = tipo_login;
        this.id_oficina = id_oficina;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo_login() {
        return tipo_login;
    }

    public void setTipo_login(String tipo_login) {
        this.tipo_login = tipo_login;
    }

    public int getId_oficina() {
        return id_oficina;
    }

    public void setId_oficina(int id_oficina) {
        this.id_oficina = id_oficina;
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
