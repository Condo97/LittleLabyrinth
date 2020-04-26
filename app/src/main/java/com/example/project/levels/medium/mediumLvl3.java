package com.example.project.levels.medium;

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

public class mediumLvl3 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        DisplayMetrics display = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(display);
        Constants.screenWidth = display.widthPixels;
        Constants.screenHeight = display.heightPixels;

        Player player = new Player(new Rect(0, (int)(.25*Constants.screenHeight), 75, (int)(.25*Constants.screenHeight) + 75), Color.RED);
        Goal goal = new Goal(new Rect((int)(.5*Constants.screenWidth) - 25, (int)(.5*Constants.screenHeight) - 25, (int)(.5*Constants.screenWidth) + 25, (int)(.5*Constants.screenHeight) + 25), Color.GREEN);
        int difficultyDeadzoneConstant = 2;
        int difficultyCollisionScoringConstant = 50;

        ArrayList<Obstacle> obstacles = new ArrayList<>();
        obstacles.add(new Obstacle(new Rect(0, player.getRect().bottom + 20, goal.getRect().right + 100, player.getRect().bottom + 90), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(player.getRect().right + 25, 95, player.getRect().right + 100, player.getRect().bottom + 20), Color.BLACK));
        obstacles.add(new Obstacle(new Rect( player.getRect().right + 90, 95, Constants.screenWidth/4, 170), Color.BLACK));
        obstacles.add(new Obstacle(new Rect((Constants.screenWidth/4) + 110, 0, (Constants.screenWidth/4) + 185, player.getRect().top - 75), Color.BLACK));
        obstacles.add(new Obstacle(new Rect((Constants.screenWidth/4) + 70, player.getRect().top - 75, (int)(.8*Constants.screenWidth), player.getRect().top), Color.BLACK));
        obstacles.add(new Obstacle(new Rect((int)(.8*Constants.screenWidth) - 75, player.getRect().top, (int)(.8*Constants.screenWidth), Constants.screenHeight/2 - 150), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(goal.getRect().right + 10, goal.getRect().top - 15, goal.getRect().right + 100, goal.getRect().bottom + 15), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(goal.getRect().left - 30, goal.getRect().top - 15, Constants.screenWidth - 85, goal.getRect().top + 85), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(goal.getRect().right + 60, goal.getRect().top + 200, Constants.screenWidth, Constants.screenHeight), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(goal.getRect().left - 30, goal.getRect().top + 85, goal.getRect().right - 80, Constants.screenHeight - 80), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(player.getRect().right + 110, player.getRect().bottom + 90, goal.getRect().left - 220, Constants.screenHeight), Color.BLACK));

        setContentView(new GamePanel(this.getApplicationContext(), player, goal, difficultyDeadzoneConstant, difficultyCollisionScoringConstant, obstacles));
    }
}
