package com.example.packagingAndDelivery.controller;

import com.example.packagingAndDelivery.client.AuthorizationClient;
import com.example.packagingAndDelivery.exception.InvalidTokenException;
import com.example.packagingAndDelivery.service.PackageAndDeliveryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Packaging and delivery endpoint")
@CrossOrigin
public class PackageAndDeliveryController {
    @Autowired
    public PackageAndDeliveryService pd;

    @Autowired
    AuthorizationClient authorizationClient;

    @GetMapping("/GetPackagingDeliveryCharge/{componentType}/{count}")
    @Operation(summary="Returns the packaging and delivery charge", description = "Takes component type and quantity and calculates the packaging and delivery charge of the product/products")
    public @ApiResponse(description="Total charge of packaging and delivery") int getPackageAndDeliveryCharge(@Parameter(description = "Takes authorization token")@RequestHeader(name = "Authorization") String token, @Parameter(description="Takes component type")@PathVariable String componentType, @Parameter(description="Takes count")@PathVariable int count) {
        if (!authorizationClient.validate(token)) {
            throw new InvalidTokenException("You are not allowed to access this resource");
        }
        return pd.packageAndDeliveryCharge(componentType,count);
    }
}
