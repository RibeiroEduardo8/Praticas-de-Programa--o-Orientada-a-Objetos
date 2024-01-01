package fabricaautomoveis;

import fabricaautomoveis.carros.Argo;
import fabricaautomoveis.carros.Carro;
import fabricaautomoveis.carros.Categoria;
import fabricaautomoveis.carros.Strada;
import fabricaautomoveis.carros.Toro;

public class FiatFactory extends FactoryCar {
    public FiatFactory(){
        
    }

    @Override
    public Carro criarCarro(Categoria categoria, String cor) {
        Carro carro = null;

        if ( categoria == Categoria.POPULAR) {
            carro = new Argo(cor);
        }
        else if ( categoria == Categoria.PICKUP) {
            carro = new Strada(cor);
        }
        else if (categoria == Categoria.LUXO) {
            carro = new Toro(cor);
        }
        return carro;
    }
    
}
