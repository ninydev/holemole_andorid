package com.itstep.holemole.product;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

public class ProductAdapter extends ArrayAdapter<Product>
{

    public ProductAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }
}
