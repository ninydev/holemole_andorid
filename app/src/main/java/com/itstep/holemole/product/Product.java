package com.itstep.holemole.product;


public class Product {

    public Product(String name) {
        this.name = name; vendor = "NoName";
    }

    public Product(String name, String vendor) {
        this.name = name; this.vendor = vendor;
    }


    private String vendor;

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }



    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "vendor='" + vendor + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
