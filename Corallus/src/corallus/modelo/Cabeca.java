package corallus.modelo;

public class Cabeca extends Corpo {

    public Cabeca(int pos, int dir){
        super(pos, dir);
    }
    
    @Override
    public char getChar(){
        return '$'; //Caracter correspondente a cabeça
    }
}
