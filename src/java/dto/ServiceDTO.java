package dto;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class ServiceDTO {

    private String serviceId;
    private String name;
    private double rate;
    private String description;
    private String img;
    private boolean status;


    public ServiceDTO() {
    }

    public ServiceDTO(String serviceId, String name, double rate, String description, String img, boolean status) {
        this.serviceId = serviceId;
        this.name = name;
        this.rate = rate;
        this.description = description;
        this.img = img;
        this.status = status;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    
}

