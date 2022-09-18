package com.example.packagingAndDelivery.service;

import org.springframework.stereotype.Service;

import static com.example.packagingAndDelivery.model.PackagingAndDeliveryDetails.*;
import static com.example.packagingAndDelivery.model.PackagingAndDeliveryDetails.deliveryAccessoryItem;

@Service
public class PackageAndDeliveryService {
    public int packageAndDeliveryCharge(String componentType, int count) {
        int sum=0;
        if(count <=0) {
            throw new IllegalArgumentException("Count should be greater than zero");
        }
        if(componentType.equalsIgnoreCase("integral")) {
            sum = integralItem*count+protectiveSheath*count+deliveryIntegralItem*count;
        }
        else if(componentType.equalsIgnoreCase("accessory")) {
            sum = accessoryItem*count+protectiveSheath*count+deliveryAccessoryItem*count;
        } else {
            throw new IllegalArgumentException("Component type should either be Integral or Accessory");
        }
        return sum;
    }
}
