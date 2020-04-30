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

public class hardLvl2 extends Activity {
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

        //This is the difficulty collision constant
        int difficultyCollisionConstant = 30;

        int playerHeight = player.getRect().top - player.getRect().bottom;

        ArrayList<Obstacle> obstacles = new ArrayList<>();
        obstacles.add(new Obstacle(new Rect(0, 0, Constants.screenWidth, 50), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(0, player.getRect().bottom + 14, Constants.screenWidth - 150, player.getRect().bottom + 64), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(150, playerHeight + 350, Constants.screenWidth, playerHeight + 400), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(0, playerHeight + 500, Constants.screenWidth - 150, playerHeight + 550), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(0, playerHeight + 650, Constants.screenWidth / 3, playerHeight + 1400), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(2 * Constants.screenWidth / 3, playerHeight + 650, Constants.screenWidth, playerHeight + 1400), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(Constants.screenWidth / 3 + 100, playerHeight + 700, 2 * Constants.screenWidth / 3 - 100, playerHeight + 850), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(Constants.screenWidth / 3 + 100, playerHeight + 825, 2 * Constants.screenWidth / 3, playerHeight + 850), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(Constants.screenWidth / 3 + 100, playerHeight + 950, 2 * Constants.screenWidth / 3 - 100, playerHeight + 1100), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(Constants.screenWidth / 3, playerHeight + 1075, 2 * Constants.screenWidth / 3 - 100, playerHeight + 1100), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(Constants.screenWidth / 3 + 100, playerHeight + 1200, 2 * Constants.screenWidth / 3 - 100, playerHeight + 1350), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(Constants.screenWidth / 3 + 100, playerHeight + 1325, 2 * Constants.screenWidth / 3, playerHeight + 1350), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(150, playerHeight + 1500, Constants.screenWidth, playerHeight + 1550), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(0, playerHeight + 1650, Constants.screenWidth / 2 - 150, playerHeight + 1700), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(150, playerHeight + 1800, Constants.screenWidth / 2, playerHeight + 1850), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(0, playerHeight + 1950, Constants.screenWidth / 2 - 150, playerHeight + 2000), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(150, playerHeight + 2100, Constants.screenWidth / 2, playerHeight + 2150), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(0, playerHeight + 2250, Constants.screenWidth / 2 - 150, playerHeight + 2300), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(0, Constants.screenHeight - 50, Constants.screenWidth, Constants.screenHeight), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(Constants.screenWidth / 2, playerHeight + 1500, Constants.screenWidth / 2 + 50, Constants.screenHeight - 200), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(Constants.screenWidth / 2 + 150, playerHeight + 1650, Constants.screenWidth / 2 + 200, Constants.screenHeight - 50), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(Constants.screenWidth / 2 + 300, playerHeight + 1500, Constants.screenWidth / 2 + 350, Constants.screenHeight - 200), Color.BLACK));
        obstacles.add(new Obstacle(new Rect(Constants.screenWidth / 2 + 450, playerHeight + 1650, Constants.screenWidth / 2 + 500, Constants.screenHeight - 50), Color.BLACK));



        setContentView(new GamePanel(this.getApplicationContext(), player, goal, difficultyDeadzoneConstant, difficultyCollisionScoringConstant, difficultyCollisionConstant, obstacles));
    }
}
