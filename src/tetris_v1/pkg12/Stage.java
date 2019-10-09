
package tetris_v1.pkg12;


import elements.BackgroundElement;
import elements.FixBlock;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 *Represetação dos espaços e armazenamento das informações principais.
 *Posui a pontuação e os auxiliáres para realizar desenhos.
 * @author user
 */
public class Stage {
    private int stage;
    private int combo = 0;
    private int pontos = 0;
    private int larguraDesenho, alturaDesenho;
    private double speed = 1;
    private String tabuleiro[][];
    private BackgroundElement back;
    private FixBlock blocoFixo;
    private SuperficieDeDesenho superficie;
    private Position pos;
    private Imagem img;
    private SaveMe save;

    public Stage(SuperficieDeDesenho superficie, int stage) {
        this.save = new SaveMe();
        this.back = new BackgroundElement();
        this.superficie = superficie;
        this.stage = stage;
        this.blocoFixo = new FixBlock(new Position(0,0));
        calcularPosicao();
        tabuleiro = new String[Consts.NUM_COLUNAS][Consts.NUM_LINHAS];
        criaNovoFixo(stage);
        recomecaPosicoes(stage);
    }
    
    public void saveGame(){
        this.save.save(stage,pontos,speed,tabuleiro);
    }
    public void loadGame(){
        this.save.load();
        getResources();
    }
    public void getResources(){
        this.stage = save.getStage();
        this.pontos = save.getPontos();
        this.speed = save.getSpeed();
        this.tabuleiro = save.getTabuleiro();
    }
    
    public void criaNovoFixo(int stageNumber){
        this.stage = stageNumber;
        blocoFixo.getPos().X = Math.random()*Consts.NUM_COLUNAS*(stageNumber - 1);
        blocoFixo.getPos().Y = Consts.NUM_LINHAS/2 + Math.random()*(Consts.NUM_LINHAS/2);
    }
    
    /**
     * Percorre toda a matriz de string e setta seus valores como vazio, signifi
     * cando que não possuem mais nada.
     */
    public void recomecaPosicoes(int stageNumber){
        for (int y = 0; y < Consts.NUM_LINHAS; y++) {
            for (int x = 0; x < Consts.NUM_COLUNAS; x++) {
                tabuleiro[x][y] = "";
            }
        }
        criaNovoFixo(stageNumber);
        tabuleiro[blocoFixo.getPos().intX()][blocoFixo.getPos().intY()] = "X";
    }
    
    /**
     * Baseado na posição relativa, na posição da janela e nas proporções da janela,
     * faz os cálculos de onde devem estar os pedaços de cada peça.
     */
    public void calcularPosicao(){
        this.larguraDesenho = superficie.getWidth();
        this.alturaDesenho = superficie.getHeight();
        pos = new Position ( (  this.larguraDesenho - Consts.NUM_COLUNAS*Consts.TAM_LADO)/2,
                                (this.alturaDesenho - Consts.NUM_LINHAS*Consts.TAM_LADO) /2); 
    }
    
    /**
     * Método para remover linhas preenchidas e contabilizar pontos.
     * Percorre todas as linhas em busca de uma com NUM_COLUNAS elementos, ou seja,
     * linha cheia.
     * Se encontrar, aumenta o contador de combo e se chama recursivamente, possibi
     * litando remover várias linhas depois de uma mesma queda e contabilizando os combos.
     * Percorre as linhas acima e faz com que todas as posições com peça acima sejam
     * decrementadas.
     */
    public void limpaLinhas(){
        int y = Consts.NUM_LINHAS-1;
        int linhas = 0;
        while(y >= 0){
            int x = 0;
            while(x < Consts.NUM_COLUNAS && tabuleiro[x][y] != ""){
                x++;
            }
            if (x == Consts.NUM_COLUNAS){
                linhas++;
                for (int i = 0; i < Consts.NUM_COLUNAS; i++) {
                    tabuleiro[i][y] = "";
                }
                while (y >= 0){
                    for (int i = 0; i < Consts.NUM_COLUNAS; i++) {
                        tabuleiro[i][y] = y==0?"":tabuleiro[i][y-1];
                    }
                    y--;
                }
                limpaLinhas();
                combo++;
            }else{
                y--;
            }
        }
        tabuleiro[blocoFixo.getPos().intX()][blocoFixo.getPos().intY()] = "X";
    }

    /**
     * Auxiliar para contabilizar a quantidade de pontos feita baseado no valor
     * int combo.
     */
    public void somaCombo(){
        switch (combo){
            case 1:
                somaPontos(50);
                break;
            case 2:
                somaPontos(200);
                break;
            case 3:
                somaPontos(800);
                break;
            case 4:
                somaPontos(1600);
                break;
        }
        combo = 0;
    }
    
    /**
     * Faz a soma do valor da pontuação e chama o método estático do controlador
     * para que seja atualizado na tela incial.
     * Caso os pontos ultrapassem 1000 a velocidade também é passada como parâmetro
     * para acontecer a atualização na interface.
     * @param i 
     */
    private void somaPontos(int i){
        pontos += i;
        speed = 100;
        for (int j = 0; j < pontos/1000; j++) {
            speed *= 0.7;
            Controlador.setCounterMax(speed);
        }
    }
    
    /**
     * Auxiliar par exibição da velocidade atual de queda.
     * @return 
     */
    public int speedPorcentual(){
        speed = 100;
        for (int j = 0; j < pontos/1000; j++) {
            speed *= 1.3;
        }
        return (int)speed;
    }
    
    /**
     * Método que realiza os desenhos posição por posião das peças e do fundo
     * da tela de jogo com preto.
     * @param g 
     */
    public void desenhar(Graphics2D g){
        for (int y = 0; y < Consts.NUM_LINHAS; y++) {
            for (int x = 0; x < Consts.NUM_COLUNAS; x++) {
                Position aux =  new Position(x*Consts.TAM_LADO+pos.X , y*Consts.TAM_LADO+pos.Y);
                
                if(tabuleiro[x][y] == ""){
                    if( (x+y)%2 == 1){
                        g.setColor(back.getC_1());
                        g.fillRect( aux.intX(), aux.intY(), Consts.TAM_LADO, Consts.TAM_LADO);
                    }else{
                        g.setColor(back.getC_2());
                        g.fillRect( aux.intX(), aux.intY(), Consts.TAM_LADO, Consts.TAM_LADO);
                    }
                    
                }else{
                    img.desenhar(new Position(x,y), g, tabuleiro[x][y]);
                }
                
                if(false){
                    g.setFont(new Font("verdana",Font.PLAIN, 8));
                    g.drawString(x+","+y, (int)aux.X , (int)aux.Y+10);
                }
                
                
            }
        }
        
    }
    
    /**
     * Método para acessar posição específica da matriz de String.
     * Os parâmetros x e y são usados para acessar a posição e retornar a string
     * que representa o que há lá, podendo ser um vazio ou uma das peças possíveis 
     * @param x
     * @param y
     * @return 
     */
    public String obter(int x, int y){
        if ( y >=0 ){
            return tabuleiro[x][y];
        }
        return "";
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public int getCombo() {
        return combo;
    }

    public void setCombo(int combo) {
        this.combo = combo;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public int getLarguraDesenho() {
        return larguraDesenho;
    }

    public void setLarguraDesenho(int larguraDesenho) {
        this.larguraDesenho = larguraDesenho;
    }

    public int getAlturaDesenho() {
        return alturaDesenho;
    }

    public void setAlturaDesenho(int alturaDesenho) {
        this.alturaDesenho = alturaDesenho;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public String[][] getTabuleiro() {
        return tabuleiro;
    }

    public void setTabuleiro(String[][] tabuleiro) {
        this.tabuleiro = tabuleiro;
    }

    public FixBlock getBlocoFixo() {
        return blocoFixo;
    }

    public void setBlocoFixo(FixBlock blocoFixo) {
        this.blocoFixo = blocoFixo;
    }

    public SuperficieDeDesenho getSuperficie() {
        return superficie;
    }

    public void setSuperficie(SuperficieDeDesenho superficie) {
        this.superficie = superficie;
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public Imagem getImg() {
        return img;
    }

    public void setImg(Imagem img) {
        this.img = img;
    }
    
    
    
}
