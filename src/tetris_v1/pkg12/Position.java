package tetris_v1.pkg12;

/**
 * Armazena posições e realiza operações em duas dimensões.
 * Matém a lógica simples e enxuta.
 * @author user
 */
public class Position{
    
    double X;
    double Y;

    public Position(double X, double Y) {
        this.X = X;
        this.Y = Y;
    }
    
    /**
     * Getters.
     * Adicional de getter que converte o valor de X para int diretamente.
     * @return 
     */
    public int intX(){
        return (int)X;
    }
    public int intY(){
        return (int)Y;
    }
    public double getX() {
        return X;
    }
    public double getY() {
        return Y;
    }
    
    /**
     * Setters.
     * @param X 
     */
    public void setX(double X) {
        this.X = X;
    }
    public void setY(double Y) {
        this.Y = Y;
    }
    
    /**
     * Operações de movimento simplificadas para serem usadas pedaço por pedaço.
     */
    public void moverDireita(){
        X++;
    }
    public void moverEsquerda(){
        X--;
    }
    public void moverBaixo(){
        Y++;
    }
    public void moverCima(){
        Y--;
    }

    /**
     * Estratégia de rotação e anti rotação.
     * Basea-se no espelhamento entre o eixos X e Y.
     */
    public void girar(){
        double aux = X;
        X = -Y;
        Y = aux;
    }
    public void girarInverso(){
        double aux = X;
        X = Y;
        Y = -aux;
    }
    
}
