/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifam.modelo;

import java.util.Calendar;

/**
 *
 * @author Jucibs
 */
public class ContaReceber {
   private int noConta;
   private Contato contato;
   private Calendar dataCompra;
   private Calendar dataVencimento;
   private double valorCompra;
   private double percJurosDia;
   private double valorJuros;
   private double valorRecebido;

    public ContaReceber() {
    }

    public ContaReceber(int noConta, Contato contato, Calendar dataCompra, Calendar dataVencimento, double valorCompra, double percJurosDia, double valorJuros, double valorRecebido) {
        this.noConta = noConta;
        this.contato = contato;
        this.dataCompra = dataCompra;
        this.dataVencimento = dataVencimento;
        this.valorCompra = valorCompra;
        this.percJurosDia = percJurosDia;
        this.valorJuros = valorJuros;
        this.valorRecebido = valorRecebido;
    }

   
   
    public int getNoConta() {
        return noConta;
    }

    public void setNoConta(int noConta) {
        this.noConta = noConta;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public Calendar getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(Calendar dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Calendar getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(Calendar dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public double getPercJurosDia() {
        return percJurosDia;
    }

    public void setPercJurosDia(double percJurosDia) {
        this.percJurosDia = percJurosDia;
    }

    public double getValorJuros() {
        return valorJuros;
    }

    public void setValorJuros(double valorJuros) {
        this.valorJuros = valorJuros;
    }

    public double getValorRecebido() {
        return valorRecebido;
    }

    public void setValorRecebido(double valorRecebido) {
        this.valorRecebido = valorRecebido;
    }
   
   
   
}
