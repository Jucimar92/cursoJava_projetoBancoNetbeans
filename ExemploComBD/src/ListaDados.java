/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Aluno
 */
public class ListaDados {

    public static void main(String[] args) {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/sistema";
        String user = "root";
        String password = "root";
        Connection conn;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            String sql = "SELECT CODIGO, NOME FROM PESSOA";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                System.out.print("Codigo: "+rs.getString("CODIGO"));
                System.out.println("\tNome: "+rs.getString("NOME"));
            }
            rs.close();

            st.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

}
