/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package analisisimagenes;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author peper
 */
public class CraerImagen 
{
    public CraerImagen() 
    {
        Image preImagen;
        BufferedImage buffer;
        String name = "imagen";
        String ruta = "C:/Users/peper/Documents/New folder/";
        
        buffer = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);
        
        for(int x = 0; x < 10; x++)
        {
            for(int y = 0; y < 10; y++)
            {
                Color color = new Color(2, 2, 2);
                buffer.setRGB(x, y, color.getRGB());
            }
        }
        
        preImagen = herramientas.herramientas.toImage(buffer);
        
        if(!name.equals(""))
        {
            try 
            {
                if(ruta.equals(""))
                {
                    File outputfile = new File(name+".png");
                    ImageIO.write(herramientas.herramientas.toBufferedImage(preImagen), "png", outputfile);
                }
                else
                {
                    File outputfile = new File(ruta+name+".png");
                    ImageIO.write(herramientas.herramientas.toBufferedImage(preImagen), "png", outputfile);
                }
                
                JOptionPane.showMessageDialog(null, "Imagen creada correctamente", "Javadesde0.com", 1);
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Ingrese un nombre", "Javadesde0.com", 0);
        }
    }
    
    public CraerImagen(BufferedImage buffer)
    {
        
    }
    
    public static void main(String args[])
    {
        CraerImagen crearImagen = new CraerImagen();
    }
}
