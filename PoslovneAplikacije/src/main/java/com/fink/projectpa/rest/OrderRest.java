/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.rest;

import com.fink.projectpa.data.Order;
import com.fink.projectpa.exception.WarehouseException;
import com.fink.projectpa.service.OrderService;
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
@Path("order")
public class OrderRest {
    private final OrderService orderService = OrderService.getInstance();
    
    @GET
    @Path("/{order_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Order getOrderById(@PathParam("order_id") int order_id) throws WarehouseException {
        return orderService.findOrder(order_id);
    }
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Order> getAllOrders() throws WarehouseException {
        return orderService.getAllOrder();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addCustomer(Order order) throws WarehouseException{
            orderService.addNewOrder(order);
            return Response.ok().build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCustomer(Order order) throws WarehouseException {
            orderService.updateOrder(order);
            return Response.ok().build();
    }
    
    @DELETE
    @Path("/{order_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCustomer(@PathParam("order_id") int order_id) throws WarehouseException {
            orderService.deleteOrder(order_id);
            return Response.ok().build();
    }
}
