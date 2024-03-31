package dto;

import java.sql.Date;

public class PetDTO {

    private String petId;
    private String name;
    private Date dob;
    private CustomerDTO customer;
    private String type;
    private double weight;
    private String gender;
    private boolean vaccinated;
    private boolean status;
    private String img;

    public PetDTO() {
    }

    public PetDTO(String petId, String name, Date dob, CustomerDTO customer, String type, double weight, String gender, boolean vaccinated, boolean status, String img) {
        this.petId = petId;
        this.name = name;
        this.dob = dob;
        this.customer = customer;
        this.type = type;
        this.weight = weight;
        this.gender = gender;
        this.vaccinated = vaccinated;
        this.status = status;
        this.img = img;
    }

    public String getPetId() {
        return petId;
    }

    public void setPetId(String petId) {
        this.petId = petId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomerId(CustomerDTO customerId) {
        this.customer = customer;
    }

    public String getType() {
        return type;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setType(String type) {
        this.type = type;
    }
    public boolean isVaccinated() {
        return vaccinated;
    }

    public void setVaccinated(boolean vaccinated) {
        this.vaccinated = vaccinated;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
    
    

}
