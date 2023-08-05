package dama;

public class Tabuleiro {
    private String[][] matriz;
    private final String pecaFFF = "b";
    private final String rainhaFFF = "q";
    private final String peca000 = "p";
    private final String rainha000 = "Q";
    private final String vazio = " ";

    public Tabuleiro() {
        matriz = new String[8][8];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    matriz[i][j] = vazio;
                } else {
                    if (i < 3) {
                        matriz[i][j] = peca000;
                    } else if (i > 4) {
                        matriz[i][j] = pecaFFF;
                    } else {
                        matriz[i][j] = vazio;
                    }
                }
            }
        }
    }

    public void mostrarTabuleiro() {
        System.out.println("  0 1 2 3 4 5 6 7");
        for (int i = 0; i < 8; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 8; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    public String[][] getTabuleiro() {
        return this.matriz;
    }
}
