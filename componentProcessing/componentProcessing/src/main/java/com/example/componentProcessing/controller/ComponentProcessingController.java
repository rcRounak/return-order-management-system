package com.example.componentProcessing.controller;

import com.example.componentProcessing.client.AuthorizationClient;
import com.example.componentProcessing.client.PackageAndDeliveryClient;
import com.example.componentProcessing.exception.InvalidTokenException;
import com.example.componentProcessing.model.CompleteProcessing;
import com.example.componentProcessing.model.CompleteResponse;
import com.example.componentProcessing.model.ProcessRequest;
import com.example.componentProcessing.model.ProcessResponse;
import com.example.componentProcessing.repository.ProcessRequestRepo;
import com.example.componentProcessing.repository.ProcessResponseRepo;
import com.example.componentProcessing.service.ComponentProcessingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@Tag(name = "Component processing endpoint")
@CrossOrigin
public class ComponentProcessingController {

    ComponentProcessingService cps;

    public ComponentProcessingController(ComponentProcessingService cps) {
        this.cps=cps;
    }

    @Autowired
    ProcessRequestRepo prRepo;

    @Autowired
    ProcessResponseRepo presRepo;

    @Autowired
    PackageAndDeliveryClient pdC;
    @Autowired
    AuthorizationClient authorizationClient;

    @ResponseBody
    @PostMapping("/ProcessDetail")
    @Operation(summary="Returns details of a product", description = "Takes name, contact number, component type, component name and quantity and calculates the charges and date of delivery of the product/products")
    public @ApiResponse(description="RequestId, charges and date of delivery")ResponseEntity<ProcessResponse> processDetails(@Parameter(description = "Takes authorization token")@RequestHeader(name = "Authorization") String token,@RequestBody ProcessRequest pRequest) {
        if (!authorizationClient.validate(token)) {
            throw new InvalidTokenException("You are not allowed to access this resource");
        }
        prRepo.save(pRequest);
        ProcessResponse pResponse = cps.processDetail(pRequest);

        pResponse.setPackagingAndDeliveryCharge(pdC.getPackageAndDeliveryCharge(token,pRequest.getComponentType(),pRequest.getQuantity()));
        presRepo.save(pResponse);
        return new ResponseEntity<>(pResponse, HttpStatus.OK);
    }
    @ResponseBody
    @PostMapping("/CompleteProcessing")
    @Operation(summary="Returns the status of the component processing", description = "Takes credit request id, card details, and date of delivery to display processing status of the product/products")
    public @ApiResponse(description="Message of processing")ResponseEntity<CompleteResponse> completeProcessing(@Parameter(description = "Takes authorization token")@RequestHeader(name = "Authorization") String token, @Parameter(description = "Takes request id, credit card details and date of delivery to process the request") @RequestBody CompleteProcessing cp) {
        if (!authorizationClient.validate(token)) {
            throw new InvalidTokenException("You are not allowed to access this resource");
        }
        return new ResponseEntity<>(cps.message(cp), HttpStatus.OK);
    }


}
