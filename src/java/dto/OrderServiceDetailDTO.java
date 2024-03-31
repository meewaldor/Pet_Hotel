/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.sql.Timestamp;

/**
 *
 * @author Admin
 */
public class OrderServiceDetailDTO {
    private String orderId;
    private ServiceDTO service;
    private PetDTO pet;
    private Timestamp time;
    private double unit_price;

    public OrderServiceDetailDTO() {
    }

    public OrderServiceDetailDTO(String orderId, ServiceDTO service, PetDTO pet,Timestamp time,double unit_price) {
        this.orderId = orderId;
        this.service = service;
        this.pet = pet;
        this.time = time;
        this.unit_price = unit_price;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) {
        this.unit_price = unit_price;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public ServiceDTO getService() {
        return service;
    }

    public void setService(ServiceDTO service) {
        this.service = service;
    }

    public PetDTO getPet() {
        return pet;
    }

    public void setPet(PetDTO pet) {
        this.pet = pet;
    }
    
    
    
}