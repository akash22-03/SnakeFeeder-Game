import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;


public class GamePlay extends JPanel implements KeyListener, ActionListener
{
	private static final long serialVersionUID = 1L;  
	
	private int [] snakeXlength = new int[750];
	private int [] snakeYlength = new int[750];
	private boolean left = false , right = false, top = false, down = false;
	
	ImageIcon headright, headtop, headdown ,headleft, tail, titleImage, fruit;
	
	private int lengthofSnake = 3;
	private int moves = 0;
	private int score = 0;
	
	//fruit parameters
	private int [] fruitXpos = {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
	private int [] fruitYpos = {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,525,550,575,600,625};
	
	Random random = new Random();
	int xpos = random.nextInt(34);
	int ypos = random.nextInt(23);
	
	Timer timer;
	int delay = 100;
	
	public GamePlay()
	{
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();	
	}
	public void paint(Graphics g)
	{
		if(moves == 0)
		{
			snakeXlength[0] = 100;
			snakeXlength[1] = 75;
			snakeXlength[2] = 50;
			
			snakeYlength[0] = 100;
			snakeYlength[1] = 100;
			snakeYlength[2] = 100;
		}
		
		//This is for my title
		titleImage = new ImageIcon("heading.png");
		titleImage.paintIcon(this, g, 25, 5);
		
		//Border
		g.setColor(Color.DARK_GRAY);
		g.drawRect(24,74,851,577);
		
		//BackGround
		g.setColor(Color.black);
		g.fillRect(25, 75, 850, 575);
		
		//draw score
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.PLAIN, 14));
		g.drawString("Score:"+score, 780, 30);
		
		//draw length snake
		g.setColor(Color.white);
		g.setFont(new Font("Arial", Font.PLAIN, 14));
		g.drawString("Length:"+lengthofSnake, 780, 50);
		
		//initial position
		headright = new ImageIcon("headRight.png");
		headright.paintIcon(this, g, snakeXlength[0], snakeYlength[0]);
		
		for(int i=0; i<lengthofSnake; i++)
		{
			if(i==0 && right){
				headright = new ImageIcon("headRight.png");
				headright.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
			}
			if(i==0 && left){
				headleft = new ImageIcon("headLeft.png");
				headleft.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
			}
			if(i==0 && down){
				headdown = new ImageIcon("headDown.png");
				headdown.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
			}
			if(i==0 && top){
				headtop = new ImageIcon("headTop.png");
				headtop.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
			}
			if(i!=0){
				tail = new ImageIcon("tail.png");
				tail.paintIcon(this, g, snakeXlength[i], snakeYlength[i]);
			}
			fruit = new ImageIcon("fruit.png");
			
			if(fruitXpos[xpos] == snakeXlength[0] && fruitYpos[ypos] == snakeYlength[0])
			{
				score = score + 5;
				lengthofSnake++;
				xpos = random.nextInt(34);
				ypos = random.nextInt(23);
			}
			fruit.paintIcon(this , g, fruitXpos[xpos], fruitYpos[ypos]);
			
		}	
		
		for(int i=1; i<lengthofSnake; i++)
		{
			if(snakeXlength[i] == snakeXlength[0] && snakeYlength[i] == snakeYlength[0])
			{
				right = false;
				left = false;
				top = false;
				down = false;
				
				g.setColor(Color.WHITE);
				g.setFont(new Font("arial", Font.BOLD, 40));
				g.drawString("Game Over! Score: " + score, 250, 300);
				
				g.setFont(new Font("arial", Font.BOLD, 20));
				g.drawString("Press Enter to restart", 350, 340);
			}
		}
		g.dispose();	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		timer.restart();
		if(right)
		{
			for(int n = lengthofSnake-1; n>=0; n--)
			{
				snakeYlength[n+1] = snakeYlength[n];
			}
			for(int n = lengthofSnake; n>=0; n--)
			{
				if(n==0)
					snakeXlength[n] = snakeXlength[n]+25;
				else
					snakeXlength[n] = snakeXlength[n-1];
				if(snakeXlength[n] > 850)
					snakeXlength[n] = 25;	
			}
			repaint();
		}
		if(left) 
		{
			for(int n = lengthofSnake-1; n>=0;n--){
				snakeYlength[n+1] = snakeYlength[n];
			}
			for(int n = lengthofSnake; n>=0; n--) {
				if (n==0) {
					snakeXlength[n] = snakeXlength[n]-25;
				}
				else {
					snakeXlength[n] = snakeXlength[n-1];
				}
				if(snakeXlength[n] < 25){
					snakeXlength[n] = 850;
				}	
			}
			repaint();			
		}
		if(top) 
		{
			for(int n = lengthofSnake-1; n>=0;n--){
				snakeXlength[n+1] = snakeXlength[n];
			}
			for(int n = lengthofSnake; n>=0; n--){
				if (n==0) {
					snakeYlength[n] = snakeYlength[n]-25;
				}
				else {
					snakeYlength[n] = snakeYlength[n-1];
				}
				if(snakeYlength[n] < 75){
					snakeYlength[n] = 625;
				}	
			}
			repaint();	
		}
		if(down) 
		{
			for(int n = lengthofSnake-1; n>=0;n--){
				snakeXlength[n+1] = snakeXlength[n];
			}
			for(int n = lengthofSnake; n>=0; n--) {
				if (n==0) {
					snakeYlength[n] = snakeYlength[n]+25;
				}
				else {
					snakeYlength[n] = snakeYlength[n-1];
				}
				if(snakeYlength[n] > 625){
					snakeYlength[n] = 75;
				}		
			}		
			repaint();
		}
	}

	@Override
	public void keyTyped(KeyEvent e){
		
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_ENTER)
		{
			moves = 0;
			score = 0;
			lengthofSnake = 3;
			repaint();
		}
		if(e.getKeyCode()== KeyEvent.VK_RIGHT)
		{
			moves++;
			right = true;
			if(!left)
			{
				right = true;
			}
			else
			{
				right = false;
				left = true;
			}
			top = false;
			down = false;
		}
		if(e.getKeyCode()== KeyEvent.VK_LEFT)
		{
			moves++;
			left = true;
			if(!right)
			{
				left = true;
			}
			else
			{
				left = false;
				right = true;
			}
			top = false;
			down = false;
		}
		if(e.getKeyCode()== KeyEvent.VK_UP)
		{
			moves++;
			top = true;
			if(!down)
			{
				top = true;
			}
			else
			{
				top = false;
				down = true;
			}
			left = false;
			right = false;
		}
		if(e.getKeyCode()== KeyEvent.VK_DOWN)
		{
			moves++;
			down = true;
			if(!top)
			{
				down = true;
			}
			else
			{
				down = false;
				top = true;
			}
			left = false;
			right = false;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}


}
