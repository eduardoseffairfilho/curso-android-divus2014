package com.example.exemploprocessoassincrono;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class DownloadUtil {

	public static Bitmap downloadImagem(String url) {
		final DefaultHttpClient client = new DefaultHttpClient();
		final HttpGet request = new HttpGet(url);
		try {
			HttpResponse response = client.execute(request);
			int statusCode = response.getStatusLine().getStatusCode();
			if(statusCode != HttpStatus.SC_OK){
				return null;
			}
			//resposta que necessito
			HttpEntity entity = response.getEntity();
			if(entity != null){
				InputStream input = null;
				
				try{
					//Conteudo HTTP
					input = entity.getContent();
					
					//Decodificar para imagem
					Bitmap imagem = BitmapFactory.decodeStream(input);
					
					return imagem;
				}finally{
					if(input != null){
						input.close();
					}
					entity.consumeContent();
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
