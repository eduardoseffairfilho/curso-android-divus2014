package com.example.gc1exemploservicodownload;

import com.example.gc1exemploservicodownload.servico.DownloadServico;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

public class ResultadoActivity extends Activity {
	
	private TextView txtCaminho;
	
	private WebView webview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tela_resultado);
		
		txtCaminho = (TextView) findViewById(R.id.txtCaminho);
		
		webview = (WebView) findViewById(R.id.webView);
		webview.setWebViewClient(new MyWebViewClient());
		
		Bundle bundle = getIntent().getExtras();
		if (bundle != null) {
			String caminho = bundle.getString(DownloadServico.PATH_DOWNLOAD);
			String url = bundle.getString(DownloadServico.URL_PATH);
			txtCaminho.setText(caminho);
			
			webview.getSettings().setJavaScriptEnabled(true);
			//webview.loadUrl(caminho);
			webview.loadUrl(url);
		}
	}
}
