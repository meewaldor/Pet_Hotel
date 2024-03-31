
package dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 *
 * @author Admin
 */
public class OrderDTO {

    private String orderId;
    private Timestamp createdDate;
    private String status;
    private double total;
    private VoucherDTO voucher;
    private CustomerDTO customer;
    private OrderBoardingDetailDTO orderBoardingDetail;
    private ArrayList<OrderServiceDetailDTO> orderServiceDetailList;
    private TreeMap<Timestamp,String> statusRecords;

    public OrderDTO() {
    }

    public OrderDTO(String orderId, Timestamp createdDate, String status, double total, VoucherDTO voucher, CustomerDTO customer, 
            OrderBoardingDetailDTO orderBoardingDetail, ArrayList<OrderServiceDetailDTO> orderServiceDetailList, TreeMap<Timestamp,String> statusRecords) {
        this.orderId = orderId;
        this.createdDate = createdDate;
        this.status = status;
        this.total = total;
        this.voucher = voucher;
        this.customer = customer;
        this.orderBoardingDetail = orderBoardingDetail;
        this.orderServiceDetailList = orderServiceDetailList;
        this.statusRecords = statusRecords;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public VoucherDTO getVoucher() {
        return voucher;
    }

    public void setVoucher(VoucherDTO voucher) {
        this.voucher = voucher;
    }

    public CustomerDTO getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDTO customer) {
        this.customer = customer;
    }

    public OrderBoardingDetailDTO getOrderBoardingDetail() {
        return orderBoardingDetail;
    }

    public void setOrderBoardingDetail(OrderBoardingDetailDTO orderBoardingDetail) {
        this.orderBoardingDetail = orderBoardingDetail;
    }

    public ArrayList<OrderServiceDetailDTO> getOrderServiceDetailList() {
        return orderServiceDetailList;
    }

    public void setOrderServiceDetailList(ArrayList<OrderServiceDetailDTO> orderServiceDetailList) {
        this.orderServiceDetailList = orderServiceDetailList;
    }

    public TreeMap<Timestamp, String> getStatusRecords() {
        return statusRecords;
    }

    public void setStatusRecords(TreeMap<Timestamp, String> statusRecords) {
        this.statusRecords = statusRecords;
    }
    

}
