package br.ufla.gac106.s2022_2.Bug_killers.Model;

import java.util.Collection;

import br.ufla.gac106.s2022_2.base.Avaliacao;
import br.ufla.gac106.s2022_2.base.Avaliacoes;

public class AvaliacoesCelebridades implements Avaliacoes {

    private Collection<Avaliacao> avaliacoes;


    public AvaliacoesCelebridades(Collection<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    @Override
    public String temaAvaliacao() {
        return "Celebridades";
    }

    @Override
    public Collection<Avaliacao> colecaoAvaliacoes() {
       return avaliacoes;
    }
    
}
