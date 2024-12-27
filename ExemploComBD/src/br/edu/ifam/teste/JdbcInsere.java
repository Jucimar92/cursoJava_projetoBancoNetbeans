/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifam.teste;

import br.edu.ifam.jdbc.ConnectionFactory;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Aluno
 */
public class JdbcInsere {

    public static void main(String[] args) throws SQLException {
        Connection con = new ConnectionFactory().getConnection();
        String sql = "insert into contatos" + 
                "(nome,email,endereco,dataNascimento) "
                + "values(?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, "Maria Claudia");
        ps.setString(2, "mclaudia@ifam.edu.br");
        ps.setString(3, "Rua Z,1234");
        Calendar nasc = Calendar.getInstance();
        nasc.set(2007, 2, 31);
        Date dataParaGravar = new Date(nasc.getTimeInMillis());
        ps.setDate(4, dataParaGravar);
        ps.executeUpdate();
        ps.close();
    }

}
