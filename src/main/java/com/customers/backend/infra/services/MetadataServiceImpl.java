package com.customers.backend.infra.services;

import com.customers.backend.domain.entities.Customer;
import com.customers.backend.domain.entities.response.Metadata;
import com.customers.backend.domain.entities.response.MetadataType;
import com.customers.backend.infra.contracts.services.MetadataService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetadataServiceImpl implements MetadataService {
    @Override
    public Metadata fromList(Integer start, Integer limit, Integer total, List<Customer> list) {
        return Metadata.builder()
                .start(start)
                .limit(limit)
                .total(total)
                .type(MetadataType.fromValue("list")).build();
    }
}
