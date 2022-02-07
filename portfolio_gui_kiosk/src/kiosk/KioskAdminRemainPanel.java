package kiosk;

import java.awt.Font;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class KioskAdminRemainPanel extends Utils {
	
	private final int NAME = 0;
	private final int QUANTITY = 1;
	private final int PRICE = 2;
	
	public static Vector<Vector<String>> adminVector = setVector();
	
	private JLabel label = new JLabel();
	
	private JButton button = new JButton();
	
	private JTable table;
	
	public KioskAdminRemainPanel() {
		setLayout(null);
		setBounds(0, 0, 600, 800);
		
		setPanel();
	}

	private void setPanel() {
		this.label.setText("재고 관리");
		this.label.setBounds(100, 50, 400, 50);
		this.label.setFont(new Font("", Font.BOLD, 20));
		this.label.setHorizontalAlignment(JLabel.CENTER);
		
		add(this.label);
		
		setVector();
		setTable();
	}

	private static Vector<Vector<String>> setVector() {
		String[] names = {"싸이버거","내슈빌핫치킨버거",
				"언빌리버블버거","디럭스불고기버거","휠렛버거","새우불고기버거","통새우버거","불고기버거",
				"싸이플렉스버거","텍사스바베큐치킨버거","에그불고기버거","딥치즈버거",
				"케이준양념감자","코울슬로","후라이드텐더2조각","할라피뇨너겟",
				"콘샐러드","치즈스틱2조각","콜라","사이다","레몬에이드","아메리카노","오렌지주스","초코라떼"};
		
		int[] prices = {3500, 4000, 5000, 3500, 3500, 3500, 4000, 2500, 6000,
				5000, 3200, 3800, 1500, 1200, 2000, 3000, 1500, 2000, 1300, 1300, 1500, 
				1500, 1100, 1700};
		
		Vector<Vector<String>> temp = new Vector<>();
		
		for(int i = 0; i<names.length; i++) {
			Vector<String> inside = new Vector<>();
			inside.add(names[i]);
			inside.add(String.valueOf(5));
			inside.add(String.valueOf(prices[i]));
			temp.add(inside);
		}
		
		return temp;
	}

	private void setTable() {
		Vector<String> colName = new Vector<>();
		colName.add("Name");
		colName.add("Quantity");
		colName.add("Price");
		this.table = new JTable(this.adminVector, colName);
		
		JScrollPane sp = new JScrollPane(this.table);
		
		sp.setBounds(50, 100, 500, 500);
		add(sp);
	}
}
