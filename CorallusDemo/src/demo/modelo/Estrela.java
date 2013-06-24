/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.modelo;

import corallus.modelo.Customizado;
import corallus.modelo.Fase;

/**
 *
 * @author itakenami
 */
public class Estrela extends Customizado {
    
    boolean comeu;
    
    public Estrela(){
        comeu = false;
    }

    @Override
    public String getImagemPath() {
        return "resource/estrela.gif";
    }

    @Override
    public void acao(Fase f) {
        //f.setDefaultVelocidade();
        //f.reiniciarFase();
        //f.getCobra().getTamanho();
        comeu = true;
    }

    @Override
    public boolean isItemMovimentoFim() {
        return comeu;
    }
    
    
    
}
