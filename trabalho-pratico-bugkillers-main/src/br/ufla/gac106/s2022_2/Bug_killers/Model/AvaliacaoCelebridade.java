package br.ufla.gac106.s2022_2.Bug_killers.Model;

import java.util.Collection;

import br.ufla.gac106.s2022_2.base.Avaliacao;

public class AvaliacaoCelebridade implements Avaliacao {
    private Celebridade celebridade;


    public AvaliacaoCelebridade(Celebridade celebridade) {
        this.celebridade = celebridade;
    }

    @Override
    public String nomeItemAvaliado() {
        return celebridade.getNome();
    }

    @Override
    public double classificacaoMedia() {
        return celebridade.pegarMedia();

    }

    
}
