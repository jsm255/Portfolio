package kiosk;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class KioskPayPanel extends Utils{
	
	public static boolean paying = false;
	public static boolean credit = false;
	public static int change = 0;
	
	private JLabel total = new JLabel();
	private JLabel select = new JLabel();
	
	public static KioskFinalFrame kff;
	
	private JButton cash = new JButton(new ImageIcon(new ImageIcon("images/manwon.png").
			getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
	private JButton card = new JButton(new ImageIcon(new ImageIcon("images/card.png").
			getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));
	
	public KioskPayPanel() {
		setLayout(null);
		setBounds(0,0,400,400);
		
		setPanel();
	}

	private void setPanel() {
		this.cash.setBounds(75, 250, 100, 100);
		this.cash.addActionListener(this);
		
		this.card.setBounds(225,250,100,100);
		this.card.addActionListener(this);
		
		add(this.cash);
		add(this.card);
		
		this.total.setBounds(100, 50, 200, 75);
		this.total.setFont(new Font("",Font.PLAIN,15));
		this.total.setText("<html>총 금액 : "+String.valueOf(KioskOrderPanel.yourPay)+ "원<br/>"+
				"결제 방법을 선택하세요.</html>");
		this.total.setHorizontalAlignment(JLabel.CENTER);
		this.total.setVerticalAlignment(JLabel.CENTER);
		
		add(this.total);
		
//		this.select.setBounds(100, 150, 200, 100);
//		this.select.setFont(new Font("",Font.PLAIN,15));
//		this.select.setText("결제 방법을 선택하세요.");
//		this.select.setHorizontalAlignment(JLabel.CENTER);
//		this.select.setVerticalAlignment(JLabel.CENTER);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(!paying) {
			if(e.getSource() == this.card) credit = true;
			else if(e.getSource() == this.cash) credit = false;
			this.kff = new KioskFinalFrame();
			paying = true;
		}
	}
}
