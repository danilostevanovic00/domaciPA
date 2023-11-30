/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.rest;

import com.fink.projectpa.data.Product;
import com.fink.projectpa.exception.WarehouseException;
import com.fink.projectpa.service.ProductService;
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
@Path("product")
public class ProductRest {
    private final ProductService productService = ProductService.getInstance();
    
    @GET
    @Path("/{product_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product getProductById(@PathParam("product_id") int product_id) throws WarehouseException {
        return productService.findProduct(product_id);
    }
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getAllProduct() throws WarehouseException {
        return productService.getAllProduct();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addProduct(Product product) throws WarehouseException{
            productService.addNewProduct(product);
            return Response.ok().build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProduct(Product product) throws WarehouseException {
            productService.updateProduct(product);
            return Response.ok().build();
    }
    
    @DELETE
    @Path("/{product_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProduct(@PathParam("product_id") int product_id) throws WarehouseException {
            productService.deleteProduct(product_id);
            return Response.ok().build();
    }
}
