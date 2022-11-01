package com.itstep.holemole;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.itstep.holemole.product.Product;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    String[] arrCountriesNames = {"Ukraine" , "Poland", "Germany"};

    ArrayList<Product> products = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.begin_adapters);

        products.add(new Product("Gresha"));
        products.add(new Product("Moloko"));
        products.add(new Product("kefir"));

        ListView lst = findViewById(R.id.lstCountries);

        ArrayAdapter<Product> adapterLst = new ArrayAdapter<>(this,
                R.layout.product_item,
                //android.R.layout.simple_list_item_1, // Стандартный вид отображения элемента списка
                products);
        lst.setAdapter(adapterLst);

    }


    private void adapters () {
        // Создадим адаптер для листа
        ListView lst = findViewById(R.id.lstCountries);
        ArrayAdapter<String> adapterLst = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, // Стандартный вид отображения элемента списка
                arrCountriesNames);
        lst.setAdapter(adapterLst);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("Selected List ", "num: " + i);
            }
        });

        // Создадим адаптер для выпадающего списка
        Spinner spinner = findViewById(R.id.spinnerCountries);
        ArrayAdapter<String> adapterSpinner =  new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                arrCountriesNames);
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapterSpinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("Selected Spinner ", "num: " + i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


}