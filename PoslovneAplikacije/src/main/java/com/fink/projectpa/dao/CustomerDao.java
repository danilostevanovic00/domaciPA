/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.dao;
import com.fink.projectpa.data.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.fink.projectpa.data.ResourcesManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author danil
 */
public class CustomerDao {
    private static final CustomerDao instance = new CustomerDao();
    
    private CustomerDao(){
    }
    
    public static CustomerDao getInstance(){
        return instance;
    }
    
    public Customer find(int customer_id, Connection con) throws SQLException{
        PreparedStatement ps = null;
        ResultSet rs = null;
        Customer customer = null;
        try {
            ps = con.prepareStatement("SELECT * FROM customer where customer_id=?");
            ps.setInt(1, customer_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                customer = new Customer(customer_id, rs.getString("name"),rs.getString("contact_person"),rs.getString("address"),rs.getString("city"),rs.getInt("post_code"),rs.getString("country"));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return customer;
    }
    
    public List<Customer> find(Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Customer> customerList = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM customer");
            rs = ps.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer(rs.getInt("customer_id"), rs.getString("name"), rs.getString("contact_person"), rs.getString("address"), rs.getString("city"), rs.getInt("post_code"), rs.getString("countryd"));
                customerList.add(customer);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return customerList;
    }
    
    public int insert(Customer customer, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = -1;
        try {
            ps = con.prepareStatement("INSERT INTO customer(name,contact_person,address,city,post_code,country) VALUES(?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getContact_person());
            ps.setString(3, customer.getAddress());
            ps.setString(4, customer.getCity());
            ps.setInt(5, customer.getPost_code());
            ps.setString(6, customer.getCountry());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return id;
    }
    
    public void update(Customer customer, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE customer SET name=?,contact_person=?,address=?,city=?,post_code=?,country=? WHERE customer_id=?");
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getContact_person());
            ps.setString(3, customer.getAddress());
            ps.setString(4, customer.getCity());
            ps.setInt(5, customer.getPost_code());
            ps.setString(6, customer.getCountry());
            ps.setInt(7, customer.getCustomer_id());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public void delete(int customer_id, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM customer WHERE customer_id=?");
            ps.setInt(1, customer_id);
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
}
