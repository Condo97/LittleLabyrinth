package com.example.project;

import android.content.Intent;
import android.os.Bundle;
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
        setContentView(R.layout.easy_levels);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

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
