package com.itstep.holemole;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConstraintLayout gamePad = findViewById(R.id.gamePad);

        ImageView hole = new ImageView(this);
        hole.setImageResource(R.drawable.hole);


        gamePad.addView(hole);

    }
}