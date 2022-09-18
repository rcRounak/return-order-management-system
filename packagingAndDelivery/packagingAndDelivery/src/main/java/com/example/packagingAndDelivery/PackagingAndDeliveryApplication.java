package com.example.packagingAndDelivery;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@OpenAPIDefinition(info=@Info(title="Packaging and Delivery API", version="1.0", description = "This API calculates the packaging and delivery charge for products"))
public class PackagingAndDeliveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(PackagingAndDeliveryApplication.class, args);
	}

}
