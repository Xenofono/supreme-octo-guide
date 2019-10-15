package com.kristoffer.resttest.models.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRestResponse {

    private long id;
    private String firstName;
    private String lastName;
}
