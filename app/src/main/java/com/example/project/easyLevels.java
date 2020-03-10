package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class easyLevels extends AppCompatActivity
{
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
                toLevel = new Intent(getApplicationContext(), easyLvl1.class);
                startActivity(toLevel);
            }
        });
    }
}
