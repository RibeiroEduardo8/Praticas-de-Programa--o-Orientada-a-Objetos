package concessionaria;

import fabricaautomoveis.carros.Carro;
import fabricaautomoveis.carros.Categoria;
import fabricaautomoveis.carros.Marca;
import fabricaautomoveis.ChevroletFactory;
import fabricaautomoveis.FactoryCar;
import fabricaautomoveis.FiatFactory;
import fabricaautomoveis.VWFactory;
import java.util.ArrayList;
import java.util.List;

import detran.GeradorDePlaca;

/**
 * Representa uma concessionária que vende carros de uma determinada Marca.
 */
public class Concessionaria {
    // Nome da concessionária
    private String nome;
    // Carros da concessionária
    private List<Carro> carros;
    // Marca da qual a concessionária vende os carros
    private Marca marcaFranquia;
    private FactoryCar factoryCar;
    
    /**
     * Uma concessionária é construída com um nome e uma Marca da qual vende carros
     * @param nome O nome da concessionária.
     * @param marca A marca da qual a concessionária vende os carros
     */
    public Concessionaria(String nome, Marca marca) {
        this.nome = nome;        
        this.marcaFranquia = marca;
        carros = new ArrayList<>(); 
        criarMarca();         
    }
    
    /**
     * Retorna o nome da concessionária
     * 
     * @return O nome da concessionária
     */
    public String getNome() {
        return nome;
    }
    
    /**
     * Retorna a Marca da qual a concessionária vende os carros.
     * 
     * @return A marca dos carros
     */
    public Marca getMarcaFranquia() {
        return marcaFranquia;
    }
    
    /**
     * Realiza a compra de um carro de uma determinada categoria e com uma cor
     * @param categoria Categoria do carro a ser comprado.
     * @param cor Cor do carro a ser comprado
     * 
     * @return Verdadeiro se o carro pode ser comprado (modelo disponível)
     */
    public boolean comprarCarro(Categoria categoria, String cor) {
        Carro carro = factoryCar.criarCarro(categoria, cor);
        
        if (carro != null) {
            carro.emplacar(GeradorDePlaca.gerarPlaca());
            carro.prepararParaEntrega();
            carro.liberarDocumentacao();
            carros.add(carro);
            return true;
        }
        else {
            System.out.println("Não há modelos disponíveis para essa categoria");
            return false;
        }
    }
    private void criarMarca(){
        
        if(marcaFranquia==Marca.FIAT){
            
            factoryCar=new FiatFactory();

        }else if(marcaFranquia==Marca.VW){

            factoryCar = new VWFactory();
        
        }else if(marcaFranquia==Marca.CHEVROLET){
        
            factoryCar=new ChevroletFactory();
        
        }else{
        
            System.out.println( "Não existe essa marca");
        
        }
    }
    public String verificaMarca(String marca){
        String mensagem="nao existe";
        for (Marca marcas : Marca.values()) {
            if(marca.equals(marcas.toString())){
                mensagem="existe";
            }
            
        }
        return mensagem;
    }
    public void trocarMarca(String marca){
        if(marca.equals("CHEVROLET")){
            marcaFranquia= Marca.CHEVROLET;
        }else if(marca.equals("FIAT")){
            marcaFranquia= Marca.FIAT;
        }else if(marca.equals("VW")){
            marcaFranquia= Marca.VW;
        }
        criarMarca();
    }
}
