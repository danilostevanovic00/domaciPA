/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.fink.projectpa.data;

import java.io.Serializable;
import java.sql.Date;
/**
 *
 * @author danil
 */
public class Employee implements Serializable {
    private int employee_id = -1;
    private String lastname;
    private String firstname;
    private Date birth_date;
    
    public Employee(){
    }

    public Employee(int employee_id, String lastname, String firstname, Date birth_date) {
        this.employee_id = employee_id;
        this.lastname = lastname;
        this.firstname = firstname;
        this.birth_date = birth_date;
    }
    
    public Employee(String lastname, String firstname, Date birth_date) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.birth_date = birth_date;
    }

    
    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    @Override
    public String toString() {
        return "Employee{" + "employee_id=" + employee_id + ", lastname=" + lastname + ", firstname=" + firstname + ", birth_date=" + birth_date + '}';
    }
    
}
