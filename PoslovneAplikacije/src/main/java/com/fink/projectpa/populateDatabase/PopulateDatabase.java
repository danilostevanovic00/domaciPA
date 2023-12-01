/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.populateDatabase;

import com.fink.projectpa.dao.*;
import com.fink.projectpa.data.*;
import com.fink.projectpa.exception.WarehouseException;

import java.sql.Connection;
import java.sql.SQLException;
/**
 *
 * @author danil
 */
public class PopulateDatabase {
    public static void seedDatabase() {
        try (Connection con = ResourcesManager.getConnection()) {
            // Create some customers
            CustomerDao customerDao = CustomerDao.getInstance();
            Customer customer1 =new Customer(1, "Customer1", "Contact1", "Address1", "City1", 34000, "Country1");
            Customer customer2 = new Customer(2, "Customer2", "Contact2", "Address2", "City2", 11000, "Country2");
            customerDao.insert(customer1, con);
            customerDao.insert(customer2, con);

            // Create some employees
            EmployeeDao employeeDao = EmployeeDao.getInstance();
            Employee employee1 = new Employee(1, "LastName1", "FirstName1", java.sql.Date.valueOf("1980-01-01"));
            Employee employee2 = new Employee(2, "LastName2", "FirstName2", java.sql.Date.valueOf("1985-02-02"));
            employeeDao.insert(employee1, con);
            employeeDao.insert(employee2, con);


            // Create some suppliers
            SupplierDao supplierDao = SupplierDao.getInstance();
            Supplier supplier1 = new Supplier(1, "Supplier1", "Contact1", "Address1", "City1", "Country1", "1241412341");
            Supplier supplier2 = new Supplier(2, "Supplier2", "Contact2", "Address2", "City2", "Country2", "1212121212");
            supplierDao.insert(supplier1, con);
            supplierDao.insert(supplier2, con);

            // Create some shippers
            ShipperDao shipperDao = ShipperDao.getInstance();
            Shipper shipper1 = new Shipper(1, "Shipper1", "154353513");
            Shipper shipper2 = new Shipper(2, "Shipper2", "151351311");
            shipperDao.insert(shipper1, con);
            shipperDao.insert(shipper2, con);

            // Create some products
            ProductDao productDao = ProductDao.getInstance();
            Product product1 = new Product(1, "Product1", "1", 100, supplier1);
            Product product2 = new Product(2, "Product2", "2", 150, supplier2);
            productDao.insert(product1, con);
            productDao.insert(product2, con);

            // Create some orders
            OrderDao orderDao = OrderDao.getInstance();
            Order order1 = new Order(1, java.sql.Date.valueOf("2022-01-01"), customer1, employee1, shipper1);
            Order order2 = new Order(2, java.sql.Date.valueOf("2022-02-02"), customer2, employee2, shipper2);
            orderDao.insert(order1, con);
            orderDao.insert(order2, con);

            // Create some order details
            OrderDetailDao orderDetailsDao = OrderDetailDao.getInstance();
            orderDetailsDao.insert(new OrderDetail(1, order1, product1, 10), con);
            orderDetailsDao.insert(new OrderDetail(2, order2, product2, 20), con);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws WarehouseException {
        seedDatabase();
    }
}
