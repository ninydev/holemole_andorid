package com.itstep.holemole;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Random;

public class MainActivity_27_10 extends AppCompatActivity {
     int maxX;
     int maxY ;
    ConstraintLayout gamePad;
    Random rnd = new Random();
    ImageView mole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hole_mole);

        gamePad = (ConstraintLayout)findViewById(R.id.gamePad);

        mole = new ImageView(this);
        mole.setImageResource(R.drawable.mole);
        ConstraintLayout.LayoutParams moleLayoutParams = new ConstraintLayout.LayoutParams(
                WRAP_CONTENT, WRAP_CONTENT);
        moleLayoutParams.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
        moleLayoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        mole.setLayoutParams(moleLayoutParams);

        mole.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Ok", Toast.LENGTH_LONG);
                Log.i("Game", "Click");
            }
        });



        ViewTreeObserver vto = gamePad.getViewTreeObserver();
        vto.addOnGlobalLayoutListener (new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                gamePad.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                maxX = gamePad.getMeasuredWidth();
                maxY = gamePad.getMeasuredHeight();

                buildGamePad();
            }
        });

    }

    void buildGamePad() {
        Log.i("geometry", "maxX= " + maxX + "maxY" + maxY);

        gamePad.removeAllViews();

        for(int h = 0; h < 5; h++) {

            ImageView hole = new ImageView(this);
            hole.setImageResource(R.drawable.hole);
            // hole.setPadding(100,100,0,0);

            ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                    WRAP_CONTENT, WRAP_CONTENT);

            int x = rnd.nextInt(maxX - 250);
            int y = rnd.nextInt(maxY - 100);
            layoutParams.leftMargin = x;
            layoutParams.topMargin = y;
            layoutParams.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
            layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;

            hole.setLayoutParams(layoutParams);

            gamePad.addView(hole);
        }

        ImageView hole = new ImageView(this);
        hole.setImageResource(R.drawable.hole);
        // hole.setPadding(100,100,0,0);

        ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(
                WRAP_CONTENT, WRAP_CONTENT);


        layoutParams.leftMargin = maxX - 250;
        layoutParams.topMargin = maxY - 100;
        layoutParams.leftToLeft = ConstraintLayout.LayoutParams.PARENT_ID;
        layoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;

        hole.setLayoutParams(layoutParams);

        gamePad.addView(hole);

    }

    ImageView oldHole;

    public void restart(View view) {
        buildGamePad();
    }

    public void moveMole(View view) {
//        if (oldHole != null)
//            oldHole.setImageResource(R.drawable.hole);
        // oldHole.setImageResource(R.drawable.mole);

        int count = gamePad.getChildCount();
        oldHole = (ImageView) gamePad.getChildAt(rnd.nextInt(count));

        gamePad.removeView(mole);
        ConstraintLayout.LayoutParams holeLayoutParams =
                (ConstraintLayout.LayoutParams) oldHole.getLayoutParams();
        ConstraintLayout.LayoutParams moleLayoutParams =
                (ConstraintLayout.LayoutParams) mole.getLayoutParams();

        moleLayoutParams.leftMargin = holeLayoutParams.leftMargin + 20;
        moleLayoutParams.topMargin = holeLayoutParams.topMargin - 120;

        mole.setLayoutParams(moleLayoutParams);

        gamePad.addView(mole);
    }
}