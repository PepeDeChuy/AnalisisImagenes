package frames;

import herramientas.Grafica;
import static herramientas.herramientas.toBufferedImage;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

/**
 *
 * @author peper
 */
public class JInternalFrameGraficaHistograma extends javax.swing.JInternalFrame 
{
    private JFramePrincipal JFP;
    private Image preImagen;
    
    int[] r = new int[256];
    int[] g = new int[256];
    int[] b = new int[256];
    
    public JInternalFrameGraficaHistograma(JFramePrincipal JFP, String metodo) 
    {
        this.JFP = JFP;
        initComponents();
        
        jLabelImagen.setText("");
        
        switch (metodo)
        {
            case "Graficar":
            {
                jPanelExpandir.setVisible(false);
                jPanelImagen.setLocation(70, 70);
                setSize((this.getWidth()-250), (this.getHeight()-180));
                this.jButton_Graficar.addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent ae) 
                    {
                        generarGrafica(0);
                    }
                });

                this.jButton_GraficarV.addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent ae) 
                    {
                        generarGrafica(1);
                    }
                });
                
                addInternalFrameListener(new InternalFrameAdapter(){
                    public void internalFrameClosing(InternalFrameEvent e) 
                    {
                        JFP.setJIFGH(null);
                    }
                });
                
                break;
            }
            
            case "Expandir":
            {
                jPanelGraficar.setVisible(false);
                jPanelExpandir.setLocation(0, 0);
                jPanelImagen.setLocation(70, 120);
                setSize((this.getWidth()), (this.getHeight()-100));
                this.jButton_ExpandirLineal.addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent ae) 
                    {
                        expandir("LI");
                    }
                });
                
                this.jButton_ExpandirExpo.addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent ae) 
                    {
                        expandir("EX");
                    }
                });
                
                this.jButton_ExpandirLog.addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent ae) 
                    {
                        expandir("LOG");
                    }
                });
                
                this.jButton_ExpandirEcualizacion.addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent ae) 
                    {
                        expandir("EC");
                    }
                });
                
                this.jButton_GenenrarVentana.addActionListener(new ActionListener() 
                {
                    @Override
                    public void actionPerformed(ActionEvent ae) 
                    {
                        herramientas.herramientas.generarVentana(preImagen, JFP);
                    }
                });
                
                addInternalFrameListener(new InternalFrameAdapter(){
                    public void internalFrameClosing(InternalFrameEvent e) 
                    {
                        JFP.setJIFGHE(null);
                    }
                });
                
                break;
            }
        }
        
        
    }
    
    public void generarGrafica(int graficas)
    {
        extraerHistogra();
                
        if(graficas == 0)
        {
            Grafica grafica = new Grafica("Valor RGB","Num","RGB");
            grafica.agregarSerie("R",r);
            grafica.agregarSerie("B",b);
            grafica.agregarSerie("G",g);
            grafica.crearGrafica();
            Color[] colores = new Color[3];
            colores[0] = Color.RED;
            colores[1] = Color.GREEN;
            colores[2] = Color.BLUE;
            grafica.cambiarColores(colores);
            grafica.muestraGrafica();
        }
        else
        {
            Grafica gR = new Grafica("Valor RGB","Num","R");
            gR.agregarSerie("R",r);
            //gR.agregarSerie("B",b);
            //gR.agregarSerie("G",g);
            gR.crearGrafica();
            gR.cambiarColores(new Color[]{Color.RED});
            gR.muestraGrafica();

            Grafica gG = new Grafica("Valor RGB","Num","G");
            //gG.agregarSerie("R",r);
            //gG.agregarSerie("B",b);
            gG.agregarSerie("G",g);
            gG.crearGrafica();
            gG.cambiarColores(new Color[]{Color.GREEN});
            gG.muestraGrafica();

            Grafica gB = new Grafica("Valor RGB","Num","B");
            //gB.agregarSerie("R",r);
            gB.agregarSerie("B",b);
            //gB.agregarSerie("G",g);
            gB.crearGrafica();
            gB.cambiarColores(new Color[]{Color.BLUE});
            gB.muestraGrafica();
        }
        
    }
    
    public void extraerHistogra()
    {
        r = new int[256];
        g = new int[256];
        b = new int[256];
    
        BufferedImage bufferimagen = toBufferedImage(JFP.getJIFI().getImagenSecundaria());
        
        int width = JFP.getJIFI().getImagenSecundaria().getWidth(null);
        int height = JFP.getJIFI().getImagenSecundaria().getHeight(null);
        
        for(int x = 0; x < width; x++)
        {
            for(int y = 0; y < height; y++)
            {
                Color c = new Color(bufferimagen.getRGB(x, y));
                
                r[c.getRed()]++;
                g[c.getGreen()]++;
                b[c.getBlue()]++;
            }
        }
    }
    
    public void expandir(String metodo)
    {
        double r1 = 0;
        double r2 = 0;
        
        //variables para la ecualizacion
        double[] suma = new double[] {0,0,0};
        double[][] sumah = new double[3][256];
        
        int tono = 0;
        
        double[] constante = new double[] {0,0,0};
        
        BufferedImage bufferimagen = herramientas.herramientas.toBufferedImage(JFP.getJIFI().getImagenSecundaria());
        
        if(metodo.equals("LI"))
        {
            if(jTextField_R1.getText().equals("") == false)
            {
                r1 = Double.parseDouble(jTextField_R1.getText());
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Agregre un valor em R1");
            }
            
            if(jTextField_R2.getText().equals("") == false)
            {
                r2 = Double.parseDouble(jTextField_R2.getText());
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Agregre un valor em R2");
            }
        }
        
        if(metodo.equals("EX"))
        {
            if(jTextField_R1.getText().equals("") == false)
            {
                r1 = Double.parseDouble(jTextField_R1.getText());
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Agregre un valor em R1");
            }
        }
        
        if(metodo.equals("EC"))
        {
            extraerHistogra();
            
            for(int x = 0; x < 255; x++)
            {
                suma[0] = suma[0] + r[x];
                suma[1] = suma[1] + g[x];
                suma[2] = suma[2] + b[x];
                
                sumah[0][x] = suma[0];
                sumah[1][x] = suma[1];
                sumah[2][x] = suma[2];
            }
            
            double wh = JFP.getJIFI().getImagenSecundaria().getWidth(null) * JFP.getJIFI().getImagenSecundaria().getHeight(null);
            
            constante[0] = suma[0] / wh;
            constante[1] = suma[1] / wh;
            constante[2] = suma[2] / wh;
        }
        
        for(int x = 0; x < bufferimagen.getWidth(); x++)
        {
            for(int y = 0; y < bufferimagen.getHeight(); y++)
            {   
                Color color = new Color(bufferimagen.getRGB(x, y));
                
                switch (metodo)
                {
                    case "LI":
                    {
                        color = expandirLineal(color, r1, r2);
                        break;
                    }
                    
                    case "EX":
                    {
                        color = expandirExpo(color, r1);
                        break;
                    }
                    
                    case "LOG":
                    {
                        color = expandirLog(color);
                        break;
                    }
                    
                    case "EC":
                    {
                        color = expandirEcualización(color, sumah, constante);
                        break;
                    }
                }
                
                bufferimagen.setRGB(x, y, color.getRGB());
            }
        }
        
        preImagen = herramientas.herramientas.toImage(bufferimagen);
        
        setImagen();
    }
    
    public Color expandirLineal(Color color, double r1, double r2)
    {
                
        int auxR = validarDato((int)((color.getRed()-r1)*(255/(r2-r1))));
        int auxG = validarDato((int)((color.getGreen()-r1)*(255/(r2-r1))));
        int auxB = validarDato((int)((color.getBlue()-r1)*(255/(r2-r1))));
        
        return new Color(auxR, auxG, auxB);
    }
    
    public Color expandirExpo(Color color, double r1)
    {
        int auxR = validarDato((int)(Math.pow(1+r1,color.getRed())/r1));
        int auxG = validarDato((int)(Math.pow(1+r1,color.getGreen())/r1));
        int auxB = validarDato((int)(Math.pow(1+r1,color.getBlue())/r1));
        
        return new Color(auxR, auxG, auxB);
    }
    
    public Color expandirLog(Color color)
    {
        int auxR = validarDato((int)((255*Math.log(1+color.getRed()))/(Math.log(1+255))));
        int auxG = validarDato((int)((255*Math.log(1+color.getGreen()))/(Math.log(1+255))));
        int auxB = validarDato((int)((255*Math.log(1+color.getBlue()))/(Math.log(1+255))));
        
        return new Color(auxR, auxG, auxB);
    }
    
    public Color expandirEcualización(Color color, double[][] sumah, double[] cosntante)
    {
        int auxR = validarDato((int)(r[color.getRed()] * cosntante[0]));
        int auxG = validarDato((int)(g[color.getGreen()] * cosntante[1]));
        int auxB = validarDato((int)(b[color.getBlue()] * cosntante[2]));
        
        return new Color(auxR, auxG, auxB);
    }
    
    public int validarDato(int valor)
    {
        if(valor>255)
        {
            return 255;
        }
        else if(valor<0)
        {
            return 0;
        }
        else
        {
            return valor;
        }
    }
    
    public void setImagen()
    {
        jLabelImagen.removeAll();
        jLabelImagen.setIcon(new ImageIcon(preImagen));
        
        int w = preImagen.getWidth(null);
        int h = preImagen.getHeight(null)+200;
        
        if(w < this.getWidth())
        {
            w = this.getWidth();
        }
        else
        {
            w += 750;
        }
        
        setSize(w,h);
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton_Graficar1 = new javax.swing.JButton();
        jPanelGraficar = new javax.swing.JPanel();
        jButton_GraficarV = new javax.swing.JButton();
        jButton_Graficar = new javax.swing.JButton();
        jPanelExpandir = new javax.swing.JPanel();
        jButton_ExpandirLineal = new javax.swing.JButton();
        jTextField_R1 = new javax.swing.JTextField();
        jButton_GenenrarVentana = new javax.swing.JButton();
        jTextField_R2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton_ExpandirExpo = new javax.swing.JButton();
        jButton_ExpandirLog = new javax.swing.JButton();
        jButton_ExpandirEcualizacion = new javax.swing.JButton();
        jPanelImagen = new javax.swing.JPanel();
        jLabelImagen = new javax.swing.JLabel();

        jButton_Graficar1.setText("Graficar");
        jButton_Graficar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_Graficar1ActionPerformed(evt);
            }
        });

        setClosable(true);
        setIconifiable(true);

        jButton_GraficarV.setText("Graficar Separadas");
        jButton_GraficarV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_GraficarVActionPerformed(evt);
            }
        });

        jButton_Graficar.setText("Graficar");
        jButton_Graficar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_GraficarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelGraficarLayout = new javax.swing.GroupLayout(jPanelGraficar);
        jPanelGraficar.setLayout(jPanelGraficarLayout);
        jPanelGraficarLayout.setHorizontalGroup(
            jPanelGraficarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGraficarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton_Graficar, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_GraficarV, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelGraficarLayout.setVerticalGroup(
            jPanelGraficarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelGraficarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelGraficarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_GraficarV, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Graficar, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton_ExpandirLineal.setText("Lineal");
        jButton_ExpandirLineal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ExpandirLinealActionPerformed(evt);
            }
        });

        jButton_GenenrarVentana.setText("Generar Ventana");
        jButton_GenenrarVentana.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_GenenrarVentanaActionPerformed(evt);
            }
        });

        jLabel1.setText("R1");

        jLabel2.setText("R2");

        jButton_ExpandirExpo.setText("Exponencial");
        jButton_ExpandirExpo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ExpandirExpoActionPerformed(evt);
            }
        });

        jButton_ExpandirLog.setText("Logaritmica");
        jButton_ExpandirLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ExpandirLogActionPerformed(evt);
            }
        });

        jButton_ExpandirEcualizacion.setText("Ecualizacion");
        jButton_ExpandirEcualizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ExpandirEcualizacionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelExpandirLayout = new javax.swing.GroupLayout(jPanelExpandir);
        jPanelExpandir.setLayout(jPanelExpandirLayout);
        jPanelExpandirLayout.setHorizontalGroup(
            jPanelExpandirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelExpandirLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelExpandirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanelExpandirLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(4, 4, 4)
                        .addComponent(jTextField_R1, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField_R2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_ExpandirEcualizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelExpandirLayout.createSequentialGroup()
                        .addComponent(jButton_ExpandirLineal, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_ExpandirExpo, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton_ExpandirLog, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton_GenenrarVentana, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelExpandirLayout.setVerticalGroup(
            jPanelExpandirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelExpandirLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelExpandirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelExpandirLayout.createSequentialGroup()
                        .addGroup(jPanelExpandirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_R1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jTextField_R2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_ExpandirEcualizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanelExpandirLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton_ExpandirLineal, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_ExpandirExpo, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton_ExpandirLog, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelExpandirLayout.createSequentialGroup()
                        .addComponent(jButton_GenenrarVentana, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39))))
        );

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelGraficar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelExpandir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 12, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelGraficar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelExpandir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton_GraficarVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_GraficarVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_GraficarVActionPerformed

    private void jButton_Graficar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_Graficar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_Graficar1ActionPerformed

    private void jButton_GraficarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_GraficarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_GraficarActionPerformed

    private void jButton_GenenrarVentanaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_GenenrarVentanaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_GenenrarVentanaActionPerformed

    private void jButton_ExpandirLinealActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ExpandirLinealActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_ExpandirLinealActionPerformed

    private void jButton_ExpandirExpoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ExpandirExpoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_ExpandirExpoActionPerformed

    private void jButton_ExpandirLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ExpandirLogActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_ExpandirLogActionPerformed

    private void jButton_ExpandirEcualizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ExpandirEcualizacionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_ExpandirEcualizacionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_ExpandirEcualizacion;
    private javax.swing.JButton jButton_ExpandirExpo;
    private javax.swing.JButton jButton_ExpandirLineal;
    private javax.swing.JButton jButton_ExpandirLog;
    private javax.swing.JButton jButton_GenenrarVentana;
    private javax.swing.JButton jButton_Graficar;
    private javax.swing.JButton jButton_Graficar1;
    private javax.swing.JButton jButton_GraficarV;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabelImagen;
    private javax.swing.JPanel jPanelExpandir;
    private javax.swing.JPanel jPanelGraficar;
    private javax.swing.JPanel jPanelImagen;
    private javax.swing.JTextField jTextField_R1;
    private javax.swing.JTextField jTextField_R2;
    // End of variables declaration//GEN-END:variables

}
