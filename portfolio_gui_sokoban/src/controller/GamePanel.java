package controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class GamePanel extends Utility{
	
	private Random ran = new Random();
	
	private JButton reset = new JButton();
	private JLabel label = new JLabel();
	
	private final int EMPTY = 0;
	private final int WALL = 1;
	private final int BOX = 5;
	private final int GOAL = 3;
	private final int PLAYER = 7;
	private final int ARRIVED = 8;
	
	private final int SIZE = 8;
	private final int BLOCK = 50;
	
	private int stage = 1;
	
	private int goalCnt = 7;
	private int startPY = 2;
	private int startPX = 2;
	private int pY = 2;
	private int pX = 2;
	
	private boolean gameClear = false;
	private boolean popUp = false;
	
	private ImageIcon boxImage = new ImageIcon(new ImageIcon("image/box.png").
			getImage().getScaledInstance(BLOCK, BLOCK, Image.SCALE_SMOOTH));
	private ImageIcon guyImage = new ImageIcon(new ImageIcon("image/guy.png").
			getImage().getScaledInstance(BLOCK, BLOCK, Image.SCALE_SMOOTH));
	private ImageIcon arrivedImage = new ImageIcon(new ImageIcon("image/arrivedBox.png").
			getImage().getScaledInstance(BLOCK, BLOCK, Image.SCALE_SMOOTH));
	
	private int[][] map = {{EMPTY,EMPTY,WALL,WALL,WALL,WALL,WALL,EMPTY},
							{WALL,WALL,WALL,EMPTY,EMPTY,EMPTY,WALL,EMPTY},
							{WALL,GOAL,PLAYER,BOX,EMPTY,EMPTY,WALL,EMPTY},
							{WALL,WALL,WALL,EMPTY,BOX,GOAL,WALL,EMPTY},
							{WALL,GOAL,WALL,WALL,BOX,EMPTY,WALL,EMPTY},
							{WALL,EMPTY,WALL,EMPTY,GOAL,EMPTY,WALL,WALL},
							{WALL,BOX,EMPTY,ARRIVED,BOX,BOX,GOAL,WALL},
							{WALL,EMPTY,EMPTY,EMPTY,GOAL,EMPTY,EMPTY,WALL}};
	
	private int[][] mapTemp = new int[SIZE][SIZE];
	
	private int[][] goalMemo = {{2,1},{3,5},{4,1},{5,4},{6,3},{6,6},{7,4}};
			
	public GamePanel() {
		setLayout(null);
		setBounds(0, 0, 800, 700);
		
		tempMap();
		makeButtonAndLabel();
		
		setFocusable(true);
		addKeyListener(this);
		
	}

	private void makeButtonAndLabel() {
		this.reset.setText("Restart(r)");
		this.reset.setBounds(100, 50, 100, 75);
		this.reset.addActionListener(this);
		
		add(this.reset);
		
		this.label.setBounds(300, 25, 200, 100);
		this.label.setHorizontalAlignment(JLabel.CENTER);
		this.label.setVerticalAlignment(JLabel.CENTER);
		this.label.setFont(new Font("",Font.BOLD,30));
		this.label.setText("SOKOBAN");
		
		add(this.label);
	}

	private void tempMap() {	// ???????????? ?????? ??????
		for(int i = 0; i<SIZE; i++) {
			for(int j = 0; j<SIZE; j++) {
				this.mapTemp[i][j] = this.map[i][j];
			}
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(this.stage == 1) checkMapForStage1();
		else checkMap();
		if(this.gameClear && this.popUp) {
			new FinishFrame();
			this.stage ++;
			randomStageGenerator();
		}
		
		g.setFont(new Font("",Font.BOLD, 20));
		g.drawString(String.valueOf("Stage "+this.stage), 600, 75);
		
		int x = 50;
		int y = 150;
		for(int i = 0; i<this.SIZE; i++) {
			for(int j = 0; j<this.SIZE; j++) {
				if(this.map[i][j] == EMPTY) {
					g.setColor(Color.white);
					g.fillRect(x, y, BLOCK, BLOCK);
				}
				else if(this.map[i][j] == WALL) {
					g.setColor(Color.DARK_GRAY);
					g.fillRect(x, y, BLOCK, BLOCK);
				}
				else if(this.map[i][j] == BOX) {
					g.drawImage(this.boxImage.getImage(), x, y, null);
				}
				else if(this.map[i][j] == PLAYER) {
					g.drawImage(this.guyImage.getImage(), x, y, null);
				}
				else if(this.map[i][j] == GOAL) {
					g.setColor(Color.white);
					g.fillRect(x, y, BLOCK, BLOCK);
					g.setColor(Color.red);
					g.fillRoundRect(x+15, y+15, 20, 20, 20, 20);
				}
				else if(this.map[i][j] == ARRIVED) {
					g.drawImage(this.arrivedImage.getImage(), x, y, null);
				}
				
				x += 50;
			}
			x = 50;
			y += 50;
		}
		requestFocusInWindow();
		repaint();
	}
	
	private void checkMap() {
		int arriveCnt = 0;
		for(int i = 0; i<this.goalMemo.length; i++) {
			int goalY = this.goalMemo[i][0];
			int goalX = this.goalMemo[i][1];
			
			if(this.map[goalY][goalX] == EMPTY) this.map[goalY][goalX] = GOAL;
			else if(this.map[goalY][goalX] == ARRIVED) arriveCnt ++;
		}
		
		if(arriveCnt == this.goalCnt) {
			if(!this.gameClear && !this.popUp) {
				this.gameClear = true;
				this.popUp = true;
			}
		}
	}

	private void randomStageGenerator() {
		this.map = new int[SIZE][SIZE];
		
		int boxs = ran.nextInt(3)+4;
		int goals = boxs;
		int walls = ran.nextInt(2)+4;
		for(int i = 0; i<walls; i++) {
			int ranY = ran.nextInt(SIZE);
			int ranX = ran.nextInt(SIZE);
			
			if(this.map[ranY][ranX] == EMPTY) {	// ??? ??????
				int wallCnt = 0;		// ?????? ??????????????? ????????? ??? ???????????? ????????? ????????? ????????? ?????????
				for(int j = -1; j<=1; j++) {
					if(ranY + j < 0 || ranY + j > SIZE - 1) {}
					else {
						if(this.map[ranY+j][ranX] == WALL) wallCnt ++;
					}
					if(ranX + j < 0 || ranX + j > SIZE - 1) {}
					else {
						if(this.map[ranY][ranX+j] == WALL) wallCnt ++;
					}
				}
				if(wallCnt >= 1) i--;
				else this.map[ranY][ranX] = WALL;
			}
			else i --;
		}
		
		for(int i = 0; i<boxs; i++) {	// ?????? ??????
			int ranY = ran.nextInt(SIZE-2) + 1;	// ????????? ?????? ??????????????????
			int ranX = ran.nextInt(SIZE-2) + 1;
			
			if(this.map[ranY][ranX] == EMPTY) {
				int wallCnt = 0;	// ????????? ????????? ????????? ??? ??? ?????? ????????? ???????????? ??? ??? ?????? ??????
				for(int j = -1; j<=1; j++) {
					if(this.map[ranY+j][ranX] == WALL || this.map[ranY+j][ranX] == BOX)
						wallCnt ++;
					if(this.map[ranY][ranX+j] == WALL || this.map[ranY+j][ranX] == BOX)
						wallCnt ++;
				}
				if(wallCnt >= 2) i--;
				else this.map[ranY][ranX] = BOX;
			}
			else i --;
		}
		
		this.goalMemo = new int[goals][2];	// ??? ??????
		this.goalCnt = goals;	// ?????? ??? ??? ??????
		for(int i = 0; i<goals; i++) {	// ??? ??????
			int ranY = ran.nextInt(SIZE);
			int ranX = ran.nextInt(SIZE);
			
			if(this.map[ranY][ranX] == EMPTY) this.map[ranY][ranX] = GOAL;
			else if(this.map[ranY][ranX] == BOX) this.map[ranY][ranX] = ARRIVED;
			else {
				i --;
				continue;
			}
			this.goalMemo[i][0] = ranY;
			this.goalMemo[i][1] = ranX;
		}
		
		while(true) {	// ???????????? ??????
			int ranY = ran.nextInt(SIZE);
			int ranX = ran.nextInt(SIZE);
			
			if(this.map[ranY][ranX] == EMPTY) {
				this.map[ranY][ranX] = PLAYER;
				this.pY = ranY;
				this.pX = ranX;
				this.startPY = ranY;
				this.startPX = ranX;
				break;
			}
		}
		
		this.gameClear = false;
		this.popUp = false;
		tempMap();
	}
	
	private void checkMapForStage1() {
		int arriveCnt = 0;
		for(int i = 0; i<this.goalMemo.length; i++) {
			int goalY = this.goalMemo[i][0];
			int goalX = this.goalMemo[i][1];
			
			if(this.map[goalY][goalX] == EMPTY) this.map[goalY][goalX] = GOAL;
			else if(this.map[goalY][goalX] == ARRIVED) arriveCnt ++;
		}
		
		if(arriveCnt == this.goalCnt) {
			if(!this.gameClear && !this.popUp) {
				this.gameClear = true;
				this.popUp = true;
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.reset) {
			resetGame();
		}
	}
	
	private void resetGame() {
		this.gameClear = false;
		this.popUp = false;
		
		this.pY = this.startPY;
		this.pX = this.startPX;
		
		for(int i = 0; i<this.SIZE; i++) {
			for(int j = 0; j<this.SIZE; j++) {
				this.map[i][j] = this.mapTemp[i][j];
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == e.VK_R) resetGame();
		else {
			
			int tempY = this.pY;
			int tempX = this.pX;
			
			if(e.getKeyCode() == e.VK_UP) tempY --;
			else if(e.getKeyCode() == e.VK_DOWN) tempY ++;
			else if(e.getKeyCode() == e.VK_LEFT) tempX --;
			else if(e.getKeyCode() == e.VK_RIGHT) tempX ++;
			
			// ??? ????????? ????????????
			if(tempY < 0 || tempY > this.SIZE-1 || tempX < 0 || tempX > this.SIZE-1) return;
			else {
				// ?????? ???????????????
				if(this.map[tempY][tempX] == WALL) return;
				
				// ?????? ????????? ?????????
				else if(this.map[tempY][tempX] == BOX || this.map[tempY][tempX] == ARRIVED) {
					
					// ????????? ????????? ?????? ????????? ??????????????? ??? ??? ?????????
					int boxTempY = tempY != this.pY ? tempY+(tempY-this.pY) : tempY;
					int boxTempX = tempX != this.pX ? tempX+(tempX-this.pX) : tempX;
					
					// ??? ????????? ?????? ????????? ?????????
					if(boxTempY < 0 || boxTempY > this.SIZE-1 ||
							boxTempX < 0 || boxTempX > this.SIZE-1) return;
					else {
						// ?????? ??? ????????? ?????? ??????????????? ??? ?????? ??? ????????? ?????????
						if(this.map[boxTempY][boxTempX] == WALL ||
								this.map[boxTempY][boxTempX] == BOX ||
								this.map[boxTempY][boxTempX] == ARRIVED) return;
						else {
							
							// ????????? ???????????? ?????????
							if(this.map[boxTempY][boxTempX] == GOAL)
								this.map[boxTempY][boxTempX] = ARRIVED;
							else {
								
								// ??? ????????????
								// ??? ????????? ????????? ????????? ??????????????? ????????? ???????????? ????????? ??????
								this.map[boxTempY][boxTempX] = BOX;
							}
						}
					}
				}
				
				this.map[this.pY][this.pX] = EMPTY;
				this.map[tempY][tempX] = PLAYER;
				this.pY = tempY;
				this.pX = tempX;
			}
		}
	}
}
