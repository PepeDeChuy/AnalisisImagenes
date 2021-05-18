/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import frames.JFramePrincipal;
import frames.JInternalFrameImagen;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

/**
 *
 * @author peper
 */
public class ListenerInternalFrame implements InternalFrameListener
{
    private JFramePrincipal JFP;
    private JInternalFrameImagen JFI;

    public ListenerInternalFrame(JFramePrincipal JFP, JInternalFrameImagen JFI) 
    {
        this.JFP = JFP;
        this.JFI = JFI;
    }
    
    public ListenerInternalFrame(JFramePrincipal JFP) 
    {
        this.JFP = JFP;
        this.JFI = null;
    }
    
    @Override
    public void internalFrameOpened(InternalFrameEvent ife) {}

    @Override
    public void internalFrameClosing(InternalFrameEvent ife) {}

    @Override
    public void internalFrameClosed(InternalFrameEvent ife) {}

    @Override
    public void internalFrameIconified(InternalFrameEvent ife) {}

    @Override
    public void internalFrameDeiconified(InternalFrameEvent ife) {
    }

    @Override
    public void internalFrameActivated(InternalFrameEvent ife) 
    {
        if(JFI == null)
        {
            JFP.getJIFGI().setImagen();
        }
        else
        {
            JFP.setJIFI(JFI);
        }
    }

    @Override
    public void internalFrameDeactivated(InternalFrameEvent ife){}
    
}
