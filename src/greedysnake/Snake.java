package greedysnake;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Snake{
	public static final int BLOCK_WIDTH = SnakeFrame.BLOCK_WIDTH;
	public static final int BLOCK_HEIGHT = SnakeFrame.BLOCK_HEIGHT;

	protected ArrayList head= null;
	private ArrayList tail = null;

	protected SnakeFrame sf;

	private int size = 0;
	public Snake(SnakeFrame sf,ArrayList arrayList) {


		head = arrayList;
		tail = arrayList;
		size ++;
		this.sf = sf ;
		
	}

	public void draw(Graphics g){
		if(head==null){
			return ;
		}
		move();
		head.drawhead(g,data.head);
		for(ArrayList arrayList = head.next; arrayList!=null; arrayList = arrayList.next){

			arrayList.draw(g,data.body);
		}	
	}
	//add a node in head,then delete one in tail,then the snake will move

	public void move() {
		addNodeInHead();
		checkDead();
		deleteNodeInTail();
	}

	private void checkDead() {
		//head node detection
		if(head.row<5||head.row+3>SnakeFrame.ROW||head.col<2||head.col+3>SnakeFrame.COL){
			this.sf.gameOver();
		}

		for(ArrayList arrayList = head.next; arrayList!=null; arrayList = arrayList.next){
			if(head.row==arrayList.row&&head.col == arrayList.col){
				this.sf.gameOver();
			}
		}


	}

	protected void deleteNodeInTail() {
		ArrayList arrayList = tail.pre;
		tail = null;
		arrayList.next = null;
		tail = arrayList;
	}

	protected void addNodeInHead() {
		ArrayList arrayList = null;
		switch(head.dir){
		case "L":
			arrayList = new ArrayList(head.row,head.col-1,head.dir);
			break;
		case "U":
			arrayList = new ArrayList(head.row-1,head.col,head.dir);
			break;
		case "R":
			arrayList = new ArrayList(head.row,head.col+1,head.dir);
			break;
		case "D":
			arrayList = new ArrayList(head.row+1,head.col,head.dir);
			break;
		case "Dead":
			arrayList = new ArrayList(head.row,head.col,head.dir);
			break;
		}
		
		arrayList.next = head;
		head.pre = arrayList;
		head = arrayList;

	}


	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch(key){
		case KeyEvent.VK_LEFT :
			if(head.dir!="R"){
				head.dir = "L";
			}
			break;
		case KeyEvent.VK_UP :
			if(head.dir!="D"){
				head.dir = "U";
			}
			break;
		case KeyEvent.VK_RIGHT :
			if(head.dir!="L"){
				head.dir = "R";
			}
			break;
		case KeyEvent.VK_DOWN :
			if(head.dir!="U"){
				head.dir = "D";
			}
			break;
		}
	}
	
	public Rectangle getRect(){
		return new Rectangle(head.col*BLOCK_WIDTH, head.row*BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT);
	}
	
	public boolean eatApple(Apple apple){
		
		if(this.getRect().intersects(apple.getRect())){

			addNodeInHead();
			apple.reAppear();
			return true;
		}
		else{
			return false;
		}
	}
	public void SnakeCollide(SNAK snake){
		for(ArrayList arrayList = head.next; arrayList!=null; arrayList = arrayList.next){
			if(snake.head.row==arrayList.row&&snake.head.col == arrayList.col){
				this.sf.gameOver();
			}
		}


	}
	public boolean eatFaeces(faeces faeces){

		if(this.getRect().intersects(faeces.getRect())){
			if(tail!=head){
			deleteNodeInTail();}
			faeces.reAppear();
			return true;
		}
		else{
			return false;
		}
	}
	public boolean eatGrids(Grid grid){

		if(this.getRect().intersects(grid.getRect())){
			return true;
		}
		else{
			return false;
		}
	}



}
