package br.ufla.gac106.s2022_2.Bug_killers.Model;

public class Comentario{
    private Celebridade celebridade;
    private String autor;
    private String data;
    private String comentario;

    public Comentario(Celebridade celebridade, String autor, String data, String comentario) {
        this.celebridade = celebridade;
        this.autor = autor;
        this.data = data;
        this.comentario = comentario;
    }

    public String getCelebridade() {
        return celebridade.getNome();
    }


    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

}