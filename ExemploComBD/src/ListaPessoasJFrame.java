
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Jucibs
 */
public class ListaPessoasJFrame extends JFrame
        implements ActionListener, ItemListener {

    JComboBox comboNomes;
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/sistema";
    String user = "root";
    String password = "root";
    Connection conn;
    ResultSet resultSet;
    Statement st;
    private JButton btnIncluir, btnExcluir, btnPrimeiro,
            btnUltimo, btnProximo, btnAnterior;
    private JPanel pnlBotoes, pnlDados;
    private JTextField txtCodigo, txtNome;
    private JLabel lblCodigo, lblNome;

    public ListaPessoasJFrame() {
        super("Lista Pessoas Form");
        setLayout(new BorderLayout());
        comboNomes = new JComboBox();
        comboNomes.addItemListener(this);
        add(comboNomes, BorderLayout.NORTH);

        btnIncluir = new JButton("Incluir");
        btnExcluir = new JButton("Excluir");
        btnExcluir.addActionListener(this);
        btnPrimeiro = new JButton("<<");

        btnUltimo = new JButton(">>");
        btnProximo = new JButton(">");
        btnProximo.addActionListener(this);
        btnAnterior = new JButton("<");
        btnAnterior.addActionListener(this);
        pnlBotoes = new JPanel();
        pnlBotoes.add(btnIncluir);
        pnlBotoes.add(btnExcluir);
        pnlBotoes.add(btnPrimeiro);
        pnlBotoes.add(btnAnterior);
        pnlBotoes.add(btnProximo);
        pnlBotoes.add(btnUltimo);

        add(pnlBotoes, BorderLayout.SOUTH);
        txtCodigo = new JTextField(10);
        txtNome = new JTextField(40);
        lblCodigo = new JLabel("Código: ");
        lblNome = new JLabel("Nome: ");
        pnlDados = new JPanel();
        pnlDados.setLayout(new GridLayout(2, 2));
        pnlDados.add(lblCodigo);
        pnlDados.add(txtCodigo);
        pnlDados.add(lblNome);
        pnlDados.add(txtNome);
        add(pnlDados, BorderLayout.CENTER);
        setSize(500, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        insereDados();
    }

    public void insereDados() {
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, password);
            String sql = "SELECT CODIGO, NOME FROM PESSOA";
            st = conn.createStatement();
            resultSet = st.executeQuery(sql);
            while (resultSet.next()) {
                comboNomes.addItem(resultSet.getString("nome"));
            }
            resultSet.first();
            txtCodigo.setText(resultSet.getString("codigo"));
            txtNome.setText(resultSet.getString("nome"));

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ListaPessoasJFrame();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnProximo) {
            try {
                if (resultSet.next()) {
                    txtCodigo.setText(resultSet.getString("codigo"));
                    txtNome.setText(resultSet.getString("nome"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(ListaPessoasJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (e.getSource() == btnAnterior) {
            try {
                if (resultSet.previous()) {
                    txtCodigo.setText(resultSet.getString("codigo"));
                    txtNome.setText(resultSet.getString("nome"));
                }
            } catch (SQLException ex) {
                Logger.getLogger(ListaPessoasJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == btnExcluir) {
            // try {
            int resp = JOptionPane.showConfirmDialog(this, "Desejas Excluir os dados?",
                    "Confirma Exclusao",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            if (resp == JOptionPane.OK_OPTION) {
                String sql = "delete from pessoa where codigo=" + txtCodigo.getText();
                JOptionPane.showMessageDialog(this, "asdf", "asdf", 0);
            }
           // } catch (SQLException ex) {
            //     Logger.getLogger(ListaPessoasJFrame.class.getName()).log(Level.SEVERE, null, ex);
            // }

        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            try {
                resultSet.absolute(comboNomes.getSelectedIndex()+1);
                txtCodigo.setText(resultSet.getString("codigo"));
                txtNome.setText(resultSet.getString("nome"));
            } catch (SQLException ex) {
                Logger.getLogger(ListaPessoasJFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
}
