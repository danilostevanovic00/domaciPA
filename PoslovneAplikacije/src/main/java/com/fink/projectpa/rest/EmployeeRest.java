/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.rest;

import com.fink.projectpa.data.Employee;
import com.fink.projectpa.exception.WarehouseException;
import com.fink.projectpa.service.EmployeeService;
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
@Path("employee")
public class EmployeeRest {
    private final EmployeeService employeeService = EmployeeService.getInstance();
    
    @GET
    @Path("/{employee_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Employee getEmployeeById(@PathParam("employee_id") int employee_id) throws WarehouseException {
        return employeeService.findEmployee(employee_id);
    }
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Employee> getAllEmployee() throws WarehouseException {
        return employeeService.getAllEmployees();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEmployee(Employee employee) throws WarehouseException{
            employeeService.addNewEmployee(employee);
            return Response.ok().build();
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateEmployee(Employee employee) throws WarehouseException {
            employeeService.updateEmployee(employee);
            return Response.ok().build();
    }
    
    @DELETE
    @Path("/{employee_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteEmployee(@PathParam("employee_id") int employee_id) throws WarehouseException {
            employeeService.deleteEployee(employee_id);
            return Response.ok().build();
    }
}
