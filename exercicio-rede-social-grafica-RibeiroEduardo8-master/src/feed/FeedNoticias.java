package feed;
import java.util.*;
import java.util.Collections;


import iu.MensagemNaoEncontradaException;

/**
 * Classe que define o Feed de Noticias da Rede Social.
 * Possui uma lista de mensagens que pode ser de texto ou com foto e metodos
 para postar as mensagens e getTextoExibicao todo o Feed.
 
 Esse eh um exemplo do livro: Programacao Orientada a Objetos com Java - uma 
 introducao pratica utilizando BlueJ.
 * 
 * @author  Michael Kölling and David J. Barnes
 *          Traduzido e adaptado por Julio Cesar Alves
 */
public class FeedNoticias {
    // lista de mensagens publicadas no Feed
    private List<Mensagem> mensagens;
    private List<String> autores;

    /**
     * Cria o Feed de Noticias (apenas inicializa a lista de mensagens)
     */
    public FeedNoticias() {
        mensagens = new ArrayList<>();
        autores= new ArrayList<>();
    }

    /**
     * Metodo para postar uma mensagem de texto no feed. Cria a mensagem e a
     * adiciona na lista de mensagens.
     * 
     * @param autor Nome do autor da mensagem.
     * @param texto Texto da mensagem em si.
     */
    public void postarMensagemTexto(String autor, String texto) {
        mensagens.add(new MensagemTexto(autor, texto));
        adicionaAutor(autor);
    }

    /**
     * Metodo para postar uma mensagem com foto no feed. Cria a mensagem e a
     * adiciona na lista de mensagens.
     * 
     * @param autor Nome do autor da mensagem.
     * @param arquivoFoto Nome do arquivo da foto.
     * @param legenda Legenda da foto.
     */
    public void postarMensagemFoto(String autor, String arquivoFoto, String legenda) {
        mensagens.add(new MensagemFoto(autor, arquivoFoto, legenda));
    }

    /**
     * Metodo de obtenção da lista de publicações (somente-leitura) do Feed de Noticias.     
     * 
     * @return A lista de publicações do Feed
     */
    public List<Publicacao> getPublicacoes() {
        // Cria uma lista de publicações somente-leitura
        List<Publicacao> publicacoes = new ArrayList<>();
        // Adiciona todas as mensagens do feed na lista de publicações
        publicacoes.addAll(mensagens);
        // Retorna a lista de publicações
        return publicacoes;
    }
    public List<Publicacao> getPublicacoes(String autor) {
        List<Publicacao> publicacoes = new ArrayList<>();
        for(Mensagem m : mensagens){
            if(m.getAutor().equals(autor)){
                publicacoes.add(m);
            }
        }
        return publicacoes;   
    }
    
    /**
     * Método para curtir uma mensagem no Feed de notícias (a partir do 
     * identificador da mensagem).
     * 
     * @param idMensagem Identificador da mensagem
     */
    public void curtir(int idMensagem) {
        Mensagem mensagem = buscarMensagemPeloId(idMensagem);
        if(mensagem!= null){
            mensagem.curtir();
        }else{
            throw new MensagemNaoEncontradaException(idMensagem);
        }
    }
    
    /**
     * Método para comentar uma mensagem do Feed de notícias (a partir do
     * identificador da mensagem)
     * 
     * @param idMensagem Identificador da mensagem
     * @param comentario Comentário a ser acrescentado
     */
    public void comentar(int idMensagem, String comentario) {
        Mensagem mensagem = buscarMensagemPeloId(idMensagem);
        mensagem.comentar(comentario);
    }

    /**
     * Retorna a quantidade de mensagens publicadas
     * 
     * @return A quantidade de mensagens já publicadas
     */
    public int nroMensagens() {
        return mensagens.size();
    }
    
    /**
     * Retorna a mensagem correspondente ao identificador passado. Retorna null
     * se não existir mensagem com esse identificador no feed. Obs: realiza
     * busca simples (percorrendo a lista).
     * 
     * @param idMensagem Identificador da mensagem
     * @return Mensagem correspondente ao identificador
     */
    private Mensagem buscarMensagemPeloId(int idMensagem) {
        for (Mensagem mensagem : mensagens) {
            if (mensagem.getId() == idMensagem) {
                return mensagem;
            }
        }
        return null;
    }
    // metodo para verificar se o autor esta armazenado na lista de autores
    private boolean verificaAutor(String autor){
        for (String a : autores) {
            if(a.equals(autor)){
                return false;
            }      
        }
        return true;

    }
    private void adicionaAutor(String autor){
        //se retornar false significa que o autor ja esta na lista e não precisa adicionar novamente 
        if(verificaAutor(autor)){
            autores.add(autor);
        }
    }
    public List<String> getAutores(){
        return Collections.unmodifiableList(autores);
    }

}