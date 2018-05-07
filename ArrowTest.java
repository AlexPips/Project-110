import javax.swing.*;
import java.awt.*;
import java.awt.PointerInfo.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.concurrent.*;

public class ArrowTest
{
	private final static int FrameX = 700;
	private final static int FrameY = 1000;
	private final static int BallSize = 10;
	private final static int ballsNum=1;
	private static int mouseX;
	private static int mouseY;
	private static double xSpeed;
	private static double ySpeed;
	private static Ball[] Balls;
	private static Rectangle[] Rectangles;
	private static int[] XRectangles= new int[]{0,100,200,300,400,500,600,0,200,300,400,600,0,600,0,100,500,600};
	private static int[] YRectangles= new int[]{0,0,0,0,0,0,0,100,100,100,100,100,200,200,300,300,300,300};
	private static double[] x= new double[5];
	private static double[] y= new double[5];
	private static double[] xRec= new double[18];
	private static double[] yRec= new double[18];
	private static int notEnd=0;
	public static void main(String[] args){
		GameArena stadium = new GameArena(FrameX,FrameX);
		Rectangle Rectangles[] = new Rectangle[18];
		for(int i=0; i<Rectangles.length; i++){		
			Rectangles[i] = new Rectangle(XRectangles[i]+50, YRectangles[i]+50, 100, 100, "YELLOW");
			stadium.addRectangle(Rectangles[i]);
		}
		Arrow arrow = new Arrow(FrameX/2,FrameX,mouseX,mouseY,5,"GREEN",stadium);					//Add Arrow
		Ball balls[] = new Ball[ballsNum];										//add balls
		for(int i=0; i<balls.length; i++){		
			balls[i] = new Ball(FrameX/2,FrameX-BallSize, BallSize, "GREEN");
			stadium.addBall(balls[i]);
		}
		Text textBalls= new Text("1", FrameX/2-5,FrameX-4, 10, "RED");
		stadium.addText(textBalls);			
		int textBallsChanger;
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
			}
			
			if(notEnd==1){
				for(int i=0; i<balls.length; i++){
					x[i]=balls[i].getXPosition();
					y[i]=balls[i].getYPosition();
					if(x[i]>=FrameX || x[i]<=0)
						xSpeed=-xSpeed;
						if( y[i]<=0)
							ySpeed=-ySpeed;
						for(int z=0; z<Rectangles.length; z++){		
							double y1;
							double x1;
							double y2;
							double x2;
							y1=Rectangles[z].getYPosition()+50;
							x1=Rectangles[z].getXPosition()+50;
							y2=Rectangles[z].getYPosition()-50;
							x2=Rectangles[z].getXPosition()-50;

							if(x[i]>=x2 && x[i]<=x1 && y[i]>=y2 && y[i]<=y1){
								stadium.removeRectangle(Rectangles[z]);
								if(y[i]+2>=y1){
									ySpeed=-ySpeed;
								}
								if(y[i]-2<=y2){
									ySpeed=-ySpeed;
								}
								if(x[i]-2<=x2){
									xSpeed=-xSpeed;
								}
								if(x[i]+2>=x1){
									xSpeed=-xSpeed;
								}
							}
							
						}
						x[i] = x[i]+xSpeed;
						y[i] = y[i]+ySpeed;
			
						balls[i].setXPosition(x[i]);
						balls[i].setYPosition(y[i]);
						if(y[i]>=FrameX){  
							balls[i].setXPosition(FrameX/2);
							balls[i].setYPosition(FrameX-BallSize);
							notEnd=2;
						}
														
						textBallsChanger=Integer.parseInt(textBalls.getText());
						if(textBallsChanger>0){
							textBallsChanger=textBallsChanger-1;
						}
					String strI = "" + textBallsChanger;
					textBalls.setText(strI);
				}
			}
			if(notEnd==2){
				for(int i=0; i<Rectangles.length; i++){
					double y;
					y=Rectangles[i].getYPosition();
					Rectangles[i].setYPosition(y+100);
					textBalls.setText("1");
				}
				notEnd=0;
			}
			
		stadium.update();
		}
	
	}
}
