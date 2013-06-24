package corallus.ui.componente;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import corallus.Config;
import corallus.ui.util.Imagens;

public class Bloco extends JPanel {
    
    private int TIPO;
    private int DIRECAO;
    private int NUM;
    private Image CUSTOM_IMAGE;
    private static final int TAMANHO_BLOCO = 18;
    
    public static final int CAMINHO = 0;
    public static final int CABECA  = 1;
    public static final int CORPO   = 2;
    public static final int RABO    = 3;
    public static final int COMIDA  = 5;
    public static final int PEDRA   = 6;
    public static final int CUSTOM  = 10;
    
    private final static int CIMA     = 1;
    private final static int BAIXO    = 2;
    private final static int DIREITA  = 3;
    private final static int ESQUERDA = 4;
    
    /*
    public static final Image IMG_CAMINHO = (new ImageIcon("resource/caminho.png")).getImage();
    public static final Image[] IMG_CABECA = {null,(new ImageIcon("resource/cabeca1.png")).getImage(),(new ImageIcon("resource/cabeca2.png")).getImage(),(new ImageIcon("resource/cabeca3.png")).getImage(),(new ImageIcon("resource/cabeca4.png")).getImage()};
    public static final Image[] IMG_CORPO = {null,(new ImageIcon("resource/corpo1.png")).getImage(),(new ImageIcon("resource/corpo2.png")).getImage(),(new ImageIcon("resource/corpo3.png")).getImage(),(new ImageIcon("resource/corpo4.png")).getImage(),(new ImageIcon("resource/corpo5.png")).getImage(),(new ImageIcon("resource/corpo6.png")).getImage(),(new ImageIcon("resource/corpo7.png")).getImage(),(new ImageIcon("resource/corpo8.png")).getImage(),(new ImageIcon("resource/corpo9.png")).getImage(),(new ImageIcon("resource/corpo10.png")).getImage(),(new ImageIcon("resource/corpo11.png")).getImage(),(new ImageIcon("resource/corpo12.png")).getImage()};
    public static final Image[] IMG_RABO = {null,(new ImageIcon("resource/rabo1.png")).getImage(),(new ImageIcon("resource/rabo2.png")).getImage(),(new ImageIcon("resource/rabo3.png")).getImage(),(new ImageIcon("resource/rabo4.png")).getImage()};
    public static final Image IMG_COMIDA = (new ImageIcon("resource/comida.png")).getImage();
    public static final Image IMG_PEDRA = (new ImageIcon("resource/pedra.png")).getImage();
    */
    
    public Bloco(int num){
        NUM = num;
    }
    
    public void setTipo(int tipo){
        TIPO = tipo;
    }
    
    public void setDirecao(int dir){
        DIRECAO = dir;
    }
    
    public void setImagem(String img){
        CUSTOM_IMAGE = (new ImageIcon(img)).getImage();
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        grphcs.drawImage(Imagens.getInstance().IMG_CAMINHO, 0, 0, TAMANHO_BLOCO,TAMANHO_BLOCO,this);
        switch(TIPO){
            case CABECA:
                grphcs.drawImage(Imagens.getInstance().IMG_CABECA[DIRECAO], 0, 0, TAMANHO_BLOCO,TAMANHO_BLOCO,this);
                break;
            case CORPO:
                grphcs.drawImage(Imagens.getInstance().IMG_CORPO[DIRECAO], 0, 0, TAMANHO_BLOCO,TAMANHO_BLOCO,this);
                break;
            case RABO:
                grphcs.drawImage(Imagens.getInstance().IMG_RABO[DIRECAO], 0, 0, TAMANHO_BLOCO,TAMANHO_BLOCO,this);
                break;
            case COMIDA:
                grphcs.drawImage(Imagens.getInstance().IMG_COMIDA, 0, 0, TAMANHO_BLOCO,TAMANHO_BLOCO,this);
                break;
            case PEDRA:
                grphcs.drawImage(Imagens.getInstance().IMG_PEDRA, 0, 0, TAMANHO_BLOCO,TAMANHO_BLOCO,this);
                break;
            case CUSTOM:
                grphcs.drawImage(CUSTOM_IMAGE, 0, 0, TAMANHO_BLOCO,TAMANHO_BLOCO,this);
                break;
                
        }
        if(Config.SHOW_NUM){
            grphcs.setFont(new Font("Arial",0, 8));
            grphcs.drawString(NUM+"", 3, 10);
        }
         
    }
    
    
    
}
