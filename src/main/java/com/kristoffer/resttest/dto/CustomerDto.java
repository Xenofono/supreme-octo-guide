package com.kristoffer.resttest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private long id;
    private String firstName;
    private String lastName;
}
