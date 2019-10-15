package com.kristoffer.resttest;

import com.kristoffer.resttest.entities.CustomerEntity;
import com.kristoffer.resttest.repository.CustomerRepository;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private MockMvc mockMvc;

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
    public void getAllEndpointTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/customer"))
                .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(10)));

    }


}
