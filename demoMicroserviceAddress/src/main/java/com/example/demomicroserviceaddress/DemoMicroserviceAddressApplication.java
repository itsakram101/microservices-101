package com.example.demomicroserviceaddress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DemoMicroserviceAddressApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoMicroserviceAddressApplication.class, args);
    }

}
