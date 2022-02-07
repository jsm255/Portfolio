package kiosk;

import java.awt.Image;

import javax.swing.ImageIcon;

public class KioskImage {
	
	private int x,y,w,h;
	private ImageIcon img;
	
	public KioskImage(int x, int y, String str, int idx) {
		this.x = x;
		this.y = y;
		this.w = 120;
		this.h = 100;
		this.img = new ImageIcon(new ImageIcon(String.valueOf("images/"+str+(idx+1)+".png")).
				getImage().getScaledInstance(this.w, this.h, Image.SCALE_SMOOTH));
	}

	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getW() {
		return w;
	}
	
	public int getH() {
		return h;
	}

	public ImageIcon getImg() {
		return img;
	}
	
}
