/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import frames.JFramePrincipal;
import frames.JInternalFrameGuardarImagen;
import frames.JInternalFrameImagen;
import frames.JInternalFrameRuidoSP;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;

/**
 *
 * @author peper
 */
public class ListenerMenuRuido implements ActionListener
{
    private JFramePrincipal JFP;

    public ListenerMenuRuido(JFramePrincipal JFP) 
    {
        this.JFP = JFP;
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        JMenuItem item = (JMenuItem) e.getSource();
        
        switch (item.getText())
        {
            case "Sal y Pimienta":
            {
                JInternalFrameRuidoSP frameSalPimienta = new JInternalFrameRuidoSP(this.JFP,"CR");
                frameSalPimienta.setVisible(true);
                this.JFP.getjDesktopPanePrincipal().add(frameSalPimienta);
                this.JFP.setJIFRSP(frameSalPimienta);
                break;
            }
            case "Eliminar ruido":
            {
                JInternalFrameRuidoSP frameSalPimientaB = new JInternalFrameRuidoSP(this.JFP,"BR");
                frameSalPimientaB.setVisible(true);
                this.JFP.getjDesktopPanePrincipal().add(frameSalPimientaB);
                this.JFP.setJIFRSPB(frameSalPimientaB);
                break;
            }
            default:
            {
                break;
            }
        }
    }
}
