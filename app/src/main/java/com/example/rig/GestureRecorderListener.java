package com.example.rig;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mudassir on 8/16/2015.
 */
public interface GestureRecorderListener {
    public void onGestureRecorded(ArrayList<float[]> value);
    public void onGestureRecordedTest(ArrayList<float[]> value);
}
