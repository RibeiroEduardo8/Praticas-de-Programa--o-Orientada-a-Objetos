package br.ufla.gac106.s2022_2.Bug_killers.View;

import java.util.Scanner;
import br.ufla.gac106.s2022_2.Bug_killers.Controller.*;

public class InterfaceAdm extends InterfaceComum {

    private Scanner entrada;

    public InterfaceAdm() {
        entrada = new Scanner(System.in);
        insereInfo();
    }

    public void verifica() {
        String tipo = ModuloAdministracao.getInstancia().verificaTipo2();
        if (tipo.equals("adm")) {
            executar();
        } else if (tipo.equals("comum")) {
            menuAvaliacao();
        } else {
            insereInfo();
        }
    }

    public void executar() {
        int opcao = 0;
        // se o usuário for comum ou não estiver cadastrado no sistema, este modulo não
        // é visível para ele
        if (verificaComum()) {
            do {
                exibirMenu();
                System.out.println("\nDigite sua opção: ");
                try {
                    opcao = Integer.parseInt(entrada.nextLine());
                } catch (Exception e) {
                    opcao = 0;
                }
                tratarMenu(opcao);

            } while (opcao != 7);

            // fecha o objeto Scanner para liberar os seus recursos
            entrada.close();
        } else {
            System.out.println("Usuario nao encontrado, por favor tente novamente! \n");
            insereInfo();
            executar();
        }

    }

    /*
     * Método que exibe as opções de menu
     */
    public void exibirMenu() {
        System.out.println("\n");
        System.out.println("1 - Cadastrar celebridade");
        System.out.println("2 - Excluir Celebridade");
        System.out.println("3 - Exibir todas as celebridades ja cadastradas");
        System.out.println("4 - Cadastar usuario");
        System.out.println("5 - Excluir usuario");
        System.out.println("6 - Avaliacoes");
        System.out.println("7 - Sair");
    }

    // switc case para as opções do menu
    private void tratarMenu(int opcao) {
        switch (opcao) {
            case 1:
                cadastrarCelebridade();
                break;
            case 2:
                excluirCelebridade();
                break;
            case 3:
                ExibirCelebridade();
                break;
            case 4:
                cadastrarUsusario();
                break;
            case 5:
                excluirUsuario();
                break;
            case 6:
                menuAvaliacao();
                break;
            case 7:
                finalizar();
                System.out.println("Saindo do programa...");
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }

    private void exibirMensagem() {
        System.out.println("\nCelebridade é um(a)? ");
        System.out.println("1 -  Cantor(a)");
        System.out.println("2 -  Apresentador(a)");
    }

    private int definirOpcao() {
        int opcao;
        try {
            opcao = Integer.parseInt(entrada.nextLine());
        } catch (Exception e) {
            return 0;
        }
        return opcao;
    }

    // metodo para chamar as funções e encerrar todas as conexões abertas durante a
    // execução do codigo
    private void finalizar() {
        ModuloAdministracao.getInstancia().finalizar();
    }

    // Trata a opção se a celebridade é um canto(a) ou apresentador(a)
    private void tratarOpcao(int opcao, String nome, String res, String sexo, int idade) {
        if (opcao == 1) {
            cadastrarCantor(nome, res, sexo, idade);
            ;
        } else if (opcao == 2) {
            cadastrarApresentador(nome, res, sexo, idade);
            ;
        } else {
            System.out.println(" opcao invalida");
            exibirMensagem();
            tratarOpcao(opcao, nome, res, sexo, idade);
        }
    }

    // Método que permite cadastrar novos usuário no sistema
    public void cadastrarUsusario() {
        System.out.print("nome: ");
        String nome = entrada.nextLine();
        System.out.print("senha: ");
        String senha = entrada.nextLine();
        System.out.print("tipo: ");
        String tipo = entrada.nextLine();
        ModuloAdministracao.getInstancia().cadastrarUsuario(nome, senha, tipo);
    }

    public void cadastrarCelebridade() {

        System.out.print("Digite o nome: ");
        String nome = entrada.nextLine();
        if (ModuloAdministracao.getInstancia().verificaCadastro(nome)) {
            String resumo = ModuloAdministracao.getInstancia().resumo(nome);

            if (resumo.equals("Pagina não encontrada!")) {
                System.out.println(resumo);
                executar();
            } else {
                System.out.print("Digite a idade: ");
                int idade = Integer.parseInt(entrada.nextLine());

                System.out.print("Digite o sexo: ");
                String sexo = entrada.nextLine();

                exibirMensagem();
                int var = definirOpcao();

                tratarOpcao(var, nome, resumo, sexo, idade);
            }
        } else {
            System.out.println("Celebridade já cadastrada no sistema");
        }
    }

    public void cadastrarCantor(String nome, String res, String sexo, int idade) {
        System.out.println("Qual o estilo musical do cantor?");
        String estilo = entrada.nextLine();

        System.out.println("quantas musicas esse cantor possui?");
        String qntMusicas = entrada.nextLine();

        ModuloAdministracao.getInstancia().adicionarCantor(nome, res, idade, sexo, estilo, qntMusicas);

    }

    public void cadastrarApresentador(String nome, String resumo, String sexo, int idade) {

        System.out.println("Qual a emissora o apresentador trabalha?");
        String emissora = entrada.nextLine();
        System.out.println("Qual programa é apresentado por ele?");
        String programa = entrada.nextLine();

        ModuloAdministracao.getInstancia().adicionarApresentador(nome, resumo, idade, sexo, programa, emissora);
    }

    // se o usuário for um administrador permite excluir alguma celebridade do
    // sistema
    public void excluirCelebridade() {
        if (ModuloAdministracao.getInstancia().eAdm()) {
            String nome = pedirNome();
            System.out.println(ModuloAdministracao.getInstancia().excluirCelebridade(nome));
        } else {
            System.out.println("Voce não tem acesso a essa função!");
        }
    }

    // chama o método que exibe todas celebridades cadastradas no sistema
    public void ExibirCelebridade() {
        System.out.print(ModuloAdministracao.getInstancia().exibirDescricao());
    }

    private String pedirNome() {
        System.out.print("Digite o nome: ");
        String nome = entrada.nextLine();
        return nome;
    }

    private String pedirSenha() {
        System.out.print("digite sua senha: ");
        String senha = entrada.nextLine();
        return senha;
    }

    // Validacao do usuario, ao entrar no sistema
    private void insereInfo() {
        System.out.println("entre com o nome e senha de usuario: ");
        String nome = pedirNome();
        String senha = pedirSenha();
        ModuloAdministracao.getInstancia().verificaUsuario(nome, senha);
    }

    // se o usuário for um administrador permite excluir algum usuário do sistema
    private void excluirUsuario() {
        if (ModuloAdministracao.getInstancia().eAdm()) {
            String nome = pedirNome();
            ModuloAdministracao.getInstancia().excluirUsuario(nome);
        } else {
            System.out.println("Voce não tem acesso a essa função!");
        }
    }

    // chama o método que verifica se o usuário está cadastrado como comum
    private boolean verificaComum() {
        return ModuloAdministracao.getInstancia().verificaTipo();
    }
}