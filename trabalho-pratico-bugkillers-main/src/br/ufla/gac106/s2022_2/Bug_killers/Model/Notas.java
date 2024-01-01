package br.ufla.gac106.s2022_2.Bug_killers.Model;

public class Notas {
    private String autor;
    private Celebridade celebridade;
    private Double nota;
    private int avaliacoes;
    private double total;

    public Notas(Celebridade celebridade, String autor, Double nota) {
        this.celebridade = celebridade;
        this.autor = autor;
        this.nota = nota;
    }
   
    public String getAutor() {
        return autor;
    }
    
    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Double getNotas() {
        return nota;
    }
    
    public void setNotas(Double notas) {
        this.nota = notas;
    }

    public int getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(int avaliacoes) {
        this.avaliacoes = avaliacoes;
    }
    
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getCelebridade() {
        return celebridade.getNome();
    }
}
