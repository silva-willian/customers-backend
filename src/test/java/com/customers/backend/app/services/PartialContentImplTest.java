package com.customers.backend.app.services;

import com.customers.backend.app.contracts.services.PartialContent;
import com.customers.backend.domain.entities.Customer;
import com.customers.backend.domain.entities.response.CustomersGetByIdResponse;
import com.customers.backend.domain.entities.response.Metadata;
import com.customers.backend.domain.entities.response.MetadataType;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

import java.lang.reflect.Type;

public class PartialContentImplTest {

    PartialContent partialContent = new PartialContentImpl();

    @Test
    public void partial() {

        Metadata metadata = Metadata.builder()
                .start(0)
                .limit(0)
                .total(0)
                .type(MetadataType.fromValue("object")).build();

        Customer customer = Customer.builder().id(1).name("Test").build();

        CustomersGetByIdResponse item = CustomersGetByIdResponse.builder()
                .metadata(metadata)
                .results(customer).build();


        String result = null;
        try {
            result = partialContent.Partial(item, "metadata[total],results[][id,name]");

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        assertNotNull(result);

    }
}