package com.br.divus.sensores;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;

/**
 * Verifica se fez shake no device, isto é, se houve uma mudança brusca de
 * aceleração
 * 
 * @author Ricardo Lecheta
 * 
 */
public class ShakeActivity extends AcelerometroActivity implements SensorEventListener {

	private static final int TIPO_SENSOR = Sensor.TYPE_ACCELEROMETER;
	private SensorManager sensorManager;
	private Sensor sensor;
	protected long time;
	
	private float mAccel; 			// aceleração sem gravidade já com filtro
	private float mAccelCurrent; 	// aceleração com gravidade
	private float mAccelLast; 		// última aceleração
	int count = 3;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sensor_xyz);

		sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

		sensor = sensorManager.getDefaultSensor(TIPO_SENSOR);
		if (sensor == null) {
			Toast.makeText(this, "Sensor não disponível.", Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (sensor != null) {
			sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_FASTEST);
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
		// SENSOR_STATUS_ACCURACY_LOW, SENSOR_STATUS_ACCURACY_MEDIUM,
		// SENSOR_STATUS_ACCURACY_HIGH, or SENSOR_STATUS_UNRELIABLE.
	}

	
	@Override
	public void onSensorChanged(SensorEvent se) {
		// super.onSensorChanged(se);

		float x = se.values[0];
		float y = se.values[1];
		float z = se.values[2];

		// calcula a aceleração
		mAccelLast = mAccelCurrent;
		mAccelCurrent = (float) Math.sqrt((double) (x * x + y * y + z * z));

		// verifica a diferença
		float delta = mAccelCurrent - mAccelLast;

		// Aplica o filtro (Filtro passa-baixo de corte)
		mAccel = mAccel * 0.2f + delta;

		if (mAccel > 5) {
			count++;
			Log.i("shake", " > " + count);
			// Precisa mexer 3 vezes
			if(count >= 3) {
				count = 0;
				// Se mudou bruscamente
				Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
			    vibrator.vibrate(2000);
			}
		}
	}
}
