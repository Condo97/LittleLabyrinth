package com.example.project.levels.hard;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project.Constants;
import com.example.project.R;
import com.example.project.levels.medium.mediumLvl1;
import com.example.project.levels.medium.mediumLvl2;
import com.example.project.levels.medium.mediumLvl3;

public class hardLevels extends AppCompatActivity {
    Button lvl1, lvl2, lvl3;
    Intent toLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        DisplayMetrics display = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(display);
        Constants.screenWidth = display.widthPixels;
        Constants.screenHeight = display.heightPixels;
        setContentView(R.layout.easy_levels);

        lvl1 = findViewById(R.id.elvl1);
        lvl2 = findViewById(R.id.elvl2);
        lvl3 = findViewById(R.id.elvl3);

        lvl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toLevel = new Intent(getApplicationContext(), mediumLvl1.class);
                startActivity(toLevel);
            }
        });

        lvl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toLevel = new Intent(getApplicationContext(), mediumLvl2.class);
                startActivity(toLevel);
            }
        });

        lvl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toLevel = new Intent(getApplicationContext(), mediumLvl3.class);
                startActivity(toLevel);
            }
        });
    }
}
