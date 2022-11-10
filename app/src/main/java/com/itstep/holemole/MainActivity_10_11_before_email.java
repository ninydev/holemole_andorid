package com.itstep.holemole;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity_10_11_before_email extends AppCompatActivity {

    // Метка для вывода в лог консоль
    private	final	static	String TAG	= "===== MainActivity";

    private final static String FILE_NAME = "content.txt";

    private final int MY_PERMISSIONS_REQUEST_INTERNET = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet);

//        ConnectivityManager cm = (ConnectivityManager)
//                MainActivity.this.
//                        getSystemService(Context.
//                                CONNECTIVITY_SERVICE);
//        Network N = cm.getActiveNetwork();


        // проверка наличия разрешения на использование камеры
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, " NO Internet");
            // разрешение не предоставлено
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, MY_PERMISSIONS_REQUEST_INTERNET);
        }
        else {
            Log.i(TAG, " YES Internet");
            // разрешение предоставлено
        }


        TextView txtView = (TextView)findViewById(R.id.txtShowResponse);
        Button btn = findViewById(R.id.btnSendRequest);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Runnable tryGet = new Runnable() {
                    @Override
                    public void run() {
                        try {
                            URL url = new URL("https://www.ninydev.com/");
                            HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
                            connection.setRequestMethod("GET");
                            connection.addRequestProperty("Subject", "Subject");
                            connection.addRequestProperty("Message", "Message");
                            connection.setReadTimeout(10000);
                            connection.connect();

                            InputStream stream = connection.getInputStream();
                            BufferedReader reader = new BufferedReader((new InputStreamReader(stream)));

                            StringBuilder sb = new StringBuilder();
                            String line;

                            while ((line = reader.readLine()) != null) {
                                Log.i(TAG, line);
                                sb.append(line);
                            }

                            // В результате тут мы получаем JSON
                            Log.i(TAG, sb.toString());

                        } catch (MalformedURLException e) {
                            Log.e(TAG, e.getLocalizedMessage());
                            e.printStackTrace();
                        } catch (IOException e) {
                            Log.e(TAG, e.getLocalizedMessage());
                            e.printStackTrace();
                        }
                    }
                };

                Thread t = new Thread(tryGet);
                t.start();
            }
        });


    }






}