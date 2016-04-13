package br.com.divus.bluetooth;

import java.util.Iterator;
import java.util.Set;

import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

   private static final int REQUEST_ENABLE_BT = 1;
   private Button onBtn;
   private Button offBtn;
   private Button listBtn;
   private Button findBtn;
   private TextView txStatus;
   private BluetoothAdapter myBluetoothAdapter; // gerencia do blutooth
   private Set<BluetoothDevice> pairedDevices;
   private ListView myListView;
   private ArrayAdapter<String> BTArrayAdapter;
   private ProgressDialog dialog;
  
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      
      // retornar uma instancia de BluetoothAdapter
      myBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
      if(myBluetoothAdapter == null) {
    	  onBtn.setEnabled(false);
    	  offBtn.setEnabled(false);
    	  listBtn.setEnabled(false);
    	  findBtn.setEnabled(false);
    	  txStatus.setText("Status: Não Suportado");
    	  
    	  Toast.makeText(getApplicationContext(),"Seu dispositivo não possui suporte a Bluetooth", Toast.LENGTH_LONG).show();
      } else {
    	  txStatus = (TextView) findViewById(R.id.status);
	      onBtn = (Button)findViewById(R.id.turnOn);
	      
	      offBtn = (Button)findViewById(R.id.turnOff);
	      
	      listBtn = (Button)findViewById(R.id.paired);
	      
	      findBtn = (Button)findViewById(R.id.search);
	    
	      myListView = (ListView)findViewById(R.id.listView1);
	
	      BTArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
	      myListView.setAdapter(BTArrayAdapter);
      }
   }

   public void ligarOnclick(View view){
	   if (!myBluetoothAdapter.isEnabled()){
		   Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
		   startActivityForResult(intent, REQUEST_ENABLE_BT);
		   Toast.makeText(this, "Bluetooth ativado", Toast.LENGTH_LONG).show();
		   txStatus.setText("Status: Ligado");
	   } else {
		   Toast.makeText(this, "Bluetooth ativado", Toast.LENGTH_LONG).show();
		   txStatus.setText("Status: Ligado");
	   }
   }
   
   @Override
   protected void onActivityResult(int requestCode, int resultCode, Intent data) {

   }
   
   public void listarConectadosOnclick(View view) {
	   pairedDevices = myBluetoothAdapter.getBondedDevices();
	   BTArrayAdapter.clear();
	   for (BluetoothDevice device : pairedDevices) {
		   BTArrayAdapter.add(device.getName() + "\n" + device.getAddress());
		   BTArrayAdapter.notifyDataSetChanged();
	   }
   }
   
   final BroadcastReceiver bReceiver = new BroadcastReceiver() {
	    public void onReceive(Context context, Intent intent) {
	    	String action = intent.getAction();
	    	if (BluetoothDevice.ACTION_FOUND.equals(action) == true) {
	    		BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
	    		BTArrayAdapter.add(device.getName() + "\n" + device.getAddress());
	    		BTArrayAdapter.notifyDataSetChanged();
	    		dialog.dismiss();
	    	}
	    }
	};
	
   public void pesquisarDevicesOnclick(View view) {
	   if (myBluetoothAdapter.isEnabled() == true) {
		   if (myBluetoothAdapter.isDiscovering()) {
			   myBluetoothAdapter.cancelDiscovery();
		   } else {
			   BTArrayAdapter.clear();
			   myBluetoothAdapter.startDiscovery();
			   registerReceiver(bReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));
			   dialog = ProgressDialog.show(this, "Bluetooh", "Pesquisando...");
		   }
	   } else {
		   Toast.makeText(this, "Bluetooth está desativado, não é possível pesquisar dispositivos.", Toast.LENGTH_LONG).show();
	   }
   }
   
	public void desligarOnclick(View view) {
		if (myBluetoothAdapter.isEnabled() == true) { 
			myBluetoothAdapter.disable();
		}
		txStatus.setText("Status: Desligado");
		Toast.makeText(this, "Bluetooth Desligado!", Toast.LENGTH_LONG).show();
	}
   
   @Override
   protected void onDestroy() {
	   super.onDestroy();
	   if (bReceiver != null && bReceiver.isOrderedBroadcast() == true)
		   unregisterReceiver(bReceiver);
   }
		
}
