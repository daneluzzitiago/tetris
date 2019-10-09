
package tetris_v1.pkg12;
import elements.FixBlock;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
/**
 * Método que salva e carrega jogo. Não está implementado
 * @author user
 */
public class SaveMe implements Serializable{
    
    private int stage;
    private int pontos;
    private double speed;
    private String tabuleiro[][];
    private FixBlock blocoFixo;

    public SaveMe() {
        
    }
    
    
    
    public void save(int stage, int pontos, double speed, String[][] tabuleiro){

        try {
            FileOutputStream f = new FileOutputStream(new File("teste.txt"));
            ObjectOutputStream o = new ObjectOutputStream(f);
            
            o.writeObject(stage);
            o.writeObject(pontos);
            o.writeObject(speed);
            o.writeObject(tabuleiro);
            
            o.close();
            f.close();
            
            
        } catch (FileNotFoundException e) {
                System.out.println("File not found");
        } catch (IOException e) {
                System.out.println("Error initializing stream");
        }
        
    }
    
    public void load(){
        
        try {
            FileInputStream fi = new FileInputStream(new File("myObjects.txt"));
            ObjectInputStream oi = new ObjectInputStream(fi);
            this.stage = (int) oi.readObject();
            this.pontos = (int) oi.readObject();
            this.speed = (double) oi.readObject();
            this.tabuleiro = (String[][]) oi.readObject();
            oi.close();
            fi.close();
        } catch (Exception e) {
        
        }
    }

    public int getPontos() {
        return pontos;
    }

    public int getStage() {
        return stage;
    }

    public double getSpeed() {
        return speed;
    }

    public String[][] getTabuleiro() {
        return tabuleiro;
    }

    public FixBlock getBlocoFixo() {
        return blocoFixo;
    }
    
    
    
}
