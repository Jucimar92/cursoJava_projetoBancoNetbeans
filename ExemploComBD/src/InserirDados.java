/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Aluno
 */
public class InserirDados {

    public static void main(String[] args) {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/sistema";
        String user = "root";
        String password = "root";
        Connection conn;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            Statement st = conn.createStatement();
            String sql = "INSERT INTO PESSOA VALUES (1,'FERNANDO HENRIQUE')";
            st.executeUpdate(sql);
            sql = "INSERT INTO PESSOA VALUES (2,'LUÍS INÁCIO')";
            st.executeUpdate(sql);
            sql = "INSERT INTO PESSOA VALUES (3,'ANTÔNIO CARLOS')";
            st.executeUpdate(sql);

            st.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

}
