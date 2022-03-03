package bsu.rfe.java.group10.lab6.Nikolaeva.varB5;

import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.JOptionPane;
import java.awt.geom.Ellipse2D;

public class BouncingBall implements Runnable {
	private static final int MAX_RADIUS = 40;
	private static final int MIN_RADIUS = 3;
	private static final int MAX_SPEED = 15;
	private Field field;
	private int radius;
	private Color color;
	private double x;
	private double y;
	private int speed;
	private double speedX;
	private double speedY;
	private int X=0;
	private int Y=0;
	private int counter=0;
	private boolean setSnowBall = false;
	
	public BouncingBall(Field field) {
		this.field = field;
		radius = new Double(Math.random()*(MAX_RADIUS - MIN_RADIUS)).intValue() + MIN_RADIUS;
		speed = new Double(Math.round(5*MAX_SPEED / radius)).intValue();
		if (speed>MAX_SPEED) {
			speed = MAX_SPEED;
		}
		double angle = Math.random()*2*Math.PI;
		speedX = 3*Math.cos(angle);
		speedY = 3*Math.sin(angle);
		color = new Color((float)Math.random(), (float)Math.random(), (float)Math.random());
		x = Math.random()*(field.getSize().getWidth()-2*radius) + radius;
		y = Math.random()*(field.getSize().getHeight()-2*radius) + radius;
		Thread thisThread = new Thread(this);
		thisThread.start();
	}
	
	public void run() {
		try {
			
			while(true) {
				field.canMove(this);
				if (x + speedX <= radius) {
					speedX = -speedX;
					x = radius;
				} else if (x + speedX >= field.getWidth() - radius) {
					speedX = -speedX;
					x=new Double(field.getWidth()-radius).intValue();
				} else if (y + speedY <= radius) {
					speedY = -speedY;
					y = radius;
				} else if (y + speedY >= field.getHeight() - radius) {
					speedY = -speedY;
					y=new Double(field.getHeight()-radius).intValue();
				} else if (speed!=0){
					x += speedX;
					y += speedY;
				}
				if(setSnowBall && X!=0 && Y!=0 && speed!=0) {
					counter++;
					if(counter==X) {
						radius+=Y;
						counter=0;
						speed=new Double(Math.round(5*MAX_SPEED / radius)).intValue();
						if (speed>MAX_SPEED) {
							speed = MAX_SPEED;
						}
					}
				}
				Thread.sleep(16-speed);
			}
		} catch (InterruptedException ex) {}
	}
	
	public void switchSnowBall(boolean isSelected, int X0, int Y0) {
		setSnowBall=isSelected;
		if(setSnowBall) {
			X=X0;
			Y=Y0;
		}
		else{
			X=0;
			Y=0;
		}
	}
	
	public void paint(Graphics2D canvas) {
		canvas.setColor(color);
		canvas.setPaint(color);
		Ellipse2D.Double ball = new Ellipse2D.Double(x-radius, y-radius, 2*radius, 2*radius);
		canvas.draw(ball);
		canvas.fill(ball);
	}
	
}
