package com.example.gc2exemplopushnotificationgcm;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.android.gcm.GCMBaseIntentService;

public class GCMIntentService extends GCMBaseIntentService {
	
	public static final String MESSAGE = "message";
	public static final String SEND_ID = "699967688009";

	public GCMIntentService() {
		// TODO Auto-generated constructor stub
		super(SEND_ID);
		Log.i("GCM", "GCMIntentService=" + SEND_ID);
	}
	
	@Override
	protected void onError(Context context, String errorId) {
		// TODO Auto-generated method stub
		Log.i("GCM", "errorId=" + errorId);
	}

	@Override
	protected void onMessage(Context context, Intent data) {
		// TODO Auto-generated method stub
		String mensagem = data.getStringExtra(MESSAGE);
		
		Notification notification = new Notification.Builder(this)
		.setSmallIcon(R.drawable.ic_launcher)
		.setWhen(System.currentTimeMillis())
		.setContentTitle("Exemplo de Mensagem")
		.setContentText(mensagem)
		.getNotification();
		
		notification.flags |= Notification.FLAG_AUTO_CANCEL;
		
		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		manager.notify(R.string.app_name, notification);
		
		Log.i("GCM", "messageId=" + mensagem);
	}

	@Override
	protected void onRegistered(Context context, String registrationId) {
		// TODO Auto-generated method stub
		Log.i("GCM", "registrationId=" + registrationId);
	}

	@Override
	protected void onUnregistered(Context context, String arg1) {
		// TODO Auto-generated method stub
	}

}
