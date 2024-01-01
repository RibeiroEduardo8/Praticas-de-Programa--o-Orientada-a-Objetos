package br.ufla.gac106.s2022_2.Bug_killers.Model;

public class Usuario {
    private String senha;
    private String nome;
    private String tipo;

    public Usuario(String nome, String senha, String tipo) {
        this.senha = senha;
        this.nome = nome;
        this.tipo = tipo;
    }

    public String getSenha() {
        return senha;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }
  
}
