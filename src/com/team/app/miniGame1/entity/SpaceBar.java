package com.team.app.miniGame1.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.team.app.entity.Entity;
import com.team.app.miniGame1.canvas.MiniGame1Canvas;

public class SpaceBar extends Entity {
	private static final int SPACEBARKEY = KeyEvent.VK_SPACE;
	
	private Image spaceImg; //�����̽� �̹���
	private int imgTempo = 30; //�̹��� ���� �ӵ����� �ε��� 
	private int imgIndex; //�̹��� ������ ���� �ε���
	
	private SpaceBarListener spaceBarListener;
	
	//�������̽�
	public void setPressListener(SpaceBarListener spaceBarListener) {
		this.spaceBarListener = spaceBarListener;
	}
	
	//������
	public SpaceBar() {
		this(130, 550);
	}
	
	
	public SpaceBar(int x, int y) {
		this.setX(x);
		this.setY(y);
		
		try {
			spaceImg = ImageIO.read(new File("res/spaceBar.png"));//����� �̹��� �����б�
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setWidth(spaceImg.getWidth(MiniGame1Canvas.instance));
		this.setHeight(spaceImg.getHeight(MiniGame1Canvas.instance));
	
	}


	@Override
	public void update() {
		
	}

	@Override
	public void paint(Graphics g) {
		if(imgTempo == 0) {
			imgIndex++;
			imgIndex = imgIndex % 2;
			
			imgTempo = 20;
		} else {
			imgTempo--;
		}
				
		
		int imgW = (int)this.getWidth()/2;
		int imgH = (int)this.getHeight();
		
		
	
		int dx1 = (int)this.getX();
		int dy1 = (int)this.getY();

		
		int dx2 = (int)this.getX() + imgW/2;
		int dy2 = (int)this.getY() + imgH/2;
		
		int sx1 = imgW * imgIndex;
		int sx2 = imgW * imgIndex + imgW;
		
		if(this.isActive() && this.isVisible()) {
			g.drawImage(spaceImg, dx1, dy1, dx2, dy2,
					sx1, 0, sx2, imgH,
					MiniGame1Canvas.instance);
			
			//g.fillRect(dx1, dy1, (int)imgW, (int)imgH);
			/*
			 dx1, dy1: ���ȭ�� ������, dx2, dy2: ���ȭ�� ��������
			 sx1, sy1: ���� ������ ������, sx2, sy2: ���� ������ ��������
				
			 */
		}
	}

	
	public void spacePush(int key) {
		if(key==SPACEBARKEY && this.isActive() && this.isVisible()) {
			spaceBarListener.onPress();
			//this.setActive(false);
			//this.setVisible(false);
			//�ߵ�
		}

	}
}