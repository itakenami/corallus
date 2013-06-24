package corallus.modelo;

import java.util.*;
import corallus.Config;
import corallus.modelo.Casa;

public class Tabuleiro {

    private static Tabuleiro instance;
    private HashMap<Integer, Casa> casas;
    private ArrayList<Integer> change;
    public final int TOTAL_CASAS;
    public final int DELTA;
    

    private Tabuleiro() {
        change = new ArrayList<Integer>();
        casas = new HashMap<Integer, Casa>();
        TOTAL_CASAS = Config.ALTURA_TABULEIRO * Config.LARGURA_TABULEIRO;
        DELTA = TOTAL_CASAS - Config.LARGURA_TABULEIRO;
    }

    public static Tabuleiro getInstance() {
        if (instance == null) {
            instance = new Tabuleiro();
        }
        return instance;
    }

    public void addCasa(int id, Casa c) {
        casas.put(id, c);
    }
    
    public void limpar(){
        casas.clear();
    }

    public Casa getCasa(int id) {
        return casas.get(id);
    }

    public void addAlteracao(int pos) {
        change.add(pos);
    }

    public ArrayList<Integer> getAlterados() {
        ArrayList<Integer> clone = (ArrayList<Integer>) change.clone();
        change.clear();
        return clone;
    }
   
}
