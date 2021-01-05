package com.team.app.miniGame1.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.team.app.entity.Entity;
import com.team.app.miniGame1.canvas.MiniGame1Canvas;

public class Background extends Entity {

	private Image img;
	private Image[] twinkles = new Image[3];
	
//	private BackgroundListener backgroundListener;
//
//	public void setFinishImageListener(BackgroundListener backgroundListener) {
//		this.backgroundListener = backgroundListener;
//	}

	// ï§â‘¤ë±? ï¿½ì” èª˜ëª„ï¿½ï¿½?“½ ï¿½ë–†ï¿½ì˜‰,ï¿½ê±¹ ï¿½ìç§»ï¿½
//	private int dx1;
//	private int dy1;
//	private int dx2;
//	private int dy2;

	// å«„ê³—ï¿½ï§ï¿?
//	private static int sWidth = 255;
//	private static int sHeight = 255;

	// æºë¶¾ê±«ï§ï¿? å¯ƒï¿½ dx,dy
//	private static int fWidth = 700;// 331;//ï¿½ëµ¾ï¿½ë•²ï¿½ë© ï¿½ê¶—ï§ê¾©?“½ ï¿½ê¼«?®ê¾©ì” ï¿½ì˜„ ï§â‘¤ë±? dx2 ?†«?š°ëª?
//	private static int fHeight = 700;// 510;//ï¿½ëµ¾ï¿½ë•²ï¿½ë© ï¿½ê¶—ï§ê¾©?“½ ï¿½ë„‚ï¿½ì” ï¿½ì” ï¿½ì˜„ ï§â‘¤ë±? dy2 ?†«?š°ëª?
	// 700,700 ï¿½ì‘æ¿¡ï¿½ dx,dy ï¿½ì˜Ÿ?¨ï¿½ ï¿½ì” èª˜ëª„ï¿½ï¿½?’— ?†«?š°ëª´ï¿½ë¾¾ï¿½?”  ï¿½ì‚¤?”±?Šï¿½ï¿½ê¼¸æ¿¡ï¿½ åª›ï¿½ï¿½ì¡‡ï¿½ë–ï¿½ëï¿½ë’—ï¿½ëœ² ï¿½ì†¢ ï§ã…»?”ï§ï¿½?

	private Boolean finish = false;

	public Boolean getFinish() {
		return finish;
	}

	public void setFinish(Boolean finish) {
		this.finish = finish;
	}

	public Background() {
		this(0, 0, 0, 0, false);
	}

	public Background(double x, double y, double w, double h, Boolean finish) {
		this.finish = finish;
		setX(x);
		setY(y);
		setWidth(w);
		setHeight(h);
		// ï¿½ë–†ï¿½ì˜‰ï¿½ì” èª˜ëª„ï¿?
		try {
			img = ImageIO.read(new File("res/game1/startBackground.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void paint(Graphics g) {

		// ï¿½ëª¢ ï¿½ì” èª˜ëª„ï¿½ï¿½?“½ ?†«?š°ëª´åª›ï¿? åª›ìˆ‡ê½?? ï¿½ë¿ï¿½ë–šï¿½ë–šï¿½ì“½ x,y?‘œï¿? ï¿½ì” ï¿½ìŠœï¿½ë¸¯ï¿½ì˜„
		// ï¿½ì” èª˜ëª„ï¿½ï¿½ë±¾ï¿½?“½ ?†«?š°ëª?
		int dx = (int) getX();// 0
		int dy = (int) getY();// 0

		int w = (int) getWidth();
		int h = (int) getHeight();

		if (finish) {
			changeImage();
			// ï¿½ì” èª˜ëª„ï¿?. x?†«?š°ëª?,y?†«?š°ëª?, ï¿½ê¼«?®ï¿?,ï¿½ë„‚ï¿½ì” 
			g.drawImage(img, dx, dy, w, h, MiniGame1Canvas.instance);
		} else
			g.drawImage(img, dx, dy, w, h, MiniGame1Canvas.instance);
	}

	public void changeImage() {
		try {
			img = ImageIO.read(new File("res/game1/finishBackground.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}
}
