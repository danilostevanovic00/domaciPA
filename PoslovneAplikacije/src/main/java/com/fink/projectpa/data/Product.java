/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.data;

import java.io.Serializable;

/**
 *
 * @author danil
 */
public class Product implements Serializable{
    private int product_id = -1;
    private String name;
    private String product_category;
    private int price_per_unit;
    private Supplier supplier;

    public Product(int product_id, String name, String product_category, int price_per_unit, Supplier supplier) {
        this.product_id = product_id;
        this.name = name;
        this.product_category = product_category;
        this.price_per_unit = price_per_unit;
        this.supplier = supplier;
    }
    
    public Product(String name, String product_category, int price_per_unit, Supplier supplier) {
        this.name = name;
        this.product_category = product_category;
        this.price_per_unit = price_per_unit;
        this.supplier = supplier;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProduct_category() {
        return product_category;
    }

    public void setProduct_category(String product_category) {
        this.product_category = product_category;
    }

    public int getPrice_per_unit() {
        return price_per_unit;
    }

    public void setPrice_per_unit(int price_per_unit) {
        this.price_per_unit = price_per_unit;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public String toString() {
        return "Product{" + "product_id=" + product_id + ", name=" + name + ", product_category=" + product_category + ", price_per_unit=" + price_per_unit + ", supplier=" + supplier + '}';
    }
  
}
