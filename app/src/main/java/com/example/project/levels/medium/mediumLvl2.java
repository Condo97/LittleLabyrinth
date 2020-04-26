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

public class mediumLvl2 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        DisplayMetrics display = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(display);
        Constants.screenWidth = display.widthPixels;
        Constants.screenHeight = display.heightPixels;

        Goal goal = new Goal(new Rect(200, 25, 275, 100), Color.GREEN);
        int difficultyDeadzoneConstant = 2;
        int difficultyCollisionScoringConstant = 50;

        ArrayList<Obstacle> obstacles = new ArrayList<>();
        Obstacle obstacle1 = new Obstacle(new Rect((int)(.4*Constants.screenWidth), (int)(.5*Constants.screenHeight) - 155, Constants.screenWidth, (int)(.5*Constants.screenHeight) - 80), Color.BLACK);
        Obstacle obstacle2 = new Obstacle(new Rect((int)(.4*Constants.screenWidth), (int)(.5*Constants.screenHeight) + 20, Constants.screenWidth - 85, (int)(.5*Constants.screenHeight) + 95), Color.BLACK);
        Obstacle obstacle3 = new Obstacle(new Rect( (int)(.4*Constants.screenWidth) - 75, 300, (int)(.4*Constants.screenWidth), Constants.screenHeight - 100), Color.BLACK);
        Obstacle obstacle4 = new Obstacle(new Rect(obstacle3.getRect().right + 110, obstacle2.getRect().bottom + 100, Constants.screenWidth, obstacle2.getRect().bottom + 175), Color.BLACK);
        Obstacle obstacle6 = new Obstacle(new Rect(obstacle3.getRect().right, obstacle3.getRect().bottom - 75, Constants.screenWidth - 100, obstacle3.getRect().bottom), Color.BLACK);
        Obstacle obstacle7 = new Obstacle(new Rect(100, obstacle2.getRect().top, obstacle3.getRect().left, obstacle2.getRect().bottom), Color.BLACK);
        Obstacle obstacle8 = new Obstacle(new Rect(obstacle7.getRect().right, obstacle7.getRect().top, obstacle3.getRect().left, obstacle2.getRect().bottom), Color.BLACK);
        Obstacle obstacle9 = new Obstacle(new Rect(80,0, goal.getRect().left - 10, obstacle1.getRect().bottom), Color.BLACK);
        Obstacle obstacle10 = new Obstacle(new Rect(obstacle9.getRect().right, 125, obstacle4.getRect().left, 200), Color.BLACK);
        Obstacle obstacle5 = new Obstacle(new Rect(obstacle4.getRect().left + 35, obstacle4.getRect().bottom, obstacle4.getRect().left + 150, obstacle6.getRect().top - 100), Color.BLACK);

        Player player = new Player(new Rect(obstacle3.getRect().right - 10, obstacle1.getRect().bottom - 2, obstacle3.getRect().right + 75, obstacle2.getRect().top - 20), Color.RED);

        obstacles.add(obstacle1);
        obstacles.add(obstacle2);
        obstacles.add(obstacle3);
        obstacles.add(obstacle4);
        obstacles.add(obstacle5);
        obstacles.add(obstacle6);
        obstacles.add(obstacle7);
        obstacles.add(obstacle8);
        obstacles.add(obstacle9);
        obstacles.add(obstacle10);

        setContentView(new GamePanel(this.getApplicationContext(), player, goal, difficultyDeadzoneConstant, difficultyCollisionScoringConstant, obstacles));
    }
}