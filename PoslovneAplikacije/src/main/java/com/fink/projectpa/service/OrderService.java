/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.service;

import com.fink.projectpa.dao.OrderDao;
import com.fink.projectpa.data.Order;
import com.fink.projectpa.data.ResourcesManager;
import com.fink.projectpa.exception.WarehouseException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author danil
 */
public class OrderService {
    private static final OrderService instance = new OrderService();

    private OrderService() {
    }

    public static OrderService getInstance() {
        return instance;
    }
    
    public void addNewOrder(Order order) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            //more than one SQL statement to execute, needs to be a single transaction
            con.setAutoCommit(false);

            OrderDao.getInstance().insert(order, con);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to add new order " + order, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public Order findOrder(int order_id) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return OrderDao.getInstance().find(order_id, con);

        } catch (SQLException ex) {
            throw new WarehouseException("Failed to find order with id " + order_id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public List<Order> getAllOrder() throws WarehouseException{
        Connection con = null;
        try{
            con = ResourcesManager.getConnection();
            return OrderDao.getInstance().find(con);
        }catch (SQLException ex) {
            throw new WarehouseException("Failed to get all orders", ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
    }
    
    public void deleteOrder(int order_id) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            Order order = OrderDao.getInstance().find(order_id, con);
            if (order != null) {
                OrderDao.getInstance().delete(order_id, con);
            }
            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to delete order with id " + order_id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public void updateOrder(Order order) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            OrderDao.getInstance().update(order, con);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to update order " + order, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
}
