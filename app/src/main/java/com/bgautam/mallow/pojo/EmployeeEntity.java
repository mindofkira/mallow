
package com.bgautam.mallow.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

// Value Object classes
public class EmployeeEntity implements Serializable{

    @SerializedName("employee")
    @Expose
    private List<Employee> employee = null;

    public List<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
    }

    public EmployeeEntity withEmployee(List<Employee> employee) {
        this.employee = employee;
        return this;
    }

}
