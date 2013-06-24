package corallus.modelo;

public class Casa {

    //Item que uma casa pode ter
    private Item item;
    //Verifica se a casa está ocupada
    private boolean OCUPADA;

    public Casa() {
        item = null;
        OCUPADA = false;
    }

    public void setItem(Item i) {
        item = i;
        OCUPADA = true;
    }

    public Item getItem() {
        return item;
    }

    public void desocupar() {
        item = null;
        OCUPADA = false;
    }

    public boolean isOcupada() {
        return OCUPADA;
    }
}
