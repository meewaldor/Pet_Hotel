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
public class TransactionDTO {
    private String transactionID;
    private OrderDTO order;
    private CustomerDTO customer;
    private Timestamp createdTime;
    private double value;

    public TransactionDTO() {
    }

    public TransactionDTO(String transactionID, OrderDTO order, CustomerDTO customer, Timestamp createdTime, double value) {
        this.transactionID = transactionID;
        this.order = order;
        this.customer = customer;
        this.createdTime = createdTime;
        this.value = value;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public OrderDTO getOrder() {
        return order;
    }

    public void setOrder(OrderDTO order) {
        this.order = order;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
    
    
}
