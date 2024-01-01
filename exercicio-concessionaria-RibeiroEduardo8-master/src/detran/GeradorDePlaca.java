package detran;

import java.util.Random;

public class GeradorDePlaca {
    private static Random geradorAleatorio;

    public static String gerarPlaca() {
        if (geradorAleatorio == null) {
            geradorAleatorio = new Random();
        }

        // As placas no padrão MERCOSUL têm formato LLLNLNN
        // onde L é letra e N é número
        return letraAleatoria() + letraAleatoria() + letraAleatoria() +
               algarimosAleatorio() +
               letraAleatoria() +
               algarimosAleatorio() + algarimosAleatorio();
    }

    private static String letraAleatoria() {
        char letra = (char) ('A' + geradorAleatorio.nextInt(26));
        return Character.toString(letra);
    }

    private static int algarimosAleatorio() {
        return geradorAleatorio.nextInt(10);
    }
}
