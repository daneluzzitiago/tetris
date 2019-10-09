package tetris_v1.pkg12;
import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 *  JFrame base para o Canvas do jogo.
 * @author user
 */
public class JanelaPrincipal extends JFrame{

    public JanelaPrincipal(String nome, SuperficieDeDesenho superficie) {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle(nome);
//        this.setIconImage(superficie.imagem.carregarImagem("logo",".png"));
        this.setResizable(false);
        this.setLayout(new BorderLayout());
        this.add(superficie);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    
    }
    
    
    
}
