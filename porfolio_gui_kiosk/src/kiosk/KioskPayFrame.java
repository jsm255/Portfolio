package kiosk;

import javax.swing.JFrame;

public class KioskPayFrame extends JFrame{
	
	public KioskPayFrame() {
		setLayout(null);
		setBounds(200, 300, 400, 400);
		setTitle("I will have order!");
		
		this.add(new KioskPayPanel());
		
		setVisible(true);
		revalidate();

	}
	
	public void disposeFrame() {
		dispose();
	}
}
