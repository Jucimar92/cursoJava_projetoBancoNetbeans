/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifam.enumeradores;

import br.edu.ifam.modelo.Usuario;

/**
 *
 * @author Jucimar Brito
 */
public class TestaEnum {
    
    public static void main(String[] args) {
        //EnumTipoUsuario usrAdm = EnumTipoUsuario.ADMINISTRADOR;
        EnumTipoUsuario usrAdm = EnumTipoUsuario.ADMINISTRADOR;
        System.out.println(usrAdm.getValor());
        
        System.out.println(usrAdm.porValor(1));        
        System.out.println("Tipo de Usuario = " + usrAdm.name() + "Ordem = " + usrAdm.ordinal());
        
        for (EnumTipoUsuario usr : EnumTipoUsuario.values()) {
            System.out.println("Tipo de Usuario = " + usr.name() + "Ordem = " + usr.ordinal());            
        }
        
        DiasDaSemana dia;
        dia = DiasDaSemana.QUARTA;
        System.out.println("Ordem : " + dia.ordinal());
        
        for (DiasDaSemana d : DiasDaSemana.values()) {
            System.out.println("Dia: " + d.name());
        }
        
        DiasDaSemana d = DiasDaSemana.SEGUNDA;
        
        Usuario usr=new Usuario();
        usr.setNome("Jucimar");
        usr.setTipoUsuario(EnumTipoUsuario.ADMINISTRADOR);
        
        System.out.println("usuario "+usr.getNome()+" "+usr.getTipoUsuario()+" ordem "+usr.getTipoUsuario().getValor());
        
    }
    
}
