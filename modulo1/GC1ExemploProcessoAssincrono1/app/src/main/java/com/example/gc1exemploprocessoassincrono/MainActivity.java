package com.example.gc1exemploprocessoassincrono;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
	
	private ImageView imgView;
	private Bitmap imagem;
	private ProgressDialog progress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		imgView = (ImageView) findViewById(R.id.imgFoto);
	}
	
	private Handler mensagem = new Handler() {
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			imgView.setImageBitmap(imagem);
			progress.dismiss();
		}
	};
	
	/**
	 *  Esse código funciona sem Thread.
	 *
	public void atualizarImagemOnclick(View v) {
		Bitmap imagem = downloadImagem("https://www.google.com.br/images/srpr/logo11w.png");
		imgView.setImageBitmap(imagem);
	}*/

	/**
	 * Esse código não funciona com Thread.
	 *
	public void atualizarImagemOnclick(View v) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Bitmap imagem = downloadImagem("https://www.google.com.br/images/srpr/logo11w.png");
				imgView.setImageBitmap(imagem);
			}
		}).start(); 
	}*/
	
	/**
	 *  Esse código funciona com Thread.
	 *
	public void atualizarImagemOnclick(View v) {
		//progress = ProgressDialog.show(this, "Download", "carregando.....");
		progress = ProgressDialog.show(this, null, "carregando.....");
		new Thread(new Runnable() {
			@Override
			public void run() {
				imagem = downloadImagem("https://www.google.com.br/images/srpr/logo11w.png");
				mensagem.sendEmptyMessage(0);
			}
		}).start();
		
	}*/
	
	/**
	 *  Esse código funciona com Thread.
	 */
	public void atualizarImagemOnclick(View v) {
		//progress = ProgressDialog.show(this, "Download", "carregando.....");
		progress = ProgressDialog.show(this, null, "carregando.....");
		new Thread(new Runnable() {
			@Override
			public void run() {
				imagem = downloadImagem("https://www.google.com.br/images/srpr/logo11w.png");
				imgView.post(new Runnable() {
					@Override
					public void run() {
						imgView.setImageBitmap(imagem);
						progress.dismiss();
					}
				});
			}
		}).start();
		
	}
	
	/**
	 *  Esse código funciona com Thread.
	 *
	public void atualizarImagemOnclick(View v) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						Bitmap imagem = downloadImagem("https://www.google.com.br/images/srpr/logo11w.png");
						
						imgView.setImageBitmap(imagem);
					}
				});
			}
		}).start();
	}*/
	
	private Bitmap downloadImagem(String url) {

		final DefaultHttpClient client = new DefaultHttpClient();
		final HttpGet request = new HttpGet(url);
		Bitmap imagem = null;
		
		try {
			HttpResponse response = client.execute(request);
			
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				return null;
			}
			
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream input = null;
				
				try {
					input = entity.getContent();
					if (input != null) {
						imagem = BitmapFactory.decodeStream(input);
					}
				} finally {
					if (input != null) {
						input.close();
					}
					if (entity != null) {
						entity.consumeContent();
					}
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return imagem;
	}
}
