/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.rest;

import com.fink.projectpa.data.Shipper;
import com.fink.projectpa.exception.WarehouseException;
import com.fink.projectpa.service.ShipperService;
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
@Path("shipper")
public class ShipperRest {
    private final ShipperService shipperService = ShipperService.getInstance();
    
    @GET
    @Path("/{shipper_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Shipper getShipperById(@PathParam("shipper_id") int shipper_id) throws WarehouseException {
        return shipperService.findShipper(shipper_id);
    }
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Shipper> getAllShippers() throws WarehouseException {
        return shipperService.getAllShipper();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addShipper(Shipper shipper) throws WarehouseException{
            shipperService.addNewShipper(shipper);
            return Response.ok().build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateShipper(Shipper shipper) throws WarehouseException {
            shipperService.updateShipper(shipper);
            return Response.ok().build();
    }
    
    @DELETE
    @Path("/{shipper_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteShipper(@PathParam("shipper_id") int shipper_id) throws WarehouseException {
            shipperService.deleteShipper(shipper_id);
            return Response.ok().build();
    }
}
