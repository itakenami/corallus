/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corallus.ui.componente;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import corallus.ui.util.Imagens;

/**
 *
 * @author itakenami
 */
public class Topo extends JPanel {
    
    public Topo(){
        setLayout(null);
    }
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        grphcs.drawImage(Imagens.getInstance().IMG_TOPO, 0, 0, this);
        for(int x=375;x<getWidth()-26;x=x+4){
            grphcs.drawImage(Imagens.getInstance().IMG_TOPO2, x, 0, this);
        }
        grphcs.drawImage(Imagens.getInstance().IMG_TOPO3, getWidth()-26, 0, this);
    }
    
}
