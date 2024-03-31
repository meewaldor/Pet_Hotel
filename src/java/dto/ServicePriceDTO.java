
package dto;

import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author Admin
 */
public class ServicePriceDTO {
    private String serviceId;
    private String type;
    private double weight;
    private float price;
    private boolean status;

    public ServicePriceDTO() {
    }

    public ServicePriceDTO(String serviceId, String type, double weight, float price, boolean status) {
        this.serviceId = serviceId;
        this.type = type;
        this.weight = weight;
        this.price = price;
        this.status = status;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}
