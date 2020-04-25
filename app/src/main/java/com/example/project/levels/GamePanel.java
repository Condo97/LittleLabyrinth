package com.example.project.levels;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.project.Constants;
import com.example.project.Controls;
import com.example.project.Goal;
import com.example.project.MainThread;
import com.example.project.Obstacle;
import com.example.project.Player;

import java.util.ArrayList;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread mainThread;
    private Point point;
    private Paint paint = new Paint();
    private Rect screenRect = new Rect(0, 0, Constants.screenWidth, 100);
    private int numCollide = 0;
    private Controls data;
    private boolean complete = false;
    private long frameTime;

    private Player player;
    private Goal goal;
    private int difficultyDeadzoneConstant, difficultyCollisionScoringConstant;
    private ArrayList<Obstacle> obstacles = new ArrayList<>();

    public long completedTime;

    public GamePanel(Context context, Player player, Goal goal, int difficultyDeadzoneConstant, int difficultyCollisionScoringConstant, ArrayList<Obstacle> obstacles) {
        super(context);

        //Instantiate common objects
        mainThread = new MainThread(getHolder(), this);
        point = new Point(150, 150);
        data = new Controls(context);
        frameTime = System.currentTimeMillis();
        setFocusable(true);
        setWillNotDraw(false);

        //SET IN SUBCLASS: player, goal, obstacles, difficultyDeadzoneConstant, difficultyCollisionScoringConstant
        this.player = player;
        this.goal = goal;
        this.difficultyDeadzoneConstant = difficultyDeadzoneConstant;
        this.difficultyCollisionScoringConstant = difficultyCollisionScoringConstant;
        this.obstacles = obstacles;
    }


    /******** SurfaceHolder Overrides *******/

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mainThread = new MainThread(getHolder(), this);
        mainThread.setRun(true);
        data.newGame();
        data.register();
        mainThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while(retry){
            try {
                data.pause();
                mainThread.setRun(false);
                mainThread.join();
            } catch (Exception e) { e.printStackTrace(); }

            retry = false;
            data.register();
        }
    }


    /******* View Overrides *******/

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        invalidate();
        canvas.drawColor(Constants.backgroundColor);
        player.draw(canvas);
        goal.draw(canvas);

        for(Obstacle obstacle: obstacles) obstacle.draw(canvas);

        update();

        if(complete) {
            paint.setTextSize(40);
            paint.setColor(Color.BLACK);
            drawText(canvas, paint, "Congratulations! Your Score is: " + (completedTime + numCollide));
        }
    }


    /******* GamePanel Functions *******/

    public void update() {
        if(!complete) {
            int elapsedTime = (int)(System.currentTimeMillis() - frameTime);
            frameTime = System.currentTimeMillis();
            if(data.getOrientation() != null && data.getStartOrientation() != null) {
                float pitch = data.getOrientation()[1] - data.getStartOrientation()[1];
                float roll = data.getOrientation()[2] - data.getStartOrientation()[2];

                float xSpeed = 2 * roll * Constants.screenWidth/1000f;
                float ySpeed = pitch * Constants.screenHeight/1000f;

                point.x += Math.abs(xSpeed*elapsedTime) > difficultyDeadzoneConstant ? xSpeed*elapsedTime : 0;
                point.y -= Math.abs(ySpeed*elapsedTime) > difficultyDeadzoneConstant ? ySpeed*elapsedTime : 0;
            }

            for(Obstacle obstacle: obstacles) if(obstacle.collision(player)) numCollide += difficultyCollisionScoringConstant;

            if(point.x < 0) point.x = 0;
            else if(point.x > Constants.screenWidth) point.x = Constants.screenWidth;

            if(point.y < 0) point.y = 0;
            else if(point.y > Constants.screenHeight) point.y = Constants.screenHeight;

            for(Obstacle obstacle: obstacles) {
                if(obstacle.collision(player)) {
                    if(obstacle.getRect().top <= player.getRect().bottom && obstacle.getRect().top > player.getRect().top) point.y = obstacle.getRect().top - Constants.collisionConstant;
                    else if(obstacle.getRect().bottom >= player.getRect().top && obstacle.getRect().bottom < player.getRect().bottom) point.y = obstacle.getRect().bottom + Constants.collisionConstant;
                    else if(obstacle.getRect().left <= player.getRect().right && obstacle.getRect().left > player.getRect().left) point.x = obstacle.getRect().left - Constants.collisionConstant;
                    else if(obstacle.getRect().right >= player.getRect().left && obstacle.getRect().right < player.getRect().right) point.x = obstacle.getRect().right + Constants.collisionConstant;
                }
            }

            player.update(point);

            if(goal.collision(player)) {
                complete = true;
                completedTime = System.currentTimeMillis() / 1000000000;
            }
        }
    }

    private void drawText(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(screenRect);
        int cWidth = screenRect.width();
        paint.getTextBounds(text, 0, text.length(), screenRect);
        float x = cWidth / 2f - screenRect.width() / 2f - screenRect.left;
        float y = 100;
        canvas.drawText(text, x, y, paint);
    }
}
