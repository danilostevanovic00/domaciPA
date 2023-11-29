/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.service;

import com.fink.projectpa.dao.CustomerDao;
import com.fink.projectpa.data.Customer;
import com.fink.projectpa.data.ResourcesManager;
import com.fink.projectpa.exception.WarehouseException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author danil
 */
public class CustomerService {
    private static final CustomerService instance = new CustomerService();

    private CustomerService() {
    }

    public static CustomerService getInstance() {
        return instance;
    }
    
    public void addNewCustomer(Customer customer) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            //more than one SQL statement to execute, needs to be a single transaction
            con.setAutoCommit(false);

            CustomerDao.getInstance().insert(customer, con);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to add new customer " + customer, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public Customer findCustomer(int customer_id) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return CustomerDao.getInstance().find(customer_id, con);

        } catch (SQLException ex) {
            throw new WarehouseException("Failed to find customer with id " + customer_id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public List<Customer> getAllCustomers() throws WarehouseException{
        Connection con = null;
        try{
            con = ResourcesManager.getConnection();
            return CustomerDao.getInstance().find(con);
        }catch (SQLException ex) {
            throw new WarehouseException("Failed to get all customers", ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
    }
    
    public void deleteCustomer(int customer_id) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            Customer customer = CustomerDao.getInstance().find(customer_id, con);
            if (customer != null) {
                CustomerDao.getInstance().delete(customer_id, con);
            }

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to delete customer with id " + customer_id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public void updateCustomer(Customer customer) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            CustomerDao.getInstance().update(customer, con);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to update customer " + customer, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
}
