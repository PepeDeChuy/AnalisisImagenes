package frames;

import static herramientas.herramientas.toBufferedImage;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;
import mascaras.MascarasBordes;

/**
 *
 * @author peper
 */
public class JInternalFrameMatrizConvolucion extends javax.swing.JInternalFrame 
{
    private int[][] matriz = new int[5][5];
    private JFramePrincipal JFP;
    private int divisor = 1;
    private int desplacamiento = 0;
    
    boolean r;
    boolean g;
    boolean b;
    
    private Image preImagen;
    BufferedImage bufferimagen;
    
    private int w;
    private int h;
    
    private boolean ki;
    private int[] kiArray;
    
    public JInternalFrameMatrizConvolucion(JFramePrincipal JFP, String metodo) 
    {
        this.JFP = JFP;
        initComponents();
        jLabelImagen.setText("");
        
        switch (metodo)
        {
            case "MC":
            {
                MC();
                break;
            }
            case "KI":
            {
                KI();
                break;
            }
        }
    }
    
    private void MC()
    {
        jPanelMatriz.setVisible(true);
        jPanelKirsch.setVisible(false);
        
        jLabelImagen.setLocation(223, 0);
        
        this.setSize(699, 250);
        
        w = 699;
        h = 250;
        
        jCheckBoxRed.setSelected(true);
        jCheckBoxGreen.setSelected(true);
        jCheckBoxBlue.setSelected(true);
        
        jButtonPreImagenMatriz.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                ki = false;
                extraerMatriz();
                //mostrarMatriz();
                calcularPreImagen();
            }
        });
        
        jButtonGenerarVentanaMatriz.addActionListener(new ActionListener() 
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
                JFP.setJIFMC(null);
            }
        });
    }
    
    private void extraerMatriz()
    {
        matriz[0][0] = (Integer)jSpinner00.getValue();
        matriz[0][1] = (Integer)jSpinner01.getValue();
        matriz[0][2] = (Integer)jSpinner02.getValue();
        matriz[0][3] = (Integer)jSpinner03.getValue();
        matriz[0][4] = (Integer)jSpinner04.getValue();
                
        matriz[1][0] = (Integer)jSpinner10.getValue();
        matriz[1][1] = (Integer)jSpinner11.getValue();
        matriz[1][2] = (Integer)jSpinner12.getValue();
        matriz[1][3] = (Integer)jSpinner13.getValue();
        matriz[1][4] = (Integer)jSpinner14.getValue();    
                
        matriz[2][0] = (Integer)jSpinner20.getValue();
        matriz[2][1] = (Integer)jSpinner21.getValue();
        matriz[2][2] = (Integer)jSpinner22.getValue();
        matriz[2][3] = (Integer)jSpinner23.getValue();
        matriz[2][4] = (Integer)jSpinner24.getValue();
                
        matriz[3][0] = (Integer)jSpinner30.getValue();
        matriz[3][1] = (Integer)jSpinner31.getValue();
        matriz[3][2] = (Integer)jSpinner32.getValue();
        matriz[3][3] = (Integer)jSpinner33.getValue();
        matriz[3][4] = (Integer)jSpinner34.getValue();  
                
        matriz[4][0] = (Integer)jSpinner40.getValue();
        matriz[4][1] = (Integer)jSpinner41.getValue();
        matriz[4][2] = (Integer)jSpinner42.getValue();
        matriz[4][3] = (Integer)jSpinner43.getValue();
        matriz[4][4] = (Integer)jSpinner44.getValue();
    }
    
    private void KI()
    {   
        jPanelMatriz.setVisible(false);
        jPanelKirsch.setVisible(true);
        
        jPanelKirsch.setLocation(1/2, 1/2);
        jLabelImagen.setLocation(155, 0);
        
        this.setSize(343, 170);
        
        w = 343;
        h = 170;
        
        jCheckBoxKirsch1.setSelected(true);
        jCheckBoxKirsch2.setSelected(true);
        jCheckBoxKirsch3.setSelected(true);
        jCheckBoxKirsch4.setSelected(true);
        jCheckBoxKirsch5.setSelected(true);
        jCheckBoxKirsch6.setSelected(true);
        jCheckBoxKirsch7.setSelected(true);
        jCheckBoxKirsch8.setSelected(true);
        
        jButtonPreImagenKirsch.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent ae) 
            {
                ki = true;
                extraerMatrizKirsch();
                calcularPreImagen();
            }
        });
        
        jButtonGenerarVentanaKirsch.addActionListener(new ActionListener() 
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
                JFP.setJIFMCK(null);
            }
        });
    }
    
    private void extraerMatrizKirsch()
    {
        
        kiArray = new int[8];
        if(jCheckBoxKirsch1.isSelected())
        {
            kiArray[0] = 1;
        }
        if(jCheckBoxKirsch2.isSelected())
        {
            kiArray[1] = 1;
        }
        if(jCheckBoxKirsch3.isSelected())
        {
            kiArray[2] = 1;
        }
        if(jCheckBoxKirsch4.isSelected())
        {
            kiArray[3] = 1;
        }
        if(jCheckBoxKirsch5.isSelected())
        {
            kiArray[4] = 1;
        }
        if(jCheckBoxKirsch6.isSelected())
        {
            kiArray[5] = 1;
        }
        if(jCheckBoxKirsch7.isSelected())
        {
            kiArray[6] = 1;
        }
        if(jCheckBoxKirsch8.isSelected())
        {
            kiArray[7] = 1;
        }
        
    }
    
    private void mostrarMatriz()
    {
        for(int x = 0; x < 5; x++)
        {
            for(int y = 0; y < 5; y++)
            {
                System.out.print(matriz[x][y]+" ");
            }
            System.out.println("");
        }
    }
    
    private void calcularPreImagen()
    {
        if(ki)
        {
            r = true;
            g = true;
            b = true;
            
            divisor = 1;
            desplacamiento = 0;
        }
        else
        {
            r = jCheckBoxRed.isSelected();
            g = jCheckBoxGreen.isSelected();
            b = jCheckBoxBlue.isSelected();
            
            divisor = (Integer)jSpinnerDivisor.getValue();
            desplacamiento = (Integer)jSpinnerDesplazamiento.getValue();
        }
        
        bufferimagen = toBufferedImage(JFP.getJIFI().getImagenSecundaria());
        BufferedImage bufferimagen2 = toBufferedImage(JFP.getJIFI().getImagenSecundaria());
        preImagen = herramientas.herramientas.toImage(bufferimagen);
        setImagen();
        
        for(int x = 0; x < bufferimagen.getWidth(); x++)
        {
            for(int y = 0; y < bufferimagen.getHeight(); y++)
            {
                Color color = new Color(bufferimagen.getRGB(x, y));
                bufferimagen2.setRGB(x,y,nuevoValorColor(x,y,color).getRGB());
            }
        }
        
        preImagen = herramientas.herramientas.toImage(bufferimagen2);
        setImagen();
    }
    
    private Color nuevoValorColor(int x, int y, Color color)
    {
        int auxR = 0;
        int auxG = 0;
        int auxB = 0;
        
        int[] cont = new int[2];
        
        cont[0] = 0;
        cont[1] = 0;
        
        for(int xx = x-2; xx <= x+2; xx++)
        {
            for(int yy = y-2; yy <= y+2; yy++)
            {
                if(xx>0 && xx<bufferimagen.getWidth() && yy>0 && yy<bufferimagen.getHeight())
                {
                    if(r)
                    {
                        if(ki)
                        {
                            auxR += extraerValorCordenadasKI(xx,yy,cont,'r');
                        }
                        else
                        {
                            auxR += extraerValorCordenadas(xx,yy,cont,'r');
                        }
                    }

                    if(g)
                    {
                        if(ki)
                        {
                            auxG += extraerValorCordenadasKI(xx,yy,cont,'g');
                        }
                        else
                        {
                            auxG += extraerValorCordenadas(xx,yy,cont,'g');
                        }
                        
                    }

                    if(b)
                    {
                        if(ki)
                        {
                            auxB += extraerValorCordenadasKI(xx,yy,cont,'b');
                        }
                        else
                        {
                            auxB += extraerValorCordenadas(xx,yy,cont,'b');
                        }
                        
                    }
                }
                cont[1]++;
            }
            cont[0]++;
            cont[1] = 0;
        }
                
        if(!r)
        {
            auxR = color.getRed();  
        }
        
        if(!g)
        {
            auxG = color.getGreen();
        }
        
        if(!b)
        {
            auxB = color.getBlue();
        }
        
        auxR = auxR + desplacamiento;
        auxG = auxG + desplacamiento;
        auxB = auxB + desplacamiento;
        
        auxR = validarDato(auxR);
        auxG = validarDato(auxG);
        auxB = validarDato(auxB);
        
        return new Color(auxR, auxG, auxB);
    }
    
    public int extraerValorCordenadas(int x, int y, int[] cont, char metodo)
    {
        Color AuxColor = new Color(bufferimagen.getRGB(x, y));
        
        switch (metodo)
        {
            case 'r':
            {
                return (matriz[cont[0]][cont[1]] * AuxColor.getRed())/ divisor;
            }
            case 'g':
            {
                return (matriz[cont[0]][cont[1]] * AuxColor.getGreen())/ divisor;
            }
            case 'b':
            {
                return (matriz[cont[0]][cont[1]] * AuxColor.getBlue())/ divisor;
            }
            default:
            {
                return -1;
            }
        }
    }
    
    public int extraerValorCordenadasKI(int x, int y, int[] cont, char metodo)
    {
        Color AuxColor = new Color(bufferimagen.getRGB(x, y));
        
        switch (metodo)
        {
            case 'r':
            {
                int auxR = 0;
                
                for(int i = 0; i < 8; i++)
                {
                    if(kiArray[i] == 1)
                    {
                        auxR += (MascarasBordes.arregloMascaras[i][cont[0]][cont[1]] * AuxColor.getRed())/ divisor;
                    }
                }
                return auxR;
            }
            case 'g':
            {
                int auxG = 0;
                
                for(int i = 0; i < 8; i++)
                {
                    if(kiArray[i] == 1)
                    {
                        auxG += (MascarasBordes.arregloMascaras[i][cont[0]][cont[1]] * AuxColor.getGreen())/ divisor;
                    }
                }
                return auxG;
            }
            case 'b':
            {
                int auxB = 0;
                
                for(int i = 0; i < 8; i++)
                {
                    if(kiArray[i] == 1)
                    {
                        auxB += (MascarasBordes.arregloMascaras[i][cont[0]][cont[1]] * AuxColor.getBlue())/ divisor;
                    }
                }
                return auxB;
            }
            default:
            {
                return -1;
            }
        }
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
        
        int ww = preImagen.getWidth(null);
        int hh = preImagen.getHeight(null)+h;
        
        if(ww < this.getWidth())
        {
            ww = this.getWidth();
        }
        else
        {
            ww += w;
        }
        
        setSize(ww,hh);
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelImagen = new javax.swing.JLabel();
        jPanelMatriz = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanelMatrizSliders = new javax.swing.JPanel();
        jSpinner30 = new javax.swing.JSpinner();
        jSpinner31 = new javax.swing.JSpinner();
        jSpinner40 = new javax.swing.JSpinner();
        jSpinner00 = new javax.swing.JSpinner();
        jSpinner41 = new javax.swing.JSpinner();
        jSpinner01 = new javax.swing.JSpinner();
        jSpinner02 = new javax.swing.JSpinner();
        jSpinner42 = new javax.swing.JSpinner();
        jSpinner43 = new javax.swing.JSpinner();
        jSpinner03 = new javax.swing.JSpinner();
        jSpinner44 = new javax.swing.JSpinner();
        jSpinner04 = new javax.swing.JSpinner();
        jSpinner10 = new javax.swing.JSpinner();
        jSpinner11 = new javax.swing.JSpinner();
        jSpinner12 = new javax.swing.JSpinner();
        jSpinner13 = new javax.swing.JSpinner();
        jSpinner14 = new javax.swing.JSpinner();
        jSpinner22 = new javax.swing.JSpinner();
        jSpinner23 = new javax.swing.JSpinner();
        jSpinner24 = new javax.swing.JSpinner();
        jSpinner20 = new javax.swing.JSpinner();
        jSpinner21 = new javax.swing.JSpinner();
        jSpinner32 = new javax.swing.JSpinner();
        jSpinner33 = new javax.swing.JSpinner();
        jSpinner34 = new javax.swing.JSpinner();
        jPanelMatrizBotones = new javax.swing.JPanel();
        jCheckBoxRed = new javax.swing.JCheckBox();
        jCheckBoxGreen = new javax.swing.JCheckBox();
        jCheckBoxBlue = new javax.swing.JCheckBox();
        jSpinnerDesplazamiento = new javax.swing.JSpinner();
        jSpinnerDivisor = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButtonGenerarVentanaMatriz = new javax.swing.JButton();
        jButtonPreImagenMatriz = new javax.swing.JButton();
        jPanelKirsch = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jCheckBoxKirsch1 = new javax.swing.JCheckBox();
        jCheckBoxKirsch2 = new javax.swing.JCheckBox();
        jCheckBoxKirsch3 = new javax.swing.JCheckBox();
        jCheckBoxKirsch4 = new javax.swing.JCheckBox();
        jCheckBoxKirsch5 = new javax.swing.JCheckBox();
        jCheckBoxKirsch6 = new javax.swing.JCheckBox();
        jCheckBoxKirsch7 = new javax.swing.JCheckBox();
        jCheckBoxKirsch8 = new javax.swing.JCheckBox();
        jButtonPreImagenKirsch = new javax.swing.JButton();
        jButtonGenerarVentanaKirsch = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setResizable(true);

        jLabelImagen.setText("Imagen");

        jLabel3.setText("Matriz de convulucion");

        jSpinner30.setPreferredSize(new java.awt.Dimension(60, 26));

        jSpinner31.setPreferredSize(new java.awt.Dimension(60, 26));

        jSpinner40.setPreferredSize(new java.awt.Dimension(60, 26));

        jSpinner00.setPreferredSize(new java.awt.Dimension(60, 26));

        jSpinner41.setPreferredSize(new java.awt.Dimension(60, 26));

        jSpinner01.setPreferredSize(new java.awt.Dimension(60, 26));

        jSpinner02.setPreferredSize(new java.awt.Dimension(60, 26));

        jSpinner42.setPreferredSize(new java.awt.Dimension(60, 26));

        jSpinner43.setPreferredSize(new java.awt.Dimension(60, 26));

        jSpinner03.setPreferredSize(new java.awt.Dimension(60, 26));

        jSpinner44.setPreferredSize(new java.awt.Dimension(60, 26));

        jSpinner04.setPreferredSize(new java.awt.Dimension(60, 26));

        jSpinner10.setPreferredSize(new java.awt.Dimension(60, 26));

        jSpinner11.setPreferredSize(new java.awt.Dimension(60, 26));

        jSpinner12.setPreferredSize(new java.awt.Dimension(60, 26));

        jSpinner13.setPreferredSize(new java.awt.Dimension(60, 26));

        jSpinner14.setPreferredSize(new java.awt.Dimension(60, 26));

        jSpinner22.setPreferredSize(new java.awt.Dimension(60, 26));

        jSpinner23.setPreferredSize(new java.awt.Dimension(60, 26));

        jSpinner24.setPreferredSize(new java.awt.Dimension(60, 26));

        jSpinner20.setPreferredSize(new java.awt.Dimension(60, 26));

        jSpinner21.setPreferredSize(new java.awt.Dimension(60, 26));

        jSpinner32.setPreferredSize(new java.awt.Dimension(60, 26));

        jSpinner33.setPreferredSize(new java.awt.Dimension(60, 26));

        jSpinner34.setPreferredSize(new java.awt.Dimension(60, 26));

        javax.swing.GroupLayout jPanelMatrizSlidersLayout = new javax.swing.GroupLayout(jPanelMatrizSliders);
        jPanelMatrizSliders.setLayout(jPanelMatrizSlidersLayout);
        jPanelMatrizSlidersLayout.setHorizontalGroup(
            jPanelMatrizSlidersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMatrizSlidersLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMatrizSlidersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMatrizSlidersLayout.createSequentialGroup()
                        .addComponent(jSpinner40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinner41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinner42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinner43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinner44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanelMatrizSlidersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanelMatrizSlidersLayout.createSequentialGroup()
                            .addComponent(jSpinner30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jSpinner31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jSpinner32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jSpinner33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jSpinner34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanelMatrizSlidersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanelMatrizSlidersLayout.createSequentialGroup()
                                .addComponent(jSpinner20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinner21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinner22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinner23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinner24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelMatrizSlidersLayout.createSequentialGroup()
                                .addComponent(jSpinner10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinner11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinner12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinner13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinner14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanelMatrizSlidersLayout.createSequentialGroup()
                                .addComponent(jSpinner00, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinner01, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinner02, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinner03, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinner04, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelMatrizSlidersLayout.setVerticalGroup(
            jPanelMatrizSlidersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMatrizSlidersLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanelMatrizSlidersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner00, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner01, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner02, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner03, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner04, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMatrizSlidersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMatrizSlidersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMatrizSlidersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMatrizSlidersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jSpinner40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14))
        );

        jCheckBoxRed.setText("Red");

        jCheckBoxGreen.setText("Green");

        jCheckBoxBlue.setText("Blue");

        jSpinnerDesplazamiento.setModel(new javax.swing.SpinnerNumberModel());

        jSpinnerDivisor.setModel(new javax.swing.SpinnerNumberModel(1, null, null, 1));

        jLabel1.setText("Divisor");

        jLabel2.setText("Desplazamiento");

        jButtonGenerarVentanaMatriz.setText("Generar ventana");

        jButtonPreImagenMatriz.setText("Pre imagen");

        javax.swing.GroupLayout jPanelMatrizBotonesLayout = new javax.swing.GroupLayout(jPanelMatrizBotones);
        jPanelMatrizBotones.setLayout(jPanelMatrizBotonesLayout);
        jPanelMatrizBotonesLayout.setHorizontalGroup(
            jPanelMatrizBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMatrizBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMatrizBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMatrizBotonesLayout.createSequentialGroup()
                        .addGroup(jPanelMatrizBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelMatrizBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSpinnerDesplazamiento, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSpinnerDivisor, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jCheckBoxBlue)
                    .addComponent(jCheckBoxGreen)
                    .addComponent(jCheckBoxRed))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(jPanelMatrizBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButtonGenerarVentanaMatriz, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonPreImagenMatriz, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );
        jPanelMatrizBotonesLayout.setVerticalGroup(
            jPanelMatrizBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMatrizBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jCheckBoxRed)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMatrizBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBoxGreen)
                    .addComponent(jButtonPreImagenMatriz, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(jCheckBoxBlue)
                .addGroup(jPanelMatrizBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMatrizBotonesLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelMatrizBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jSpinnerDesplazamiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelMatrizBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jSpinnerDivisor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addContainerGap(12, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelMatrizBotonesLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonGenerarVentanaMatriz, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))))
        );

        javax.swing.GroupLayout jPanelMatrizLayout = new javax.swing.GroupLayout(jPanelMatriz);
        jPanelMatriz.setLayout(jPanelMatrizLayout);
        jPanelMatrizLayout.setHorizontalGroup(
            jPanelMatrizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMatrizLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMatrizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelMatrizLayout.createSequentialGroup()
                        .addComponent(jPanelMatrizSliders, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanelMatrizBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanelMatrizLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanelMatrizLayout.setVerticalGroup(
            jPanelMatrizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMatrizLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelMatrizLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanelMatrizBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelMatrizSliders, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel4.setText("Matriz con kirsch");

        jCheckBoxKirsch1.setText("Kirsch 1");

        jCheckBoxKirsch2.setText("Kirsch 2");

        jCheckBoxKirsch3.setText("Kirsch 3");

        jCheckBoxKirsch4.setText("Kirsch 4");

        jCheckBoxKirsch5.setText("Kirsch 5");

        jCheckBoxKirsch6.setText("Kirsch 6");

        jCheckBoxKirsch7.setText("Kirsch 7");

        jCheckBoxKirsch8.setText("Kirsch 8");
        jCheckBoxKirsch8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxKirsch8ActionPerformed(evt);
            }
        });

        jButtonPreImagenKirsch.setText("Pre imagen");

        jButtonGenerarVentanaKirsch.setText("Generar ventana");

        javax.swing.GroupLayout jPanelKirschLayout = new javax.swing.GroupLayout(jPanelKirsch);
        jPanelKirsch.setLayout(jPanelKirschLayout);
        jPanelKirschLayout.setHorizontalGroup(
            jPanelKirschLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelKirschLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelKirschLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanelKirschLayout.createSequentialGroup()
                        .addGroup(jPanelKirschLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBoxKirsch2)
                            .addComponent(jCheckBoxKirsch1)
                            .addComponent(jCheckBoxKirsch3)
                            .addComponent(jCheckBoxKirsch4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanelKirschLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBoxKirsch6)
                            .addComponent(jCheckBoxKirsch5)
                            .addComponent(jCheckBoxKirsch7)
                            .addComponent(jCheckBoxKirsch8))
                        .addGap(47, 47, 47)
                        .addGroup(jPanelKirschLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButtonPreImagenKirsch, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonGenerarVentanaKirsch, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelKirschLayout.setVerticalGroup(
            jPanelKirschLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelKirschLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelKirschLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelKirschLayout.createSequentialGroup()
                        .addComponent(jCheckBoxKirsch1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelKirschLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBoxKirsch2)
                            .addComponent(jCheckBoxKirsch6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelKirschLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBoxKirsch3)
                            .addComponent(jCheckBoxKirsch7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanelKirschLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBoxKirsch4)
                            .addComponent(jCheckBoxKirsch8)))
                    .addGroup(jPanelKirschLayout.createSequentialGroup()
                        .addGroup(jPanelKirschLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCheckBoxKirsch5)
                            .addComponent(jButtonPreImagenKirsch, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonGenerarVentanaKirsch, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(46, 46, 46))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelImagen)
                    .addComponent(jPanelMatriz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanelKirsch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelMatriz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanelKirsch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelImagen)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBoxKirsch8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxKirsch8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxKirsch8ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonGenerarVentanaKirsch;
    private javax.swing.JButton jButtonGenerarVentanaMatriz;
    private javax.swing.JButton jButtonPreImagenKirsch;
    private javax.swing.JButton jButtonPreImagenMatriz;
    private javax.swing.JCheckBox jCheckBoxBlue;
    private javax.swing.JCheckBox jCheckBoxGreen;
    private javax.swing.JCheckBox jCheckBoxKirsch1;
    private javax.swing.JCheckBox jCheckBoxKirsch2;
    private javax.swing.JCheckBox jCheckBoxKirsch3;
    private javax.swing.JCheckBox jCheckBoxKirsch4;
    private javax.swing.JCheckBox jCheckBoxKirsch5;
    private javax.swing.JCheckBox jCheckBoxKirsch6;
    private javax.swing.JCheckBox jCheckBoxKirsch7;
    private javax.swing.JCheckBox jCheckBoxKirsch8;
    private javax.swing.JCheckBox jCheckBoxRed;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelImagen;
    private javax.swing.JPanel jPanelKirsch;
    private javax.swing.JPanel jPanelMatriz;
    private javax.swing.JPanel jPanelMatrizBotones;
    private javax.swing.JPanel jPanelMatrizSliders;
    private javax.swing.JSpinner jSpinner00;
    private javax.swing.JSpinner jSpinner01;
    private javax.swing.JSpinner jSpinner02;
    private javax.swing.JSpinner jSpinner03;
    private javax.swing.JSpinner jSpinner04;
    private javax.swing.JSpinner jSpinner10;
    private javax.swing.JSpinner jSpinner11;
    private javax.swing.JSpinner jSpinner12;
    private javax.swing.JSpinner jSpinner13;
    private javax.swing.JSpinner jSpinner14;
    private javax.swing.JSpinner jSpinner20;
    private javax.swing.JSpinner jSpinner21;
    private javax.swing.JSpinner jSpinner22;
    private javax.swing.JSpinner jSpinner23;
    private javax.swing.JSpinner jSpinner24;
    private javax.swing.JSpinner jSpinner30;
    private javax.swing.JSpinner jSpinner31;
    private javax.swing.JSpinner jSpinner32;
    private javax.swing.JSpinner jSpinner33;
    private javax.swing.JSpinner jSpinner34;
    private javax.swing.JSpinner jSpinner40;
    private javax.swing.JSpinner jSpinner41;
    private javax.swing.JSpinner jSpinner42;
    private javax.swing.JSpinner jSpinner43;
    private javax.swing.JSpinner jSpinner44;
    private javax.swing.JSpinner jSpinnerDesplazamiento;
    private javax.swing.JSpinner jSpinnerDivisor;
    // End of variables declaration//GEN-END:variables
}
