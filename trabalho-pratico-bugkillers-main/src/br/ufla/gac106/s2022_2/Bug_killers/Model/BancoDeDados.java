package br.ufla.gac106.s2022_2.Bug_killers.Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BancoDeDados {

    private Statement comando;
    private Connection conexao;

    public BancoDeDados() {
        try {
            conexao = DriverManager.getConnection("jdbc:sqlite:teste.db");
            comando = conexao.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Metodo para criar a tabela no banco de dados
    public void criarTabela() {
        try {
            comando.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS CELEBRIDADE(NOME VARCHAR(50) NOT NULL, RESUMO VARCHAR(8000) NOT NULL, IDADE INT NOT NULL, SEXO VARCHAR(50) NOT NULL, ESTILO VARCHAR(50) NULL, QNTMUSICAS VARCHAR(50) NULL, PROGRAMA VARCHAR(50) NULL, EMISSORA VARCHAR(50) NULL, IDENTIFICAÇÃO INT NOT NULL);");
            comando.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS USUARIO(NOME VARCHAR(50) NOT NULL, SENHA VARCHAR(50) NOT NULL, TIPO VARCHAR(50) NOT NULL);");
            comando.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS NOTAS(NOME VARCHAR(50) NOT NULL,AUTOR VARCHAR(50) NOT NULL, NOTA DOUBLE NOT NULL);");

            comando.executeUpdate(
                    "CREATE TABLE IF NOT EXISTS COMENTARIOS(NOME VARCHAR(50) NOT NULL, USUARIO VARCHAR(50) NOT NULL,DATA TEXT, COMENTARIO VARCHAR(500) NOT NULL);");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Deu erro!");
        }
    }

    // metodo para inserir dados na tabela Celebridade

    public void inserirCantor(Cantor celebridade) {
        try {
            comando.executeUpdate(
                    "INSERT INTO CELEBRIDADE (NOME, RESUMO, IDADE, SEXO, ESTILO, QNTMUSICAS,IDENTIFICAÇÃO) VALUES ('"
                            + celebridade.getNome() + "', '" + celebridade.getResumo() + "', " + celebridade.getIdade()
                            + ", '" + celebridade.getSexo() + "','" + celebridade.getEstilo() + "','"
                            + celebridade.getQntMusica() + "'," + 1 + ");");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Deu erro");
        }
    }

    public void inserirNotas(String nomeUsuario, String nomeCelebridade, Double notas) {
        try {
            comando.executeUpdate("INSERT INTO NOTAS (NOME, AUTOR , NOTA ) VALUES ('" + nomeCelebridade + "','"
                    + nomeUsuario + "', '" + notas + "');");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void inserirApresentador(Apresentador celebridade) {
        try {
            comando.executeUpdate(
                    "INSERT INTO CELEBRIDADE (NOME, RESUMO, IDADE, SEXO, PROGRAMA, EMISSORA,IDENTIFICAÇÃO) VALUES ('"
                            + celebridade.getNome() + "', '" + celebridade.getResumo() + "', " + celebridade.getIdade()
                            + ", '" + celebridade.getSexo() + "','" + celebridade.getPrograma() + "','"
                            + celebridade.getEmissora() + "'," + 2 + ");");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Deu erro");
        }
    }

    // metodo para inserir dados do usuario no banco de dados
    public void inserirDadosUsuario(String nome, String senha, String tipo) {
        try {
            comando.executeUpdate("INSERT INTO USUARIO (NOME, SENHA, TIPO) VALUES ('" + nome + "', '" + senha + "', '"
                    + tipo + "');");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Deu erro");
        }
    }

    // metodo para transferir dados do usuario do banco de dados para o arrayList em
    // interação
    public Usuario pegarUsuario(String n, String s) {
        Usuario tipoUsuario = null;
        ArrayList<Usuario> dados = new ArrayList<>();
        try {
            ResultSet linhas = comando.executeQuery("SELECT * FROM USUARIO;");
            while (linhas.next()) {
                String nome = linhas.getString("NOME");
                String senha = linhas.getString("SENHA");
                String tipo = linhas.getString("TIPO");
                dados.add(new Usuario(nome, senha, tipo));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (Usuario u : dados) {
            if (u.getNome().equals(n) && u.getSenha().equals(s)) {
                tipoUsuario = u;
            }
        }
        return tipoUsuario;
    }

    // metodo para transferir dados do banco de dados para o arrayList em interação
    public ArrayList<Celebridade> tranferirDados() {
        ArrayList<Celebridade> dados = new ArrayList<>();
        try {
            ResultSet linhas = comando.executeQuery("SELECT * FROM CELEBRIDADE;");
            while (linhas.next()) {
                int identificacao = linhas.getInt("IDENTIFICAÇÃO");
                // se a identificção for igual a 1 ele vai adicionar um cantor no arraList de
                // celebridades

                if (identificacao == 1) {
                    String nome = linhas.getString("NOME");
                    String resumo = linhas.getString("RESUMO");
                    int idade = linhas.getInt("IDADE");
                    String sexo = linhas.getString("SEXO");
                    String estilo = linhas.getString("ESTILO");
                    String qntMusica = linhas.getString("QNTMUSICAS");
                    // adiciona no arrayList o objeto que vai ser retornado pela função
                    dados.add(new Cantor(nome, resumo, idade, sexo, estilo, qntMusica));
                } else {
                    String nome = linhas.getString("NOME");
                    String resumo = linhas.getString("RESUMO");
                    int idade = linhas.getInt("IDADE");
                    String sexo = linhas.getString("SEXO");
                    String programa = linhas.getString("PROGRAMA");
                    String emissora = linhas.getString("EMISSORA");
                    // adiciona no arrayList o objeto que vai ser retornado pela função
                    dados.add(new Apresentador(nome, resumo, idade, sexo, programa, emissora));

                }
            }
            linhas.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dados;
    }

    // metodo para excluir uma celebradade
    public void excluirCelebridade(String nome) {
        try {
            comando.executeUpdate("DELETE FROM CELEBRIDADE WHERE NOME = '" + nome + "';");
            comando.executeUpdate("DELETE FROM NOTAS WHERE NOME = '" + nome + "';");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // metodo para excluir um usuario
    public void excluirUsuario(String nome) {
        try {
            comando.executeUpdate("DELETE FROM USUARIO WHERE NOME = '" + nome + "';");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // metodo para encerrar a conexão com o banco de dados
    public void encerraConexao() {
        try {
            conexao.close();
            comando.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // -------------------------------------------------------------------------------------------------------------------------------------------------------

    // insere no banco de dados um comentario com dados da celebridade, usuario e
    // data
    public void addComentario(Celebridade celebridade, String usuario, String data, String comentario) {
        try {
            comando.executeUpdate(
                    "INSERT INTO COMENTARIOS (NOME, USUARIO, DATA, COMENTARIO) VALUES ('" + celebridade.getNome()
                            + "', '" + usuario + "', '" + data + "', '" + comentario + "');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // insere no banco de dados uma nota atribuida a uma celebridadepor um usuario
    public void atribuirNota(Celebridade celebridade, String autor, double nota) {
        try {
            comando.executeUpdate("INSERT INTO NOTAS (NOME, AUTOR, NOTA) VALUES ('" + celebridade.getNome()
                    + "', '" + autor + "', '" + nota + "');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // pega notas salvas do banco de dados e as adiciona em um vetor de objetos
    public List<Notas> tranferirNotas(List<Celebridade> celebridades) {
        ArrayList<Notas> notas = new ArrayList<>();
        try {
            ResultSet linhas = comando.executeQuery("SELECT * FROM NOTAS;");
            while (linhas.next()) {
                String nome = linhas.getString("NOME");
                String autor = linhas.getString("AUTOR");
                Double nota = linhas.getDouble("NOTA");
                for (Celebridade a : celebridades) {
                    if (nome.equals(a.getNome())) {
                        // cria objeto Notas adicionado ela ao vetor
                        notas.add(new Notas(a, autor, nota));
                    }
                }

            }
            linhas.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return notas;
    }

    // pega comentarios salvos no banco de dados e os adiciona em um arraylist de
    // objetos
    public List<Comentario> tranferirComentarios(List<Celebridade> celebridades) {
        ArrayList<Comentario> comentarios = new ArrayList<>();
        try {
            ResultSet linhas = comando.executeQuery("SELECT * FROM COMENTARIOS;");
            while (linhas.next()) {
                String nome = linhas.getString("NOME");
                String autor = linhas.getString("USUARIO");
                String data = linhas.getString("DATA");
                String comentario = linhas.getString("COMENTARIO");
                for (Celebridade a : celebridades) {
                    if (nome.equals(a.getNome())) {
                        comentarios.add(new Comentario(a, autor, data, comentario));
                    }
                }
            }
            linhas.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comentarios;
    }

    // altera a avaliacao de um usuario a determinada celebridade
    public void updateNota(Celebridade c, String nome, Double nota) {
        try {
            comando.executeUpdate("UPDATE NOTAS SET NOME = '" + c.getNome() + "' , AUTOR = '" + nome
                    + "', NOTA = '" + nota + "' WHERE NOME = '" + c.getNome() + "' AND AUTOR = '" + nome + "';");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro no update nota");
        }
    }
}