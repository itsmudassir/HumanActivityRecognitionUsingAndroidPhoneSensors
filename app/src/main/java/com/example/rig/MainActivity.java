package com.example.rig;




import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	String activeTrainingSet,gestureName;

	ISignalProvider SignalProvider;
	double minDistance = Double.MAX_VALUE;
	Button btnTrain,btnStopTrain;
	TextView txtTrainSetName,txtGestureName;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnTrain=(Button)this.findViewById(R.id.btnTrain);
		btnStopTrain=(Button)this.findViewById(R.id.btnStopTrain);
		txtTrainSetName=(TextView)this.findViewById(R.id.trainingSet);
		txtGestureName=(TextView)this.findViewById(R.id.gestureName);




		btnTrain.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try {
					gestureName= txtGestureName.getText().toString();

					activeTrainingSet = txtTrainSetName.getText().toString();
					SignalProvider.startLearning(activeTrainingSet,gestureName);
					Toast.makeText(getApplicationContext(), "trainButtone work", Toast.LENGTH_SHORT).show();

				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});


		btnStopTrain.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try {
					SignalProvider.stopLearning();
					Toast.makeText(getApplicationContext(), "stopTrain work", Toast.LENGTH_SHORT).show();

				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}


			}
		});

	}

	private final ServiceConnection SignalConnection= new ServiceConnection(){

		@Override
		public void onServiceConnected(ComponentName arg0, IBinder service) {
			// TODO Auto-generated method stub
			SignalProvider= ISignalProvider.Stub.asInterface(service);

			Toast.makeText(getApplicationContext(), "OnServiceConn", Toast.LENGTH_SHORT).show();

			try {
				activeTrainingSet = txtTrainSetName.getText().toString();

				SignalProvider.startTesting(activeTrainingSet);
				Log.d("MainAct", "onSerConn-StartTesting");

			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}





		}

		@Override
		public void onServiceDisconnected(ComponentName arg0) {
			// TODO Auto-generated method stub
			SignalProvider=null;
		}};



	@Override
	protected void onResume() {

		Toast.makeText(getApplicationContext(), "intent", Toast.LENGTH_SHORT).show();

		Intent BindIntent= new Intent("com.example.rig.SignalProvider");
		bindService(BindIntent, SignalConnection,Context.BIND_AUTO_CREATE);
		super.onResume();

	}

	@Override
	protected void onPause() {


		SignalProvider = null;
		unbindService(SignalConnection);
		super.onPause();
	}







}