/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.rest;

import com.fink.projectpa.data.Customer;
import com.fink.projectpa.exception.WarehouseException;
import com.fink.projectpa.service.CustomerService;
import java.util.List;
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
@Path("customer")
public class CustomerRest {
    private final CustomerService customerService = CustomerService.getInstance();
    
    @GET
    @Path("/{customer_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer getCustomerById(@PathParam("customer_id") int customer_id) throws WarehouseException {
        return customerService.findCustomer(customer_id);
    }
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> getAllCustomer() throws WarehouseException {
        return customerService.getAllCustomers();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCustomer(Customer customer) throws WarehouseException{
            customerService.addNewCustomer(customer);
            return Response.ok().build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCustomer(Customer customer) throws WarehouseException {
            customerService.updateCustomer(customer);
            return Response.ok().build();
    }
    
    @DELETE
    @Path("/{customer_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCusomer(@PathParam("customer_id") int customer_id) throws WarehouseException {
            customerService.deleteCustomer(customer_id);
            return Response.ok().build();
    }
}
