/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg1819_p2si;

/**
 *
 * @author fidel
 */
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
  
public class MostrarImagen extends JPanel
{
    Image image;
  
    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if(image != null)
            g.drawImage(image, 0, 0, this);
    }
    
    void setImage(BufferedImage bimage){
        image = bimage.getScaledInstance(500,500,Image.SCALE_SMOOTH);
    }
    
    void setImage(Imagen img){
        image = img.getBufferedImage().getScaledInstance(500,500,Image.SCALE_SMOOTH);
    }
    
    void mostrar(){
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(500,500);
        f.setLocation(200,200);
        f.add(this);
        f.setVisible(true);
    }
  
  
}
