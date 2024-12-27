
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aluno
 */
public class AdcionarDadosPrepare {

    public static void main(String[] args) {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/sistema";
        String user = "root";
        String password = "root";
        Connection conn;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            String sql = "insert into pessoa values (?,?)";
            PreparedStatement pst=conn.prepareStatement(sql);
            pst.setInt(1,8);
            pst.setString(2,"O proximo");
            pst.executeUpdate();
            pst.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

}
