package com.divus.academia.android.GC1ExemploSuplementar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.divus.academia.android.GC1ExemploSuplementar.R;


public class MainActivity extends Activity {

	private EditText edtNome;
	
	private Button btnExibir;
	private Button btnAlerta;
	private Button btnTrocaTela;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btnExibir = (Button) findViewById(R.id.btnExibir);
        btnAlerta = (Button) findViewById(R.id.btnAlerta);
        
        btnTrocaTela = (Button) findViewById(R.id.btnTrocaTela);
        
        edtNome = (EditText) findViewById(R.id.edtNome);
        
        btnExibir.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), edtNome.getText(), Toast.LENGTH_LONG).show();
			}
		});
        
        btnAlerta.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
				builder.setMessage("Ação realizada com sucesso!!");
				builder.setNeutralButton("OK", null);

				AlertDialog alerta = builder.create();
				alerta.setTitle("Resultado");
				alerta.show();
			}
		});
        
        btnTrocaTela.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent it = new Intent(MainActivity.this, DetalheActivity.class);
				it.putExtra("nome", edtNome.getText().toString());
				startActivity(it);
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
