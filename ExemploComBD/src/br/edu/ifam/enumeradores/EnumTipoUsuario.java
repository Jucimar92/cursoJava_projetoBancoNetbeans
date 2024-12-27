/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.edu.ifam.enumeradores;

/**
 *
 * @author Jucimar Brito
 */
public enum EnumTipoUsuario {
     ADMINISTRADOR(1), GERENTE(2), FUNCIONARIO(3), CLIENTE(4);

     public final int valor;
      
          
       EnumTipoUsuario(int valor){
           this.valor=valor;
      }
       
       public int getValor() 
        { return valor; }

    public static EnumTipoUsuario porValor(int valor) {
        for (EnumTipoUsuario vlr: EnumTipoUsuario.values()) {
            if (valor == vlr.getValor()) return vlr;
        }
        throw new IllegalArgumentException("codigo invalido");
    }
}

 enum CartasEnum {
        J(11), Q(12), K(13), A(14);

        public int valorCarta;

        CartasEnum(int valor) {
            valorCarta = valor;
        }
    }