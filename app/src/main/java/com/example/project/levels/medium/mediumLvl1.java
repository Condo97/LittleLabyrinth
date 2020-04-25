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

public class mediumLvl1 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        DisplayMetrics display = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(display);
        Constants.screenWidth = display.widthPixels;
        Constants.screenHeight = display.heightPixels;

        Player player = new Player(new Rect(25, 25, 100, 100), Color.RED);
        Goal goal = new Goal(new Rect(Constants.screenWidth - 100, Constants.screenHeight - 100, Constants.screenWidth - 25, Constants.screenHeight - 25), Color.GREEN);
        int difficultyDeadzoneConstant = 2;
        int difficultyCollisionScoringConstant = 50;

        ArrayList<Obstacle> obstacles = new ArrayList<>();
        obstacles.add(new Obstacle(new Rect(0,  200, (int)(.5*Constants.screenWidth), 275), Color.BLACK));
        obstacles.add(new Obstacle(new Rect((int)(.5*Constants.screenWidth) + 110, 200, Constants.screenWidth, 275), Color.BLACK));
        obstacles.add(new Obstacle(new Rect( (int)(.5*Constants.screenWidth) - 75, 275, (int)(.5*Constants.screenWidth), (int)(.8*Constants.screenHeight)), Color.BLACK));
        obstacles.add(new Obstacle(new Rect((int)(.5*Constants.screenWidth), (int)(.2*Constants.screenHeight), (int)(.85*Constants.screenWidth) - 10, (int)(.2*Constants.screenHeight) + 75), Color.BLACK));
        obstacles.add(new Obstacle(new Rect((int)(.5*Constants.screenWidth) + 130, obstacles.get(3).getRect().bottom + 100, Constants.screenWidth, obstacles.get(3).getRect().bottom + 300), Color.BLACK));
        obstacles.add(new Obstacle(new Rect((int)(.5*Constants.screenWidth), (int)(.5*Constants.screenHeight), (int)(.7*Constants.screenWidth) + 100, (int)(.5*Constants.screenHeight) + 75), Color.BLACK));
        obstacles.add(new Obstacle(new Rect((int)(.7*Constants.screenWidth), (int)(.5*Constants.screenHeight) + 75, (int)(.7*Constants.screenWidth) + 75, Constants.screenHeight - 310), Color.BLACK));
        obstacles.add(new Obstacle(new Rect((int)(.5*Constants.screenWidth) + 25, Constants.screenHeight - 200, Constants.screenWidth, Constants.screenHeight - 125), Color.BLACK));

        setContentView(new GamePanel(this.getApplicationContext(), player, goal, difficultyDeadzoneConstant, difficultyCollisionScoringConstant, obstacles));

//        setContentView(new GamePanelM1(this.getApplicationContext()));
    }
}
