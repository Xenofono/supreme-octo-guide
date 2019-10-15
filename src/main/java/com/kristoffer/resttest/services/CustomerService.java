package com.kristoffer.resttest.services;


import com.kristoffer.resttest.dto.CustomerDto;

import java.util.List;
import java.util.Optional;

public interface CustomerService  {

    List<CustomerDto> findAll();
    Optional<CustomerDto> findById(String id);
    CustomerDto createCustomer(CustomerDto customerDto);
    void deleteAll();
    CustomerDto save(CustomerDto customerDto);
}
