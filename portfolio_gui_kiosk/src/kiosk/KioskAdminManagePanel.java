package kiosk;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class KioskAdminManagePanel extends Utils {
	
	// 뭐가 얼마나 팔렸냐


	public static Vector<Vector<String>> sold = new Vector<>();
	private JTable table;
	
	public static int earn = 0;
	
	private JButton setItems = new JButton();
	private JButton checkEarn = new JButton();
	private JButton exit = new JButton();
	
	private JLabel label = new JLabel();
	
	public KioskAdminManagePanel() {
		setLayout(null);
		setBounds(0, 0, 600, 800);
		
		setPanel();
	}

	private void setPanel() {
		this.setItems.setBounds(50, 20, 500, 75);
		this.setItems.setFont(new Font("", Font.BOLD, 12));
		this.setItems.setText("재고 관리");
		this.setItems.addActionListener(this);
		
		add(this.setItems);
		
		this.checkEarn.setBounds(50, 100, 200, 75);
		this.checkEarn.setFont(new Font("",Font.BOLD, 15));
		this.checkEarn.setText("총 매출 확인");
		this.checkEarn.addActionListener(this);
		
		add(this.checkEarn);
		
		this.exit.setBounds(350, 100, 200, 75);
		this.checkEarn.setFont(new Font("",Font.BOLD, 15));
		this.exit.setText("관리자 모드 나가기");
		this.exit.addActionListener(this);
		
		add(this.exit);
		
		this.label.setBounds(100, 225, 400, 75);
		this.label.setFont(new Font("", Font.BOLD, 20));
		this.label.setText("품목별 매출 내역");
		this.label.setHorizontalAlignment(JLabel.CENTER);
		
		add(this.label);
		
		setTable();
	}
	
	private void setTable() {
		Vector<String> colName = new Vector<>();
		colName.add("Name");
		colName.add("Quantity");
		colName.add("Price");
		
		this.table = new JTable(sold, colName);
		this.table.setBounds(100, 300, 400, 450);
		
		JScrollPane sp = new JScrollPane(this.table);
		sp.setBounds(100, 300, 400, 450);
		
		add(sp);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.checkEarn) {
			JOptionPane.showMessageDialog(null, String.valueOf(earn + "원 벌었습니다!"));
		}
		else if(e.getSource() == this.exit) {
			KioskAdminFrame.kaf.dispose();
		}
		else if(e.getSource() == this.setItems) {
			KioskAdminFrame.kaf.setContentPane(KioskAdminFrame.kaf.karp = new KioskAdminRemainPanel());
		}
		
	}
}
