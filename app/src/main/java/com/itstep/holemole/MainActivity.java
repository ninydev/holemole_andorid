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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itstep.holemole.novaPoshta.Areas;
import com.itstep.holemole.novaPoshta.AreasHelpers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

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
                            URL url = new URL("https://api.novaposhta.ua/v2.0/json/");
                            HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
                            connection.setRequestMethod("POST");

                            // Сообщить что содержим
                            connection.setRequestProperty("Content-Type", "application-json");
                            // Сообщить что будет содержаться тело запроса
                            connection.setDoOutput(true);
                            connection.setChunkedStreamingMode(0);

                            // Получить поток для отпавки
                            BufferedOutputStream outputStream = new BufferedOutputStream( connection.getOutputStream());

                            JSONObject objToSend = new JSONObject();
                            objToSend.put("modelName", "Address");
                            objToSend.put("calledMethod", "getAreas");

                            String jsonToSend = "{\n" +
                                    "    \"modelName\": \"Address\",\n" +
                                    "    \"calledMethod\": \"getAreas\",\n" +
                                    "    \"methodProperties\": {}\n" +
                                    "}";
                            outputStream.write(
                                    objToSend.toString().getBytes(StandardCharsets.UTF_8)
                                    // jsonToSend.getBytes(StandardCharsets.UTF_8)
                            );
                            outputStream.flush();

                            connection.setReadTimeout(10000);
                            connection.connect();


                            //* --------------------------------------------------

                            int responseStatusCode = connection.getResponseCode();
                            if (responseStatusCode != 200) {
                                Log.e(TAG, " Код ошибки сервера");
                            }


                            InputStream stream = connection.getInputStream();
                            BufferedReader reader = new BufferedReader((new InputStreamReader(stream)));

                            StringBuilder sb = new StringBuilder();
                            String line;

                            while ((line = reader.readLine()) != null) {
                                Log.i(TAG, line);
                                sb.append(line);
                            }

                            // В результате тут мы получаем JSON
                            String getFromServer = sb.toString();
                            // Log.i(TAG, sb.toString());

                            //* --------------------------------------------------
                            JSONObject jsonObject = new JSONObject(getFromServer);

                            JSONArray array = jsonObject.getJSONArray("data");

                            ArrayList<Areas> list = AreasHelpers.areasJsonToArray(array);


//                            Log.i(TAG, array.toString());
//                            for (int i=0; i < array.length(); i++) {
//                                if (array.getJSONObject(i).has("Description")) {
//                                    if(array.getJSONObject(i).get("Description") instanceof String) {
//                                        Log.i(TAG, array.getJSONObject(i).getString("Description").toString());
//                                        // Log.i(TAG, array.getJSONObject(i).getLong("Description") + " long ");
//                                    }
//                                    else{
//                                        Log.i(TAG, "Not String");
//                                    }
//                                }
//                            }

//                            JSONArray names = array.getJSONObject(0).names();
//
//                            for(int i = 0; i < names.length(); i++) {
//                                Log.i(TAG, names.get(i).toString());
//                            }

                            final ObjectMapper objectMapper = new ObjectMapper();
                            Areas[] arrs = objectMapper.readValue(
                                    jsonObject.getJSONArray("data").toString()
                                    , Areas[].class);

                            Log.i(TAG, arrs.toString());


                        } catch (MalformedURLException e) {
                            Log.e(TAG, e.getLocalizedMessage());
                            e.printStackTrace();
                        } catch (IOException e) {
                            Log.e(TAG, e.getLocalizedMessage());
                            e.printStackTrace();
                        } catch (JSONException e) {
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