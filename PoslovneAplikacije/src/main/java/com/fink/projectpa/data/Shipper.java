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
public class Shipper implements Serializable{
    private int shipper_id = -1;
    private String name;
    private String phone;

    public Shipper(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
    
     public Shipper(int shipper_id, String name, String phone) {
        this.shipper_id = shipper_id;
        this.name = name;
        this.phone = phone;
    }

    public int getShipper_id() {
        return shipper_id;
    }

    public void setShipper_id(int shipper_id) {
        this.shipper_id = shipper_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Shipper{" + "shipper_id=" + shipper_id + ", name=" + name + ", phone=" + phone + '}';
    }
  
}
