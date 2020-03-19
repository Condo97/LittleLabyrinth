package com.example.project;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Animation {
    private Bitmap[] frames;
    private int frameIndex;
    private boolean isPlaying = false;
    private float frameTime;
    private long lastFrame;

    public Animation(Bitmap[] frames, float animationTime){
        this.frames = frames;
        frameIndex = 0;
        frameTime = animationTime/frames.length;
        lastFrame = System.currentTimeMillis();
    }

    public void update(){
        if(!isPlaying)
            return;
        if(System.currentTimeMillis() - lastFrame > frameTime*1000){
            frameIndex++;
            frameIndex = frameIndex >= frames.length ? 0 : frameIndex;
            lastFrame = System.currentTimeMillis();
        }
    }

    public void onDraw(Canvas canvas, Rect dest){
        if(!isPlaying)
            return;

        canvas.drawBitmap(frames[frameIndex], null, dest, new Paint());
    }

    public void play(){
        isPlaying = true;
        frameIndex = 0;
        lastFrame = System.currentTimeMillis();
    }

    public void stop(){
        isPlaying = false;
    }

    public boolean isPlaying(){
        return isPlaying;
    }
}
