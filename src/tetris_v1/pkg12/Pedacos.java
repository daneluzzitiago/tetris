package tetris_v1.pkg12;

/**
 * Classe auxiliar para lidar com as peças.
 * A partir do parâmetro nome, a posição relativa de cada pedaço da peça é
 * definido, facilitando as operações de movimento.
 * @author user
 */
public class Pedacos {
    
    private Position posPedacos[];
    private String nome;

    public Pedacos(Position pos1, Position pos2 , Position pos3, Position pos4, String nome) {
        posPedacos = new Position[4];
        posPedacos[0] = pos1;
        posPedacos[1] = pos2;
        posPedacos[2] = pos3;
        posPedacos[3] = pos4;
        this.nome = nome;
    }
    
    public static Pedacos random(){
        String opcoes[] = {"I","J","L","O","S","Z","T"};
        //Criação aleatória utilizando random math com range máximo de 6,99999...
        return criarPeca(opcoes[(int)(Math.random()*6.9)]);
    }
    
    /**
     * A posição relativa basea-se na diferença das posições a partir de um
     * pedaço de referência.
     * Esse pedaço é o 0 , 0, e nunca terá interferência de rotação.
     * @param nome
     * @return 
     */
    public static Pedacos criarPeca(String nome){
        switch (nome){
            case"I": return new Pedacos (new Position(0,0), new Position(1,0),  new Position(-1,0), new Position(2,0),  "I");
            case"J": return new Pedacos (new Position(0,0), new Position(-1,-1),new Position(-1,0), new Position(1,0),  "J");
            case"L": return new Pedacos (new Position(0,0), new Position(1,-1), new Position(-1,0), new Position(1,0),  "L");
            case"O": return new Pedacos (new Position(0,0), new Position(0,-1), new Position(1,0),  new Position(1,-1), "O");
            case"S": return new Pedacos (new Position(0,0), new Position(-1,0), new Position(0,-1), new Position(1,-1), "S");
            case"Z": return new Pedacos (new Position(0,0), new Position(-1,-1),new Position(0,-1), new Position(1,0),  "Z");
            case"T": return new Pedacos (new Position(0,0), new Position(0,-1), new Position(-1,0), new Position(1,0),  "T"); }
        
        return new Pedacos (new Position(0,0), new Position(0,0), new Position(0,0), new Position(0,0), "");
    }
    
    /**
     * Gira todos as posicoes utilizando o "girar" da classe Position.
     */
    public void girar(){
        for (int i = 0; i < posPedacos.length; i++) {
            posPedacos[i].girar();
        }
    }
    /**
     * Necessário em casos de giros que levaram a posições inválidas.
     */
    public void girarInverso(){
        for (int i = 0; i < posPedacos.length; i++) {
            posPedacos[i].girarInverso();
        }
    }

    public Position[] getPosPedacos() {
        return posPedacos;
    }

    public void setPosPedacos(Position[] posPedacos) {
        this.posPedacos = posPedacos;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
    
}
