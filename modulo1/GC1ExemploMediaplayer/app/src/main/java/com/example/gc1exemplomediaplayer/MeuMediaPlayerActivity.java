package com.example.gc1exemplomediaplayer;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.VideoView;

public class MeuMediaPlayerActivity extends Activity {
	
	private EditText edtFaixa;
	private VideoView videoView1;
	
	private Player player = new Player();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_meu_media_player);
		
		edtFaixa = (EditText) findViewById(R.id.edtFaixa);
		videoView1 = (VideoView) findViewById(R.id.videoView1);
		
		prepararVideo();
	}
	
	private void prepararVideo() {
		File sdcardDir = android.os.Environment.getExternalStoragePublicDirectory(android.os.Environment.DIRECTORY_MOVIES);
		File file = new File(sdcardDir, "wild_life.mp4"); // Professor
		/*File file1 = new File(sdcardDir, "video.3gp"); // Professor
		File file2 = new File(sdcardDir, "Eduardo e Mônica - O filme.mp4"); // Eduardo
		File file3 = new File(sdcardDir, "Wildlife.wmv"); // Windows7 */
		
		if (file.exists()) {
			videoView1.setVideoPath(file.getAbsolutePath());
			//videoView1.setMediaController(new MediaController(this));
		}
	}

	public void playOnclick(View view) {
		player.start(edtFaixa.getText().toString());
		/*if (videoView1.isPlaying()) {
			videoView1.resume();
		} else {
			videoView1.start();
		}*/
		videoView1.resume();
		if (videoView1.isPlaying() == false) {
			videoView1.start();
		}
	}
	
	public void pauseOnclick(View view) {
		player.pause();
		videoView1.pause();
	}
	
	public void stopOnclick(View view) {
		player.stop();
		//videoView1.stopPlayback();
		videoView1.suspend();
	}
	
	
	@Override
	protected void onDestroy() {
		player.fechar();
		videoView1.stopPlayback();
		super.onDestroy();
	}
}
