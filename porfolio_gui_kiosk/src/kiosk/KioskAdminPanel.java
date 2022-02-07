package kiosk;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class KioskAdminPanel extends Utils{
	
	private String adminId = "admin";
	private String adminPw = "0000";
	
	private JLabel label = new JLabel();
	
	private JTextField idField = new JTextField();
	private JTextField pwField = new JTextField();
	
	private JButton login = new JButton();
	
	public KioskAdminPanel() {
		setLayout(null);
		setBounds(0, 0, 600, 800);
		
		setPanel();
	}

	private void setPanel() {
		this.label.setFont(new Font("",Font.PLAIN, 20));
		this.label.setText("관리자 기본 id는 admin, pw는 0000입니다.");
		this.label.setBounds(100, 100, 400, 100);
		this.label.setHorizontalAlignment(JLabel.CENTER);
		
		add(this.label);
		
		this.idField.setBounds(100, 200, 200, 50);
		this.idField.setFocusable(true);
		this.idField.addKeyListener(this);
		
		this.pwField.setBounds(100, 250, 200, 50);
		this.pwField.setFocusable(true);
		this.pwField.addKeyListener(this);
		
		this.login.setBounds(350, 200, 100, 100);
		this.login.setText("로그인");
		this.login.addActionListener(this);
		
		add(this.idField);
		add(this.pwField);
		add(this.login);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.login) {
			checkIdPw();
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == e.VK_ENTER) {
			checkIdPw();
		}
	}
	
	private void checkIdPw() {
		if(this.idField.getText().equals(this.adminId) &&
				this.pwField.getText().equals(this.adminPw)) {
			KioskAdminFrame.kaf.setContentPane(
					KioskAdminFrame.kaf.kamp = new KioskAdminManagePanel());
			KioskAdminFrame.kaf.revalidate();
		}
		else {
			JOptionPane.showMessageDialog(null, "틀렸습니다!");
			this.idField.setText("");
			this.pwField.setText("");
		}
	}
}
