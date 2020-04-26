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

//ree why wont you push
public class easyLvl2 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        DisplayMetrics display = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(display);
        Constants.screenWidth = display.widthPixels;
        Constants.screenHeight = display.heightPixels;

        //So all objects are created here rather than the GamePanel classes
        Player player = new Player(new Rect(100, 100, 200, 200), Color.RED);
        Goal goal = new Goal(new Rect(Constants.screenWidth - 200, Constants.screenHeight - 200, Constants.screenWidth - 100, Constants.screenHeight - 100), Color.GREEN);

        //This was the deadzone thing that was 5 in easy, 2 in medium, and was established as 1 in hard
        int difficultyDeadzoneConstant = 5;

        //This was the collision scoring constant that was 10 in easy, 50 in medium, and was established as 100 in hard
        int difficultyCollisionScoringConstant = 10;

        //All obstacles in the game are added to this ArrayList
        ArrayList<Obstacle> obstacles2 = new ArrayList<>();
        obstacles2.add(new Obstacle(new Rect(0, 225, Constants.screenWidth - 400, 325), Color.BLACK));
        obstacles2.add(new Obstacle(new Rect(400, obstacles2.get(0).getRect().bottom + 175, Constants.screenWidth, obstacles2.get(0).getRect().bottom + 275), Color.BLACK));
        obstacles2.add(new Obstacle(new Rect(0, 225 + Constants.screenHeight / 2, Constants.screenWidth - 400, 325 + Constants.screenHeight / 2), Color.BLACK));
        obstacles2.add(new Obstacle(new Rect(400, obstacles2.get(2).getRect().bottom + 150, Constants.screenWidth, obstacles2.get(2).getRect().bottom + 250), Color.BLACK));

        //GamePanel is now one class rather than a new GamePanel for each level, and must include these variables
        setContentView(new GamePanel(this.getApplicationContext(), player, goal, difficultyDeadzoneConstant, difficultyCollisionScoringConstant, obstacles2));
    }
}
