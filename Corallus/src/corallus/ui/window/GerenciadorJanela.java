/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corallus.ui.window;

/**
 *
 * @author itakenami
 */
public class GerenciadorJanela {
    
    private static GerenciadorJanela instance;
    private Principal principal;
    
    private GerenciadorJanela(Principal p){
        this.principal = p;
    }
    
    
    protected static void newInstance(Principal p){
        instance = new GerenciadorJanela(p);
    }
    
    public static GerenciadorJanela getInstance(){
        return instance;
    }
    
    public Principal getPrincipal(){
        return principal;
    }
    
}
