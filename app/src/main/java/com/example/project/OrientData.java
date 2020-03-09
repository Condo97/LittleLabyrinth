package com.example.project;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import java.util.Arrays;

public class OrientData implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor sensor;
    private static final float NS2S = 1.0f / 1000000000.0f;
    private final float[] deltaRotationVector = new float[4];
    private float[] orientation;
    private float[] startOrientation = null;
    private float timestamp;

    public void onSensorChanged(SensorEvent event){
        if(timestamp != 0){
            final float dT = (event.timestamp - timestamp) * NS2S;
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            float magnitude = (float)Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));

            // trial for epsilon = 10
            if(magnitude > 10){
                x = x / magnitude;
                y = y / magnitude;
                z = z / magnitude;
            }

            float thetaOver2 = magnitude * dT / 2.0f;
            float sinTO2 = (float)Math.sin(thetaOver2);
            float cosTO2 = (float)Math.cos(thetaOver2);

            deltaRotationVector[0] = sinTO2 * x;
            deltaRotationVector[1] = sinTO2 * y;
            deltaRotationVector[2] = sinTO2 * z;
            deltaRotationVector[3] = cosTO2;
        }
        timestamp = event.timestamp;
        float[] deltaRotationMatrix = new float[9];
        SensorManager.getRotationMatrixFromVector(deltaRotationMatrix, deltaRotationVector);
        SensorManager.getOrientation(deltaRotationMatrix, orientation);

        if(startOrientation == null){
            startOrientation = new float[orientation.length];
            System.arraycopy(orientation, 0, startOrientation, 0, orientation.length);
        }else{
            orientation = deltaRotationMatrix.clone();
            if(!Arrays.equals(startOrientation, orientation)){

            }
        }
    }

    public void register() {
        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_GAME);
    }

    public void pause() {
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // idk what this is for
    }
}