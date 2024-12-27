
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aluno
 */
public class CriaTabela {

    public static void main(String[] args) {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/sistema";
        String user = "root";
        String password = "root";
        Connection conn;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            String sql = "CREATE TABLE PESSOA(CODIGO INT, "
                    + "NOME VARCHAR(40) )";
            Statement st = conn.createStatement();
            st.executeUpdate(sql);
            st.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

}
