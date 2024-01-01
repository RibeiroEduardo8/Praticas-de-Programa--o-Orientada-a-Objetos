package br.ufla.gac106.s2022_2.Bug_killers.Controller;

import java.util.ArrayList;
import java.util.List;
import br.ufla.gac106.javaWikiAPI.PaginaWiki;
import br.ufla.gac106.javaWikiAPI.Wiki;
import br.ufla.gac106.s2022_2.Bug_killers.Factory.CelebridadeFactory;
import br.ufla.gac106.s2022_2.Bug_killers.Model.*;

public class ModuloAdministracao {
    private ArrayList<Celebridade> celebridades;
    private Wiki wiki;
    private BancoDeDados bd;
    private Usuario usuarioLogado;
    private static ModuloAdministracao instancaiUnica;

    private ModuloAdministracao() {
        celebridades = new ArrayList<>();
        wiki = new Wiki();
        usuarioLogado = null;
        bd = new BancoDeDados();
        InicializaBD();
        trazerDoBD();

    }

    public static ModuloAdministracao getInstancia() {
        if (instancaiUnica == null) {
            instancaiUnica = new ModuloAdministracao();
        }
        return instancaiUnica;
    }

    // metodo chamado para criar as tabelas no inicio do codigo se elas ainda não
    // existirem
    public void InicializaBD() {
        bd.criarTabela();

    }

    /*
     * O método retorna uma instância de Celebridade, mas sabemos que essa instância
     * criada é na verdade um Cantor porque passamos o tipo "cantor" para o método.
     * Então é feito um cast para Cantor e adicioná-lo na lista de celebridades e no
     * banco de dados.
     */
    public void adicionarCantor(String nome, String resumo, int idade, String sexo, String estilo, String qntMusica) {
        Celebridade celeb = CelebridadeFactory.criarCelebridade("cantor", nome, resumo, idade, sexo, null, null, estilo,
                qntMusica);
        if (celeb instanceof Cantor) {
            Cantor cantor = (Cantor) celeb;
            celebridades.add(cantor);
            bd.inserirCantor(cantor);
        } else {
            throw new IllegalArgumentException("Celebridade criada não é um cantor");
        }
    }

    public boolean verificaCadastro(String nome) {
        boolean teste = true;
        for (Celebridade c : celebridades) {
            if (nome.equals(c.getNome())) {
                teste = false;
            }
        }
        return teste;
    }

    public List<Celebridade> getCelebridades() {
        return (celebridades);
    }

    /*
     * O método retorna uma instância de Celebridade, mas sabemos que essa instância
     * criada é na verdade um Apresentador porque passamos o tipo "cantor" para o
     * método.
     * Então é feito um cast para Cantor e adicioná-lo na lista de celebridades e no
     * banco de dados.
     */
    public void adicionarApresentador(String nome, String resumo, int idade, String sexo, String programa,
            String emissora) {
        Celebridade celeb = CelebridadeFactory.criarCelebridade("apresentador", nome, resumo, idade, sexo, programa,
                emissora, null, null);
        if (celeb instanceof Apresentador) {
            Apresentador apresentador = (Apresentador) celeb;
            celebridades.add(apresentador);
            bd.inserirApresentador(apresentador);
        } else {
            throw new IllegalArgumentException("Celebridade criada não é um apresentador");
        }
    }

    public void cadastrarUsuario(String nome, String senha, String tipo) {
        bd.inserirDadosUsuario(nome, senha, tipo);
    }

    public void excluirUsuario(String nome) {
        bd.excluirUsuario(nome);
    }

    // A partir do nome da celebridade chama um método da Wiki para ober um resumo
    // sobre esse artista
    public String resumo(String nome) {
        String a = " ";
        try {
            PaginaWiki pagina = wiki.consultarPagina(nome);
            a = pagina.getResumo();
        } catch (Exception e) {
            System.out.println("pagina nao encontrada! ");
        }
        return a;

    }

    public String excluirCelebridade(String nome) {
        Celebridade c = verificaCelebridade(nome);
        if (c != null) {
            celebridades.remove(c);
            bd.excluirCelebridade(nome);
            return nome + " foi removido(a) do sistema com sucesso";
        } else {
            return nome + " nao foi encontrado no sistema";
        }
    }

    // Verifica se a celebridade está cadastrada no sistema (dentro do arrylist)
    private Celebridade verificaCelebridade(String nome) {
        for (Celebridade c : celebridades) {
            if (nome.equals(c.getNome())) {
                return c;
            }
        }
        return null;
    }

    public String exibirDescricao() {
        String descricao = "";
        for (Celebridade c : celebridades) {
            descricao += c.DescricaoCompleta() + "\n";
        }
        return descricao;
    }

    public String exibirDescricaoSimpls() {
        String descricao = "";
        for (Celebridade c : celebridades) {
            descricao += c.getNome() + "\n";
        }
        return descricao;
    }

    // metodo para receber os dados das celebridades que estão no banco de dados
    public void trazerDoBD() {
        celebridades = (bd.tranferirDados());
    }

    public void verificaUsuario(String n, String s) {
        usuarioLogado = bd.pegarUsuario(n, s);
    }

    public Usuario pegarUsuario() {
        return usuarioLogado;
    }

    // metodo para verificar se um usuario é administrador
    public boolean eAdm() {
        if (usuarioLogado.getTipo().equals("administrador")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean verificaTipo() {
        if (usuarioLogado.getTipo().equals("comum") || usuarioLogado.getTipo().equals(" ")) {
            return false;
        } else {
            return true;
        }
    }

    public String verificaTipo2() {
        String tipo = "nenhum";
        if (usuarioLogado.getTipo().equals("comum")) {
            tipo = "comum";
        } else if (usuarioLogado.getTipo().equals("moderador") || usuarioLogado.getTipo().equals("administrador")) {
            tipo = "adm";
        }
        return tipo;
    }

    // método para encerrar a conexão com o banco de dados e com a wiki
    public void finalizar() {
        bd.encerraConexao();
        try {
            wiki.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
     * public void chamaGrafico() {
     * for (Celebridade celebridade : celebridades) {
     * avaliacoes.add(new AvaliacaoCelebridade(celebridade));
     * }
     * 
     * }
     * /*
     * public Usuario pegarUsuario(){
     * return usuarioLogado;
     * }
     */

    // ------------------------------------------------------------------------------------------------------

    /*
     * public double retornaMedia(String nome){
     * double media = bd.buscaMedia(nome);
     * return media;
     * 
     * }
     * public void testeMedia(String nome){
     * System.out.println(bd.buscaMedia(nome));
     * 
     * }
     * 
     * }
     * 
     * public void addCimentario(String celebridade, String comentario){
     * bd.addComentario(celebridade, usuarioLogado.getNome(), comentario);
     * }
     * 
     * public void atribuitrNota(String nome, Double nota){
     * for(Notas n : notas) {
     * if(nome.equals(n.getNome())){
     * n.setQnt(n.getQnt()+1);
     * n.setTotal(n.getTotal()+nota);
     * n.setMedia(n.getTotal()/n.getQnt());
     * }
     * }
     * }
     */

}
