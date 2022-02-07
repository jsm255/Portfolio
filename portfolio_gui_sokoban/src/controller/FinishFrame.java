package controller;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class FinishFrame extends JFrame{
	
	private JLabel label = new JLabel();
	
	public FinishFrame() {
		setLayout(null);
		setBounds(350, 250, 300, 200);
		setTitle("Finished!");
		
		this.label.setText("Congratulations!");
		this.label.setBounds(20, 20, 260, 160);
		this.label.setFont(new Font("",Font.BOLD,25));
		this.label.setHorizontalAlignment(JLabel.CENTER);
		this.label.setVerticalAlignment(JLabel.CENTER);
		
		add(this.label);
		
		setVisible(true);
		revalidate();
	}
}
