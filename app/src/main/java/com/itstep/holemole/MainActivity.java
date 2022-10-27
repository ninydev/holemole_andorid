package com.itstep.holemole;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        ConstraintLayout keyPad = (ConstraintLayout)findViewById(R.id.keyPad);
        ImageView num3 = new ImageView(this);
        num3.setId(R.id.btn_3);
        num3.setImageResource(R.drawable.num_3);

        ConstraintLayout.LayoutParams num3LayoutParams = new ConstraintLayout.LayoutParams(
                WRAP_CONTENT, WRAP_CONTENT);
        num3LayoutParams.leftToRight = R.id.btn_2;
        num3LayoutParams.topToTop = ConstraintLayout.LayoutParams.PARENT_ID;
        num3.setLayoutParams(num3LayoutParams);
        num3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("btn", "click 3");
            }
        });

        keyPad.addView(num3);


        ImageView num2 = (ImageView) findViewById(R.id.btn_2);

        num2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Log.i("btn", "click 2");

                                    }
                                });



    }




    public void numClick_1(View view) {
        Log.i("btn", "click 1");
    }
}