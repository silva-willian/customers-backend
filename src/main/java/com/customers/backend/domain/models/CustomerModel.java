package com.customers.backend.domain.models;

import com.customers.backend.domain.contracts.repositories.CustomersRepository;
import com.customers.backend.domain.entities.response.CustomersGetAllResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerModel {

    @Autowired
    CustomersRepository repository;

    public CustomersGetAllResponse get(Integer start, Integer limit) {
        return repository.get(start, limit);
    }

}
