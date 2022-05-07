package greedysnake;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;


public class SnakeFrame extends Frame{
	public static final int BLOCK_WIDTH = 10 ;
	public static final int BLOCK_HEIGHT = 10 ;
	//gamepanel setup
	public static final int ROW = 40;
	public static final int COL = 40;

	//point
	private int score = 0;
	private int score1 = 0;


	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	//init objects

	private MyPaintThread paintThread = new MyPaintThread();
	private Image offScreenImage = null;
	private ArrayList arrayList = new ArrayList(8,9,"R");
	private ArrayList arrayList1 = new ArrayList(18,9,"R");
	private Snake snake = new Snake(this,arrayList);
	private SNAK snake1=new SNAK(this,arrayList1);
	public List<Grid> gridlist=new List() {
		@Override
		public int size() {
			return 0;
		}

		@Override
		public boolean isEmpty() {
			return false;
		}

		@Override
		public boolean contains(Object o) {
			return false;
		}

		@Override
		public Iterator iterator() {
			return null;
		}

		@Override
		public Object[] toArray() {
			return new Object[0];
		}

		@Override
		public Object[] toArray(Object[] a) {
			return new Object[0];
		}

		@Override
		public boolean add(Object o) {
			return false;
		}

		@Override
		public boolean remove(Object o) {
			return false;
		}

		@Override
		public boolean containsAll(Collection c) {
			return false;
		}

		@Override
		public boolean addAll(Collection c) {
			return false;
		}

		@Override
		public boolean addAll(int index, Collection c) {
			return false;
		}

		@Override
		public boolean removeAll(Collection c) {
			return false;
		}

		@Override
		public boolean retainAll(Collection c) {
			return false;
		}

		@Override
		public void clear() {

		}

		@Override
		public Object get(int index) {
			return null;
		}

		@Override
		public Object set(int index, Object element) {
			return null;
		}

		@Override
		public void add(int index, Object element) {

		}

		@Override
		public Object remove(int index) {
			return null;
		}

		@Override
		public int indexOf(Object o) {
			return 0;
		}

		@Override
		public int lastIndexOf(Object o) {
			return 0;
		}

		@Override
		public ListIterator listIterator() {
			return null;
		}

		@Override
		public ListIterator listIterator(int index) {
			return null;
		}

		@Override
		public List subList(int fromIndex, int toIndex) {
			return null;
		}
	};
	private Apple apple = new Apple();
	private faeces faeces=new faeces();
	private Grid grid= new Grid();
	private Grid grid1= new Grid();
	private Grid grid2= new Grid();
	private Grid grid3= new Grid();
	private Grid grid4= new Grid();
	private Grid grid5= new Grid();
	private Grid grid6= new Grid();
	private Grid grid7= new Grid();

	private static final Random r = new Random();
	private static SnakeFrame sf =null;
	
	public static void main(String[] args) {
		sf = new SnakeFrame();
		sf.launch();
	}
	
	public void launch(){
		
		this.setTitle("Snake");
		this.setSize(ROW*BLOCK_HEIGHT, COL*BLOCK_WIDTH);
		this.setLocation(30, 40);
		this.setBackground(Color.black);
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
		this.setResizable(false);
		this.setVisible(true);
		
		//为界面添加监听事件
		this.addKeyListener(new KeyMonitor());
		
		new Thread(paintThread).start();


	}
	
	
	private boolean b_gameOver = false;
	private boolean b1_gameOver = false;
	public void gameOver(){
		b_gameOver = true;
	}
	public void gameOver1(){
		b1_gameOver = true;
	}

	@Override
	public void update(Graphics g) {
		if(offScreenImage==null){
			offScreenImage = this.createImage(ROW*BLOCK_HEIGHT, COL*BLOCK_WIDTH);
		}
		Graphics offg = offScreenImage.getGraphics();
		//draw snake in a virtual canvas
		paint(offg);
		//paint the canvas in the game panel
		g.drawImage(offScreenImage, 0, 0, null);
		if(this.b1_gameOver){
			snake1.head.dir="Dead";
			g.setColor(Color.RED);
			g.drawString("p2 is dead！！！", 5*BLOCK_HEIGHT, 25*BLOCK_WIDTH);
			paintThread.dead();
		}
		if(this.b_gameOver){
			snake.head.dir="Dead";
			g.setColor(Color.RED);
			g.drawString("p1 is dead！！！", 5*BLOCK_HEIGHT, 25*BLOCK_WIDTH);
			paintThread.dead();
		}
		if(this.b_gameOver && this.b1_gameOver){
			g.setColor(Color.CYAN);
			g.drawImage(data.bgi.getImage(),-10,-10,null);
			g.drawString("press ENTER to restart", 5*BLOCK_HEIGHT, 35*BLOCK_WIDTH);
			paintThread.dead();
		}
		snake1.draw(g);
		snake.draw(g);


		boolean b_Success=snake.eatApple(apple);
		boolean b_Fail=snake.eatFaeces(faeces);
		boolean b1_Success=snake1.eatApple(apple);
		boolean b1_Fail=snake1.eatFaeces(faeces);
		// count score
		if(b_Success){
			score+=5;
		}
		if(b1_Success){
			score1+=5;
		}
		if(b_Fail){
			score-=5;
		}
		if(b1_Fail){
			score1-=5;
		}

		apple.draw(g);
		faeces.draw(g);
		grid.draw(g);
		grid1.draw(g);
		grid2.draw(g);
		grid3.draw(g);
		grid4.draw(g);
		grid5.draw(g);
		grid6.draw(g);
		grid7.draw(g);
		displaySomeInfor(g);
		
		
	}
	//show some information
	public void displaySomeInfor(Graphics g){
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.drawString("B-dash Space-speed up", 25*BLOCK_HEIGHT, 37*BLOCK_WIDTH);
		g.drawString("player1 score:"+score, 5*BLOCK_HEIGHT, 37*BLOCK_WIDTH);
		g.drawString("player2 score:"+score1, 15*BLOCK_HEIGHT, 37*BLOCK_WIDTH);
		g.setColor(c);
		
	}

	@Override
	public void paint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.GRAY);
		g.setColor(c);
	}



	private class MyPaintThread implements Runnable{
		private static final boolean  running = true;
		private boolean  pause = false;
		public int i=1;
		@Override
		public void run() {
			while(running){
				if(i==2){
					TimerTask task=new TimerTask() {
						@Override
						public void run() {

							repaint();
							i=1;
						}
					};
					Timer timer=new Timer("t");
					long delay=1000L;
					timer.schedule(task,delay);

				}
				if(score1<0){gameOver1();}
				if(score<0){gameOver();}
				snake.SnakeCollide(snake1);
				snake1.SnakeCollide(snake);
				if( grid.GridCollide(snake,grid)||grid1.GridCollide(snake,grid1)||grid2.GridCollide(snake,grid2)||grid3.GridCollide(snake,grid3)||grid4.GridCollide(snake,grid4)||grid5.GridCollide(snake,grid5)||grid6.GridCollide(snake,grid6)||grid7.GridCollide(snake,grid7)){
						snake.sf.gameOver();

				}
				if( grid.GridCollide(snake1,grid)||grid1.GridCollide(snake1,grid1)||grid2.GridCollide(snake1,grid2)||grid3.GridCollide(snake1,grid3)||grid4.GridCollide(snake1,grid4)||grid5.GridCollide(snake1,grid5)||grid6.GridCollide(snake1,grid6)||grid7.GridCollide(snake1,grid7)){
					snake1.sf.gameOver1();

				}
				repaint();
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if(pause){

					continue;

				}
			}

		}
		
		//speed up the game
		public void speedup(){
			launch();

		}
		//snake dash

		public void shift(){
			i+=1;

		}

		public void dead(){
			//pause = true;
		}
		
		//restart the game
		public void reStart(){
			sf.b_gameOver = false;
			sf.b1_gameOver= false;
			this.pause = false;
			snake = new Snake(sf,arrayList);
			snake1=new SNAK(sf,arrayList1);
			score=0;
			score1=0;
			faeces.reAppear();
			apple.reAppear();
			grid.reAppear();
			grid1.reAppear();
			grid2.reAppear();
			grid3.reAppear();
			grid4.reAppear();
			grid5.reAppear();
			grid6.reAppear();
			grid7.reAppear();

	
		}

	}
	
	private class KeyMonitor extends KeyAdapter{
		
		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if(key == KeyEvent.VK_B){
				paintThread.speedup();
			}
			else if(key == KeyEvent.VK_SPACE){
				paintThread.shift();
			}
			else if(key == KeyEvent.VK_ENTER){
				paintThread.reStart();
			}
			else{
				snake.keyPressed(e);
				snake1.keyPressed(e);
			}			
		}
		
	}
	
}
