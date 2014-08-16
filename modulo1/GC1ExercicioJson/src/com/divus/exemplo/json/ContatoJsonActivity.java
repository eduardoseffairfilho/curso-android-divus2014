package com.divus.exemplo.json;

import org.json.JSONObject;

import android.app.ListActivity;
import android.os.Bundle;

public class ContatoJsonActivity extends ListActivity {
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);

	JSONObject objJson = createJson();
	parseJson(objJson);

	// ArrayAdapter<Endereco> adapter = new ArrayAdapter<Endereco>(this, android.R.layout.simple_list_item_1, enderecos);
	//setListAdapter(adapter);
    }

    private JSONObject createJson() {
	return null;
    }

    private void parseJson(JSONObject json) {
    }

}
