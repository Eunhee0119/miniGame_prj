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

	// 紐⑤�? �씠誘몄��?�� �떆�옉,�걹 �쐞移�
//	private int dx1;
//	private int dy1;
//	private int dx2;
//	private int dy2;

	// 嫄곗�吏�?
//	private static int sWidth = 255;
//	private static int sHeight = 255;

	// 源붾걫吏�? 寃� dx,dy
//	private static int fWidth = 700;// 331;//�뵾�땲�돩 �궗吏꾩?�� �꼫?��꾩씠�옄 紐⑤�? dx2 ?��?���?
//	private static int fHeight = 700;// 510;//�뵾�땲�돩 �궗吏꾩?�� �넂�씠�씠�옄 紐⑤�? dy2 ?��?���?
	// 700,700 �쑝濡� dx,dy �옟?�� �씠誘몄��?�� ?��?��몴�뾾�?�� �삤?��?���꼸濡� 媛��졇�떎�띁�뒗�뜲 �솢 吏ㅻ?��吏�?

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
		// �떆�옉�씠誘몄�?
		try {
			img = ImageIO.read(new File("res/game1/startBackground.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void paint(Graphics g) {

		// �몢 �씠誘몄��?�� ?��?��몴媛�? 媛숇�?? �뿏�떚�떚�쓽 x,y?���? �씠�슜�븯�옄
		// �씠誘몄��뱾�?�� ?��?���?
		int dx = (int) getX();// 0
		int dy = (int) getY();// 0

		int w = (int) getWidth();
		int h = (int) getHeight();

		if (finish) {
			changeImage();
			// �씠誘몄�?. x?��?���?,y?��?���?, �꼫?���?,�넂�씠
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
