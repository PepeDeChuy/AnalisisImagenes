/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import static herramientas.herramientas.toBufferedImage;
import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.AncestorListener;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import listeners.ListenerChange;

/**
 *
 * @author peper
 */
public class JInternalFrameFiltros extends javax.swing.JInternalFrame 
{
    /**
     * Creates new form JInternalFrameFiltros
     */
    
    private BufferedImage bufferimagen;
    private Image preImagen;
    private JFramePrincipal JFP;
    
    private int h;
    
    public JInternalFrameFiltros(JFramePrincipal JFP,String metodo) 
    {
        this.JFP = JFP;
        initComponents();
        jSlider_BN.setMaximum(255);
        jSlider_BN.setMinimum(0);
        
        jSlider_BN2.setMaximum(255);
        jSlider_BN2.setMinimum(0);
        
        System.out.println("Num: "+ this.getSize().width);
        this.preImagen = null;
        
        jLabelImagen.setText("");
        
        switch (metodo)
        {
            case "Escala Grises":
            {
                jSlider_BN.setVisible(false);
                jSlider_BN2.setVisible(false);
                jPanelBotones.setLocation(0, 0);
                jPanelImagen.setLocation(100, 0);
                this.setSize(this.getSize().width, 100);
                this.h = 110;
                
                jButton_Filtro.setText("Escala Grises");
                jButton_Filtro.addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent ae) 
                    {
                        listenerEG();
                    }
                });
                
                addInternalFrameListener(new InternalFrameAdapter(){
                    public void internalFrameClosing(InternalFrameEvent e) 
                    {
                        JFP.setJIFFEG(null);
                    }
                });
                break;
            }
            case "Negativo":
            {
                jSlider_BN.setVisible(false);
                jSlider_BN2.setVisible(false);
                jPanelBotones.setLocation(0, 0);
                this.setSize(this.getSize().width, 100);
                this.h = 110;
                
                jButton_Filtro.setText("Negativo");
                jButton_Filtro.addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent ae) 
                    {
                        listenerN();
                    }
                });
                
                addInternalFrameListener(new InternalFrameAdapter(){
                    public void internalFrameClosing(InternalFrameEvent e) 
                    {
                        JFP.setJIFFNG(null);
                    }
                });
                break;
            }
            case "Blanco y negro":
            {
                jSlider_BN.setVisible(true);
                jSlider_BN2.setVisible(false);
                jPanelBotones.setLocation(44, 0);
                
                this.setSize(this.getSize().width, 150);
                this.h = 160;
                
                jButton_Filtro.setText("Blanco y negro");
                jButton_Filtro.addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent ae) 
                    {
                        listenerBN();
                    }
                });
                
                addInternalFrameListener(new InternalFrameAdapter(){
                    public void internalFrameClosing(InternalFrameEvent e) 
                    {
                        JFP.setJIFFBN(null);
                    }
                });
                break;
            }
            case "Blanco y negro doble U":
            {
                jSlider_BN.setVisible(true);
                jSlider_BN2.setVisible(true);
                
                int valorx = getjSlider_BN().getValue();
                getjSlider_BN2().setMinimum(valorx);
                
                this.setSize(this.getSize().width, 200);
                this.h = 210;
                
                jSlider_BN.addChangeListener(new ListenerChange(this));
                jButton_Filtro.setText("Blanco y negro");
                //System.out.println("valor1: "+jSlider_BN.getValue()+" valor2: "+jSlider_BN2.getValue());
                jButton_Filtro.addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent ae) 
                    {
                        listenerBN2();
                    }
                });
                
                addInternalFrameListener(new InternalFrameAdapter(){
                    public void internalFrameClosing(InternalFrameEvent e) 
                    {
                        JFP.setJIFFBN2(null);
                    }
                });
                break;
            }
            case "Blanco y negro Umbral Automatico":
            {
                jSlider_BN.setVisible(false);
                jSlider_BN2.setVisible(false);
                jPanelBotones.setLocation(0, 0);
                this.setSize(this.getSize().width, 100);
                this.h = 110;
                
                jButton_Filtro.setText("Blanco y negro Automatico");
                jButton_Filtro.addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent ae) 
                    {
                        listenerBNA();
                    }
                });
                
                addInternalFrameListener(new InternalFrameAdapter(){
                    public void internalFrameClosing(InternalFrameEvent e) 
                    {
                        JFP.setJIFFBNA(null);
                    }
                });
                break;
            }
            default:
            {
                break;
            }
        }
        
        this.jButton_Generar.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                herramientas.herramientas.generarVentana(preImagen, JFP);
            }
        });
    }
    
    public void listenerEG()
    {
        Image aux = JFP.getJIFI().getImagenSecundaria();
        BufferedImage aux2 = herramientas.herramientas.toBufferedImage(aux);
        int w = aux.getWidth(this);
        int h = aux.getHeight(this);
        BufferedImage aux1 = new BufferedImage (w, h,BufferedImage.TYPE_BYTE_GRAY);
        
        for(int x = 0; x < w; x++)
        {
            for(int y = 0; y < h; y++)
            {
                aux1.setRGB(x, y, aux2.getRGB(x, y));
            } 
        }
        
        setImagen(herramientas.herramientas.toImage(aux1));
    }
    
    public void listenerN()
    {
        Image aux = JFP.getJIFI().getImagenSecundaria();
        BufferedImage aux2 = herramientas.herramientas.toBufferedImage(aux);
        int w = aux.getWidth(this);
        int h = aux.getHeight(this);
        BufferedImage aux1 = herramientas.herramientas.toBufferedImage(aux);
        Color color;
        int r,g,b;
        
        for(int x = 0; x < w; x++)
        {
            for(int y = 0; y < h; y++)
            {
                //se obtiene el color del pixel
                color = new Color(aux2.getRGB(x, y));
                //se extraen los valores RGB
                r = color.getRed();
                g = color.getGreen();
                b = color.getBlue();
                //se coloca en la nueva imagen con los valores invertidos
                aux1.setRGB(x, y, new Color(255-r,255-g,255-b).getRGB());
            } 
        }
        setImagen(herramientas.herramientas.toImage(aux1));
    }
    
    public void listenerBN()
    {
        int valor = getjSlider_BN().getValue();
        Image aux = JFP.getJIFI().getImagenSecundaria();
        BufferedImage aux2 = herramientas.herramientas.toBufferedImage(aux);
        int w = aux.getWidth(this);
        int h = aux.getHeight(this);
        BufferedImage aux1 = new BufferedImage (w, h,BufferedImage.TYPE_BYTE_BINARY);
        
        Color color;
        int r,g,b;
        for(int x = 0; x < w; x++)
        {
            for(int y = 0; y < h; y++)
            {
                //se obtiene el color del pixel
                color = new Color(aux2.getRGB(x, y));
                //se extraen los valores RGB
                if(color.getRed() < valor)
                {
                    r = 0; 
                }
                else
                {
                    r = 255;
                }
                
                if(color.getGreen() < valor)
                {
                    g = 0; 
                }
                else
                {
                    g = 255;
                }
                
                if(color.getBlue() < valor)
                {
                    b = 0; 
                }
                else
                {
                    b = 255;
                }
                //se coloca en la nueva imagen con los valores invertidos
                aux1.setRGB(x, y, new Color(r,g,b).getRGB());
            } 
        }
        
        setImagen(herramientas.herramientas.toImage(aux1));
    }
    
    public void listenerBN2()
    {
        int valorMin = getjSlider_BN().getValue();
        int valorMax = getjSlider_BN2().getValue();
        Image aux = JFP.getJIFI().getImagenSecundaria();
        BufferedImage aux2 = herramientas.herramientas.toBufferedImage(aux);
        int w = aux.getWidth(this);
        int h = aux.getHeight(this);
        BufferedImage aux1 = new BufferedImage (w, h,BufferedImage.TYPE_BYTE_BINARY);
        
        Color color;
        int r,g,b;
        for(int x = 0; x < w; x++)
        {
            for(int y = 0; y < h; y++)
            {
                //se obtiene el color del pixel
                color = new Color(aux2.getRGB(x, y));
                //se extraen los valores RGB
                if((color.getRed()>= valorMin) && (color.getRed()<= valorMax))
                {
                    //r = 255;
                    r = 0;
                }
                else
                {
                    //r = 0;
                    r = 255;
                }
                
                if((color.getGreen()>= valorMin) && (color.getGreen()<= valorMax))
                {
                    //g = 0;
                    g = 255;
                }
                else
                {
                    //g = 255;
                    g = 0;
                }
                
                if((color.getBlue()>= valorMin) && (color.getBlue()<= valorMax))
                {
                    //b = 0;+
                    b = 255;
                }
                else
                {
                    //b = 255;
                    b = 0;
                }
                //se coloca en la nueva imagen con los valores invertidos
                aux1.setRGB(x, y, new Color(r,g,b).getRGB());
            } 
        }
        
        setImagen(herramientas.herramientas.toImage(aux1));
    }
    
    public void listenerBNA()
    {
        Image aux = JFP.getJIFI().getImagenSecundaria();
        BufferedImage aux2 = herramientas.herramientas.toBufferedImage(aux);
        int w = aux.getWidth(this);
        int h = aux.getHeight(this);
        BufferedImage aux1 = new BufferedImage (w, h,BufferedImage.TYPE_BYTE_BINARY);
        
        Color color;
        
        int[] arrayR = new int[256];
        int[] arrayG = new int[256];
        int[] arrayB = new int[256];
        
        for(int x = 0; x < w; x++)
        {
            for(int y = 0; y < h; y++)
            {
                color = new Color(aux2.getRGB(x, y));
                arrayR[color.getRed()]++;
                arrayG[color.getGreen()]++;
                arrayB[color.getBlue()]++;
            } 
        }
        
        int auxIndiceR = 0;
        int auxIndiceG = 0;
        int auxIndiceB = 0;
        
        for(int x = 0; x < 255; x++)
        {
            if(arrayR[auxIndiceR] > arrayR[x])
            {
                auxIndiceR = x;
            }
            if(arrayR[auxIndiceG] > arrayG[x])
            {
                auxIndiceG = x;
            }
            if(arrayR[auxIndiceB] > arrayB[x])
            {
                auxIndiceB = x;
            }
        }
        
        int r,g,b;
        for(int x = 0; x < w; x++)
        {
            for(int y = 0; y < h; y++)
            {
                //se obtiene el color del pixel
                color = new Color(aux2.getRGB(x, y));
                //se extraen los valores RGB
                if(color.getRed()>= auxIndiceR)
                {
                    r = 255;
                    //r = 0;
                }
                else
                {
                    r = 0;
                    //r = 255;
                }
                
                if(color.getGreen()>= auxIndiceG)
                {
                    g = 0;
                    //g = 255;
                }
                else
                {
                    g = 255;
                    //g = 0;
                }
                
                if(color.getBlue()>= auxIndiceB)
                {
                    b = 0;
                    //b = 255;
                }
                else
                {
                    b = 255;
                    //b = 0;
                }
                //se coloca en la nueva imagen con los valores invertidos
                aux1.setRGB(x, y, new Color(r,g,b).getRGB());
            } 
        }
        JOptionPane.showMessageDialog(null, "Valor de U: R="+auxIndiceR+" G="+auxIndiceG+" B="+auxIndiceB);
        setImagen(herramientas.herramientas.toImage(aux1));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSlider1 = new javax.swing.JSlider();
        jLabel_Imagen = new javax.swing.JLabel();
        jLabel_PreImagen = new javax.swing.JLabel();
        jSlider_BN = new javax.swing.JSlider();
        jSlider_BN2 = new javax.swing.JSlider();
        jPanelBotones = new javax.swing.JPanel();
        jButton_Filtro = new javax.swing.JButton();
        jButton_Generar = new javax.swing.JButton();
        jPanelImagen = new javax.swing.JPanel();
        jLabelImagen = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);

        jSlider_BN.setMajorTickSpacing(10);
        jSlider_BN.setPaintLabels(true);
        jSlider_BN.setPaintTicks(true);

        jSlider_BN2.setMajorTickSpacing(10);
        jSlider_BN2.setPaintLabels(true);
        jSlider_BN2.setPaintTicks(true);

        jButton_Filtro.setText("Filtro");
        jButton_Filtro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_FiltroActionPerformed(evt);
            }
        });

        jButton_Generar.setText("Generar ventana");
        jButton_Generar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_GenerarActionPerformed(evt);
            }
        });

        jLabelImagen.setText("jLabel1");

        javax.swing.GroupLayout jPanelImagenLayout = new javax.swing.GroupLayout(jPanelImagen);
        jPanelImagen.setLayout(jPanelImagenLayout);
        jPanelImagenLayout.setHorizontalGroup(
            jPanelImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelImagenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelImagen)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelImagenLayout.setVerticalGroup(
            jPanelImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelImagenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelImagen)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanelBotonesLayout = new javax.swing.GroupLayout(jPanelBotones);
        jPanelBotones.setLayout(jPanelBotonesLayout);
        jPanelBotonesLayout.setHorizontalGroup(
            jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton_Filtro, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_Generar, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanelBotonesLayout.createSequentialGroup()
                .addComponent(jPanelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanelBotonesLayout.setVerticalGroup(
            jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelBotonesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Filtro, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Generar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel_PreImagen)
                .addGap(32, 32, 32)
                .addComponent(jLabel_Imagen)
                .addGap(224, 224, 224))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSlider_BN2, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSlider_BN, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(114, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSlider_BN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSlider_BN2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 269, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_Imagen)
                    .addComponent(jLabel_PreImagen)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_FiltroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_FiltroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_FiltroActionPerformed

    private void jButton_GenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_GenerarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_GenerarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Filtro;
    private javax.swing.JButton jButton_Generar;
    private javax.swing.JLabel jLabelImagen;
    private javax.swing.JLabel jLabel_Imagen;
    private javax.swing.JLabel jLabel_PreImagen;
    private javax.swing.JPanel jPanelBotones;
    private javax.swing.JPanel jPanelImagen;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JSlider jSlider_BN;
    private javax.swing.JSlider jSlider_BN2;
    // End of variables declaration//GEN-END:variables

    
    public void setImagen(Image nuevaImagen)
    {
        preImagen = nuevaImagen;
        jLabelImagen.removeAll();
        jLabelImagen.setIcon(new ImageIcon(nuevaImagen));
        
        if(nuevaImagen.getWidth(null) < 672)
        {
            setSize(672,(nuevaImagen.getHeight(null) + this.h));
        }
        else
        {
            setSize(nuevaImagen.getWidth(null),(nuevaImagen.getHeight(null) + this.h));
        }
        
    }

    /**
     * @return the jSlider_BN
     */
    public javax.swing.JSlider getjSlider_BN() {
        return jSlider_BN;
    }

    /**
     * @param jSlider_BN the jSlider_BN to set
     */
    public void setjSlider_BN(javax.swing.JSlider jSlider_BN) {
        this.jSlider_BN = jSlider_BN;
    }

    /**
     * @return the jSlider_BN2
     */
    public javax.swing.JSlider getjSlider_BN2() {
        return jSlider_BN2;
    }

    /**
     * @param jSlider_BN2 the jSlider_BN2 to set
     */
    public void setjSlider_BN2(javax.swing.JSlider jSlider_BN2) {
        this.jSlider_BN2 = jSlider_BN2;
    }
}
