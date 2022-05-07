package greedysnake;

import java.awt.*;
import java.util.Random;

public class faeces extends Apple{
    protected static final Random r = new Random();
    public void draw(Graphics g){
        Image img=data.faeces.getImage();
        g.drawImage(img,col*BLOCK_WIDTH,row*BLOCK_HEIGHT,null);
    }
}
