package corallus.modelo;

import corallus.Config;
import corallus.modelo.util.CarregadorFase;
import corallus.modelo.Fase;
import corallus.modelo.Casa;
import corallus.modelo.Cobra;
import corallus.modelo.Tabuleiro;

public final class Jogo {

    private final static int CIMA = 1;
    private final static int BAIXO = 2;
    private final static int DIREITA = 3;
    private final static int ESQUERDA = 4;
    private int DIRECAO;
    
    
    private boolean FIM_JOGO;
    private Cobra cobra;
    private Tabuleiro tabuleiro;
    private double pontos;
    private int velocidade;
    private boolean MONTAR_TABULEIRO;
    
    private Fase fase;
    

    public Jogo() {

        tabuleiro = Tabuleiro.getInstance();
        
        FIM_JOGO            = false;
        pontos              = 0;
        velocidade          = 100;
        MONTAR_TABULEIRO    = true;
        
        CarregadorFase.getInstance().carregarFases();
        mudarFase();
        
    }
    
    public boolean getMontarTabuleiro(){
        return MONTAR_TABULEIRO;
    }
    
    public void setMontarTabuleiro(boolean v){
        MONTAR_TABULEIRO = v;
    }
    
    public void mudarFase(){
        
        
        if(CarregadorFase.getInstance().getIdxFase()>0){
            fase.fimFase();
        }
        
        tabuleiro.limpar();
        DIRECAO = DIREITA;
        cobra = new Cobra(Config.LARGURA_TABULEIRO + 3, DIRECAO);
        for (int x = 1; x <= tabuleiro.TOTAL_CASAS; x++) {
            tabuleiro.addCasa(x, new Casa());
        }
        try{
            fase = CarregadorFase.getInstance().getProximaFase();
            fase.carregar(this);
        }catch(Exception ex){
            setFim(true);
        }
    }
    
    public void recarregarFase(){
        
        tabuleiro.limpar();
        DIRECAO = DIREITA;
        cobra = new Cobra(Config.LARGURA_TABULEIRO + 3, DIRECAO);
        for (int x = 1; x <= tabuleiro.TOTAL_CASAS; x++) {
            tabuleiro.addCasa(x, new Casa());
        }
        fase.recarregar(this);
        
    }

    public boolean isFimJogo() {
        return FIM_JOGO;
    }

    public void moverCima() {
        DIRECAO = CIMA;
        mover();
    }

    public void moverBaixo() {
        DIRECAO = BAIXO;
        mover();
    }

    public void moverEsquerda() {
        DIRECAO = ESQUERDA;
        mover();
    }

    public void moverDireita() {
        DIRECAO = DIREITA;
        mover();
    }
    
    public void mover(int pos, int direcao){
        DIRECAO = direcao;
        cobra.move(pos, direcao);
    }

    public void mover() {
        switch (DIRECAO) {
            case DIREITA:
                cobra.moveDireita();
                break;
            case ESQUERDA:
                cobra.moveEsquerda();
                break;
            case CIMA:
                cobra.moveCima();
                break;
            case BAIXO:
                cobra.moveBaixo();
                break;
        }
        verificar();
    }

    private void verificar() {
        if (cobra.achouItem()) {
            cobra.getItemAchado().acao(fase);
        }else{
            fase.moveu();
        }
    }

    /*
    public void print(){
    System.out.print("\n\n");
    int total = Config.TAMANHO * Config.TAMANHO;
    for (int x=1;x<=total;x++){
    
    if(casas.get(x).isOcupada()){
    System.out.print(casas.get(x).getItem().print());
    }else{
    System.out.print(" ");
    }
    
    if(x % Config.TAMANHO == 0){
    System.out.println("");    
    }
    
    }
    }*/
    
    
    public int getDirecao(){
        return DIRECAO;
    }


    public void setFim(boolean fim) {
        FIM_JOGO = fim;
    }
    

    public Tabuleiro getTabuleiro() {
        return tabuleiro;
    }

    public Cobra getCobra() {
        return cobra;
    }

    public Fase getFase() {
        return fase;
    }
    
    public void addPontos(int p){
        pontos+=p;
    }
    
    public double getPontos(){
        return pontos;
    }
    
    public void diminuirPontos(int p){
        pontos-=p;
    }
    
    public void aumentarVelocidade(int v){
        velocidade-=v;
    }
    
    public void diminuirVelocidade(int v){
        velocidade+=v;
    }
    
    public int getVelocidade(){
        return velocidade;
    }
    
    public void setVelocidade(int v){
        velocidade = v;
    }

       
}
