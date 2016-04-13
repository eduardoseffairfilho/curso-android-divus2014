package com.example.exemploprocessoassincrono;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity implements ImagemListener {
	
	private ImageView imgView;
	private final static String URL = "http://images.forbes.com/media/lists/companies/google_200x200.jpg";
	private Bitmap bitmap = null;
	private ProgressDialog progress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		imgView = (ImageView) findViewById(R.id.imgFoto);
	}
	
	private Handler messagem = new Handler(){
		public void handleMessage(Message msg){
			super.handleMessage(msg);
			imgView.setImageBitmap(bitmap);
			progress.dismiss();
		}
	};
	
	public void atualizarOnClick(View view) {
		new DownloadTask(this,this).execute(URL);
	}

	@Override
	public void setImagem(Bitmap imagem) {
		imgView.setImageBitmap(imagem);		
	}
	
	//worker threads
//	public void atualizarOnClick(View view) {
//		progress = ProgressDialog.show(this, "Download", "Carregando...");
//		new Thread(new Runnable(){
//			@Override
//			public void run() {
//				bitmap  = donwloadImagem(URL);
//				imgView.post(new Runnable() {
//					@Override
//					public void run() {
//						imgView.setImageBitmap(bitmap);
//						progress.dismiss();
//					}
//				});
//			}
//		}).start();
//	}
	
//	public void atualizarOnClick(View view) {
//		progress = ProgressDialog.show(this, "Download", "Carregando...");
//		new Thread(new Runnable(){
//			@Override
//			public void run() {
//				bitmap  = donwloadImagem(URL);
//				messagem.sendEmptyMessage(0);
//			}
//		}).start();
//	}
	
//	private Bitmap donwloadImagem(String url) {
//		final DefaultHttpClient client = new DefaultHttpClient();
//		final HttpGet request = new HttpGet(url);
//		try {
//			HttpResponse response = client.execute(request);
//			int statusCode = response.getStatusLine().getStatusCode();
//			if(statusCode != HttpStatus.SC_OK){
//				return null;
//			}
//			//resposta que necessito
//			HttpEntity entity = response.getEntity();
//			if(entity != null){
//				InputStream input = null;
//				
//				try{
//					//Conteudo HTTP
//					input = entity.getContent();
//					
//					//Decodificar para imagem
//					Bitmap imagem = BitmapFactory.decodeStream(input);
//					
//					return imagem;
//				}finally{
//					if(input != null){
//						input.close();
//					}
//					entity.consumeContent();
//				}
//			}
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
}
