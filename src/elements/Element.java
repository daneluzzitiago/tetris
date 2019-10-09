/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;


import tetris_v1.pkg12.Position;

/**
 *  Classe abstrata de elementos "Printáveis" na tela de jogo.
 * @author user
 */
public abstract class Element {
    Position pos;

    public Element(Position pos) {
        this.pos = pos;
    }
    public Element(){
        
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }
    
    
}
