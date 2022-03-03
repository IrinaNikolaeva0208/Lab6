package bsu.rfe.java.group10.lab6.Nikolaeva.varB5;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	private static final int WIDTH = 700;
	private static final int HEIGHT = 500;
	private JMenuItem pauseMenuItem;
	private JMenuItem resumeMenuItem;
	private JCheckBoxMenuItem snowBallMenuItem;
	private Field field = new Field();
	
	public MainFrame() {
		super("���������������� � ������������� �������");
		setSize(WIDTH, HEIGHT);
		Toolkit kit = Toolkit.getDefaultToolkit();
		setLocation((kit.getScreenSize().width - WIDTH)/2,
		(kit.getScreenSize().height - HEIGHT)/2);
		setExtendedState(MAXIMIZED_BOTH);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu ballMenu = new JMenu("����");
		
		Action addBallAction = new AbstractAction("�������� ���") {
			public void actionPerformed(ActionEvent event) {
				field.addBall();
				if (!pauseMenuItem.isEnabled() &&
				!resumeMenuItem.isEnabled()) {
					pauseMenuItem.setEnabled(true);
					snowBallMenuItem.setEnabled(true);
				}
			}
		};
		
		menuBar.add(ballMenu);
		ballMenu.add(addBallAction);
		JMenu controlMenu = new JMenu("����������");
		menuBar.add(controlMenu);
		
		Action pauseAction = new AbstractAction("������������� ��������"){
			public void actionPerformed(ActionEvent event) {
				field.pause();
				pauseMenuItem.setEnabled(false);
				resumeMenuItem.setEnabled(true);
				snowBallMenuItem.setEnabled(false);
			}
		};
		
		pauseMenuItem = controlMenu.add(pauseAction);
		pauseMenuItem.setEnabled(false);
		
		Action resumeAction = new AbstractAction("����������� ��������") {
			public void actionPerformed(ActionEvent event) {
				field.resume();
				pauseMenuItem.setEnabled(true);
				resumeMenuItem.setEnabled(false);
				snowBallMenuItem.setEnabled(true);
			}
		};
		
		resumeMenuItem = controlMenu.add(resumeAction);
		resumeMenuItem.setEnabled(false);
		getContentPane().add(field, BorderLayout.CENTER);
		
		Action snowBallAction=new AbstractAction("������� ���") {
			public void actionPerformed(ActionEvent event) {
				try {
				field.setSnowBall(snowBallMenuItem.isSelected());
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(MainFrame.this,"������� �������� ������","������",JOptionPane.ERROR_MESSAGE);
					snowBallMenuItem.setState(false);
				}
			}
		}; 
		
		snowBallMenuItem = new JCheckBoxMenuItem(snowBallAction);
		controlMenu.add(snowBallMenuItem);
		snowBallMenuItem.setEnabled(false);
	}
	
	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}