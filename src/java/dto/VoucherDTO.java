/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

import java.sql.Date;


public class VoucherDTO {
    private String voucherId;
    private Date fromDate;
    private Date toDate;
    private double value;

    public VoucherDTO() {
    }

    public VoucherDTO(String voucherId, Date fromDate, Date toDate, double value) {
        this.voucherId = voucherId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.value = value;
    }

    public String getVoucherId() {
        return voucherId;
    }

    public void setVoucherId(String voucherId) {
        this.voucherId = voucherId;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
    
    
}
