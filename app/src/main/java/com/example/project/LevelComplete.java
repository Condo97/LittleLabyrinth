package com.example.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LevelComplete extends Activity {
    Intent back;
    Intent intent = getIntent();

    Button toHome;
    TextView score;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complete_score);

        toHome = findViewById(R.id.toHome);
        score = findViewById(R.id.score);

        int numCollide = intent.getIntExtra("numCollide", -1);
        int completedTime = intent.getIntExtra("completedTime", -1);

        int totalScore = numCollide + completedTime;

        score.setText(totalScore);

        toHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(back);
            }
        });
    }
}
