/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.dao;

import com.fink.projectpa.data.Product;
import com.fink.projectpa.data.ResourcesManager;
import com.fink.projectpa.data.Supplier;
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
public class ProductDao {
    private static final ProductDao instance = new ProductDao();

    private ProductDao() {
    }

    public static ProductDao getInstance() {
        return instance;
    }

    public Product find(int product_id, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Product product = null;
        try {
            ps = con.prepareStatement("SELECT * FROM product WHERE product_id=?");
            ps.setInt(1, product_id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Supplier supplier = SupplierDao.getInstance().find(rs.getInt("supplier_id"), con);
                product = new Product(product_id,rs.getString("name"), rs.getString("product_category"), rs.getInt("price_per_unit"),supplier );
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return product;
    }
    
    public List<Product> find(Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Product> productList = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM product");
            rs = ps.executeQuery();
            while (rs.next()) {
                Supplier supplier = SupplierDao.getInstance().find(rs.getInt("supplier_id"), con);
                Product product = new Product(rs.getInt("customer_id"),rs.getString("name"), rs.getString("product_category"), rs.getInt("price_per_unit"),supplier );
                productList.add(product);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return productList;
    }

    public int insert(Product product, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id =-1;
        try {

            Integer supplier_id = null;
            if (product.getSupplier() != null) {
                //insert address and receive the value of id
                supplier_id = SupplierDao.getInstance().insert(product.getSupplier(), con);
            }

            ps = con.prepareStatement("INSERT INTO product(name, product_category, price_per_unit, suplier_id) VALUES(?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, product.getName());
            ps.setString(2, product.getProduct_category());
            ps.setInt(3, product.getPrice_per_unit());
            ps.setInt(4, supplier_id);
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
            
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return id;
    }

    public void update(Product product, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {

            ps = con.prepareStatement("UPDATE product SET name=?, product_category=?, price_per_unit=? WHERE product_id=?");
            ps.setString(1, product.getName());
            ps.setString(2, product.getProduct_category());
            ps.setInt(3, product.getPrice_per_unit());
            ps.setInt(4, product.getProduct_id());
            ps.executeUpdate();

            if (product.getSupplier() != null) {
                SupplierDao.getInstance().update(product.getSupplier(), con);
            }

        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public void delete(int product_id, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {

            OrderDetailDao.getInstance().deleteByProduct(product_id, con);

            //delete customer
            ps = con.prepareStatement("DELETE FROM product WHERE product_id=?");
            ps.setInt(1, product_id);
            ps.executeUpdate();

        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
}
