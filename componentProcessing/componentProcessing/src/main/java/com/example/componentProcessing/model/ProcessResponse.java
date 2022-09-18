package com.example.componentProcessing.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class ProcessResponse {
    @Id
    private long requestID;
    private int processingCharge;
    private int packagingAndDeliveryCharge;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dateOfDelivery;

    public ProcessResponse() {}

    public ProcessResponse(long requestID, int processingCharge, int packagingAndDeliveryCharge, Date dateOfDelivery) {
        this.requestID = requestID;
        this.processingCharge = processingCharge;
        this.packagingAndDeliveryCharge = packagingAndDeliveryCharge;
        this.dateOfDelivery = dateOfDelivery;
    }

    public long getRequestID() {
        return requestID;
    }

    public void setRequestID(long requestID) {
        this.requestID = requestID;
    }

    public int getProcessingCharge() {
        return processingCharge;
    }

    public void setProcessingCharge(int processingCharge) {
        this.processingCharge = processingCharge;
    }

    public int getPackagingAndDeliveryCharge() {
        return packagingAndDeliveryCharge;
    }

    public void setPackagingAndDeliveryCharge(int packagingAndDeliveryCharge) {
        this.packagingAndDeliveryCharge = packagingAndDeliveryCharge;
    }

    public Date getDateOfDelivery() {
        return dateOfDelivery;
    }

    public void setDateOfDelivery(Date dateOfDelivery) {
        this.dateOfDelivery = dateOfDelivery;
    }

    @Override
    public String toString() {
        return "processResponse{" +
                "requestID=" + requestID +
                ", processingCharge=" + processingCharge +
                ", packagingAndDeliveryCharge=" + packagingAndDeliveryCharge +
                ", dateOfDelivery=" + dateOfDelivery +
                '}';
    }
}
