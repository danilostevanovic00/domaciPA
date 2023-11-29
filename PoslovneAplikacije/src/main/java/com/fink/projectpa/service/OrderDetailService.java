/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.service;

import com.fink.projectpa.dao.OrderDetailDao;
import com.fink.projectpa.data.OrderDetail;
import com.fink.projectpa.data.ResourcesManager;
import com.fink.projectpa.exception.WarehouseException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author danil
 */
public class OrderDetailService {
    private static final OrderDetailService instance = new OrderDetailService();

    private OrderDetailService() {
    }

    public static OrderDetailService getInstance() {
        return instance;
    }
    
    public void addNewOrderDetail(OrderDetail order_detail) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            //more than one SQL statement to execute, needs to be a single transaction
            con.setAutoCommit(false);

            OrderDetailDao.getInstance().insert(order_detail, con);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to add new order detail " + order_detail, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public OrderDetail findOrder(int order_detail_id) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return OrderDetailDao.getInstance().find(order_detail_id, con);

        } catch (SQLException ex) {
            throw new WarehouseException("Failed to find order detail with id " + order_detail_id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public List<OrderDetail> getAllOrderDetail() throws WarehouseException{
        Connection con = null;
        try{
            con = ResourcesManager.getConnection();
            return OrderDetailDao.getInstance().find(con);
        }catch (SQLException ex) {
            throw new WarehouseException("Failed to get all order details", ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
    }
    
    public void deleteOrderDetail(int order_detail_id) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            OrderDetail customer = OrderDetailDao.getInstance().find(order_detail_id, con);
            if (customer != null) {
                OrderDetailDao.getInstance().delete(order_detail_id, con);
            }
            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to delete order detail with id " + order_detail_id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public void updateOrderDetail(OrderDetail product) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            OrderDetailDao.getInstance().update(product, con);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to update product " + product, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
}
