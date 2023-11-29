/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.service;

import com.fink.projectpa.dao.ProductDao;
import com.fink.projectpa.data.Product;
import com.fink.projectpa.data.ResourcesManager;
import com.fink.projectpa.exception.WarehouseException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author danil
 */
public class ProductService {
    private static final ProductService instance = new ProductService();

    private ProductService() {
    }

    public static ProductService getInstance() {
        return instance;
    }
    
    public void addNewProduct(Product product) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            //more than one SQL statement to execute, needs to be a single transaction
            con.setAutoCommit(false);

            ProductDao.getInstance().insert(product, con);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to add new product " + product, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public Product findProduct(int product_id) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return ProductDao.getInstance().find(product_id, con);

        } catch (SQLException ex) {
            throw new WarehouseException("Failed to find product with id " + product_id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public List<Product> getAllProduct() throws WarehouseException{
        Connection con = null;
        try{
            con = ResourcesManager.getConnection();
            return ProductDao.getInstance().find(con);
        }catch (SQLException ex) {
            throw new WarehouseException("Failed to get all products", ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
    }
    
    public void deleteProduct(int product_id) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            Product customer = ProductDao.getInstance().find(product_id, con);
            if (customer != null) {
                ProductDao.getInstance().delete(product_id, con);
            }
            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to delete product with id " + product_id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public void updateProduct(Product product) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            ProductDao.getInstance().update(product, con);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to update product " + product, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
}
