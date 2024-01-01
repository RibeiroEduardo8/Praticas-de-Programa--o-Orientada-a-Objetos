package corredores;

public class Corredor implements Competidor{

    private String nome;
    private int velocidade;
    private int distanciaPercorrida;
    private boolean estaCorrendo;
    private int distanciaDaCorrida;
    
    public Corredor(String nome, int velocidade) {
        this.nome = nome;
        this.velocidade = velocidade;
        distanciaPercorrida=0;
        estaCorrendo=false;
    }
    @Override
    public void run() {
        try {
            
            while(distanciaPercorrida<distanciaDaCorrida){
            estaCorrendo=true;
            Thread.sleep(1000/velocidade);
            distanciaPercorrida++;
            }
            estaCorrendo=false;

        } catch (InterruptedException e) {

            e.printStackTrace();
        }
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public int getVelocidade() {

        return velocidade;
    }

    @Override
    public int getDistanciaPercorrida() {
        
        return distanciaPercorrida;
    }

    @Override
    public boolean estaCorrendo() {
        return estaCorrendo;
    }

    @Override
    public void prepararParaNovaCorrida(int distanciaDaCorrida) {
       if(distanciaDaCorrida>0){
        this.distanciaDaCorrida=distanciaDaCorrida;
        distanciaPercorrida=0;
       }
    }
    
}
