package com.customers.backend.domain.models;

import com.customers.backend.domain.contracts.repositories.CustomersRepository;
import com.customers.backend.domain.entities.response.CustomersGetAllResponse;
import com.customers.backend.domain.entities.response.CustomersGetByIdResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerModel {

    @Autowired
    CustomersRepository repository;

    public CustomersGetAllResponse get(Integer start, Integer limit, String sort, String desc) {
        return repository.get(start, limit, sort, desc);
    }

    public CustomersGetByIdResponse get(Integer id) {
        return repository.get(id);
    }


}
