package com.kristoffer.resttest;

import com.kristoffer.resttest.entities.CustomerEntity;
import com.kristoffer.resttest.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;


@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    private List<String> fName = List.of("Kalle", "Pelle", "Janne", "Musse");
    private List<String> lName = List.of("Pigg", "Anka", "Långben", "Svanslös");

    @Before
    public void loadData() {
        customerRepository.deleteAll();

        for (int i = 0; i < 10; i++) {
            String firstName = fName.get(ThreadLocalRandom.current().nextInt(fName.size()-1));
            String lastName = lName.get(ThreadLocalRandom.current().nextInt(lName.size()-1));
            customerRepository.save(new CustomerEntity(null, firstName, lastName));
        }
    }

    @Test
    public void getAllTest() {
        List<CustomerEntity> allEntities = customerRepository.findAll();

        Assert.assertEquals(allEntities.size(), 10);
    }

    @Test
    public void getCustomerById() {
        CustomerEntity entity = new CustomerEntity(null, "koko", "momo");

        customerRepository.save(entity);

        Optional<CustomerEntity> savedEntity = customerRepository.findById(11L);

        savedEntity.ifPresent(customer -> {
            Assert.assertSame(11L, customer.getId());
            Assert.assertEquals( "koko", customer.getFirstName());
            Assert.assertEquals("momo", customer.getLastName());
        });
    }
}
