package com.example.demomicroserviceaddress.response;

import com.example.demomicroserviceaddress.entity.Address;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressResponse {

    private Long addressId;
    private String street;
    private String city;

    public AddressResponse(Address address){
        this.addressId = address.getId();
        this.city = address.getCity();
        this.street = address.getStreet();
    }
}
