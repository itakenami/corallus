/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.fases;

import corallus.modelo.Fase;
import corallus.modelo.util.Variaveis;
import corallus.ui.window.GerenciadorJanela;
import demo.modelo.Bandeira;
import demo.modelo.Estrela;

/**
 *
 * @author itakenami
 */
public class Fase3 extends Fase {

    @Override
    public void iniciar() {
        
        int tamanho_rabo;
        try {
            tamanho_rabo = (Integer) Variaveis.getInstance().getVar("TAMANHO_RABO");
        } catch (Exception ex) {
            tamanho_rabo = 0;
            GerenciadorJanela.getInstance().getPrincipal().setErroTexto(ex.getMessage());
        }
        getCobra().addRabo(tamanho_rabo);
        bordaPedra();
        linhaHorizontalPedra(363, 26);
        linhaHorizontalPedra(963, 26);
        linhaVerticalPedra(363, 13);
        linhaVerticalPedra(388, 13);
        
        //Adiciona itens customizados na tela
        setItem(1275, new Estrela());
        setItem(195, new Bandeira());
        
        novaComida(625);
        
         
    }

    @Override
    public void comeu() {
        mudarFase();
    }
    
}
