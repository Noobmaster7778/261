package greedysnake;

import javax.swing.*;
import java.awt.*;

public class ArrayList {



     //location

    public int row;
    public int col;
    //direction
    public String dir;

    protected ArrayList pre;
    protected ArrayList next;


    public ArrayList(int row, int col, String dir) {
        this.row = row;
        this.col = col;
        this.dir = dir;
    }

    public void drawhead(Graphics g,ImageIcon imageIcon) {
        Image img = imageIcon.getImage();
        g.drawImage(img, col * Snake.BLOCK_WIDTH, row * Snake.BLOCK_HEIGHT, null);
    }

    public void draw(Graphics g,ImageIcon imageIcon) {
        Image img = imageIcon.getImage();
        g.drawImage(img, col * Snake.BLOCK_WIDTH, row * Snake.BLOCK_HEIGHT, null);

    }

}
