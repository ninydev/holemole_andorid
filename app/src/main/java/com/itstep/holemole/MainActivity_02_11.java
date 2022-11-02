package com.itstep.holemole;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.itstep.holemole.product.Product;
import com.itstep.holemole.product.ProductAdapter;

import java.util.ArrayList;

public class MainActivity_02_11 extends AppCompatActivity {

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
                // R.layout.product_item,
                android.R.layout.simple_list_item_1, // Стандартный вид отображения элемента списка
                products);

        ProductAdapter productAdapter = new ProductAdapter(
                this,
                R.layout.product_item,
                products
        );

        lst.setAdapter(productAdapter);

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