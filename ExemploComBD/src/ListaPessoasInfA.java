
import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jucibs
 */
public class ListaPessoasInfA extends JFrame {

    JComboBox cbxNomes;
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/sistema";
    String user = "root";
    String password = "root";
    Connection conn;
    ResultSet rs;
    public ListaPessoasInfA() {
        setLayout(new BorderLayout());
        cbxNomes=new JComboBox();
        add(cbxNomes,BorderLayout.NORTH);
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            String sql = "Select * from Pessoa";
            PreparedStatement pst = conn.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                String nome=rs.getString("Nome");
                cbxNomes.addItem(nome);
            }
            pst.close();
            conn.close();
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        ListaPessoasInfA app=new ListaPessoasInfA();
        app.setSize(300,400);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }

}
