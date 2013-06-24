/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.fases;

import corallus.modelo.Fase;
import corallus.modelo.util.Variaveis;

/**
 *
 * @author itakenami
 */
public class Fase2 extends Fase {
    
    private int qtd;

    @Override
    public void iniciar() {
        
        qtd = 0;
        bordaPedra(); //Define uma borda de pedra
        
        //Comidas vão aparecer em posições predefinidas
        novaComida(103);
        novaComida(1353);
        novaComida(148);
        novaComida(1398);
        novaComida(524);
        
        //Cria uma caixa com um espaço para entrar
        linhaHorizontalPedra(421, 3);
        linhaHorizontalPedra(425, 3);
        linhaVerticalPedra(421, 7);
        linhaVerticalPedra(427, 7);
        linhaHorizontalPedra(721, 7);
                
    }
    

    @Override
    public void comeu() {
        
        qtd++;
        if(qtd==5){
            //Quando passa de fase salva o tamanho do rabo
            Variaveis.getInstance().setVar("TAMANHO_RABO", getCobra().getTamanho());
            mudarFase();
        }else{
            addRabo(); //Cresce o rabo
            aumentarVelocidade(5); //Aumenta a velocidade
            addPontos(20); //Adiciona 20 pontos
        }
        
    }

    @Override
    public void moveu() {
        addPontos(1); //Ao mover-se pela tela ganha 1 ponto
    }


    @Override
    public void bateuPedra() {
        
        //Ao bater na pedra reinicia a fase
        int tamanho_rabo = getCobra().getTamanho();
        reiniciarFase();
        iniciar();
        getCobra().addRabo(tamanho_rabo);
    }
    
     @Override
    public String getTituloFase() {
        return "Fase2";
    }

    @Override
    public String getTextoFase() {
        return "Coma todas as maçãs e passe de fase.";
    }
    
}
