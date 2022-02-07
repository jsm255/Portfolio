package kiosk;

import javax.swing.JFrame;

public class KioskFrame extends JFrame{
	
	public static KioskFrame kf;
	
	public KioskPlacePanel kpp;
	public KioskOrderPanel kop;
	
	public static boolean swap = false;
	
	public KioskFrame() {
		setLayout(null);
		setBounds(100, 100, 600, 800);
		setTitle("B U R G E R");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.add(this.kpp = new KioskPlacePanel());
		
		setVisible(true);
		revalidate();
		
//		while(true) {
//			if(!swap) revalidate();
//			if(KioskPlacePanel.where.compareTo("") != 0 && !swap) {
//				this.setContentPane(kop);
//				swap = true;
//				break;
//			}
//		}
	}
}
