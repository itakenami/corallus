/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corallus.ui.util;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author itakenami
 */
public class Imagens {
    
    private static Imagens instance;
    public final Image IMG_CAMINHO;
    public final Image[] IMG_CABECA;
    public final Image[] IMG_CORPO;
    public final Image[] IMG_RABO;
    public final Image IMG_COMIDA;
    public final Image IMG_PEDRA;
    
    public final Image IMG_FUNDO;
    public final Image IMG_TOPO;
    public final Image IMG_TOPO2;
    public final Image IMG_TOPO3;
    public final Image IMG_SCREEN;
    
    private Imagens(){
        IMG_CAMINHO = (new ImageIcon(getClass().getClassLoader().getResource("resource/caminho.png"))).getImage();
        
        IMG_CABECA = new Image[5];
        IMG_CABECA[1] = (new ImageIcon(getClass().getClassLoader().getResource("resource/cabeca1.png"))).getImage();
        IMG_CABECA[2] = (new ImageIcon(getClass().getClassLoader().getResource("resource/cabeca2.png"))).getImage();
        IMG_CABECA[3] = (new ImageIcon(getClass().getClassLoader().getResource("resource/cabeca3.png"))).getImage();
        IMG_CABECA[4] = (new ImageIcon(getClass().getClassLoader().getResource("resource/cabeca4.png"))).getImage();
        
        IMG_CORPO = new Image[13];
        IMG_CORPO[1]  = (new ImageIcon(getClass().getClassLoader().getResource("resource/corpo1.png"))).getImage();
        IMG_CORPO[2]  = (new ImageIcon(getClass().getClassLoader().getResource("resource/corpo2.png"))).getImage();
        IMG_CORPO[3]  = (new ImageIcon(getClass().getClassLoader().getResource("resource/corpo3.png"))).getImage();
        IMG_CORPO[4]  = (new ImageIcon(getClass().getClassLoader().getResource("resource/corpo4.png"))).getImage();
        IMG_CORPO[5]  = (new ImageIcon(getClass().getClassLoader().getResource("resource/corpo5.png"))).getImage();
        IMG_CORPO[6]  = (new ImageIcon(getClass().getClassLoader().getResource("resource/corpo6.png"))).getImage();
        IMG_CORPO[7]  = (new ImageIcon(getClass().getClassLoader().getResource("resource/corpo7.png"))).getImage();
        IMG_CORPO[8]  = (new ImageIcon(getClass().getClassLoader().getResource("resource/corpo8.png"))).getImage();
        IMG_CORPO[9]  = (new ImageIcon(getClass().getClassLoader().getResource("resource/corpo9.png"))).getImage();
        IMG_CORPO[10] = (new ImageIcon(getClass().getClassLoader().getResource("resource/corpo10.png"))).getImage();
        IMG_CORPO[11] = (new ImageIcon(getClass().getClassLoader().getResource("resource/corpo11.png"))).getImage();
        IMG_CORPO[12] = (new ImageIcon(getClass().getClassLoader().getResource("resource/corpo12.png"))).getImage();
        
        IMG_RABO = new Image[5];
        IMG_RABO[1] = (new ImageIcon(getClass().getClassLoader().getResource("resource/rabo1.png"))).getImage();
        IMG_RABO[2] = (new ImageIcon(getClass().getClassLoader().getResource("resource/rabo2.png"))).getImage();
        IMG_RABO[3] = (new ImageIcon(getClass().getClassLoader().getResource("resource/rabo3.png"))).getImage();
        IMG_RABO[4] = (new ImageIcon(getClass().getClassLoader().getResource("resource/rabo4.png"))).getImage();
        
        IMG_COMIDA = (new ImageIcon(getClass().getClassLoader().getResource("resource/comida.png"))).getImage();
        IMG_PEDRA = (new ImageIcon(getClass().getClassLoader().getResource("resource/pedra.png"))).getImage();
        IMG_FUNDO = (new ImageIcon(getClass().getClassLoader().getResource("resource/fundo.png"))).getImage();
        IMG_TOPO = (new ImageIcon(getClass().getClassLoader().getResource("resource/topo.png"))).getImage();
        IMG_TOPO2 = (new ImageIcon(getClass().getClassLoader().getResource("resource/topo2.png"))).getImage();
        IMG_TOPO3 = (new ImageIcon(getClass().getClassLoader().getResource("resource/topo3.png"))).getImage();
        IMG_SCREEN = (new ImageIcon(getClass().getClassLoader().getResource("resource/screen.png"))).getImage();
        
    }
    
    public static Imagens getInstance(){
        if(instance==null){
            instance = new Imagens();
        }
        return instance;
    }
    
}
