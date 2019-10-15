package com.kristoffer.resttest.controllers;

import com.kristoffer.resttest.dto.CustomerDto;
import com.kristoffer.resttest.models.request.CreateAndUpdateCustomerModel;
import com.kristoffer.resttest.models.response.CustomerRestResponse;
import com.kristoffer.resttest.services.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    private CustomerRestResponse dtoToResponse(CustomerDto customerDto) {
        CustomerRestResponse customerResponse = new CustomerRestResponse();
        BeanUtils.copyProperties(customerDto, customerResponse);
        return customerResponse;
    }



    @GetMapping
    public ResponseEntity<List<CustomerRestResponse>> getAllCustomers() {
        List<CustomerDto> allCustomerDto = customerService.findAll();
        List<CustomerRestResponse> responseList = allCustomerDto.stream()
                .map(this::dtoToResponse)
                .collect(Collectors.toList());
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerRestResponse> getCustomerById(@PathVariable String id) {
        Optional<CustomerDto> foundCustomer = customerService.findById(id);

        return foundCustomer.map(customerDto -> {
            CustomerRestResponse customerResponse = dtoToResponse(customerDto);
            return new ResponseEntity<>(customerResponse, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PostMapping
    public ResponseEntity<CustomerRestResponse> createCustomer(@Valid @RequestBody CreateAndUpdateCustomerModel createAndUpdateCustomerModel) {
        CustomerDto customerToCreate = new CustomerDto();
        BeanUtils.copyProperties(createAndUpdateCustomerModel, customerToCreate);
        CustomerDto createdCustomer = customerService.createCustomer(customerToCreate);

        CustomerRestResponse customerResponse = new CustomerRestResponse();
        System.out.println(customerResponse);
        BeanUtils.copyProperties(createdCustomer, customerResponse);
        return new ResponseEntity<>(customerResponse, HttpStatus.OK);
    }

}
