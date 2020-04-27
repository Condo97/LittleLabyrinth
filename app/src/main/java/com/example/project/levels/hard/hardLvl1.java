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

public class hardLvl1 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        DisplayMetrics display = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(display);
        Constants.screenWidth = display.widthPixels;
        Constants.screenHeight = display.heightPixels;

        Player player = new Player(new Rect(0, 70, 50, 120), Color.RED);
        Goal goal = new Goal(new Rect(Constants.screenWidth - 140, Constants.screenHeight - 140, Constants.screenWidth - 70, Constants.screenHeight - 70), Color.GREEN);
        int difficultyDeadzoneConstant = 1;
        int difficultyCollisionScoringConstant = 100;

        ArrayList<Obstacle> obstacles = new ArrayList<>();
        obstacles.add(new Obstacle(new Rect(0, 0, Constants.screenWidth/2, 50), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(0, player.getRect().bottom + 14, (Constants.screenWidth/2) - 70, player.getRect().bottom + 64), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(Constants.screenWidth/2, 0, (Constants.screenWidth/2) + 50, Constants.screenHeight/6), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(70, obstacles.get(1).getRect().bottom + 70, Constants.screenWidth/2, Constants.screenHeight/6), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(0, obstacles.get(3).getRect().bottom + 70, (4*Constants.screenWidth/6), obstacles.get(3).getRect().bottom + 120), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(obstacles.get(2).getRect().right + 70, obstacles.get(1).getRect().bottom, (4*Constants.screenWidth/6), obstacles.get(4).getRect().top), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(obstacles.get(2).getRect().right, obstacles.get(1).getRect().top - 50, Constants.screenWidth - 50, obstacles.get(1).getRect().top), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(Constants.screenWidth - 50, 0, Constants.screenWidth, Constants.screenHeight), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(obstacles.get(5).getRect().right, obstacles.get(5).getRect().top, obstacles.get(7).getRect().left - 70, obstacles.get(4).getRect().bottom), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(Constants.screenWidth/3, obstacles.get(4).getRect().bottom + 70, Constants.screenWidth - 50, obstacles.get(4).getRect().bottom + 140), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(obstacles.get(9).getRect().left - 70, obstacles.get(9).getRect().top, obstacles.get(9).getRect().left, (2*Constants.screenHeight/3)), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(obstacles.get(10).getRect().left - 140, obstacles.get(4).getRect().bottom, obstacles.get(10).getRect().left - 70, obstacles.get(10).getRect().bottom + 140), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(obstacles.get(11).getRect().right, obstacles.get(11).getRect().bottom - 70, obstacles.get(4).getRect().right - 70, obstacles.get(11).getRect().bottom), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(obstacles.get(10).getRect().right, obstacles.get(10).getRect().bottom - 70, obstacles.get(12).getRect().right + 140, obstacles.get(10).getRect().bottom), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(obstacles.get(13).getRect().right - 70, obstacles.get(9).getRect().bottom, obstacles.get(13).getRect().right, Constants.screenHeight - 140), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(obstacles.get(12).getRect().right - 70, obstacles.get(12).getRect().bottom, obstacles.get(12).getRect().right, Constants.screenHeight), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(obstacles.get(15).getRect().right, Constants.screenHeight - 70, Constants.screenWidth, Constants.screenHeight), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(obstacles.get(14).getRect().right + 70, Constants.screenHeight/2, obstacles.get(14).getRect().right + 140, Constants.screenHeight - 70), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(obstacles.get(14).getRect().right, obstacles.get(17).getRect().top - 140, Constants.screenWidth, obstacles.get(17).getRect().top - 70), Color.BLACK));

        setContentView(new GamePanel(this.getApplicationContext(), player, goal, difficultyDeadzoneConstant, difficultyCollisionScoringConstant, obstacles));
    }
}
