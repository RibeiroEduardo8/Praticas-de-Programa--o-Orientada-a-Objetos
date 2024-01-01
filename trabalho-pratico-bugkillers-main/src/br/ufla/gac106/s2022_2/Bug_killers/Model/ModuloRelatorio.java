package br.ufla.gac106.s2022_2.Bug_killers.Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import br.ufla.gac106.s2022_2.Bug_killers.Controller.ModuloAdministracao;
import br.ufla.gac106.s2022_2.base.Avaliacao;
import br.ufla.gac106.s2022_2.base.relatorios.Grafico;

public class ModuloRelatorio {

    private List<Celebridade> celebridades;

    public ModuloRelatorio() {

    }

    public String qntSemAvaliacoes() {
        celebridades = ModuloAdministracao.getInstancia().getCelebridades();
        String mensagem = "";
        int cont = 0;
        for (Celebridade celebridade : celebridades) {
            if (celebridade.qntAvaliacoes() == 0) {
                mensagem = mensagem + "\n" + "Celebridade sem avaliação: " + celebridade.getNome() + "\n";
                cont++;
            } else {

            }

        }
        return "Quantidade de celebridades sem classificações: " + cont + " \n" + mensagem;
    }

    public String qntAvaliacoes() {
        celebridades = ModuloAdministracao.getInstancia().getCelebridades();
        String mensagem = "";
        int cont = 0;
        for (Celebridade celebridade : celebridades) {
            if (celebridade.qntAvaliacoes() != 0) {
                mensagem = mensagem + "\n" + "Celebridade avaliada: " + celebridade.getNome() + "\n";
                cont++;
            }

        }
        return "Quantidade de celebridades com classificações: " + cont + " \n" + mensagem;
    }

    public String retornaCincoPrimeiros() {
        celebridades = ModuloAdministracao.getInstancia().getCelebridades();

        String mensagem = "";
        Comparator<Celebridade> comparador = new Comparator<>() {
            @Override
            public int compare(Celebridade c1, Celebridade c2) {

                return c1.getNome().compareTo(c2.getNome());
            }
        };

        celebridades.sort(comparador);
        for (int i = 1; i < 6 && i< celebridades.size(); i++) {
            mensagem = mensagem + "\n" + i + ":" + celebridades.get(i).getNome() + "\n";
        }
        return mensagem;
    }

    public void exibriGrafico() {
        celebridades = ModuloAdministracao.getInstancia().getCelebridades();

        Collection<Avaliacao> avaliacoes=new ArrayList<>();

        for (Celebridade celebridade : celebridades) {
            avaliacoes.add(new AvaliacaoCelebridade(celebridade));
        }
        AvaliacoesCelebridades a=new AvaliacoesCelebridades(avaliacoes);
        Grafico g= new Grafico();
        g.exibir(a.temaAvaliacao(), a);
    }
}