package kiosk;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

public class KioskOrderPanel extends Utils{
	
	public static Vector<Vector<String>> receipt = new Vector<>();
	private JTable table;
	
	public static int yourPay = 0;
	
	public static KioskPayFrame kpf;

	private boolean menu = true;
	
	private JButton burger = new JButton();
	private JButton side = new JButton();
	private JButton pay = new JButton();
	private JButton initialize = new JButton();
	
	private final int NAME = 0;
	private final int QUANTITY = 1;
	private final int PRICE = 2;
	
	private final int DISPLAY = 12;
	
	private KioskImage[] burgers = new KioskImage[DISPLAY];
	private KioskImage[] sides = new KioskImage[DISPLAY];
	
	public KioskOrderPanel() {
		setLayout(null);
		setBounds(0, 0, 600, 800);
		
		setButtons();
		generateArrays();
		generateReceipt();
		
		addMouseListener(this);
	}
	
	private void generateReceipt() {
		Vector<String> colName = new Vector<>();
		colName.add("MenuName");
		colName.add("Quantity");
		colName.add("Price");
		
		this.table = new JTable(receipt, colName);
		
		this.table.setBounds(50, DISPLAY, DISPLAY, DISPLAY);
		this.table.setBorder(new LineBorder(Color.orange));
		this.table.setGridColor(Color.black);
		
		JScrollPane sp = new JScrollPane(this.table);	// 스크롤을 달아줄 친구
		sp.setBounds(50, 550, 500, 175);
		
		add(sp);
	}
	
	private void generateArrays() {
		int x = 30;
		int y = 100;
		
		for(int i = 0; i<this.DISPLAY; i++) {
			this.burgers[i] = new KioskImage(x, y, "borgar", i);
			this.sides[i] = new KioskImage(x, y, "side", i);
			
			x += 140;
			if(i % 4 == 3) {
				x = 30;
				y += 160;
			}
		}
	}

	private void setButtons() {
		this.burger.setBounds(200, 20, 75, 30);
		this.burger.setText("Burger");
		this.burger.addActionListener(this);
		
		this.side.setBounds(325, 20, 75, 30);
		this.side.setText("Side");
		this.side.addActionListener(this);
		
		this.pay.setBounds(450, 20, 100, 50);
		this.pay.setBackground(Color.pink);
		this.pay.setText("결제하기");
		this.pay.addActionListener(this);
		
		this.initialize.setBounds(20, 60, 100, 30);
		this.initialize.setText("초기화");
		this.initialize.addActionListener(this);
		
		add(this.burger);
		add(this.side);
		add(this.pay);
		add(this.initialize);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int sum = 0;
		for(int i = 0; i<receipt.size(); i++) {
			sum += Integer.parseInt(receipt.get(i).get(PRICE));
		}
		yourPay = sum;
		
		g.setFont(new Font("", Font.BOLD, 20));
		g.drawString("총 "+String.valueOf(sum)+"원", 30, 40);
		
		g.setFont(new Font("", Font.PLAIN, 13));
		int x = 30;
		int y = 215;
		for(int i = 0; i<this.DISPLAY; i++) {
				
			if(this.menu) {
				g.drawImage(this.burgers[i].getImg().getImage(),
						this.burgers[i].getX(), this.burgers[i].getY(), null);
				
				if(Integer.parseInt(KioskAdminRemainPanel.adminVector.
						get(i).get(QUANTITY)) == 0) {
					ImageIcon temp = new ImageIcon(new ImageIcon("images/X.png").
							getImage().getScaledInstance(120, 100, Image.SCALE_SMOOTH));
					g.drawImage(temp.getImage(),
							this.burgers[i].getX(), this.burgers[i].getY(), null);
				}
			}
			else {
				g.drawImage(this.sides[i].getImg().getImage(),
						this.sides[i].getX(), this.sides[i].getY(), null);

				if(Integer.parseInt(KioskAdminRemainPanel.adminVector.
						get(i + 12).get(QUANTITY)) == 0) {
					ImageIcon temp = new ImageIcon(new ImageIcon("images/X.png").
							getImage().getScaledInstance(120, 100, Image.SCALE_SMOOTH));
					g.drawImage(temp.getImage(),
							this.sides[i].getX(), this.sides[i].getY(), null);
				}
			}
			
			if(this.menu) {
				g.drawString(KioskAdminRemainPanel.adminVector.get(i).get(NAME), x, y);
				y += 15;
				g.drawString(String.valueOf(KioskAdminRemainPanel.adminVector.get(i).get(PRICE)
						+"원")+ "  " + Integer.parseInt(KioskAdminRemainPanel.adminVector.
								get(i).get(QUANTITY)) + "개 남음", x, y);
				y -= 15;	// 잠깐 내려서 가격을 적고 다시 위로
			}
			else {
				g.drawString(KioskAdminRemainPanel.adminVector.get(i + 12).get(NAME) , x, y);
				y += 15;
				g.drawString(String.valueOf(KioskAdminRemainPanel.adminVector.get(i + 12)
						.get(PRICE)+"원") + "  " + Integer.parseInt(KioskAdminRemainPanel.
								adminVector.get(i + 12).get(QUANTITY))+ "개 남음", x, y);
				y -= 15;	// 잠깐 내려서 가격을 적고 다시 위로
			}
			
			x += 140;
			if(i % 4 == 3) {
				x = 30;
				y += 160;
			}
		}
		
		if(this.kpf != null && !KioskPlacePanel.ordering) this.kpf.dispose();
		
		repaint();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.burger) {
			this.menu = true;
		}
		else if(e.getSource() == this.side) {
			this.menu = false;
		}
		else if(e.getSource() == this.pay) {
			if(yourPay <= 0) JOptionPane.showMessageDialog(null, "아무것도 고르지 않았습니다!");
			else this.kpf = new KioskPayFrame();
		}
		else if(e.getSource() == this.initialize) {
			yourPay = 0;
			for(int i = 0; i<receipt.size(); i++) {
				for(int j = 0; j<KioskAdminRemainPanel.adminVector.size(); j++) {
					if(KioskAdminRemainPanel.adminVector.get(j).get(NAME).
							equals(receipt.get(i).get(NAME))) {
						while(Integer.parseInt(receipt.get(i).get(QUANTITY)) >= 1) {
							// 주문 목록에선 하나 줄이고
							receipt.get(i).set(QUANTITY, String.valueOf(Integer.parseInt(
									receipt.get(i).get(QUANTITY))-1));
							
							// 재고는 올리고
							KioskAdminRemainPanel.adminVector.get(j).set(QUANTITY, 
									String.valueOf(Integer.parseInt(
											KioskAdminRemainPanel.adminVector.get(j).get(QUANTITY))+1));
						}
					}
				}
			}
			
			// 다했으면 새걸로 바꾸고
			receipt.clear();	// 벡터를 완전히 비워버림
			// 새집가서 아직도 헌집을 참조중이다
			
//			KioskFrame.kf.setContentPane(KioskFrame.kf.kop = new KioskOrderPanel());
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		
		if(this.menu) {
			for(int i = 0; i<DISPLAY; i++) {
				if(x >= this.burgers[i].getX() &&
						x <= this.burgers[i].getX()+this.burgers[i].getW() &&
						y >= this.burgers[i].getY() &&
						y <= this.burgers[i].getY()+this.burgers[i].getH()) {
					if(Integer.parseInt(KioskAdminRemainPanel.adminVector.
							get(i).get(QUANTITY)) >= 1) {
						Vector<String> temp = new Vector<>();
						temp.add(KioskAdminRemainPanel.adminVector.get(i).get(NAME));
						temp.add(String.valueOf(1));
						temp.add(String.valueOf(KioskAdminRemainPanel.adminVector.get(i).get(PRICE)));
						
						boolean found = false;
						for(int j = 0; j<receipt.size(); j++) {
							// 이미 있다면 수량만 하나 추가
							if(receipt.get(j).get(NAME).equals(temp.get(NAME))) {
								receipt.get(j).set(QUANTITY,String.valueOf(
										Integer.parseInt(receipt.get(j).get(QUANTITY))+1));
								
								receipt.get(j).set(PRICE, String.valueOf(
										Integer.parseInt(receipt.get(j).get(PRICE))+
										Integer.parseInt(temp.get(PRICE))));
								found = true;
							}
						}
						if(!found) receipt.add(temp);
						
						KioskAdminRemainPanel.adminVector.get(i).set(
								QUANTITY, String.valueOf(Integer.parseInt(
								KioskAdminRemainPanel.adminVector.get(i).get(QUANTITY)) - 1));
						
					}
					else {
						JOptionPane.showMessageDialog(null, "재고가 없습니다!");
					}
				}
			}
		}
		else {
			for(int i = 0; i<DISPLAY; i++) {
				if(x >= this.sides[i].getX() &&
						x <= this.sides[i].getX()+this.sides[i].getW() &&
						y >= this.sides[i].getY() &&
						y <= this.sides[i].getY()+this.sides[i].getH()) {
					if(Integer.parseInt(KioskAdminRemainPanel.adminVector.
							get(i + 12).get(QUANTITY)) >= 1) {
						Vector<String> temp = new Vector<>();
						temp.add(String.valueOf(KioskAdminRemainPanel.adminVector.get(i+12).get(NAME)));
						temp.add(String.valueOf(1));
						temp.add(String.valueOf(KioskAdminRemainPanel.adminVector.get(i+12).get(PRICE)));
						
						boolean found = false;
						for(int j = 0; j<receipt.size(); j++) {
							// 이미 있다면 수량만 하나 추가
							if(receipt.get(j).get(NAME).equals(temp.get(NAME))) {
								receipt.get(j).set(QUANTITY,String.valueOf(
										Integer.parseInt(receipt.get(j).get(QUANTITY))+1));
								
								receipt.get(j).set(PRICE, String.valueOf(
										Integer.parseInt(receipt.get(j).get(PRICE))+
										Integer.parseInt(temp.get(PRICE))));
								found = true;
							}
						}
						if(!found) receipt.add(temp);
						KioskAdminRemainPanel.adminVector.get(i + 12).set(
								QUANTITY, String.valueOf(Integer.parseInt(
								KioskAdminRemainPanel.adminVector.get(i + 12).get(QUANTITY)) - 1));
					}
					else {
						JOptionPane.showMessageDialog(null, "재고가 없습니다!");
					}
				}
			}
		}
		
		this.table.revalidate();
	}
}
