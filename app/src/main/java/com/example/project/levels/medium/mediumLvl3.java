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

        Player player = new Player(new Rect(10, (int)(.25*Constants.screenHeight), 60, (int)(.25*Constants.screenHeight) + 50), Color.RED);
        Goal goal = new Goal(new Rect((int)(.5*Constants.screenWidth) - 25, (int)(.5*Constants.screenHeight) - 25, (int)(.5*Constants.screenHeight) + 25, (int)(.5*Constants.screenHeight) + 25), Color.GREEN);
        int difficultyDeadzoneConstant = 2;
        int difficultyCollisionScoringConstant = 50;

        ArrayList<Obstacle> obstacles = new ArrayList<>();
        obstacles.add(new Obstacle(new Rect(0, player.getRect().bottom + 20, goal.getRect().right + 100, player.getRect().bottom + 95), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(player.getRect().right + 15, 75, player.getRect().right + 90, player.getRect().bottom + 20), Color.BLACK));
        obstacles.add(new Obstacle(new Rect( player.getRect().right + 90, 75, Constants.screenWidth/4, 150), Color.BLACK));

        setContentView(new GamePanel(this.getApplicationContext(), player, goal, difficultyDeadzoneConstant, difficultyCollisionScoringConstant, obstacles));
    }
}
