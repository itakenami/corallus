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
public class Bandeira extends Customizado {

    @Override
    public String getImagemPath() {
        return "resource/bandeira.gif";
    }

    @Override
    public void acao(Fase f) {
        f.moverPara(574, f.DIREITA);
        //f.reiniciarFase();
        //f.getCobra().getTamanho();
    }
    
}
