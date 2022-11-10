package com.itstep.holemole;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity_09_11_threads extends AppCompatActivity {

    // Метка для вывода в лог консоль
    private	final	static	String TAG	= "===== MainActivity";

    private final static String FILE_NAME = "content.txt";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);


        TextView txtView = (TextView)findViewById(R.id.txtViewThread);
        Button btn = findViewById(R.id.btnThread);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Runnable newThread = new Runnable() {
                    @Override
                    public void run() {
                        Calendar c = Calendar.getInstance();
                        String time = c.getTime().toString();

                        Log.i(TAG, time);

                        // Попытка обратиться к элементу в другом потоке
                        // Вызывает ошибку
                        // txtView.setText(time);

                        // Выполним код внутри потока, в котором создан элемент
//                        txtView.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                txtView.setText(time);
//                            }
//                        });

                        // Выполним код другим способом
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                txtView.setText(time);
                            }
                        });

                    }
                };

                Thread t = new Thread(newThread);
                t.start();
            }
        });


    }






}