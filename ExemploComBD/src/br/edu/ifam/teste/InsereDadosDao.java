/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifam.teste;

import br.edu.ifam.modelo.Contato;
import br.edu.ifam.modelo.ContatoDao;
import java.util.Calendar;

/**
 *
 * @author Jucimar Brito
 */
public class InsereDadosDao {
   public static void main(String[] args) {
        Contato contato=new Contato();
        contato.setNome("Igor");
        contato.setEmail("Igor@ifam.edu.br");
        contato.setEndereco("Cidade do Porto");
        Calendar dataNasc=Calendar.getInstance();
        dataNasc.set(2005,3,25);
        contato.setDataNascimento(dataNasc);
        
        ContatoDao contatoDao=new ContatoDao();
        contatoDao.adiciona(contato);
   }
 
}
