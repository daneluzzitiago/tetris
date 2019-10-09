package tetris_v1.pkg12;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Definição das características visuais de cada peça.
 * Definição de cor.
 * Calculos para posicionar no lugar certo da tela.
 * @author user
 */
public class Imagem {
    
    SuperficieDeDesenho superficie;
    Position posicaoTabuleiro;
    int lado;
    Color c;
    
    public Imagem(SuperficieDeDesenho superficie) {
        this.superficie = superficie;
        posicaoTabuleiro = superficie.getStage().getPos();
        lado = Consts.TAM_LADO;
    }
    
    public Position calcularPosicao(Position pos){
        int x = (int) (pos.X*lado + posicaoTabuleiro.X);
        int y = (int) (pos.Y*lado + posicaoTabuleiro.Y);
        return new Position (x,y);
    }

    
    public void desenhar(Position pos, Graphics g, String peca){
        switch (peca){
            case"I":
                g.setColor(Color.CYAN);
                break;
            case"J":g.setColor(Color.BLUE);
                break;
            case"L":g.setColor(Color.ORANGE);
                break;
            case"O":g.setColor(Color.YELLOW);
                break;
            case"S":g.setColor(Color.GREEN);
                break;
            case"Z":g.setColor(Color.RED);
                break;
            case"T":g.setColor(Color.MAGENTA);
                break;
                case"X":
                    g.setColor(Color.WHITE);
                    break;
        }
        g.fillRect( (int)calcularPosicao(pos).intX(), (int)calcularPosicao(pos).intY(), lado,lado );
    }

}
