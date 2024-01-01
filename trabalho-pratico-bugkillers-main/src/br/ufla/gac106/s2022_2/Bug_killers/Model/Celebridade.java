package br.ufla.gac106.s2022_2.Bug_killers.Model;

import java.util.ArrayList;
import java.util.List;

public class Celebridade {
    private String nome;
    private String resumo;
    private int idade;
    private String sexo;
    private List<Notas> notas;
    private List<Comentario> comentarios;
    private Double media;

    public Celebridade(String nome, String resumo, int idade, String sexo) {
        this.nome = nome;
        this.resumo = resumo;
        this.idade = idade;
        this.sexo = sexo;
        notas = new ArrayList<>();
        comentarios = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getResumo() {
        return resumo;
    }

    public String DescricaoCompleta() {
        return "Nome: " + nome + "\n" + "Resumo: " + resumo + "\n" + "Idade: " + idade + "\n" + "Sexo:" + sexo;
    }

    public int getIdade() {
        return idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void pegaNotas(Notas nota) {
        notas.add(nota);
    }

    public void pegaComentario(Comentario c) {
        comentarios.add(c);
    }

    public void setNota(String c, String autor, double nota) {
        for (Notas n : notas) {
            if (c.equals(n.getCelebridade()) && autor.equals(n.getAutor())) {
                n.setNotas(nota);
            }
        }
    }

    public void retornaNotas() {
        for (Notas n : notas) {
            System.out.println(n.getAutor() + " " + nome + " Nota: " + n.getNotas());
        }
    }

    public String retornaComentario() {
        String comentario = "";
        for (Comentario n : comentarios) {
            comentario = comentario + " \n" + "Autor da postagem: "+n.getAutor() + " \n " +"Nome da celebridade avaliada: "+ nome +"\n"+ " Comentario: "+ n.getComentario()+"\n"+ " Data do comentario: " + n.getData()+"\n";
        }
        return comentario;
    }

    public Double pegarMedia() {
        int cont = 0;
        double total = 0.0;
        for (Notas n : notas) {
            total = total + n.getNotas();
            cont++;

        }
        media = (total / cont);
        return media;
    }

    public int qntAvaliacoes(){
        return notas.size();
    }
}
