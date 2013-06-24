/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corallus.modelo;

import java.util.ArrayList;

/**
 *
 * @author itakenami
 */
public class Caminho {
    
    private int[] caminho;
    private int idx = -1;
    
    private int val_atual    = -1;
    private int val_anterior = -1;
    
    public Caminho(int[] passos){
        caminho = passos;
    }
    
    public Integer getProximo(){
        
        val_anterior = val_atual;
        
        if(idx==caminho.length-1){    
            idx = 0;
        }else{
            idx++;
        }
        
        val_atual = caminho[idx];
                
        return val_atual;
    }
    
    public int getAnterior(){
        return val_anterior;
    }
    
}
