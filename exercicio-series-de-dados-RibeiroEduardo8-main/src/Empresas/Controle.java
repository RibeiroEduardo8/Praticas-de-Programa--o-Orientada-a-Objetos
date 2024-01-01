package Empresas;

import java.util.ArrayList;

import series.SerieDados;

public class Controle {
    
    private SerieDadosAcoes serie;
    private SerieDadosAcoes serie2;

    public Controle() {
        serie = new SerieDadosAcoes(1, 3, "Petrobras");
        serie2 = new SerieDadosAcoes(1, 3, "Vale");

    }
    public void adiciona(){
        if(serie.adicionarAcoes(1, 25)){
            
        }else{
            System.out.println("Erro");
        }
        if(serie.adicionarAcoes(2, 22)){
            
        }else{
            System.out.println("Erro");
        }
        if(serie.adicionarAcoes(3, 26)){
            
        }else{
            System.out.println("Erro");
        }
        if(serie2.adicionarAcoes(1, 25)){
            
        }else{
            System.out.println("Erro");
        }
        if(serie2.adicionarAcoes(2, 15)){
            
        }else{
            System.out.println("Erro");
        }
        if(serie2.adicionarAcoes(3, 35)){
            
        }else{
            System.out.println("Erro");
        }
    }

    public ArrayList<SerieDados> serie(){
        ArrayList<SerieDados> teste=new ArrayList<>();
        teste.add(serie);
        teste.add(serie2);
        return teste;

    }
}
