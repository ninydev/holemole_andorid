package com.itstep.holemole.product;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.itstep.holemole.R;

import java.util.ArrayList;

public class ProductAdapter extends ArrayAdapter<Product>
{

    public ProductAdapter(@NonNull Context context, ArrayList<Product> products) {
        super(context, R.layout.product_item, products);

        this.products = products;
        this.resource = R.layout.product_item;
        this.inflater = LayoutInflater.from(context);
    }

    private LayoutInflater inflater;
    ArrayList<Product> products;
    int resource;

    public  ProductAdapter(@NonNull Context context, int resource, ArrayList<Product> products) {
        super(context, resource, products);

        this.resource = resource;
        this.products = products;

        this.inflater = LayoutInflater.from(context);
    }


    public View getView(int position, View convertView, ViewGroup parent) {
        // Беру нужную вьюшку (шаблон)
        View view=inflater.inflate(this.resource, parent, false);

        // Расставляю элементы сущности по нужным мне позициям
        ((TextView) view.findViewById(R.id.productName)).setText(products.get(position).getName());
        ((TextView) view.findViewById(R.id.productVendor)).setText(products.get(position).getVendor());

        return view;
    }



}
