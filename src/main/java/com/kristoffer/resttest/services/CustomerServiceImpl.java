package com.kristoffer.resttest.services;

import com.kristoffer.resttest.dto.CustomerDto;
import com.kristoffer.resttest.entities.CustomerEntity;
import com.kristoffer.resttest.models.request.CreateAndUpdateCustomerModel;
import com.kristoffer.resttest.repository.CustomerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    private CustomerDto entityToDto(CustomerEntity entity) {
        CustomerDto customerDto = new CustomerDto();
        BeanUtils.copyProperties(entity, customerDto);
        return customerDto;
    }

    private CustomerEntity dtoToEntity(CustomerDto dto) {
        CustomerEntity entity = new CustomerEntity();
        BeanUtils.copyProperties(dto, entity);
        return entity;
    }


    @Override
    public List<CustomerDto> findAll() {
        List<CustomerEntity> customerEntities = customerRepository.findAll();
        return customerEntities.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<CustomerDto> findById(String id) {
        long idToSearch = Long.parseLong(id);

        return customerRepository.findById(idToSearch)
                .map(this::entityToDto);
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        CustomerEntity entity = dtoToEntity(customerDto);
        CustomerEntity savedEntity = customerRepository.save(entity);
        return entityToDto(savedEntity);
    }

    @Override
    public void deleteAll() {
        customerRepository.deleteAll();;
    }

    @Override
    public CustomerDto save(CustomerDto customerDto) {
        CustomerEntity entity = dtoToEntity(customerDto);

        CustomerEntity savedEntity = customerRepository.save(entity);
        return entityToDto(savedEntity);
    }
}
