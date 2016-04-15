package com.example.gc1exemploservicodownload;

import com.example.gc1exemploservicodownload.servico.DownloadServico;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class PrincipalActivity extends Activity {
	
	private TextView txtMensagem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);
		
		txtMensagem = (TextView) findViewById(R.id.txtMensagem);
	}
	
	public void iniciarOnclick(View v) {
		Intent it = new Intent(this, DownloadServico.class);
		it.putExtra(DownloadServico.URL_DOWNLOAD, "http://www.google.com.br/");
		startService(it);
		
		txtMensagem.setText("Download iniciado...");
	}
}
