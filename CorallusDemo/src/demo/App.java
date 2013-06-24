/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import corallus.Config;
import demo.fases.Fase1;
import demo.fases.Fase2;
import demo.fases.Fase3;

/**
 *
 * @author itakenami
 */
public class App extends Config {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //SHOW_NUM = true;   
        addFase(new Fase1());
        addFase(new Fase2());
        addFase(new Fase3());
        iniciar();
    }
}
