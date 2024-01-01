import java.util.Scanner;

/*
 * Classe que trata a interface com o usuário (menu de opções)
 */
public class InterfaceUsuario {

    // Scanner para obter dados do usuário via terminal
    private Scanner entrada;
    private Zoologico chamada;

    /* 
     * Construtor da classe
     */
    public InterfaceUsuario() {
        entrada = new Scanner(System.in);
        chamada= new Zoologico();
    }

    /*
     * Método que trata o loop de exibição e tratamento do menu
     */
    public void executar() {
        int opcao;
        do {
            exibirMenu();
            
            System.out.println("\nDigite sua opção: ");
            opcao = Integer.parseInt(entrada.nextLine());

            tratarMenu(opcao);

        } while (opcao != 5);

        // fecha o objeto Scanner para liberar os seus recursos
        entrada.close();
    }

    /*
     * Método que exibe as opções de menu
     */
    public void exibirMenu() {
        System.out.println("1 - Cadastrar animal");
        System.out.println("2 - Descrever animal/ Descrição simples");
        System.out.println("3 - Listar animal/Descrição longa");        
        System.out.println("4 - Listar animais/ Descrição completa do zoologico");
        System.out.println("5 - Sair");
    }

    /*
     * Método que trata uma opção de menu escolhida pelo usuário
     */
    private void tratarMenu(int opcao) {
        switch (opcao) {
            case 1:
                cadastrarAnimal();
                break;
            case 2:
                descreverAnimal();
                break;
            case 3:
                listarAnimais();
                break;
            case 4:
                listarAnimaisCompleto();
                break;
            case 5:
                System.out.println("Saindo do programa...");
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }

        // se o usuário não estiver saindo do programa, pede para ele digitar ENTER antes de exibir o menu novamente
        if (opcao != 5) {
            System.out.println("\nDigite ENTER para continuar!");
            entrada.nextLine();
        }
    }

    /*
     * Método auxiliar que obtém uma String do usuário
     */
    private String pedirString(String instrucao) {
        System.out.print(instrucao + ": ");
        String informacao = entrada.nextLine();
        return informacao;
    }


    /*
     * Trata a opção de menu: Cadastrar Animal
     */
    private void cadastrarAnimal() {
        // implemente seu código aqui.
        System.out.println("Qual animal voce deseja cadastrar?");
        String animal= entrada.nextLine();
        if(animal.equals("leao") || animal.equals("Leao")){
            String nome = pedirString("Digite o nome do animal");
            String cor=pedirString("qual a cor do animal?");
            chamada.adicionarLeao(nome, cor);
        }else if(animal.equals("gorila") || animal.equals("Gorila")){
            String nome = pedirString("Digite o nome do animal");
            String cor=pedirString("qual a cor do animal?");
            chamada.adicionarGorila(nome, cor);
        }else if(animal.equals("ema") || animal.equals("Ema")){
            String nome = pedirString("Digite o nome do animal");
            String cor=pedirString("qual o tipo de voo?");
            chamada.adicionarEma(nome, cor);
        }else if(animal.equals("arara") || animal.equals("Arara")){
            String nome = pedirString("Digite o nome do animal");
            String cor=pedirString("qual o tipo de voo?");
            chamada.adicionarArara(nome, cor);
        }else{
            System.out.println("Não existe esse animal no zoologico");
        }       
    }

    /*
     * Trata a opção de menu: Descrever Animal
     */
    private void descreverAnimal() {
        // implemente seu código aqui.
        String nome = pedirString("Digite o nome do animal");
        System.out.println(chamada.getDescricaoCurta(nome));
    }

    /*
     * Trata a opção de menu: Listar Animais
     */
    private void listarAnimais() {
        // implemente seu código aqui.
        String nome = pedirString("Digite o nome do animal");
        System.out.println(chamada.getDescricaoLonga(nome));
    }

    /*
     * Trata a opção de menu: Listar Animais
     */
    private void listarAnimaisCompleto() {
        // implemente seu código aqui.
        chamada.getDescricaoZoologico();
    }
}