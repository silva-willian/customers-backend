package com.customers.backend.domain.contracts.repositories;

import com.customers.backend.domain.entities.response.CustomersGetAllResponse;
import com.customers.backend.domain.entities.response.CustomersGetByIdResponse;

public interface CustomersRepository {
    CustomersGetAllResponse get(Integer start, Integer limit, String sort, String desc);
    CustomersGetByIdResponse get(Integer id);
}
