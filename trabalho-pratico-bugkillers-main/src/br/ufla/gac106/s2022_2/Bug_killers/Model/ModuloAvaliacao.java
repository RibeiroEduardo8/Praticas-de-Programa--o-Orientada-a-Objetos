package br.ufla.gac106.s2022_2.Bug_killers.Model;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.ufla.gac106.s2022_2.Bug_killers.Controller.ModuloAdministracao;

import java.util.Comparator;

public class ModuloAvaliacao {

    private List<Notas> notas;
    private List<Comentario> comentarios;
    private List<Celebridade> celebridades;
    private BancoDeDados bd;

    public ModuloAvaliacao() {
        celebridades = new ArrayList<>();
        notas = new ArrayList<>();
        comentarios = new ArrayList<>();
        bd = new BancoDeDados();
        tranferirNotas();
        tranferirComentarios();
    }

    /*
     * private void trazerDoBD() {
     * celebridades = (bd.tranferirDados());
     * }
     */

    private void tranferirNotas() {
        celebridades = ModuloAdministracao.getInstancia().getCelebridades();
        notas = (bd.tranferirNotas(celebridades));
        for (Celebridade c : celebridades) {
            for (Notas n : notas) {
                if (c.getNome().equals(n.getCelebridade())) {
                    c.pegaNotas(n);
                }
            }

        }
    }

    public Double pegarMedia(String nome) {
        celebridades = ModuloAdministracao.getInstancia().getCelebridades();

        Double notas = 0.0;
        for (Celebridade c : celebridades) {
            if (nome.equals(c.getNome())) {
                notas = c.pegarMedia();
            }
        }
        if (notas.equals("NaN") || notas == null || notas == 0) {
            return 0.0;
        } else {
            return notas;
        }
    }

    public String retornaOrdenado() {
        celebridades = ModuloAdministracao.getInstancia().getCelebridades();

        String ordena = "";
        Comparator<Celebridade> comparador = new Comparator<>() {
            @Override
            public int compare(Celebridade c1, Celebridade c2) {

                return c1.getNome().compareTo(c2.getNome());
            }
        };

        celebridades.sort(comparador);
        for (Celebridade c : celebridades) {
            ordena = ordena + "\n" + "Nome da celebridade: " + (c.getNome() + " Média: " + c.pegarMedia());
        }
        return "Ordenando em ordem crescente e com as iniciais maiusculas antes: " + "\n" + ordena;
    }

    public String retornaOrdenadoMedia() {
        celebridades = ModuloAdministracao.getInstancia().getCelebridades();

        String ordena = "";
        Comparator<Celebridade> comparador = new Comparator<>() {
            @Override
            public int compare(Celebridade c1, Celebridade c2) {
                return Double.compare(c2.pegarMedia(), (c1.pegarMedia()));
            }
        };

        celebridades.sort(comparador);
        for (Celebridade c : celebridades) {
            ordena = ordena + "\n" + "Nome da celebridade: " + (c.getNome() + " Média: " + c.pegarMedia());
        }
        return "Apresentando celebridades em ordem crescente a partir da media: " + "\n" + ordena;
    }

    public void addComentario(String celebridade, String comentario) {
        celebridades = ModuloAdministracao.getInstancia().getCelebridades();

        Usuario usuarioLogado = ModuloAdministracao.getInstancia().pegarUsuario();

        String data = getDateTime();
        for (Celebridade c : celebridades) {
            if (c.getNome().equals(celebridade)) {
                c.pegaComentario(new Comentario(c, usuarioLogado.getNome(), data, comentario));
                bd.addComentario(c, usuarioLogado.getNome(), data, comentario);
            }
        }
    }

    public String atribuirNota(String nome, Double nota) {
        celebridades = ModuloAdministracao.getInstancia().getCelebridades();
        String mensagem = " Nota cadastrada com sucesso!! ";
        Usuario usuarioLogado = ModuloAdministracao.getInstancia().pegarUsuario();
        if (nota <= 10 && nota >= 0) {

            if (!verificaUser(usuarioLogado, nome)) {
                for (Celebridade c : celebridades) {
                    if (c.getNome().equals(nome)) {
                        c.pegaNotas(new Notas(c, usuarioLogado.getNome(), nota));
                        bd.atribuirNota(c, usuarioLogado.getNome(), nota);
                    }
                }
                return mensagem;
            } else {
                for (Celebridade c : celebridades) {
                    if (c.getNome().equals(nome)) {
                        c.setNota(c.getNome(), usuarioLogado.getNome(), nota);
                        bd.updateNota(c, usuarioLogado.getNome(), nota);
                    }
                }
                mensagem = "Voce possui uma avaliacao para essa celebridade, sua antiga nota foi substituida! ";
            }
        } else {
            mensagem = "Nota não esta nos padrões (Entre 0 ou 10)!";
        }
        return mensagem;
    }

    private boolean verificaUser(Usuario usuarioLogado, String nome) {
        celebridades = ModuloAdministracao.getInstancia().getCelebridades();

        boolean comentou = false;
        for (Notas n : notas) {
            if (n.getAutor().equals(usuarioLogado.getNome()) &&
                    n.getCelebridade().equals(nome)) {
                comentou = true;
                System.out.println(n.getAutor() + " " + usuarioLogado.getNome());
            }
        }
        return comentou;
    }

    public String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        return dateFormat.format(date);
    }

    public void tranferirComentarios() {
        celebridades = ModuloAdministracao.getInstancia().getCelebridades();

        comentarios = (bd.tranferirComentarios(celebridades));
        for (Celebridade c : celebridades) {
            for (Comentario n : comentarios) {
                if (c.getNome().equals(n.getCelebridade())) {
                    c.pegaComentario(n);
                }
            }
        }
    }

    public void testeNotas(String nome) {
        celebridades = ModuloAdministracao.getInstancia().getCelebridades();

        for (Celebridade c : celebridades) {
            if (nome.equals(c.getNome())) {
                c.retornaNotas();
            }
        }
    }

    public String exibeComentario(String nome) {
        celebridades = ModuloAdministracao.getInstancia().getCelebridades();

        String comentario = null;
        for (Celebridade c : celebridades) {
            if (nome.equals(c.getNome())) {
                comentario = c.retornaComentario();
            }
        }
        return comentario;
    }

}
