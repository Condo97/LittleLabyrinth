package com.example.project;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;
    private Player play;
    private Goal goal;
    private Point point;
    private boolean complete = false;
    private long completedTime;

    public GamePanel(Context context){
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        play = new Player(new Rect(100, 100, 200, 200), Color.RED);
        goal = new Goal(new Rect(800, 1500, 925, 1625), Color.BLUE);
        point = new Point(150, 150);
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        thread = new MainThread(getHolder(), this);
        thread.setRun(true);
        thread.start();
    }

    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        while(retry){
            try{
                thread.setRun(false);
                thread.join();
            }catch (Exception e) {e.printStackTrace();}
            retry = false;
        }
    }

    @Override
    public void draw(Canvas canvas){
        super.draw(canvas);
        canvas.drawColor(Color.argb(255,  249, 162, 53));
        play.draw(canvas);
        goal.draw(canvas);
    }

    public void update(){
        if(!complete) {
            play.update();

            if(goal.collision(play)){
                complete = true;
                completedTime = System.currentTimeMillis();
            }
        }
    }
}
