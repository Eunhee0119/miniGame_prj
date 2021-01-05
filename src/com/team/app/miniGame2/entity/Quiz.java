package com.team.app.miniGame2.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import com.team.app.entity.SoundEffect;
import com.team.app.miniGame2.canvas.MiniGame2Canvas;

public class Quiz extends Entity {

	private Random rand;

	// �ʱ� �迭
	private int round;
	private int min;// ù �迭�� ũ��
	private int max = 7;
	private int[][] quiz;
	private SoundEffect se;
	
	
	

	// check �ҋ� �̿��� ����
	private int quizRound = 0;
	private int quizNum = 0;
	private int keyCode; // �긦 �̿��ؼ� ���� �̹��� ����Ұž�

	private static Image img_punch;
	private static Image img_boom;

	private final int LEFT = 37;
	private final int UP = 38;
	private final int RIGHT = 39;
	private final int DOWN = 40;
	
	private boolean half;
	private boolean end;

	private boolean activeSound = false;
	

	private QuizListener quizListener;

	public void setQuizListener(QuizListener quizListener) {
		this.quizListener = quizListener;
	}

	static {
		try {
			img_punch = ImageIO.read(new File("res/game2/box_40.png"));
			img_boom = ImageIO.read(new File("res/game2/boom.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Quiz() {
		this(10, 10, 7, 4);
	}

	public Quiz(double x, double y, int round, int min) {
		setX(x);
		setY(y);
		this.round = round; // ���� 2 2
		this.min = min; // ���� ���� �� 4 4
		

		quiz = new int[round][];
		se = new SoundEffect();
		rand = new Random();
		for (int i = 0; i < round; i++) {
			quiz[i] = new int[min];
			for (int j = 0; j < min; j++) {

				quiz[i][j] = 37 + rand.nextInt(4); // [37][38][39][37]
													// [38][40][39][38][38]
			}
			min++;
		}
	}

	@Override
	public void update() {
		if (round == quizRound) {
			if(quizListener != null)
				quizListener.onEnd();
		}
	}

	@Override
	public void paint(Graphics g) {
		int width = 180;
		int height = 180;

		int dx1;
		int dy1 = 45+(int)getY();
		int dx2;
		int dy2 = height * 4/3+(int)getY();

		int sx1;
		int sx2;
		int sy1 = 0;
		int sy2 = height;

		//������尡 round/2 �� ����������
		//������尡 round-1 �̸� ��Ʈ��
		if (round != quizRound) {
			for (int j = 0; j < quiz[quizRound].length; j++) {// �̹� �迭�� ��������ְ� ���� �־����־� ��¸� �ϸ��
				int key = quiz[quizRound][j];

				dx1 = j * width /2+(int)getX(); //���ϴ� �ָ��� ũ�� + ���ϴ� ��ġ
				dx2 = (j + 1) * width /2+(int)getX();

				sx1 = width * (key - 37);
				sx2 = width * (key - 37) + width;


				switch (key) {
				case LEFT: // key-37 = 0,1,2,3
				case UP:
				case RIGHT:
				case DOWN:
					//System.out.printf("���繮�� �迭 : %d,%d,%d,%d\n", round, quizRound, quizNum, quiz[quizRound].length - 1);

					if (quizNum > j) {
						g.drawImage(img_boom, dx1, dy1, dx2, dy2, 0, 0, 700, 700, MiniGame2Canvas.instance);
					}
					else g.drawImage(img_punch, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, MiniGame2Canvas.instance);

					break;
				}
			}
		}
	}

	public boolean check(int keyCode) {
		// �ѻ���Ŭ�� �������� quizNum���� ���������ְ�
		// ���̻� �迭�� ���ٸ� quizRound�� ����
		this.keyCode = keyCode;
		if (quizRound < round) {
			if (quiz[quizRound][quizNum] == keyCode) { // 37 38 39 40

				quizNum++;// üũ�ؼ� ������ �������� �� ���� �÷����ϱ� �Ʒ��� ���̿��� -1�� ����
				if (quizNum == quiz[quizRound].length) {// ���� ������ ������ ������
					if (round != quizRound) {// ������ ���尡 �ƴϸ�
						quizRound++;
						if(activeSound) {
							if(quizRound == 1)
								SoundEffect.play("audio/round1.wav", false);
							if(quizRound == round/2)
								SoundEffect.play("audio/icandoit.wav", false);
							if(quizRound == round-1)
								SoundEffect.play("audio/lastone.wav",false) ;
//							if(quizRound == round)
//								SoundEffect.play("audio/end.wav", false);
						}
						quizNum = 0;
					} // ������ �����(round==quizRound) �ƹ��͵� ���� �̰� ����Ʈ���� �׸���
				}

				return true;
			}

			quizNum = 0;// Ʋ���� ó������
		}
		return false;
	}

	public void setQuiz(String line) {
		String[] temps = line.split("&");

		List<Integer> quizs = new ArrayList<>();

		quiz = new int[temps.length][];
		
		for ( int i = 0; i < temps.length; i++) {
			String[] temp = temps[i].split(":");
			quiz[i] = new int[temp.length];
			for( int j = 0; j < temp.length; j++) {
				quiz[i][j] = Integer.parseInt(temp[j]);
			}
			
		}

		round = temps.length;
		
	}

	public boolean isHalf() {
		return half;
	}

	public void setHalf(boolean half) {
		this.half = half;
	}

	public boolean isEnd() {
		return end;
	}

	public void setEnd(boolean end) {
		this.end = end;
	}

	public boolean isActiveSound() {
		return activeSound;
	}

	public void setActiveSound(boolean activeSound) {
		this.activeSound = activeSound;
	}
	
	
}

//package com.team.mini2.app.entity;
//
//import java.awt.Graphics;
//import java.awt.Image;
//import java.io.File;
//import java.io.IOException;
//import java.util.Random;
//
//import javax.imageio.ImageIO;
//
//import com.team.mini2.app.canvas.ActionCanvas;
//
//public class Quiz extends Entity {
//
//   private Random rand;
//
//   // �ʱ� �迭
//   private int num;
//   private int max;
//   private int min;// ù �迭�� ũ��
//   private int[][] quiz;
//   private int i = 0;
//   private int j = 0;
//
//   // check �ҋ� �̿��� ����
//   private int quizRound = 0;
//   private int quizNum = 0;
//
//   private static Image img_box;
//   private static Image img_boom;
//
//   final int LEFT = 37;
//   final int UP = 38;
//   final int RIGHT = 39;
//   final int DOWN = 40;
//
//   static {
//      try {
//         img_box = ImageIO.read(new File("res/box.png"));
//         img_boom = ImageIO.read(new File("res/boom.png"));
//      } catch (IOException e) {
//         // TODO Auto-generated catch block
//         e.printStackTrace();
//      }
//   }
//
//   public Quiz() {
//      this(0, 0, 4, 2, 10);
//   }
//
//   public Quiz(double x, double y, int num, int min, int max) {
//      setX(x);
//      setY(y);
//      this.num = num;
//      this.min = min;
//      this.max = max;
//      quiz = new int[num][];
//      rand = new Random();
//      for (int i = 0; i < num; i++) {
//         quiz[i]=new int[min];
//         for (int j = 0; j < min; j++) {
//            quiz[i][j] = 37 + rand.nextInt(4); // [37][38][39][37]
//                                       // [38][40][39][38][38]
//         }
//         min++;
//      }
//
//   }
//
//   @Override
//   public void update() {
//
//   }
//
//   @Override
//   public void paint(Graphics g) {
//      int weight = 180;
//      int height = 180;
//
//      int dx1;
//      int dy1 = height * 0;
//      int dx2;
//      int dy2 = height * 1;
//
//      int sx1;
//      int sx2;
//      int sy1 = 0;
//      int sy2 = height;
//
//      if( quizRound != num) {
//         for (int j = 0; j < quiz[quizRound].length; j++) {// �̹� �迭�� ��������ְ� ���� �־����־� ��¸� �ϸ��
//            int key = quiz[quizRound][j];
//   
//            dx1 = j * weight / 3;
//            dx2 = (j + 1) * weight / 3;
//   
//            sx1 = weight * (key - 37);
//            sx2 = weight * (key - 37) + weight;
//   
//            switch (key) {
//            case LEFT: // key-37 = 0,1,2,3
//            case UP:
//            case RIGHT:
//            case DOWN:
//   //            if (check(key)) {
//   //               
//   //               g.drawImage(img_boom, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, ActionCanvas.instance);
//   //            }
//               g.drawImage(img_box, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, ActionCanvas.instance);
//            }
//         }
//      } else {
//         System.out.println("��");
//      }
//   }
//
//   public boolean check(int keyCode) {
//      // �ѻ���Ŭ�� �������� j���� ���������ְ�
//      // ���̻� �迭�� ���ٸ� i�� ����
//
//      if (quiz[quizRound][quizNum] == keyCode) { // 37 38 39 40
////         System.out.printf("���繮�� �迭 : %d,%d,%d\n", quizRound, quizNum,quiz[quizRound].length - 1);
////         �ϴ� ����
//         quizNum++;// üũ�ؼ� ������ ��������
//         int len = quiz[quizRound].length;
//         if (quizNum == len) {
//            if( quizRound < num) { // ������ ������ �ƴ϶�� ������Ŵ
//               quizRound++;// ���� ������ ������ ������
//            }
//            System.out.println("����");
//            quizNum = 0;
//         }
//         return true;
//      }
//      quizNum = 0;// Ʋ���� ó������
//      return false;
//   }
//
//}
