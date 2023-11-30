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
public class OrderDetail implements Serializable{
    private int order_detail_id = -1;
    private Order order;
    private Product product;
    private int quantity;
    
    public OrderDetail(){
    }

    public OrderDetail(int order_detail_id, Order order, Product product, int quantity) {
        this.order_detail_id = order_detail_id;
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }
    
    public OrderDetail(Order order, Product product, int quantity) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }

    public int getOrder_detail_id() {
        return order_detail_id;
    }

    public void setOrder_detail_id(int order_detail_id) {
        this.order_detail_id = order_detail_id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderDetail{" + "order_detail_id=" + order_detail_id + ", order_id=" + order + ", product_id=" + product + ", quantity=" + quantity + '}';
    }
    
    
     
}
