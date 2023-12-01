/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.rest;

import com.fink.projectpa.data.Customer;
import com.fink.projectpa.data.Employee;
import com.fink.projectpa.data.Product;
import com.fink.projectpa.exception.WarehouseException;
import com.fink.projectpa.service.AdvancedService;
import java.util.List;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
/**
 *
 * @author danil
 */
@Path("advanced")
public class AdvancedRest {
    private final AdvancedService advancedService = AdvancedService.getInstance();
    
    @GET
    @Path("/2/{supplier_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getAllProductBySupplierID(@PathParam("supplier_id") int supplier_id) throws WarehouseException {
        return advancedService.getAllProductBySupplierID(supplier_id);
    }
    
    @GET
    @Path("/3/{shipper_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getAllProductByShipperID(@PathParam("shipper_id") int shipper_id) throws WarehouseException {
        return advancedService.getAllProductByShipperID(shipper_id);
    }
    @GET
    @Path("/4")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Integer> getPriceOfAllOrders() throws WarehouseException {
        return advancedService.getPriceOfAllOrders();
    }
    
    @GET
    @Path("/5/{customer_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Integer> getPriceOfAllOrdersByCustomer(@PathParam("customer_id") int customer_id) throws WarehouseException {
        return advancedService.getPriceOfAllOrdersByCustomer(customer_id);
    }
    
    @GET
    @Path("/6/{shipper_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Integer> getPriceOfAllOrdersByShipper(@PathParam("shipper_id") int shipper_id) throws WarehouseException {
        return advancedService.getPriceOfAllOrdersByShipper(shipper_id);
    }
    
    @GET
    @Path("/7/{supplier_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Integer> getPriceOfAllOrdersBySupplier(@PathParam("supplier_id") int supplier_id) throws WarehouseException {
        return advancedService.getPriceOfAllOrdersBySupplier(supplier_id);
    }
    
    @GET
    @Path("/8")
    @Produces(MediaType.APPLICATION_JSON)
    public Employee getEmployeeThatSoldMostByPrice() throws WarehouseException {
        return advancedService.getEmployeeThatSoldMostByPrice();
    }
    
    @GET
    @Path("/9")
    @Produces(MediaType.APPLICATION_JSON)
    public Product[] getTwoMostSoldProducts() throws WarehouseException {
        return advancedService.getTwoMostSoldProducts();
    }
    
    @GET
    @Path("/10")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer[] getFourCustomersThatOrdersMost() throws WarehouseException {
        return advancedService.getFourCustomersThatOrdersMost();
    }
    
    
    
    
    
}
