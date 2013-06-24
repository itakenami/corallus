package corallus.modelo;

public abstract class Corpo implements Item {

    protected int posicao;
    protected int direcao;
    
    
    public Corpo(int pos, int dir){
        setPos(pos);
        setDirecao(dir);
    }
    
    public void setPos(int p){
        posicao = p;
    }
    
    public int getPos(){
        return posicao;
    }
    
    public void setDirecao(int d){
        direcao = d;
    }
    
    public int getDirecao(){
        return direcao;
    }
    
    //Metodos da interface
    public void acao(Fase f){
        f.bateuCobra();    
    }
    
    
    public abstract char getChar();

}
