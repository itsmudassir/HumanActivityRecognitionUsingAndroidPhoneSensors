package com.example.rig;

import java.util.ArrayList;

import com.example.rig.GestureRecorder.RecordMode;



import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SignalProvider extends Service implements  GestureRecorderListener  {
	GestureRecorder recorder;
	GestureClassifier classifier;
	String activeTrainingSet="walk";
	String activeLearnLabel;
	ArrayList<float[]> value;

	boolean isLearning,isTesting;

	public float accel;
	@Override
	public IBinder onBind(Intent intent) {
		// Auto-generated method stub
		recorder.registerListener(this);


		return SignalProviderStub;
	}

	@Override
	public boolean onUnbind(Intent intent) {
		recorder.unregisterListener(this);
		return super.onUnbind(intent);
	}

	@Override
	public void onCreate() {
		recorder = new GestureRecorder(this);
		classifier = new GestureClassifier(this);
		ArrayList<float[]> value= new ArrayList<float[]>();

		super.onCreate();
	}


	IBinder SignalProviderStub = new ISignalProvider.Stub(){

		@Override
		public void start() throws RemoteException {

			// TODO Auto-generated method stub
			//do stuff



			Log.d("recorder.start", "hit");
			//recorder.start();
		}

		@Override
		public void setCommit(boolean a) throws RemoteException {
			// TODO Auto-generated method stub
		}

		@Override
		public void setTest(boolean a) throws RemoteException {
			// TODO Auto-generated method stub
			//test=a;
			double results=classifier.Classifysignal(new Gesture(value,"TEST"),activeTrainingSet);
			Toast.makeText( getApplicationContext(), "DTW DIST="+Double.toString(results), Toast.LENGTH_SHORT).show();

		}

		@Override
		public void startLearning(String ActiveTraininSet,String gestureName) throws RemoteException {
			// TODO Auto-generated method stub
			isLearning = true;
			activeTrainingSet=ActiveTraininSet;
			activeLearnLabel= gestureName;
			recorder.setRecordMode(GestureRecorder.RecordMode.PUSH_TO_GESTURE);
		}

		@Override
		public void startTesting(String ActiveTrainingSet) throws RemoteException {
			// TODO Auto-generated method stub
			activeTrainingSet=ActiveTrainingSet;
			isTesting= true;
			recorder.start();
			recorder.setRecordMode(GestureRecorder.RecordMode.MOTION_DETECTION);

		}

		@Override
		public void stopLearning() throws RemoteException {
			// TODO Auto-generated method stub
			isLearning = false;
			recorder.setRecordMode(GestureRecorder.RecordMode.MOTION_DETECTION);

		}

		@Override
		public void stopTesting() throws RemoteException {
			// TODO Auto-generated method stub
			isTesting=false;
			recorder.stop();
		}






	};






	public void onDestroy(){



		super.onDestroy();

	}




	@Override
	public void onGestureRecorded(ArrayList<float[]> value) {
		//value=value1;
		if(isLearning){

			Log.d("Gesture", "array is in SigProvider");
			Log.d(activeTrainingSet, activeLearnLabel);

			classifier.trainData(activeTrainingSet,new Gesture(value,activeLearnLabel) );
			classifier.commitData();
			float a=value.get(3)[2];
			// TODO Auto-generated method stub
			Toast.makeText(getApplicationContext(), "intent"+Float.toString(a), Toast.LENGTH_SHORT).show();
			for(int i=0;i<48;i++){
				Log.d("OnGesture", " Val: "+value.get(i)[0]+" "+value.get(i)[1]+" "+value.get(i)[2]);

			}
			recorder.setRecordMode(GestureRecorder.RecordMode.MOTION_DETECTION);
			Toast.makeText( getApplicationContext(), "New Gestr Got", Toast.LENGTH_SHORT).show();

		}



	}
	double results=00;
	@Override
	public void onGestureRecordedTest(ArrayList<float[]> value1) {
		value=value1;
		// TODO Auto-generated method stub
		//recorder.stop();

		Log.d("RecordedTest", "in");

		if(isTesting){
			recorder.setRecordMode(GestureRecorder.RecordMode.IDLE);
			Log.d("MODE", "IDLE");


			//recorder.stop();
			//classifier.loadTrainingSet("walk");
//results++;
			//Log.d(activeTrainingSet, activeLearnLabel);

			results=classifier.Classifysignal(new Gesture(value,"TEST"),activeTrainingSet);
			Toast.makeText( getApplicationContext(), "DTW DIST="+Double.toString(results), Toast.LENGTH_SHORT).show();
			//recorder.start();
			recorder.setRecordMode(GestureRecorder.RecordMode.MOTION_DETECTION);
			Log.d("MODE", "Motion_DETECTION");


		}		//classifier.loadTrainingSet("walk");

	}




}

