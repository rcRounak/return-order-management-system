package com.example.componentProcessing.model;

import java.util.Date;

public class CompleteProcessing {

    private long requestID;
    private String creditCardNumber;
    private int creditLimit;
    private String dateOfDelivery;

    public CompleteProcessing(){

    }

    public CompleteProcessing(long requestID, String creditCardNumber, int creditLimit, String dateOfDelivery) {
        this.requestID = requestID;
        this.creditCardNumber = creditCardNumber;
        this.creditLimit = creditLimit;
        this.dateOfDelivery = dateOfDelivery;
    }

    public long getRequestID() {
        return requestID;
    }

    public void setRequestID(long requestID) {
        this.requestID = requestID;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public int getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(int creditLimit) {
        this.creditLimit = creditLimit;
    }

    public String getDateOfDelivery() {
        return dateOfDelivery;
    }

    public void setDateOfDelivery(String dateOfDelivery) {
        this.dateOfDelivery = dateOfDelivery;
    }

    @Override
    public String toString() {
        return "CompleteProcessing{" +
                "requestID=" + requestID +
                ", creditCardNumber='" + creditCardNumber + '\'' +
                ", creditLimit=" + creditLimit +
                ", dateOfDelivery='" + dateOfDelivery + '\'' +
                '}';
    }
}
