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

        Player player = new Player(new Rect(25, 25, 100, 100), Color.RED);
        Goal goal = new Goal(new Rect(Constants.screenWidth - 100, Constants.screenHeight - 100, Constants.screenWidth - 25, Constants.screenHeight - 25), Color.GREEN);
        int difficultyDeadzoneConstant = 1;
        int difficultyCollisionScoringConstant = 100;

        //This is the difficulty collision constant
        int difficultyCollisionConstant = 30;

        ArrayList<Obstacle> obstacles = new ArrayList<>();
    }
}
