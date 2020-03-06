package com.example.project;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class easyLvl1 extends AppCompatActivity {
    GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameView = new GameView(this);
        setContentView(gameView);
    }

    class GameView extends SurfaceView implements Runnable {
        private SensorManager sensorManager;
        private Sensor sensor;
        private static final float NS2S = 1.0f / 1000000000.0f;
        private final float[] deltaRotationVector = new float[4];
        private float[] orientation;
        private float[] startOrientation = null;
        private float timestamp;
        boolean playing;
        boolean isMoving = false;
        float movementSpeed = 150;
        float xPos = 10;
        float yPos = 200;
        Thread gameThread = null;
        SurfaceHolder hold;
        Canvas canvas;
        Paint paint;
        Bitmap ballMap;

        public GameView(Context context) {
            super(context);

            hold = getHolder();
            paint = new Paint();
            playing = true;
            ballMap = BitmapFactory.decodeResource(this.getResources(), R.drawable.ball1);
            ballMap = Bitmap.createScaledBitmap(ballMap, (int)(ballMap.getWidth()*0.09), (int)(ballMap.getHeight()*0.09), true);
        }

        @Override
        public void run() {
            while(playing){
                update();
                draw();
            }
        }

        public void update() {
            if(isMoving){
            }

        }

        public void draw() {

            // Make sure our drawing surface is valid or we crash
            if (hold.getSurface().isValid()) {
                // Lock the canvas ready to draw
                canvas = hold.lockCanvas();

                // Draw the background color
                canvas.drawColor(Color.argb(255,  249, 162, 53));

                // Choose the brush color for drawing
                paint.setColor(Color.argb(255,  249, 129, 0));

                // Insert the ball
                canvas.drawBitmap(ballMap, xPos, yPos, paint);

                // Draw everything to the screen
                hold.unlockCanvasAndPost(canvas);
            }

        }

        public void pause() {
            playing = false;
            try {
                gameThread.join();
            } catch (InterruptedException e) {
                Log.e("Error:", "joining thread");
            }
        }

        public void resume() {
            playing = true;
            gameThread = new Thread(this);
            gameThread.start();
        }

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

            if(startOrientation == null){
                startOrientation = deltaRotationMatrix.clone();
            }
            
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();

    }

    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }
}
