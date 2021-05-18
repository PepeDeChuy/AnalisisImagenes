/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

/**
 *
 * @author peper
 */
public class JInternalFrameIluminacion extends javax.swing.JInternalFrame 
{
    private JInternalFrameImagen JFI;
    private BufferedImage bufferimagen;
    private Image preImagen;
    private JFramePrincipal JFP;
    /**
     * Creates new form JInternalFrameIluminacion
     */
    public JInternalFrameIluminacion(JFramePrincipal JFP) 
    {
        this.JFP = JFP;
        this.JFI = JFP.getJIFI();
        initComponents();
        
        jButton_Filtro.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                generarPreImagen();
            }
        });
        
        jButton_Generar.addActionListener(new ActionListener() 
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
                JFP.setJIFIL(null);
            }
        });
    }
    
    public void generarPreImagen()
    {
        int valor = Integer.parseInt(jTextField_x.getText());
        bufferimagen = herramientas.herramientas.toBufferedImage(getJFI().getImagenSecundaria());
        
        for(int x = 0; x < bufferimagen.getWidth(); x++)
        {
            for(int y = 0; y < bufferimagen.getHeight(); y++)
            {
                int auxR = 0;
                int auxG = 0;
                int auxB = 0;
                
                Color color = new Color(bufferimagen.getRGB(x, y));
                
                auxR = validarDato(color.getRed() + valor);
                auxG = validarDato(color.getGreen() + valor);
                auxB = validarDato(color.getBlue() + valor);
                
                color = new Color(auxR, auxG, auxB);
                
                bufferimagen.setRGB(x, y, color.getRGB());
            }
        }
        
        preImagen = herramientas.herramientas.toImage(bufferimagen);
        
        setImagen();
    }
    
    public int validarDato(int x)
    {
        if(x > 255) 
        {
            return 255;
        }
        else if(x < 0)
        {
            return 0;
        }
        else
        {
            return x;
        }
    }
    
    public void setImagen()
    {
        jLabel_PreImagen.removeAll();
        jLabel_PreImagen.setIcon(new ImageIcon(preImagen));
        setSize(preImagen.getWidth(null)+20,(preImagen.getHeight(null) + 150));
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton_Filtro = new javax.swing.JButton();
        jButton_Generar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jTextField_x = new javax.swing.JTextField();
        jLabel_PreImagen = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);

        jButton_Filtro.setText("Agregar Iluminacion");
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

        jLabel1.setText("Nivel a iuminacion a cambiar");

        jLabel_PreImagen.setText("jLabel1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_PreImagen)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jButton_Filtro)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_Generar, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(150, 150, 150))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_x, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_x, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_Filtro, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Generar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel_PreImagen))
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel_PreImagen;
    private javax.swing.JTextField jTextField_x;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the JFI
     */
    public JInternalFrameImagen getJFI() {
        return JFI;
    }

    /**
     * @param JFI the JFI to set
     */
    public void setJFI(JInternalFrameImagen JFI) {
        this.JFI = JFI;
    }
}
