package com.divus.exemplo.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

public class EnderecoJsonActivity extends ListActivity {

	private List<Endereco> enderecos;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		JSONObject objJson = createJson();
		parseJson(objJson);

		ArrayAdapter<Endereco> adapter = new ArrayAdapter<Endereco>(this, android.R.layout.simple_list_item_1, enderecos);
		setListAdapter(adapter);
	}

	private JSONObject createJson() {
		JSONObject jObj = null;
		String json = null;

		try {
			InputStream input = this.getAssets().open("enderecos.json");
			BufferedReader reader = new BufferedReader(new InputStreamReader(input, "UTF-8"));
			StringBuilder sb = new StringBuilder();
			String linha = null;
			while ((linha = reader.readLine()) != null) {
				sb.append(linha + "\n");
			}
			input.close();
			json = sb.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			jObj = new JSONObject(json);
		} catch (JSONException e) {
			e.printStackTrace();
			Log.e("JSON Parser", "Erro no parse dos Dados: " + e.getMessage());
		}
		return jObj;
	}

	private void parseJson(JSONObject json) {
		enderecos = new ArrayList<Endereco>();

		try {
			JSONArray jContatos = json.getJSONArray("enderecos");
			for(int i = 0; i < jContatos.length(); i++) {
				JSONObject jsonObject = jContatos.getJSONObject(i);
				Endereco endereco = new Endereco();
				endereco.setRua(jsonObject.getString("rua"));
				endereco.setNumero(jsonObject.getString("numero"));
				endereco.setTelefone(jsonObject.getString("telefone"));
				endereco.setResponsavel(jsonObject.getString("responsavel"));
				
				enderecos.add(endereco);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		System.out.println("lista enderecos qtd:" + enderecos.size());
	}
}
