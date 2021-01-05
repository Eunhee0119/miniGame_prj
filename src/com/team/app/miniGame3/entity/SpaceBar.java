package com.team.app.miniGame3.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.team.app.entity.SoundEffect;
import com.team.app.miniGame3.canvas.MiniGame3Canvas;

public class SpaceBar extends Entity {
	private static final int SPACEBARKEY = 32;
	
	private Image spaceImg; //�����̽� �̹���
	private int imgTempo = 30; //�̹��� ���� �ӵ����� �ε��� 
	private int imgIndex; //�̹��� ������ ���� �ε���
	
	private Clip clip; //���� Ŭ��
	
	private SpaceBarListener spaceBarListener;
	
	//�������̽�
	public void setPressListener(SpaceBarListener spaceBarListener) {
		this.spaceBarListener = spaceBarListener;
	}
	
	
	//ȿ����
	public void playSound(String pathName, boolean isLoop) {
		try {
			clip = AudioSystem.getClip();
			File audioFile = new File(pathName);
			AudioInputStream ais = AudioSystem.getAudioInputStream(audioFile);
			clip.open(ais);
			clip.start();
			if(isLoop)
				clip.loop(Clip.LOOP_CONTINUOUSLY); //�Ҹ� ���ѹݺ�
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	//������
	public SpaceBar() {
		this(540, 600);
	}
	
	
	public SpaceBar(int x, int y) {
		this.setX(x);
		this.setY(y);
		
		try {
			spaceImg = ImageIO.read(new File("res/spaceBar.png"));//����� �̹��� �����б�
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.setWidth(spaceImg.getWidth(MiniGame3Canvas.instance));
		this.setHeight(spaceImg.getHeight(MiniGame3Canvas.instance));
	
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
		}
		else {
			imgTempo--;
		}
				
		
		int imgW = (int)this.getWidth()/2;
		int imgH = (int)this.getHeight();
		
		
	
		int dx1 = (int)this.getX();
		int dy1 = (int)this.getY();

		
		int dx2 = (int)this.getX() + imgW;
		int dy2 = (int)this.getY() + imgH;
		
		int sx1 = imgW * imgIndex;
		int sx2 = imgW * imgIndex + imgW;
		
		if(this.isActive() && this.isVisible()) {
			g.drawImage(spaceImg, dx1, dy1, dx2, dy2,
					sx1, 0, sx2, imgH,
					MiniGame3Canvas.instance);
			
			//g.fillRect(dx1, dy1, (int)imgW, (int)imgH);
			/*
			 dx1, dy1: ���ȭ�� ������, dx2, dy2: ���ȭ�� ��������
			 sx1, sy1: ���� ������ ������, sx2, sy2: ���� ������ ��������
				
			 */
		}
	}
	
	
	public void spacePush() {
		if(this.isActive() && this.isVisible()) {
			spaceBarListener.onPress();
			SoundEffect.play("audio/finish.wav", false); //����� ����
			this.setActive(false);
			this.setVisible(false);
		}

	}
}
