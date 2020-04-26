package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.example.project.levels.easy.easyLevels;
import com.example.project.levels.hard.hardLevels;
import com.example.project.levels.medium.mediumLevels;

public class MainActivity extends AppCompatActivity {
    Button easy, medium, hard, help;
    Intent toDifficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        DisplayMetrics display = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(display);
        Constants.screenWidth = display.widthPixels;
        Constants.screenHeight = display.heightPixels;
        setContentView(R.layout.activity_main);
        startService(new Intent(this, MusicService.class));

        easy = findViewById(R.id.easy);
        medium = findViewById(R.id.medium);
        hard = findViewById(R.id.hard);
        help = findViewById(R.id.about);

        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDifficulty = new Intent(getApplicationContext(), easyLevels.class);
                startActivity(toDifficulty);
            }
        });

        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDifficulty = new Intent(getApplicationContext(), mediumLevels.class);
                startActivity(toDifficulty);
            }
        });

        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDifficulty = new Intent(getApplicationContext(), hardLevels.class);
                startActivity(toDifficulty);
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDifficulty = new Intent(getApplicationContext(), Help.class);
                startActivity(toDifficulty);
            }
        });
    }
}
