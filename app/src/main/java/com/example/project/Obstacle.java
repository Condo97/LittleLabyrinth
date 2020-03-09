package com.example.project;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Obstacle implements GameObject{
    private Rect rect;
    private int color;

    public Obstacle(Rect rect, int color){
        this.rect = rect;
        this.color = color;
    }

    public boolean collision(Player player){
        if(rect.contains(player.getRect().left, player.getRect().top)
                || rect.contains(player.getRect().right, player.getRect().top)
                || rect.contains(player.getRect().left, player.getRect().bottom)
                || rect.contains(player.getRect().right, player.getRect().bottom)){
            return true;
        }
        return false;
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rect, paint);
    }

    @Override
    public void update() {

    }

    public Rect getRect(){
        return rect;
    }
}
