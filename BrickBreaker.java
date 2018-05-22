import javax.swing.*;
import java.awt.*;
import java.awt.PointerInfo.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/** 
  * @author  Alexis Piperide
  * 
  * BrickBraker Project
  *The aim of the player in the game is to break as many bricks as possible by firing a number of bouncing balls.
  * The direction and the speed of the balls can be controlled by the mouse. Each time the player fires the balls 
  * the bricks move one position downwards towards the floor. The bricks must be smashed before touching the floor 
  * otherwise the game is over. The score of the game is 1 point for every smashed brick.
  *
 **/


public class BrickBreaker{  
	private final static int FrameX = 700;
	private final static int FrameY = 1000;
	private final static int BallSize = 10;
	private static int ballsNum=10;
	private static int mouseX;
	private static int mouseY;
	private static double xSpeed;
	private static double ySpeed;
	private static Ball[] Balls;
	private static int[] RectanglesInOut;
	private static Rectangle[] Rectangles;
	private static Text[] rectanglesScore;
	private static Rectangle ScoreBoard;
	private static int[] XRectangles= new int[]{0,100,200,300,400,500,600,0,200,300,400,600,0,600,0,100,500,600,100,300,400,600,000,300,400,500,0,100,200,300,400,500,600};
	private static int[] YRectangles= new int[]{0,0,0,0,0,0,0,100,100,100,100,100,200,200,300,300,300,300,-100,-100,-100,-100,-200,-200,-200,-200,-300,-300,-300,-300,-300,-300,-300};
	private static double[] x= new double[20];
	private static double[] y= new double[20];
	private static double[] xRec= new double[18];
	private static double[] yRec= new double[18];
	private static int notEnd=0;
	private static int round=1;


	
	public BrickBreaker(){																			//Main Game
		GameArena stadium = new GameArena(FrameX,FrameX);
		Rectangle Rectangles[] = new Rectangle[33];
		Text rectanglesScore[] = new Text[33];
		int RectanglesInOut[]= new int[33];
		for(int i=0; i<Rectangles.length; i++){														//Adding Rectangle
			Rectangles[i] = new Rectangle(XRectangles[i]+50, YRectangles[i]+50, 100, 100, "YELLOW");
			stadium.addRectangle(Rectangles[i]);
			Rectangles[i].setRectValue(5);
			rectanglesScore[i] = new Text("5", XRectangles[i]+50, YRectangles[i]+50, 20, "RED" );
			stadium.addText(rectanglesScore[i]);
			RectanglesInOut[i]=0;
		}
		ScoreBoard = new Rectangle(850, 350, 300, 700, "BLUE");
		stadium.addRectangle(ScoreBoard);
		Arrow arrow = new Arrow(FrameX/2,FrameX,mouseX,mouseY,5,"GREEN",stadium);					//Add Arrow
		Ball balls[] = new Ball[ballsNum];				
		Text textScore= new Text(" Score", 700, 50, 20, "RED");
		stadium.addText(textScore);
		Text textScoreNum= new Text(" 0", 745, 70, 20, "RED");
		stadium.addText(textScoreNum);
		int ballText =ballsNum;
		String numberOfBallText = Integer.toString(ballText);
		Text ballNum= new Text(numberOfBallText, FrameX/2-5, FrameX, 15, "RED");
		String numberOfRound = Integer.toString(round);
		Text roundNum= new Text("Round "+numberOfRound, 10,FrameX-10 , 15, "RED");
		stadium.addText(roundNum);

				
		while(true){
			mouseX=MouseInfo.getPointerInfo().getLocation().x-60;
			mouseY=MouseInfo.getPointerInfo().getLocation().y-60;
			arrow.setEnd(mouseX,mouseY);
			if(notEnd==0){																			//Waiting for the user to decide the direction
				if (stadium.spacePressed()){	
					xSpeed=mouseX-FrameX/2;
					ySpeed=mouseY-FrameX;
					notEnd=1;
				}
				if(xSpeed>0){																		//Finding the Speed of the Balls throw the Arrow
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
				for(int i=0; i<balls.length; i++){													//Adding the balls
					balls[i] = new Ball(FrameX/2,FrameX-BallSize, BallSize, "GREEN");
					stadium.addBall(balls[i]);
					balls[i].setXSpeed(intxSpeed+intxSpeed/10);
					balls[i].setYSpeed(intySpeed);

				}
				numberOfRound = Integer.toString(round);
				roundNum.setText("Round "+numberOfRound);
				stadium.addText(ballNum);
				
			}
			
			if(notEnd==1){																			//The balls are moving and hitting the rectangles
				for(int z=0; z<balls.length; z++){
					for(int i=0; i<=z; i++){
						balls[i].moveball();
						balls[i].bounceball(FrameX,FrameX);
						for(int a=0; a<Rectangles.length; a++){
							int newnum=Rectangles[a].getRectValue();
							if (newnum<=0){
								stadium.removeRectangle(Rectangles[a]);
								stadium.removeText(rectanglesScore[a]);
								RectanglesInOut[a]=1;
								
							}else{
								double x = Rectangles[a].getXPosition();
								double y = Rectangles[a].getYPosition();
								balls[i].bounceOnRectangle( x, y, 50);
								double ballX=balls[i].getXPosition();
								double ballY=balls[i].getYPosition();
								Rectangles[a].bounceOnRectangle(ballX,ballY);
								String numberOfRecText = Integer.toString(newnum);
								rectanglesScore[a].setText(numberOfRecText);
							}
							int newballnum=0;
							for(int b=0; b<balls.length; b++){
								newballnum= balls[b].getballValue()+newballnum;
							}
							if(newballnum==ballsNum){
							notEnd=2;
							}
						}
						
					}
					
				}
			}
			if(notEnd==2){																				//Next Round
				notEnd=0;
				round++;
				for(int i=0; i<Rectangles.length; i++){
					double y;
					y=Rectangles[i].getYPosition();
					Rectangles[i].setYPosition(y+100);
					rectanglesScore[i].setYPosition(y+100);
					if(RectanglesInOut[i]==0){
						if(y+100>FrameX){
							notEnd=3;
						
						}
					}
					int win=0;
					for(int a=0; a<Rectangles.length; a++){
						win=RectanglesInOut[i]+win;
					}
					if(win==33){
						notEnd=4;
					}
				}
				notEnd=notEnd;
			}
			if(notEnd==3){																					//Game Over
				Text GAMEOVER= new Text("GAME OVER", 200, 500, 40, "RED");
				stadium.addText(GAMEOVER);
				
			}
			if(notEnd==4){																					//Winner
				Text GAMEOVER= new Text("YOU ARE THE WINNER", 200, 500, 40, "RED");
				stadium.addText(GAMEOVER);
				
			}
		stadium.update();
		}

		
	}
	
		
	public static void main(String[] args) { 
		
		BrickBreaker brickBreaker = new BrickBreaker();
	}
	
	

}
