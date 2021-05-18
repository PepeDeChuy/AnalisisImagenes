/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package herramientas;

import frames.JFramePrincipal;
import frames.JInternalFrameImagen;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import listeners.ListenerInternalFrame;

/**
 *
 * @author peper
 */
public class herramientas 
{
    public static BufferedImage toBufferedImage (Image imagen)
    {
         // imagen es un objeto de tipo BufferedImage
        if (imagen instanceof BufferedImage)
        {
          return (BufferedImage)imagen;
        }
        
        BufferedImage bi = new BufferedImage(
                                            imagen.getWidth(null), 
                                            imagen.getHeight(null), 
                                            BufferedImage.TYPE_INT_RGB
                                            );
        
        Graphics2D nueva = bi.createGraphics();
        nueva.drawImage(imagen, 0, 0,null);
        nueva.dispose();
        
        return bi;
    }
    
    public static Image toImage(BufferedImage bi)
    {
        return bi.getScaledInstance(bi.getWidth(),bi.getHeight(), BufferedImage.TYPE_INT_RGB);
    }
    
    public static void generarVentana(Image preImagen, JFramePrincipal JFP)
    {
        if(preImagen !=  null)
        {
            JInternalFrameImagen JFINew = new JInternalFrameImagen(preImagen);
            JFINew.setVisible(true);
            JFINew.addInternalFrameListener(new ListenerInternalFrame(JFP,JFINew));
            JFP.getjDesktopPanePrincipal().add(JFINew);
        }
    }
}
