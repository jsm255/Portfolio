package controller;

import javax.swing.JFrame;

public class GameFrame extends JFrame{
	
	public GameFrame() {
		setLayout(null);
		setBounds(100, 100, 800, 700);
		setTitle("sokoban");
		
		add(new GamePanel());
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		revalidate();
		
	}
}
