package kiosk;

import javax.swing.JFrame;

public class KioskAdminFrame extends JFrame{
	
	public static KioskAdminFrame kaf;
	
	private KioskAdminPanel kap;
	public KioskAdminManagePanel kamp;
	public KioskAdminRemainPanel karp;
	
	public KioskAdminFrame() {
		setLayout(null);
		setBounds(700, 100, 600, 800);
		setTitle("관리자의 권력");
		
		add(kap = new KioskAdminPanel());
		
		setVisible(true);
		revalidate();
	}
}
