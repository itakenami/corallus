/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.fases;

import demo.modelo.Estrela;

/**
 *
 * @author itakenami
 */
public class Fase1 extends DefaultFase {
    
    private int qtd;

    @Override
    public void iniciar() {
       qtd=0; //Quantidade de macãs comidas
       novaComida();
    }

    @Override
    public void comeu() {
        qtd++;
        if(qtd==5){ //Se comer 5 maçãs, passa de fase
            mudarFase();
        }else{
            addPontos(10); //Quando come maçaã adiciona 10 pontos
            addRabo(); //cresce o rabo
            novaComida(); //aparece uma nova comida em uma posição aleatória
        }
    }

    @Override
    public String getTituloFase() {
        return "Fase1";
    }

    @Override
    public String getTextoFase() {
        return "Coma 5 maçãs para passar de fase.";
    }
    
     
    
    
    
    
}
