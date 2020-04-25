package com.example.project.levels.easy;


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

public class easyLvl1 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        DisplayMetrics display = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(display);
        Constants.screenWidth = display.widthPixels;
        Constants.screenHeight = display.heightPixels;

        Player player = new Player(new Rect(100, 100, 200, 200), Color.RED);
        Goal goal = new Goal(new Rect(Constants.screenWidth - 200, Constants.screenHeight - 200, Constants.screenWidth - 100, Constants.screenHeight - 100), Color.GREEN);
        int difficultyDeadzoneConstant = 5;
        int difficultyCollisionScoringConstant = 10;

        ArrayList<Obstacle> obstacles2 = new ArrayList<>();
        obstacles2.add(new Obstacle(new Rect(0, 225, Constants.screenWidth - 400, 325), Color.BLACK));
        obstacles2.add(new Obstacle(new Rect(400, Constants.screenHeight - 500, Constants.screenWidth, Constants.screenHeight - 400), Color.BLACK));
        obstacles2.add(new Obstacle(new Rect( 300, Constants.screenHeight/2 - 50, Constants.screenWidth - 300, Constants.screenHeight/2 + 50), Color.BLACK));

        setContentView(new GamePanel(this.getApplicationContext(), player, goal, difficultyDeadzoneConstant, difficultyCollisionScoringConstant, obstacles2));
    }
}
