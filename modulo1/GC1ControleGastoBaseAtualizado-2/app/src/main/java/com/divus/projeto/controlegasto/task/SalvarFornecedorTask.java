package com.divus.projeto.controlegasto.task;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

import com.divus.projeto.controlegasto.model.Fornecedor;


public class SalvarFornecedorTask extends AsyncTask<Fornecedor, Void, Void>{
	
	private final static String URL = "http://192.168.0.147:8080/exemplo-rest/resteasy/fornecedor";
	
	private FornecedorListener listener;
	
	private boolean isSucesso = false;
	
	private HttpResponse enviarFornecedor(String json){
		try {
			HttpPost post = new HttpPost(URL);
			post.setEntity(new StringEntity(json));
			post.setHeader("Acept", "application/json");
			post.setHeader("Content-type", "application/json");
			HttpResponse resposta = new DefaultHttpClient().execute(post);
			
			return resposta;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public SalvarFornecedorTask(FornecedorListener listener){
		this.listener = listener;
	}

	@Override
	protected Void doInBackground(Fornecedor... params) {
		parserFornecedorParaJson(params[0]);
		return null;
	}
	
	protected void onPostExecute(Void result){
		super.onPostExecute(result);
		if (isSucesso) {
			listener.executarSincronizacao();
			isSucesso = false;
		}
	}
	
	public void parserFornecedorParaJson(Fornecedor fornecedor){
		JSONObject jsonObj = new JSONObject();
		
		try {
			jsonObj.put("id", fornecedor.getId());
			jsonObj.put("nome", fornecedor.getNome());
			jsonObj.put("endereco", fornecedor.getEndereco());
			jsonObj.put("fone", fornecedor.getFone());
			
			String strJson = jsonObj.toString();
			
			HttpResponse resposta = enviarFornecedor(strJson);
			
			if (resposta.getStatusLine().getStatusCode() == 200){
				isSucesso = true;
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
}

