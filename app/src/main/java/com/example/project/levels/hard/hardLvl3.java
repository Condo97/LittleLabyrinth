package com.example.project.levels.hard;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

import com.example.project.Constants;
import com.example.project.Goal;
import com.example.project.Obstacle;
import com.example.project.Player;
import com.example.project.levels.GamePanel;

import java.util.ArrayList;

public class hardLvl3 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        DisplayMetrics display = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(display);
        Constants.screenWidth = display.widthPixels;
        Constants.screenHeight = display.heightPixels;

        Player player = new Player(new Rect(70, Constants.screenHeight/3, 120, (Constants.screenHeight/3) + 50), Color.RED);
        Goal goal = new Goal(new Rect(Constants.screenWidth - 120, Constants.screenHeight - 120, Constants.screenWidth - 70, Constants.screenHeight - 70), Color.GREEN);
        int difficultyDeadzoneConstant = 1;
        int difficultyCollisionScoringConstant = 100;

        //This is the difficulty collision constant
        int difficultyCollisionConstant = 30;
        //
        ArrayList<Obstacle> obstacles = new ArrayList<>();
        obstacles.add(new Obstacle(new Rect(0, 0, 50, Constants.screenHeight), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(0, Constants.screenHeight/3, 190, (Constants.screenHeight/3) + 50 ), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(140, 140, 190, Constants.screenHeight/3), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(0, 0, Constants.screenWidth, 50), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(obstacles.get(2).getRect().right, obstacles.get(2).getRect().top, (Constants.screenWidth/2) - 95, obstacles.get(2).getRect().top + 50), Color.BLACK));
        obstacles.add(new Obstacle(new Rect((Constants.screenWidth/2) - 25, 0, (Constants.screenWidth/2) + 25, Constants.screenHeight/4), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(obstacles.get(4).getRect().right - 50, obstacles.get(4).getRect().bottom, obstacles.get(4).getRect().right, obstacles.get(5).getRect().bottom + 70), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(obstacles.get(6).getRect().left, obstacles.get(6).getRect().bottom, obstacles.get(5).getRect().right + 80, obstacles.get(6).getRect().bottom + 50), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(obstacles.get(7).getRect().right, obstacles.get(4).getRect().top, Constants.screenWidth - 120, obstacles.get(4).getRect().bottom), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(obstacles.get(7).getRect().right, obstacles.get(8).getRect().top, obstacles.get(7).getRect().right + 50, obstacles.get(7).getRect().bottom), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(Constants.screenWidth - 50, 0, Constants.screenWidth, Constants.screenHeight), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(obstacles.get(8).getRect().right - 50, obstacles.get(8).getRect().bottom, obstacles.get(8).getRect().right, Constants.screenHeight/3), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(obstacles.get(4).getRect().right - 50, obstacles.get(11).getRect().bottom, obstacles.get(11).getRect().right, obstacles.get(11).getRect().bottom + 50), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(obstacles.get(12).getRect().left + 120, obstacles.get(12).getRect().bottom + 70, Constants.screenWidth, obstacles.get(12).getRect().bottom + 120), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(obstacles.get(12).getRect().left, obstacles.get(12).getRect().bottom, obstacles.get(12).getRect().left + 50, (2*Constants.screenHeight/3)), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(obstacles.get(13).getRect().left, obstacles.get(13).getRect().bottom, obstacles.get(13).getRect().left + 50, (2*Constants.screenHeight/3) + 70), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(0, obstacles.get(14).getRect().bottom - 50, obstacles.get(14).getRect().left, obstacles.get(14).getRect().bottom), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(120, obstacles.get(15).getRect().bottom, obstacles.get(15).getRect().right + 70, obstacles.get(15).getRect().bottom + 50), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(120, obstacles.get(17).getRect().bottom, 170, Constants.screenHeight - 120), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(0, Constants.screenHeight - 50, Constants.screenWidth, Constants.screenHeight), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(obstacles.get(18).getRect().right, obstacles.get(18).getRect().bottom - 50, obstacles.get(6).getRect().right - 50, obstacles.get(18).getRect().bottom), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(obstacles.get(20).getRect().right, obstacles.get(14).getRect().bottom + 70, obstacles.get(20).getRect().right + 50, obstacles.get(20).getRect().bottom), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(obstacles.get(15).getRect().left, obstacles.get(17).getRect().bottom + 70, obstacles.get(15).getRect().right, Constants.screenHeight), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(obstacles.get(17).getRect().right, obstacles.get(17).getRect().top, obstacles.get(17).getRect().right + 50, Constants.screenHeight - 120), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(obstacles.get(23).getRect().right, obstacles.get(23).getRect().bottom - 50, obstacles.get(23).getRect().right + 70, obstacles.get(23).getRect().bottom), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(obstacles.get(24).getRect().right, Constants.screenHeight/2, obstacles.get(24).getRect().right + 50, obstacles.get(24).getRect().bottom), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(obstacles.get(25).getRect().right + 70, obstacles.get(25).getRect().top + 120, goal.getRect().left - 20, Constants.screenHeight), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(obstacles.get(25).getRect().right, obstacles.get(25).getRect().top, Constants.screenWidth, obstacles.get(25).getRect().top + 50), Color.BLACK));

        setContentView(new GamePanel(this.getApplicationContext(), player, goal, difficultyDeadzoneConstant, difficultyCollisionScoringConstant, difficultyCollisionConstant, obstacles));
    }
}