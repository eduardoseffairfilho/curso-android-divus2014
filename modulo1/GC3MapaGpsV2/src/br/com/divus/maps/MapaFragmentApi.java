package br.com.divus.maps;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import br.com.divus.maps.R;

import com.google.android.gms.maps.MapFragment;

/**
 * Exemplo de MapFragment por API
 * 
 * @author ricardo
 *
 */
public class MapaFragmentApi extends Activity {
	@Override
	protected void onCreate(Bundle icicle) {
		super.onCreate(icicle);

		setContentView(R.layout.exemplo_mapa_v2_api);

		MapFragment map = new MapFragment();
		FragmentTransaction t = getFragmentManager().beginTransaction();
		t.add(R.id.layoutMap, map, null);
		t.commit();
	}
}