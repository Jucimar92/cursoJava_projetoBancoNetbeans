/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifam.teste;

import br.edu.ifam.modelo.Contato;
import br.edu.ifam.modelo.ContatoDao;
import java.util.List;

/**
 *
 * @author Aluno
 */
public class ListaDadosDao {

    public static void main(String[] args) {
        ContatoDao contatoDao=new ContatoDao();
        List<Contato> listaDeContatos=contatoDao.getLista();
        for(Contato c : listaDeContatos){
            System.out.println(c.toString());
        }
        
    }

}
