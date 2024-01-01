public class Ave extends Animal{
    private String tipoDeVoo;


    public Ave(String especie, String nome, String somCaracteristico, int quantidadePatas, String tipoDeVoo) {
        super(especie, nome, somCaracteristico, quantidadePatas);
        this.tipoDeVoo = tipoDeVoo;
    }


    public String descricaoLonga(){
        return super.descricaoLonga()+" e tem um voo "+tipoDeVoo ;

    }

    public String getTipoDeVoo() {
        return tipoDeVoo;
    }

}
