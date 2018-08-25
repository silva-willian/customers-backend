package com.customers.backend.domain.entities;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
public class Customer {
    private Integer id;
    private String name;
    private String sex;
    private String email;
    private String cel_ddi;
    private String cel_ddd;
    private String cel_number;
    private Date date_birth;
    private Date register_date;
    private Date last_update_date;
}