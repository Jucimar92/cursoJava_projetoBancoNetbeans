/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifam.modelo;

import br.edu.ifam.jdbc.ConnectionFactory;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

import javax.imageio.stream.ImageOutputStream;
import javax.swing.ImageIcon;

/**
 *
 * @author Aluno
 */
public class ContatoDao {

    private Connection connection;

    public ContatoDao() {
        connection = new ConnectionFactory().getConnection();
    }

    public void adiciona(Contato contato) {
        PreparedStatement ps = null;
        String sql = "insert into contatos"
                + "(nome,email,endereco,dataNascimento) "
                + "values(?,?,?,?)";
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, contato.getNome());
            ps.setString(2, contato.getEmail());
            ps.setString(3, contato.getEndereco());
            Calendar nasc = contato.getDataNascimento();
            Date dataParaGravar = new Date(nasc.getTimeInMillis());
            ps.setDate(4, dataParaGravar);
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Contato getContato(Long idContato) {
        String sql = "select * from contatos where id=?";
        Contato contato = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setLong(1, idContato);
            rs = ps.executeQuery();
            if (rs.next()) {
                //Instancia a classe Contato
                contato = new Contato();
                // atribui os valores recuperados do banco 
                // para classe Contato
                contato.setId(rs.getLong("id"));
                contato.setNome(rs.getString("nome"));
                contato.setEmail(rs.getString("email"));
                contato.setEndereco(rs.getString("endereco"));
                Calendar dataNasc = Calendar.getInstance();
                dataNasc.setTime(rs.getDate("dataNascimento"));
            }
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.closeConnection(connection, ps, rs);//fecha conexão
        }
        return contato;
    }

    public List<Contato> getLista() {
        List<Contato> contatos = new ArrayList<>();
        String sql = "select * from contatos";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                //Instancia a classe Contato
                Contato contato = new Contato();
                // atribui os valores recuperados do banco 
                // para classe Contato
                contato.setId(rs.getLong("id"));
                contato.setNome(rs.getString("nome"));
                contato.setEmail(rs.getString("email"));
                contato.setEndereco(rs.getString("endereco"));
                Calendar dataNasc = Calendar.getInstance();
                dataNasc.setTime(rs.getDate("dataNascimento"));
                contato.setDataNascimento(dataNasc);
                Blob blob = rs.getBlob("foto");
                System.out.println(blob);
                if (blob != null) {
                    byte[] fotoBytes = blob.getBytes(1, (int) blob.length());
                    System.out.println("largura" + blob.length());
                    BufferedImage bImg = ImageIO.read(new ByteArrayInputStream(fotoBytes));
                    if (bImg == null) {
                        System.out.println("nao gerou imagem");
                        contato.setFoto(null);
                    } else {
                        contato.setFoto(new ImageIcon(bImg));
                    }
                } else {
                    System.out.println(contato.getId() + " nao tem imgagem");
                }
                // adiciona o contato no ArrayList
                contatos.add(contato);
            }
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException ex) {
            Logger.getLogger(ContatoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(connection, ps, rs);//fecha conexão
        }
        return contatos;
    }

    public void altera(Contato contato) {
        String sql = "update contatos set nome=?,email=?,"
                + "endereco=?, dataNascimento=? where id=?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, contato.getNome());
            ps.setString(2, contato.getEmail());
            ps.setString(3, contato.getEndereco());
            ps.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));
            ps.setLong(5, contato.getId());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.closeConnection(connection, ps);//fecha conexão
        }
        //atualizaFoto(contato);
        gravaFoto2(contato);
    }

    public void remove(Contato contato) {
        String sql = "delete from contatos where id=?";
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(sql);
            ps.setLong(1, contato.getId());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.closeConnection(connection, ps);//fecha conexão
        }
    }

    public void atualizaFoto(Contato contato) {
        // Coluna "coluna_imagem" do tipo BLOB
        String sql = "UPDATE contatos SET foto = ? where id=?";
        PreparedStatement ps = null;
        byte[] imgByte = null;
        try {
            System.out.println("Largura " + contato.getFoto().getIconWidth());
            System.out.println("Altura " + contato.getFoto().getIconHeight());
            BufferedImage bufImg = new BufferedImage(contato.getFoto().getIconWidth(),
                    contato.getFoto().getIconHeight(), BufferedImage.TYPE_INT_ARGB);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageOutputStream ios = ImageIO.createImageOutputStream(baos);
            ImageIO.write(bufImg, "gif", ios);
            System.out.println("Tamnho do vetor " + baos.size());
            imgByte = baos.toByteArray();
            ByteArrayInputStream baInputStm = new ByteArrayInputStream(imgByte);
            ps = connection.prepareStatement(sql);
            ps.setBlob(1, baInputStm);
            ps.setLong(2, contato.getId());
            ps.execute();
            ps.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionFactory.closeConnection(connection, ps);//fecha conexão
        }

    }

    public void gravaFoto(Contato contato) {
        String sql = "UPDATE contatos SET foto = ? where id=?";
        if (contato.getFoto() != null) {
            BufferedImage buffered = new BufferedImage(contato.getFoto().getIconWidth(),
                    contato.getFoto().getIconHeight(), Image.SCALE_SMOOTH);
            buffered.getGraphics().drawImage(contato.getFoto().getImage(), 0, 0, null);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] imageInByte = baos.toByteArray();
            PreparedStatement ps = null;
            try {
                ImageIO.write(buffered, "png", baos);
                Blob blob = connection.createBlob();
                blob.setBytes(1, imageInByte);
                ps = connection.prepareStatement(sql);
                ps.setBlob(1, blob);
                ps.setLong(2, contato.getId());
                ps.execute();
                ps.close();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } 
            finally {
                ConnectionFactory.closeConnection(connection, ps);//fecha conexão
            }
        }
    }

    public void gravaFoto2(Contato contato) {
        connection = new ConnectionFactory().getConnection();
        String sql = "UPDATE contatos SET foto = ? where id=?";
        PreparedStatement ps = null;
        try {
            FileInputStream fis = new FileInputStream(contato.getFotoFile());
            ps = connection.prepareStatement(sql);
            ps.setBinaryStream(1, fis, (int) contato.getFotoFile().length());
            ps.setLong(2, contato.getId());
            ps.execute();
            ps.close();
        } catch (IOException ex) {
            Logger.getLogger(ContatoDao.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ContatoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(connection, ps);//fecha conexão
        }

    }

}
