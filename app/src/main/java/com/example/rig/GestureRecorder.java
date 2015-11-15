/*
 * GestureRecorder.java
 *
 * Created: 18.08.2011
 *
 * Copyright (C) 2011 Robert Nesselrath
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.example.rig;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.widget.Toast;

public class GestureRecorder implements SensorEventListener {
//registers listenrs for the interface of GestureRecorder Service

	float value;
	public enum RecordMode {
		MOTION_DETECTION, PUSH_TO_GESTURE , IDLE
	};

	final int MIN_GESTURE_SIZE = 63;  //0 to 63 = 64 window as the fft takes a window in powers of 2
	float THRESHOLD = 2;
	SensorManager sensorManager;
	boolean isRecording;

	int stepsSinceNoMovement;
	ArrayList<float[]> gestureValues;
	ArrayList<float[]> A;
	ArrayList<float[]> subA;
	ArrayList<float[]> B;
	ArrayList<float[]> subB;
	Context context;
	GestureRecorderListener listener;
	boolean isRunning;
	RecordMode recordMode = RecordMode.MOTION_DETECTION;

	public GestureRecorder(Context context) {
		this.context = context;
		gestureValues = new ArrayList<float[]>();
		A= new ArrayList<float[]>();
		B= new ArrayList<float[]>();
		subA= new ArrayList<float[]>();
		subB= new ArrayList<float[]>();

	}

	private float calcVectorNorm(float[] values) {
		float norm = (float) Math.sqrt(values[SensorManager.DATA_X] * values[SensorManager.DATA_X] + values[SensorManager.DATA_Y] * values[SensorManager.DATA_Y] + values[SensorManager.DATA_Z]
				* values[SensorManager.DATA_Z]) - 9.9f;
		return norm;
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
	}

	public RecordMode getRecordMode() {
		return recordMode;
	}

	public void setThreshold(float threshold) {
		THRESHOLD = threshold;
		System.err.println("New Threshold " + threshold);
	}

	public boolean isRunning() {
		return isRunning;
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// do nothing
	}

	public void onPushToGesture() {

		if (gestureValues.size() > MIN_GESTURE_SIZE) {
			listener.onGestureRecorded(gestureValues);
			//its mine
			Log.d("storing ", "new move");
			//recordMode = RecordMode.MOTION_DETECTION;



		}
	}
	boolean window=false;

	public void onPushToTrain() {

		if (gestureValues.size() > MIN_GESTURE_SIZE) {



			if(window){  //after 1st window
B=gestureValues;
			for(int i=0;i<32;i++) {


				subA.add(i,A.get(31+i)) ;
				subA.add(32+i,B.get(i));
				//fix it
				Log.d("SubA!!!  " + subA.get(i)[0]," Value^^^^^^^^^^^^^^^^^^^\n");
				Log.d("SubB!!!  " + subB.get(i)[0]," Value^^^^^^^^^^^^^^^^^^^\n");

			}
				Log.d("subA "+subA.size(), "subB "+subB.size());
				subA.clear();
				subB.clear();
				A=B;
				Log.d("Below A=b","");
			return;

			}
			Log.d("in 1st window "+gestureValues.size(),"############################");

			//listener.onGestureRecordedTest(gestureValues);
window=true;
			A=gestureValues;
			gestureValues.clear();
			Log.d("PushtoTrain", "w");



		}


	}


	@Override
	public void onSensorChanged(SensorEvent sensorEvent) {

		float[] value = { sensorEvent.values[SensorManager.DATA_X], sensorEvent.values[SensorManager.DATA_Y], sensorEvent.values[SensorManager.DATA_Z] };
		//	Log.d("ONSEMSOR", "CHANGED");

		switch(recordMode){

			case MOTION_DETECTION:
				//gestureValues = new ArrayList<float[]>();
				gestureValues.add(value);

				onPushToTrain();
				//	Log.d("SensChang", "MotionDetect;
				break;

			case PUSH_TO_GESTURE:
				gestureValues.add(value);

				onPushToGesture();
				break;

		}
	}
	public void registerListener(GestureRecorderListener listener) {
		this.listener = listener;
		start();
	}

	public void setRecordMode(RecordMode recordMode) {
		this.recordMode = recordMode;
	}

	public void start() {
		sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
		sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);
		isRunning = true;//this lets run the onGestureRecorded(List<float[]> values) in GesturRecognsionService
	}

	public void stop() {
		sensorManager.unregisterListener(this);
		Log.d("stop() ", "unreg ");

		isRunning = false;

	}

	public void unregisterListener(GestureRecorderListener listener) {
		this.listener = null;
		stop();
	}

	public void pause(boolean b) {
		if (b) {
			sensorManager.unregisterListener(this);
		} else {
			sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);
		}
	}

}