package com.example.gc1exemploservicodownload.servico;

import com.example.gc1exemploservicodownload.R;
import com.example.gc1exemploservicodownload.ResultadoActivity;
import com.example.gc1exemploservicodownload.util.NotificationUtil;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NotificaBroadcast extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		int resultado = intent.getExtras().getInt(DownloadServico.RESULT_DOWNLOAD);
		String caminho = intent.getExtras().getString(DownloadServico.PATH_DOWNLOAD);
		String urlPath = intent.getExtras().getString(DownloadServico.URL_PATH);
		
		if (resultado == Activity.RESULT_OK) {
			Intent it = new Intent(context, ResultadoActivity.class);
			
			it.putExtra(DownloadServico.PATH_DOWNLOAD, intent.getExtras().getString(DownloadServico.PATH_DOWNLOAD));
			it.putExtra(DownloadServico.URL_PATH, intent.getExtras().getString(DownloadServico.URL_PATH));
			
			NotificationUtil.create(context, "Download Serviço", "Download Serviço", "Download efetuado com Sucesso!", R.drawable.ic_launcher, 1, it);
		} else {
			Intent it = new Intent();
			
			NotificationUtil.create(context, "Download Serviço", "Download Serviço", "Erro Download!", R.drawable.ic_launcher, 2, it);
		}
	}

}
