package greedysnake;

import java.awt.*;
import java.awt.event.KeyEvent;

public class SNAK extends Snake{
    public SNAK(SnakeFrame sf, ArrayList arrayList) {
        super(sf, arrayList);
    }
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch(key){
            case KeyEvent.VK_A :
                if(head.dir!="R"){
                    head.dir = "L";
                }
                break;
            case KeyEvent.VK_W :
                if(head.dir!="D"){
                    head.dir = "U";
                }
                break;
            case KeyEvent.VK_D :
                if(head.dir!="L"){
                    head.dir = "R";
                }
                break;
            case KeyEvent.VK_S :
                if(head.dir!="U"){
                    head.dir = "D";
                }
                break;
        }
    }
    public void draw(Graphics g){
        if(head==null){
            return ;
        }
        move();
        head.drawhead(g,data.head1);
        for(ArrayList arrayList = head.next; arrayList!=null; arrayList = arrayList.next){

            arrayList.draw(g,data.body1);
        }
    }
    private void checkDead() {

        if(head.row<5||head.row+3>SnakeFrame.ROW||head.col<2||head.col+3>SnakeFrame.COL){
            this.sf.gameOver1();
        }
        for(ArrayList arrayList = head.next; arrayList!=null; arrayList = arrayList.next){
            if(head.row==arrayList.row&&head.col == arrayList.col){
                this.sf.gameOver1();
            }
        }
    }
    public void move() {
        addNodeInHead();
        checkDead();
        deleteNodeInTail();
    }
    public void SnakeCollide(Snake snake){

        for(ArrayList arrayList = head.next; arrayList!=null; arrayList = arrayList.next){
            if(snake.head.row==arrayList.row&&snake.head.col == arrayList.col){
                this.sf.gameOver1();
            }
        }

    }

}
