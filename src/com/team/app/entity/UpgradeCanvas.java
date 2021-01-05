package com.team.app.entity;

import java.awt.Canvas;
import java.awt.Graphics;

import com.team.app.miniGame1.entity.GameEventListener;

public class UpgradeCanvas extends Canvas{

	private boolean activeCanvas = false;
	private int finishPlayers = 0;
	private int playerNum = -1;
	
	private GameEventListener gameEventListener;
	
	public void setGameEventListener(GameEventListener gameEventListener) {
		this.gameEventListener = gameEventListener;
	}
	
	public GameEventListener getGameEventListener() {
		return gameEventListener;
	}
	
	@Override
	public void update(Graphics g) {
		paint(g);
	}
	
	public void start() {
		// Frame���� ��ü����ȯ ���� �������� ȣ���� �ʿ�� ���� �޼ҵ�
	}
	
	public void playerSetting(int playerNum) {
		// ĵ���� ����Ī �ϰ� �ٷ� ȣ��� �޼ҵ�
		// ���� �ϱ� ���� Frame���� ȣ���� �÷��̾� �⺻ ���� �޼ҵ�
		activeCanvas = true;
		this.playerNum = playerNum;
	}
	
	public void otherPlayerEvent(int otherPlayer, String key, String value) {
		// �ٸ� �÷��̾��� �̺�Ʈ�� �޾��� �� ȣ��� �޼ҵ�
	}
	
//	public abstract void start();
//	public abstract void otherPlayerEvent(int otherPlayer, String key, String value);
//	public abstract void playerSetting(int playerNum);
	
	public boolean isActiveCanvas() {
		return activeCanvas;
	}

	public void setActiveCanvas(boolean activeCanvas) {
		this.activeCanvas = activeCanvas;
	}
	
	public int getPlayerNum() {
		return playerNum;
	}

	public void setPlayerNum(int playerNum) {
		this.playerNum = playerNum;
	}

	public int getFinishPlayers() {
		return finishPlayers;
	}

	public void setFinishPlayers(int finishPlayers) {
		this.finishPlayers = finishPlayers;
	}
	
}

