package com.example.gc1exemplomediaplayer;

import java.io.File;
import java.io.IOException;

import android.media.MediaPlayer;

public class Player {
	
	private static final int NOVO = 0;
	private static final int INICIADO = 1;
	private static final int PAUSADO = 2;
	private static final int PARADO = 3;
	private int statusCorrente = NOVO;
	private MediaPlayer player;
	private String faixa;
	
	public Player() {
		player = new MediaPlayer();
	}
	
	public void start(String faixa) {
		this.faixa = faixa;
		File teste1 = android.os.Environment.getRootDirectory();
		File teste2 = android.os.Environment.getDataDirectory();
		File teste3 = android.os.Environment.getExternalStorageDirectory();
		File teste4 = android.os.Environment.getExternalStoragePublicDirectory(android.os.Environment.DIRECTORY_MUSIC);
		String state = android.os.Environment.getExternalStorageState();
		boolean testea = android.os.Environment.isExternalStorageEmulated();
		boolean testeb = android.os.Environment.isExternalStorageRemovable();

		//File sdscardDir = android.os.Environment.getExternalStorageDirectory();
		File sdscardDir = android.os.Environment.getExternalStoragePublicDirectory(android.os.Environment.DIRECTORY_MUSIC);
		File fileFaixa = new File(sdscardDir, "/midias_curso/" + this.faixa);
		try {
			switch (statusCorrente) {
				case INICIADO:
					System.out.println("Iniciado.");
					player.stop();
				case PARADO:
					System.out.println("Parado.");
					player.reset();
				case NOVO:
					System.out.println("Novo.");
					player.setDataSource(fileFaixa.getAbsolutePath());
					player.prepare();
				case PAUSADO:
					System.out.println("Pausado.");
					player.start();
					break;
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void pause() {
		player.pause();
		statusCorrente = PAUSADO;
	}
	
	public void stop() {
		player.stop();
		statusCorrente = PARADO;
	}
	
	public void fechar() {
		player.stop();
		player.release();
	}
}
