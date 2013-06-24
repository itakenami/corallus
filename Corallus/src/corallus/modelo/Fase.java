/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corallus.modelo;

import java.util.Random;
import corallus.Config;
import corallus.ui.util.Som;
import corallus.ui.window.GerenciadorJanela;
import corallus.ui.window.Principal;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author itakenami
 */
public abstract class Fase {

    private Tabuleiro tabuleiro;
    private Cobra cobra;
    private Jogo jogo;
    private boolean reload;
    public final int CIMA = 1;
    public final int BAIXO = 2;
    public final int DIREITA = 3;
    public final int ESQUERDA = 4;

    public abstract void iniciar();

    public abstract void comeu();

    //metodo pode ser reimplementado para informar o que vai acontecer no caso da fase ser reiniciada
    public void reiniciar() {
        iniciar();
    }

    private void preparar(Jogo j) {
        reload = false;
        jogo = j;
        tabuleiro = j.getTabuleiro();
        cobra = j.getCobra();
    }

    final void carregar(Jogo j) {
        preparar(j);
        iniciar();
        reload = true;
    }

    final void recarregar(Jogo j) {
        preparar(j);
        reiniciar();
        reload = true;

    }

    public final void fim() {
        jogo.setFim(true);
    }

    public final void addRabo() {
        getCobra().addRabo();
    }

    public final void addAlteracao(int pos) {
        if (reload) {
            getTabuleiro().addAlteracao(pos);
        }
    }

    public final void desocuparCasa(int pos) {
        if (pos > 0) {
            getTabuleiro().getCasa(pos).desocupar();
            addAlteracao(pos);
        }
    }

    public final int getPosDesocupada() {
        int x;
        do {
            x = new Random().nextInt(getTabuleiro().TOTAL_CASAS);
        } while (getTabuleiro().getCasa(x).isOcupada());
        return x;
    }

    public final void novaComida(int pos) {
        getTabuleiro().getCasa(pos).setItem(new Comida());
        addAlteracao(pos);
    }

    public final void novaComida() {
        novaComida(getPosDesocupada());

    }

    public final void bordaPedra() {

        Tabuleiro tabuleiro = getTabuleiro();

        int delta = (tabuleiro.TOTAL_CASAS - Config.LARGURA_TABULEIRO);

        for (int x = 1; x <= Config.LARGURA_TABULEIRO; x++) {
            tabuleiro.getCasa(x).setItem(new Pedra());
            tabuleiro.getCasa(delta + x).setItem(new Pedra());
            addAlteracao(x);
            addAlteracao(delta + x);
        }


        int i = 1;
        for (int x = 1; x < Config.ALTURA_TABULEIRO; x++) {
            tabuleiro.getCasa(i).setItem(new Pedra());
            tabuleiro.getCasa(i + (Config.LARGURA_TABULEIRO - 1)).setItem(new Pedra());
            addAlteracao(i);
            addAlteracao(i + (Config.LARGURA_TABULEIRO - 1));
            i += Config.LARGURA_TABULEIRO;
        }
    }

    public final void addPedra(int pos) {
        tabuleiro.getCasa(pos).setItem(new Pedra());
        addAlteracao(pos);
    }

    public final void caixaPedraAberta(int pos, int largura, int altura) {
        linhaHorizontalPedra(pos, largura);
        linhaVerticalPedra(pos, altura);
        linhaHorizontalPedra(pos + (Config.LARGURA_TABULEIRO * altura) - Config.LARGURA_TABULEIRO, largura);
        linhaVerticalPedra(pos + largura - 1, altura);

    }

    public final void caixaPedra(int pos, int qtd) {
        for (int y = 0; y < qtd; y++) {
            for (int x = pos; x < pos + qtd; x++) {
                tabuleiro.getCasa(x).setItem(new Pedra());
                addAlteracao(x);
            }
            pos += Config.LARGURA_TABULEIRO;
        }
    }

    public final void linhaVerticalPedra(int pos, int qtd) {
        for (int y = 0; y < qtd; y++) {
            tabuleiro.getCasa(pos).setItem(new Pedra());
            addAlteracao(pos);
            pos += Config.LARGURA_TABULEIRO;
        }
    }

    public final void linhaHorizontalPedra(int pos, int qtd) {
        for (int x = pos; x < pos + qtd; x++) {
            tabuleiro.getCasa(x).setItem(new Pedra());
            addAlteracao(x);
        }
    }

    public final void diminuirVelocidade(int v) {
        jogo.diminuirVelocidade(v);
    }

    public final void aumentarVelocidade(int v) {
        jogo.aumentarVelocidade(v);
    }

    public final void setDefaultVelocidade() {
        jogo.setVelocidade(100);
    }

    public final void addPontos(int p) {
        jogo.addPontos(p);
    }

    public final void mudarFase() {
        jogo.mudarFase();
        GerenciadorJanela.getInstance().getPrincipal().setDisplayTexto("");
        jogo.setMontarTabuleiro(true);
    }

    public final void moverPara(int pos, int direcao) {
        jogo.mover(pos, direcao);
    }

    public final void reiniciarFase() {
        jogo.recarregarFase();
        jogo.setMontarTabuleiro(true);

    }

    public final Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public final Cobra getCobra() {
        return cobra;
    }
    
    public final void tocarSom(String som){
        Som.play(som);
    }

    public void setItem(int pos, Item i) {
        tabuleiro.getCasa(pos).setItem(i);
        addAlteracao(pos);
    }

    public void bateuCobra() {
        fim();
    }

    public void bateuPedra() {
        fim();
    }

    public void fimFase() {
    }

    public void moveu() {
    }

    public String getTituloFase() {
        return this.getClass().getName();
    }

    public String getTextoFase() {
        return "Texto para " + this.getClass().getName();
    }

    public void setDisplayTexto(String txt) {
        GerenciadorJanela.getInstance().getPrincipal().setDisplayTexto(txt);
    }

    public void setItemMovimento(final int[] pos, final long tempo, final Customizado item) {

        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                try {

                    int idx = 0;
                    int pos_anterior = -1;


                    while (item.isItemMovimentoFim() == false) {

                        if (!GerenciadorJanela.getInstance().getPrincipal().isShowMessage()) {



                            if (pos_anterior != -1) {
                                desocuparCasa(pos_anterior);
                            }

                            setItem(pos[idx], item);
                            pos_anterior = pos[idx];

                            if (idx == pos.length - 1) {
                                idx = 0;
                            } else {
                                idx++;
                            }

                        }

                        Thread.sleep(tempo);

                    }
                } catch (InterruptedException ex) {
                    System.out.println("ERRO");
                }
            }
        });
        t.start();

    }
}
