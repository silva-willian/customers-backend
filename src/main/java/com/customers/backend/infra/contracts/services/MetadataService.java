package com.customers.backend.infra.contracts.services;

import com.customers.backend.domain.entities.Customer;
import com.customers.backend.domain.entities.response.Metadata;

import java.util.List;

public interface MetadataService {
    Metadata fromList(Integer start, Integer limit, Integer total, List<Customer> list);
    Metadata fromObject();
}
