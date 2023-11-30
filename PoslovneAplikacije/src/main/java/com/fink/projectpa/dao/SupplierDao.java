/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.fink.projectpa.data.ResourcesManager;
import com.fink.projectpa.data.Supplier;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author danil
 */
public class SupplierDao {
    private static final SupplierDao instance = new SupplierDao();
    
    private SupplierDao(){
    } 
    
    public static SupplierDao getInstance(){
        return instance;   
    }
     public Supplier find( int supplier_id, Connection con) throws SQLException{
         PreparedStatement ps = null;
         ResultSet rs = null;
         Supplier supplier = null;
         try {
             ps= con.prepareStatement("SELECT * FROM supplier WHERE supplier_id=?");
             ps.setInt(1,supplier_id);
             rs= ps.executeQuery();
             if(rs.next()){
                 supplier = new Supplier(rs.getInt("supplier_id"),rs.getString("name"),rs.getString("contact_person"), rs.getString("address"),rs.getString("city"),rs.getString("country"),rs.getString("phone"));
             }
         }finally{
             ResourcesManager.closeResources(rs, ps);
         }
         return supplier;
     }
     
     public List<Supplier> find(Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Supplier> supplierList = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM supplier");
            rs = ps.executeQuery();
            while (rs.next()) {
                Supplier supplier = new Supplier(rs.getInt("supplier_id"),rs.getString("name"),rs.getString("contact_person"), rs.getString("address"),rs.getString("city"),rs.getString("country"),rs.getString("phone"));
                supplierList.add(supplier);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return supplierList;
    }
     public int insert(Supplier supplier, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = -1;
        try {
            ps = con.prepareStatement("INSERT INTO supplier(name,contact_person,address,city,country,phone) VALUES(?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, supplier.getName());
            ps.setString(2, supplier.getContact_person());
            ps.setString(3, supplier.getAddress());
            ps.setString(4, supplier.getCity());
            ps.setString(5, supplier.getCountry());
            ps.setString(6, supplier.getPhone());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return id;
    }
    
    public void update(Supplier supplier, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE supplier SET name=?,contact_person=?,address=?,city=?,country=?,phone=? WHERE supplier_id=?");
            ps.setString(1, supplier.getName());
            ps.setString(2, supplier.getContact_person());
            ps.setString(3, supplier.getAddress());
            ps.setString(4, supplier.getCity());
            ps.setString(5, supplier.getCountry());
            ps.setString(6, supplier.getPhone());
            ps.setInt(7, supplier.getSupplier_id());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public void delete(int supplier_id, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM supplier WHERE supplier_id=?");
            ps.setInt(1, supplier_id);
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
}
