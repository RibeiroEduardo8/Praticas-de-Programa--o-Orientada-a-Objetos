public class Animal {
    private String especie,nome,somCaracteristico;  
    private int quantidadePatas;


    public Animal(String especie, String nome, String somCaracteristico, int quantidadePatas) {
        this.especie = especie;
        this.nome = nome;
        this.somCaracteristico = somCaracteristico;
        this.quantidadePatas = quantidadePatas;
    }
    public String getSomCaracteristico() {
        return somCaracteristico;
    }


    public String getNome() {
        return nome;
    }


    public String getEspecie() {
        return especie;
    }


    public int getQuantidadePatas() {
        return quantidadePatas;
    }


    public String DescricaoSimples(){
        return (nome+ " é um "+ especie);
    }

    public String descricaoLonga(){
        return nome+ " é um(a) "+especie+" que faz "+ somCaracteristico+" tem "+ quantidadePatas+ " patas";

    }
}
