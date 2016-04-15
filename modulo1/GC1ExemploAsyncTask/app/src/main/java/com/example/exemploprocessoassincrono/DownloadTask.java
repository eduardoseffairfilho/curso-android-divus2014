package com.example.exemploprocessoassincrono;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;

public class DownloadTask extends AsyncTask<String, Integer, Bitmap>{

	private ProgressDialog progress;
	private Context context;
	private ImagemListener listener;
	
	public DownloadTask(Context context, ImagemListener listener) {
		this.context = context;
		this.listener = listener;
	}
	
	//pré tarefa
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		progress = ProgressDialog.show(context, "Download", "Carregando...");
	}
	
	//durante tarefa
	@Override
	protected Bitmap doInBackground(String... params) {
		Bitmap imagem = DownloadUtil.downloadImagem(params[0]);
		return imagem;
	}
	
	//após tarefa
	@Override
	protected void onPostExecute(Bitmap result) {
		super.onPostExecute(result);
		listener.setImagem(result);
		progress.dismiss();
	}

}
