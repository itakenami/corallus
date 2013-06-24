package corallus.modelo;

public class Pedra implements Item {

    public void acao(Fase f){
        f.bateuPedra();
    }
        
    public char getChar(){
        return '#';
    }
        
}
