package com.example.demomicroservicestudent.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressResponse {

    private Long addressId;
    private String street;
    private String city;

}
