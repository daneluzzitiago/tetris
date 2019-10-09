package tetris_v1.pkg12;

/**
 * Classe principal com controle de loop e de parâmetros estáticos.
 * Utiliza contador de nanosegundos para loop de atualização de posições.
 * Possui a Main do programa.
 * Faz as primeiras instâncias da parte gráfica.
 * @author user
 */
public class Controlador {
    
    private static SuperficieDeDesenho superficie;
    private static JanelaPrincipal janela;
    private static int APS = 0;
    private static int FPS = 0;
    private static int counter = 100;
    private static int counter_max = Consts.TAXA_QUEDA;

    /**
     * Inicia o jogo e a janela principal.
     * Cria quadro de tamanho pré definido com o título do jogo.
     * Da iíncio ao loop de atualização de imagens que se mantém até o fim do jogo.
     * @param args 
     */
    public static void main(String[] args) {
        criarQuadro("Tetris: Hard Mode", Consts.TAM_LADO_JANELA, Consts.TAM_ALTURA_JANELA);
        startNewGame(Consts.STANDARD_STAGE);
        loop();
    }
    
    /**
     * Método estático para iniciar novo jogo.
     * Chama métodos da superfície para esvaziar a matriz de posições, pontos
     * e define valo de Stage.
     * Chama flush() que cria novamente as peças e as próximas.
     * Taxa de queda volta à inicial.
     * @param stage 
     */
    public static void startNewGame(int stage){
        superficie.getStage().setPontos(0);
        superficie.getStage().setStage(stage);
        superficie.getPeca().flush();
        counter_max = Consts.TAXA_QUEDA;
        
    }
    
    /**
     * Criação da tela de jogo.
     * Facilita alterações na interface futuras.
     * @param nome Título do jogo
     * @param tamX Largura
     * @param tamY Altura
     */
    public static void criarQuadro(String nome, int tamX, int tamY){
        superficie = new SuperficieDeDesenho(tamX, tamY);
        janela = new JanelaPrincipal(nome, superficie);
    }

    /**
     * Método de atualzação.
     * Utiliza um contador para saber em qual atualização deve acontecer um
     * movimento para baixo da peça.
     * Chama o método de desenho da superfície de desenho.
     */
    public static void atualizar(){
        APS++;
        counter--;
        if (counter == 0){
            superficie.getPeca().moverBaixo();   
            
            counter = counter_max;
        }
        superficie.desenhar();
    }
    
    /**
     * Realiza alterações na taxa de queda.
     * @param fator nova taxa de queda
     */
    public static void setCounterMax(double fator){
        counter_max = (int)fator;
    }

    /**
     * Loop de atualização do jogo.
     * Utiliza um contador de nanosegundos.
     * Após certa quantidade (taxa de atualização) realiza as operações de
     * desenho.
     * Método se mantém até o encerramento do jogo.
     */
    public static void loop(){
        final int NS_POR_SEGUNDO = 1000000000;
        final int APS_OBJETIVO = 60;
        final int NS_POR_ATUALIZACAO = NS_POR_SEGUNDO/APS_OBJETIVO;
        long tempoReferencia = System.nanoTime();
        long contadorReferencia = System.nanoTime();
        double delta = 0;
        double tempoSemProcessamento;
        while(true){
                
                long  tempoInicial = System.nanoTime();                             //Pega tempo exato do sistema com precisão de nanosegundos
                tempoSemProcessamento = tempoInicial - tempoReferencia;
                tempoReferencia = tempoInicial;
                delta = delta + tempoSemProcessamento/NS_POR_ATUALIZACAO;
                while (delta >= 1){
                    
                    //Atualização
                    delta --;
                    atualizar();
                }
                FPS++;
                if (System.nanoTime() - contadorReferencia >= NS_POR_ATUALIZACAO ){
                    contadorReferencia = System.nanoTime();
                    APS = 0;
                    FPS = 0;
                }
                
            
        }
    }
    
}
