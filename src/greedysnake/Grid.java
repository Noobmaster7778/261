package greedysnake;

import java.awt.*;


public class Grid extends Apple{

    public void draw(Graphics g){
        Image img=data.grid.getImage();
        g.drawImage(img,col*BLOCK_WIDTH,row*BLOCK_HEIGHT,null);}


    public boolean GridCollide(Snake snake,Grid grid){
        for(int i=0;i<7;i++){
            if(snake.getRect().intersects(grid.getRect())){
                return true;
            }
        }
        return false;
    }}

