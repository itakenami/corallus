package corallus.modelo;

import java.util.*;
import corallus.Config;

public final class Cobra {

    //Cabeça da cobra
    private Cabeca cabeca;
    //Rabo da cobra
    private ArrayList<Rabo> rabo;
    //Ultima posição ocupada pelo rabo
    private int ULTIMA_POSICAO;
    
    //Direção atual da cobra
    private int DIRECAO;
    
    //Constantes de direção
    private final int CIMA = 1;
    private final int BAIXO = 2;
    private final int DIREITA = 3;
    private final int ESQUERDA = 4;
    
    //Informa se a cobra achou algum item na casa
    private boolean ACHOU_ITEM;
    //Item encontrado pela cobra
    private Item item;

    public Cobra(int pos, int direcao) {
        
        //Valores de inicialização
        ACHOU_ITEM = false;
        item = null;
        DIRECAO = direcao;
        ULTIMA_POSICAO = pos;
        
        //Cria a cabeça
        cabeca = new Cabeca(pos,direcao);
        //Adiciona a quantidade mínima de rabos que a cobra deve ter
        rabo = new ArrayList<Rabo>();
        addRabo();
        addRabo();
        addRabo();
        
    }
    
    //Retorna o último rabo
    public Rabo getRabo(){
        return rabo.get(rabo.size()-1);
    }
    
    //Retorna a cabeça
    public Cabeca getCabeca(){
        return cabeca;
    }
    
    //Adiciona uma quantidade de rabos a cobra
    public void addRabo(int tam) {
        for(int x=0;x<tam;x++){
            addRabo();
        }
    }

    //Adiciona 1 rabo a cobra
    public void addRabo() {
        Rabo r;
        if(ULTIMA_POSICAO==getPos()){//Se o rabo estiver sendo adicionado enquanto a cobra está parada
            r = new Rabo(getPos(),DIRECAO);
        }else{
            r = new Rabo(ULTIMA_POSICAO,getRabo().getDirecao());
        }
        rabo.add(r);
    }
    

    //Move a cobra para direita
    public void moveDireita() {
        //Evita movimento errado
        if(DIRECAO == ESQUERDA){
            setPos(getPos()-1);
            return;
        }
        DIRECAO = DIREITA;
        setPos(getPos() + 1);
    }

    //Move para esquerda
    public void moveEsquerda() {
        //Evita movimento errado
        if(DIRECAO == DIREITA){
            setPos(getPos()+1);
            return;
        }
        DIRECAO = ESQUERDA;
        setPos(getPos() - 1);
    }

    //Move para cima
    public void moveCima() {
        //Evita movimento errado
        if(DIRECAO == BAIXO){
            setPos(getPos()+Config.LARGURA_TABULEIRO);
            return;
        }
        DIRECAO = CIMA;
        //Se passar do topo vai para baixo
        setPos(getPos() - Config.LARGURA_TABULEIRO < 1?(Tabuleiro.getInstance().DELTA+Config.LARGURA_TABULEIRO) + (getPos() - Config.LARGURA_TABULEIRO) + 1:getPos() - Config.LARGURA_TABULEIRO);
    }

    public void moveBaixo() {
        //Evita movimento errado
        if(DIRECAO == CIMA){
            setPos(getPos()-Config.LARGURA_TABULEIRO);
            return;
        }
        DIRECAO = BAIXO;
        //Se passar de baixo vai para o topo
        setPos(getPos() + Config.LARGURA_TABULEIRO > Tabuleiro.getInstance().TOTAL_CASAS?getPos()-Tabuleiro.getInstance().DELTA-1:getPos() + Config.LARGURA_TABULEIRO);
    }
    
    //Coloca a cobra em uma nova posição e direção
    public void move(int pos, int direcao) {
        
        DIRECAO = direcao;
        setPos(pos,direcao);
    }
    
    //Retorna o tamanho da cobra
    public int getTamanho(){
        return rabo.size()-3;
    }

    //Verifica se a cobra achou um item
    public boolean achouItem() {
        return ACHOU_ITEM;
    }

    //Retorna o item achado, caso não tenho encontrado um item, retorna uma exceção
    public Item getItemAchado() {
        if(achouItem()){
            return item;
        }else{
            throw new RuntimeException("Item não encontrado");
        }
        
    }

    //Retorna a posição da cobra que corresponde a cabeça
    public int getPos() {
        return cabeca.getPos();
    }
    
    //Define uma nova posição para a cobra seguir pela cabeça
    private void setPos(int pos, int direcao){
        
        //Adiciona uma alteração para a posição anterior da cebeça
        Tabuleiro.getInstance().addAlteracao(cabeca.getPos());
        
        //A linha de baixa estava comentada e descomentei para não ficar a cabeça quando teletransportava para outro lugar
        Tabuleiro.getInstance().getCasa(cabeca.getPos()).desocupar();
        
        cabeca.setPos(pos);
        cabeca.setDirecao(direcao);
        
        for(int x=0;x<rabo.size();x++){
            Tabuleiro.getInstance().addAlteracao(rabo.get(x).getPos());
            Tabuleiro.getInstance().getCasa(rabo.get(x).getPos()).desocupar();
            rabo.get(x).setPos(pos);
            rabo.get(x).setDirecao(direcao);
        }
    }

    private void setPos(int pos) {

        if (Tabuleiro.getInstance().getCasa(pos).isOcupada()) {
            ACHOU_ITEM = true;
            item = Tabuleiro.getInstance().getCasa(pos).getItem();
        } else {
            ACHOU_ITEM = false;
        }


        int rabo_nova_pos = cabeca.getPos();
        int rabo_nova_dir = cabeca.getDirecao();

        Tabuleiro.getInstance().addAlteracao(rabo_nova_pos);//Mudar o que é cabeca e passará a ser corpo 
        
        int dir_atual = cabeca.getDirecao();
        int dir_prox = DIRECAO;
        
        
        if (dir_atual != dir_prox) {
            if ((dir_atual == 3 && dir_prox == 2)) {
                rabo_nova_dir = 5;
            }
            if((dir_atual == 1 && dir_prox == 4)){
                rabo_nova_dir = 9;
            }
            if ((dir_atual == 2 && dir_prox == 4)) {
                rabo_nova_dir = 6;
            }
            if((dir_atual == 3 && dir_prox == 1)){
                rabo_nova_dir = 10;
            }
            if ((dir_atual == 4 && dir_prox == 1)) {
                rabo_nova_dir = 7;
            }
            if((dir_atual == 2 && dir_prox == 3)){
                rabo_nova_dir = 11;
            }
            if ((dir_atual == 1 && dir_prox == 3)) {
                rabo_nova_dir = 8;
            }
            if((dir_atual == 4 && dir_prox == 2)){
                rabo_nova_dir = 12;
            }
        }else{
            rabo_nova_dir = DIRECAO;
        }
        
        cabeca.setPos(pos);
        cabeca.setDirecao(DIRECAO);
       

        //comentado para deixa para plotar a cabeca por ultimo para sobrepor o corpo
        //Tabuleiro.getInstance().getCasa(pos).setItem(cabeca);
        //Tabuleiro.getInstance().addAlteracao(pos);//Mudar a nova cabeça

        for (int x = 0; x < rabo.size(); x++) {

            Rabo r = rabo.get(x);

            int rabo_velha_pos = r.getPos();
            int rabo_velha_dir = r.getDirecao();

            r.setPos(rabo_nova_pos);
            

            
            if(x!=rabo.size()-1){
                r.setDirecao(rabo_nova_dir);
            }else{
                if(rabo_nova_dir>4){
                    r.setDirecao(rabo.get(x-1).getDirecao());
                }
                
            }

            if (x == rabo.size() - 1) {
                
                ULTIMA_POSICAO = rabo_velha_pos;
                r.setUltimo(true);
                
              
                if(r.getDirecao()==10){
                    r.setDirecao(3);
                }
                if(r.getDirecao()==7){
                    r.setDirecao(4);
                }
                if(r.getDirecao()==12){
                    r.setDirecao(4);
                }
                if(r.getDirecao()==5){
                    r.setDirecao(3);
                }
                if(r.getDirecao()==8){
                    r.setDirecao(1);
                }
                if(r.getDirecao()==11){
                    r.setDirecao(2);
                }
                if(r.getDirecao()==6){
                    r.setDirecao(2);
                }
                if(r.getDirecao()==9){
                    r.setDirecao(1);
                }
                 
                
                Tabuleiro.getInstance().addAlteracao(rabo_nova_pos);
                if (rabo_velha_pos != 0) {
                    Tabuleiro.getInstance().getCasa(rabo_velha_pos).desocupar();
                    Tabuleiro.getInstance().addAlteracao(rabo_velha_pos);
                }
            }

            Tabuleiro.getInstance().getCasa(rabo_nova_pos).setItem(r);
            rabo_nova_pos = rabo_velha_pos;
            rabo_nova_dir = rabo_velha_dir;

        }

        Tabuleiro.getInstance().getCasa(pos).setItem(cabeca);
        Tabuleiro.getInstance().addAlteracao(pos);//Mudar a nova cabeça
    }
}
