package br.ufla.gac106.s2022_2.Bug_killers.View;

import java.util.Scanner;
import br.ufla.gac106.s2022_2.Bug_killers.Controller.*;
import br.ufla.gac106.s2022_2.Bug_killers.Model.ModuloAvaliacao;
import br.ufla.gac106.s2022_2.Bug_killers.Model.*;

public class InterfaceComum {
    private ModuloAvaliacao mod;
    private ModuloRelatorio relatorio= new ModuloRelatorio();
    private Scanner entrada;

    public InterfaceComum() {
        mod = new ModuloAvaliacao();
        entrada = new Scanner(System.in);
    }

    public void menuAvaliacao() {
        int opcao;
        {
            do {
                exibirMenuAvaliacao();
                System.out.println("\nDigite sua opção: ");
                try {
                    opcao = Integer.parseInt(entrada.nextLine());
                } catch (Exception e) {
                    opcao = 0;
                }
                tratarMenuAvaliacao(opcao);

            } while (opcao != 7);
        }
    }

    private void exibirMenuAvaliacao() {
        System.out.println("\n");
        System.out.println("1 - Avaliar celebridade");
        System.out.println("2 - Exibir celebridades ordenadas");
        System.out.println("3 - Exibir media");
        System.out.println("4 - Adicionar Comentario");
        System.out.println("5 - Ver Comentarios");
        System.out.println("6 - Relatorios");
        System.out.println("7 - Sair");
    }

    private void tratarMenuAvaliacao(int opcao) {
        switch (opcao) {
            case 1:
                cadastrarNota();
                break;
            case 2:
                /*
                 * String nome;
                 * System.out.
                 * println("digite o nome da celebridade que voce deseja verificar a nota: ");
                 * nome = entrada.nextLine();
                 * mod.testeNotas(nome);
                 */
                exibirCelebridadesOrdenado();
                break;
            case 3:
                exibirMedia();
                break;
            case 4:
                cadastrarComentario();
                break;
            case 5:
                verComentarios();
                break;

            case 6:
                menuRelatorio();
                break;
            case 7:
                System.out.println("Sair");
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }

    private void cadastrarNota() {
        String nome;
        Double nota;
        System.out.println(ModuloAdministracao.getInstancia().exibirDescricaoSimpls());
        System.out.println("digite o nome da celebridade que voce deseja dar uma nota: ");
        nome = entrada.nextLine();
        System.out.println("digite o a nota para essa celebridade: ");
        nota = Double.parseDouble(entrada.nextLine());
        System.out.println(mod.atribuirNota(nome, nota));
    }

    public void cadastrarComentario() {
        String nome;
        String comentario;
        System.out.println(ModuloAdministracao.getInstancia().exibirDescricaoSimpls());
        System.out.println("digite o nome da celebridade que voce deseja fazer um comentario: ");
        nome = entrada.nextLine();
        System.out.println("digite um comentario para essa celebridade: ");
        comentario = entrada.nextLine();
        mod.addComentario(nome, comentario);
    }

    private void exibirMedia() {
        String nome2;
        System.out.println("digite o nome da celebridade que voce deseja verificar a nota media: ");
        nome2 = entrada.nextLine();
        double media = mod.pegarMedia(nome2);
        if (media == 0) {
            System.out.println("Ainda não existe nenhuma avaliacao para esta celebridade ");
        } else {
            System.out.println(media);
        }
    }

    private void exibirCelebridadesOrdenado() {
        int opcao;
        System.out.println("Voce deseja exibir as celebridades ordenadas por: ");
        System.out.println("1- Nome");
        System.out.println("2- Media");
        opcao = Integer.parseInt(entrada.nextLine());
        if (opcao == 1) {
            System.out.println(mod.retornaOrdenado());
        } else if (opcao == 2) {
            System.out.println(mod.retornaOrdenadoMedia());
        } else {
            System.out.println("Opcao invalida!");
        }

        mod.retornaOrdenado();
    }

    private void verComentarios() {
        System.out.println("digite o nome da celebridade que voce deseja verificar os comentarios: ");
        String nome2 = entrada.nextLine();
        System.out.println(mod.exibeComentario(nome2));
    }

    public void menuRelatorio() {
        int opcao;
        {
            do {
                exibirMenuRelatorio();
                System.out.println("\nDigite sua opção: ");
                try {
                    opcao = Integer.parseInt(entrada.nextLine());
                } catch (Exception e) {
                    opcao = 0;
                }
                tratarMenuRelatorio(opcao);

            } while (opcao != 5);
        }
    }

    public void exibirMenuRelatorio() {
        System.out.println("\n");
        System.out.println("1 - Quantidade de Itens classificados");
        System.out.println("2 - Quantidade de itens sem classificação");
        System.out.println("3 - 5 melhores itens classificados");
        System.out.println("4 - Exibir Grafico");
        System.out.println("5 - Sair");
    }

    private void tratarMenuRelatorio(int opcao) {
        switch (opcao) {
            case 1:
                itensClassificados();
                break;
            case 2:
                itensSemClassificacao();
                break;
            case 3:
                melhoresClassificados();
                break;
            case 4:
                exibriGrafico();
                break;
            case 5:
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }

    private void itensClassificados() {
        System.out.println(relatorio.qntSemAvaliacoes());
    }

    private void itensSemClassificacao() {
        System.out.println(relatorio.qntAvaliacoes());
    }

    private void melhoresClassificados() {
        System.out.println(relatorio.retornaCincoPrimeiros());
    }
    private void exibriGrafico(){
        relatorio.exibriGrafico();
    }

}