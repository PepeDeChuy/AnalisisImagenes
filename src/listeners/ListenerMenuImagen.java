/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import frames.JFramePrincipal;
import frames.JInternalFrameGuardarImagen;
import frames.JInternalFrameImagen;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;

/**
 *
 * @author peper
 */
public class ListenerMenuImagen implements ActionListener
{
    private JFramePrincipal JFP;

    public ListenerMenuImagen(JFramePrincipal JFP) 
    {
        this.JFP = JFP;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        JMenuItem item = (JMenuItem) ae.getSource();
        
        switch (item.getText())
        {
            case "Abrir Imagen":
            {
                JInternalFrameImagen frameImagen = new JInternalFrameImagen();
                frameImagen.setVisible(true);
                frameImagen.addInternalFrameListener(new ListenerInternalFrame(JFP,frameImagen));
                this.JFP.getjDesktopPanePrincipal().add(frameImagen);
                this.JFP.setJIFI(frameImagen);
                frameImagen.setJFP(JFP);
                break;
            }
            case "Guardar Imagen":
            {
                JInternalFrameGuardarImagen frameGuardar = new JInternalFrameGuardarImagen();
                frameGuardar.setVisible(true);
                frameGuardar.addInternalFrameListener(new ListenerInternalFrame(JFP));
                this.JFP.getjDesktopPanePrincipal().add(frameGuardar);
                this.JFP.setJIFGI(frameGuardar);
                frameGuardar.setJFP(JFP);
                break;
            }
            default:
            {
                break;
            }
        }
    }
    
}
