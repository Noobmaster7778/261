package greedysnake;

import java.awt.*;
import java.util.Random;

public class Apple {
	//Location
	protected int row;
	protected int col;
	//Size of game panel grid
	protected static final int BLOCK_WIDTH = SnakeFrame.BLOCK_WIDTH;
	protected static final int BLOCK_HEIGHT = SnakeFrame.BLOCK_HEIGHT;
	
	protected static final Random r = new Random();
	
	private Color color = Color.RED;
	
	public Apple(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public Apple() {
		this((r.nextInt(SnakeFrame.ROW-4)+5),(r.nextInt(SnakeFrame.COL-4)+5));

	}
	
	public void reAppear(){
		this.row = (r.nextInt(SnakeFrame.ROW-4))+3;
		this.col = (r.nextInt(SnakeFrame.COL-4))+3;
	} 
	
	public void draw(Graphics g){
		Image img=data.apple.getImage();
		g.drawImage(img,col*BLOCK_WIDTH,row*BLOCK_HEIGHT,null);
		
	}
	//Return head location
	public Rectangle getRect(){
		return new Rectangle(col*BLOCK_WIDTH, row*BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT);
	}
	
}
