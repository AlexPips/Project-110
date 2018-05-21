import javax.swing.*;
import java.awt.*;
import java.awt.PointerInfo.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BrickBreaker{  
	private final static int FrameX = 700;
	private final static int FrameY = 1000;
	private final static int BallSize = 10;
	private static int ballsNum=5;
	private static int mouseX;
	private static int mouseY;
	private static double xSpeed;
	private static double ySpeed;
	private static Ball[] Balls;
	private static Rectangle[] Rectangles;
	private static Text[] rectanglesScore;
	private static Rectangle ScoreBoard;
	private static int[] XRectangles= new int[]{0,100,200,300,400,500,600,0,200,300,400,600,0,600,0,100,500,600};
	private static int[] YRectangles= new int[]{0,0,0,0,0,0,0,100,100,100,100,100,200,200,300,300,300,300};
	private static double[] x= new double[20];
	private static double[] y= new double[20];
	private static double[] xRec= new double[18];
	private static double[] yRec= new double[18];
	private static int notEnd=0;


	
	public BrickBreaker(){
		GameArena stadium = new GameArena(FrameY,FrameX);
		Rectangle Rectangles[] = new Rectangle[18];
		Text rectanglesScore[] = new Text[18];
		for(int i=0; i<Rectangles.length; i++){		
			Rectangles[i] = new Rectangle(XRectangles[i]+50, YRectangles[i]+50, 100, 100, "YELLOW");
			stadium.addRectangle(Rectangles[i]);
			Rectangles[i].setValue(5);
			rectanglesScore[i] = new Text("5", XRectangles[i]+50, YRectangles[i]+50, 20, "RED" );
			stadium.addText(setValue[i]);
		}
		ScoreBoard = new Rectangle(850, 350, 300, 700, "BLUE");
		stadium.addRectangle(ScoreBoard);
		Arrow arrow = new Arrow(FrameX/2,FrameX,mouseX,mouseY,5,"GREEN",stadium);					//Add Arrow
		Ball balls[] = new Ball[ballsNum];										//add balls
		Text textScore= new Text(" Score", 700, 50, 20, "RED");
		stadium.addText(textScore);
		Text textScoreNum= new Text(" 0", 745, 70, 20, "RED");
		stadium.addText(textScoreNum);
		int ballText =ballsNum;
		String numberOfBallText = Integer.toString(ballText);
		Text ballNum= new Text(numberOfBallText, FrameX/2-5, FrameX, 15, "RED");
		

				
		while(true){
			mouseX=MouseInfo.getPointerInfo().getLocation().x-60;
			mouseY=MouseInfo.getPointerInfo().getLocation().y-60;
			arrow.setEnd(mouseX,mouseY);
			if(notEnd==0){
				if (stadium.spacePressed()){	
					xSpeed=mouseX-FrameX/2;
					ySpeed=mouseY-FrameX;
					notEnd=1;
				}
				if(xSpeed>0){
					while(xSpeed>10){
						xSpeed=xSpeed/2;
						ySpeed=ySpeed/2;
						
					}
				}else{
					while(xSpeed<-10){
						xSpeed=xSpeed/2;
						ySpeed=ySpeed/2;	
					}
				}
				if(ySpeed<0){
					while(ySpeed<-10){
						xSpeed=xSpeed/2;
						ySpeed=ySpeed/2;	
					}
				}
				xSpeed=xSpeed+xSpeed/20;
				int intxSpeed=(int) xSpeed;
				int intySpeed=(int) ySpeed;
				for(int i=0; i<balls.length; i++){		
					balls[i] = new Ball(FrameX/2,FrameX-BallSize, BallSize, "GREEN");
					stadium.addBall(balls[i]);
					balls[i].setXSpeed(intxSpeed);
					balls[i].setYSpeed(intySpeed);

				}
				stadium.addText(ballNum);
				
				
			}
			
			if(notEnd==1){
				for(int z=0; z<balls.length; z++){
					for(int i=0; i<=z; i++){
						balls[i].moveball();
						balls[i].bounceball(FrameX,FrameX);
						for(int a=0; a<Rectangles.length; a++){
							double x = Rectangles[a].getXPosition();
							double y = Rectangles[a].getYPosition();
							balls[i].bounceOnRectangle( x, y, 50);
							double ballX=balls[i].getXPosition();
							double ballY=balls[i].getYPosition();
							Rectangles[a].bounceOnRectangle(ballX,ballY);
							//int newnum=Rectangles[a].getRectangleValue();
							//numberOfBallText = Integer.toString(newnum);
							//rectanglesScore[i].setText(numberOfBallText);
						}
						
					}
				if(ballText>0){
					ballText--;
					
					numberOfBallText = Integer.toString(ballText);
					ballNum.setText(numberOfBallText);
					stadium.addText(ballNum);
				}	
				}
			}
			if(notEnd==2){
				for(int i=0; i<Rectangles.length; i++){
					double y;
					y=Rectangles[i].getYPosition();
					Rectangles[i].setYPosition(y+100);
					
				}
				notEnd=0;
			}
			
		stadium.update();
		}

		
	}
	
		
	public static void main(String[] args) { 
		
		BrickBreaker brickBreaker = new BrickBreaker();
	}
	
	

}
