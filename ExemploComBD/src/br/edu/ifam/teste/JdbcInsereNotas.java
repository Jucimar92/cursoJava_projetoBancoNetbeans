/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifam.teste;

import br.edu.ifam.jdbc.ConnectionFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aluno
 */
public class JdbcInsereNotas {

    public static void main(String[] args) throws SQLException {
        Connection con = new ConnectionFactory().getConnection();

        // Ler arquivo
        File arquivo = new File("C:\\Users\\Jucibs\\Documents\\ifam\\NotasCMC_15052019_utf8.CSV");
        StringBuffer sb = new StringBuffer();
        try {
            FileReader reader = new FileReader(arquivo);
            BufferedReader bufReader = new BufferedReader(reader);
            String s;
            Statement st = con.createStatement();    
            int conta=0;
            do {
                s = bufReader.readLine();
                if (conta==0){
                    conta++;
                    continue;
                }
                conta++;
                String dados[]=s.split(";");
                String sql = "insert into dadosnotas "
                 + "values(";
                int contaCampos=0;
                for(String info : dados){
                   // String tmp = new String(info.getBytes("UTF-8"));
                    contaCampos++;
                    if(contaCampos!=17)
                       sql+="'"+info+"',";
                      else
                       sql+="'"+info+"')";
                   //System.out.print();
                }
                //System.out.println(sql+"\n"+contaCampos);
            
                st.executeUpdate(sql);
                if (s != null) {
                    sb.append(s + "\r\n");
                }
                
            } while (s != null);
            bufReader.close();
            reader.close();
            st.close();
            con.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        //return sb.toString();
    /*

    

    ps.setString (

    1, "Maria Claudia");
    ps.setString (

    2, "mclaudia@ifam.edu.br");
    ps.setString (
    3, "Rua Z,1234");
        Calendar nasc = Calendar.getInstance();

    nasc.set (
    2007, 2, 31);
            Date dataParaGravar = new Date(nasc.getTimeInMillis());

    ps.setDate (

    4, dataParaGravar);
    ps.executeUpdate ();

    ps.close ();*/
}

}
