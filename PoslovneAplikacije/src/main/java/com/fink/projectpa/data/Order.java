/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.data;

import java.io.Serializable;
import java.sql.Date;
/**
 *
 * @author danil
 */
public class Order implements Serializable{
    private int order_id = -1;
    private Date order_date;
    private Customer customer;
    private Employee employee;
    private Shipper shipper;

    public Order(int order_id, Date order_date, Customer customer, Employee employee, Shipper shipper) {
        this.order_id = order_id;
        this.order_date = order_date;
        this.customer = customer;
        this.employee = employee;
        this.shipper = shipper;
    }
    
    public Order(Date order_date, Customer customer, Employee employee, Shipper shipper) {
        this.order_date = order_date;
        this.customer = customer;
        this.employee = employee;
        this.shipper = shipper;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Shipper getShipper() {
        return shipper;
    }

    public void setShipper(Shipper shipper) {
        this.shipper = shipper;
    }

    @Override
    public String toString() {
        return "Order{" + "order_id=" + order_id + ", order_date=" + order_date + ", customer_id=" + customer + ", employee_id=" + employee + ", shipper_id=" + shipper + '}';
    }
    
}
