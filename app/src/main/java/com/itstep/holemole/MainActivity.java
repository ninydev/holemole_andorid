package com.itstep.holemole;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.itstep.holemole.product.Product;
import com.itstep.holemole.product.ProductAdapter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {

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