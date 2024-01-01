package Empresas;
import series.SerieDados;
import java.util.HashMap;

public class SerieDadosAcoes implements SerieDados{
    private int inicio;
    private int fim;
    private  String identificacao;
    private HashMap<Integer,Integer> series;

    public SerieDadosAcoes(int inicio, int fim, String identificacao) {
        this.inicio = inicio;
        this.fim = fim;
        this.identificacao = identificacao;
        series= new HashMap<>();
    }
    @Override
    public String obterIdentificacaoSerie(){
        return identificacao;

    }
    @Override
    public int obterDiaInicial(){
        return inicio;
    }
    
    @Override
    public int obterDiaFinal(){
        return fim;
    }
    
    @Override
    public int obterDado(int dia){
        return series.get(dia);
    }
    public boolean adicionarAcoes(int dia , int dado){
        if(dia>=inicio && dia<=fim){
            series.put(dia,dado);
            return true;
        }else{
            return false;
        }
    }
}