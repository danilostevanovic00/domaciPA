/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.dao;

import com.fink.projectpa.data.Employee;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.fink.projectpa.data.ResourcesManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
/**
 *
 * @author danil
 */
public class EmployeeDao {
    private static final EmployeeDao instance = new EmployeeDao();
    
    private EmployeeDao(){
    } 
    
    public static EmployeeDao getInstance(){
        return instance;   
    }
     public Employee find( int employee_id, Connection con) throws SQLException{
         PreparedStatement ps = null;
         ResultSet rs = null;
         Employee employee = null;
         try {
             ps= con.prepareStatement("SELECT * FROM employee WHERE employee_id=?");
             ps.setInt(1,employee_id);
             rs= ps.executeQuery();
             if(rs.next()){
                 employee = new Employee(rs.getInt("employee_id"),rs.getString("lastname"),rs.getString("firstname"), rs.getDate("birth_date"));
             }
         }finally{
             ResourcesManager.closeResources(rs, ps);
         }
         return employee;
     }
     
     public List<Employee> find(Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Employee> employeeList = new ArrayList<>();
        try {
            ps = con.prepareStatement("SELECT * FROM employee");
            rs = ps.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee(rs.getInt("employee_id"), rs.getString("lastname"), rs.getString("firstname"), rs.getDate("birth_date"));
                employeeList.add(employee);
            }
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return employeeList;
    }
     public int insert(Employee employee, Connection con) throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id = -1;
        try {
            ps = con.prepareStatement("INSERT INTO employee(lastname,firstname,birth_date) VALUES(?,?,?)",Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, employee.getLastname());
            ps.setString(2, employee.getFirstname());
            ps.setDate(3, employee.getBirth_date());
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            id = rs.getInt(1);
        } finally {
            ResourcesManager.closeResources(rs, ps);
        }
        return id;
    }
    
    public void update(Employee employee, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("UPDATE employee SET lastname=?,firstname=?,birth_date=? WHERE employee_id=?");
            ps.setString(1, employee.getLastname());
            ps.setString(2, employee.getFirstname());
            ps.setDate(3, employee.getBirth_date());
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }

    public void delete(int employee_id, Connection con) throws SQLException {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM employee WHERE employee_id=?");
            ps.setInt(1, employee_id);
            ps.executeUpdate();
        } finally {
            ResourcesManager.closeResources(null, ps);
        }
    }
}

