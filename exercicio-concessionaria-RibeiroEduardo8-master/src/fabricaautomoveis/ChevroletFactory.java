package fabricaautomoveis;

import fabricaautomoveis.carros.Carro;
import fabricaautomoveis.carros.Categoria;
import fabricaautomoveis.carros.Cruze;

public class ChevroletFactory extends FactoryCar {
    public ChevroletFactory(){

    }

    @Override
    public Carro criarCarro(Categoria categoria, String cor) {
        Carro carro = null;

        if ( categoria == Categoria.POPULAR) {
            carro = new Cruze(cor);
        }
        return carro;
    }
    
}
