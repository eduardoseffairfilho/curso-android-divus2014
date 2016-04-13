package br.com.divus.maps;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;

public class LocationService implements LocationListener {

	private Context context;
	
	private GoogleMap map;
	
	private AndroidLocationSource locationSource;
	
	public LocationService(Context context, GoogleMap map, AndroidLocationSource locationSource) {
		this.locationSource = locationSource;
		this.map = map;
		this.context = context;
	}

	@Override
	public void onLocationChanged(Location location) {
		Toast.makeText(context, "Location: " + location, Toast.LENGTH_SHORT).show();

		// Centraliza o mapa nesta coordenada
		LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
		map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
		
		// Atualiza a bolinha azul para a nova coordenada
		this.locationSource.setLocation(location);
	}

	@Override
	public void onProviderDisabled(String provider) {
		// Provedor do GPS foi desabilitado
	}

	@Override
	public void onProviderEnabled(String provider) {
		// Provedor do GPS foi habilitado
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// Status do provedor do GPS mudou
	}

}
