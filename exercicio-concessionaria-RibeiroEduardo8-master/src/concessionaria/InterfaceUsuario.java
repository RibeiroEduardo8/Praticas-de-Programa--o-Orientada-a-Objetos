/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concessionaria;

import fabricaautomoveis.carros.Categoria;
import fabricaautomoveis.carros.Marca;

import java.util.Scanner;

/**
 *
 * @author julio
 */
public class InterfaceUsuario {

    private Concessionaria ppooVeiculos;
    private Scanner entrada;
        
    public void exibir() {
        ppooVeiculos = new Concessionaria("PPOO Veículos", Marca.FIAT);
        entrada = new Scanner(System.in);
        
        int opcao;        
        do {
            opcao = menu();
            
            switch (opcao) {
                case 1:
                    comprarCarro();
                    
                    break;
                case 2:
                    mudarMarca();
                    break;
                case 3:
                    System.out.println("Até mais!");
                    break;
                default:
                    System.out.println("Opção inválida!");                   
            }
            
        } while (opcao != 3);        
    }            
    
    private int menu() {
        System.out.println("1 - Comprar Carro");
        System.out.println("2 - Mudar marca");
        System.out.println("3 - Sair");
        
        return Integer.parseInt(entrada.nextLine());
    }

    private void comprarCarro() { 
        System.out.println("Voce esta comprado carros da marca: "+ppooVeiculos.getMarcaFranquia());
        System.out.println("Voce deseja:");
        System.out.println("1- Prosseguir");
        System.out.println("2- Trocar de marca");
        int opcao= Integer.parseInt(entrada.nextLine());
        if(opcao==1){
            System.out.print("Escolha a categoria (1: Popular, 2: Pickup ou 3: Luxo): ");
            Categoria categoria = Categoria.peloID(Integer.parseInt(entrada.nextLine()));
            
            System.out.print("Escolha a cor: ");
            String cor = entrada.nextLine();
            
            if (ppooVeiculos.comprarCarro(categoria, cor)) {
                System.out.println("Parabéns!!! O carro é seu!!!");            
            }
            else {
                System.out.println("Sinto muito, não quer escolher outro?");
            }
            
            esperarTecla();
        }else if(opcao==2){
            mudarMarca();
        }
    }
    private void mudarMarca(){
        System.out.println("Qual marca voce deseja comprar?");
        String marca= entrada.nextLine();
        String mensagem=ppooVeiculos.verificaMarca(marca);
        if(mensagem.equals("nao existe")){
            System.out.println("Esta marca não existe na concessionaria, por favor digite uma marca valida!");
            mudarMarca();
        }else if(mensagem.equals("existe")){
            ppooVeiculos.trocarMarca(marca);

        }

       
    }
    
    private void esperarTecla() {
        entrada.nextLine();
    }
}

