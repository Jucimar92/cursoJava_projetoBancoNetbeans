/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifam.teste;

import br.edu.ifam.jdbc.ConnectionFactory;
import br.edu.ifam.modelo.Contato;
import br.edu.ifam.modelo.ContatoDao;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Aluno
 */
public class InsereNotasNaAta {

    public static void main(String[] args) throws SQLException {
        Connection con = new ConnectionFactory().getConnection();
        Scanner ent=new Scanner(System.in);
        System.out.print("Curso :");              
        String curso=ent.nextLine();
        byte[] ptext = curso.getBytes(Charset.forName("UTF-8")); 
        String utfCurso=new String(ptext, Charset.forName("ISO-8859-1")); 
        
        System.out.print("Codigo da Turma :");
        String codTurma=ent.nextLine();
        System.out.print("Turma :");
        String turma=ent.nextLine();
       
        String sql = "select ensino_componente_curricular_detalhes_nome from disciplinas "+
                " where public_curso_nome='" +utfCurso+"'"+
        //String sql = "select ensino_componente_curricular_detalhes_nome from disciplinas where public_curso_nome='TÉCNICO DE NÍVEL MÉDIO EM ELETROTÉCNICA NA FORMA INTEGRADA'"+
                    " and id_turma_entrada='" +codTurma+"'"+
                    " order by ensino_componente_curricular_detalhes_nome";
        System.out.println(sql);
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            String disciplinas[]=new String[17];
            int indice=0;
            System.out.println("antes ");
            while (rs.next()) {
                // atribui os valores recuperados do banco 
                // para o vetor
                disciplinas[indice]=rs.getString(1);
                System.out.println(rs.getString(1));
                System.out.println("dentro");
                indice++;
            }
            st.close();
            for(String str: disciplinas){
               System.out.println(str);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    /*

        
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
    */
}

}
