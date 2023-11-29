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
public class Customer implements Serializable {
    private int customer_id = -1;
    private String name;
    private String contact_person;
    private String address;
    private String city;
    private int post_code;
    private String country;

    public Customer(int customer_id, String customer_name, String contact_person, String address, String city, int post_code, String country) {
        this.customer_id = customer_id;
        this.name = customer_name;
        this.contact_person = contact_person;
        this.address = address;
        this.city = city;
        this.post_code = post_code;
        this.country = country;
    }
    
    public Customer(String customer_name, String contact_person, String address, String city, int post_code, String country) {
        this.name = customer_name;
        this.contact_person = contact_person;
        this.address = address;
        this.city = city;
        this.post_code = post_code;
        this.country = country;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String customer_name) {
        this.name = customer_name;
    }

    public String getContact_person() {
        return contact_person;
    }

    public void setContact_person(String contact_person) {
        this.contact_person = contact_person;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPost_code() {
        return post_code;
    }

    public void setPost_code(int post_code) {
        this.post_code = post_code;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "Customer{" + "customer_id=" + customer_id + ", customer_name=" + name + ", contact_person=" + contact_person + ", address=" + address + ", city=" + city + ", post_code=" + post_code + ", country=" + country + '}';
    }
   
}
