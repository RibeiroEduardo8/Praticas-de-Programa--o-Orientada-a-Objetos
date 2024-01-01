public class Teste {
    public static void exibirDescricaoCompleta(Animal animal){
        System.out.println(animal.descricaoLonga());
    }

    public static void main(String[] args) throws Exception {
        
        Animal animal=new Leao("Ted", "Laranja");
        System.out.println(animal.getNome());
        animal=new Arara("Bob", "Bom");
        System.out.println(animal.getNome());

        Leao leo=new Leao("Ted", "Laranja");
        Ema ema=new Ema("Bob", "ruim");
        exibirDescricaoCompleta(leo);
        exibirDescricaoCompleta(ema);

    }
}
