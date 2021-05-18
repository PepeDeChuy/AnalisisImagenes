/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import frames.JFramePrincipal;
import frames.JInternalFrameCopear;
import frames.JInternalFrameFiltros;
import frames.JInternalFrameIluminacion;
import frames.JInternalFrameImagen;
import frames.JInternalFrameMatrizConvolucion;
import frames.JInternalFrameModificar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;

/**
 *
 * @author peper
 */
public class ListenerMenuEspacial implements ActionListener
{
    private JFramePrincipal JFP;

    public ListenerMenuEspacial(JFramePrincipal JFP) 
    {
        this.JFP = JFP;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        JMenuItem item = (JMenuItem) ae.getSource();
        switch (item.getText())
        {
            case "Modificar":
            {
                if(JFP.getJIFM() == null)
                {
                    JInternalFrameModificar frameModificar = new JInternalFrameModificar(JFP);
                    frameModificar.setVisible(true);
                    this.JFP.getjDesktopPanePrincipal().add(frameModificar);
                    this.JFP.setJIFM(frameModificar);
                }
                break;
            }
            case "Copear pixeles":
            {
                if(JFP.getJIFC() == null)
                {
                    JInternalFrameCopear frameCopear = new JInternalFrameCopear(JFP);
                    frameCopear.setVisible(true);
                    this.JFP.getjDesktopPanePrincipal().add(frameCopear);
                    this.JFP.setJIFC(frameCopear);
                }
                break;
            }
            case "Escala Grises":
            {
                if(JFP.getJIFFEG() == null)
                {
                    JInternalFrameFiltros frameFiltrosEG = new JInternalFrameFiltros(this.JFP,"Escala Grises");
                    frameFiltrosEG.setVisible(true);
                    this.JFP.getjDesktopPanePrincipal().add(frameFiltrosEG);
                    this.JFP.setJIFFEG(frameFiltrosEG);
                }
                break;
            }
            case "Negativo":
            {
                if(JFP.getJIFFNG() == null)
                {
                    JInternalFrameFiltros frameFiltrosNG = new JInternalFrameFiltros(this.JFP,"Negativo");
                    frameFiltrosNG.setVisible(true);
                    this.JFP.getjDesktopPanePrincipal().add(frameFiltrosNG);
                    this.JFP.setJIFFNG(frameFiltrosNG);
                }
                break;
            }
            case "Blanco y negro":
            {
                if(JFP.getJIFFBN() == null)
                {
                    JInternalFrameFiltros frameFiltrosBN = new JInternalFrameFiltros(this.JFP,"Blanco y negro");
                    frameFiltrosBN.setVisible(true);
                    this.JFP.getjDesktopPanePrincipal().add(frameFiltrosBN);
                    this.JFP.setJIFFNG(frameFiltrosBN);
                }
                break;
            }
            case "Blanco y negro doble U":
            {
                if(JFP.getJIFFBN2() == null)
                {
                    JInternalFrameFiltros frameFiltrosBN2 = new JInternalFrameFiltros(this.JFP,"Blanco y negro doble U");
                    frameFiltrosBN2.setVisible(true);
                    this.JFP.getjDesktopPanePrincipal().add(frameFiltrosBN2);
                    this.JFP.setJIFFNG(frameFiltrosBN2);
                }
                break;
            }
            case "Blanco y negro Umbral Automatico":
            {
                if(JFP.getJIFFBN2() == null)
                {
                    JInternalFrameFiltros frameFiltrosBNA = new JInternalFrameFiltros(this.JFP,"Blanco y negro Umbral Automatico");
                    frameFiltrosBNA.setVisible(true);
                    this.JFP.getjDesktopPanePrincipal().add(frameFiltrosBNA);
                    this.JFP.setJIFFBNA(frameFiltrosBNA);
                }
                break;
            }
            case "Iluminacion":
            {
                if(JFP.getJIFIL() == null)
                {
                    JInternalFrameIluminacion frameIluminacion = new JInternalFrameIluminacion(this.JFP);
                    frameIluminacion.setVisible(true);
                    this.JFP.getjDesktopPanePrincipal().add(frameIluminacion);
                    this.JFP.setJIFIL(frameIluminacion);
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
