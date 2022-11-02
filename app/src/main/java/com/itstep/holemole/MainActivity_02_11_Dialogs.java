package com.itstep.holemole;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity_02_11_Dialogs extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialogs);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        ((Button) findViewById(R.id.btnDialog)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("btn", "start Dialog");
                builder.setTitle("Hello");
                builder.setCancelable(true);
                builder.setMessage("Who do u do?");

                builder.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Log.i("btn", " YES ");
                                dialog.cancel();
                            }
                        });

                builder.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Log.i("btn", " NO ");
                                dialog.cancel();
                            }
                        });



                AlertDialog alert11 = builder.create();
                alert11.show();
            }
        });


    }


}