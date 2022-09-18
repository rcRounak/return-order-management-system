package com.example.componentProcessing.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="PackageAndDelivery-Service", url="http://localhost:8082")
public interface PackageAndDeliveryClient {
    @GetMapping("/GetPackagingDeliveryCharge/{componentType}/{count}")
    int getPackageAndDeliveryCharge(@RequestHeader(name = "Authorization") String token, @PathVariable String componentType, @PathVariable int count);
}
