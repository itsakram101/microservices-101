package com.example.demomicroservicestudent.service;

import com.example.demomicroservicestudent.feignClient.AddressFeignClient;
import com.example.demomicroservicestudent.response.AddressResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonService {

    Logger logger = LoggerFactory.getLogger(CommonService.class);

    long count = 1;

    @Autowired
    private AddressFeignClient addressFeignClient;

    @CircuitBreaker(name = "demoMicroserviceAddress", fallbackMethod = "fallbackGetAddressById")
    public AddressResponse getAddressById(long addressId){
        //mono is in the reactive programming
        //Mono<AddressResponse> addressResponseMono = webClient.get().uri("/getById/" + addressId)
        //.retrieve().bodyToMono(AddressResponse.class);
        //return addressResponseMono.block();


        // to see how many times we are logging - for testing purposes only
        logger.info("count = " + count);
        count++;

        AddressResponse addressResponse = addressFeignClient.getById(addressId);

        return addressResponse;
    }

    public AddressResponse fallbackGetAddressById(long addressId, Throwable throwable){
        logger.error("Error = " + throwable);

        return new AddressResponse();
    }
}
