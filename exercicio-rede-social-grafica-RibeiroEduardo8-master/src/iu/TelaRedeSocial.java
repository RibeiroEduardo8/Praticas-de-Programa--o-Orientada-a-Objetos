package iu;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Font;

import feed.FeedNoticias;
import feed.Publicacao;

/**
 * Classe criada para implementar a interface gráfica da Rede Social.
 * O objetivo dessa implementação é didático! 
 * - Exercitar e apresentar conceitos de GUIs (Interfaces Gráficas de Usuário) 
 *   e Tratamento de Exceções
 * 
 * @author Julio Cesar Alves
 */
public class TelaRedeSocial {
    // Janela da nossa tela
    private JFrame janela;
    // Caixa de texto para exibir o feed de noticiai    
    private JTextArea areaTextoFeed;    
    // Botão para postar uma mensagem no feed
    private JScrollPane deslizar;
    private JButton botaoPostarMensagem;
    // Botão para curtir uma mensagem do feed
    private JButton botaoCurtir;
    // Botão para comentar uma mensagem do feed
    private JButton botaoComentar;
    
    // Objeto que representa a Regra de Negócios (a lógica da Rede Social em si)
    private FeedNoticias feed;
    private JButton botaoAtualizar;
    private JComboBox<String> opcoes;
    private boolean carregando;
    /**
     * Construtor da classe: cria o feed, os componentes e monta a tela.
    */
    public TelaRedeSocial() {
        feed = new FeedNoticias();
        
        construirJanela();
        carregando=true;
    }

    /**
     * Constroi os componentes e monta a janela
    */
    private void construirJanela() throws HeadlessException {
        janela = new JFrame("GUI - Rede Social");
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        criarComponentes();
        
        montarJanela();
    }

    /**
     * Cria os componentes da tela e faz a inscrição nos eventos necessários
     */
    private void criarComponentes() {
        // criando os componentes
        areaTextoFeed = new JTextArea();
        botaoPostarMensagem = new JButton("Postar Mensagem");
        botaoCurtir = new JButton("Curtir");
        botaoComentar = new JButton("Comentar");
        botaoAtualizar=new JButton("Atualizar");
        deslizar=new JScrollPane(areaTextoFeed);
        opcoes= new JComboBox<>();
        
        // impede que o usuário edite a área de texto do feed
        areaTextoFeed.setEditable(false);
        areaTextoFeed.setFont(new Font("Arial", Font.ITALIC, 16));
        
        // adiciona o método que tratará o evento de clique no botão Postar Mensagem
        botaoPostarMensagem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                postarMensagem();
            }            
        });
        
        // adiciona o método que tratará o evento de clique no botão Curtir
        botaoCurtir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                curtirMensagem();
            }
        });

        botaoComentar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                comentarMensagem();
            }
        });
        botaoAtualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarAreaTextoFeed();
            }
        });
        opcoes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarAreaTextoFeed();
            }
        });
    }

    /**
     * Monta a janela
     */
    private void montarJanela() {
        janela.setSize(500, 600); 
        janela.setLayout(new BorderLayout());

        JPanel painelSuperior = new JPanel();
        painelSuperior.setLayout(new FlowLayout());
        painelSuperior.add(new JLabel("Feed de Notícias"));
        painelSuperior.add(opcoes);
        adicionaItens(feed.getAutores());
        janela.add(painelSuperior, BorderLayout.NORTH);
        
        JPanel painelCentral = new JPanel();
        painelCentral.setLayout(new BoxLayout(painelCentral, BoxLayout.Y_AXIS));
        painelCentral.add(deslizar);
        janela.add(painelCentral, BorderLayout.CENTER);
        
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new FlowLayout());
        painelBotoes.add(botaoPostarMensagem);
        painelBotoes.add(botaoCurtir);
        painelBotoes.add(botaoComentar);
        painelBotoes.add(botaoAtualizar);
        janela.add(painelBotoes, BorderLayout.SOUTH);

    }
    
    /*
     * Exibe a tela da Rede Social
    */
    public void exibir() {
        janela.setVisible(true);
    }
    
    /**
     * Posta uma mensagem no feed. Solicita o autor e a mensagem ao usuário,
     * posta no Feed e atualiza a área de texto de exibição do feed.
     */
    private void postarMensagem() {
        String autor = JOptionPane.showInputDialog("Autor da mensagem");
        // Se o usuário digitou algum autor e confirmou
        if(autor != null) {
            String mensagem = JOptionPane.showInputDialog("Texto da mensagem");
            // Se o usuário digitou alguma mensagem e confirmou
            if (mensagem != null) {
                feed.postarMensagemTexto(autor, mensagem);
                adicionaItens(feed.getAutores());   
                atualizarAreaTextoFeed();
            }
        }
    }
    
    /**
     * Curte uma mensagem. Solicita o identificador da mensagem ao usuário,
     * curte a mensagem e atualiza a área de texto de exibição do feed.
     */
    private void curtirMensagem(){
        try{
            if(feed.nroMensagens()>0){
                int idMensagem = Integer.parseInt(JOptionPane.showInputDialog("Id da mensagem"));
                feed.curtir(idMensagem);
                atualizarAreaTextoFeed();
                
                
            }else{
                JOptionPane.showMessageDialog(janela, "Não existe mensagens para curtir", "ERRO!", JOptionPane.ERROR_MESSAGE);

            }
        }catch(MensagemNaoEncontradaException e){
            JOptionPane.showMessageDialog(janela, "Mesnagem com id "+ e.getId()+" não encontrada", "ERRO!", JOptionPane.ERROR_MESSAGE);

        }
        catch(Exception e){
            JOptionPane.showMessageDialog(janela, e.getMessage(), "ERRO!", JOptionPane.ERROR_MESSAGE);
            curtirMensagem();
        }
        
    }    
    private void comentarMensagem() {
        int idMensagem = Integer.parseInt(JOptionPane.showInputDialog("Id da mensagem"));
        String comentario = JOptionPane.showInputDialog("Texto do comentario");
        if (comentario != null) {
            feed.comentar(idMensagem, comentario);
            atualizarAreaTextoFeed();
        }
        
        
    }    
    /**
     * Atauliza a área de texto de exibição do Feed.
     */
    private void atualizarAreaTextoFeed() {  
        areaTextoFeed.setText("");
        List<Publicacao> publicacoes;
        // Obtém as publicações do feed de notícias
        if(carregando==false){
            int a = opcoes.getSelectedIndex();
            String teste=opcoes.getItemAt(a);
            if(teste.equals("Todos")){
               publicacoes=feed.getPublicacoes();
            }else{
                publicacoes = feed.getPublicacoes(teste);
            }
            for (Publicacao publicacao : publicacoes) {
                areaTextoFeed.append(publicacao.getTextoExibicao());
            }
        }

        

        
       
    }

    private void adicionaItens(List <String> autor){
        carregando=true;
        opcoes.removeAllItems();
        opcoes.addItem("Todos");
        for (String a : autor) {
            opcoes.addItem(a);
        }
        carregando=false;

    }   
}
