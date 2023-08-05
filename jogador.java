package dama;
import java.util.Scanner;

public class Jogador {
    boolean cor;
    private final String pecaFFF = "b";
    private final String rainhaFFF = "q";
    private final String peca000 = "p";
    private final String rainha000 = "Q";
    private final String vazio = " ";
    
    private int pecas000 = 12;
    private int pecasFFF = 12;
    
    Scanner sc = new Scanner(System.in);
    
    public Jogador(boolean cor) {
        this.cor = cor;
    }
    
    public int getPecas000() {
        return this.pecas000;
    }
    
    public int getPecasFFF() {
        return this.pecasFFF;
    }
    
    public void selecionarPeca(String[][] matriz, boolean cor) {
        if (cor) {
            System.out.println("Vez do jogador " + pecaFFF);
        } else {
            System.out.println("Vez do jogador " + peca000);
        }
        int linha = -1;
        int coluna = -1;
        String colunaFake;
        
        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                System.out.print("Insira a coluna da peca que voce quer selecionar: ");
                colunaFake = sc.nextLine();
                coluna = converter(colunaFake);
            } else {
                System.out.print("Insira a linha da peca que voce quer selecionar: ");
                linha = sc.nextInt(); sc.nextLine();
            }
        }
        
        if (validarEscolha(linha, coluna, matriz, cor)) {
            selecionarAlvo(linha, coluna, matriz, cor);
        } else {
            selecionarPeca(matriz, cor); // Tente denovo
        }
    }

    private void capturarInimigo(int linha, int coluna, String[][] matriz) {
        matriz[linha][coluna] = vazio;
    }
    
    private void selecionarAlvo(int linhaPeca, int colunaPeca, String[][] matriz, boolean cor) {
        int linha = -1;
        int coluna = -1;
        String colunaFake;
        boolean mexeu = false;
        
        for (int i = 0; i < 2; i++) {
            if (i == 0) {
                System.out.print("Insira a coluna da posicao que voce quer mexer: ");
                colunaFake = sc.nextLine();
                coluna = converter(colunaFake);
            } else {
                System.out.print("Insira a linha da posicao que voce quer mexer: ");
                linha = sc.nextInt(); sc.nextLine();
            }
        }
        
        if (validarAlvo(linha, coluna, matriz, cor)) {



            if (cor) {

                if (matriz[linhaPeca][colunaPeca].equals(rainhaFFF)) {
                
                    int difLinha = linha - linhaPeca;
                    int difColuna = coluna - colunaPeca;

                    if (Math.abs(difLinha) == Math.abs(difColuna)) {
                        
                        int direcaoLinha = difLinha > 0 ? 1 : -1;
                        int direcaoColuna = difColuna > 0 ? 1 : -1;

                        int proxLinha = linhaPeca + direcaoLinha;
                        int proxColuna = colunaPeca + direcaoColuna;

                        boolean inimigoEncontrado = false;

                        while (proxLinha != linha && proxColuna != coluna) {

                            if (verificarInimigo(proxLinha, proxColuna, matriz, cor)) {
                                inimigoEncontrado = true;
                                capturarInimigo(proxLinha, proxColuna, matriz);
                                pecasFFF -= 1;
                            };

                            proxLinha += direcaoLinha;
                            proxColuna += direcaoColuna;
                        }

                        matriz[linhaPeca][colunaPeca] = vazio;
                        matriz[linha][coluna] = rainhaFFF;
                        mexeu = true;

                    }
                } else if (verificarInimigo(posSuperior(linhaPeca), posEsquerda(colunaPeca), matriz, cor)) {
                    if (linha == posSuperior(posSuperior(linhaPeca)) && coluna == posEsquerda(posEsquerda(colunaPeca))) {
                        matriz[linhaPeca][colunaPeca] = vazio;
                        matriz[posSuperior(linhaPeca)][posEsquerda(colunaPeca)] = vazio;
                        matriz[linha][coluna] = pecaFFF;
                        pecas000 -= 1;
                        mexeu = true;
                    }
                } else {
                    if (linha == posSuperior(linhaPeca) && coluna == posEsquerda(colunaPeca)) {
                        matriz[linhaPeca][colunaPeca] = vazio;
                        matriz[linha][coluna] = pecaFFF;
                        mexeu = true;
                    }
                }
                if (verificarInimigo(posSuperior(linhaPeca), posDireita(colunaPeca), matriz, cor)) {
                    if (linha == posSuperior(posSuperior(linhaPeca)) && coluna == posDireita(posDireita(colunaPeca))) {
                        matriz[linhaPeca][colunaPeca] = vazio;
                        matriz[posSuperior(linhaPeca)][posDireita(colunaPeca)] = vazio;
                        matriz[linha][coluna] = pecaFFF;
                        pecas000 -= 1;
                        mexeu = true;
                    }
                } else {
                    if (linha == posSuperior(linhaPeca) && coluna == posDireita(colunaPeca)) {
                        matriz[linhaPeca][colunaPeca] = vazio;
                        matriz[linha][coluna] = pecaFFF;
                        mexeu = true;
                    }
                }
            } else {

                if (matriz[linhaPeca][colunaPeca].equals(rainha000)) {
                
                    int difLinha = linha - linhaPeca;
                    int difColuna = coluna - colunaPeca;

                    if (Math.abs(difLinha) == Math.abs(difColuna)) {
                        
                        int direcaoLinha = difLinha > 0 ? 1 : -1;
                        int direcaoColuna = difColuna > 0 ? 1 : -1;

                        int proxLinha = linhaPeca + direcaoLinha;
                        int proxColuna = colunaPeca + direcaoColuna;

                        boolean inimigoEncontrado = false;

                        while (proxLinha != linha && proxColuna != coluna) {

                            if (verificarInimigo(proxLinha, proxColuna, matriz, cor)) {
                                inimigoEncontrado = true;
                                capturarInimigo(proxLinha, proxColuna, matriz);
                                pecas000 -= 1;
                            };

                            proxLinha += direcaoLinha;
                            proxColuna += direcaoColuna;
                        }

                        matriz[linhaPeca][colunaPeca] = vazio;
                        matriz[linha][coluna] = rainha000;
                        mexeu = true;

                    }
                } else if (verificarInimigo(posInferior(linhaPeca), posEsquerda(colunaPeca), matriz, cor)) {
                    if (linha == posInferior(posInferior(linhaPeca)) && coluna == posEsquerda(posEsquerda(colunaPeca))) {
                        matriz[linhaPeca][colunaPeca] = vazio;
                        matriz[posInferior(linhaPeca)][posEsquerda(colunaPeca)] = vazio; //mata
                        matriz[linha][coluna] = peca000;
                        pecasFFF -= 1;
                        mexeu = true;
                    }
                } else {
                    if (linha == posInferior(linhaPeca) && coluna == posEsquerda(colunaPeca)) {
                        matriz[linhaPeca][colunaPeca] = vazio;
                        matriz[linha][coluna] = peca000;
                        mexeu = true;
                    }
                }
                if (verificarInimigo(posInferior(linhaPeca), posDireita(colunaPeca), matriz, cor)) {
                    if (linha == posInferior(posInferior(linhaPeca)) && coluna == posDireita(posDireita(colunaPeca))) {
                        matriz[linhaPeca][colunaPeca] = vazio;
                        matriz[posInferior(linhaPeca)][posDireita(colunaPeca)] = vazio; //mata
                        matriz[linha][coluna] = peca000;
                        pecasFFF -= 1;
                        mexeu = true;
                    }
                } else {
                    if (linha == posInferior(linhaPeca) && coluna == posDireita(colunaPeca)) {
                        matriz[linhaPeca][colunaPeca] = vazio;
                        matriz[linha][coluna] = peca000;
                        mexeu = true;
                    }
                }
            }

            if (!mexeu) {
                System.out.println("Por favor, faca um movimento valido de acordo com as regras do jogo de damas!");
                selecionarAlvo(linhaPeca, colunaPeca, matriz, cor);
            }
            // Cria rainhas
            if (matriz[linha][coluna].equals(pecaFFF) && linha == 0) {
                matriz[linha][coluna] = rainhaFFF; 
            }
            
            if (matriz[linha][coluna].equals(peca000) && linha == 7) {
                matriz[linha][coluna] = rainha000; 
            }
        } else {
            selecionarAlvo(linhaPeca, colunaPeca, matriz, cor);
        }
    }
    
    private boolean validarEscolha(int linha, int coluna, String[][] matriz, boolean cor) {
        if (!verificarCasaExiste(linha, coluna)) {
            System.out.println("Valor invalido! Por favor, insira uma casa presente no tabuleiro (a-h)(0-7).");
            return false;
        }
        if (verificarCasaVazia(linha, coluna, matriz)) {
            System.out.println("Valor invalido! Por favor, escolha uma peca, e nao um espaco vazio.");
            return false; 
        }
        if (verificarInimigo(linha, coluna, matriz, cor)) {
            System.out.println("Valor invalido! Por favor, Mexa uma peca da sua equipe.");
            return false;
        }
        return true;
    }
    
    private boolean validarAlvo(int linha, int coluna, String[][] matriz, boolean cor) {
        if (!verificarCasaExiste(linha, coluna)) {
            System.out.println("Valor invalido! Por favor, insira uma casa presente no tabuleiro (a-h)(0-7).");
            return false;
        }
        if (!verificarCasaVazia(linha, coluna, matriz)) {
            System.out.println("Valor invalido! Por favor, nao sobreponha pecas.");
            return false; 
        }
        return true;
    }
    
    private boolean verificarInimigo(int linha, int coluna, String[][] matriz, boolean cor) {
        if (verificarCasaExiste(linha, coluna)) {
            if (cor) {
                if (matriz[linha][coluna].equals(peca000) || matriz[linha][coluna].equals(rainha000)) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if (matriz[linha][coluna].equals(pecaFFF) || matriz[linha][coluna].equals(rainhaFFF)) {
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }
    
    private boolean verificarCasaVazia(int linha, int coluna, String[][] matriz) {
        if (matriz[linha][coluna].equals(vazio)) {
            return true;
        } else {
            return false;
        }
    }
    
    private boolean verificarCasaExiste(int linha, int coluna) {
        if ((linha > -1) && (coluna > -1) && (linha < 8) && (coluna < 8)) {
            return true;
        } else { return false; }
    }
    
    private int posSuperior(int linha) {
        return linha - 1;
    }
    
    private int posInferior(int linha) {
        return linha + 1;
    }
    
    private int posEsquerda(int coluna) {
        return coluna - 1;
    }
    
    private int posDireita(int coluna) {
        return coluna + 1;
    }
    
    private int converter(String col) {
        int valor = col.charAt(0) - 'a';
        return valor;
    }
}
