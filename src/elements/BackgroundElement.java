
package elements;

import java.awt.Color;
import tetris_v1.pkg12.Consts;

/**
 * Elemento translucido para quadricular o fundo
 * @author user
 */
public class BackgroundElement {
    Color c_1;
    Color c_2;
    
    public BackgroundElement(){
        this.c_1 = Consts.BG_COLOR_1;
        this.c_2 = Consts.BG_COLOR_2;
    }

    public Color getC_1() {
        return c_1;
    }

    public Color getC_2() {
        return c_2;
    }
    
    
    
}
