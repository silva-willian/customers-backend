package com.customers.backend.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
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

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date register_date;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date last_update_date;
}