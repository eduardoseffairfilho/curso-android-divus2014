package com.example.gc1exemploprocessoassincrono;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;

public class DownloadTask extends AsyncTask<String, Integer, Bitmap> {
	
	private ProgressDialog progress;
	private Context context;
	
	public DownloadTask(Context contexto) {
		this.context = contexto;
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		progress = ProgressDialog.show(context, "Processar", "Carregando...");
	}
	
	@Override
	protected Bitmap doInBackground(String... params) {
		return null;
	}
	
	@Override
	protected void onPostExecute(Bitmap result) {
		super.onPostExecute(result);
		progress.dismiss();
	}
}
