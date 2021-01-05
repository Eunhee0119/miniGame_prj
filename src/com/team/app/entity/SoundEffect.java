package com.team.app.entity;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundEffect {
	private static boolean activeSound = true;
	private static float volume = 0;
	private static FloatControl control;
	
	public static void play(String pathName, boolean isLoop) {
		try {
			Clip clip; // ���� Ŭ��
			clip = AudioSystem.getClip();
			File audioFile = new File(pathName);
			AudioInputStream ais;
			ais = AudioSystem.getAudioInputStream(audioFile);
			clip.open(ais);
			control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			setVolume(volume);
			clip.start();
			if (isLoop)
				clip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception e) {
			System.out.println("ȿ���� ����");
		}
	}

	public static void setVolume(double volume) {
		try {
			SoundEffect.volume = (float)volume;
			control.setValue(SoundEffect.volume);
		} catch (Exception e) {
//			e.printStackTrace();
			System.out.println("����� ���� ���� ����");
		}
	}
}
