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
import com.fink.projectpa.data.Shipper;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author danil
 */
public class ShipperDao {
    private static final ShipperDao instance = new ShipperDao();
    
    private ShipperDao(){
    } 
    
    public static ShipperDao getInstance(){
        return instance;   
    }
     public Shipper find( int shipper_id, Connection con) throws SQLException{
         PreparedStatement ps = null;
         ResultSet rs = null;
         Shipper shipper = null;
         try {
             ps= con.prepareStatement("SELECT * FROM shipper WHERE shipper_id=?");
             ps.setInt(1,shipper_id);
             rs= ps.executeQuery();
             if(rs.next()){
                 shipper = new Shipper(shipper_id,rs.getString("name"),rs.getString("phone"));
             }
         }finally{
             ResourcesManager.closeResources(rs, ps);
         }
         return shipper;
     }
     
     public List<Shipper> find(Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Shipper> shipperList = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM shipper");
            rs = ps.executeQuery();
            while (rs.next()) {
                Shipper shipper = new Shipper(rs.getInt("shipper_id"),rs.getString("name"),rs.getString("phone"));
                shipperList.add(shipper);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return shipperList;
    }
     public int insert(Shipper shipper, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = -1;
        try {
            ps = con.prepareStatement("INSERT INTO shipper(name,phone) VALUES(?,?)",Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, shipper.getName());
            ps.setString(2, shipper.getPhone());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return id;
    }
    
    public void update(Shipper shipper, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE shipper SET name=?,phone=? WHERE shipper_id=?");
            ps.setString(1, shipper.getName());
            ps.setString(2, shipper.getPhone());
            ps.setInt(3, shipper.getShipper_id());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public void delete(int shipper_id, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM shipper WHERE shipper_id=?");
            ps.setInt(1, shipper_id);
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
}
