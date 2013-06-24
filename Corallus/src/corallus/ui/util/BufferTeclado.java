package corallus.ui.util;


import java.util.Vector;


public class BufferTeclado {
    
    private static BufferTeclado instance;
    
    private Vector<Integer> buf;
    
    public static BufferTeclado getInstance(){
        if(instance==null){
            instance = new BufferTeclado();
        }
        return instance;
    }
    
    private BufferTeclado(){
        buf = new Vector<Integer>();
    }
    
    
    public synchronized void addBuffer(int i){
        buf.add(i);
        
    }
    
    public void limpar(){
        buf.clear();
    }
    
    public synchronized int getBuffer(){
        if(buf.size()<1){
            return 0;
        }
        int r = buf.firstElement();
        buf.remove(0);
        return r;
    }
    
}
