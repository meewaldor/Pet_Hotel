/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 *
 * @author Admin
 */
public class BoardingDTO {

    private String boardingId;
    private String name;
    private double rate;
    private String[] description;
    private String img;
    private double length;
    private double height;
    private double width;
    private double maxWeight;
    private boolean status;
    private float price;
    private ArrayList<FeedbackDTO> feedback;

    public BoardingDTO() {
    }

    public BoardingDTO(String boardingId, String name, double rate, String[] description, String img, Double length, double height, double width, double maxWeight, boolean status, float price,ArrayList<FeedbackDTO> feedback) {
        this.boardingId = boardingId;
        this.name = name;
        this.rate = rate;
        this.description = description;
        this.img = img;
        this.length = length;
        this.height = height;
        this.width = width;
        this.maxWeight = maxWeight;
        this.status = status;
        this.price = price;
        this.feedback = feedback;
    }
    public BoardingDTO(String boardingId, String name, double rate, String[] description, String img, Double length, double height, double width, double maxWeight, boolean status, float price) {
        this.boardingId = boardingId;
        this.name = name;
        this.rate = rate;
        this.description = description;
        this.img = img;
        this.length = length;
        this.height = height;
        this.width = width;
        this.maxWeight = maxWeight;
        this.status = status;
        this.price = price;
    }

    public BoardingDTO(String boardingId, String name, String img) {
        this.boardingId = boardingId;
        this.name = name;
        this.img = img;
    }
    

    public String getBoardingId() {
        return boardingId;
    }

    public void setBoardingId(String boardingId) {
        this.boardingId = boardingId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String[] getDescription() {
        return description;
    }

    public void setDescription(String[] description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public ArrayList<FeedbackDTO> getFeedback() {
        return feedback;
    }

    public void setFeedback(ArrayList<FeedbackDTO> feedback) {
        this.feedback = feedback;
    }

    

}
