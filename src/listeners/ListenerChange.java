/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listeners;

import frames.JInternalFrameFiltros;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author peper
 */
public class ListenerChange implements ChangeListener
{

    JInternalFrameFiltros JIFF;
    
    public ListenerChange(JInternalFrameFiltros JIFF) 
    {
        this.JIFF = JIFF;
    }
    

    @Override
    public void stateChanged(ChangeEvent ce) 
    {
        int valorx = JIFF.getjSlider_BN().getValue();
        
        JIFF.getjSlider_BN2().setMinimum(valorx);
        
        //System.out.println("Valor1: "+JIFF.getjSlider_BN().getValue()+" valor2:"+JIFF.getjSlider_BN2().getValue());
    }
    
}
