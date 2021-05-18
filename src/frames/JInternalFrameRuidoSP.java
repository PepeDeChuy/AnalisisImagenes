package frames;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

public class JInternalFrameRuidoSP extends javax.swing.JInternalFrame 
{
    private JFramePrincipal JFP;
    private Image preImagen;
    
    private int w = 0, h = 0;
    
    public JInternalFrameRuidoSP(JFramePrincipal JFP, String metodo) 
    {
        this.JFP = JFP;
        initComponents();
        
        jLabel_Imagen.setText("");
        
        switch (metodo)
        {
            case "CR":
            {
                cr();
                jPanel_CR.setLocation(0, 0);
                setSize(895, 220);
                w = 895;
                h = 230;
                jPanel_BR.setVisible(false);
                break;
            }
            case "BR":
            {
                br();
                jPanel_BR.setLocation(0, 0);
                setSize(470, 155);
                w = 470;
                h = 160;
                jPanel_CR.setVisible(false);
                break;
            }
        }
    }
    
    private void cr()
    {
        jSlider_P.setMaximum(100);
        jSlider_P.setMinimum(0);
        
        jButton_Sal.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                preImagen = JFP.getJIFI().getImagenSecundaria();
                sal(jSlider_P.getValue());
            }
        });
        
        jButton_Pimienta.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                preImagen = JFP.getJIFI().getImagenSecundaria();
                pimienta(jSlider_P.getValue());
            }
        });
        
        jButton_Ambos.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                preImagen = JFP.getJIFI().getImagenSecundaria();
                sal(jSlider_P.getValue()/2);
                pimienta(jSlider_P.getValue()/2);
            }
        });
        
        jButton_GV.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                herramientas.herramientas.generarVentana(preImagen, JFP);
            }
        });
        
        addInternalFrameListener(new InternalFrameAdapter()
        {
            public void internalFrameClosing(InternalFrameEvent e) 
            {
                JFP.setJIFRSP(null);
            }
        });
    }
    
    private void br()
    {
        jButton_Sal_B.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                preImagen = JFP.getJIFI().getImagenSecundaria();
                salB();
            }
        });
        
        jButton_Pimienta_B.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                preImagen = JFP.getJIFI().getImagenSecundaria();
                pimientaB();
            }
        });
        
        jButton_Ambos_B.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                preImagen = JFP.getJIFI().getImagenSecundaria();
                salB();
                pimientaB();
            }
        });
        
        jButton_GV_B.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                herramientas.herramientas.generarVentana(preImagen, JFP);
            }
        });
        
        addInternalFrameListener(new InternalFrameAdapter()
        {
            public void internalFrameClosing(InternalFrameEvent e) 
            {
                JFP.setJIFRSPB(null);
            }
        });
    }
    
    /* CR - CR - CR - CR - CR - CR - CR - CR - CR - CR - CR - CR - CR - CR - CR - CR - CR - CR - CR - CR - CR*/
    private void sal(int porcentaje)
    {
        BufferedImage aux2 = herramientas.herramientas.toBufferedImage(preImagen);
        int totalPixeles = ((aux2.getHeight() * aux2.getWidth()) * porcentaje) / 100;
        
        alterar(totalPixeles, aux2, Color.WHITE);
    }
    
    private void pimienta(int porcentaje)
    {
        BufferedImage aux2 = herramientas.herramientas.toBufferedImage(preImagen);
        int totalPixeles = ((aux2.getHeight() * aux2.getWidth()) * porcentaje) / 100;
        
        alterar(totalPixeles, aux2, Color.BLACK);
    }
    
    private void alterar(int totalPixeles, BufferedImage aux2, Color c)
    {
        int cont = 0;
        
        while(cont != totalPixeles)
        {
            int wAux = (int) (Math.random()*aux2.getWidth());
            int hAux = (int) (Math.random()*aux2.getHeight());
            
            aux2.setRGB(wAux, hAux, c.getRGB());
            cont++;
        }
        
        preImagen = herramientas.herramientas.toImage(aux2);
        setImagen();
    }
    
    /* CR - CR - CR - CR - CR - CR - CR - CR - CR - CR - CR - CR - CR - CR - CR - CR - CR - CR - CR - CR - CR*/
    
    /* BR - BR - BR - BR - BR - BR - BR - BR - BR - BR - BR - BR - BR - BR - BR - BR - BR - BR - BR - BR - BR */
    
    private void salB()
    {
        BufferedImage aux2 = herramientas.herramientas.toBufferedImage(preImagen);
        
        alterarB(aux2, 255);
    }
    
    private void pimientaB()
    {
        BufferedImage aux2 = herramientas.herramientas.toBufferedImage(preImagen);
        
        alterarB(aux2, 0);
    }
    
    private void alterarB(BufferedImage aux2, int valor)
    {
        BufferedImage aux = herramientas.herramientas.toBufferedImage(preImagen);
        
        for(int x = 0; x < aux2.getWidth(); x++)
        {
            for(int y = 0; y < aux2.getHeight(); y++)
            {
                Color colorAux = new Color(aux2.getRGB(x, y));
                if(colorAux.getRed() == valor && colorAux.getGreen() == valor && colorAux.getBlue() == valor)
                {
                    aux.setRGB(x, y, nuevoColor(x,y,aux2,valor).getRGB());
                }
            }
        }
        
        preImagen = herramientas.herramientas.toImage(aux);
        setImagen();
    }
    
    private Color nuevoColor(int x, int y, BufferedImage aux2, int SP)
    {
        int rgb = 0;
        
        if(SP == 0)
        {
            rgb = -16777216;
        }
        else
        {
            rgb = -1;
        }
        
        for(int i = x-1; i < x+1; i++)
        {
            for(int m = y-1; m < y+1; m++)
            {
                if(x > 0 && x < aux2.getWidth() && y > 0 && y < aux2.getHeight())
                {
                    if(SP == 0)
                    {
                        if(rgb < aux2.getRGB(i, m))
                        {
                            rgb = aux2.getRGB(i, m);
                        }
                    }
                    else
                    {
                        if(rgb > aux2.getRGB(i, m))
                        {
                            rgb = aux2.getRGB(i, m);
                        }
                    }
                }
            }
        }
        
        return new Color(rgb);
    }
    
    /* BR - BR - BR - BR - BR - BR - BR - BR - BR - BR - BR - BR - BR - BR - BR - BR - BR - BR - BR - BR - BR */
    
    public void setImagen()
    {
        jLabel_Imagen.removeAll();
        jLabel_Imagen.setIcon(new ImageIcon(preImagen));
        
        if(preImagen.getWidth(null) < w)
        {
            setSize(w,(preImagen.getHeight(null)) + h);
        }
        else
        {
            setSize(preImagen.getWidth(null),(preImagen.getHeight(null)) + h);
        }
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel_Imagen = new javax.swing.JLabel();
        jPanel_CR = new javax.swing.JPanel();
        jSlider_P = new javax.swing.JSlider();
        jButton_Sal = new javax.swing.JButton();
        jButton_Pimienta = new javax.swing.JButton();
        jButton_Ambos = new javax.swing.JButton();
        jButton_GV = new javax.swing.JButton();
        jPanel_BR = new javax.swing.JPanel();
        jButton_Sal_B = new javax.swing.JButton();
        jButton_Pimienta_B = new javax.swing.JButton();
        jButton_Ambos_B = new javax.swing.JButton();
        jButton_GV_B = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);

        jLabel_Imagen.setText("jLabel1");

        jPanel_CR.setBackground(new java.awt.Color(50, 50, 50));

        jSlider_P.setMajorTickSpacing(10);
        jSlider_P.setMinorTickSpacing(1);
        jSlider_P.setPaintLabels(true);
        jSlider_P.setPaintTicks(true);

        jButton_Sal.setText("Sal");

        jButton_Pimienta.setText("Pimienta");

        jButton_Ambos.setText("Ambos");

        jButton_GV.setText("Generar Ventana");

        javax.swing.GroupLayout jPanel_CRLayout = new javax.swing.GroupLayout(jPanel_CR);
        jPanel_CR.setLayout(jPanel_CRLayout);
        jPanel_CRLayout.setHorizontalGroup(
            jPanel_CRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_CRLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSlider_P, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel_CRLayout.createSequentialGroup()
                .addGap(221, 221, 221)
                .addComponent(jButton_Sal, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel_CRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_GV, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel_CRLayout.createSequentialGroup()
                        .addComponent(jButton_Pimienta, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_Ambos, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(226, Short.MAX_VALUE))
        );
        jPanel_CRLayout.setVerticalGroup(
            jPanel_CRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_CRLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSlider_P, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel_CRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Sal, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Pimienta, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Ambos, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_GV, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel_BR.setBackground(new java.awt.Color(50, 50, 50));

        jButton_Sal_B.setText("Sal");

        jButton_Pimienta_B.setText("Pimienta");

        jButton_Ambos_B.setText("Ambos");

        jButton_GV_B.setText("Generar Ventana");

        javax.swing.GroupLayout jPanel_BRLayout = new javax.swing.GroupLayout(jPanel_BR);
        jPanel_BR.setLayout(jPanel_BRLayout);
        jPanel_BRLayout.setHorizontalGroup(
            jPanel_BRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_BRLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton_Sal_B, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel_BRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton_GV_B, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel_BRLayout.createSequentialGroup()
                        .addComponent(jButton_Pimienta_B, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton_Ambos_B, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel_BRLayout.setVerticalGroup(
            jPanel_BRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_BRLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_BRLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Sal_B, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Pimienta_B, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Ambos_B, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_GV_B, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel_CR, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel_BR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_Imagen))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel_CR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel_BR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_Imagen)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Ambos;
    private javax.swing.JButton jButton_Ambos_B;
    private javax.swing.JButton jButton_GV;
    private javax.swing.JButton jButton_GV_B;
    private javax.swing.JButton jButton_Pimienta;
    private javax.swing.JButton jButton_Pimienta_B;
    private javax.swing.JButton jButton_Sal;
    private javax.swing.JButton jButton_Sal_B;
    private javax.swing.JLabel jLabel_Imagen;
    private javax.swing.JPanel jPanel_BR;
    private javax.swing.JPanel jPanel_CR;
    private javax.swing.JSlider jSlider_P;
    // End of variables declaration//GEN-END:variables
}
