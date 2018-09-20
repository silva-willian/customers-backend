package com.customers.backend.app.contracts.services;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.lang.reflect.Type;

public interface PartialContent {

    public String Partial(Object content, String fields) throws JsonProcessingException;
}
