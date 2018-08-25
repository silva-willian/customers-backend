package com.customers.backend.domain.contracts.repositories;

import com.customers.backend.domain.entities.response.CustomersGetAllResponse;

public interface CustomersRepository {
    CustomersGetAllResponse get(Integer start, Integer limit);
}
