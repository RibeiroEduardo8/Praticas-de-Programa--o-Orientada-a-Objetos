package br.ufla.gac106.s2022_2.Bug_killers.Model;

public class Cantor extends Celebridade {
    private String estilo;
    private String qntMusica;
    

    public Cantor(String nome, String resumo, int idade, String sexo, String estilo, String qntMusica) {
        super(nome, resumo, idade, sexo);
        this.estilo = estilo;
        this.qntMusica = qntMusica;
    }

    public String getEstilo() {
        return estilo;
    }

    public String getQntMusica() {
        return qntMusica;
    }

    @Override
    public String DescricaoCompleta(){
        return super.DescricaoCompleta()+ "\n" + "Estilo Musical: "+estilo + "\nQuantidade de musicas: " + qntMusica + "\r\n";

    }
    
}
