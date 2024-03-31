/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class OrderBoardingDetailDTO {
    private String orderId;
    private RoomDTO room;
    private PetDTO pet;
    private Date checkInDate;
    private Date checkOutDate;
    private double unitPrice;
    private double totalPrice;
    private String feedback;

    public OrderBoardingDetailDTO() {
    }

    public OrderBoardingDetailDTO(String orderId, RoomDTO room, PetDTO pet, Date checkInDate, Date checkOutDate, double unitPrice, double totalPrice, String feedback) {
        this.orderId = orderId;
        this.room = room;
        this.pet = pet;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.feedback = feedback;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public RoomDTO getRoom() {
        return room;
    }

    public void setRoom(RoomDTO room) {
        this.room = room;
    }

    public PetDTO getPet() {
        return pet;
    }

    public void setPet(PetDTO pet) {
        this.pet = pet;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
    
    
}