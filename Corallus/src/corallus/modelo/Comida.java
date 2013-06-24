package corallus.modelo;

public class Comida implements Item {

    public void acao(Fase f){
        f.comeu();
    }
    
    public char getChar(){
        return '&';
    }

}
