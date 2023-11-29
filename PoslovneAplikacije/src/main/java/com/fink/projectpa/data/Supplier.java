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
public class Supplier implements Serializable{
    private int supplier_id = -1;
    private String name;
    private String contact_person;
    private String address;
    private String city;
    private String country;
    private String phone;

    public Supplier(int supplier_id, String name, String contact_person, String address, String city, String country, String phone) {
        this.supplier_id = supplier_id;
        this.name = name;
        this.contact_person = contact_person;
        this.address = address;
        this.city = city;
        this.country = country;
        this.phone = phone;
    }
    
    public Supplier(String name, String contact_person, String address, String city, String country, String phone) {
        this.name = name;
        this.contact_person = contact_person;
        this.address = address;
        this.city = city;
        this.country = country;
        this.phone = phone;
    }

    public int getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(int supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Supplier{" + "supplier_id=" + supplier_id + ", name=" + name + ", contact_person=" + contact_person + ", address=" + address + ", city=" + city + ", country=" + country + ", phone=" + phone + '}';
    }
    
}
