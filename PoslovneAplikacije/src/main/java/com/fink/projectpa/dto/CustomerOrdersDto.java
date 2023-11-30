/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.dto;

import java.util.List;

/**
 *
 * @author danil
 */
public class CustomerOrdersDto {
    private String customerName;
    private List<Integer> orderIds;

    public CustomerOrdersDto(String customerName, List<Integer> orderIds) {
        this.customerName = customerName;
        this.orderIds = orderIds;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<Integer> getOrderIds() {
        return orderIds;
    }

    public void setOrderIds(List<Integer> orderIds) {
        this.orderIds = orderIds;
    }
    
    
}
