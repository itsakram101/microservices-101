package com.example.demomicroserviceaddress.service;

import com.example.demomicroserviceaddress.entity.Address;
import com.example.demomicroserviceaddress.repository.AddressRepository;
import com.example.demomicroserviceaddress.request.CreateAddressRequest;
import com.example.demomicroserviceaddress.response.AddressResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AddressService {

    Logger logger = LoggerFactory.getLogger(AddressService.class);

    @Autowired
    AddressRepository addressRepository;

    public AddressResponse createAddress(CreateAddressRequest createAddressRequest){
        Address address = new Address();
        address.setCity(createAddressRequest.getCity());
        address.setStreet(createAddressRequest.getStreet());

        addressRepository.save(address);
        return new AddressResponse(address);
    }

    public AddressResponse getById(Long id){
        logger.info("Inside getById " + id);
        Address address = addressRepository.findById(id).get();

        return new AddressResponse(address);
    }
}
