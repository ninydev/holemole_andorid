package com.itstep.holemole;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity_03_11 extends AppCompatActivity {

    // Метка для вывода в лог консоль
    private	final	static	String TAG	= "===== MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_files);

        int permission = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED)
        {
            Log.d("permissions", "Разрешения нет, запросим это разрешение у пользователя");

            // ----- Разрешения нет, запросим это разрешение у пользователя --------
            ActivityCompat.requestPermissions(
                    this,
                    new String[]
                            {
                                    Manifest.permission.READ_EXTERNAL_STORAGE,
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                            }, 1);
        }


        File esMainDir = null;

        if (this.isExternalStorageWritable())
        {
            // ----- Получение пути к каталогу внешнего носителя -------------------
            esMainDir	= Environment.getExternalStorageDirectory();
            Log.d(MainActivity_03_11.TAG, "Путь к каталогу внешнего носителя : " +
                    esMainDir.getAbsolutePath());
        }
        else
        {
            Log.d(MainActivity_03_11.TAG, "Устройство не готово!");
        }


        try {
            File myExternalFile;
            myExternalFile = new File(esMainDir.getAbsolutePath(), "test.txt");
            FileOutputStream fos = new FileOutputStream(myExternalFile, true);
            String str = "Test";
            fos.write(str.getBytes(StandardCharsets.UTF_8));
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        /**
         * Прочитать список файлов
         */
        // ----- Получение списка каталогов и файлов внешнего носителя ---------
        ArrayList<String>	listFiles	= this.fillDirectory(esMainDir);
        Log.i(TAG, "=============== FILES ================");
        for (String f:listFiles) {
            Log.i(TAG, f);
        }


    }

    /**
     *
     * Метод проверяет готовность внешнего носителя для операций чтения/записи.
     * ----------------------------------------------------------------------
     */
    public boolean isExternalStorageWritable()
    {
        String state = Environment.getExternalStorageState();
        return (state.equals(Environment.MEDIA_MOUNTED));
    }
    /**
     *
     * Метод проверяет готовность внешнего носителя для операций чтения.
     * ----------------------------------------------------------------------
     */
    public boolean isExternalStorageReadable()
    {
        String state = Environment.getExternalStorageState();
        return (state.equals(Environment.MEDIA_MOUNTED) ||
                state.equals(Environment.MEDIA_MOUNTED_READ_ONLY));
    }


    /**
     * Метод возвращает коллекцию названий файлов и каталогов
     * внешнего носителя, которые находятся в каталоге dir.
     * @param dir	- Каталог внешнего носителя, список файлов и
     *                 подкаталогов которого необходимо получить.
     * @return		- Коллекцию любого размера строк. Имена каталогов
     * заключены в [квадратные скобки].
     */
    private	ArrayList<String>	fillDirectory(File dir)
    {
        ArrayList<String>	listFiles	= new ArrayList<>();

        if (this.isExternalStorageReadable())
        {
            File[] arrFiles	= dir.listFiles();
            if (arrFiles != null)
            {
                for (File f : arrFiles)
                {
                    if (f.isDirectory())
                    {
                        listFiles.add("[" + f.getName() + "]");
                    }
                    else
                    {
                        listFiles.add(f.getName());
                    }
                }
            }
        }
        return	listFiles;
    }


}