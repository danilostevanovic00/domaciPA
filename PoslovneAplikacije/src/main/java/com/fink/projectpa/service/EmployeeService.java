/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.service;

import com.fink.projectpa.dao.EmployeeDao;
import com.fink.projectpa.data.Employee;
import com.fink.projectpa.data.ResourcesManager;
import com.fink.projectpa.exception.WarehouseException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author danil
 */
public class EmployeeService {
    private static final EmployeeService instance = new EmployeeService();

    private EmployeeService() {
    }

    public static EmployeeService getInstance() {
        return instance;
    }
    
    public void addNewEmployee(Employee employee) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            //more than one SQL statement to execute, needs to be a single transaction
            con.setAutoCommit(false);

            EmployeeDao.getInstance().insert(employee, con);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to add new employee " + employee, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public Employee findemployee(int employee_id) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();

            return EmployeeDao.getInstance().find(employee_id, con);

        } catch (SQLException ex) {
            throw new WarehouseException("Failed to find employee with id " + employee_id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public List<Employee> getAllEmployees() throws WarehouseException{
        Connection con = null;
        try{
            con = ResourcesManager.getConnection();
            return EmployeeDao.getInstance().find(con);
        }catch (SQLException ex) {
            throw new WarehouseException("Failed to get all employees", ex);
        }
        finally{
            ResourcesManager.closeConnection(con);
        }
    }
    
    public void deleteEployee(int employee_id) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            Employee customer = EmployeeDao.getInstance().find(employee_id, con);
            if (customer != null) {
                EmployeeDao.getInstance().delete(employee_id, con);
            }

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to delete employee with id " + employee_id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
    
    public void updateEmployee(Employee employee) throws WarehouseException {
        Connection con = null;
        try {
            con = ResourcesManager.getConnection();
            con.setAutoCommit(false);

            EmployeeDao.getInstance().update(employee, con);

            con.commit();
        } catch (SQLException ex) {
            ResourcesManager.rollbackTransactions(con);
            throw new WarehouseException("Failed to update employee " + employee, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
    }
}
