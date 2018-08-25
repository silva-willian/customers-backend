package com.customers.backend.infra.services;

import com.customers.backend.domain.entities.Customer;
import com.customers.backend.domain.entities.response.Metadata;
import com.customers.backend.domain.entities.response.MetadataType;
import com.customers.backend.infra.contracts.services.MetadataService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MetadataServiceImplTest {

    MetadataService metadataService = new MetadataServiceImpl();

    public List<Customer> getContent() {
        List<Customer> customers = new ArrayList<>();
        customers.add(Customer.builder().id(1).name("Name").sex("M").cel_ddi("55").cel_ddd("11").cel_number("987654321").build());
        customers.add(Customer.builder().id(2).name("Name").sex("M").cel_ddi("55").cel_ddd("11").cel_number("987654321").build());
        customers.add(Customer.builder().id(3).name("Name").sex("M").cel_ddi("55").cel_ddd("11").cel_number("987654321").build());
        customers.add(Customer.builder().id(4).name("Name").sex("M").cel_ddi("55").cel_ddd("11").cel_number("987654321").build());
        customers.add(Customer.builder().id(5).name("Name").sex("M").cel_ddi("55").cel_ddd("11").cel_number("987654321").build());
        customers.add(Customer.builder().id(6).name("Name").sex("M").cel_ddi("55").cel_ddd("11").cel_number("987654321").build());
        customers.add(Customer.builder().id(7).name("Name").sex("M").cel_ddi("55").cel_ddd("11").cel_number("987654321").build());
        customers.add(Customer.builder().id(8).name("Name").sex("M").cel_ddi("55").cel_ddd("11").cel_number("987654321").build());
        customers.add(Customer.builder().id(9).name("Name").sex("M").cel_ddi("55").cel_ddd("11").cel_number("987654321").build());
        customers.add(Customer.builder().id(10).name("Name").sex("M").cel_ddi("55").cel_ddd("11").cel_number("987654321").build());
        customers.add(Customer.builder().id(11).name("Name").sex("M").cel_ddi("55").cel_ddd("11").cel_number("987654321").build());
        customers.add(Customer.builder().id(12).name("Name").sex("M").cel_ddi("55").cel_ddd("11").cel_number("987654321").build());

        return customers;
    }

    @Test
    public void fromList() {

        List<Customer> customers = getContent();

        Metadata metadata = metadataService.fromList(0, 10, customers.size(), customers);

        assertNotNull(metadata);
        assertEquals(0, metadata.getStart());
        assertEquals(10, metadata.getLimit());
        assertEquals(customers.size(), metadata.getTotal());
        assertEquals(MetadataType.LIST, metadata.getType());
    }

}