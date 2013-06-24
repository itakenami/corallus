/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corallus.modelo.util;

import corallus.ui.window.GerenciadorJanela;
import corallus.ui.window.Log;
import java.util.HashMap;

/**
 *
 * @author itakenami
 */
public class Variaveis {
    
    private static Variaveis instance;
    private HashMap<String, Object> vars;
    
    private Variaveis(){
        vars = new HashMap<String, Object>();
    }
    
    public void setVar(String nome, Object cont){
        vars.put(nome, cont);
    }
    
    public Object getVar(String nome) throws Exception{
        if(vars.containsKey(nome)){
            return vars.get(nome);
        }else{
            GerenciadorJanela.getInstance().getPrincipal().setErroTexto(nome+": Variavel não encontrada.");
            Log.getInstance().addText(nome+": Variavel não encontrada.");
            throw new Exception(nome+": Variavel não encontrada.");
        }
        
    }
    
    public static Variaveis getInstance(){
        if(instance==null){
            instance = new Variaveis();
        }
        return instance;
    }
    
}
