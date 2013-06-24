package corallus;

import corallus.modelo.util.CarregadorFase;
import corallus.modelo.Fase;
import corallus.ui.window.Principal;

public class Config {
    
    //Largura do tabuleiro
    public static int LARGURA_TABULEIRO = 50;
    //Altura do tabuleiro
    public static int ALTURA_TABULEIRO = 30;
    //Exibir o numero das casas
    public static boolean SHOW_NUM = false;
    
    //Adiciona uma fase ao jogo
    public static void addFase(Fase f){
        CarregadorFase.getInstance().addFase(f);
    }
    
    //Inicia o jogo definindo se exibirá a tela de autostart
    public static void iniciar(boolean autostart){
        new Principal(autostart);
    }
    
    //Inicia o jogo automaticamente
    public static void iniciar(){
        iniciar(true);
    }
}
