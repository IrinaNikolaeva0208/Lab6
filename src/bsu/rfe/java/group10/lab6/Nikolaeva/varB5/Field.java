package bsu.rfe.java.group10.lab6.Nikolaeva.varB5;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Field extends JPanel {
	private boolean paused;
	private boolean snowBall=false;
	private int X=0;
	private int Y=0;
	private ArrayList<BouncingBall> balls = new ArrayList<BouncingBall>(10);
	private Timer repaintTimer = new Timer(10, new ActionListener() {
		public void actionPerformed(ActionEvent ev) {
			repaint();
		}
	});
	
	public Field() {
		setBackground(Color.WHITE);
		repaintTimer.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D canvas = (Graphics2D) g;
		for (BouncingBall ball: balls) {
			ball.switchSnowBall(snowBall, X, Y);
			ball.paint(canvas);
		}
	}
	
	public void setSnowBall(boolean isSelected) {
		snowBall=isSelected;
		if(snowBall) {
			X=Integer.parseInt(JOptionPane.showInputDialog("Введите Х"));
			Y=Integer.parseInt(JOptionPane.showInputDialog("Введите Y"));
			if(X<1 || Y<1) {
				X=0;
				Y=0;
				throw new NumberFormatException("Неверное число");
			}
		}
		else {
			X=0;
			Y=0;
		}
		
	}
	
	public void addBall() {
		balls.add(new BouncingBall(this));
	}
	
	public synchronized void pause() {
		paused = true;
	}
	
	public synchronized void resume() {
		paused = false;
		notifyAll();
	}
	
	public synchronized void canMove(BouncingBall ball) throws
	InterruptedException {
		if (paused) {
			wait();
		}
	}
}
