/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifam.teste;

import br.edu.ifam.modelo.Contato;
import br.edu.ifam.modelo.ContatoDao;
import java.util.Calendar;

/**
 *
 * @author Aluno
 */
public class TestaDao {
    public static void main(String[] args) {
        Contato contato=new Contato();
        contato.setNome("JoaoCarlos");
        contato.setEmail("jc@ifam.edu.br");
        contato.setEndereco("Rio de Janeiro");
        Calendar dataNasc=Calendar.getInstance();
        dataNasc.set(2010,6,25);
        contato.setDataNascimento(dataNasc);
        
        ContatoDao contatoDao=new ContatoDao();
        contatoDao.adiciona(contato);
    }
    
}
