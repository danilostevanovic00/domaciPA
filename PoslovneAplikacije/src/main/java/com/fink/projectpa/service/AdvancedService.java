/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.service;

import com.fink.projectpa.dao.OrderDao;
import com.fink.projectpa.data.Customer;
import com.fink.projectpa.data.Employee;
import com.fink.projectpa.data.Order;
import com.fink.projectpa.data.OrderDetail;
import com.fink.projectpa.data.Product;
import com.fink.projectpa.data.ResourcesManager;
import com.fink.projectpa.dto.CustomerOrdersDto;
import com.fink.projectpa.exception.WarehouseException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author danil
 */
public class AdvancedService {
    private static final AdvancedService instance = new AdvancedService();

    private AdvancedService() {
    }

    public static AdvancedService getInstance() {
        return instance;
    }
    
    public List<CustomerOrdersDto> getCustomersAndOrders() throws WarehouseException{
       List<CustomerOrdersDto> result = new ArrayList<>();
        try {
            List<Customer> customers = CustomerService.getInstance().getAllCustomers();
            Collections.sort(customers, (c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));

            for (Customer customer : customers) {
                List<Integer> orderIds = new ArrayList<>();
                List<Order> orders = AdvancedService.getInstance().getOrdersByCustomerId(customer.getCustomer_id());
                for (Order order : orders) {
                    orderIds.add(order.getOrder_id());
                }

                result.add(new CustomerOrdersDto(customer.getName(), orderIds));
            }
        } catch(Exception e) {
            throw new WarehouseException("Failed to get all customers and their orders ", e);
        }
        return result;
    }
    
    public List<Product> getAllProductBySupplierID(int supplier_id) throws WarehouseException{
        List<Product> products = null;
        List<Product> productsBySupplier = new ArrayList<>();
        try{
            products = ProductService.getInstance().getAllProduct();
            for (Product p : products){
                if(p.getProduct_id()==supplier_id){
                    productsBySupplier.add(p);
                }
            }
        }catch (Exception e) {
            throw new WarehouseException("Failed to get all products by supplier id ", e);
        }
        return productsBySupplier;
    }
    
    public List<Product> getAllProductByShipperID(int shipper_id) throws WarehouseException{
        List<Order> orders = null;
        List<Order> ordersBtShipper = new ArrayList<>();
        List<Product> products = new ArrayList<>();
        List<OrderDetail> orderDetails= null;
        List<OrderDetail> orderDetailsByShipper= new ArrayList<>();
        try{
            orders = OrderService.getInstance().getAllOrder();
            for (Order o : orders){
                if (o.getShipper().getShipper_id()==shipper_id){
                    ordersBtShipper.add(o);
                }
            }
            
            orderDetails= OrderDetailService.getInstance().getAllOrderDetail();
            
            for (Order o: ordersBtShipper){
                for (OrderDetail od : orderDetails){
                    if (o.getOrder_id()==od.getOrder().getOrder_id()){
                        orderDetailsByShipper.add(od);
                    }
                }
            }
            
            for (OrderDetail od: orderDetailsByShipper){
                Product p = ProductService.getInstance().findProduct(od.getProduct().getProduct_id());
                products.add(p);
            }
            
        }catch (Exception e) {
            throw new WarehouseException("Failed to get all customers and their orders ", e);
        }
        return products;
    }
    
    public int getPriceOfAllOrders()throws WarehouseException{
        int sum=0;
        List<OrderDetail> orderDetails= null;
        try{
            orderDetails= OrderDetailService.getInstance().getAllOrderDetail();
             for (OrderDetail od: orderDetails){
                 sum+= od.getProduct().getPrice_per_unit()*od.getQuantity();
             }
        }catch (Exception e) {
            throw new WarehouseException("Failed to get all price of all orders combined ", e);
        }
        return sum;
    }
    
    public int getPriceOfAllOrdersByCustomer(int customer_id)throws WarehouseException{
        int sum=0;
        List<OrderDetail> orderDetails= null;
        List<OrderDetail> orderDetailsByCustomer= new ArrayList<>();
        List<Order> orders= null;
        List<Order> ordersByCustomer= new ArrayList<>();
        try{
            orderDetails = OrderDetailService.getInstance().getAllOrderDetail();
            orders= OrderService.getInstance().getAllOrder();
            for (Order o: orders){
                if (o.getCustomer().getCustomer_id()==customer_id){
                    ordersByCustomer.add(o);
                }
            }
            
            for (Order o: ordersByCustomer){
                for(OrderDetail od: orderDetails){
                    if (o.getOrder_id()==od.getOrder().getOrder_id()){
                        orderDetailsByCustomer.add(od);
                        break;
                    }
                }
            }
            
             for (OrderDetail od: orderDetailsByCustomer){
                 sum+= od.getProduct().getPrice_per_unit()*od.getQuantity();
             }
        }catch (Exception e) {
            throw new WarehouseException("Failed to get all price of all orders by customer with id  "+ customer_id, e);
        }
        return sum;
    }
    
    public int getPriceOfAllOrdersByShipper(int shipper_id)throws WarehouseException{
        int sum=0;
        List<OrderDetail> orderDetails= null;
        List<OrderDetail> orderDetailsByShipper= new ArrayList<>();
        List<Order> orders= null;
        List<Order> ordersByShipper= new ArrayList<>();
        try{
            orderDetails = OrderDetailService.getInstance().getAllOrderDetail();
            orders= OrderService.getInstance().getAllOrder();
            for (Order o: orders){
                if (o.getShipper().getShipper_id()==shipper_id){
                    ordersByShipper.add(o);
                }
            }
            
            for (Order o: ordersByShipper){
                for(OrderDetail od: orderDetails){
                    if (o.getOrder_id()==od.getOrder().getOrder_id()){
                        orderDetailsByShipper.add(od);
                        break;
                    }
                }
            }
            
             for (OrderDetail od: orderDetailsByShipper){
                 sum+= od.getProduct().getPrice_per_unit()*od.getQuantity();
             }
        }catch (Exception e) {
            throw new WarehouseException("Failed to get all price of all orders delivered by shipper with id "+ shipper_id, e);
        }
        return sum;
    }
    
    public int getPriceOfAllOrdersBySupplier(int supplier_id)throws WarehouseException{
        int sum=0;
        List<OrderDetail> orderDetails= null;
        List<OrderDetail> orderDetailsBySupplier= new ArrayList<>();
        List<Product> product= null;
        List<Product> productBySupplier= new ArrayList<>();
        try{
            orderDetails = OrderDetailService.getInstance().getAllOrderDetail();
            product= ProductService.getInstance().getAllProduct();
            for (Product p: product){
                if (p.getSupplier().getSupplier_id()==supplier_id){
                    productBySupplier.add(p);
                }
            }
            
            for (Product p: productBySupplier){
                for(OrderDetail od: orderDetails){
                    if (p.getProduct_id()==od.getProduct().getProduct_id()){
                        orderDetailsBySupplier.add(od);
                        break;
                    }
                }
            }
            
             for (OrderDetail od: orderDetailsBySupplier){
                 sum+= od.getProduct().getPrice_per_unit()*od.getQuantity();
             }
        }catch (Exception e) {
            throw new WarehouseException("Failed to get all price of all orders supplied by supplier with id " +supplier_id, e);
        }
        return sum;
    }
    
    public Employee getEmployeeThatSoldMostByPrice () throws WarehouseException{
        Employee employee=null;
        List<Employee> employees= null;
        int sumMain=0;
        try{
            employees=EmployeeService.getInstance().getAllEmployees();
            for (Employee e: employees){
                int sum=0;
                List<OrderDetail> orderDetails= OrderDetailService.getInstance().getAllOrderDetail();
                List<OrderDetail> orderDetailsByEmployee= new ArrayList<>();
                List<Order> orders= OrderService.getInstance().getAllOrder();
                List<Order> orderByEmployee= new ArrayList<>();
                
                for (Order o: orders){
                    if (o.getEmployee().getEmployee_id()==e.getEmployee_id()){
                        orderByEmployee.add(o);
                    }
                }

                for (Order o: orderByEmployee){
                    for(OrderDetail od: orderDetails){
                        if (o.getOrder_id()==od.getOrder().getOrder_id()){
                            orderDetailsByEmployee.add(od);
                            break;
                        }
                    }
                }

                for (OrderDetail od: orderDetailsByEmployee){
                    sum+= od.getProduct().getPrice_per_unit()*od.getQuantity();
                }
                
                if (sum>sumMain){
                    sumMain=sum;
                    employee =e;
                }
            }
        }catch (Exception e) {
            throw new WarehouseException("Failed to get employee that earned most ", e);
        }
        return employee;
    }
    
    public Product[] getTwoMostSoldProducts () throws WarehouseException {
        Product[] result = new Product[2];
        List<Product> products= null;
        List<OrderDetail> orderDetails= null;
        int sum1=0;
        int sum2=0;
        try{
            products =ProductService.getInstance().getAllProduct();
            orderDetails = OrderDetailService.getInstance().getAllOrderDetail();
            for (Product p : products){
                int sum=0;
                for(OrderDetail od : orderDetails){
                    if (p.getProduct_id()==od.getProduct().getProduct_id()){
                        sum++;
                    }
                }
                if(sum>sum1){
                    sum1=sum;
                    result[0]=p;
                }else if(sum>sum2){
                    sum2=sum;
                    result[1]=p;
                }
            }
        }catch (Exception e) {
            throw new WarehouseException("Failed to get employee that earned most ", e);
        }
        return result;
    }
    
    public List<Order> getOrdersByCustomerId(int customer_id) throws WarehouseException{
        Connection con = null;
        List<Order> orders=null;
        List<Order> customer_orders= new ArrayList<>();
        try{
            con = ResourcesManager.getConnection();
            orders= OrderDao.getInstance().find(con);
            for(Order order:orders){
                if(order.getCustomer().getCustomer_id()==customer_id){
                    customer_orders.add(order);
                }
            }
        }catch (SQLException ex) {
            throw new WarehouseException("Failed to find orders with customer id " + customer_id, ex);
        } finally {
            ResourcesManager.closeConnection(con);
        }
        return customer_orders;
    }
    
    public Customer[] getFourCustomersThatOrdersMost ()throws WarehouseException{
        Customer[] customersMost=new Customer[4];
        int[] sumMost=new int[]{0,0,0,0};
        List<Customer> customers=null;
        List<Order> orders=null;
        try{
            customers = CustomerService.getInstance().getAllCustomers();
            orders =OrderService.getInstance().getAllOrder();
            for (Customer c: customers){
                List<Order> customerOrders = new ArrayList<>();
                int sum=0;
                for (Order o: orders){
                    if(c.getCustomer_id()==o.getCustomer().getCustomer_id()){
                        customerOrders.add(o);
                    }
                }
                
                List<OrderDetail> orderDetails = OrderDetailService.getInstance().getAllOrderDetail();
                List<OrderDetail> customerOrderDetail=new ArrayList<>();
                for (Order o: customerOrders){
                    for(OrderDetail od:orderDetails){
                        if (o.getOrder_id()==od.getOrder().getOrder_id()){
                            customerOrderDetail.add(od);
                        }
                    }
                }
                for(OrderDetail od:customerOrderDetail){
                    int curr_sum=od.getQuantity()*od.getProduct().getPrice_per_unit();
                    sum+=curr_sum;
                }
                
                if (sum>sumMost[0]){
                    sumMost[0]=sum;
                    customersMost[0]=c;
                }else if(sum>sumMost[1]){
                    sumMost[1]=sum;
                    customersMost[1]=c;
                }
                else if(sum>sumMost[2]){
                    sumMost[2]=sum;
                    customersMost[2]=c;
                }
                else if(sum>sumMost[3]){
                    sumMost[3]=sum;
                    customersMost[3]=c;
                }
                
            }
            
        }catch (Exception ex) {
            throw new WarehouseException("Failed to find four customers with most sold in total cash amount  ", ex);
        }
        return customersMost;
    }
}
