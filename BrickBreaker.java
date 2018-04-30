import javax.swing.*;
import java.awt.event.*;
  
public class BrickBreaker extends JPanel {  
	private final static int FrameX = 700;
	private final static int FrameY = 1000;
	private Ball[] Balls;
	private int score = 0;
	
	public  void BrickBreaker(){
		GameArena stadium = new GameArena(FrameX,FrameX, true);
		Arrow arrow = new Arrow(150,150,300,300,5,"GREEN",stadium);
		Ball b = new Ball(50,50, 10, "GREEN");
		a.addBall(b);
		while(true){
            stadium.update();
		}
	}
		
	public static void main(String[] args) { 
	JFrame frame = new JFrame("Brick Breaker");
	//frame.getContentPane().add(BrickBreaker());
	frame.pack();
	frame.setVisible(true);
	frame.setSize(FrameY,FrameX);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	BrickBreaker brickBreaker = new BrickBreaker();
	frame.add(brickBreaker);
	}
	
	

}
