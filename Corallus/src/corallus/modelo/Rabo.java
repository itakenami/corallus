package corallus.modelo;

public class Rabo extends Corpo {
    
    private boolean ULTIMO;

    public Rabo(int pos, int dir){
        super(pos,dir);
        ULTIMO = false;
    }
    
    public void setUltimo(boolean u){
        ULTIMO = u;
    }
    
    public void setPos(int p){
        posicao = p;
        ULTIMO = false;
    }
    
    public char getChar(){
        if(ULTIMO){
            return '*';
        }else{
            return '@';
        }
    }
}
