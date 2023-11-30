/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.rest;

import com.fink.projectpa.data.Supplier;
import com.fink.projectpa.exception.WarehouseException;
import com.fink.projectpa.service.SupplierService;
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
@Path("supplier")
public class SupplierRest {
    private final SupplierService supplierService = SupplierService.getInstance();
    
    @GET
    @Path("/{supplier_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Supplier getSupplierById(@PathParam("supplier_id") int supplier_id) throws WarehouseException {
        return supplierService.findSupplier(supplier_id);
    }
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Supplier> getAllSupplier() throws WarehouseException {
        return supplierService.getAllSupplier();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addSupplier(Supplier supplier) throws WarehouseException{
            supplierService.addNewSupplier(supplier);
            return Response.ok().build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSupplier(Supplier supplier) throws WarehouseException {
            supplierService.updateSupplier(supplier);
            return Response.ok().build();
    }
    
    @DELETE
    @Path("/{supplier_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteSupplier(@PathParam("supplier_id") int supplier_id) throws WarehouseException {
            supplierService.deleteSupplier(supplier_id);
            return Response.ok().build();
    }
}
