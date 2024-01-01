import java.util.ArrayList;

public class Zoologico {
    private ArrayList<Animal> animal;


    public Zoologico() {
        animal=new ArrayList<Animal>();
        
    }
    public void adicionarGorila(String nome, String corDoPelo){
        Gorila teste=new Gorila(nome,corDoPelo);
        animal.add(teste);

    }

    public String getDescricaoLonga(String nome){
        String descricao=null;

        for (Animal ani : animal) {

            if(ani.getNome().equals(nome)){
                descricao= ani.descricaoLonga();
            }
        }

        return descricao;

    }
    public String getDescricaoCurta(String nome){
        String descricao=null;
        for(Animal ani : animal){

            if(ani.getNome().equals(nome)){
                descricao=ani.DescricaoSimples();
            }
        }
       

        return descricao;

    }

    public void getDescricaoZoologico(){

        for(Animal ani : animal){

            System.out.println(ani.descricaoLonga());
        
        }
        
    }


    public void adicionarEma(String nome, String corDoPelo){
        Ema teste=new Ema(nome,corDoPelo);
        animal.add(teste);

    }

    public void adicionarArara(String nome, String corDoPelo){
        Arara teste=new Arara(nome,corDoPelo);
        animal.add(teste);

    }

    public void adicionarLeao(String nome, String corDoPelo){
        Leao teste=new Leao(nome,corDoPelo);
        animal.add(teste);

    }
}
