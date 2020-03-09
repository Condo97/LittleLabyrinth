package com.example.project;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class Goal implements GameObject {
    private Rect rect;
    private int color;

    public Goal(Rect rect, int color){
        this.rect = rect;
        this.color = color;
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

    public boolean collision(Player player){
        return Rect.intersects(rect, player.getRect());
    }

    public Rect getRect(){
        return rect;
    }
}
