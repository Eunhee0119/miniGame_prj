package com.team.app.miniGame1.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.team.app.GameFrame;
import com.team.app.entity.Entity;
import com.team.app.entity.SoundEffect;
import com.team.app.miniGame1.canvas.MiniGame1Canvas;

public class Hand extends Entity{
	private static final int LEFT = KeyEvent.VK_LEFT;
	private static final int RIGHT = KeyEvent.VK_RIGHT;
	
	private Image img; //���̹���
	private Clip clip; //���� Ŭ��
	private int keyTempo;
	
	boolean direction = true; //�տ����ӹ���
	private double per;
	private HandMoveListener handMoveListener;
	
	
	
	//�������̽�
	public void setMoveListener(HandMoveListener handMoveListener) {
		this.handMoveListener = handMoveListener;
	}
	
	//������(�ʱ�ȭ)
	public Hand() {
		this(200, 350);
		
	}
	//������2(�ʱ�ȭ)
	public Hand(int x, int y) {
		
		this.setX(x);
		this.setY(y);
		
		try {
			img = ImageIO.read(new File("res/game1/hand.png"));//����� �̹��� �����б�
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setWidth(img.getWidth(MiniGame1Canvas.instance));
		this.setHeight(img.getHeight(MiniGame1Canvas.instance));
		
//		this.setVisible(true);//Ȱ��ȭ
//		this.setActive(true);//������;
	}
	

	
//	//ȿ����
//	public void playSound(String pathName, boolean isLoop) {
//		try {
//			clip = AudioSystem.getClip();
//			File audioFile = new File(pathName);
//			AudioInputStream ais = AudioSystem.getAudioInputStream(audioFile);
//			clip.open(ais);
//			clip.start();
//			if(isLoop)
//				clip.loop(Clip.LOOP_CONTINUOUSLY); //�Ҹ� ���ѹݺ�
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (LineUnavailableException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (UnsupportedAudioFileException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
	
	//Ű������
	public void moveKey(int key) {
		
		if(key == LEFT ) {
			direction = false; //���ʹ������� ����
		}else if(key == RIGHT) {
			direction = true; //�����ʹ������� ����
		}
			
		moveHand();
	}
	
	//���Ǹ����� ������
	public void moveHand() {
		int MinX = 50; //��ġ x�� ���� �� �ִ� �ּҰ�
		int MaxX = GameFrame.frameWidth / GameFrame.totalPlayerNum - 150; //��ġ x�� ���� �� �ִ� �ִ�
		
		if(this.isActive()) {
			if(direction == true) {
				if(getX()+ 50 > MaxX) {
					return;
				}
				
				if(handMoveListener != null)
					handMoveListener.onMove("right");
				//������ ��������
				setX(getX()+50);
				//setY(getY()-10);
		

			}
			
			if(direction == false) {
				if(getX()-50 < MinX) {
					return;
				}

				if(handMoveListener != null)
					handMoveListener.onMove("left");
				//���ʹ�������
				setX(getX()-50);
				//setY(getY()+10);
			}
			SoundEffect.play("audio/handSound.wav", false); //����� ����
		}
		if(per >= MiniGame1Canvas.MAX_PUSH) {
			this.setActive(false); //�Ⱥ���
			this.setActive(false); //Ȱ��ȭ ����	
		}
		 
	}
	
	
	public void update() {
	}
	
	public void paint(Graphics g) {
		if(this.isActive() && this.isVisible()) {
			g.drawImage(img, (int)getX(), (int)getY(), MiniGame1Canvas.instance);
		}
	}
}