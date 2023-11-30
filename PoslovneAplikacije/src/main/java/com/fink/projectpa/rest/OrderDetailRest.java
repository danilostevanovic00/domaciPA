/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.rest;

import com.fink.projectpa.data.OrderDetail;
import com.fink.projectpa.exception.WarehouseException;
import com.fink.projectpa.service.OrderDetailService;
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
@Path("orderDetail")
public class OrderDetailRest {
    private final OrderDetailService orderDetailService = OrderDetailService.getInstance();
    
    @GET
    @Path("/{order_detail_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public OrderDetail getOrderDetailById(@PathParam("order_detail_id") int order_detail_id) throws WarehouseException {
        return orderDetailService.findOrder(order_detail_id);
    }
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderDetail> getAllOrderDetail() throws WarehouseException {
        return orderDetailService.getAllOrderDetail();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addOrderDetail(OrderDetail orderDetail) throws WarehouseException{
            orderDetailService.addNewOrderDetail(orderDetail);
            return Response.ok().build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateOrderDetail(OrderDetail orderDetail) throws WarehouseException {
            orderDetailService.updateOrderDetail(orderDetail);
            return Response.ok().build();
    }
    
    @DELETE
    @Path("/{order_detail_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteOrderDetail(@PathParam("order_detail_id") int order_detail_id) throws WarehouseException {
            orderDetailService.deleteOrderDetail(order_detail_id);
            return Response.ok().build();
    }
}
