package com.kristoffer.resttest.datainitializer;

import com.kristoffer.resttest.entities.CustomerEntity;
import com.kristoffer.resttest.repository.CustomerRepository;
import com.kristoffer.resttest.services.CustomerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class CustomerDataInitializer implements CommandLineRunner {

    private CustomerRepository customerRepository;

    public CustomerDataInitializer(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    private List<String> fName = List.of("Kalle", "Pelle", "Janne", "Musse");
    private List<String> lName = List.of("Pigg", "Anka", "Långben", "Svanslös");

    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i < 10; i++) {
            String firstName = fName.get(ThreadLocalRandom.current().nextInt(fName.size()-1));
            String lastName = lName.get(ThreadLocalRandom.current().nextInt(lName.size()-1));
            customerRepository.save(new CustomerEntity(null, firstName, lastName));
        }
    }
}
