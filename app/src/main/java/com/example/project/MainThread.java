package com.example.project;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

import com.example.project.levels.easy.GamePanelE1;

public class MainThread extends Thread {
    public static final int MAX_FPS = 30;
    private double avgFPS;
    private SurfaceHolder holder;
    private GamePanelE1 panel;
    private boolean isRunning;
    public static Canvas canvas;

    public MainThread(SurfaceHolder holder, GamePanelE1 panel){
        super();
        this.holder = holder;
        this.panel = panel;
    }

    @Override
    public void run(){
        long startTime;
        long timeMillis = 1000/MAX_FPS;
        long targetTime = 1000/MAX_FPS;
        long waitTime;
        long totalTime = 0;
        int frameCount = 0;

        while(isRunning){
            startTime = System.nanoTime();
            canvas = null;

            try{
                canvas = this.holder.lockCanvas();
                synchronized (holder){
                    this.panel.update();
                    this.panel.draw(canvas);
                }
            }catch (Exception e) {e.printStackTrace();}finally {
                if(canvas != null){
                    try {
                        holder.unlockCanvasAndPost(canvas);
                    }catch (Exception e) {e.printStackTrace();}
                }
            }
            timeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime - timeMillis;
            try{
                if(waitTime > 0){
                    this.sleep(waitTime);
                }
            }catch (Exception e) {e.printStackTrace();}

            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if(frameCount == MAX_FPS){
                avgFPS = 1000/((totalTime/frameCount)/1000000);
                frameCount = 0;
                totalTime = 0;
            }
        }
    }

    public void setRun(boolean isRunning){
        this.isRunning = isRunning;
    }
}
