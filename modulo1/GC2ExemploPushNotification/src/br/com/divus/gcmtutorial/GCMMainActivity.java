package br.com.divus.gcmtutorial;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gcm.GCMRegistrar;

public class GCMMainActivity extends Activity {

	String TAG = "GCM Divus::Activity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		GCMRegistrar.checkDevice(this);
		GCMRegistrar.checkManifest(this);

		// Register Device Button
		Button regbtn = (Button) findViewById(R.id.register);

		regbtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i(TAG, "Registering device");
				// Sender ID sera registrado no GCMRegistrar
				GCMRegistrar.register(GCMMainActivity.this,
						GCMIntentService.SENDER_ID);
			}
		});
	}
}