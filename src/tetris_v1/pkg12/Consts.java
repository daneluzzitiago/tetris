package tetris_v1.pkg12;

import java.awt.Color;

/**
 *  Classe com parâmetros do jogo.
 * Possui os principais parâmetros, possibilitando personalização sem precisar
 * encontrar trechos de códigos que fazem uso.
 * @author user
 */
public class Consts {
    //Tamanho lateral da janela
    public static final int TAM_LADO_JANELA = 700;           
    //Altura da janela
    public static final int TAM_ALTURA_JANELA = 620 ;
    //Valor máximo que o contador deve chegar para fazer a peça descer
    public static final int TAXA_QUEDA = 100 ;
    //Tamanho lateral de cada pedacinho de peça
    public static final int TAM_LADO = 30 ;
    //Quantidade de linhas do jogo
    public static final int NUM_LINHAS = 18 ;
    //Quantidade de colunas do jogo
    public static final int NUM_COLUNAS = 10 ;
    //Stage inicial do jogo
    public static final int STANDARD_STAGE = 1;
    //Cor de fundo alternada
    public static final Color BG_COLOR_1 = new Color(12, 16, 65);
    //Cor de fundo alternada
    public static final Color BG_COLOR_2 = new Color(0, 12, 14);
}
