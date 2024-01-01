package fabricaautomoveis;

import fabricaautomoveis.carros.Carro;
import fabricaautomoveis.carros.Categoria;
import fabricaautomoveis.carros.Gol;
import fabricaautomoveis.carros.Saveiro;

public class VWFactory extends FactoryCar {

    @Override
    public Carro criarCarro(Categoria categoria, String cor) {
        
        Carro carro = null;
        
        if (categoria == Categoria.POPULAR) {
            carro = new Gol(cor);
        }
        else if(categoria == Categoria.PICKUP) {
            carro = new Saveiro(cor);
        }
        return carro;
    }
    

}
