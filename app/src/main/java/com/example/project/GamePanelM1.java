package com.example.project;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.SurfaceHolder;

public class GamePanelM1 extends GamePanel implements SurfaceHolder.Callback {
    private MainThread thread;
    private Player play;
    private Point point;
    private Paint paint = new Paint();
    private Goal goal;
    private Obstacle obstacle1;
    private Obstacle obstacle2;
    private Obstacle obstacle3;
    private Obstacle obstacle4;
    private Obstacle obstacle5;
    private Obstacle obstacle6;
    private Obstacle obstacle7;
    private Obstacle obstacle8;
    private Rect rect = new Rect(0, 0, Constants.screenWidth, 100);
    private int numCollide = 0;
    private Controls data;
    private boolean complete = false;
    private long frameTime;
    long completedTime;

    public GamePanelM1(Context context) {
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);
        play = new Player(new Rect(25, 25, 100, 100), Color.RED);
        goal = new Goal(new Rect(Constants.screenWidth - 100, Constants.screenHeight - 100, Constants.screenWidth - 25, Constants.screenHeight - 25), Color.GREEN);
        obstacle1 = new Obstacle(new Rect(0, 200, (int)(.5*Constants.screenWidth), 275), Color.BLACK);
        obstacle2 = new Obstacle(new Rect((int)(.5*Constants.screenWidth) + 110, 200, Constants.screenWidth, 275), Color.BLACK);
        obstacle3 = new Obstacle(new Rect( (int)(.5*Constants.screenWidth) - 75, 275, (int)(.5*Constants.screenWidth), (int)(.8*Constants.screenHeight)), Color.BLACK);
        obstacle4 = new Obstacle(new Rect((int)(.5*Constants.screenWidth), (int)(.2*Constants.screenHeight), (int)(.85*Constants.screenWidth) - 10, (int)(.2*Constants.screenHeight) + 75), Color.BLACK);
        obstacle5 = new Obstacle(new Rect((int)(.5*Constants.screenWidth) + 130, obstacle4.getRect().bottom + 100, Constants.screenWidth, obstacle4.getRect().bottom + 300), Color.BLACK);
        obstacle6 = new Obstacle(new Rect((int)(.5*Constants.screenWidth), (int)(.5*Constants.screenHeight), (int)(.7*Constants.screenWidth) + 100, (int)(.5*Constants.screenHeight) + 75), Color.BLACK);
        obstacle7 = new Obstacle(new Rect((int)(.7*Constants.screenWidth), (int)(.5*Constants.screenHeight) + 75, (int)(.7*Constants.screenWidth) + 75, Constants.screenHeight - 310), Color.BLACK);
        obstacle8 = new Obstacle(new Rect((int)(.5*Constants.screenWidth) + 25, Constants.screenHeight - 200, Constants.screenWidth, Constants.screenHeight - 125), Color.BLACK);

        point = new Point(150, 150);
        data = new Controls(context);
        frameTime = System.currentTimeMillis();
        setFocusable(true);
        setWillNotDraw(false);
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
        while(retry){
            try{
                data.pause();
                thread.setRun(false);
                thread.join();
            }catch (Exception e) {e.printStackTrace();}
            retry = false;
            data.register();
        }
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        invalidate();
        canvas.drawColor(Color.argb(255,  245, 165, 55));
        play.draw(canvas);
        goal.draw(canvas);
        obstacle1.draw(canvas);
        obstacle2.draw(canvas);
        obstacle3.draw(canvas);
        obstacle4.draw(canvas);
        obstacle5.draw(canvas);
        obstacle6.draw(canvas);
        obstacle7.draw(canvas);
        obstacle8.draw(canvas);
        update();
        if(complete){
            paint.setTextSize(40);
            paint.setColor(Color.BLACK);
            drawText(canvas, paint, "Congratulations! Your Score is: " + (completedTime + numCollide));
        }
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

                point.x += Math.abs(xSpeed*elapsedTime) > 2 ? xSpeed*elapsedTime : 0;
                point.y -= Math.abs(ySpeed*elapsedTime) > 2 ? ySpeed*elapsedTime : 0;
            }

            if(obstacle1.collision(play) || obstacle2.collision(play) || obstacle3.collision(play) ||
                obstacle4.collision(play) || obstacle5.collision(play) || obstacle6.collision(play) ||
                obstacle7.collision(play) || obstacle8.collision(play)) {
                numCollide += 50;
            }

            if(point.x < 0)
                point.x = 0;
            else if(point.x > Constants.screenWidth)
                point.x = Constants.screenWidth;
            if(point.y < 0)
                point.y = 0;
            else if(point.y > Constants.screenHeight)
                point.y = Constants.screenHeight;
            if(obstacle1.collision(play)){
                if(obstacle1.getRect().top <= play.getRect().bottom && obstacle1.getRect().top > play.getRect().top){
                    point.y = obstacle1.getRect().top-55;
                }else if(obstacle1.getRect().bottom >= play.getRect().top && obstacle1.getRect().bottom < play.getRect().bottom){
                    point.y = obstacle1.getRect().bottom+55;
                }else if(obstacle1.getRect().left <= play.getRect().right && obstacle1.getRect().left > play.getRect().left){
                    point.x = obstacle1.getRect().left-55;
                }else if(obstacle1.getRect().right >= play.getRect().left && obstacle1.getRect().right < play.getRect().right){
                    point.x = obstacle1.getRect().right+55;
                }
            }else if(obstacle2.collision(play)){
                if(obstacle2.getRect().top <= play.getRect().bottom && obstacle2.getRect().top > play.getRect().top){
                    point.y = obstacle2.getRect().top-55;
                }else if(obstacle2.getRect().bottom >= play.getRect().top && obstacle2.getRect().bottom < play.getRect().bottom){
                    point.y = obstacle2.getRect().bottom+55;
                }else if(obstacle2.getRect().left <= play.getRect().right && obstacle2.getRect().left > play.getRect().left){
                    point.x = obstacle2.getRect().left-55;
                }else if(obstacle2.getRect().right >= play.getRect().left && obstacle2.getRect().right < play.getRect().right){
                    point.x = obstacle2.getRect().right+55;
                }
            }else if(obstacle3.collision(play)) {
                if (obstacle3.getRect().top <= play.getRect().bottom && obstacle3.getRect().top > play.getRect().top) {
                    point.y = obstacle3.getRect().top - 55;
                } else if (obstacle3.getRect().bottom >= play.getRect().top && obstacle3.getRect().bottom < play.getRect().bottom) {
                    point.y = obstacle3.getRect().bottom + 55;
                } else if (obstacle3.getRect().left <= play.getRect().right && obstacle3.getRect().left > play.getRect().left) {
                    point.x = obstacle3.getRect().left - 55;
                } else if (obstacle3.getRect().right >= play.getRect().left && obstacle3.getRect().right < play.getRect().right) {
                    point.x = obstacle3.getRect().right + 55;
                }
            }else if(obstacle4.collision(play)) {
                if (obstacle4.getRect().top <= play.getRect().bottom && obstacle4.getRect().top > play.getRect().top) {
                    point.y = obstacle4.getRect().top - 55;
                } else if (obstacle4.getRect().bottom >= play.getRect().top && obstacle4.getRect().bottom < play.getRect().bottom) {
                    point.y = obstacle4.getRect().bottom + 55;
                } else if (obstacle4.getRect().left <= play.getRect().right && obstacle4.getRect().left > play.getRect().left) {
                    point.x = obstacle4.getRect().left - 55;
                } else if (obstacle4.getRect().right >= play.getRect().left && obstacle4.getRect().right < play.getRect().right) {
                    point.x = obstacle4.getRect().right + 55;
                }
            }else if(obstacle5.collision(play)) {
                if (obstacle5.getRect().top <= play.getRect().bottom && obstacle5.getRect().top > play.getRect().top) {
                    point.y = obstacle5.getRect().top - 55;
                } else if (obstacle5.getRect().bottom >= play.getRect().top && obstacle5.getRect().bottom < play.getRect().bottom) {
                    point.y = obstacle5.getRect().bottom + 55;
                } else if (obstacle5.getRect().left <= play.getRect().right && obstacle5.getRect().left > play.getRect().left) {
                    point.x = obstacle5.getRect().left - 55;
                } else if (obstacle5.getRect().right >= play.getRect().left && obstacle5.getRect().right < play.getRect().right) {
                    point.x = obstacle5.getRect().right + 55;
                }
            }else if(obstacle6.collision(play)) {
                if (obstacle6.getRect().top <= play.getRect().bottom && obstacle6.getRect().top > play.getRect().top) {
                    point.y = obstacle6.getRect().top - 55;
                } else if (obstacle6.getRect().bottom >= play.getRect().top && obstacle6.getRect().bottom < play.getRect().bottom) {
                    point.y = obstacle6.getRect().bottom + 55;
                } else if (obstacle6.getRect().left <= play.getRect().right && obstacle6.getRect().left > play.getRect().left) {
                    point.x = obstacle6.getRect().left - 55;
                } else if (obstacle6.getRect().right >= play.getRect().left && obstacle6.getRect().right < play.getRect().right) {
                    point.x = obstacle6.getRect().right + 55;
                }
            }else if(obstacle7.collision(play)) {
                if (obstacle7.getRect().top <= play.getRect().bottom && obstacle7.getRect().top > play.getRect().top) {
                    point.y = obstacle7.getRect().top - 55;
                } else if (obstacle7.getRect().bottom >= play.getRect().top && obstacle7.getRect().bottom < play.getRect().bottom) {
                    point.y = obstacle7.getRect().bottom + 55;
                } else if (obstacle7.getRect().left <= play.getRect().right && obstacle7.getRect().left > play.getRect().left) {
                    point.x = obstacle7.getRect().left - 55;
                } else if (obstacle7.getRect().right >= play.getRect().left && obstacle7.getRect().right < play.getRect().right) {
                    point.x = obstacle7.getRect().right + 55;
                }
            }else if(obstacle8.collision(play)) {
                if (obstacle8.getRect().top <= play.getRect().bottom && obstacle8.getRect().top > play.getRect().top) {
                    point.y = obstacle8.getRect().top - 55;
                } else if (obstacle8.getRect().bottom >= play.getRect().top && obstacle8.getRect().bottom < play.getRect().bottom) {
                    point.y = obstacle8.getRect().bottom + 55;
                } else if (obstacle8.getRect().left <= play.getRect().right && obstacle8.getRect().left > play.getRect().left) {
                    point.x = obstacle8.getRect().left - 55;
                } else if (obstacle8.getRect().right >= play.getRect().left && obstacle8.getRect().right < play.getRect().right) {
                    point.x = obstacle8.getRect().right + 55;
                }
            }

            play.update(point);

            if(goal.collision(play)){
                complete = true;
                completedTime = System.currentTimeMillis()/1000000000;
            }
        }
    }

    private void drawText(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(rect);
        int cWidth = rect.width();
        paint.getTextBounds(text, 0, text.length(), rect);
        float x = cWidth / 2f - rect.width() / 2f - rect.left;
        float y = 100;
        canvas.drawText(text, x, y, paint);
    }
}
