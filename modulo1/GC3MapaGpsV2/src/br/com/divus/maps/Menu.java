package br.com.divus.maps;

import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Menu extends ListActivity {

	@Override
	public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
		String[] mStrings = new String[] { 
				"Map Fragment",
				"Map Fragment + GPS",
				"Rota",
				"Sair"
		};
		this.setListAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, mStrings));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		switch (position) {
			case 0:
				startActivity(new Intent(this,MapaFragmentGeral.class));
				break;
			case 1:
				startActivity(new Intent(this,ExemploMapaV2_GPS.class));
				break;
			case 2:
				String origem = "-3.1344895, -59.9785514";
				String destino = "-3.061530, -60.026252";
				String url = "http://maps.googleapis.com/maps/api/directions/json?origin="+origem+"&destination="+destino+"&sensor=true&mode=driving";
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
				break;
			default:
				finish();
		}
	}

}