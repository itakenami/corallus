/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corallus.ui.componente;

import java.awt.Graphics;
import javax.swing.JPanel;
import corallus.ui.util.Imagens;

/**
 *
 * @author itakenami
 */
public class Screen extends JPanel {
    
    private int largura,altura;
    
    public Screen(int largura, int altura){
        this.largura = largura;
        this.altura = altura;
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        grphcs.drawImage(Imagens.getInstance().IMG_SCREEN, 0, 0, this);
    }
    
}
