package fabricaautomoveis;
import fabricaautomoveis.carros.Carro;
import fabricaautomoveis.carros.Categoria;


public abstract class FactoryCar {
    
    public abstract Carro criarCarro(Categoria categoria, String cor);
}
