package com.example.gc1exemploservicodownload.servico;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Environment;

public class DownloadServico extends IntentService {
	
	public static final String URL_DOWNLOAD = "url";
	public static final String URL_PATH = "urlPath";
	public static final String RESULT_DOWNLOAD = "result";
	public static final String PATH_DOWNLOAD = "path";
	public static final String CONST_FILENAME = "pagina.html";
	
	private int result = Activity.RESULT_CANCELED;
	private String urlPath;
	
	public DownloadServico() {
		super("DownloadServico");
	}
	
	@Override
	protected void onHandleIntent(Intent intent) {
		urlPath = intent.getStringExtra(URL_DOWNLOAD);
		File output = new File(Environment.getExternalStorageDirectory(), CONST_FILENAME);
		if (output.exists()) {
			output.delete();
		}
		
		InputStream stream = null;
		FileOutputStream fos = null;
		try {
			URL url = new URL(urlPath);
			stream = url.openConnection().getInputStream();
			InputStreamReader reader = new InputStreamReader(stream);
			fos = new FileOutputStream(output.getPath());
			int next = -1;
			while((next = reader.read()) != -1) {
				fos.write(next);
			}
			
			this.result = Activity.RESULT_OK;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		publicarResultado(output.getAbsolutePath());
	}
	
	public void publicarResultado(String path) {
		Intent it = new Intent(this, NotificaBroadcast.class);
		
		it.putExtra(URL_PATH, urlPath);
		it.putExtra(PATH_DOWNLOAD, path);
		it.putExtra(RESULT_DOWNLOAD, result);
		
		sendBroadcast(it);
	}
}
