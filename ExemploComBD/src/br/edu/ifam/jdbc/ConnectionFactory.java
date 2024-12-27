/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifam.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Aluno
 */
public class ConnectionFactory {
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/sistema";
    String user = "root";
    String password = "root";

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url,user,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
     public static void closeConnection(Connection con) {

        if (con != null) {
            try {
                con.close();
            } catch (SQLException ex) {
                System.err.println("Erro ao fechar conexão: " + ex);
            }
        }
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt) {

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                System.err.println("Erro ao fechar conexão: " + ex);
            }
        }
        
        closeConnection(con);
    }
    
    public static void closeConnection(Connection con, PreparedStatement stmt,
            ResultSet rs) {

        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                System.err.println("Erro ao fechar conexão: " + ex);
            }
        }
        
        closeConnection(con, stmt);
    }
}
