package com.example.project;


import android.app.Activity;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;

public class easyLvl1 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        DisplayMetrics display = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(display);
        Constants.screenWidth = display.widthPixels;
        Constants.screenHeight = display.heightPixels;

        setContentView(new GamePanel(this));
    }


    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameView = new GameView(this);
        setContentView(gameView);
    }

    class GameView extends SurfaceView implements Runnable {
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

        }

        public void draw() {

            // Make sure our drawing surface is valid or we crash
            if (hold.getSurface().isValid()) {
                // Lock the canvas ready to draw
                canvas = hold.lockCanvas();

                // Draw the background color

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
    */
}
