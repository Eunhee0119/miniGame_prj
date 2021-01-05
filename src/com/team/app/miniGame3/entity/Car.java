package com.team.app.miniGame3.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.team.app.GameFrame;
import com.team.app.miniGame3.canvas.MiniGame3Canvas;

public class Car extends Entity {
	private static Image img; //�ڵ��� �̹���
	private double speed; //�ӵ�
	
	private double size; //�̹��� ũ�� ������
	private int lineIndex;
	private int imgIndex;
	
	
	// ==============================
	// ���� ����
//	private int sizeTempo;
//	private int speedTempo;
	// ==============================
	
	//������ġ
	// ==============================
	// Entity x, y�� �浹�Ͼ��
//	private double x;
//	private double y;
	// ==============================
	
	//��������
	private double dx; 
	private double dy;
	
	//�ӵ�
	private double vx; 
	private double vy;
	private CarListener carListener;
	
	public void setListener (CarListener carListener) {
		this.carListener = carListener;
	}
	
	static {
		try {
			img = ImageIO.read(new File("res/game3/car.png"));//����� �̹��� �����б�
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Car() {
		this(1, 1);
	}
	
	public Car(int lineIndex, int imgIndex) { //x: ��ġ��ȣ(1~3), imgIndex:�̹��� ��ȣ
		//System.out.println("������");
		this.size = 40; //ó�� ���� ������
		this.imgIndex = imgIndex;
		this.lineIndex = lineIndex;
		
		switch(lineIndex) {
			case 1:
				this.setX(575);//�ӽ� ��ġ
				this.dx = 295; //�������� ����
				this.dy = 900;
				break;
			case 2:
				this.setX(760);
				this.dx = 760;
				this.dy = 900; 
				break;
			case 3:
				this.setX(925);
				this.dx = 1220;
				this.dy = 900;
				
				break;
		}
		// ==============================
		// �� �պκ� ���� ���̰�
		setY(-100);//y��ǥ ����
		// ==============================
		
		this.setWidth(img.getWidth(null));
		this.setHeight(img.getHeight(null));
	}
	
	public void remove() {
		this.setY(10000);
		this.setActive(false);
		this.setVisible(false);
	}
	
	@Override
	public void update() {
		if(this.isVisible() && this.isActive()) {

			// ===================================
			// ������ ��ġ���̴� �������ּ���~~
			speed *= 1.05;
			size += 0.29*speed;

			double x = getX();
			double y = getY();

			// ===================================
			// �����ӳ��� �Ѿ�� ��Ȱ��
			if( y < GameFrame.frameHeight ) {
			// ===================================
				double w = this.dx - x;
				double h = this.dy - y;
				double d = Math.sqrt(w*w + h*h);
				this.vx = w/d*speed;
				this.vy = h/d*speed;
				x+=vx;
				y+=vy;
				
				setX(x);
				setY(y);
			} else {
			
				setActive(false);
				setVisible(false);
				// System.out.printf("x: %5.2f, y: %5.2f, vx: %5.2f, vy: %5.2f\n", x, y, vx, vy);
				// ===================================
			}
			
		}
		
		
		if(carListener != null  &&  MiniGame3Canvas.instance.getHeight() < this.getY()) {
			carListener.onOut(this);
			//System.out.println("���ڴ� ������ ����");
		}
				
	}
	


	@Override
	public void paint(Graphics g) {
		if(this.isVisible() && this.isActive()) {	
			int w = (int)getWidth() / 4;
			int h = (int)getHeight() / 3;
			
			int sx1 = w * (imgIndex-1);
			int sy1 = h * (lineIndex-1);
			int sx2 = w * (imgIndex-1) + w; 
			int sy2 = h * (lineIndex-1) + h;		
			
			int x = (int)getX();
			int y = (int)getY();
			
			
			/*
			int size = (int)this.size;
			int x1 = x+size/2;
			int y1 = y+size;
			int x2 = x1+size;
			int y2 = y1+size;
			*/
			
			int size = (int)this.size;
			int x1 = x-size/2;
			int y1 = y-size/2;
			int x2 = x1+size;
			int y2 = y1+size;			
			
			/*
			 * g.drawImage(img, , dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer); 
			 * dx1, dy1: ���ȭ�� ������, dx2, dy2: ���ȭ�� ��������
			 * sx1, sy1: ���� ������ ������, sx2, sy2: ���� ������ ��������
			 */
			/*
			
			g.drawImage(img, x1, y1 , x2 , y2,
					sx1, 0, sx2, h, Game2Canvas.instance);
			*/
			g.drawImage(img, x1, y1 , x2 , y2,
					sx1, sy1, sx2, sy2, MiniGame3Canvas.instance);
			
		}
		
	}
	
	public double getSize() {
		return size;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void setSize(double size) {
		this.size = size;
	}

	public double getDx() {
		return dx;
	}

	public void setDx(double dx) {
		this.dx = dx;
	}

	public double getDy() {
		return dy;
	}

	public void setDy(double dy) {
		this.dy = dy;
	}

	public double getVx() {
		return vx;
	}

	public void setVx(double vx) {
		this.vx = vx;
	}

	public double getVy() {
		return vy;
	}

	public void setVy(double vy) {
		this.vy = vy;
	}
	
	
	

	
}
