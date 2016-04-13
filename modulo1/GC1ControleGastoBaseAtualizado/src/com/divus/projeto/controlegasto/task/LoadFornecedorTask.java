package com.divus.projeto.controlegasto.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

import com.divus.projeto.controlegasto.model.Fornecedor;

public class LoadFornecedorTask extends AsyncTask<Void, Void, Void> {
	
	private static final String URL = "http://192.168.0.105:8080/exemplo-rest/resteasy/fornecedor";
	private FornecedorListener listener;

	// Construtor
	public LoadFornecedorTask(FornecedorListener listener) {
		this.listener = listener;
	}
	
	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
		listener.notifyList();
	}

	@Override
	protected Void doInBackground(Void... params) {
		JSONArray jsonFromUrl = getJsonFromUrl(URL);
		parseJson(jsonFromUrl);
		return null;
	}
	
	private JSONArray getJsonFromUrl(String url) {
		JSONArray json = null;
		InputStream input = null;
		String strJson = null;
		HttpURLConnection conexao;

		try {
			conexao = (HttpURLConnection) new URL(url).openConnection();
			conexao.setRequestMethod("GET");
			input = conexao.getInputStream();
			
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
				StringBuilder sb = new StringBuilder();
				String linha = null;
				while ((linha = reader.readLine()) != null) {
					sb.append(linha).append("\n");
				}
				strJson = sb.toString();
				json = new JSONArray(strJson);
			} catch (JSONException e) {
				e.printStackTrace();
			} finally {
				input.close();
				conexao.disconnect();
			}
			
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		return json;
	}
	
	private void parseJson(JSONArray json) {
		List<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
		if (json != null && json.length() > 0) {
			for (int i = 0; i < json.length(); i++) {
				try {
					JSONObject jsonObject = json.getJSONObject(i);
					Fornecedor fornecedor = new Fornecedor(jsonObject.getInt("id"), 
							jsonObject.getString("nome"), 
							jsonObject.getString("telefone"), 
							jsonObject.getString("fone"));
					fornecedores.add(fornecedor);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}
		listener.setFornecedores(fornecedores);
	}
}
