public class Mamifero extends Animal {
    private String corDoPelo;


    public Mamifero(String especie, String nome, String somCaracteristico, int quantidadePatas, String corDoPelo) {
        super(especie, nome, somCaracteristico, quantidadePatas);
        this.corDoPelo = corDoPelo;
    }

    public String descricaoLonga(){
        return super.descricaoLonga() + " tem pelo "+corDoPelo;

    }

    public String getCorDoPelo() {
        return corDoPelo;
    }
}
