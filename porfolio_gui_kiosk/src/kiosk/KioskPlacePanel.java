package kiosk;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class KioskPlacePanel extends Utils {
	
	public static String where = "";
	public static boolean ordering = false;
	
	private JButton eatin = new JButton();
	private JButton togo = new JButton();
	private JButton admin = new JButton();
	
	private ImageIcon im = new ImageIcon(new ImageIcon("images/borgar.png").getImage().
			getScaledInstance(400, 300, Image.SCALE_SMOOTH));
	
	public KioskPlacePanel() {
		setLayout(null);
		setBounds(0, 0, 600, 800);
		
		makeButtons();
	}


	private void makeButtons() {
		this.eatin.setBounds(350, 400, 150, 100);
		this.eatin.setFont(new Font("", Font.BOLD, 20));
		this.eatin.setText("매장 식사");
		this.eatin.addActionListener(this);
		
		this.togo.setBounds(100, 400, 150, 100);
		this.togo.setFont(new Font("", Font.BOLD, 20));
		this.togo.setText("포장");
		this.togo.addActionListener(this);
		
		this.admin.setBounds(200, 600, 200, 100);
		this.admin.setFont(new Font("", Font.BOLD, 30));
		this.admin.setText("관리자 모드");
		this.admin.addActionListener(this);
		
		add(this.eatin);
		add(this.togo);
		add(this.admin);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.drawImage(this.im.getImage(), 100, 50, null);
		
		repaint();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(!ordering) {
			if(e.getSource() instanceof JButton) {
				JButton temp = (JButton) e.getSource();
				
				if(temp == this.eatin) {
					ordering = true;
					where = "store";
					KioskFrame.kf.setContentPane(KioskFrame.kf.kop = new KioskOrderPanel());
				}
				else if(temp == this.togo) {
					ordering = true;
					where = "house";
					KioskFrame.kf.setContentPane(KioskFrame.kf.kop = new KioskOrderPanel());
				}
				else if(temp == this.admin) {
					KioskAdminFrame.kaf = new KioskAdminFrame();
				}
			}
		}
	}
}
