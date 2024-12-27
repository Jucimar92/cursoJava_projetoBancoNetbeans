/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifam.modelo;

import br.edu.ifam.jdbc.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Jucibs
 */
public class ContaReceberDao {
    private Connection connection;

    public ContaReceberDao() {
        connection = new ConnectionFactory().getConnection();
    }

    public void adiciona(ContaReceber contaRec) {
        String sql = "insert into contasreceber"
                + "(noConta,codigo_contato,datacompra,datavencimento,"
                + "valorcompra,percjurosdia) "
                + "values(?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, contaRec.getNoConta());
            ps.setLong(2, contaRec.getContato().getId());
            Calendar compra = contaRec.getDataCompra();
            Date dataParaGravar = new Date(compra.getTimeInMillis());
            ps.setDate(3, dataParaGravar);
            Calendar venc = contaRec.getDataVencimento();
            dataParaGravar = new Date(venc.getTimeInMillis());
            ps.setDate(4, dataParaGravar);
            ps.setDouble(5, contaRec.getValorCompra());
            ps.setDouble(6, contaRec.getValorJuros());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public ContaReceber getContaReceber(int noConta) {
        String sql = "select * from contareceber where noconta=?";
        ContaReceber ctaRec= null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, noConta);
            rs = ps.executeQuery();
            if (rs.next()) {
                //Instancia a classe Contato
                ctaRec = new ContaReceber();
                // atribui os valores recuperados do banco 
                // para classe Contato
                ctaRec.setNoConta(rs.getInt("noConta"));
                ContatoDao dao=new ContatoDao();
                Contato c=dao.getContato(rs.getLong("codigo_contato"));
           
                ctaRec.setNoConta(rs.getInt(1));
                ctaRec.setDataCompra(dateToCalendar(rs.getDate("datacompra")));
                ctaRec.setDataVencimento(dateToCalendar(rs.getDate("datavencimento")));
                ctaRec.setValorCompra(rs.getDouble("valorcompra"));
                ctaRec.setPercJurosDia(rs.getDouble("percjurosdia"));
            }
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.closeConnection(connection, ps, rs);//fecha conexão
        }
        return ctaRec;
    }
    
    public Calendar dateToCalendar(Date dt){
        Calendar dtCalTmp=Calendar.getInstance();
        dtCalTmp.setTimeInMillis(dt.getTime());
        return dtCalTmp;
    }
}
