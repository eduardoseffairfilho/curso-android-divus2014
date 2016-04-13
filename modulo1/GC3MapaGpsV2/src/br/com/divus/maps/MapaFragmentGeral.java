package br.com.divus.maps;

import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapaFragmentGeral extends android.support.v4.app.FragmentActivity implements OnMapClickListener, OnCameraChangeListener {
	
	protected GoogleMap map;
	
	private SupportMapFragment mapFragment;
	
	protected AndroidLocationSource locationSource;
	
	private LatLng posicaoCasa = new LatLng(-3.107582, -60.012244); // Posição do lugar que será exibido no mapa.
	private LatLng posicaoManausAmbiental = new LatLng(-3.137989, -60.024116); // Posição do lugar que será exibido no mapa.
	private LatLng posicaoSefaz = new LatLng(-3.107455, -60.007942); // Posição do lugar que será exibido no mapa.
	//private LatLng posicaoDivus = new LatLng(-3.123280, -60.006008); // Posição do lugar que será exibido no mapa.
	private LatLng posicaoShopping = new LatLng(-3.093907, -60.022517);// Posição do lugar que será exibido no mapa.

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

	private void configureMap() {
		if (map == null) {
			mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
			map = mapFragment.getMap();
		}
		map.setMapType(GoogleMap.MAP_TYPE_SATELLITE); // Tipo  do Mapa a ser exibido.
		
		CameraPosition cameraPos = new CameraPosition.Builder()
		.target(posicaoCasa)
		.bearing(100) // rotacao do mapa
		.tilt(1) // inclinacao do mapa
		.zoom(17) // zoom do mapa
		.build(); 
		CameraUpdate update = CameraUpdateFactory.newCameraPosition(cameraPos);
		
		map.animateCamera(update); // Abre o mapa na posição e da forma configurada em CameraPosition().
		
		map.setOnMapClickListener(this); // Executa o método onMapClick(LatLng latLng) quando um click é efetuado no Mapa.
		
		// Executa o método que adiciona um marcador.
		Marker marcadorCasa = adicionarMarcador(map, posicaoCasa, "Local Preferido Casa", "Local Casa");
		Marker marcadorManausAmbiental = adicionarMarcador(map, posicaoManausAmbiental, "Local Preferido Manaus Ambiental", "Local Manaus Ambiental");
		Marker marcadorShopping= adicionarMarcador(map, posicaoShopping, "Local Preferido Shopping", "Local Shopping");
		
		// Executa o método que personaliza os marcadores.
		personalizarInfoWindow();

		// Executa o método que cria uma linha entre as coordenadas.
		testePolyline(map);
		
		// Executa o método que cria um poligono com as coordenadas informadas.
		//testePolygon(map);
		
		// Localizacao simulada.
		locationSource = new AndroidLocationSource();
		map.setLocationSource(locationSource);
		map.setMyLocationEnabled(true);
		locationSource.setLocation(posicaoSefaz);
		Location myLocation = map.getMyLocation();
		Toast.makeText(this, "Minha Localização é Lat:"+myLocation.getLatitude()+"; Long:"+myLocation.getLongitude(), Toast.LENGTH_LONG).show();
	}
	

	/**
	 * Este método cria uma linha entre as coordenadas.
	 * @param map
	 */
	protected void testePolyline(GoogleMap map) {
		PolylineOptions line = new PolylineOptions();
		line.add(posicaoCasa);
		line.add(posicaoManausAmbiental);
		line.add(posicaoShopping);
		Location l = null;
		try {
			l = map.getMyLocation();
		} catch (Exception e) {
		}
		
		if (l != null) {
			line.add(new LatLng(map.getMyLocation().getLatitude(), map.getMyLocation().getLongitude()));
		}
		
		line.color(Color.BLUE);
		
		map.addPolyline(line);
	}
	
	/**
	 * Este método cria um poligono com as coordenadas informadas.
	 * @param map
	 */
	protected void testePolygon(GoogleMap map) {
		PolygonOptions poligono = new PolygonOptions();
		poligono.add(posicaoCasa);
		poligono.add(posicaoManausAmbiental);
		poligono.add(posicaoShopping);
		Location l = null;
		try {
			l = map.getMyLocation();
		} catch (Exception e) {
		}
		
		if (l != null) {
			poligono.add(new LatLng(map.getMyLocation().getLatitude(), map.getMyLocation().getLongitude()));
		}
		poligono.strokeColor(Color.GREEN);
		poligono.fillColor(Color.RED);
		
		map.addPolygon(poligono);
	}

	protected void adicionaPolyline(GoogleMap map2,LatLng latLng, LatLng latLng2) {

	}
	
	/**
	 * Adiciona um marcador
	 * @param map
	 * @param latLng
	 * @return 
	 */
	private Marker adicionarMarcador(GoogleMap map, LatLng latLng, String title, String snippet) {
		MarkerOptions marcadorOpcoes = new MarkerOptions();
		marcadorOpcoes.position(latLng).title(title).snippet(snippet);
		marcadorOpcoes.icon(BitmapDescriptorFactory.fromResource(R.drawable.marcador_usu));
		
		Marker marcador = map.addMarker(marcadorOpcoes);
		marcador.showInfoWindow();
		return marcador;
	}
	
	private void personalizarInfoWindow() {
		map.setInfoWindowAdapter(new InfoWindowAdapter() {
			
			@Override
			public View getInfoWindow(Marker marker) {
				LinearLayout linear = (LinearLayout) this.getInfoContents(marker);
				linear.setBackgroundResource(R.drawable.janela_marker);
				return linear;
			}
			
			@Override
			public View getInfoContents(Marker marker) {
				LinearLayout linear = new LinearLayout(getBaseContext());
				linear.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));
				linear.setOrientation(LinearLayout.VERTICAL);
				
				TextView titulo = new TextView(getBaseContext());
				titulo.setText("Janela Personalizada");
				titulo.setTextColor(Color.BLACK);
				titulo.setGravity(Gravity.CENTER);
				linear.addView(titulo);

				TextView subTitulo = new TextView(getBaseContext());
				subTitulo.setText(marker.getTitle());
				subTitulo.setTextColor(Color.RED);
				linear.addView(subTitulo);

				TextView textSnippet = new TextView(getBaseContext());
				textSnippet.setText(marker.getSnippet());
				textSnippet.setTextColor(Color.BLUE);
				linear.addView(textSnippet);
				
				return linear;
			}
		});
	}

	@Override
	public void onCameraChange(CameraPosition position) {

	}

	/**
	 * O método é executado quando um click é efetuado no Mapa.
	 */
	@Override
	public void onMapClick(LatLng latLng) {
		TextView debug = (TextView) findViewById(R.id.tDebug);
		CameraUpdate update = CameraUpdateFactory.newLatLng(latLng);
		map.animateCamera(update);
		debug.setText("Posição: " + latLng);
	}

}
