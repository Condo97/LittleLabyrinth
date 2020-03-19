package com.example.project;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class Player implements GameObject {
    private Rect rect;
    private int color;
    private Animation idle;
    private Animation moveLeft;
    private Animation moveRight;
    private AnimationManager animationManager;

    public Player(Rect rect, int color){
        this.rect = rect;
        this.color = color;

        BitmapFactory bitmapFactory = new BitmapFactory();
        Bitmap idleImg = bitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.ball2);
        Bitmap rotate30R = bitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.ball2);
        Bitmap rotate60R = bitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.ball2);

        idle = new Animation(new Bitmap[]{idleImg}, 1);
        moveRight = new Animation(new Bitmap[]{idleImg, rotate30R, rotate60R}, 0.2f);

        Matrix mat = new Matrix();
        mat.preScale(-1, 1);
        Bitmap rotate30L = Bitmap.createBitmap(rotate30R, 0, 0, rotate30R.getWidth(), rotate30R.getHeight(), mat, false);
        Bitmap rotate60L = Bitmap.createBitmap(rotate60R, 0, 0, rotate60R.getWidth(), rotate60R.getHeight(), mat, false);

        moveLeft = new Animation(new Bitmap[]{idleImg, rotate30R, rotate60R}, 0.2f);

        animationManager = new AnimationManager(new Animation[]{idle, moveRight, moveLeft});
    }

    @Override
    public void draw(Canvas canvas) {
        //Paint paint = new Paint();
        //paint.setColor(color);
        //canvas.drawRect(rect, paint);

        animationManager.onDraw(canvas, rect);
    }

    @Override
    public void update() {
        animationManager.update();
    }

    public void update(Point point) {
        float oldLeft = rect.left;

        rect.set(point.x - rect.width()/2, point.y - rect.height()/2, point.x + rect.width()/2, point.y + rect.height()/2);

        int moveState = 0;
        if(rect.left - oldLeft > 5)
            moveState = 1;
        else if(rect.left - oldLeft < -5)
            moveState = 2;

        animationManager.playAnimation(moveState);
        animationManager.update();
    }

    public Rect getRect(){
        return rect;
    }
}
