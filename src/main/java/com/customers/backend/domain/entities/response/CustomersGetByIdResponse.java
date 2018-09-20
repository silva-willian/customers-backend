package com.customers.backend.domain.entities.response;

import com.customers.backend.domain.entities.Customer;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CustomersGetByIdResponse {
    private Metadata metadata;
    private Customer results;
}