import Empresas.Controle;
import series.SerieDados;
import series.VisualizadorSeries;

import java.util.ArrayList;
import java.util.List;

public class Principal {
    public void executar() {
        // ... implemente aqui seu código ...
        Controle c = new Controle();
        c.adiciona();
        List <SerieDados> teste= new ArrayList<>();
        teste=c.serie();
        for (SerieDados e : teste) {
            for(int i=1; i<=3;i++){
                if(e.obterIdentificacaoSerie().equals("Petrobras")){

                    System.out.println( "Ações da Petrobras no dia: "+i+ " Valor das ações: "+e.obterDado(i));
                }
                else if(e.obterIdentificacaoSerie().equals("Vale")){
                    System.out.println( "Ações da vale no dia: "+i+ " Valor das ações: "+e.obterDado(i));
                }
            }
        }
        VisualizadorSeries v= new VisualizadorSeries(teste);
        v.exibir();

    }
}
