package com.br.divus.sensores;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class LuminosidadeActivity extends Activity implements SensorEventListener {

	private static final int TIPO_SENSOR = Sensor.TYPE_LIGHT;
	private SensorManager sensorManager;
	private Sensor sensor;
	private SeekBar progress;
	
	private LinearLayout container;
	
	private float max;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sensor_progressbar);

		progress = (SeekBar) findViewById(R.id.progress);
		
		container = (LinearLayout) findViewById(R.id.container);
		
		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

		sensor = sensorManager.getDefaultSensor(TIPO_SENSOR);
		
		if (sensor != null) {

			// Define o valor maximo no SeekBar
			max = sensor.getMaximumRange();
			progress.setMax((int) max);
		} else {
			Toast.makeText(this, "Sensor não disponível", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (sensor != null) {
			sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		sensorManager.unregisterListener(this);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// Mudou o status de precisão do cursor
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		float valor = event.values[0];

		((TextView) findViewById(R.id.tValor)).setText("Luz: " + valor + ". Máximo:" + sensor.getMaximumRange());

		progress.setProgress((int) valor);

		mudarCor(valor);
	}

	private void mudarCor(float valor) {
		/*if (valor < 50) {
			this.container.setBackgroundColor(getResources().getColor(R.color.azul_claro));
		} else if (valor < 100) {
			this.container.setBackgroundColor(getResources().getColor(R.color.laranja));
		} else if (valor < 500) {
			this.container.setBackgroundColor(getResources().getColor(android.R.color.holo_purple));
		} else {
			this.container.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
		}*/
		if (valor < sensor.getMaximumRange()*0.25) {
			this.container.setBackgroundColor(getResources().getColor(R.color.azul_claro));
		} else if (valor < sensor.getMaximumRange()*0.50) {
			this.container.setBackgroundColor(getResources().getColor(R.color.laranja));
		} else if (valor < sensor.getMaximumRange()*0.75) {
			this.container.setBackgroundColor(getResources().getColor(android.R.color.holo_purple));
		} else {
			this.container.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
		}
	}
	
}
