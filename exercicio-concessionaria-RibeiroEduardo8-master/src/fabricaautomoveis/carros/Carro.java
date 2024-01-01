package fabricaautomoveis.carros;

public abstract class Carro {
    private String nome;
    private String cor;
    private double custo;
    private Categoria categoria;
    private Marca marca;
    private String placa;
    
    public Carro(String nome, Marca marca, String cor, double custo, Categoria categoria) {
        this.nome = nome;
        this.marca = marca;
        this.cor = cor;
        this.custo = custo;
        this.categoria = categoria;
    }
    
    public String obterNome() {
        return nome;
    }
    
    public double obterCusto() {
        return custo;
    }
    
    public Categoria obterCategoria() {
        return categoria;
    }
    
    public String obterCor() {
        return cor;
    }
    
    public String obterPlaca() {
        return placa;
    }
    
    @Override
    public String toString() {
        String descricao = marca + " " + nome + " " + cor;
        if (placa == null) {
            descricao += " sem placa";
        }
        else {
            descricao += " placa " + placa;
        }
        return  descricao + " (R$ " + custo + ")";
    }

    public void emplacar(String placa) {
        // simula o processo de emplacamento do carro
        System.out.println("\nEmplacando o carro: " + this + "...");
        esperar();
        this.placa = placa;
    }
    
    public void prepararParaEntrega() {
        // simula o processo de preparação do carro para entrega
        System.out.println("\nPreparando carro para entrega: " + this + "...");
        
        esperar();
    }
    
    public void liberarDocumentacao() {
        // simula o processo de liberar a documentação do carro
        System.out.println("\nLiberando documentação do carro: " + this + "...");
        
        esperar();
    }
    
    private void esperar() {
        try {
            Thread.sleep(1000);
        }
        catch(Exception e) {}
    }
}
