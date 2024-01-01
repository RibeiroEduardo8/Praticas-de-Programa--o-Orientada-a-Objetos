package br.ufla.gac106.s2022_2.Bug_killers.Model;

public class Apresentador extends Celebridade{
    private String programa;
    private String emissora;
   

    public Apresentador(String nome, String email, int idade, String sexo, String programa, String emissora) {
        super(nome, email, idade, sexo);
        this.programa = programa;
        this.emissora = emissora;
       
    }

    public String getEmissora() {
        return emissora;
    }

    public String getPrograma() {
        return programa;
    }

    @Override
    public String DescricaoCompleta(){
        return super.DescricaoCompleta()+ "\n" + "Emissora: "+ emissora + "\n" + "Programa apresentado: " + programa ;

    }


    
}
