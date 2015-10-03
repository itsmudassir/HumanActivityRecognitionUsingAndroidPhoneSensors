package com.example.rig;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;


public class GestureClassifier {
	protected List<Gesture> trainingSet= Collections.emptyList();
	protected String activeTrainingSet ;
	private final Context context;

	public GestureClassifier(Context context) {
		trainingSet = new ArrayList<Gesture>();

		// initilization is imp

		this.context = context;
	}

	double Classifysignal(Gesture signal,String TrainingSetName){
		Log.d("Classifysignal", "in");
		loadTrainingSet(TrainingSetName);
//for the time being its comparing with the first template gesture in the file of gestures
		double dist = DTWAlgorithm.calcDistance(trainingSet.get(0), signal);
		Log.d("Classifysignal", "deep");


		return dist;


	}

	public void trainData(String trainingSetName,Gesture signal){

		loadTrainingSet(trainingSetName);
		trainingSet.add(signal);



	}


	public void loadTrainingSet(String trainingSetName) {

		//the dataset it load is hard coded

		activeTrainingSet = trainingSetName;
		FileInputStream input;
		ObjectInputStream o;
		try {
			input = new FileInputStream(new File("/sdcard/MudaFit/"+activeTrainingSet+ ".gst").toString());


			o = new ObjectInputStream(input);
			trainingSet = (ArrayList<Gesture>) o.readObject();
			Toast.makeText( context, "File READ!", Toast.LENGTH_SHORT).show();

			try {
				o.close();
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			Toast.makeText( context, "file read fail!", Toast.LENGTH_SHORT).show();

			trainingSet = new ArrayList<Gesture>();
		}

	}



	public boolean commitData() {

		if(activeTrainingSet!= null && activeTrainingSet!=""){
			try {

				Toast.makeText( context, "comit", Toast.LENGTH_SHORT).show();

				File sdcard = Environment.getExternalStorageDirectory();
				// to this path add a new directory path
				File dir = new File(sdcard.getAbsolutePath() + "/sdcard/MudaFit/");
				// create this directory if not already created
				dir.mkdir();
				// create the file in which we will write the contents

				FileOutputStream fos = new FileOutputStream(new File("/sdcard/MudaFit/"+activeTrainingSet + ".gst").toString());
				ObjectOutputStream o = new ObjectOutputStream(fos);
				o.writeObject(trainingSet);
				Toast.makeText( context, "File Wrote", Toast.LENGTH_SHORT).show();

				o.close();
				fos.close();
				return true;
			} catch (IOException e) {
				Toast.makeText( context, "File write failed", Toast.LENGTH_SHORT).show();

				e.printStackTrace();
				return false;
			}

		}
		else{
			return false;}
	}







}
