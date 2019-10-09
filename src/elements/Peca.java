package elements;
import java.awt.Graphics;
import tetris_v1.pkg12.Consts;
import tetris_v1.pkg12.Controlador;
import tetris_v1.pkg12.Imagem;
import tetris_v1.pkg12.Pedacos;
import tetris_v1.pkg12.Position;
import tetris_v1.pkg12.Stage;
import tetris_v1.pkg12.SuperficieDeDesenho;

/**
 * Classe responsável pela dinâmica das peças.
 * 
 * @author user
 */
public class Peca extends Element{
    SuperficieDeDesenho superficie;
    Stage tabuleiro;
    Imagem imagem;
    Pedacos atual = Pedacos.random();
    Pedacos proximos[] = new Pedacos[3]; //Quantos próximos são exibidos

    /**
     * Coloca a primeira peça na posição correta e cria um vetor de 3 posições
     * onde armazena as três próximas peças.
     * @param superficie 
     */
    public Peca(SuperficieDeDesenho superficie) {
        super(new Position(Consts.NUM_COLUNAS/2,1));
        this.superficie = superficie;
        this.tabuleiro = superficie.getStage();
        this.imagem = superficie.getImagem();
        for (int i = 0; i < proximos.length; i++) {
            proximos[i] = Pedacos.random();
        }
    }
    
    public void flush(){
        atual = Pedacos.random();
        for (int i = 0; i < proximos.length; i++) {
            proximos[i] = Pedacos.random();
        }
        pos = new Position(Consts.NUM_COLUNAS/2,1);
    }
    
    /**
     * Método para desenhar os pedaços da peça baseado em sua posição e em sua
     * posição relativa.
     * @param g 
     */
    public void desenhar(Graphics g){
        for (int i = 0; i < 4; i++) {
            double auxX = pos.getX() + atual.getPosPedacos()[i].getX();
            double auxY = pos.getY() + atual.getPosPedacos()[i].getY();
            imagem.desenhar(new Position(auxX,auxY), g, atual.getNome()); //desenha os 4 pedacos da peca utilizando as posicoes relativas
        }
        //Onde serão printadas as próximas peças
        for (int i = 0; i < proximos.length; i++) {
            Position proximaPeca = new Position(12,1+i*3);
            for (int j = 0; j < proximos[i].getPosPedacos().length; j++) {
                double xReal = proximos[i].getPosPedacos()[j].getX() + proximaPeca.getX();
                double yReal = proximos[i].getPosPedacos()[j].getY() + proximaPeca.getY();
                imagem.desenhar(new Position(xReal, yReal), g, proximos[i].getNome());
            }
        }
        
    }
    
    /**
     * Coloca todos os pedaços da peça dentro do vetor do tabuleiro que representa
     * as posições, fazendo com que se tornem parte do cenário.
     */
    public void armazenarPeca(){
        for (int i = 0; i < atual.getPosPedacos().length; i++) {
            int col = (int)atual.getPosPedacos()[i].getX() + (int)pos.getX() ;
            int lin = (int)atual.getPosPedacos()[i].getY() + (int)pos.getY() ;
            String dado = atual.getNome();
            tabuleiro.getTabuleiro()[col][lin] = dado;
        }
    }
    /**
     * Movimentos da peça.
     * A realização é condicional.
     * Caso o movimento seja inválido é feito um movimento contrário fazendo com
     * que a peça volta para a posição que estava.
     * Vale tanto para os movimentos quando para a rotação.
     */
    public void moverDireita(){
        pos.moverDireita();
        if(movimentoInvalido()){
            pos.moverEsquerda();
        }
    }
    public void moverEsquerda(){
        pos.moverEsquerda();
        if(movimentoInvalido()){
            pos.moverDireita();
        }
    }
    public void moverBaixo(){
        pos.moverBaixo();
        if(movimentoInvalido()){
            pos.moverCima();
            armazenarPeca();
            reiniciar();
            //Operações para quando há toque no "chão".
            tabuleiro.limpaLinhas();
            tabuleiro.somaCombo();
        }
    }
    public void girar(){
        atual.girar();
        if(movimentoInvalido()){
            atual.girarInverso();
        }
    }
    
    
    /**
     * Cria nova peça e armazena a antiga.
     * Uma peça deve ser depositada quando toca o chão ou quando toca alguma 
     * outra peça.
     * Quando isso acontece, a peça é armazenada e uma nova é colocada exatamente
     * na mesma posição das demais.
     */
    public void reiniciar(){
        armazenarPeca();
        pos = new Position(Consts.NUM_COLUNAS/2,1);
        atual = proximos[0];
        for (int i = 0; i < proximos.length-1; i++) {
            proximos[i] = proximos[i+1];
        }
        proximos[proximos.length-1] = Pedacos.random();
        if(movimentoInvalido()){
            superficie.getStage().recomecaPosicoes(this.tabuleiro.getStage());
            Controlador.startNewGame(tabuleiro.getStage());
        }
    }
    
    /**
     * Garante a validade do movimento requisitado.
     * A peça inicialmente é movida e depois testada.
     * Todas as posições da peça são acessadas e testa-se se alguma delas ultra-
     * passo os limites laterais do jogo, ou se ouve toque com outras peças 
     * (comumente acontece com rotações).
     * @return 
     */
    public boolean movimentoInvalido(){
        for (int i = 0; i < atual.getPosPedacos().length; i++) {
            double xReal = atual.getPosPedacos()[i].getX() + pos.getX();
            double yReal = atual.getPosPedacos()[i].getY() + pos.getY();
            if(xReal > Consts.NUM_COLUNAS - 1 || xReal < 0){
                return true;
            }
            if(yReal > Consts.NUM_LINHAS - 1 ){  //Não há necessidade de verificar acima pois a peça só desce
                return true;
            }
            if (tabuleiro.obter( (int) xReal , (int) yReal) != ""){
                return true;
            }
        }
        return false;
    }
    
}
