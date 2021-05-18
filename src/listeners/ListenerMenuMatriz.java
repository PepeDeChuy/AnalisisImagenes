/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import frames.JFramePrincipal;
import frames.JInternalFrameMatrizConvolucion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;

/**
 *
 * @author peper
 */
public class ListenerMenuMatriz implements ActionListener
{
    private JFramePrincipal JFP;
    
    public ListenerMenuMatriz(JFramePrincipal JFP) 
    {
        this.JFP = JFP;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        JMenuItem item = (JMenuItem) e.getSource();
        
        switch (item.getText())
        {
            case "Matriz de convolucion":
            {
                if(JFP.getJIFMC() == null)
                {
                    JInternalFrameMatrizConvolucion frameMatriz = new JInternalFrameMatrizConvolucion(this.JFP,"MC");
                    frameMatriz.setVisible(true);
                    this.JFP.getjDesktopPanePrincipal().add(frameMatriz);
                    this.JFP.setJIFMC(frameMatriz);
                }
                break;
            }
            case "Matriz de Kirsch":
            {
                if(JFP.getJIFMCK() == null)
                {
                    JInternalFrameMatrizConvolucion frameMatrizKi = new JInternalFrameMatrizConvolucion(this.JFP,"KI");
                    frameMatrizKi.setVisible(true);
                    this.JFP.getjDesktopPanePrincipal().add(frameMatrizKi);
                    this.JFP.setJIFMCK(frameMatrizKi);
                }
                break;
            }
            default:
            {
                break;
            }
        }
    }
    
}
