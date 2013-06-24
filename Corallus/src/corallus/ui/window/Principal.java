package corallus.ui.window;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.swing.JLabel;
import corallus.Config;
import corallus.modelo.Jogo;
import corallus.modelo.Corpo;
import corallus.modelo.Customizado;
import corallus.modelo.Tabuleiro;
import corallus.ui.componente.Bloco;
import corallus.ui.util.BufferTeclado;
import corallus.ui.componente.Mensagem;
import corallus.ui.componente.Screen;
import corallus.ui.componente.Topo;
import corallus.ui.util.Imagens;

public class Principal extends javax.swing.JFrame {
    
    //Menus
    private javax.swing.JMenuBar barraMenu;
    
    private javax.swing.JMenu menuArquivo;
    private javax.swing.JMenuItem subNovoJogo;
    private javax.swing.JMenuItem subSair;
    
    private javax.swing.JMenu menuEditar;
    private javax.swing.JMenuItem subDespausar;
    private javax.swing.JMenuItem subLog;
    
    private javax.swing.JMenu menuAjuda;
    private javax.swing.JMenuItem subSobre;

    //Objetos graficos do Jogo
    private Hashtable<Integer, Bloco> paines_blocos;
    private JLabel label_pontos, label_fases, label_texto;
    private Mensagem msg_box;
    private Topo tela_topo;
    private Screen tela_inicial;
    
    
    //Configuracoes do jogo
    private final int TAMANHO_BLOCO = 18;
    private boolean PAUSADO;
    private boolean FIM;
    
    //Objetos principais
    private Jogo jogo;
    private Thread thread_jogo;
    
    
    
    //Construtor cria os objetos da interface e inicializa o modelo O.O.
    public Principal(boolean autostart) {
        
        GerenciadorJanela.newInstance(this);
        
        int largura = Config.LARGURA_TABULEIRO * TAMANHO_BLOCO;
        int altura = Config.ALTURA_TABULEIRO * TAMANHO_BLOCO;
        int altura_formulario = altura + (2 * TAMANHO_BLOCO) + 36 + 72;

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Corallus 0.5");
        //setAlwaysOnTop(true);
        setPreferredSize(new java.awt.Dimension(largura, altura_formulario));
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent evt) {
                keyPress(evt);
            }
        });
        getContentPane().setLayout(null);
        
        barraMenu = new javax.swing.JMenuBar();
        
        
        //Menu arquivo
        
        menuArquivo = new javax.swing.JMenu();
        menuArquivo.setText("Arquivo");
        menuArquivo.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuClick(evt);
            }
        });
        
        subNovoJogo = new javax.swing.JMenuItem();
        subNovoJogo.setText("Novo Jogo");
        subNovoJogo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuNovoJogo(evt);
            }
        });
        menuArquivo.add(subNovoJogo);
        
        subSair = new javax.swing.JMenuItem();
        subSair.setText("Sair");
        subSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.exit(0);
            }
        });
        
        menuArquivo.add(subSair);
        
        barraMenu.add(menuArquivo);

        
        //Menu editar
        
        menuEditar = new javax.swing.JMenu();
        menuEditar.setText("Editar");
        menuEditar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuClick(evt);
            }
        });
        
        subDespausar = new javax.swing.JMenuItem();
        subDespausar.setText("Despausar");
        subDespausar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPause(evt);
            }
        });
        
        menuEditar.add(subDespausar);
        
        subLog = new javax.swing.JMenuItem();
        subLog.setText("Log");
        subLog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Log.getInstance().setVisible(true);
            }
        });
        
        menuEditar.add(subLog);
        barraMenu.add(menuEditar);
        
        //Menu Ajuda
        
        menuAjuda = new javax.swing.JMenu();
        menuAjuda.setText("Ajuda");
        menuAjuda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                menuClick(evt);
            }
        });
        
        subSobre = new javax.swing.JMenuItem();
        subSobre.setText("Sobre");
        subSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuPause(evt);
            }
        });
        
        menuAjuda.add(subSobre);
        barraMenu.add(menuAjuda);
        
        
        setJMenuBar(barraMenu);
        
        //Tela inicial
        tela_inicial = new Screen(largura,altura_formulario);
        tela_inicial.setBounds(0,0,largura, altura_formulario);
        getContentPane().add(tela_inicial);

        //Objetos da interface do jogo
        tela_topo = new Topo();
        tela_topo.setBounds(0, 0, largura, 100);
        getContentPane().add(tela_topo);

        label_pontos = new JLabel();
        label_pontos.setFont(new java.awt.Font("Arial", 1, 16));
        label_pontos.setForeground(new java.awt.Color(255, 255, 255));
        //label_pontos.setBounds(largura-200, 15, 200, 20);
        label_pontos.setBounds(300, 58, 200, 20);
        tela_topo.add(label_pontos);

        label_fases = new JLabel();
        label_fases.setFont(new java.awt.Font("Arial", 1, 20));
        label_fases.setForeground(new java.awt.Color(255, 255, 255));
        label_fases.setBounds(300, 18, 200, 20);
        tela_topo.add(label_fases);
        
        label_texto = new JLabel();
        label_texto.setFont(new java.awt.Font("Arial", 1, 16));
        label_texto.setForeground(new java.awt.Color(255, 255, 255));
        label_texto.setBounds(300, 38, 400, 20);
        tela_topo.add(label_texto);

        msg_box = new Mensagem();
        msg_box.setBounds((largura / 2) / 2, (altura / 2) / 2, (largura) / 2, (altura) / 2);
        msg_box.setVisible(false);
        getContentPane().add(msg_box);
        
        
        Imagens.getInstance();
        
        
        //Area principal do jogo
        paines_blocos = new Hashtable<Integer, Bloco>();
        
        int y = 100, x = 0;

        for (int z = 1; z <= Tabuleiro.getInstance().TOTAL_CASAS; z++) {

            Bloco p = new Bloco(z);
            p.setBounds(x, y, TAMANHO_BLOCO, TAMANHO_BLOCO);

            x += TAMANHO_BLOCO;
            if (z % Config.LARGURA_TABULEIRO == 0) {
                y += TAMANHO_BLOCO;
                x = 0;
            }

            getContentPane().add(p);
            paines_blocos.put(z, p);
        }
        
        pack();
        
        this.setVisible(true);
        
        if(autostart){
            menuNovoJogo(null);
        }

    }
    
    private void desenharTabuleiro() {

        label_fases.setText(jogo.getFase().getTituloFase());
        if(label_texto.getText().equals("")){
            setDisplayTexto(jogo.getFase().getTextoFase());
        }
        

        for (int z = 1; z <= jogo.getTabuleiro().TOTAL_CASAS; z++) {

            Bloco p = paines_blocos.get(z);

            if (Tabuleiro.getInstance().getCasa(z).isOcupada()) {
                if (Tabuleiro.getInstance().getCasa(z).getItem().getChar() == '@') {
                    Corpo c = (Corpo) Tabuleiro.getInstance().getCasa(z).getItem();
                    p.setDirecao(c.getDirecao());
                    p.setTipo(Bloco.CORPO);
                }
                if (Tabuleiro.getInstance().getCasa(z).getItem().getChar() == '#') {
                    p.setTipo(Bloco.PEDRA);
                }
                if (Tabuleiro.getInstance().getCasa(z).getItem().getChar() == '$') {
                    Corpo c = (Corpo) Tabuleiro.getInstance().getCasa(z).getItem();
                    p.setDirecao(c.getDirecao());
                    p.setTipo(Bloco.CABECA);
                }
                if (Tabuleiro.getInstance().getCasa(z).getItem().getChar() == '*') {
                    Corpo c = (Corpo) Tabuleiro.getInstance().getCasa(z).getItem();
                    p.setDirecao(c.getDirecao());
                    p.setTipo(Bloco.RABO);
                }
                if (Tabuleiro.getInstance().getCasa(z).getItem().getChar() == '&') {
                    p.setTipo(Bloco.COMIDA);
                }
                if (Tabuleiro.getInstance().getCasa(z).getItem().getChar() == '%') {
                    Customizado c = (Customizado)Tabuleiro.getInstance().getCasa(z).getItem();
                    p.setTipo(Bloco.CUSTOM);
                    p.setImagem(c.getImagemPath());
                }
            } else {
                p.setTipo(Bloco.CAMINHO);
            }

        }
        repaint();
        jogo.getCobra().getCabeca().setDirecao(3);
        jogo.setMontarTabuleiro(false);
    }
    
    private void iniciar() {
        
        jogo = new Jogo();
        PAUSADO = false;
        FIM = false;
        atualizaPontos();
        BufferTeclado.getInstance().limpar();
        
        if(thread_jogo!=null){
            thread_jogo.interrupt();
            thread_jogo = null;
            System.gc();
        }
        
        thread_jogo = new Thread(new Runnable() {

            @Override
            public void run() {
                try {

                    while (!jogo.isFimJogo()) {

                        if (!PAUSADO) {

                            if (jogo.getMontarTabuleiro()) {
                                desenharTabuleiro();
                                msg_box.setTitulo(jogo.getFase().getTituloFase());
                                msg_box.setTexto(jogo.getFase().getTextoFase());
                                msg_box.setVisible(true);
                                Thread.sleep(5000);
                                msg_box.setVisible(false);
                            }

                            switch (BufferTeclado.getInstance().getBuffer()) {
                                case 0:
                                    jogo.mover();
                                    break;
                                case 1:
                                    jogo.moverCima();
                                    break;
                                case 2:
                                    jogo.moverBaixo();
                                    break;
                                case 3:
                                    jogo.moverDireita();
                                    break;
                                case 4:
                                    jogo.moverEsquerda();
                                    break;
                            }
                            atualizaTabuleiro();
                            atualizaPontos();
                        }
                        
                        Thread.sleep(jogo.getVelocidade());

                    }
                    
                    fimJogo();
                    
                    Thread.sleep(5000);
                    
                    msg_box.setVisible(false);
                    tela_inicial.setVisible(true);

                } catch (InterruptedException ext){
                    Log.getInstance().addText(ext.getMessage());
                    setErroTexto(ext.getMessage());
                } catch (Exception ex1) {
                    //System.out.println("Erro:" + ex1);
                    //ex1.printStackTrace();
                    Log.getInstance().addText(ex1.getMessage());
                    setErroTexto(ex1.getMessage());
                }

                //Jogo chegou ao fim

            }
        });

        thread_jogo.start();
    }
    
    public void pausar(){
        PAUSADO = true;
        msg_box.setTitulo("PAUSADO");
        msg_box.setTexto("Jogo Pausado. Aperte 'P' para voltar.");
        msg_box.setVisible(true);
    }
    
    public void despausar(){
        PAUSADO = false;
        msg_box.setVisible(false);
    }
    
    public void fimJogo(){
        FIM = true;
        msg_box.setTitulo("FIM DO JOGO");
        msg_box.setTexto("Obrigado por jogar Jiboia");
        msg_box.setVisible(true);
    }
    
    private void menuNovoJogo(java.awt.event.ActionEvent evt) {
        tela_inicial.setVisible(false);
        iniciar();
    }
    
    private void menuClick(java.awt.event.MouseEvent evt) {
        PAUSADO = true;
        pausar();
    }
    
    private void menuPause(java.awt.event.ActionEvent evt) {
        despausar();
    }
    
    private void keyPress(java.awt.event.KeyEvent evt) {
        
        if(FIM){
            return;
        }
        
        int key = evt.getKeyCode();
        if (!msg_box.isVisible()) {

            if (!PAUSADO) {
                if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
                    BufferTeclado.getInstance().addBuffer(4);
                } else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
                    BufferTeclado.getInstance().addBuffer(3);
                } else if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
                    BufferTeclado.getInstance().addBuffer(1);
                } else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
                    BufferTeclado.getInstance().addBuffer(2);
                }
            }
        }
        if (key == 80) {
                if (PAUSADO) {
                    despausar();
                } else {
                    pausar();
                }
            }
    }

    private void atualizaPontos() {
        label_pontos.setText("Pontos: " + (int)jogo.getPontos());
    }    


    public void atualizaTabuleiro() {

        ArrayList<Integer> changes = Tabuleiro.getInstance().getAlterados();

        for (int z = 0; z < changes.size(); z++) {

            if (Tabuleiro.getInstance().getCasa(changes.get(z)).isOcupada()) {
                if (Tabuleiro.getInstance().getCasa(changes.get(z)).getItem().getChar() == '@') {
                    Corpo c = (Corpo) Tabuleiro.getInstance().getCasa(changes.get(z)).getItem();
                    paines_blocos.get(changes.get(z)).setDirecao(c.getDirecao());
                    paines_blocos.get(changes.get(z)).setTipo(Bloco.CORPO);
                }
                if (Tabuleiro.getInstance().getCasa(changes.get(z)).getItem().getChar() == '#') {
                    paines_blocos.get(changes.get(z)).setTipo(Bloco.PEDRA);
                }
                if (Tabuleiro.getInstance().getCasa(changes.get(z)).getItem().getChar() == '$') {
                    Corpo c = (Corpo) Tabuleiro.getInstance().getCasa(changes.get(z)).getItem();
                    paines_blocos.get(changes.get(z)).setDirecao(c.getDirecao());
                    paines_blocos.get(changes.get(z)).setTipo(Bloco.CABECA);
                }
                if (Tabuleiro.getInstance().getCasa(changes.get(z)).getItem().getChar() == '*') {
                    Corpo c = (Corpo) Tabuleiro.getInstance().getCasa(changes.get(z)).getItem();
                    paines_blocos.get(changes.get(z)).setDirecao(c.getDirecao());
                    paines_blocos.get(changes.get(z)).setTipo(Bloco.RABO);
                }
                if (Tabuleiro.getInstance().getCasa(changes.get(z)).getItem().getChar() == '&') {
                    paines_blocos.get(changes.get(z)).setTipo(Bloco.COMIDA);
                }
                if (Tabuleiro.getInstance().getCasa(changes.get(z)).getItem().getChar() == '%') {
                    Customizado c = (Customizado)Tabuleiro.getInstance().getCasa(changes.get(z)).getItem();
                    paines_blocos.get(changes.get(z)).setTipo(Bloco.CUSTOM);
                    paines_blocos.get(changes.get(z)).setImagem(c.getImagemPath());
                }
            } else {
                paines_blocos.get(changes.get(z)).setTipo(Bloco.CAMINHO);
            }

        }
        repaint();

    }
    
    public void setErroTexto(String txt){
        label_texto.setForeground(new java.awt.Color(255, 0, 0));
        label_texto.setText(txt);
        
    }
    
    
    public void setDisplayTexto(String txt){
        label_texto.setForeground(new java.awt.Color(255, 255, 255));
        label_texto.setText(txt);
    }
    
    public boolean isShowMessage(){
        return msg_box.isVisible();
    }

}
