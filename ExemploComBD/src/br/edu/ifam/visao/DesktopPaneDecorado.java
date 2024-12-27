/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifam.visao;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;

/**
 *
 * @author Jucimar Brito
 */
public class DesktopPaneDecorado extends JDesktopPane {

    private Image img;

    public DesktopPaneDecorado() {
        super();
        img = new javax.swing.ImageIcon(
          getClass().getResource("/icones/livros.png")).getImage();
               
        

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        

        if (img != null) {
            g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
            
        } else {
            g.drawString("Image not found", 50, 50);
        }
        /* O repaint e o VAlidade aparentimente n fazem diferença. Caso
          encontrem algum bom motivo para usa-los, postem, please! xD
         */
        //super.repaint();
        //super.validate();
    }
}
