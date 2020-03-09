package com.example.project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private Activity game = new Activity();
    private MainThread thread;
    private Player play;
    private Point point;
    private Goal goal;
    private Obstacle obstacle1;
    private Obstacle obstacle2;
    private Obstacle obstacle3;
    private Obstacle obstacle4;
    private Obstacle obstacle5;
    private int numCollide = 0;
    private Controls data;
    private boolean complete = false;
    private long frameTime;

    public GamePanel(Context context) {
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        play = new Player(new Rect(100, 100, 200, 200), Color.RED);
        goal = new Goal(new Rect(800, 1500, 925, 1625), Color.GREEN);
        point = new Point(150, 150);
        data = new Controls();
        frameTime = System.currentTimeMillis();
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){
        thread = new MainThread(getHolder(), this);
        thread.setRun(true);
        data.newGame();
        data.register();
        thread.start();
    }

    public void surfaceDestroyed(SurfaceHolder holder){
        boolean retry = true;
        data.pause();
        while(retry){
            try{
                thread.setRun(false);
                thread.join();
            }catch (Exception e) {e.printStackTrace();}
            retry = false;
            data.register();
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
            int elapsedTime = (int)(System.currentTimeMillis() - frameTime);
            frameTime = System.currentTimeMillis();
            if(data.getOrientation() != null && data.getStartOrientation() != null) {
                float pitch = data.getOrientation()[1] - data.getStartOrientation()[1];
                float roll = data.getOrientation()[2] - data.getStartOrientation()[2];

                float xSpeed = 2 * roll * Constants.screenWidth/1000f;
                float ySpeed = pitch * Constants.screenHeight/1000f;

                point.x += Math.abs(xSpeed*elapsedTime) > 5 ? xSpeed*elapsedTime : 0;
                point.y -= Math.abs(ySpeed*elapsedTime) > 5 ? ySpeed*elapsedTime : 0;
            }

            if(point.x < 0)
                point.x = 0;
            else if(point.x > Constants.screenWidth)
                point.x = Constants.screenWidth;
            if(point.y < 0)
                point.y = 0;
            else if(point.y > Constants.screenHeight)
                point.y = Constants.screenHeight;

            play.update(point);

            if(obstacle1.collision(play) || obstacle2.collision(play)
                    || obstacle3.collision(play) || obstacle4.collision(play)
                    || obstacle5.collision(play)) {
                numCollide++;
            }

            if(goal.collision(play)){
                complete = true;
                long completedTime = System.currentTimeMillis();
                Intent intent = new Intent(getContext(), LevelComplete.class);
                intent.putExtra("numCollide", numCollide);
                intent.putExtra("completedTime", (int)(completedTime*1000));
                game.startActivity(intent);
            }
        }
    }
}
