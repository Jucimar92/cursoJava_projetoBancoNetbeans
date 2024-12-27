
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
public class AdcionarDados {

    public static void main(String[] args) {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/sistema";
        String user = "root";
        String password = "root";
        Connection conn;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            Statement st=conn.createStatement();
            
            int id=4;
            String nome="MICHEL TEMER";
            String sql = "INSERT INTO PESSOA VALUES ("+
                    id+ ",'"+nome+"')";
            st.executeUpdate(sql);
            st.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

}
