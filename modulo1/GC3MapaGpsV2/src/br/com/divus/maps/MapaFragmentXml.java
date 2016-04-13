package br.com.divus.maps;

import android.os.Bundle;
import br.com.divus.maps.R;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

/**
 * Exemplo de MapFragment por XML
 */
public class MapaFragmentXml extends android.support.v4.app.FragmentActivity {
	
	protected GoogleMap map;
	private SupportMapFragment mapFragment;

	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.exemplo_mapa_v2);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		configureMap();
	}

	protected void configureMap() {
		if(map == null) {
			mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
			
			// Recupera o objeto GoogleMap
			map = mapFragment.getMap();

			if(map != null) {
				// Configura o tipo do mapa
				map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
			}
		}
	}

}