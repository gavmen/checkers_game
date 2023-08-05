package dama;

public class Dama {
    private static Tabuleiro tabuleiro = new Tabuleiro();
    private static boolean cor = true; // true => FFF, false => 000

    private static final String pecaFFF = "p";
    private static final String peca000 = "b";

    public static void main(String[] args) {
        Jogador jogador = new Jogador(cor);

        while (true) {
            System.out.println("Pontuacao do jogador " + pecaFFF + " = " + jogador.getPecasFFF());
            System.out.println("Pontuacao do jogador " + peca000 + " = " + jogador.getPecas000());
            tabuleiro.mostrarTabuleiro();
            jogador.selecionarPeca(getTabuleiro(), cor);
            if (jogador.getPecas000() < 1) {
                System.out.println("Vitoria do jogador " + pecaFFF + "! Parabens!");
                break;
            }
            if (jogador.getPecasFFF() < 1) {
                System.out.println("Vitoria do jogador " + peca000 + "! Parabens!");
                break;
            }
            cor = !cor;
        }
    }

    public static String[][] getTabuleiro() {
        return (tabuleiro.getTabuleiro());
    }
}
