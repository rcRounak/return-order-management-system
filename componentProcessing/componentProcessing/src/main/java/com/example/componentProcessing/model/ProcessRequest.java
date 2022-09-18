package com.example.componentProcessing.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ProcessRequest {

    @Id
    private String name;
    private String contactNumber;
    private String componentType;
    private String componentName;
    private int quantity;

    public ProcessRequest() {}

    public ProcessRequest(String name, String contactNumber, String componentType, String componentName, int quantity) {
        this.name = name;
        this.contactNumber = contactNumber;
        this.componentType = componentType;
        this.componentName = componentName;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getComponentType() {
        return componentType;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "processRequest{" +
                    "Name='" + name + '\'' +
                    ", contactNumber='" + contactNumber + '\'' +
                    ", componentType='" + componentType + '\'' +
                    ", componentName='" + componentName + '\'' +
                    ", quantity=" + quantity +
                    '}';
    }

}
