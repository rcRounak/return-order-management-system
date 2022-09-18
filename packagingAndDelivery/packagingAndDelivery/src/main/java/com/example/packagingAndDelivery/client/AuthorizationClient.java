package com.example.packagingAndDelivery.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="Auth-Service", url="http://localhost:8080")
public interface AuthorizationClient {
    @GetMapping(value = "/validate")
    public boolean validate(@RequestHeader(name = "Authorization") String token);
}
