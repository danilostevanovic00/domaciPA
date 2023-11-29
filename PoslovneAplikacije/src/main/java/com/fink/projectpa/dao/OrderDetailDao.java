/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.dao;

import com.fink.projectpa.data.Order;
import com.fink.projectpa.data.OrderDetail;
import com.fink.projectpa.data.Product;
import com.fink.projectpa.data.ResourcesManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author danil
 */
public class OrderDetailDao {
    private static final OrderDetailDao instance = new OrderDetailDao();

    private OrderDetailDao() {
    }

    public static OrderDetailDao getInstance() {
        return instance;
    }

    public OrderDetail find(int order_detail_id, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        OrderDetail order_detail = null;
        try {
            ps = con.prepareStatement("SELECT * FROM order_detail WHERE order_detail_id=?");
            ps.setInt(1, order_detail_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Product product = ProductDao.getInstance().find(rs.getInt("product_id"), con);
                Order order = OrderDao.getInstance().find(rs.getInt("order_id"), con);
                order_detail = new OrderDetail(order_detail_id, order, product,rs.getInt("quantity"));
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return order_detail;
    }
    
    public List<OrderDetail> find(Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM order");
            rs = ps.executeQuery();
            while (rs.next()) {
                Product product = ProductDao.getInstance().find(rs.getInt("product_id"), con);
                Order order = OrderDao.getInstance().find(rs.getInt("order_id"), con);
                OrderDetail order_detail = new OrderDetail(rs.getInt("order_detail_id"), order, product,rs.getInt("quantity"));
                orderDetailList.add(order_detail);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return orderDetailList;
    }
    
    public void update(OrderDetail order_detail, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {

            ps = con.prepareStatement("UPDATE order_detail SET quantity=? WHERE order_detail_id=?");
            ps.setInt(1, order_detail.getQuantity());
            ps.setInt(2, order_detail.getOrder_detail_id());
            ps.executeUpdate();

            if (order_detail.getOrder() != null) {
                OrderDao.getInstance().update(order_detail.getOrder(), con);
            }
            
            if (order_detail.getProduct() != null) {
                ProductDao.getInstance().update(order_detail.getProduct(), con);
            }

        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public void insert(OrderDetail order_detail, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            
            Integer order_id = null;
            if (order_detail.getOrder() != null) {
                //insert address and receive the value of id
                order_id = OrderDao.getInstance().insert(order_detail.getOrder(), con);
            }
            
            Integer product_id = null;
            if (order_detail.getProduct() != null) {
                //insert address and receive the value of id
                product_id = ProductDao.getInstance().insert(order_detail.getProduct(), con);
            }
            
            ps = con.prepareStatement("INSERT INTO order_detail(order_id, product_id, quantity) VALUES(?,?,?)");
            ps.setInt(1, order_id);
            ps.setInt(2, product_id);
            ps.setInt(3, order_detail.getQuantity());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
    }

    public void deleteByOrder(int order_id, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM order_detail WHERE order_id=?");
            ps.setInt(1, order_id);
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public void deleteByProduct(int product_id, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM order_detail WHERE product_id=?");
            ps.setInt(1, product_id);
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
    
    public void delete(int order_detail_id, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM order_detail WHERE order_detail_id=?");
            ps.setInt(1, order_detail_id);
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
}
