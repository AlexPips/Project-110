import javax.swing.*;
import java.awt.*;
import java.awt.PointerInfo.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
  
public class BrickBreaker extends JPanel {  
	private final static int FrameX = 700;
	private final static int FrameY = 1000;
	private final static int BallSize = 10;
	private Ball[] Balls;
	private static int mouseX;
	private static int mouseY;
	private int score = 0;
	
	public  void BrickBreaker(){
		GameArena stadium = new GameArena(FrameX,FrameX);
		Arrow arrow = new Arrow(FrameX/2,FrameX,mouseX,mouseY,5,"GREEN",stadium);
		Ball b = new Ball(FrameX/2,FrameX-BallSize, BallSize, "GREEN");
		stadium.addBall(b);

		
		
		while(true){
			stadium.update();
			mouseX=MouseInfo.getPointerInfo().getLocation().x-60;
			mouseY=MouseInfo.getPointerInfo().getLocation().y-60;
			arrow.setEnd(mouseX,mouseY);
			
			double xSpeed=5;
			double ySpeed=5;
			double x=b.getXPosition();
			double y=b.getXPosition();

			x = x+xSpeed;
			y = y+ySpeed;
			b.setXPosition(x);
			b.setYPosition(y);

			if (stadium.spacePressed()){
				b.setXSpeed(0);
				b.setYSpeed(10);
			}
		}
	}
		
	public static void main(String[] args) { 
	JFrame frame = new JFrame("Brick Breaker");
	frame.pack();
	frame.setVisible(true);
	frame.setSize(FrameY,FrameX);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	BrickBreaker brickBreaker = new BrickBreaker();
	frame.add(brickBreaker);
	}
	
	

}
