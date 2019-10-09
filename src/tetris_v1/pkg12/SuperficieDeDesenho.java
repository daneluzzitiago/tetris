package tetris_v1.pkg12;

import elements.Peca;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Superfície para realizar os desenhos das peças e demais utilidades da interface.
 * Divide a tela em um diagrama de posições X e Y e utiliza a posição relativa
 * dos diagramas mais o tamanho pré definido para localizar o ponto exato onde
 * deve acontecer uma pintura para que ela represente a peça.
 * Possui o KeyListener
 * @author user
 */
public class SuperficieDeDesenho extends Canvas{

    private BufferStrategy buffer;
    private Graphics g;
    private Imagem imagem;
    private Stage stage;
    private Peca peca;
    
    public SuperficieDeDesenho(int tamX, int tamY) {
        setSize(tamX,tamY);
        stage = new Stage(this,Consts.STANDARD_STAGE);
        imagem = new Imagem(this);
        stage.setImg(imagem);
        peca = new Peca(this);
        iniciarTeclado();
        setFocusable(true);
        
    }

    public BufferStrategy getBuffer() {
        return buffer;
    }

    public void setBuffer(BufferStrategy buffer) {
        this.buffer = buffer;
    }

    public Graphics getG() {
        return g;
    }

    public void setG(Graphics g) {
        this.g = g;
    }

    public Imagem getImagem() {
        return imagem;
    }

    public void setImagem(Imagem imagem) {
        this.imagem = imagem;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public Peca getPeca() {
        return peca;
    }

    public void setPeca(Peca peca) {
        this.peca = peca;
    }
    
    
    
    /**
     * Realiza os desenhos da cor de fundo, do tabuleiro, das peças e das
     * figuras de interface.
     */
    public void desenhar(){
        buffer = getBufferStrategy();
        if (buffer == null){
            createBufferStrategy(2);
            return;
        }
        g = buffer.getDrawGraphics();
            

        //Desenhar aqui
        
        BufferedImage image;
        try{
            image = ImageIO.read(new File("src/imgs/background.png"));
            g.drawImage(image,0,0,this);
        } catch (IOException e){
            
        }
        
        stage.desenhar((Graphics2D)g);
        try {                
            image = ImageIO.read(new File("src/imgs/nextpanel.png"));
            g.drawImage(image,500, 34, this);
            
            image = ImageIO.read(new File("src/imgs/logo.png"));
            g.drawImage(image,(Consts.TAM_LADO_JANELA/2)-67, 0, this);
            
            image = ImageIO.read(new File("src/imgs/next.png"));
            g.drawImage(image,(Consts.TAM_LADO_JANELA)-190, 0, this);
            
            image = ImageIO.read(new File("src/imgs/score.png"));
            g.drawImage(image,30, 0, this);
            
            image = ImageIO.read(new File("src/imgs/speed.png"));
            g.drawImage(image,30, 100, this);
            
            image = ImageIO.read(new File("src/imgs/stage.png"));
            g.drawImage(image,30, 200, this);
            
            image = ImageIO.read(new File("src/imgs/instructions.png"));
            g.drawImage(image,30, 300, this);
            
         } catch (IOException ex) {
         }
        peca.desenhar(g); //Chama método de desenho da peca
        g.setColor(Color.BLACK);    //Fundo do mapa de jogo
        g.drawString(""+stage.getPontos(), 100, 60);
        g.drawString(""+stage.speedPorcentual()+"%", 100, 160);
        g.drawString(""+stage.getStage(), 100, 260);

        
        
      
        
        g.dispose();
        buffer.show();
    }

        /**
         * Leitor de eventos de pressionar o teclado.
         * Permite interação entre jogador e código através do keyListener.
         */
    public void iniciarTeclado(){
        addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_DOWN){
                                                    peca.moverBaixo();return;} 
                    else if(e.getKeyCode()==KeyEvent.VK_LEFT)   {
                    peca.moverEsquerda(); return;
                }   else if(e.getKeyCode()==KeyEvent.VK_RIGHT)  { 
                    peca.moverDireita();  return;
                }   else if(e.getKeyCode()==KeyEvent.VK_SPACE)  { 
                    peca.girar();         return;
                }   else if(e.getKeyCode()==KeyEvent.VK_1)      {
                    stage.recomecaPosicoes(1);
                    Controlador.startNewGame(1); return;
                }   else if(e.getKeyCode()==KeyEvent.VK_2)      { 
                    stage.recomecaPosicoes(2);
                    Controlador.startNewGame(2);return;
                }   else if(e.getKeyCode()==KeyEvent.VK_S)      {
                    stage.saveGame();
                }   else if(e.getKeyCode()==KeyEvent.VK_L)      {
                    stage.loadGame();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //batata
            }
            
        });
        
    
    }
    
}
