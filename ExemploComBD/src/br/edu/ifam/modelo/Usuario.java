/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifam.modelo;

import br.edu.ifam.enumeradores.EnumTipoUsuario;
/**
 *
 * @author Jucimar Brito
 */
public class Usuario {
  private int id;
  private String nome;
  private String nomeDeLogin;
  private String senha;
  private EnumTipoUsuario tipoUsuario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeDeLogin() {
        return nomeDeLogin;
    }

    public void setNomeDeLogin(String nomeDeLogin) {
        this.nomeDeLogin = nomeDeLogin;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public EnumTipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(EnumTipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
  
  
}