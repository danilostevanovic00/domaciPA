/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.dao;

import com.fink.projectpa.data.Customer;
import com.fink.projectpa.data.Employee;
import com.fink.projectpa.data.Order;
import com.fink.projectpa.data.ResourcesManager;
import com.fink.projectpa.data.Shipper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author danil
 */
public class OrderDao {
    private static final OrderDao instance = new OrderDao();

    private OrderDao() {
    }

    public static OrderDao getInstance() {
        return instance;
    }

    public Order find(int order_id, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Order order = null;
        try {
            ps = con.prepareStatement("SELECT * FROM order WHERE order_id=?");
            ps.setInt(1, order_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Customer customer = CustomerDao.getInstance().find(rs.getInt("customer_id"), con);
                Employee employee = EmployeeDao.getInstance().find(rs.getInt("employee_id"), con);
                Shipper shipper = ShipperDao.getInstance().find(rs.getInt("shipper_id"), con);
                order = new Order(order_id,rs.getDate("name"), customer, employee, shipper );
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return order;
    }
    
    public List<Order> find(Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Order> orderList = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM order");
            rs = ps.executeQuery();
            while (rs.next()) {
                Customer customer = CustomerDao.getInstance().find(rs.getInt("customer_id"), con);
                Employee employee = EmployeeDao.getInstance().find(rs.getInt("employee_id"), con);
                Shipper shipper = ShipperDao.getInstance().find(rs.getInt("shipper_id"), con);
                Order order = new Order(rs.getInt("order_id"),rs.getDate("name"), customer, employee, shipper );
                orderList.add(order);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return orderList;
    }

    public int insert(Order order, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = -1;
        try {

            Integer customer_id = null;
            if (order.getCustomer() != null) {
                //insert address and receive the value of id
                customer_id = CustomerDao.getInstance().insert(order.getCustomer(), con);
            }
            
            Integer employee_id = null;
            if (order.getEmployee() != null) {
                //insert address and receive the value of id
                employee_id = EmployeeDao.getInstance().insert(order.getEmployee(), con);
            }
            
            Integer shipper_id = null;
            if (order.getShipper() != null) {
                //insert address and receive the value of id
                shipper_id = ShipperDao.getInstance().insert(order.getShipper(), con);
            }

            ps = con.prepareStatement("INSERT INTO product(order_date, customer_id, employee_id, shipper_id) VALUES(?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            ps.setDate(1, order.getOrder_date());
            ps.setInt(2, customer_id);
            ps.setInt(3, employee_id);
            ps.setInt(4, shipper_id);
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return id;
    }

    public void update(Order order, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {

            ps = con.prepareStatement("UPDATE order SET order_date=? WHERE order_id=?");
            ps.setDate(1, order.getOrder_date());
            ps.setInt(2, order.getOrder_id());
            ps.executeUpdate();

            if (order.getCustomer() != null) {
                CustomerDao.getInstance().update(order.getCustomer(), con);
            }
            
            if (order.getEmployee() != null) {
                EmployeeDao.getInstance().update(order.getEmployee(), con);
            }
            
            if (order.getShipper() != null) {
                ShipperDao.getInstance().update(order.getShipper(), con);
            }

        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public void delete(int order_id, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {

            //delete purchases
            //OrderDetailDao.getInstance().delete(order, con);

            //delete customer
            ps = con.prepareStatement("DELETE FROM order WHERE order_id=?");
            ps.setInt(1, order_id);
            ps.executeUpdate();

        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
}
