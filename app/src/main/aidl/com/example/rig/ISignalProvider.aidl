package com.example.rig;

interface ISignalProvider{

	void start();
	void setTest(boolean a);
	void setCommit(boolean a);
	
	void startLearning(String trainingSetName,String gestureName);
	void stopLearning();
	
	void startTesting(String activeTrainingSet);
	void stopTesting();

}

