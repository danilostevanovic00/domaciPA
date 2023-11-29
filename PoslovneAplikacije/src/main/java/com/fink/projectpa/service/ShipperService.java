/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.service;

import com.fink.projectpa.dao.ShipperDao;
import com.fink.projectpa.data.ResourcesManager;
import com.fink.projectpa.data.Shipper;
import com.fink.projectpa.exception.WarehouseException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author danil
 */
public class ShipperService {
    private static final ShipperService instance = new ShipperService();

    private ShipperService() {
    }

    public static ShipperService getInstance() {
        return instance;
    }
    
    public void addNewShipper(Shipper shipper) throws WarehouseException, SQLException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            //more than one SQL statement to execute, needs to be a single transaction
            con.setAutoCommit(false);

            ShipperDao.getInstance().insert(shipper, con);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to add new shipper " + shipper, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public Shipper findShipper(int shipper_id) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return ShipperDao.getInstance().find(shipper_id, con);

        } catch (SQLException ex) {
            throw new WarehouseException("Failed to find shipper with id " + shipper_id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public List<Shipper> getAllShipper() throws WarehouseException{
        Connection con = null;
        try{
            con = ResourcesManager.getConnection();
            return ShipperDao.getInstance().find(con);
        }catch (SQLException ex) {
            throw new WarehouseException("Failed to get all shippers", ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
    }
    
    public void deleteShipper(int shipper_id) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            Shipper supplier = ShipperDao.getInstance().find(shipper_id, con);
            if (supplier != null) {
                ShipperDao.getInstance().delete(shipper_id, con);
            }

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to delete shipper with id " + shipper_id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public void updateShipper(Shipper shipper) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            ShipperDao.getInstance().update(shipper, con);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to update shipper " + shipper, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
}
