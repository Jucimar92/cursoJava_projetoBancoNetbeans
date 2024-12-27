/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifam.modelo;

import java.awt.Image;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.ImageIcon;

/**
 *
 * @author Aluno
 */
public class Contato {

    private Long id;
    private String nome;
    private String email;
    private String endereco;
    private Calendar dataNascimento;
    private ImageIcon foto;
    private File fotoFile;

    public File getFotoFile() {
        return fotoFile;
    }

    public void setFotoFile(File fotoFile) {
        this.fotoFile = fotoFile;
    }

    
    
       

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public Calendar getDataNascimento() {
        return dataNascimento;
    }
    
    public String getDataNascimentoStr() {
        SimpleDateFormat formatoDaData = new SimpleDateFormat("dd/MM/yyyy");
        String dtAnivFormatada = formatoDaData.format(dataNascimento.getTime());
        return dtAnivFormatada;
    }

    public void setDataNascimento(Calendar dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        
        return id+" - "+nome;
                
        /*SimpleDateFormat formatoDaData = new SimpleDateFormat("dd/MM/yyyy");
        String dtAnivFormatada = formatoDaData.format(dataNascimento.getTime());
        return "Contato{" + "id=" + id + 
                ", nome=" + nome + 
                ", email=" + email +
                ", endereco=" + endereco +
                ", dataNascimento=" +
                dtAnivFormatada+
                '}'; */
    }

    public ImageIcon getFoto() {
        return foto;
    }

    public void setFoto(ImageIcon foto) {
        this.foto = foto;
    }
    
    
    
}
