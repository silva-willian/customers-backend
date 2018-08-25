package com.customers.backend.infra.services;

import com.customers.backend.domain.entities.Customer;
import com.customers.backend.infra.contracts.services.PaginateService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PaginateServiceImplTest {

    PaginateService paginateService = new PaginateServiceImpl();

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
    public void test_not_content() {
        List<Customer> customersFiltered = paginateService.getPage(null, 0, 10);
        assertNull(customersFiltered);
    }

    @Test
    public void test_empty_content() {
        List<Customer> customers = new ArrayList<>();
        List<Customer> customersFiltered = paginateService.getPage(customers, 0, 10);
        assertNull(customersFiltered);
    }

    @Test
    public void test_paginate() {
        List<Customer> customers = getContent();

        List<Customer> customersFiltered = paginateService.getPage(customers, 0, 10);
        assertNotNull(customersFiltered);
        assertEquals(10, customersFiltered.size());
    }


    @Test
    public void test_partial_paginate() {
        List<Customer> customers = getContent();

        List<Customer> customersFiltered = paginateService.getPage(customers, 10, 10);
        assertNotNull(customersFiltered);
        assertEquals(2, customersFiltered.size());
    }
}