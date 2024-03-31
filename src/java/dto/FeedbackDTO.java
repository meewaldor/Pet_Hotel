/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author Admin
 */
public class FeedbackDTO {
    private CustomerDTO customer;
    private String orderID;
    private String boardingID;
    private String feedback;
    private String reply_feedback;

    public FeedbackDTO() {
    }

    public FeedbackDTO(CustomerDTO customer, String orderID, String boardingID, String feedback, String reply_feedback) {
        this.customer = customer;
        this.orderID = orderID;
        this.boardingID = boardingID;
        this.feedback = feedback;
        this.reply_feedback = reply_feedback;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customerID) {
        this.customer = customer;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getBoardingID() {
        return boardingID;
    }

    public void setBoardingID(String boardingID) {
        this.boardingID = boardingID;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getReply_feedback() {
        return reply_feedback;
    }

    public void setReply_feedback(String reply_feedback) {
        this.reply_feedback = reply_feedback;
    }
    
    
}
