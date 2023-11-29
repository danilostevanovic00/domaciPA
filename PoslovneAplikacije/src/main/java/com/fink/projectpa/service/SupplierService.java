/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.service;

import com.fink.projectpa.dao.SupplierDao;
import com.fink.projectpa.data.ResourcesManager;
import com.fink.projectpa.data.Supplier;
import com.fink.projectpa.exception.WarehouseException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author danil
 */
public class SupplierService {
    private static final SupplierService instance = new SupplierService();

    private SupplierService() {
    }

    public static SupplierService getInstance() {
        return instance;
    }
    
    public void addNewSupplier(Supplier supplier) throws WarehouseException, SQLException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            //more than one SQL statement to execute, needs to be a single transaction
            con.setAutoCommit(false);

            SupplierDao.getInstance().insert(supplier, con);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to add new supplier " + supplier, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public Supplier findSupplier(int supplier_id) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return SupplierDao.getInstance().find(supplier_id, con);

        } catch (SQLException ex) {
            throw new WarehouseException("Failed to find supplier with id " + supplier_id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public List<Supplier> getAllSupplier() throws WarehouseException{
        Connection con = null;
        try{
            con = ResourcesManager.getConnection();
            return SupplierDao.getInstance().find(con);
        }catch (SQLException ex) {
            throw new WarehouseException("Failed to get all suppliers", ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
    }
    
    public void deleteSupplier(int supplier_id) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            Supplier supplier = SupplierDao.getInstance().find(supplier_id, con);
            if (supplier != null) {
                SupplierDao.getInstance().delete(supplier_id, con);
            }

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to delete supplier with id " + supplier_id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public void updateSupplier(Supplier supplier) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            SupplierDao.getInstance().update(supplier, con);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to update supplier " + supplier, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
}
